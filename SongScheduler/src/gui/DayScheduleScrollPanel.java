/**
 * DayScheduleScrollPanel.java
 *
 * This panel is used to display one days worth of schedules in a tree format.
 * Each visible top node is an hour long schedule, with leaf nodes pointing
 * to the songs.
 *
 * The constructor takes in a songScheduler object, which contains all the
 * schedules for the time day (as well as other info).  This object is used
 * to generate schedules automatically, as well as correct schedules which have
 * been changed by the user.
 *
 * @author Kurtis Schmidt & Jordan Wiebe
 * Created on May 15, 2009, 2:04:14 AM
 */
package gui;

// Self contained packages
import model.*;

// Java packages
import javax.swing.tree.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Iterator;

/**
 *
 * @author kurtisschmidt
 */
public class DayScheduleScrollPanel extends javax.swing.JPanel {

    private DefaultMutableTreeNode  rootNode;      // This is needed, but is set to invisible
    private DefaultMutableTreeNode  timeNodes[];   // Nodes pointing to each hour schedule
    private DefaultTreeModel        treeModel;     // Pointer to the treeModel, used for adding/deleting nodes
    private SongScheduler           songScheduler; // The songScheduler associated with this panel
    private Schedule                schedules[];   // Pointers to each schedule

    /**
     * Constructor
     *
     * @param songScheduler - A songScheduler containing schedules for all 24 hours in the day
     * @param day - The time that the dayScheduleScrollPanel begins. This should have hour,min,sec = 0
     */
    public DayScheduleScrollPanel ( SongScheduler songScheduler, Time day ) {
        this.rootNode      = new DefaultMutableTreeNode( "RootNode" );
        this.timeNodes     = new DefaultMutableTreeNode[ 24 ];
        this.schedules     = new Schedule[ 24 ];
        this.songScheduler = songScheduler;

        // Create and add nodes to the root for each schedule
        Time currentTime = day;
        for ( int i = 0; i < 24; i++ ) {
            schedules[i] = songScheduler.getSchedule( currentTime );
            timeNodes[i] = new DefaultMutableTreeNode( schedules[i] );
            rootNode.add( timeNodes[i] );

            // Add all songs to the schedule node
            Iterator<Song> iter = schedules[i].iterator();
            while ( iter.hasNext() ) {
                timeNodes[i].add( new DefaultMutableTreeNode( iter.next() ) );
            }

            currentTime = currentTime.getNextHour();
        }

        initComponents();
        jTree1.setCellRenderer( new DayTreeCellRenderer() );
        treeModel = (DefaultTreeModel) jTree1.getModel();
    }

    /**
     * deleteSelectedSongs
     *
     * Removes all selected songs from the schedule and tree.
     * <br/>
     * If a schedule node is selected, this deletes all songs in that schedule.
     *
     * @return void
     */
    public void deleteSelectedSongs () {
        DefaultMutableTreeNode selected[] = getSelected();

        if ( selected == null ) {
            return;
        }

        for ( int i = 0; i < selected.length; i++ ) {
            DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selected[i].getParent();

            // This is an hour node, delete it from the database.
            if ( parent == rootNode ) {
                Schedule schedule = (Schedule) selected[i].getUserObject();

                schedule.clear();
                selected[i].removeAllChildren();
                treeModel.reload( selected[i] );
            } else {
                Schedule schedule = (Schedule) parent.getUserObject();
                Song song = (Song) selected[i].getUserObject();

                schedule.remove( song );
                treeModel.removeNodeFromParent( selected[i] );
            }
        }
    }

    /**
     * addSong
     *
     * Adds a song to the selected schedule. If there is a problem adding the song
     * the function throws an error.
     * <br/>
     * All songs are added to the end of the selected schedule.
     * <br/>
     * TODO: Add in place song additions.
     *
     * @param song - The song to be added.
     * @throws java.lang.Exception - Throws error if nothing is selected, or if more than one item is selected.
     */
    public void addSong ( Song song ) throws Exception {
        DefaultMutableTreeNode selected[] = getSelected();

        // Check for proper selection, otherwise throw an error22222222
        if ( selected.length > 1 ) {
            throw new Exception( "Error: Cannot add to more than 1 place at a time." );
        } else if ( selected.length < 1 ) {
            throw new Exception( "Error: Please select an hour or position to add the song." );
        }

        DefaultMutableTreeNode selectedNode = selected[0];
        DefaultMutableTreeNode scheduleNode = null;
        Object selectedItem = selectedNode.getUserObject();

        // Add the song to the selected Schedule
        if ( selectedItem instanceof Schedule ) {
            Schedule schedule = (Schedule) selectedItem;
            schedule.add( song );
            scheduleNode = selectedNode;
        } else if ( selectedItem instanceof Song ) {
            Schedule schedule = (Schedule) ( (DefaultMutableTreeNode) selectedNode.getParent() ).getUserObject();
            schedule.add( song );
            scheduleNode = (DefaultMutableTreeNode) selectedNode.getParent();
        }

        // Add the song to the tree
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode( song );
        treeModel.insertNodeInto( childNode, scheduleNode, scheduleNode.getChildCount() );
    }

