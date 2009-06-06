/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author liufeng
 */
public class SongScheduler {
    private Schedule[][] tentativeSchedule = new Schedule[7][24];
    private Time startTime;

    public SongScheduler( Time firstDay ) {
        startTime = firstDay;
        for (int i = 0; i < 7; i++ )
        {
            for ( int j = 0; j < 24; j++ ) {
                tentativeSchedule[i][j] = getScheduleFromDB(firstDay);
                firstDay = firstDay.getNextHour();
            }
        }
    }

    public void commit(){
        //commit the schedules for each day
        for(int i=0; i < tentativeSchedule.length; i++){
            //commit the schedules for each hour of a day
            for(int j=0; j < tentativeSchedule[i].length; j++){
                //if the schdule is not empty then commit it
                if(!tentativeSchedule[i][j].isEmpty())
                    commitSchedule(tentativeSchedule[i][j]);
            }
        }
    }

    private void commitSchedule(Schedule candidate) {
        //if the schedule that if trying to be committed is a null pointer don't commit
        if(candidate != null){
            try {
                Class.forName("org.sqlite.JDBC");
                Connection connection = DriverManager.getConnection("jdbc:sqlite:song.db");
                PreparedStatement prepStatement = connection.prepareStatement("insert into schedule values (?, ?);");
                prepStatement.setObject(1, candidate.getTime());
                prepStatement.setInt(2, candidate.getDuration());
                prepStatement.executeUpdate();

                prepStatement = connection.prepareStatement("insert into songSchedule values (?, ?, ?);");

                Iterator<Song> songList = candidate.iterator();
                int trackNumber = 1;
                while (songList.hasNext()) {
                    prepStatement.setObject(1, candidate.getTime());
                    prepStatement.setInt(2, trackNumber);
                    trackNumber++;
                    Song song = songList.next();
                    prepStatement.setInt(3, song.getAccessNumber());
                    updateSongData(song, connection);
                    prepStatement.executeUpdate();
                }

                prepStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateSongData(Song song, Connection connection) {
        try {
            PreparedStatement prepStatement = connection.prepareStatement("update song set lastPlayed = ?, priority = ?, playCount = ? where accessNumber = ?;");
            song.addNumberOfPlays();
            song.setLastPlayed(new Time());
            song.updatePriority();

            prepStatement.setObject(1, song.getLastPlayed());
            prepStatement.setDouble(2, song.getPriority());
            prepStatement.setInt(3, song.getNumberOfPlays());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * generateOneHour
     *
     * Assuming no schedule exists at the given time, generates a one hour schedule
     * of eligible random songs.
     * Returns the generated Schedule
     *
     * @author liufeng & aprilbugnot
     * @param startTime
     * @return Schedule
     */
    public void generateOneHour(Time startTime) {
        Schedule result = tentativeSchedule[startTime.getDayInWeek()][startTime.getHour()];
        ArrayList songs = SongDBI.getSongsByPriority();
        Iterator<Object> iter = songs.iterator();

        result.clear();

        while ( result.underMin() && iter.hasNext() )
        {
            Song current = (Song)iter.next();

            if ( canAddThisSong( current, startTime) )
            {
                result.add( current );
            }
        }

        Iterator<Song> iter2 = result.iterator();
        while ( iter2.hasNext() )
        {
            System.out.println(iter2.next());
        }
    }

    public void generateMultipleHours(Time startTime, int hours) {
        for (int i = 0; i < hours; i++) {
            generateOneHour(startTime);
            startTime = startTime.getNextHour();
        }
    }

    public void generateDays(Time startTime, int days) {
        for (int i = 0; i < 24 * days; i++) {
            generateOneHour(startTime);
            startTime = startTime.getNextHour();
        }
    }

    /**
     * canAddThisSong
     *
     * Checks schedules before and after the startTime -- among the tentative schedules,
     * as well as the database -- to see whether they already contain the given song.
     * Returns true if we are free to add the song (i.e. the preceeding and proceeding
     * schedules do not contain the song), or false if we cannot add the song.
     *
     * @author liufeng & aprilbugnot
     * @param song
     * @param startTime
     * @param connection
     * @return boolean
     */
    private boolean canAddThisSong(Song song, Time startTime) {
        Time previousHour = startTime.getPreviousHour();
        Time nextHour = startTime.getNextHour();
        Schedule previousSchedule;
        Schedule nextSchedule;
        boolean result = true;

        if ( startTime.before( tentativeSchedule[0][0].getTime() ) )
        {
            previousSchedule = getScheduleFromDB(startTime.getPreviousHour());
        }
        else
        {
            previousSchedule = tentativeSchedule[previousHour.getDay() - this.startTime.getDay()][previousHour.getHour() - this.startTime.getHour()];
        }

        if ( startTime.after( tentativeSchedule[6][23].getTime() ) )
        {
            nextSchedule = getScheduleFromDB(startTime.getNextHour());
        }
        else
        {
            nextSchedule = tentativeSchedule[nextHour.getDay() - this.startTime.getDay()][nextHour.getHour() - this.startTime.getHour()];
        }

        if ( previousSchedule.find(song) )
            result = false;
        if ( nextSchedule.find(song) )
            result = false;

        return result;
    }

    /**
     * getScheduleFromDB
     *
     * If a tentative schedule exists for the specified time -- first check tentative schedule,
     * then check database -- return this Schedule object;
     * If no schedule exists for the specified time, return null.
     *
     * @author aprilbugnot
     * @param startTime
     * @return Schedule of the specified time, or null if no schedule exists
     */
    public Schedule getScheduleFromDB(Time startTime) {
        Schedule result = null;

        return new Schedule(startTime);
        // get the schedule from db
//          try {
//                Class.forName("org.sqlite.JDBC");
//
//                Connection connection = DriverManager.getConnection("jdbc:sqlite:song.db");
//
//                PreparedStatement prepStatement = connection.prepareStatement("select song.* from song inner join songSchedule on song.accessNumber = songSchedule.accessNumber where startTime = ?;");
//                prepStatement.setObject(1, startTime);
//                ResultSet rs = prepStatement.executeQuery();
//
//                if (rs != null) {
//                    result = new Schedule(startTime);
//
//                    while (rs.next()) {
//                        Song song = new Song(
//                                rs.getString("title"),
//                                rs.getString("performer"),
//                                rs.getString("recordingTitle"),
//                                rs.getString("recordingType"),
//                                rs.getString("year"),
//                                rs.getInt("length"),
//                                rs.getInt("accessNumber"),
//                                rs.getInt("popularity"),
//                                rs.getInt("playCount"),
//                                (Time)rs.getObject("addedTime"),
//                                (Time)rs.getObject("lastPlayed"),
//                                rs.getDouble("priority"));
//
//                        result.add(song);
//                    }
//                }
//
//                rs.close();
//                prepStatement.close();
//                connection.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        return result;
    }

    /*
     * Returns the Schedule held at the specified time.
     */
    public Schedule getSchedule( Time time ) {
        return tentativeSchedule[time.getDay()- startTime.getDay()][time.getHour()];
    }
}
