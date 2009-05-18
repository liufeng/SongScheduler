/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * hourSchedulePanel.java
 *
 * Created on May 14, 2009, 2:22:30 PM
 */

package gui;
import javax.swing.*;

/**
 *
 * @author jordan
 */
public class HourSchedulePanel extends javax.swing.JPanel {
    private String buttonDisplay;
    private String[] songs;

    private ImageIcon icon_ddSelectedDown	 = new ImageIcon(getClass().getResource("/gui/icons/HourScheduleDDButton/ddSelectedDown.png"));
    private ImageIcon icon_ddSelectedRight	 = new ImageIcon(getClass().getResource("/gui/icons/HourScheduleDDButton/ddSelectedRight.png"));
    private ImageIcon icon_ddSelectedTitle	 = new ImageIcon(getClass().getResource("/gui/icons/HourScheduleDDButton/ddSelectedTitle.png"));
    private ImageIcon icon_ddUnselectedDown	 = new ImageIcon(getClass().getResource("/gui/icons/HourScheduleDDButton/ddUnselectedDown.png"));
    private ImageIcon icon_ddUnselectedRight = new ImageIcon(getClass().getResource("/gui/icons/HourScheduleDDButton/ddUnselectedRight.png"));
    private ImageIcon icon_ddUnselectedTitle = new ImageIcon(getClass().getResource("/gui/icons/HourScheduleDDButton/ddUnselectedTitle.png"));

    private enum DROP_DOWN_STATE { RIGHT, DOWN };
    private DROP_DOWN_STATE dropDownState = DROP_DOWN_STATE.RIGHT;

    private boolean isSelected = false;
    
    /** Creates new form hourSchedulePanel */
    public HourSchedulePanel( String timePeriod, String[] songs ) {
        this.buttonDisplay = timePeriod;
        this.songs = songs;

        initComponents();
        songList.setVisible(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        songList = new javax.swing.JList();
        buttonLayeredPanel = new javax.swing.JLayeredPane();
        dropDownButton = new javax.swing.JToggleButton();
        timeButton = new javax.swing.JButton();

        songList.setBackground(new java.awt.Color(238, 238, 238));
        songList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = songs;
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        songList.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                songListFocusLost(evt);
            }
        });

        dropDownButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/HourScheduleDDButton/ddUnselectedRight.png"))); // NOI18N
        dropDownButton.setAlignmentY(0.0F);
        dropDownButton.setBorderPainted(false);
        dropDownButton.setIconTextGap(0);
        dropDownButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dropDownButtonFocusLost(evt);
            }
        });
        dropDownButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dropDownButtonMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dropDownButtonMouseClicked(evt);
            }
        });
        dropDownButton.setBounds(300, 0, 33, 30);
        buttonLayeredPanel.add(dropDownButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        timeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/HourScheduleDDButton/ddUnselectedTitle.png"))); // NOI18N
        timeButton.setBorderPainted(false);
        timeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        timeButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        timeButton.setText( buttonDisplay );
        timeButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                timeButtonFocusLost(evt);
            }
        });
        timeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                timeButtonMousePressed(evt);
            }
        });
        timeButton.setBounds(0, 0, 310, 30);
        buttonLayeredPanel.add(timeButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(buttonLayeredPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 338, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, songList, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 338, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(buttonLayeredPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(298, Short.MAX_VALUE))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                    .add(34, 34, 34)
                    .add(songList, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 294, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );
    }//GEN-END:initComponents

    private void songListFocusLost (java.awt.event.FocusEvent evt) {//GEN-FIRST:event_songListFocusLost
        // TODO add your handling code here:
        int blank[] = {};
        songList.setSelectedIndices( blank );
}//GEN-LAST:event_songListFocusLost

    private void timeButtonMousePressed (java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeButtonMousePressed
        isSelected = true;
        timeButton.setIcon( icon_ddSelectedTitle );

        setDropDownButtonIconFocused();
    }//GEN-LAST:event_timeButtonMousePressed

    private void dropDownButtonMousePressed (java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dropDownButtonMousePressed
        isSelected = true;
        timeButton.setIcon( icon_ddSelectedTitle );

        setDropDownButtonIconFocused();
    }//GEN-LAST:event_dropDownButtonMousePressed

    private void timeButtonFocusLost (java.awt.event.FocusEvent evt) {//GEN-FIRST:event_timeButtonFocusLost
        if ( evt.getOppositeComponent() == dropDownButton )
            return;

        isSelected = false;

        timeButton.setIcon( icon_ddUnselectedTitle );
        setDropDownButtonIconUnfocused();
    }//GEN-LAST:event_timeButtonFocusLost

    private void dropDownButtonFocusLost (java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dropDownButtonFocusLost
        if ( evt.getOppositeComponent() == timeButton )
            return;

        isSelected = false;
        
        timeButton.setIcon( icon_ddUnselectedTitle );
        setDropDownButtonIconUnfocused();
    }//GEN-LAST:event_dropDownButtonFocusLost

    private void dropDownButtonMouseClicked (java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dropDownButtonMouseClicked
        timeButton.setIcon( icon_ddSelectedTitle );

        if ( dropDownState == DROP_DOWN_STATE.RIGHT ){
            songList.setVisible(true);
            songList.setSize(100, 100);
            dropDownState = DROP_DOWN_STATE.DOWN;
        }
        else{
            songList.setVisible(false);
            dropDownState = DROP_DOWN_STATE.RIGHT;
        }

        setDropDownButtonIconFocused();
    }//GEN-LAST:event_dropDownButtonMouseClicked

    private void setDropDownButtonIconFocused() {
        if ( dropDownState == DROP_DOWN_STATE.RIGHT ) {
            dropDownButton.setIcon( icon_ddSelectedRight );
        }
        else {
            dropDownButton.setIcon( icon_ddSelectedDown );
        }
    }

    private void setDropDownButtonIconUnfocused() {
        if ( dropDownState == DROP_DOWN_STATE.RIGHT ) {
            dropDownButton.setIcon( icon_ddUnselectedRight );
        }
        else {
            dropDownButton.setIcon( icon_ddUnselectedDown );
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane buttonLayeredPanel;
    private javax.swing.JToggleButton dropDownButton;
    private javax.swing.JList songList;
    private javax.swing.JButton timeButton;
    // End of variables declaration//GEN-END:variables

    public String[] getSelectedSongs()  {
        int[] selectedIndices = songList.getSelectedIndices();
        String[] selectedSongs = new String[0];
        String[] temp;

        for (int i = 0; i < selectedIndices.length; i++){
            temp = new String[selectedSongs.length + 1];
            for(int j=0; j < selectedSongs.length; j++){
                temp[j] = selectedSongs[j];
            }
            temp[selectedSongs.length + 1] = songs[selectedIndices[i]];
            selectedSongs = temp;
        }

        return selectedSongs;
    }


}
