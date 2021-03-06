/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HoldingsFilesWindow.java
 *
 * Created on Jul 13, 2009, 1:42:55 PM
 */

package gui;
import java.util.Iterator;
import java.util.ArrayList;
import javax.swing.*;
import model.HoldingFiles;
import model.Song;
import model.Database;
import model.Time;
import model.Conversions;

/**
 *
 * @author jordan
 * @author kurtisschmidt
 */
public class HoldingsFilesWindow extends javax.swing.JFrame {
    private ArrayList songs;                // The songs from the current holdings file
    private DefaultListModel listModel;     // The list model for the songs JList
    private SongSchedulerWindow parent;
    /**
     * Constructor
     */
    public HoldingsFilesWindow(SongSchedulerWindow parent) {
        initComponents();
        this.parent = parent;
        this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        DefaultListModel model = new DefaultListModel();
        jList1.setModel(model);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        Iterator<String> iter = HoldingFiles.iterator();
        while( iter.hasNext() )
        {
            model.add( model.size(), iter.next());
        }
        updateSongList();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        songArtistDisplay = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        songLengthDisplay = new javax.swing.JTextField();
        songAlbumDisplay = new javax.swing.JTextField();
        songNameDisplay = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        songGenreDisplay = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        songYearDisplay = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        selectHoldingFile = new javax.swing.JButton();
        fileName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        makeNewFile = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        setDefaultFile = new javax.swing.JButton();
        songPopularityDisplay = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        addSong = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        songList = new javax.swing.JList();
        saveSong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setText("Year:");

        jLabel2.setText("Artist:");

        jLabel3.setText("Song Name:");

        jLabel6.setText("Length:");

        jLabel4.setText("Genre:");

        jLabel7.setText("Album:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Holdings File"));