    /**
     * generateSchedules
     *
     * Automatically generates schedules for each selected schedule.
     *
     * TODO: Load in new tree nodes without reloading the tree.
     * 
     * @throws java.lang.Exception - Thrown if a song is selected instead of a schedule.
     */
    public void generateSchedules () throws Exception {
        DefaultMutableTreeNode selected[] = getSelected();

        /**********************************************************************
         * This section checks for error and ends the function if one is found.
         *********************************************************************/
        if ( selected.length < 1 ) {
            throw new Exception( "Error: Please select an hour or position to add the song." );
        }

        // Check each DefaultMutableTree, if one is a song, return error.
        for ( int i = 0; i < selected.length; i++ ) {
            if ( selected[i].getUserObject() instanceof Song ) {
                throw new Exception( "Error: You must select only hours, not songs before generating." );
            }
        }

        /**********************************************************************
         * This section generates the schedules for each selected hour
         *********************************************************************/
        for ( int i = 0; i < selected.length; i++ ) {
            Schedule schedule = (Schedule) selected[i].getUserObject();
            songScheduler.generateOneHour( schedule.getTime() );

            selected[i].removeAllChildren();
            treeModel.reload( selected[i] );

            Iterator<Song> iter = schedule.iterator();
            while ( iter.hasNext() ) {
                treeModel.insertNodeInto( new DefaultMutableTreeNode( (Song) iter.next() ), selected[i], selected[i].getChildCount() );
            }
        }
    }

    /**
     * getSelected
     *
     * Gets all selected nodes and returns them in an array.
     *
     * @return DefaultMutableTreeNode[] - All selected nodes
     */
    private DefaultMutableTreeNode[] getSelected () {
        javax.swing.tree.TreePath paths[] = jTree1.getSelectionPaths();

        if ( paths == null ) {
            return null;
        }

        DefaultMutableTreeNode selected[] = new DefaultMutableTreeNode[ paths.length ];

        for ( int i = 0; i < selected.length; i++ ) {
            selected[i] = (DefaultMutableTreeNode) paths[i].getLastPathComponent();
        }

        return selected;
    }

    /**
     * checkStatus
     *
     * Checks if all the schedules are of the appropraite durations.
     * Either duration == 0 or 43 < duration < 48 minutes
     *
     * TODO: Highlight schedules that are in conflict.
     * 
     * @return True if all is good, false otherwise
     */
    public boolean checkStatus () {
        boolean result = true;

        for ( int i = 0; i < 24; i++ ) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) rootNode.getChildAt( i );
            Schedule currentSchedule = (Schedule) currentNode.getUserObject();

            if ( !currentSchedule.isEmpty() && ( currentSchedule.underMin() || currentSchedule.overMax() ) ) {
                result = false;
            }
        }

        return result;
    }

    /**
     * autoCorrect
     *
     * Automatically corrects any schedules that are in conflict by adding or
     * removing the appropriate number of songs.
     *
     * @return void
     */
    public void autoCorrect () {
        for ( int i = 0; i < 24; i++ ) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) rootNode.getChildAt( i );
            Schedule currentSchedule = (Schedule) currentNode.getUserObject();

            if ( !currentSchedule.isEmpty() && ( currentSchedule.underMin() || currentSchedule.overMax() ) ) {
                songScheduler.autoCorrect( currentSchedule.getTime() );
            }

            currentNode.removeAllChildren();
            treeModel.reload( currentNode );

            Iterator<Song> iter = currentSchedule.iterator();
            while ( iter.hasNext() ) {
                treeModel.insertNodeInto( new DefaultMutableTreeNode( (Song) iter.next() ), currentNode, currentNode.getChildCount() );
            }
        }
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(rootNode));
        jTree1.setAlignmentX(0.0F);
        jTree1.setAlignmentY(0.0F);
        jTree1.setRootVisible(false);
        jTree1.setShowsRootHandles(true);
        jScrollPane1.setViewportView(jTree1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
