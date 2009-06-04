/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author liufeng
 */
public class Schedule implements Iterable {
    private LinkedList songList;
    private Time startTime;
    private int duration;
    final private int MIN_SCHEDULE_LENGTH = 2580000; //new Time(0, 0, 0, 0, 43, 0);
    final private int MAX_SCHEDULE_LENGTH = 2880000; //new Time(0, 0, 0, 0, 48, 0);

    public Schedule(Time startTime) {
        this.startTime = startTime;
        this.songList = new LinkedList();
        this.duration = 0; // new Time(0, 0, 0, 0, 0, 0);
    }

    public void add(Song song) {
        songList.add(song);
        duration += song.getLength();
    }

    public void remove(Song song) {
        songList.remove(song);
        duration -= song.getLength();
    }

    public boolean overMax() {
        if (this.duration > MAX_SCHEDULE_LENGTH) {
            return true;
        } else {
            return false;
        }
    }

    public boolean underMin() {
        if (this.duration < MIN_SCHEDULE_LENGTH) {
            return true;
        } else {
            return false;
        }
    }

    public int getDuration() {
        return duration;
    }

    public boolean contains(Song song) {
        return songList.contains(song);
    }

    public Iterator<Song> iterator()
    {
        return songList.iterator();
    }

    public String toString(){
        return startTime.getHour() + ":00 - " + (startTime.getHour() + 1) + ":00";
    }
}
