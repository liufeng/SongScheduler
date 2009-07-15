/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SchedulePanel.java
 *
 * Created on Jul 13, 2009, 10:36:11 AM
 */

package gui;

import model.*;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.print.PrinterException;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author kurtisschmidt
 */
public class SchedulePanel extends javax.swing.JPanel {

    /**
     * Constructor
     */
    public SchedulePanel() {
        initComponents();
    }

    /**
     * openDates
     *
     * Make schedules and load them for the given dates
     *
     * @param dates the dates that the schedules are being made for
     * @return void
     */
    public void openDates( Time[] dates )
    {
        openDates( dates, new SongScheduler(dates[0]) );
    }

    /**
     * openDates
     *
     * Load the given schedules for the given dates
     *
     * @param dates the dates that the schedules are being loaded for
     * @param schduler the scheduler that is used to make the schedules
     * @return void
     */
    public void openDates( Time[] dates, SongScheduler scheduler )
    {
        // Create a new SongScheduler and array for each schedule
        this.songScheduler = scheduler;
        this.schedules = new Schedule[dates.length][24];
        this.tables = new JTable[dates.length][24];
                
        // Remove all the current tabs
        tabPane.removeAll();

        // Create the new tabs
        for( int i = 0; i < dates.length; i++ )
        {
            Time currentTime = dates[i];

            JPanel schedulesPanel = new JPanel();
            schedulesPanel.setLayout( new BoxLayout( schedulesPanel, BoxLayout.Y_AXIS) );
            for( int j = 0; j < 24; j++ )
            {
                DefaultTableModel model = new DefaultTableModel();
                JTable table = new JTable( model );
                table.addFocusListener( new java.awt.event.FocusListener() {
                    public void focusGained(FocusEvent e){ onSelectTable(e); }
                    public void focusLost(FocusEvent e){}
                });
                tables[i][j] = table;

                table.setRowSelectionAllowed(true);
                table.setColumnSelectionAllowed(false);
                table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                table.setBorder( BorderFactory.createLineBorder(Color.black) );
                table.setGridColor(Color.gray);
                
                model.addColumn("Name");
                model.addColumn("Artist");
                model.addColumn("Length");
                
                schedules[i][j] = songScheduler.getSchedule( currentTime );
                Iterator<Song> iter = schedules[i][j].iterator();
                while( iter.hasNext() )
                {
                    Song song = iter.next();
                    Object data[] = {song, song.getPerformer(), Conversions.milliToHHMMSS(song.getLength())};
                    model.addRow(data);
                }
                model.addRow(blank);

                updateScheduleLength( schedules[i][j], table );
                
                schedulesPanel.add(new JLabel(currentTime.toString()));
                schedulesPanel.add(table.getTableHeader());
                schedulesPanel.add(table);
                currentTime = currentTime.getNextHour();
            }

            JScrollPane scrollPane = new JScrollPane(schedulesPanel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(40);
            tabPane.addTab( currentTime.getDateAsString(), null, scrollPane, "A panel of some sort" );
        }

        this.ready = true;
    }

    /**
     * onSelectTable
     *
     * Does something --------------------------------------------------------------------------------
     *
     * @param evt
     * @return void
     */
    public void onSelectTable( FocusEvent evt )
    {
        // Unselect from every other schedule
        int tab = tabPane.getSelectedIndex();
        for(int i = 0; i < 24; i++)
        {
            if( tables[tab][i].isFocusOwner() )
            {
                selectedTable = tables[tab][i];
                selectedSchedule = schedules[tab][i];
            }
            else
            {
                tables[tab][i].getSelectionModel().clearSelection();
            }
        }
    }

    /**
     * printSchedule
     *
     * Print the schedules from this date
     *
     * @return void
     */
    public void printSchedule()
    {
        if( !ready )
            return;

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setGridColor(Color.gray);
        int i = tabPane.getSelectedIndex();
        model.addColumn("Name");
        model.addColumn("Artist");

        for(int j = 0; j < 24; j++)
        {
            Object time[] = { j + ":00:00",""};
            model.addRow( time );
            TableModel model2 = tables[i][j].getModel();
            for(int k = 0; k < model2.getRowCount(); k++)
            {
                Object data[] = {model2.getValueAt(k, 0), model2.getValueAt(k,1), model2.getValueAt(k,2)};
                model.addRow(data);
            }
        }
        try
        {
            table.print();
        }
        catch( PrinterException exp ){ System.out.println("Error Printing\n");}
    }

    /**
     * deleteSelected
     *
     * Remove the selected song from the schedule
     *
     * @return void
     */
    public void deleteSelected()
    {
        if( !ready )
            return;
        
        int[] selectedRows = selectedTable.getSelectedRows();
        DefaultTableModel model = (DefaultTableModel)selectedTable.getModel();
        // Move backward so removing doesn't change position of elements
        for( int k = selectedRows.length-1; k >= 0; k-- )
        {
            if( selectedRows[k] < selectedTable.getRowCount()-1 ) // Ignore the blank
            {
                selectedSchedule.remove( (Song)model.getValueAt(selectedRows[k], 0));
                model.removeRow(selectedRows[k]);
            }
        }
        setTableBackground( selectedSchedule, selectedTable );
        updateScheduleLength( selectedSchedule, selectedTable );
    }

    /**
     * setTableBackground
     *
     * Sets the background for the table to show if that schedule is broken in some way
     *
     * @param schedule the schedule displayed in the table
     * @param table the table displaying the schedule
     * @return void
     */
    private void setTableBackground( Schedule schedule, JTable table )
    {
        if( !schedule.isEmpty() && schedule.underMin() )
            table.setBackground(Color.ORANGE);
        else if( schedule.overMax() )
            table.setBackground(Color.RED);
        else
            table.setBackground(Color.WHITE);
    }

    /**
     * addSong
     *
     * Add a song tot the selected schedule
     *
     * @param song the song to be scheduled
     * @return void
     */
    public void addSong( Song song )
    {
        if( !ready )
            return;
        selectedSchedule.add(song);
        Object[] data = {song, song.getPerformer(), Conversions.milliToHHMMSS(song.getLength())};
        DefaultTableModel model = (DefaultTableModel)selectedTable.getModel();
        model.removeRow( selectedTable.getRowCount()-1 );
        model.addRow(data);
        model.addRow(blank);

        setTableBackground( selectedSchedule, selectedTable );
        updateScheduleLength( selectedSchedule, selectedTable );
    }

    /**
     * updateScheduleLength
     *
     * Update the displayed legth of the schedule
     *
     * @param schedule the schedule displayed in the table
     * @param table the table displaying the schedule
     * @return void
     */
    private void updateScheduleLength( Schedule schedule, JTable table )
    {
        table.getModel().setValueAt("Total: " + Conversions.milliToHHMMSS(schedule.getDuration()), table.getRowCount()-1, 2);
    }

    /**
     * commit
     *
     * Commit all the schedules for the give day
     *
     * @return void
     */
    public void commit()
    {
        if( !ready )
            return;
        
        boolean result = true;
        for( int i = 0; i < schedules.length; i++ )
            for( int j = 0; j < 24; j++ )
            {
                Color background = tables[i][j].getBackground();
                if( background == Color.ORANGE || background == Color.RED )
                    result = false;
            }
        
        if( result )
        {
            songScheduler.commit();
            tabPane.removeAll();
            ready = false;
        }
        else
        {
            JOptionPane.showMessageDialog(this.getParent(), "Error: Some schedules are over/under time.\nPlease fix this then commit again.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * fixAllSchedules
     *
     * Fix all of the schedules for this date
     *
     * @return void
     */
    public void fixAllSchedules()
    {
        if( !ready )
            return;
        
        for( int i = 0; i < schedules.length; i++ )
        {
            for( int j = 0; j < 24; j++ )
            {
                Time currentTime = schedules[i][j].getTime();
                
                songScheduler.autoCorrect(currentTime);
                setTableBackground( schedules[i][j], tables[i][j] );

                DefaultTableModel model = (DefaultTableModel)tables[i][j].getModel();
                int rows = tables[i][j].getRowCount();
                for( int k = 0; k < rows; k++ )
                {
                    model.removeRow(0);
                }
                Iterator<Song> iter = schedules[i][j].iterator();
                while( iter.hasNext() )
                {
                    Song song = iter.next();
                    Object data[] = {song, song.getPerformer(), Conversions.milliToHHMMSS(song.getLength())};
                    model.addRow(data);
                }
                model.addRow(blank);
                updateScheduleLength( schedules[i][j], tables[i][j] );
            }
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

        tabPane = new javax.swing.JTabbedPane();

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(tabPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, tabPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tabPane;
    // End of variables declaration//GEN-END:variables

    private SongScheduler songScheduler;
    private Schedule schedules[][];
    private JTable tables[][];
    private JTable selectedTable;
    private Schedule selectedSchedule;
    private Object[] blank = {};
    private boolean ready;
}
