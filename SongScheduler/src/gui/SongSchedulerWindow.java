/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SongSchedulerWindow.java
 *
 * Created on May 13, 2009, 8:25:59 PM
 */
package gui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import model.Time;
import model.SongScheduler;

/**
 *
 * @author kurtisschmidt
 */
public class SongSchedulerWindow extends javax.swing.JFrame {

    /** Creates new form SongSchedulerWindow */
    public SongSchedulerWindow () {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        calendar = new datechooser.beans.DateChooserPanel();
        generateSchedulerButton = new javax.swing.JButton();
        viewSelectedButton = new javax.swing.JButton();
        browseSongsButton = new javax.swing.JButton();
        MenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        fileMenuOpen = new javax.swing.JMenuItem();
        fileMenuClose = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        calendar.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Lucida Grande", java.awt.Font.PLAIN, 13),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Lucida Grande", java.awt.Font.PLAIN, 13),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Lucida Grande", java.awt.Font.PLAIN, 13),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Lucida Grande", java.awt.Font.PLAIN, 13),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Lucida Grande", java.awt.Font.PLAIN, 13),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Lucida Grande", java.awt.Font.PLAIN, 13),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    calendar.setWeekStyle(datechooser.view.WeekDaysStyle.FULL);
    calendar.setLocale(new java.util.Locale("en", "", ""));
    Calendar min = Calendar.getInstance();
    min.add( Calendar.DATE, -1 );
    calendar.setMinDate( min );
    Calendar max = (Calendar)min.clone();
    max.add( Calendar.DATE, 7 );
    calendar.setMaxDate( max );
    calendar.addCommitListener(new datechooser.events.CommitListener() {
        public void onCommit(datechooser.events.CommitEvent evt) {
            calendarOnCommit(evt);
        }
    });

    generateSchedulerButton.setText("Generate Schedule");
    generateSchedulerButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            generateSchedulerButtonActionPerformed(evt);
        }
    });

    viewSelectedButton.setText("View Selected");
    viewSelectedButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            viewSelectedButtonMouseClicked(evt);
        }
    });

    browseSongsButton.setText("Browse Songs");
    browseSongsButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            browseSongsButtonActionPerformed(evt);
        }
    });

    fileMenu.setText("File");

    fileMenuOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.META_MASK));
    fileMenuOpen.setText("Open");
    fileMenu.add(fileMenuOpen);

    fileMenuClose.setText("Close");
    fileMenuClose.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            fileMenuCloseActionPerformed(evt);
        }
    });
    fileMenu.add(fileMenuClose);

    MenuBar.add(fileMenu);

    setJMenuBar(MenuBar);

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
        .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(generateSchedulerButton)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(viewSelectedButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 147, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 191, Short.MAX_VALUE)
                    .add(browseSongsButton))
                .add(calendar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 624, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .add(calendar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 475, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(generateSchedulerButton)
                .add(viewSelectedButton)
                .add(browseSongsButton))
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileMenuCloseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_fileMenuCloseActionPerformed
    {//GEN-HEADEREND:event_fileMenuCloseActionPerformed
        System.exit( 0 );
}//GEN-LAST:event_fileMenuCloseActionPerformed

    private void calendarOnCommit(datechooser.events.CommitEvent evt)//GEN-FIRST:event_calendarOnCommit
    {//GEN-HEADEREND:event_calendarOnCommit
        // TODO add your handling code here:
        openSelectedDates();
    }//GEN-LAST:event_calendarOnCommit

    private void viewSelectedButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewSelectedButtonMouseClicked
        openSelectedDates();
    }//GEN-LAST:event_viewSelectedButtonMouseClicked

    private void browseSongsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseSongsButtonActionPerformed
        // TODO add your handling code here:
        new SongBrowserWindow(this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_browseSongsButtonActionPerformed

    private void generateSchedulerButtonActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateSchedulerButtonActionPerformed
        Time daysArray[] = getSelectedDates();
        SongScheduler scheduler = new SongScheduler( daysArray[0] );

        for ( int i = 0; i < daysArray.length; i++ )
        {
            scheduler.generateMultipleHours( daysArray[i], 24);
        }

        new SchedulerListWindow( daysArray, scheduler , this ).setVisible( true );
        this.setVisible( false );
}//GEN-LAST:event_generateSchedulerButtonActionPerformed

        private Time[] getSelectedDates () {
        Iterator<Calendar> iterator = calendar.getSelectedPeriodSet().getDates().iterator();
        ArrayList days = new ArrayList();
        Time daysArray[];

        // If nothing selected, then return
        if ( !iterator.hasNext() ) {
            return null;
        }

        while ( iterator.hasNext() ) {
            days.add( iterator.next() );
        }

        daysArray = new Time[days.size()];
        for ( int i = 0; i < days.size(); i++ )
        {
            Calendar currentDay = (Calendar)days.get( i );
            daysArray[i] = new Time( currentDay.get(Calendar.YEAR), currentDay.get(Calendar.MONTH), currentDay.get( Calendar.DATE), 0,0,0 );
        }

        return daysArray;
    }

    private void openSelectedDates () {
        Time daysArray[] = getSelectedDates();

        // No days selected, just stop now
        if ( daysArray == null )
            return;

        new SchedulerListWindow( daysArray, this ).setVisible( true );
        this.setVisible( false );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JButton browseSongsButton;
    private datechooser.beans.DateChooserPanel calendar;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem fileMenuClose;
    private javax.swing.JMenuItem fileMenuOpen;
    private javax.swing.JButton generateSchedulerButton;
    private javax.swing.JButton viewSelectedButton;
    // End of variables declaration//GEN-END:variables
}
