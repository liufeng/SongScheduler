/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.*;
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
    
    public void commitSchedule(Time startTime) {
        Schedule candidate = tentativeSchedule[startTime.getDayInWeek()][startTime.getHour()];
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:song.db");
            PreparedStatement prepStatement = connection.prepareStatement("insert into schedule values (?, ?);");
            prepStatement.setObject(1, startTime);
            prepStatement.setInt(2, candidate.getDuration());
            prepStatement.executeUpdate();

            prepStatement = connection.prepareStatement("insert into songSchedule values (?, ?, ?);");

            Iterator<Song> songList = candidate.iterator();
            int trackNumber = 1;
            while (songList.hasNext()) {
                prepStatement.setObject(1, startTime);
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
    public Schedule generateOneHour(Time startTime) {
        Schedule result = new Schedule(startTime);

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:song.db");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from song order by priority desc, length desc limit 50;");
            while (result.underMin()) {
                
                if (rs.next()) {
                    String title = rs.getString("title");
                    String performer = rs.getString("performer");
                    String recordingTitle = rs.getString("recordingTitle");
                    String recordingType = rs.getString("recordingType");
                    String year = rs.getString("year");
                    int    length = rs.getInt("length");
                    int    accessNumber = rs.getInt("accessNumber");
                    int    popularity = rs.getInt("popularity");
                    int    playCount = rs.getInt("playCount");
                    Time   addedTime = new Time(rs.getString("addedTime"));
                    Time   lastPlayed = new Time(rs.getString("lastPlayed"));
                    double priority = rs.getDouble("priority");

                    Song song = new Song(title, performer, recordingTitle, recordingType, year, length, accessNumber, popularity, playCount, addedTime, lastPlayed, priority);
                    
                    if (canAddThisSong(song, startTime, connection)) {
                        result.add(song);
                    }

                    if (result.overMax()) {
                        result.remove(song);
                    }
                }
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // add to tentativeSchedule
        tentativeSchedule[startTime.getDayInWeek()][startTime.getHour()] = result;
        return result;
    }

    public Schedule[] generateMultipleHours(Time startTime, int hours) {
        Schedule[] result = new Schedule[hours];

        for (int i = 0; i < hours; i++) {
            result[i] = generateOneHour(startTime);
            startTime = startTime.getNextHour();
        }

        return result;
    }

    public Schedule[] generateDays(Time startTime, int days) {
        Schedule[] result = new Schedule[24 * days];

        for (int i = 0; i < 24 * days; i++) {
            result[i] = generateOneHour(startTime);
            startTime = startTime.getNextHour();
        }

        return result;
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
    private boolean canAddThisSong(Song song, Time startTime, Connection connection) throws SQLException {
        Time previousHour = startTime.getPreviousHour();
        Time nextHour = startTime.getNextHour();
        boolean result = true;

        if (tentativeSchedule[previousHour.getDay()][previousHour.getHour()] == null) {
            //check db;
            try {
                PreparedStatement prepStatement = connection.prepareStatement("select * from songSchedule where startTime = ? and accessNumber = ?;");
                prepStatement.setObject(1, previousHour);
                prepStatement.setInt(2, song.getAccessNumber());
                if (prepStatement.execute()) {
                    result = false;
                }
            } catch (SQLException e) {
                //connection.rollback();
                e.printStackTrace();
            }

        } else {
            result &= tentativeSchedule[previousHour.getDay()][previousHour.getHour()].contains(song);
        }

        if (tentativeSchedule[nextHour.getDay()][nextHour.getHour()] == null) {
            //check db;
            try {
                PreparedStatement prepStatement = connection.prepareStatement("select * from songSchedule where startTime = ? and accessNumber = ?;");
                prepStatement.setObject(1, nextHour);
                prepStatement.setInt(2, song.getAccessNumber());
                if (prepStatement.execute()) {
                    result = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            result &= tentativeSchedule[nextHour.getDay()][nextHour.getHour()].contains(song);
        }

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

        // get the schedule from db
          try {
                Class.forName("org.sqlite.JDBC");

                Connection connection = DriverManager.getConnection("jdbc:sqlite:song.db");
                
                PreparedStatement prepStatement = connection.prepareStatement("select song.* from song inner join songSchedule on song.accessNumber = songSchedule.accessNumber where startTime = ?;");
                prepStatement.setObject(1, startTime);
                ResultSet rs = prepStatement.executeQuery();

                if (rs != null) {
                    result = new Schedule(startTime);

                    while (rs.next()) {
                        Song song = new Song(
                                rs.getString("title"),
                                rs.getString("performer"),
                                rs.getString("recordingTitle"),
                                rs.getString("recordingType"),
                                rs.getString("year"),
                                rs.getInt("length"),
                                rs.getInt("accessNumber"),
                                rs.getInt("popularity"),
                                rs.getInt("playCount"),
                                (Time)rs.getObject("addedTime"),
                                (Time)rs.getObject("lastPlayed"),
                                rs.getDouble("priority"));

                        result.add(song);
                    }
                }

                rs.close();
                prepStatement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        return result;
    }

    /*
     * Returns the Schedule held at the specified time.
     */
    public Schedule getSchedule( Time time ) {
        return tentativeSchedule[time.getDay()- startTime.getDay()][time.getHour()];
    }
}
