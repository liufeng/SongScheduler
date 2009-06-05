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

    public static ArrayList getSongsByPopularity(){
        ArrayList theSongs = new ArrayList();
        Song currSong;
        String sqlStatement = "SELECT * FROM song ORDER BY popularity, title;";

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

    public static ArrayList getSongs( String songName ){
        ArrayList theSongs = new ArrayList();
        Song currSong;
        String sqlStatement;

        if(songName == null){
            sqlStatement = "SELECT * FROM song ORDER BY title;";
        }else{
            sqlStatement = "SELECT * FROM song WHERE title = \'" + songName + "\' ORDER BY title;";
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

    public static void changeSongPriority( String title, int newPopularity){
        if(title != null && !title.equals("")){
            String sql = "UPDATE song SET popularity = " + newPopularity + " WHERE title = \'" + title + "\';";


            try {
                Class.forName("org.sqlite.JDBC");
                Connection connection = DriverManager.getConnection("jdbc:sqlite:song.db");
                Statement statement = connection.createStatement();
                statement.executeQuery(sql);

                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
