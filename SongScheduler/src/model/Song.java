package model;

/**
 * For holding song data.
 * 
 * @author liufeng & aprilbugnot
 */
public class Song {
    private String title;
    private String performer;
    private String recordingTitle;
    private String recordingType;
    private String year;
    private int length;
    private int accessNumber;
    private int popularity;
    private int playCount;
    private Time addedTime;
    private Time lastPlayed;
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
    public Song(String title,
                String performer,
                String recordingTitle,
                String recordingType,
                String year,
                int    length,
                int    accessNumber,
                int    popularity,
                int    playCount,
                Time   addedTime,
                Time   lastPlayed,
                double priority) {
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
    }

    // Accessors
    
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

    public int getLength() {
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
     * Increment the <code>playCount</code> by 1.
     */
    public void addNumberOfPlays() {
        this.playCount++;
    }

    /**
     * set the plays in week to 0 for a new week.
     */
    public void resetNumberOfPlays() {
        this.playCount = 0;
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
    public void updatePriority() {
        double newPriority = 10 * popularity - 7 * getAveragePlays()
                - 16 / (new Time().getCurrentTime().minus(lastPlayed));
        priority = newPriority;
        SongDBI.changeSongPriority(title, priority);
    }

    /**
     * Modify the popularity of the song to <code>newPopularity</code>.
     * @param newPopularity
     */
    public void updatePopularity(int newPopularity){
        popularity = newPopularity;
        this.updatePriority();
        SongDBI.changeSongPopularity(title, newPopularity);
    }

    /**
     * Decide if the <code>song</code> is the same song to <code>this</code>.
     * @param song
     * @return <code>true</code> if <code>song</code> is the same song
     *         to <code>this</code>; <code>false</code> otherwise.
     * @author kurtisschmidt & jordan
     */
    public boolean equals(Song song) {
        return this.title.equalsIgnoreCase( song.title );
    }

    /**
     * Return the name of the song
     * @author kurtisschmidt & jordan
     */
    public String toString(){
        return title;
    }
}
