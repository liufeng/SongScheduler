package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author cmhilde
 */

public abstract class Database {
    private static String currLib;
    private static HashMap<Integer, Song> songHash;
    private static HashMap<Time, Schedule> scheduleHash;


    public static void init(){
        currLib = "library.txt";

        songHash = new HashMap<Integer, Song>();
        loadSongInfo();

        scheduleHash = new HashMap<Time, Schedule>();
        loadScheduleInfo();
    }

    public static void loadSongInfo(){
        try{
            Song currSong;
            BufferedReader in = new BufferedReader(new FileReader(currLib));
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

    private static void loadScheduleInfo(){
        try{
            Schedule currSchedule;
            BufferedReader in = new BufferedReader(new FileReader(currLib.substring(0, currLib.length()-4) + "_sched.txt"));
            String line = in.readLine();
            while (line != null) {
                currSchedule = createScheduleFromLine(line);
                if(!scheduleHash.containsKey(currSchedule.getTime()))
                    scheduleHash.put(currSchedule.getTime(), currSchedule);
            }
        }
        catch(Exception e){e.printStackTrace();}
    }

    public static ArrayList getSongsByPriority(){
        ArrayList songList = new ArrayList<Song>();

        for(Entry songEntry : songHash.entrySet()){
            songList.add((Song)songEntry.getValue());
        }

        Collections.sort(songList, new PriorityComparator());
        return songList;
    }

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

            scheduleHash.put(candidate.getTime(), candidate);
        }
    }

    /**
     * Update some of the property values for <code>song</code> to
     * the database. The changed property values are:
     *
     * <ul>
     *   <li>Increase <code>playCount</code> by 1.</li>
     *   <li>Set <code>lastPlayed</code> to the current time.</li>
     *   <li>Update the <code>priority</code>.</li>
     * </ul>
     *
     * @param song The Song object indicates the song to be updated.
     * @param connection The connection to the database.
     */
    private static void updateSongData(Song song) {
        song.addNumberOfPlays();
        song.setLastPlayed(new Time());
        song.updatePriority();
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
        if(scheduleHash.containsKey(startTime))
            return scheduleHash.get(startTime);
        else
            return new Schedule(startTime);
    }

    static class TitleComparator implements Comparator{
        public int compare(Object song1, Object song2){
            if(!(song1 instanceof Song) || !(song2 instanceof Song))
                return 0;
            else{
                return ((Song)song1).getTitle().compareTo(((Song)song2).getTitle());
            }
        }
    }

    static class PriorityComparator implements Comparator{
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

    private static Schedule createScheduleFromLine(String line){
        String[] token = line.split(";");

        Time startTime = new Time(token[0]);

        Schedule schedule = new Schedule(startTime);

        String[] songNums = token[1].split("-");

        for(String songNum : songNums){
            schedule.add(songHash.get(songNum));
        }

        return schedule;
    }

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
}
