/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Calendar;

/**
 * A Time type for all of the information about ``time''.
 *
 * @author liufeng
 * 
 */
public class Time
{
    private Calendar calendar;

    public Time() {
        calendar = Calendar.getInstance();
    }

    public Time(Calendar calendar) {
        this.calendar = calendar;
    }

    public Time(int year, int month, int day, int hour, int minute, int second) {
        calendar = Calendar.getInstance();
        calendar.set(year, month-1, day, hour, minute, second);
    }

    public Time(String datetime) {
        if (datetime == null) {
            calendar = Calendar.getInstance();
        } else {
            String[] token = datetime.split("\\s+");
            String date = token[0];
            String time = token[1];

            token = date.split("-");
            int year = Integer.parseInt(token[0]);
            int month = Integer.parseInt(token[1]);
            int day = Integer.parseInt(token[2]);

            token = time.split(":");
            int hour = Integer.parseInt(token[0]);
            int minute = Integer.parseInt(token[1]);
            int second = Integer.parseInt(token[2]);

            calendar = Calendar.getInstance();
            calendar.set(year, month-1, day, hour, minute, second);
        }
    }

    /**
     * Accessor
     */

    /**
     * 
     * NOTE: It will return 1 if the year has set to 0.
     * 
     * @return
     */
    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public int getMonth() {
        return calendar.get(Calendar.MONTH) + 1;
    }

    public int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getHour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }

    public int getSecond() {
        return calendar.get(Calendar.SECOND);
    }

    public int getDayInWeek() {
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * Methods from interface Computable
     *
     * @param Time time
     */
    public void add(Time time) {
        calendar.add(Calendar.SECOND, time.getSecond());
        calendar.add(Calendar.MINUTE, time.getMinute());
        calendar.add(Calendar.HOUR_OF_DAY, time.getHour());
        calendar.add(Calendar.DATE, time.getDay() + 1);
        calendar.add(Calendar.MONTH, time.getMonth());
        calendar.add(Calendar.YEAR, time.getYear());
    }

    /**
     * Calculate how many days are there from <em>this</em> to <em>time</em>.
     * @param time
     * @return An integer represents number of days.
     */
    
    public int minus(Time time) {
        if (time == null) {
            return 0;
        } else {
            long end = time.getTimeInMillis();
            long start = this.getTimeInMillis();
            return (int)((end - start) / (24 * 60 * 60 * 1000)) + 1;
        }
    }
    

    public long getTimeInMillis() {
        return calendar.getTimeInMillis();
    }

    /**
     * Compare this with time.
     * @param time
     * @return An integer:
     * <ul>
     *   <li><strong>Negative</strong> if <em>this</em> is earlier than <em>time</em></li>
     *   <li><strong>0</strong> if <em>this</em> is equal to <em>time</em></li>
     *   <li><strong>Positive</strong> if <em>this</em> is later than <em>time</em></li>
     * </ul>
     */
    public int compareTo(Time time) {
        return calendar.compareTo(time.getCalendar());
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public boolean after(Time time) {
        return calendar.after(time.getCalendar());
    }

    public boolean before(Time time) {
        return calendar.before(time.getCalendar());
    }

    public Time getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return new Time(year, month, day, hour, minute, second);
    }

    public Time getPreviousDay() {
        Calendar result = (Calendar) calendar.clone();
        result.add(Calendar.DAY_OF_MONTH, -1);
        return new Time(result);
    }

    public Time getNextDay() {
        Calendar result = (Calendar) calendar.clone();
        result.add(Calendar.DAY_OF_MONTH, 1);
        return new Time(result);
    }

    public Time getPreviousHour() {
        Calendar result = (Calendar) calendar.clone();
        result.add(Calendar.HOUR_OF_DAY, -1);
        return new Time(result);
    }
    
    public Time getNextHour() {
        Calendar result = (Calendar) calendar.clone();
        result.add(Calendar.HOUR_OF_DAY, 1);
        return new Time(result);
    }

    public String getDateAsString() {
        return getDay() + "/" + getMonth() + "/" + getYear();
    }
    
    // YYYY-MM-DD HH:MM:SS
    public String toString() {
        return getYear() + "-" + getMonth() + "-" + getDay() + " "
                + getHour() + ":" + getMinute() + ":" + getSecond();
    }
}