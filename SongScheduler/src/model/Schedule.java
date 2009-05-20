/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.LinkedList;

/**
 *
 * @author liufeng
 */
public class Schedule {
    private LinkedList songList;
    private Time startTime;
    private Time duration;
    final private Time MIN_SCHEDULE_LENGTH = new Time(0, 0, 0, 0, 43, 0);
    final private Time MAX_SCHEDULE_LENGTH = new Time(0, 0, 0, 0, 48, 0);

    public Schedule(Time startTime) {
        this.startTime = startTime;
        this.songList = new LinkedList();
        this.duration = new Time(0, 0, 0, 0, 0, 0);
    }

    public void add(Song song) {
        songList.add(song);
        duration.add(song.getLength());
    }

    public void remove(Song song) {
        songList.remove(song);
        duration.minus(song.getLength());
    }

    public boolean overMax() {
        if (this.duration.after(MAX_SCHEDULE_LENGTH)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean underMin() {
        if (this.duration.before(MIN_SCHEDULE_LENGTH)) {
            return true;
        } else {
            return false;
        }
    }
}
