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
public class DatabaseTest {

    public DatabaseTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of init method, of class Database.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        Database.init();
    }

    /**
     * Test of updateSongInfo method, of class Database.
     */
    @Test
    public void testUpdateSongInfo() {
        System.out.println("updateSongInfo");
        Database.loadSongInfo();
    }

}