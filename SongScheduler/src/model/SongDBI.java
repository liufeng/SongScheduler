package model;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author jordan
 */
public class SongDBI {

    public static ArrayList getSongsByPriority(){
        String sqlStatement = "SELECT * FROM song ORDER BY priority, title;";

        return getSongsFromDB(sqlStatement);
    }

    public static ArrayList getSongs( String songName ){
        String sqlStatement;

        if(songName == null){
            sqlStatement = "SELECT * FROM song ORDER BY title;";
        }else{
            sqlStatement = "SELECT * FROM song WHERE title = \'" + songName + "\' ORDER BY title;";
        }
        
        return getSongsFromDB(sqlStatement);
    }

    private static ArrayList getSongsFromDB(String sqlStatement){
        ArrayList songs = new ArrayList();
        Song currSong;
        if(sqlStatement != null && !sqlStatement.equals("")){
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
                    songs.add(currSong);
                }
                rs.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return songs;
    }

    public static void changeSongPopularity( String title, int newPopularity){
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

    /**
     * Modify the <code>priority</code> of the song to the database.
     * @param title the title of the song to be modified.
     * @param priority
     * @aurhor liufeng
     */
    static void changeSongPriority(String title, double priority) {
        if (title != null && !title.equals("")) {
            String sql = "update song set priority = " + priority +
                    " where title = \'" + title + "\';";

            try {
                Class.forName("org.sqlit.JDBC");
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
