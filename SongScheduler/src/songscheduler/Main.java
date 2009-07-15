/**
 * Main.java
 *
 * @author Kurtis Schmidt
 */
package songscheduler;

import gui.*;
import model.Database;
import model.HoldingFiles;
import model.Authentication;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main ( String args[] ) {

        HoldingFiles.init();
        Database.init(HoldingFiles.getCurrent()); //This will be removed and holdings file dynamically selected from GUI input
        Authentication.init();
        
        // TODO: Update every 7 days instead of only first time.

        // Run the program
        java.awt.EventQueue.invokeLater( new Runnable() {
            public void run () {
                new SongSchedulerWindow().setVisible( true );
            }
        } );
    }
}
