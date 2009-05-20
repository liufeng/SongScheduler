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
public class InitialDB {
    /**
     * Just trivial initial stuff.
     * not finished yet.
     */
    public static void initialDB() {
        try {
            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/liufeng/prog/ss/song.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate("create table songlist (id INTEGER PRIMARY KEY, title, performer, recordingTitle, recordingType, year, length, popularity, playCount, addedTime, lastPlayed, priority);");

            BufferedReader in = new BufferedReader(new FileReader("/Users/liufeng/prog/ss/library.txt"));
            String line = in.readLine();
            while (line != null) {
                String[] token = line.split(";");
                String title = token[0];
                String performer = token[1];
                String recordingTitle = token[2];
                String recordingType = token[3];
                String year = token[4];
                String length = token[5] + ":" + token[6];
                int popularity = Integer.parseInt(token[7]);
                int playCount = Integer.parseInt(token[8]);
                Time addedTime = new Time().getCurrentTime();
                Time lastPlayed = new Time(0, 0, 0, 0, 0, 0);
                double priority = 0;

                /**
                 * Adding the following 11 info into songlist:
                 *
                 * <ol>
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
                PreparedStatement prepStatement = connection.prepareStatement("insert into songlist values (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                prepStatement.setString(1, title);
                prepStatement.setString(2, performer);
                prepStatement.setString(3, recordingTitle);
                prepStatement.setString(4, recordingType);
                prepStatement.setString(5, year);
                prepStatement.setString(6, length);
                prepStatement.setObject(7, popularity);
                prepStatement.setObject(8, playCount);
                prepStatement.setObject(9, addedTime);
                prepStatement.setObject(10, lastPlayed);
                prepStatement.setObject(11, priority);
                prepStatement.addBatch();
                connection.setAutoCommit(false);
                prepStatement.executeBatch();
                connection.setAutoCommit(true);
                line = in.readLine();
            }

            in.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
