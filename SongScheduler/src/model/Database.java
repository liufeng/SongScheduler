package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 *
 * @author cmhilde
 */

public abstract class Database {
    private static final String scheduleFile = "schedule.hash";
    private static String currHoldingsFile;
    private static HashMap<Integer, Song> songHash;
    private static HashMap<String, Schedule> scheduleHash;


    /**
     * Initializes the database by loading songs and schedules.
     * Loads all schedules from serialized schedule hash, or creates
     * new hash if DNE.
     * @param holdingsFile - filename of holdings file
     */
    public static void init(String holdingsFile){

        setHoldingsFile(holdingsFile);

        try{
            InputStream is = new FileInputStream(scheduleFile);
            ObjectInput oi = new ObjectInputStream(is);
            scheduleHash = (HashMap<String, Schedule>)(oi.readObject());
            oi.close();

        }
        catch(FileNotFoundException nf){
            scheduleHash = new HashMap<String, Schedule>();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Changes the current holdings file.
     * @param holdingsFile - filename of holdings file
     */
    public static void setHoldingsFile(String holdingsFile){
        currHoldingsFile = holdingsFile;
        songHash = new HashMap<Integer, Song>();
        loadSongInfo();
    }


    public static void destroy(){
        try{
            OutputStream os = new FileOutputStream(scheduleFile);
            ObjectOutput oo = new ObjectOutputStream(os);
            oo.writeObject(scheduleHash);
            oo.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void loadSongInfo(){
        try{
            Song currSong;
            BufferedReader in = new BufferedReader(new FileReader(currHoldingsFile));
            String line = in.readLine();
            while (line != null) {
                currSong = createSongFromLine(line);
                if(!songHash.containsKey(currSong.getAccessNumber()))
                    songHash.put(currSong.getAccessNumber(), currSong);

                line = in.readLine();
            }
        }
        catch(Exception e){e.printStackTrace();}

    }

    /**
     * write the song list to disk.
     *
     * NOTE: the sequence of the song item maybe not correct.
     */
    public static void saveSongInfo(){
        Iterator<Song> songs = songHash.values().iterator();
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(currHoldingsFile));
            while (songs.hasNext()) {
                Song song = songs.next();
                String line = song.getTitle() + ";"
                        + song.getPerformer() + ";"
                        + song.getRecordingTitle() + ";"
                        + song.getRecordingType() + ";"
                        + song.getYear() + ";"
                        + song.getAccessNumber() + ";"
                        + song.getLastPlayed() + ";"
                        + song.getPopularity() + ";"
                        + song.getLength() + ";" +
                        + song.getNumberOfPlays()
                        + song.getPriority() + "\n";
                out.write(line);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an ArrayList of available songs, ordered by priority
     * @return
     */
    public static ArrayList getSongsByPriority(){
        ArrayList songList = new ArrayList<Song>();

        for(Entry songEntry : songHash.entrySet()){
            songList.add((Song)songEntry.getValue());
        }

        Collections.sort(songList, new PriorityComparator());
        return songList;
    }

    /**
     * Returns an ArrayList of songs that match a specified title
     * @param title
     * @return
     */
    public static ArrayList getSongs( String title ){
        if(title == null){

        }
        ArrayList songList = new ArrayList<Song>();

        for(Entry songEntry : songHash.entrySet()){
            if(title == null || title.equals(((Song)songEntry.getValue()).getTitle()))
                songList.add((Song)songEntry.getValue());
        }

        if(title != null)
            Collections.sort(songList, new TitleComparator());

        return songList;
    }

    /**
     * Commit the <code>candidate</code> schedule to the database.
     *
     * @param candidate the schedule to be commited.
     */
    public static void commitSchedule(Schedule candidate) {
        //if the schedule that if trying to be committed is a null pointer don't commit
        if(candidate != null){
            if(scheduleHash.containsKey(candidate.getTime()))
               scheduleHash.remove(candidate.getTime());

            scheduleHash.put(candidate.getTime().toString(), candidate);
        }

        candidate.updateSongsInSchedule();

        destroy();
    }


    /**
     * getScheduleFromDB
     *
     * Retrieve schedule by startTime, or null of none exists
     *
     * @author aprilbugnot
     * @param startTime
     * @return Schedule of the specified time, or null if no schedule exists
     */
    public static Schedule getScheduleFromDB(Time startTime) {
        if(scheduleHash.containsKey(startTime.toString()))
            return scheduleHash.get(startTime.toString());
        else
            return new Schedule(startTime);
    }

    /**
     * Parses a line from library holdings file to create song object
     * @param line
     * @return
     */
    private static Song createSongFromLine(String line){
        String[] token = line.split(";");
        String title = token[0];
        String performer = token[1];
        String recordingTitle = token[2];
        String recordingType = token[3];
        String year = token[4];
        int accessNumber = Integer.parseInt(token[5]);
        String addedTime = token[6];
        int popularity = Integer.parseInt(token[7]);
        int length = Integer.parseInt(token[8]);
        int playCount = Integer.parseInt(token[9]);
        String lastPlayed = token[10];



        return new Song(title, performer, recordingTitle, recordingType, year, length, popularity, accessNumber, playCount, addedTime, lastPlayed);
    }

    private static class TitleComparator implements Comparator{
        public int compare(Object song1, Object song2){
            if(!(song1 instanceof Song) || !(song2 instanceof Song))
                return 0;
            else{
                return ((Song)song1).getTitle().compareTo(((Song)song2).getTitle());
            }
        }
    }

    private static class PriorityComparator implements Comparator{
        public int compare(Object song1, Object song2){
            if(!(song1 instanceof Song) || !(song2 instanceof Song))
                return 0;
            else{
                if(((Song)song1).getPriority() > ((Song)song2).getPriority())
                    return 1;
                else if(((Song)song1).getPriority() < ((Song)song2).getPriority())
                    return -1;
                else
                    return 0;
            }
        }
    }
}
