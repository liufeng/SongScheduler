/**
 * SongSchedulerWindow.java
 *
 * @author Kurtis Schmidt & Jordan Wiebe
 */
package gui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import model.*;
import javax.swing.*;

/**
 *
 * @author kurtisschmidt
 * @author jordan
 */
public class SongSchedulerWindow extends javax.swing.JFrame {

    private ArrayList songs;                // a list of all of the song that are currently loaded into the system
    private DefaultListModel listModel;     // the list model used to hold the songs for display in the JList

    /**
     * Constructor
     */
    public SongSchedulerWindow () {
        //get the songs from the database
        songs = Database.getSongs();
        initComponents();
        //populate the JList with the songs
        updateSongList();
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
        generateScheduleButton = new javax.swing.JButton();
        viewSelectedButton = new javax.swing.JButton();
        selectedSongInfoPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        songPopularityDisplay = new javax.swing.JTextField();
        songLastPlayedDisplay = new javax.swing.JLabel();
        songPlayCountDisplay = new javax.swing.JLabel();
        songNameDisplay = new javax.swing.JTextField();
        songArtistDisplay = new javax.swing.JTextField();
        songAlbumDisplay = new javax.swing.JTextField();
        songGenreDisplay = new javax.swing.JTextField();
        songYearDisplay = new javax.swing.JTextField();
        songLengthDisplay = new javax.swing.JTextField();
        songSearchButton = new javax.swing.JButton();
        clearSongData = new javax.swing.JButton();
        changeHoldingFile = new javax.swing.JButton();
        requestSong = new javax.swing.JButton();
        songListScrollPane = new javax.swing.JScrollPane();
        songList = new javax.swing.JList();
        schedulePanel = new gui.SchedulePanel();
        addSongButton = new javax.swing.JButton();
        commitButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        fixAllButton = new javax.swing.JButton();
        MenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        fileMenuPrint = new javax.swing.JMenuItem();
        fileMenuClose = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

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

        generateScheduleButton.setText("Generate Schedule");
        generateScheduleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateScheduleButtonActionPerformed(evt);
            }
        });

        viewSelectedButton.setText("View Selected");
        viewSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewSelectedButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Song Name:");

        jLabel2.setText("Artist:");

        jLabel3.setText("Album:");

        jLabel4.setText("Genre:");

        jLabel5.setText("Year:");

        jLabel6.setText("Length:");

        jLabel7.setText("Play count:");

        jLabel8.setText("Last played:");

        jLabel9.setText("Popularity:");

        songSearchButton.setText("Search");
        songSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                songSearchButtonActionPerformed(evt);
            }
        });

        clearSongData.setText("Clear");
        clearSongData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearSongDataActionPerformed(evt);
            }
        });

        changeHoldingFile.setText("Change Song File");
        changeHoldingFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeHoldingFileActionPerformed(evt);
            }
        });

        requestSong.setText("Request");
        requestSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestSongActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout selectedSongInfoPanelLayout = new org.jdesktop.layout.GroupLayout(selectedSongInfoPanel);
        selectedSongInfoPanel.setLayout(selectedSongInfoPanelLayout);
        selectedSongInfoPanelLayout.setHorizontalGroup(
            selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(selectedSongInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(selectedSongInfoPanelLayout.createSequentialGroup()
                        .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(songArtistDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 556, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(songNameDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 556, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(songAlbumDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 556, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(songGenreDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 556, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(songYearDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 556, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(songLengthDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 556, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, selectedSongInfoPanelLayout.createSequentialGroup()
                        .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel8))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(songPopularityDisplay, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                            .add(songPlayCountDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(songLastPlayedDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 158, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(87, 87, 87)
                        .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(selectedSongInfoPanelLayout.createSequentialGroup()
                                .add(59, 59, 59)
                                .add(requestSong, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 103, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(clearSongData, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                            .add(selectedSongInfoPanelLayout.createSequentialGroup()
                                .add(changeHoldingFile, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 162, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(songSearchButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        selectedSongInfoPanelLayout.setVerticalGroup(
            selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(selectedSongInfoPanelLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(songNameDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(songArtistDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(songAlbumDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(songGenreDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(songYearDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(songLengthDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(selectedSongInfoPanelLayout.createSequentialGroup()
                        .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel7)
                            .add(songPlayCountDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel8)
                            .add(songLastPlayedDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel9)
                            .add(songPopularityDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(selectedSongInfoPanelLayout.createSequentialGroup()
                        .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(clearSongData)
                            .add(requestSong))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(selectedSongInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(changeHoldingFile)
                            .add(songSearchButton)))))
        );

        songListScrollPane.setMaximumSize(new java.awt.Dimension(300, 300));

        songList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        songList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                songListValueChanged(evt);
            }
        });
        songListScrollPane.setViewportView(songList);

        addSongButton.setText("Add");
        addSongButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSongButtonActionPerformed(evt);
            }
        });

        commitButton.setText("Commit");
        commitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commitButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        fixAllButton.setText("Fix All");
        fixAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fixAllButtonActionPerformed(evt);
            }
        });

        fileMenu.setText("File");

        fileMenuPrint.setText("Print");
        fileMenuPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuPrintActionPerformed(evt);
            }
        });
        fileMenu.add(fileMenuPrint);

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
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(11, 11, 11)
                                .add(calendar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 447, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(songListScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 442, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(27, 27, 27)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(commitButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, fixAllButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, deleteButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, addSongButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, viewSelectedButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, generateScheduleButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(selectedSongInfoPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 669, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(schedulePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, schedulePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(calendar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(generateScheduleButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(viewSelectedButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(addSongButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(deleteButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(fixAllButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(commitButton))
                            .add(songListScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 231, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(selectedSongInfoPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * fileMenuCloseActionPerformed
     *
     * Quits the program.
     *
     * @param evt
     * @return void
     */
    private void fileMenuCloseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_fileMenuCloseActionPerformed
    {//GEN-HEADEREND:event_fileMenuCloseActionPerformed
        System.exit( 0 );
}//GEN-LAST:event_fileMenuCloseActionPerformed

    /**
     * calendarOnCommit
     *
     * Opens the selected dates when a date is double clicked.
     *
     * @param evt
     * @return void
     */
    private void calendarOnCommit(datechooser.events.CommitEvent evt)//GEN-FIRST:event_calendarOnCommit
    {//GEN-HEADEREND:event_calendarOnCommit
        openSelectedDates();
    }//GEN-LAST:event_calendarOnCommit

    /**
     * browseSongsButtonActionPerformed
     *
     * Opens the SongBrowserWindow. Hides itself.
     *
     * @param evt
     * @return void
     */
    /**
     * generateScheduleButtonActionPerformed
     *
     * Creates a new SongScheduler for dates that are selected. Creates a new schedule
     * for every hour, in every day selected.  Opens a SchedulerListWindow to display
     * the new schedules.
     *
     * @param evt
     * @return void
     */
    private void generateScheduleButtonActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateScheduleButtonActionPerformed
        Time daysArray[] = getSelectedDates();
        SongScheduler scheduler = new SongScheduler( daysArray[0] );

        for ( int i = 0; i < daysArray.length; i++ ) {
            scheduler.generateMultipleHours( daysArray[i], 24 );
        }

        schedulePanel.openDates( daysArray, scheduler );
}//GEN-LAST:event_generateScheduleButtonActionPerformed

    /**
     * viewSelectedButtonActionPerformed
     *
     * Open selected dates in calendar.
     *
     * @param evt
     * @return void
     */
    private void viewSelectedButtonActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewSelectedButtonActionPerformed
        openSelectedDates();
    }//GEN-LAST:event_viewSelectedButtonActionPerformed

    /**
     * songSearchButtonActionPerformed
     *
     * Search for a song by title, artist, album and/or year
     *
     * @param evt
     * @return void
     */
    private void songSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_songSearchButtonActionPerformed
        //get the search information from the form
        String title = songNameDisplay.getText();
        String artist = songArtistDisplay.getText();
        String album = songAlbumDisplay.getText();
        String year = songYearDisplay.getText();

        //if the artist, ablum, or year are blank set them to null
        if( artist.equals("") )
            artist = null;

        if( album.equals("") )
            album = null;

        if( year.equals("") )
            year = null;

        //set the list of song to the search results
        songs = Database.getSongs(title,artist,album,year);
        //populate the JList with the search results
        updateSongList();
    }//GEN-LAST:event_songSearchButtonActionPerformed

    /**
     * songListValueChanged
     *
     * Update the information displayed about a song to the information about the song that is selected in the JList
     *
     * @param evt
     * @return void
     */
    private void songListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_songListValueChanged
        //get the selected song
        Song selection = (Song) songs.get( songList.getSelectedIndex() );
        //conver the songs length
        int milliseconds = selection.getLength();
        int minutes = milliseconds / 60000;
        int seconds = ( milliseconds - ( minutes * 60000 ) ) / 1000;
        String length = minutes + ":";
        if ( seconds < 10 ) {
            length += "0" + seconds;
        } else {
            length += seconds;
        }

        //set all of the forms fields
        songLengthDisplay.setText( length );
        songAlbumDisplay.setText( selection.getRecordingTitle() );
        songArtistDisplay.setText( selection.getPerformer() );
        songGenreDisplay.setText( selection.getRecordingType() );
        songLastPlayedDisplay.setText( selection.getLastPlayed().toString() );
        songNameDisplay.setText( selection.getTitle() );
        songPlayCountDisplay.setText( selection.getNumberOfPlays() + "" );
        songPopularityDisplay.setText( selection.getPopularity() + "" );
        songYearDisplay.setText( selection.getYear() );
}//GEN-LAST:event_songListValueChanged

    /**
     * clearSongDataActionPerformed
     *
     * Clear all of the song fields and update the JList with the full list of songs from this holdings file
     *
     * @param evt
     * @return void
     */
    private void clearSongDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearSongDataActionPerformed
        //clear the fields
        songLengthDisplay.setText( "" );
        songAlbumDisplay.setText( "" );
        songArtistDisplay.setText( "" );
        songGenreDisplay.setText( "" );
        songLastPlayedDisplay.setText( "" );
        songNameDisplay.setText( "" );
        songPlayCountDisplay.setText( "" );
        songPopularityDisplay.setText( "" );
        songYearDisplay.setText( "" );

        //get all the songs from the database again
        songs = Database.getSongs();
        //update the JList
        updateSongList();
    }//GEN-LAST:event_clearSongDataActionPerformed

    /**
     * changeHoldingFileActionPerformed
     *
     * Start the process for opening the Holdings file window
     *
     * @param evt
     * @return void
     */
    private void changeHoldingFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeHoldingFileActionPerformed
        AuthenticateWindow enterPassword = new AuthenticateWindow(this);
        enterPassword.setVisible(true);
    }//GEN-LAST:event_changeHoldingFileActionPerformed

    /**
     * requestSongActionPerformed
     *
     * Record a request made for a song
     *
     * @param evt
     * @return void
     */
    private void requestSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestSongActionPerformed
        Song song = (Song) songs.get( songList.getSelectedIndex() );
        song.makeRequest();
    }//GEN-LAST:event_requestSongActionPerformed

    /**
     * fileMenuPrintActionPerformed
     *
     * Print the schedule
     *
     * @param evt
     * @return void
     */
    private void fileMenuPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuPrintActionPerformed
        schedulePanel.printSchedule();
    }//GEN-LAST:event_fileMenuPrintActionPerformed

    /**
     * addSongButtonActionPerformed
     *
     * Add a song to the selected schedule
     *
     * @param evt
     * @return void
     */
    private void addSongButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSongButtonActionPerformed
        Song song = (Song) songs.get( songList.getSelectedIndex() );
        schedulePanel.addSong(song);
    }//GEN-LAST:event_addSongButtonActionPerformed

    /**
     * commitButtonActionPerformed
     *
     * Commit all tentative schedules
     *
     * @param evt
     * @return void
     */
    private void commitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commitButtonActionPerformed
        schedulePanel.commit();
    }//GEN-LAST:event_commitButtonActionPerformed

    /**
     * deleteButtonActionPerformed
     *
     * Remove the selected song from their schedules
     *
     * @param evt
     * @return void
     */
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        schedulePanel.deleteSelected();
    }//GEN-LAST:event_deleteButtonActionPerformed

    /**
     * fixAllButtonActionPerformed
     *
     * Fix all of the broken schedules
     *
     * @param evt
     * @return void
     */
    private void fixAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fixAllButtonActionPerformed
        schedulePanel.fixAllSchedules();
    }//GEN-LAST:event_fixAllButtonActionPerformed



    public void changeSongs(){
        songs = Database.getSongs();
        updateSongList();
    }

    /**
     * updateSongList
     *
     * Update the JList so that it displays the song in the songs ArrayList
     *
     * @return void
     */
    private void updateSongList()
    {
        Song currSong;
        //make a new listModel
        listModel = new DefaultListModel();

        //add each song in the ArrayList to the listModel
        for ( int i = 0; i < songs.size(); i++ ) {
            currSong = (Song) songs.get( i );
            listModel.add( i, currSong );
        }

        //used the list model made
        songList.setModel( listModel );
    }

    /**
     * getSelectedDates
     *
     * Gets all the selected dates from the calendar.
     *
     * @return Time[] - Array of Time objects, 1 for each day selected.
     * @return Returns null if no days are selected.
     */
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

        daysArray = new Time[ days.size() ];
        for ( int i = 0; i < days.size(); i++ ) {
            Calendar currentDay = (Calendar) days.get( i );
            daysArray[i] = new Time( currentDay.get( Calendar.YEAR ), ( currentDay.get( Calendar.MONTH ) + 1 ), currentDay.get( Calendar.DATE ), 0, 0, 0 );
        }

        return daysArray;
    }

    /**
     * openSelectedDates
     *
     * Opens the selected dates in a SchedulerListWindow.
     *
     * @return void
     */
    private void openSelectedDates () {
        Time daysArray[] = getSelectedDates();

        // No days selected, just stop now
        if ( daysArray == null ) {
            return;
        }

       // new SchedulerListWindow( daysArray, this ).setVisible( true );
       // this.setVisible( false );
        schedulePanel.openDates(daysArray);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JButton addSongButton;
    private datechooser.beans.DateChooserPanel calendar;
    private javax.swing.JButton changeHoldingFile;
    private javax.swing.JButton clearSongData;
    private javax.swing.JButton commitButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem fileMenuClose;
    private javax.swing.JMenuItem fileMenuPrint;
    private javax.swing.JButton fixAllButton;
    private javax.swing.JButton generateScheduleButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton requestSong;
    private gui.SchedulePanel schedulePanel;
    private javax.swing.JPanel selectedSongInfoPanel;
    private javax.swing.JTextField songAlbumDisplay;
    private javax.swing.JTextField songArtistDisplay;
    private javax.swing.JTextField songGenreDisplay;
    private javax.swing.JLabel songLastPlayedDisplay;
    private javax.swing.JTextField songLengthDisplay;
    private javax.swing.JList songList;
    private javax.swing.JScrollPane songListScrollPane;
    private javax.swing.JTextField songNameDisplay;
    private javax.swing.JLabel songPlayCountDisplay;
    private javax.swing.JTextField songPopularityDisplay;
    private javax.swing.JButton songSearchButton;
    private javax.swing.JTextField songYearDisplay;
    private javax.swing.JButton viewSelectedButton;
    // End of variables declaration//GEN-END:variables
}
