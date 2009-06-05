/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author jordan
 */
public class SongDBI {

    public static ArrayList getSongs( String songName ){
        ArrayList theSongs = new ArrayList();
        Song currSong;
        String sqlStatement;

        if(songName == null){
            sqlStatement = "select * from song order by title;";
        }else{
            sqlStatement = "select * from song where title = \'" + songName + "\' order by title;";
        }

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:song.db");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            
            while (rs.next()) {
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

                currSong = new Song(title, performer, recordingTitle, recordingType, year, length, accessNumber, popularity, playCount, addedTime, lastPlayed, priority);
                theSongs.add(currSong);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return theSongs;
    }
}
