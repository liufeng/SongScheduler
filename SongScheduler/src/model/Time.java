/**
 * Time.java
 * 
 * A Time type for all of the information about ``time''.
 *
 * @author liufeng & aprilbugnot
 *
 */
package model;

import java.io.Serializable;
import java.util.Calendar;

public class Time implements Serializable {

    private Calendar calendar;

    /**
     * Constructs a <code>Time</code> instance contains the current time.
     */
    public Time() {
        calendar = Calendar.getInstance();
    }

    /**
     * Constructs a <code>Time</code> instance contains the time
     * from the param.
     * @param calendar a <code>Calendar</code> variable.
     */
    public Time(Calendar calendar) {
        this.calendar = calendar;
    }

    /**
     * Constructs a <code>Time</code> instance contains the time
     * from the param.
     * @param year An <code>int</code> type indicates the year.
     * @param month An <code>int</code> type indicates the month.
     * @param day An <code>int</code> type indicates the day.
     * @param hour An <code>int</code> type indicates the hour.
     * @param minute An <code>int</code> type indicates the minute.
     * @param second An <code>int</code> type indicates the second.
     */
    public Time(int year, int month, int day, int hour, int minute, int second) {
        calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, second);
    }

    /**
     * Constructs a <code>Time</code> instance contains the time
     * from the param.
     * @param datetime a SQL formated string. Looks like "2009-06-06 20:28:32".
     */
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
            calendar.set(year, month - 1, day, hour, minute, second);
        }
    }

    /**
     * Accessors
     */
    /**
     * 
     * NOTE: It will return 1 if the year has set to 0.
     * 
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
     * Adds the <code>time</code> to the time in <code>this</code>.
     * This methods is from interface Computable.
     *
     * @param time
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
            return (int) ((end - start) / (24 * 60 * 60 * 1000)) + 1;
        }
    }

    /**
     * Get the millisecond type of the time in <code>this</code>.
     * @return A <code>long</code> type indicates the millisecond
     *         starts from "January 1, 1970, 0:00:00 GMT".
     */
    public long getTimeInMillis() {
        return calendar.getTimeInMillis();
    }

    /**
     * Compare this with time.
     * Note: we may not need this method since we have <code>before</code>
     *       and <code>after</code> methods.
     * @param time
     * @return An integer:
     * <ul>
     *   <li><strong>Negative</strong> if <code>this</code> is earlier than <code>this</code></li>
     *   <li><strong>0</strong> if <code>this</code> is equal to <code>this</code></li>
     *   <li><strong>Positive</strong> if <code>this</code> is later than <code>this</code></li>
     * </ul>
     */
    public int compareTo(Time time) {
        return calendar.compareTo(time.getCalendar());
    }

    /**
     * Get the instance of <code>calendar</code> in <code>this</code> object.
     * @return A <code>Calendar</code> instance.
     */
    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * Decide if <code>time</code> comes after <code>this</code>.
     * @param time
     * @return <code>true</code> if <code>time</code> is later than
     *         <code>this</code>; <code>false</code> otherwise.
     */
    public boolean after(Time time) {
        return calendar.after(time.getCalendar());
    }

    /**
     * Decide if <code>time</code> comes before <code>this</code>.
     * @param time
     * @return <code>true</code> if <code>time</code> is earlier than
     *         <code>this</code>; <code>false</code> otherwise.
     */
    public boolean before(Time time) {
        return calendar.before(time.getCalendar());
    }

    /**
     * Get the current in <code>Time</code> format.
     * @return A <code>Time</code> object indicate the current time.
     */
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

    /**
     * Get yesterday.
     * @return An <code>int</code> indicates the day of yesterday
     *         in this month.
     */
    public Time getPreviousDay() {
        Calendar result = (Calendar) calendar.clone();
        result.add(Calendar.DAY_OF_MONTH, -1);
        return new Time(result);
    }

    /**
     * Get tomorrow.
     * @return An <code>int</code> indicates the day of tomorrow
     *         in this month.
     */
    public Time getNextDay() {
        Calendar result = (Calendar) calendar.clone();
        result.add(Calendar.DAY_OF_MONTH, 1);
        return new Time(result);
    }

    /**
     * Get the last hour.
     * @return An <code>int</code> indicates the number of last hour.
     */
    public Time getPreviousHour() {
        Calendar result = (Calendar) calendar.clone();
        result.add(Calendar.HOUR_OF_DAY, -1);
        return new Time(result);
    }

    /**
     * Get the next hour.
     * @return An <code>int</code> indicates the number of next hour.
     */
    public Time getNextHour() {
        Calendar result = (Calendar) calendar.clone();
        result.add(Calendar.HOUR_OF_DAY, 1);
        return new Time(result);
    }

    /**
     * Get the date in <code>String</code> type.
     * @return "DD/MM/YYYY".
     */
    public String getDateAsString() {
        return getDay() + "/" + getMonth() + "/" + getYear();
    }

    public String getSQLString() {
        return getYear() + "-" + getMonth() + "-" + getDay() + " " + getHour() + ":" + getMinute() + ":" + getSecond();
    }

    /**
     * Get the date and time in <code>String</code> type.
     * @return "YYYY-MM-DD HH:MM:SS"
     */
    public String toString() {
        return getYear() + "-" + getMonth() + "-" + getDay() + " " + getHour() + ":" + getMinute() + ":" + getSecond();
    }
}
