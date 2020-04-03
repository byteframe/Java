// ContactPanel.java
//
//
//

package gui;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import xml.TactsTreeModel;
import xml.TactsTableModel;

public final class ContactPanel extends JSplitPane {

    static JTree tree;
    private JTable table;
    protected static TactsTreeModel treeModel;
    protected static TactsTableModel tableModel;

    public ContactPanel() {
        // CONTAINER
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setDividerLocation(150);
        setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        
        // DEFINE COMPONENTS
        reeModel = new TactsTreeModel(new DefaultMutableTreeNode("Tacts"));
        tree = new JTree(treeModel);
        tree.setRootVisible(false);
        tree.setToggleClickCount(1);

        tableModel = new TactsTableModel();
        table = new JTable(tableModel);

        // ADD COMPONENTS
        setLeftComponent(new JScrollPane(tree));
        setRightComponent(new JScrollPane(table));

        // LISTENERS
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                TreePath treePath = e.getNewLeadSelectionPath();
                Object node = treePath.getLastPathComponent();
                tableModel.showContacts(node);
            }
        });
    }
}
