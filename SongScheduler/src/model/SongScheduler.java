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

        return result;
    }

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
}