        selectHoldingFile.setText("Select");
        selectHoldingFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectHoldingFileActionPerformed(evt);
            }
        });

        jLabel1.setText("File Name: ");

        makeNewFile.setText("Make New");
        makeNewFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeNewFileActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jList1);

        setDefaultFile.setText("Set as Default");
        setDefaultFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setDefaultFileActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                        .add(selectHoldingFile)
                        .add(18, 18, 18)
                        .add(setDefaultFile))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(fileName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(makeNewFile)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(setDefaultFile)
                    .add(selectHoldingFile))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(fileName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(makeNewFile)))
        );

        jLabel9.setText("Popularity:");

        addSong.setText("Add");
        addSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSongActionPerformed(evt);
            }
        });

        songList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                songListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(songList);

        saveSong.setText("Save");
        saveSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSongActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(saveSong, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(addSong, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 122, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .add(jLabel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                            .add(layout.createSequentialGroup()
                                .add(jLabel9)
                                .add(61, 61, 61)))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(songPopularityDisplay)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, songLengthDisplay, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, songYearDisplay, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, songGenreDisplay, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, songAlbumDisplay, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, songArtistDisplay, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, songNameDisplay, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))))
                .add(18, 18, 18)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 341, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel3)
                            .add(songNameDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel2)
                            .add(songArtistDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel7)
                            .add(songAlbumDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel4)
                            .add(songGenreDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel5)
                            .add(songYearDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel6)
                            .add(songLengthDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel9)
                            .add(songPopularityDisplay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(addSong)
                            .add(saveSong))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 17, Short.MAX_VALUE)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * addSongActionPerformed
     *
     * Add a song to the selected holdings file
     *
     * @param evt
     * @return void
     */
    private void addSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSongActionPerformed
        //get the song info
        String name       = songNameDisplay.getText();
        String artist     = songArtistDisplay.getText();
        String album      = songAlbumDisplay.getText();
        String genre      = songGenreDisplay.getText();
        String year       = songYearDisplay.getText();
        String length     = songLengthDisplay.getText();
        String popularity = songPopularityDisplay.getText();
        //get the next access number
        int accessNumber = Database.getNextAccessNum();
        //get the current time
        Time now = new Time();

        // title; artist; album; genre; year; access_number; datetime_added; popularity; length; play_count; last_played

        // make a string with the song info
        String song = name + ";"
                    + artist + ";"
                    + album + ";"
                    + genre + ";"
                    + year + ";"
                    + accessNumber + ";"
                    + now.toString() + ";"
                    + popularity +";"
                    + Conversions.milliFromMSS(length) + ";"
                    + "0" + ";"
                    + "0";
        //write the song to the holdings file
        String holdingsFile = (String)jList1.getSelectedValue();
        Database.addSong(song);
        updateSongList();
    }//GEN-LAST:event_addSongActionPerformed

    /**
     * selectHoldingFileActionPerformed
     *
     * Select a holdings file to be the current holdings file for the program
     *
     * @param evt
     * @return void
     */
    private void selectHoldingFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectHoldingFileActionPerformed
        HoldingFiles.setCurrent( (String)jList1.getSelectedValue() );
        Database.setHoldingsFile( HoldingFiles.getCurrent() );
        updateSongList();
        parent.changeSongs();
    }//GEN-LAST:event_selectHoldingFileActionPerformed

    /**
     * setDefaultFileActionPerformed
     *
     * Select a holdings file to be the default holdings file for the program
     *
     * @param evt
     * @return void
     */
    private void setDefaultFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setDefaultFileActionPerformed
        HoldingFiles.setDefault( (String)jList1.getSelectedValue() );
    }//GEN-LAST:event_setDefaultFileActionPerformed

    /**
     * makeNewFileActionPerformed
     *
     * Make a new holdings file
     *
     * @param evt
     * @return void
     */
    private void makeNewFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeNewFileActionPerformed
        String filename = fileName.getText();
        HoldingFiles.addFile(filename);
        DefaultListModel model = (DefaultListModel)jList1.getModel();
        model.add(model.getSize(), filename);
    }//GEN-LAST:event_makeNewFileActionPerformed

    /**
     * saveSongActionPerformed
     *
     * Save the info from the song selected in the JList to the Database
     *
     * @param evt
     * @return void
     */
    private void saveSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSongActionPerformed
        //get the song from the JList
        Song song = (Song) songs.get( songList.getSelectedIndex() );
        //get the data from the text fields
        song.setPerformer(songArtistDisplay.getText());
        song.setRecordingTitle(songAlbumDisplay.getText());
        song.setRecordingType(songGenreDisplay.getText());
        song.setTitle(songNameDisplay.getText());
        song.setYear(songYearDisplay.getText());
        song.setPopularity(Integer.parseInt(songPopularityDisplay.getText()));
        //update the songs info
        song.updatePriority();
    }//GEN-LAST:event_saveSongActionPerformed

    /**
     * songListValueChanged
     *
     * Update the song text fields to the info from the the song selected in the JList
     *
     * @param evt
     * @return void
     */
    private void songListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_songListValueChanged
        //get the song from the list
        Song selection = (Song) songs.get( songList.getSelectedIndex() );
        //calculate the minutes, seconds and milliseconds for the song length
        int milliseconds = selection.getLength();
        int minutes = milliseconds / 60000;
        int seconds = ( milliseconds - ( minutes * 60000 ) ) / 1000;
        String length = minutes + ":";
        if ( seconds < 10 ) {
            length += "0" + seconds;
        } else {
            length += seconds;
        }

        //set the text fields
        songLengthDisplay.setText( length );
        songAlbumDisplay.setText( selection.getRecordingTitle() );
        songArtistDisplay.setText( selection.getPerformer() );
        songGenreDisplay.setText( selection.getRecordingType() );
        songNameDisplay.setText( selection.getTitle() );
        songPopularityDisplay.setText( selection.getPopularity() + "" );
        songYearDisplay.setText( selection.getYear() );
    }//GEN-LAST:event_songListValueChanged

    /**
     * updateSongList
     *
     * Populate the JList with the songs from the currently selected holdings file
     *
     * @return void
     */
    private void updateSongList()
    {
        Song currSong;
        //get the songs
        songs = Database.getSongs();
        listModel = new DefaultListModel();

        //put each song from the holdings file into the list model
        for ( int i = 0; i < songs.size(); i++ ) {
            currSong = (Song) songs.get( i );
            listModel.add( i, currSong );
        }

        //use the new list model for the song JList
        songList.setModel( listModel );
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSong;
    private javax.swing.JTextField fileName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton makeNewFile;
    private javax.swing.JButton saveSong;
    private javax.swing.JButton selectHoldingFile;
    private javax.swing.JButton setDefaultFile;
    private javax.swing.JTextField songAlbumDisplay;
    private javax.swing.JTextField songArtistDisplay;
    private javax.swing.JTextField songGenreDisplay;
    private javax.swing.JTextField songLengthDisplay;
    private javax.swing.JList songList;
    private javax.swing.JTextField songNameDisplay;
    private javax.swing.JTextField songPopularityDisplay;
    private javax.swing.JTextField songYearDisplay;
    // End of variables declaration//GEN-END:variables

}
