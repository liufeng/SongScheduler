/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.*;
import java.io.*;

/**
 *
 * @author liufeng
 */
public class Database {
    /**
     * Initialize the database. Creates three tables.
     *
     * NOTE: this method is supposed to run at the first time of the program runs.
     */
    public static void init() {
        try {
            Class.forName("org.sqlite.JDBC");

            //Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/liufeng/prog/ss/song.db");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:song.db");
            Statement statement = connection.createStatement();
            //statement.executeUpdate("create table songlist (title, performer, recordingTitle, recordingType, year, length INTEGER, accessNumber INTEGER, popularity, playCount, addedTime, lastPlayed, priority REAL);");
            
            statement.addBatch("CREATE TABLE IF NOT EXISTS song(accessNumber INTEGER PRIMARY KEY, title TEXT, performer TEXT, recordingTitle TEXT, recordingType TEXT, year TEXT, length INTEGER, popularity INTEGER, playCount INTEGER, addedTime DATETIME, lastPlayed DATETIME, priority REAL);");
            statement.addBatch("CREATE TABLE IF NOT EXISTS schedule(startTime DATETIME PRIMARY KEY, duration INTEGER);");
            statement.addBatch("CREATE TABLE IF NOT EXISTS songSchedule(accessNumber INTEGER CONSTRAINT fk_song_id REFERENCES song(accessNumber), startTime DATETIME CONSTRAINT fk_schedule_id REFERENCES schedule(startTime) ON DELETE CASCADE, trackNumber INTEGER, PRIMARY KEY(startTime, trackNumber));");
            statement.addBatch("CREATE TRIGGER IF NOT EXISTS fki_songSchedule_accessNumber_song_accessNumber BEFORE INSERT ON [songSchedule] FOR EACH ROW BEGIN   SELECT RAISE(ROLLBACK, 'insert on table \"songSchedule\" violates foreign key constraint \"fki_songSchedule_accessNumber_song_accessNumber\"')   WHERE NEW.accessNumber IS NOT NULL AND (SELECT accessNumber FROM song WHERE accessNumber = NEW.accessNumber) IS NULL;END;");
            statement.addBatch("CREATE TRIGGER IF NOT EXISTS fku_songSchedule_accessNumber_song_accessNumber BEFORE UPDATE ON [songSchedule]  FOR EACH ROW BEGIN     SELECT RAISE(ROLLBACK, 'update on table \"songSchedule\" violates foreign key constraint \"fku_songSchedule_accessNumber_song_accessNumber\"')       WHERE NEW.accessNumber IS NOT NULL AND (SELECT accessNumber FROM song WHERE accessNumber = NEW.accessNumber) IS NULL;END;");
            statement.addBatch("CREATE TRIGGER IF NOT EXISTS fkd_songSchedule_accessNumber_song_accessNumber BEFORE DELETE ON song FOR EACH ROW BEGIN   SELECT RAISE(ROLLBACK, 'delete on table \"song\" violates foreign key constraint \"fkd_songSchedule_accessNumber_song_accessNumber\"')   WHERE (SELECT accessNumber FROM songSchedule WHERE accessNumber = OLD.accessNumber) IS NOT NULL;END;");
            statement.addBatch("CREATE TRIGGER IF NOT EXISTS fki_songSchedule_startTime_schedule_startTime BEFORE INSERT ON [songSchedule] FOR EACH ROW BEGIN   SELECT RAISE(ROLLBACK, 'insert on table \"songSchedule\" violates foreign key constraint \"fki_songSchedule_startTime_schedule_startTime\"')   WHERE NEW.startTime IS NOT NULL AND (SELECT startTime FROM schedule WHERE startTime = NEW.startTime) IS NULL;END;");
            statement.addBatch("CREATE TRIGGER IF NOT EXISTS fku_songSchedule_startTime_schedule_startTime BEFORE UPDATE ON [songSchedule]  FOR EACH ROW BEGIN     SELECT RAISE(ROLLBACK, 'update on table \"songSchedule\" violates foreign key constraint \"fku_songSchedule_startTime_schedule_startTime\"')       WHERE NEW.startTime IS NOT NULL AND (SELECT startTime FROM schedule WHERE startTime = NEW.startTime) IS NULL;END;");
            statement.addBatch("CREATE TRIGGER IF NOT EXISTS fkdc_songSchedule_startTime_schedule_startTime BEFORE DELETE ON schedule FOR EACH ROW BEGIN      DELETE FROM songSchedule WHERE songSchedule.startTime = OLD.startTime;END;");
            statement.executeBatch();
            connection.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * updates the new song data from the library text file.
     *
     * NOTE: should be executed at least once a week.
     */
    public static void updateSongInfo() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:song.db");
            
            BufferedReader in = new BufferedReader(new FileReader("library.txt"));
            String line = in.readLine();
            while (line != null) {
                String[] token = line.split(";");
                String title = token[0];
                String performer = token[1];
                String recordingTitle = token[2];
                String recordingType = token[3];
                String year = token[4];
                int length = Integer.parseInt(token[5]);
                int accessNumber = Integer.parseInt(token[6]);
                int popularity = Integer.parseInt(token[7]);
                int playCount = Integer.parseInt(token[8]);
                Time addedTime = new Time().getCurrentTime();
                Time lastPlayed = null; // new Time(0, 0, 0, 0, 0, 0);
                double priority = 0;

                /**
                 * Adding the following 12 info into songlist:
                 *
                 * <ol>
                 *   <li>accessNumber</li>
                 *   <li>title</li>
                 *   <li>performer</li>
                 *   <li>recordingTitle</li>
                 *   <li>recordingType</li>
                 *   <li>year</li>
                 *   <li>length</li>
                 *   <li>popularity</li>
                 *   <li>playCount</li>
                 *   <li>addedTime</li>
                 *   <li>lastPlayed</li>
                 *   <li>priority</li>
                 * </ol>
                 */
                PreparedStatement prepStatement = connection.prepareStatement("insert into song values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                prepStatement.setInt(1, accessNumber);
                prepStatement.setString(2, title);
                prepStatement.setString(3, performer);
                prepStatement.setString(4, recordingTitle);
                prepStatement.setString(5, recordingType);
                prepStatement.setString(6, year);
                prepStatement.setInt(7, length);
                prepStatement.setInt(8, popularity);
                prepStatement.setInt(9, playCount);
                prepStatement.setObject(10, addedTime);
                prepStatement.setObject(11, lastPlayed);
                prepStatement.setDouble(12, priority);
                prepStatement.addBatch();

                Statement statement = connection.createStatement();
                String sql = "select * from song where accessNumber = " + accessNumber + ";";
                ResultSet rs = statement.executeQuery(sql);
                if (!rs.next()) {

                    connection.setAutoCommit(false);
                    prepStatement.executeBatch();
                    connection.setAutoCommit(true);
                }

                line = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
