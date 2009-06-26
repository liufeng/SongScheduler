/**
 * Song.java
 *
 * For holding song data.
 *
 * @author liufeng & aprilbugnot
 */
package model;

import java.util.Calendar;

public class Song {

    private String title;
    private String performer;
    private String recordingTitle;
    private String recordingType;
    private String year;
    private int length;
    private int accessNumber;
    private double popularity;
    private int playCount;
    private int requestCount;
    private Time addedTime;
    private Time lastPlayed;
    private long lastPopularized;
    private double priority;

    /**
     * Constructs a <code>Song</code> instance with the information
     * supplied by the params.
     * 
     * @param title
     * @param performer
     * @param recordingTitle
     * @param recordingType
     * @param year
     * @param length
     * @param accessNumber
     * @param popularity
     * @param playCount
     * @param addedTime
     * @param lastPlayed
     * @param priority
     */
    public Song ( String title,
            String performer,
            String recordingTitle,
            String recordingType,
            String year,
            int length,
            int accessNumber,
            int popularity,
            int playCount,
            Time addedTime,
            Time lastPlayed,
            double priority ) {
        this.title = title;
        this.performer = performer;
        this.recordingTitle = recordingTitle;
        this.recordingType = recordingType;
        this.year = year;
        this.length = length;
        this.accessNumber = accessNumber;
        this.popularity = popularity;
        this.playCount = playCount;
        this.addedTime = addedTime;
        this.lastPlayed = lastPlayed;
        this.priority = priority;
        this.lastPopularized = Calendar.getInstance().getTimeInMillis();
    }

    // Accessors
    public String getTitle () {
        return title;
    }

    public String getPerformer () {
        return performer;
    }

    public String getRecordingTitle () {
        return recordingTitle;
    }

    public String getRecordingType () {
        return recordingType;
    }

    public String getYear () {
        return year;
    }

    public int getLength () {
        return length;
    }

    public int getAccessNumber () {
        return accessNumber;
    }

    public double getPopularity () {
        return popularity;
    }

    public int getNumberOfPlays () {
        //return numberOfPlays;
        return playCount;
    }

    public Time getLastPlayed () {
        return lastPlayed;
    }

    public double getPriority () {
        return priority;
    }

    /**
     * Get the average played time of the song.
     * If the song hasn't added for 1 week, return the number
     * of plays of the song. (otherwise will get a <em>Divide
     * by zero</em> error.
     * @return an <code>int</code> value.
     */
    private int getAveragePlays () {
        int weeks = ( new Time().getCurrentTime().minus( addedTime ) ) / 7;
        if ( weeks == 0 ) {
            return playCount;
        } else {
            return playCount / weeks;
        }
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setPerformer(String performer){
        this.performer = performer;
    }

    public void setRecordingTitle(String recordingTitle){
        this.recordingTitle = recordingTitle;
    }

    public void setRecordingType(String recordingType){
        this.recordingType = recordingType;
    }

    public void setYear(String year){
        this.year = year;
    }

    /**
     * Set a new last played date if it is more recent than the current lastPlayed time
     * @param lastPlayed
     */
    
    public void setLastPlayed ( Time lastPlayed ) {
        if ( lastPlayed.minus( this.lastPlayed ) > 0 ) {
            this.lastPlayed = lastPlayed;
            Database.saveLastPlayed( this );
            updatePriority();
        }
    }
    /**
     * Increment the <code>playCount</code> by 1.
     */
    public void addNumberOfPlays () {
        this.playCount++;
        Database.savePlayCount( this );
    }

    /**
     * set the plays in week to 0 for a new week.
     */
    public void resetNumberOfPlays () {
        this.playCount = 0;
    }
    /**
     * update the priority by the algorithm in the
     * <em>user Requirement</em>.
     *
     * Happens every time the user alter the popularity and every time the
     * system makes schedule.
     */
    public void updatePriority () {
        double newPriority = 10 * popularity - 7 * getAveragePlays() - 16 / ( new Time().getCurrentTime().minus( lastPlayed ) );
        priority = newPriority;
        Database.changeSongPriority( title, priority );
    }

    /**
     * Increases the requestCount so popularity can be calculated.  Count
     * automatically reset to zero when <code>updatePopularity()</code> is called.
     */
    public void makeRequest(){
        requestCount++;
    }

    /**
     * Re-calculates the popularity of the song based on requests since last called.
     * Updates object and database values.
     */
    public void updatePopularity(){
        long timeSinceUpdated = (Calendar.getInstance().getTimeInMillis() - lastPopularized)/1000;
        double reqPerMinute = requestCount/(double)timeSinceUpdated * 60.0;

        if(reqPerMinute >= 1){
            popularity += reqPerMinute;
        }

        else{
            popularity -= (1.0/24.0);
        }

        Database.changeSongPopularity( title, popularity );
        requestCount = 0;
        lastPopularized = Calendar.getInstance().getTimeInMillis();
    }

    /**
     * Decide if the <code>song</code> is the same song to <code>this</code>.
     * @param song
     * @return <code>true</code> if <code>song</code> is the same song
     *         to <code>this</code>; <code>false</code> otherwise.
     * @author Kurtis Schmidt
     */
    public boolean equals ( Song song ) {
        return this.title.equalsIgnoreCase( song.title );
    }

    /**
     * Return the name of the song
     * @author Kurtis Schmidt
     */
    public String toString () {
        return title;
    }
}
