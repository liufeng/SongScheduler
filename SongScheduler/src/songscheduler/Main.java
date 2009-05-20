/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package songscheduler;

import gui.*;
import javax.swing.*;
/**
 *
 * @author kurtisschmidt
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        testAll();
    }


    public static void testAll(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SongSchedulerWindow().setVisible(true);
            }
        });
    }

}
