/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author liufeng
 */
public class ConversionsTest {

    public ConversionsTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of milliToHHMMSS method, of class Conversions.
     */
    @Test
    public void testMilliToHHMMSS() {
        System.out.println("milliToHHMMSS");
        int milli = 0;
        String expResult = "00:00:00";
        String result = Conversions.milliToHHMMSS(milli);
        assertEquals(expResult, result);
    }

    /**
     * Test of milliFromMSS method, of class Conversions.
     */
    @Test
    public void testMilliFromMSS() {
        System.out.println("milliFromMSS");
        String time = "00:00";
        int expResult = 0;
        int result = Conversions.milliFromMSS(time);
        assertEquals(expResult, result);
    }

}