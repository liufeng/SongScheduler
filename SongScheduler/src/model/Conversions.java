/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author kurtisschmidt
 */
public class Conversions {
    public static String milliToHHMMSS(int milli) {
        int hours = milli / 3600000;
        milli = milli % 3600000;
        int mins = milli / 60000;
        milli = milli % 60000;
        int secs = milli / 1000;

        String sHours, sMins, sSecs;
        if( hours < 10 )
            sHours = "0" + hours;
        else
            sHours = "" + hours;
        if( mins < 10 )
            sMins = "0" + mins;
        else
            sMins = "" + mins;
        if( secs < 10 )
            sSecs = "0" + secs;
        else
            sSecs = "" + secs;
        return sHours + ":" + sMins + ":" + sSecs;
    }
}
