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
    public Schedule generateOneHour(Time startTime) {
        Schedule result = new Schedule(startTime);

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/liufeng/prog/ss/song.db");
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
                    result.add(song);

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
}
