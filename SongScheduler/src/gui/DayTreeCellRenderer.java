/**
 * DayTreeCellRenderer.java
 *
 * A custom renderer for the jTree in DayScheduleScrollPanel.
 * All this does is remove the icons from the jTree.
 * 
 * @author Kurtis Schmidt
 */

package gui;

// Java packages
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Component;
import javax.swing.JTree;

public class DayTreeCellRenderer extends DefaultTreeCellRenderer {

    public DayTreeCellRenderer() {
        super();
    }

    @Override
    public Component getTreeCellRendererComponent(
                        JTree tree,
                        Object value,
                        boolean sel,
                        boolean expanded,
                        boolean leaf,
                        int row,
                        boolean hasFocus) {

        super.getTreeCellRendererComponent(
                        tree, value, sel,
                        expanded, leaf, row,
                        hasFocus);
        
        setIcon(null);

        return this;
    }

}

