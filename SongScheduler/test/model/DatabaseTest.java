/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
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
        System.out.println("init");    
        Database.init("testLibrary.txt");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testGetSongs(){
        ArrayList<Song> allSongs = Database.getSongs();
        assertEquals(31, allSongs.size());

        Song prev = null;
        for(Song curr : allSongs){
            assertTrue(prev == null || curr.getTitle().compareTo(prev.getTitle()) > 0);
            prev = curr;
        }
    }

    @Test
    public void testGetSongsByPriority(){
        ArrayList<Song> allSongs = Database.getSongsByPriority();
        assertEquals(31, allSongs.size());

        assertTrue(allSongs.get(0).getAccessNumber() == 5);
        assertTrue(allSongs.get(1).getAccessNumber() == 27);
        assertTrue(allSongs.get(29).getAccessNumber() == 20);
        assertTrue(allSongs.get(30).getAccessNumber() == 26);
    }

    public void testCommitAndFetchSchedule(){
        
    }
}