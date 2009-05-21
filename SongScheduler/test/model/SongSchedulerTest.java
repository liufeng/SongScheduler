/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author liufeng
 */
public class SongSchedulerTest {

    public SongSchedulerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of generateOneHour method, of class SongScheduler.
     */
    @Test
    public void testGenerateOneHour() {
        System.out.println("generateOneHour");
        Time startTime = new Time();
        SongScheduler instance = new SongScheduler();
        //Schedule expResult = null;
        int expResult = 0;
        Schedule result = instance.generateOneHour(startTime);
        assertEquals(expResult, result.getDuration());
    }

}