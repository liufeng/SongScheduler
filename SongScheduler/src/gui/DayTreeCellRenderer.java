/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

// Java packages
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Component;
import javax.swing.JTree;

/**
 *
 * @author kurtisschmidt
 */
public class DayTreeCellRenderer extends DefaultTreeCellRenderer {

    public DayTreeCellRenderer() {
        super();
    }

    @Override public Component getTreeCellRendererComponent(
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

