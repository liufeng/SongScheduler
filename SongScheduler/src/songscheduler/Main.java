/**
 * Main.java
 *
 * @author Kurtis Schmidt
 */
package songscheduler;

import gui.*;
import model.Database;
import java.io.File;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main ( String args[] ) {
        // Check for the database
        File f = new File("song.db");
        if ( !f.exists() )
        {
            Database.init();
            Database.updateSongInfo();
        }
        // TODO: Update every 7 days instead of only first time.

        // Run the program
        java.awt.EventQueue.invokeLater( new Runnable() {
            public void run () {
                new SongSchedulerWindow().setVisible( true );
            }
        } );
    }
}
