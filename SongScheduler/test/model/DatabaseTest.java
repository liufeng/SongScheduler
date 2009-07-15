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
        Database.init("test/testLibrary.txt");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testGetSongs() {
        ArrayList<Song> allSongs = Database.getSongs();
        assertEquals(31, allSongs.size());

        Song prev = null;
        for (Song curr : allSongs) {
            assertTrue(prev == null || curr.getTitle().compareTo(prev.getTitle()) > 0);
            prev = curr;
        }
    }

    @Test
    public void testGetSongsByPriority() {
        ArrayList<Song> allSongs = Database.getSongsByPriority();
        assertEquals(31, allSongs.size());

        assertTrue(allSongs.get(0).getAccessNumber() == 5);
        assertTrue(allSongs.get(1).getAccessNumber() == 27);
        assertTrue(allSongs.get(29).getAccessNumber() == 20);
        assertTrue(allSongs.get(30).getAccessNumber() == 26);
    }

    @Test
    public void testSetHoldingsFile() {
        ArrayList<Song> initialTest1Songs = Database.getSongs();

        Database.setHoldingsFile("test/testLibrary2.txt");
        ArrayList<Song> test2Songs = Database.getSongs();
        assertEquals(42, test2Songs.size());

        Database.setHoldingsFile("test/testLibrary.txt");
        ArrayList<Song> finalTest1Songs = Database.getSongs();
        assertEquals(initialTest1Songs.size(), finalTest1Songs.size());

        for (int i = 0; i < finalTest1Songs.size(); i++) {
            assertTrue(initialTest1Songs.get(i).toString().equals(finalTest1Songs.get(i).toString()));
        }
    }

    @Test
    public void testCommitAndFetchSchedule() {
        ArrayList<Song> songs = Database.getSongs();

        Schedule sched = new Schedule(new Time(2009, 9, 9, 9, 0, 0));
        sched.add(songs.get(0));
        sched.add(songs.get(1));

        Database.commitSchedule(sched);
        Database.init("test/testLibrary.txt");

        Schedule retSched = Database.getScheduleFromDB(new Time(2009, 9, 9, 9, 0, 0));

        assertEquals(sched.getSongCount(), retSched.getSongCount());

        for(int i=0; i<sched.getSongCount(); i++){
            assertTrue(sched.getSong(i).toString().equals(retSched.getSong(i).toString()));
        }
    }
}