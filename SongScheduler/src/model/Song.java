/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author liufeng
 */
public class Song {
    private String title;
    private String performer;
    private String recordingTitle;
    private String recordingType;
    private String year;
    private Time length;
    private int accessNumber;
    private int popularity;
    //private int numberOfPlays;
    private int playCount;
    private Time addedTime;
    private Time lastPlayed;
    private double priority;

    public String getTitle() {
        return title;
    }

    public String getPerformer() {
        return performer;
    }

    public String getRecordingTitle() {
        return recordingTitle;
    }

    public String getRecordingType() {
        return recordingType;
    }

    public String getYear() {
        return year;
    }

    public Time getLength() {
        return length;
    }

    public int getAccessNumber() {
        return accessNumber;
    }

    public int getPopularity() {
        return popularity;
    }

    /**
     * Set the popularity. User may alter it.
     * @param popularity
     */
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getNumberOfPlays() {
        //return numberOfPlays;
        return playCount;
    }

    /**
     * Set the number of plays in the current week
     * @param number The number to add to.
     */
    public void addNumberOfPlays(int number) {
        this.accessNumber += number;
    }

    /**
     * set the plays in week to 0 for a new week.
     */
    public void resetNumberOfPlays() {
        this.accessNumber = 0;
    }

    public Time getLastPlayed() {
        return lastPlayed;
    }

    /**
     * Set a new last played date.
     * @param lastPlayed
     */
    public void setLastPlayed(Time lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public double getPriority() {
        return priority;
    }

    private int getAveragePlays() {
        return playCount / ((new Time().getCurrentTime().minus(addedTime)) / 7);
    }

    /**
     * update the priority by the algorithm in the
     * <em>user Requirement</em>.
     *
     * Happens every time the user alter the popularity and every time the
     * system makes schedule.
     */
    private void updatePriority() {
        double newPriority = 10 * popularity - 7 * getAveragePlays()
                - 16 / (new Time().getCurrentTime().minus(lastPlayed));
        priority = newPriority;
    }
}