/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SchedulerListWindow.java
 *
 * Created on May 14, 2009, 2:21:35 PM
 */

package gui;

import model.*;
import javax.swing.JOptionPane;
/**
 *
 * @author kurtisschmidt
 */
public class SchedulerListWindow extends javax.swing.JFrame {
    private SongSchedulerWindow parentWindow;
    private String daysList[];

    /** Creates new form SchedulerListWindow */
    public SchedulerListWindow ( String days[] , SongSchedulerWindow parentWindow ) {
        daysList = days;
        this.parentWindow = parentWindow;

        initComponents();

        // Create a new hourSchedulePanel for every day in the array.
        for ( int i = 0; i < days.length; i++ ) {
            scheduleTabPane.addTab( days[i], null, new DayScheduleScrollPanel(), "A panel of some sort" );
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scheduleTabPane = new javax.swing.JTabbedPane();
        deleteButton = new javax.swing.JButton();
        addSongButton = new javax.swing.JButton();
        addScheduleButton = new javax.swing.JButton();
        doneButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        editMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        deleteButton.setText("Delete Selected");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        addSongButton.setText("Add Song");
        addSongButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSongButtonActionPerformed(evt);
            }
        });

        addScheduleButton.setText("Add Schedule");

        doneButton.setText("Done");
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        fileMenu.setText("File");
        menuBar.add(fileMenu);

        editMenu.setText("Edit");
        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(deleteButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(addSongButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(addScheduleButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(doneButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(scheduleTabPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                        .add(17, 17, 17))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(scheduleTabPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 437, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(1, 1, 1)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(deleteButton)
                    .add(addSongButton)
                    .add(addScheduleButton)
                    .add(doneButton))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        DayScheduleScrollPanel currentTab = (DayScheduleScrollPanel)scheduleTabPane.getSelectedComponent();
        currentTab.deleteSelectedSongs();
}//GEN-LAST:event_deleteButtonActionPerformed

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
        if( parentWindow != null){
            parentWindow.setVisible(true);
        }
    }//GEN-LAST:event_doneButtonActionPerformed

    private void addSongButtonActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSongButtonActionPerformed
        DayScheduleScrollPanel currentTab = (DayScheduleScrollPanel)scheduleTabPane.getSelectedComponent();

        String a = JOptionPane.showInputDialog( "Input a song name or something" );
        Song song = new Song(a,"","","","",0,0,50,0,new Time(),new Time(),1.0);

        try {
            currentTab.addSong( song );
        }
        catch (Exception exp) {
            System.out.println(exp);
        }
    }//GEN-LAST:event_addSongButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String days[] = { "Sunday", "Monday" };
                new SchedulerListWindow( days , null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addScheduleButton;
    private javax.swing.JButton addSongButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton doneButton;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTabbedPane scheduleTabPane;
    // End of variables declaration//GEN-END:variables

}
