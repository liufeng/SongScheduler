/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.*;

/**
 *
 * @author liufeng
 */
public class SongScheduler {
    private Schedule[][] tentativeSchedule = new Schedule[7][24];

    public void commitSchedule(Time startTime) {
        
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

            ResultSet rs = statement.executeQuery("select * from songlist order by priority desc, length desc limit 50;");

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
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO: april&feng - should this be where we add this schedule to tentativeSchedules?
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
    private boolean canAddThisSong(Song song, Time startTime, Connection connection) {
        Time previousHour = startTime.getPreviousHour();
        Time nextHour = startTime.getNextHour();
        boolean result = true;

        if (tentativeSchedule[previousHour.getDay()][previousHour.getHour()] == null) {
            //check db;
        } else {
            result &= tentativeSchedule[previousHour.getDay()][previousHour.getHour()].contains(song);
        }

        if (tentativeSchedule[nextHour.getDay()][nextHour.getHour()] == null) {
            //check db;
        } else {
            result &= tentativeSchedule[nextHour.getDay()][nextHour.getHour()].contains(song);
        }

        return result;
    }

    /**
     * getSchedule
     *
     * If a tentative schedule exists for the specified time -- first check tentative schedule,
     * then check database -- return this Schedule object;
     * If no schedule exists for the specified time, return null.
     *
     * @author aprilbugnot
     * @param startTime
     * @return Schedule of the specified time, or null if no schedule exists
     */
    public Schedule getSchedule(Time startTime) {
        Schedule result = null;

        if (tentativeSchedule[startTime.getDay()][startTime.getHour()] == null) {
            // check db;
        } else {
            result = tentativeSchedule[startTime.getDay()][startTime.getHour()];
        }
        
        return result;
    }
}
