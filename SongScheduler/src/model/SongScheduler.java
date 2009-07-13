/**
 * SongScheduler.java
 *
 * Holds a schedule for 7 days, starting from startTime.
 * Provides methods for generating schedules, fixing schedules and
 * committing schedules.
 *
 * @author Feng Liu
 * @author April Bugnot
 * @author Kurtis Schmidt
 * @author Jordan Wiebe
 */

package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A bunch of methods for generating schedules.
 * 
 * @author liufeng & aprilbugnot
 */
public class SongScheduler {
    private Schedule[][] tentativeSchedule = new Schedule[7][24];
    private Time startTime;

    /**
     * Constructor for initialize the tentativeSchedule array.
     * 
     * @author kurtisschmidt & jordan
     * @param firstHour the start hour of tentativeSchedule[0][0];
     */
    public SongScheduler( Time firstHour ) {
        startTime = firstHour;
        for (int i = 0; i < 7; i++ )
        {
            for ( int j = 0; j < 24; j++ ) {
                tentativeSchedule[i][j] = Database.getScheduleFromDB(firstHour);
                firstHour = firstHour.getNextHour();
            }
        }
    }

    /**
     * Commit the each schedules in the tentativeSchedule array
     * to the database.
     *
     * @author kurtisschmidt & jordan
     */
    public void commit(){
        //commit the schedules for each day
        for(int i=0; i < tentativeSchedule.length; i++){
            //commit the schedules for each hour of a day
            for(int j=0; j < tentativeSchedule[i].length; j++){
                //if the schdule is not empty then commit it
                if(!tentativeSchedule[i][j].isEmpty())
                    Database.commitSchedule(tentativeSchedule[i][j]);
            }
        }
    }

    /**
     * generateOneHour
     *
     * Assuming no schedule exists at the given time, generates an
     * one-hour schedule of eligible random songs.
     *
     * @author liufeng & aprilbugnot
     * @param startTime The start time of the schedule.
     */
    public void generateOneHour(Time startTime) {
        Schedule result = getSchedule(startTime);
        ArrayList songs = Database.getSongsByPriority();
        Iterator<Object> iter = songs.iterator();
        ArrayList topSongs = new ArrayList();
        ArrayList midSongs = new ArrayList();
        ArrayList bottomSongs = new ArrayList();
        Song current;
        int counter = 0;

        result.clear();

        for(counter = 0; (counter < 50 && iter.hasNext()); counter++)
        {
            current = (Song)iter.next();
            topSongs.add(current);
        }
        addToSchedule(result,topSongs,4);

        for(counter = 0; (counter < 100 && iter.hasNext()); counter++)
        {
            current = (Song)iter.next();
            midSongs.add(current);
        }
        addToSchedule(result,midSongs,2);

        while(iter.hasNext())
        {
            current = (Song)iter.next();
            bottomSongs.add(current);
        }
        addToSchedule(result,bottomSongs,-1);

    }


    private void addToSchedule(Schedule schedule, ArrayList songs, int limit){
        Song current = null;
        int counter = 0;
        int randNum;

        while ( schedule.underMin() && (counter < limit || limit == -1) )
        {
            randNum = (int) (Math.random() * songs.size());
            current = (Song) songs.get(randNum);

            if ( canAddThisSong( current, startTime) )
            {
                schedule.add( current );
                counter++;
            }

            // do this check for preventing a song is too long that makes
            // the schedule longer than 48 minutes.
            if ( schedule.overMax() ) {
                schedule.remove( current );
                counter--;
            }
        }
    }

    /**
     * Generate a schedule starts from <code>startTime</code> with
     * the length of <code>hours</code> hours.
     * 
     * NOTE: it simply calls the <code>generateOneHour()</code>
     *       method several times. Maybe we need to change it to
     *       a better way.
     *
     * @param startTime
     * @param hours
     */
    public void generateMultipleHours(Time startTime, int hours) {
        for (int i = 0; i < hours; i++) {
            generateOneHour(startTime);
            startTime = startTime.getNextHour();
        }
    }

    /**
     * Generate a schedule starts from <code>startTime</code> with
     * the length of <code>days</code> days.
     *
     * NOTE: we have the same problem as the
     * <code>generateMultipleHours()</code> method.
     *
     * @param startTime
     * @param days
     */
    public void generateDays(Time startTime, int days) {
        for (int i = 0; i < 24 * days; i++) {
            generateOneHour(startTime);
            startTime = startTime.getNextHour();
        }
    }

    /**
     * autoCorrect
     *
     * This function will automatically add and remove elements from a list
     * until it fits the 43-48 minute mark.  
     * 
     * Right now it is lazy by only adding the first song it finds that fits the criteria.
     * As well, the implementation has some serious downfalls. It cannot handle any
     * problems where it relies on finding songs that work.
     * @param startTime
     */
    public void autoCorrect( Time startTime )
    {
        Schedule schedule = getSchedule( startTime );
        Iterator<Song> iter = schedule.iterator();
        ArrayList songs = Database.getSongsByPriority();
        int duration;
        int postAdd;
        int postRemove;
        Song song;

        while ( schedule.underMin() || schedule.overMax() )
        {
            duration = schedule.getDuration();

            if ( schedule.underMin() )
            {
                for ( int i = 0; i < songs.size(); i++ )
                {
                    song = (Song)songs.get( i );
                    postAdd = duration + song.getLength();

                    if ( postAdd < Schedule.MAX_SCHEDULE_LENGTH && canAddThisSong( song, startTime ))
                    {
                        schedule.add( song );
                        duration = schedule.getDuration();
                    }
                }
            }
            else if ( schedule.overMax() )
            {

                while( iter.hasNext() )
                {
                    song = iter.next();
                    postRemove = duration - song.getLength();

                    if ( postRemove > Schedule.MIN_SCHEDULE_LENGTH )
                    {
                        schedule.remove( song );
                        duration = schedule.getDuration();
                    }
                }
            }
        }
    }

    /**
     * canAddThisSong
     *
     * Checks schedules before and after the startTime -- among the tentative schedules,
     * as well as the database -- to see whether they already contain the given song.
     * Returns true if we are free to add the song (i.e. the preceeding and proceeding
     * schedules do not contain the song), or false if we cannot add the song.
     *
     * @author liufeng & aprilbugnot
     * @param song
     * @param startTime
     * @param connection
     * @return boolean
     */
    private boolean canAddThisSong(Song song, Time startTime) {
        Time previousHour = startTime.getPreviousHour();
        Time nextHour = startTime.getNextHour();
        Schedule previousSchedule;
        Schedule nextSchedule;
        Schedule thisSchedule = getSchedule(startTime);
        boolean result = true;

        if ( previousHour.before( this.startTime ) )
        {
            previousSchedule = Database.getScheduleFromDB( previousHour );
        }
        else
        {
            previousSchedule = getSchedule( previousHour );
        }

        if ( nextHour.after( tentativeSchedule[6][23].getTime() ) )
        {
            nextSchedule = Database.getScheduleFromDB( nextHour );
        }
        else
        {
            nextSchedule = getSchedule( nextHour );
        }

        if ( previousSchedule.contains(song) )
            result = false;
        if ( nextSchedule.contains(song) )
            result = false;
        if ( thisSchedule.contains(song))
            result = false;
        return result;
    }

    /**
     * Returns the Schedule held at the specified time.
     * @param time 
     */
    public Schedule getSchedule( Time time ) {
        return tentativeSchedule[time.getDay() - this.startTime.getDay()][time.getHour()];
    }
}
