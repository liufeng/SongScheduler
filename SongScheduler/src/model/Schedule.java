/**
 * Schedule.java
 *
 * For holding song schedules.
 *
 * @author Feng Liu
 * @author April Bugnot
 * @author Kurtis Schmidt
 */

package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Iterator;

public class Schedule implements Iterable, Serializable {
    private LinkedList<Song> songList;
    private Time startTime;
    private int duration; // in millisecond
    final static public int MIN_SCHEDULE_LENGTH = 2580000; // 43 minutes
    final static public int MAX_SCHEDULE_LENGTH = 2880000; // 48 minutes
    static final long serialVersionUID= 911893579883338575L;

    /**
     * Constructs an empty schedule starts from <em>startTime</em>.
     * @param startTime
     */
    public Schedule(Time startTime) {
        this.startTime = startTime;
        this.songList = new LinkedList();
        this.duration = 0;
    }

    /**
     * Add the <em>song</em> to the current schedule.
     * @param song
     */
    public void add(Song song) {
        songList.add(song);
        duration += song.getLength();
    }

    /**
     * Remove the <em>song</em> from the current schedule.
     * @param song
     */
    public void remove(Song song) {
        songList.remove(song);
        duration -= song.getLength();
    }

    public Song getSong( int index )
    {
        if( index < songList.size() )
            return songList.get(index);
        return null;
    }

    public int getSongCount()
    {
        return songList.size();
    }

    /**
     * Determine if the length of the current schedule is
     * greater than <code>MAX_SCHEDULE_LENGTH</code>.
     *
     * @return
     * <code>true</code> if the schedule is longer than MAX_SCHEDULE_LENGTH;
     * <code>false</code> otherwise.
     */
    public boolean overMax() {
        if (this.duration > MAX_SCHEDULE_LENGTH) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determine if the length of the current schedule is
     * less than <code>MIN_SCHEDULE_LENGTH</code>.
     *
     * @return
     * <code>true</code> if the schedule is shorter than MIN_SCHEDULE_LENGTH;
     * <code>false</code> otherwise.
     */
    public boolean underMin() {
        if (this.duration < MIN_SCHEDULE_LENGTH) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get the length of the schedule.
     * @return the length of the schedule.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Check if the current schedule contains the <code>song</code>.
     * @param song  The Song object to be checked.
     * @return <code>true</code> if the schedule contains the
     * <code>song</code>; <code>false</code> otherwise.
     */
    public boolean contains(Song song) {
        Iterator<Song> iter = songList.iterator();
        while ( iter.hasNext() )
            if ( song.equals( (Song)iter.next() ) )
                return true;

        return false;
    }

    public Iterator<Song> iterator() {
        return songList.iterator();
    }

    /**
     * Get the start time of the schedule.
     * @return the start time of the schedule.
     */
    public Time getTime() {
        return startTime;
    }

    /**
     * Remove all songs in this schedule. The schedule will be empty
     * after executing this method.
     */
    public void clear() {
        songList.clear();
        duration = 0;
    }

    /**
     * Decide if the schedule is empty.
     *
     * @return <code>true</code> if the schedule is empty;
     *         <code>false</code> otherwise.
     */
    public boolean isEmpty(){
        return songList.isEmpty();
    }

    /**
     * updateSongsInSchedule
     *
     * Updates song info for all songs in schedule..
     *
     * @return void
     */
    public void updateSongsInSchedule(){
        for (int i=0; i < songList.size(); i++){
            Song song = (Song)songList.get(i);
            song.setLastPlayed(startTime);
            song.addNumberOfPlays();
            song.updatePriority();
        }
    }

    /**
     * toString
     *
     * Prints out the time of the schedule.  This function is used by the GUI
     * and so should not be changed.
     *
     * @return String - Start time of the schedule.
     */
    public String toString(){
        int start = startTime.getHour();
        int finish = start + 1;
        String startAP = "am";
        String finishAP = "am";

        if (start == 0){
            start = 12;
            startAP = "pm";
        } else if(start == 12) {
            finish = 1;
            finishAP = "pm";
        } else if(start > 12) {
            start = start - 12;
            finish = finish - 12;
            startAP = "pm";
            finishAP = "pm";
        }

        return start + ":00" + startAP + " - " + finish + ":00" + finishAP;
    }

    public String toTextFileLine(){
        String retString = startTime.toString() + ";";
        for (Song song : songList){
            retString += song.getAccessNumber() + "-";
        }
        retString = retString.substring(0, retString.length()-1);

        return retString;
    }
}
