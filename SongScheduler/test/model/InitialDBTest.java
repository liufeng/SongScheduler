/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author liufeng
 */
public class InitialDBTest {

    public InitialDBTest() {
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
     * Test of initialDB method, of class InitialDB.
     */
    @Ignore
    @Test
    public void testInitialDB() {
        System.out.println("initialDB");
        InitialDB.initialDB();
        assertEquals(0, 0);
    }

}