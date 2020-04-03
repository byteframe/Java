package xml;

import javax.swing.event.*;
import javax.swing.table.*;

public class TactsTableModel extends AbstractTableModel { // make extend  DefaultTreeModel

    private String[] columns = { "Name", "E-Mail", "Phone", "Address" };    
    private TactsElement grocNode;
 	
    public int getColumnCount() { return columns.length; }
    public String getColumnName(int c) { return columns[c]; } 
    
    public int getRowCount() { 
        if (grocNode != null) {
            if (grocNode.childCount() == 0) {
                if (grocNode.getNodeName().equals("group")) {
                    return 0;
                } else {
                    return 1;
                }
            }
            return grocNode.childCount();
        }
        return 0;
    }
     
    public Object getValueAt(int r, int c) {
        if (grocNode != null) {
            if (grocNode.childCount() == 0) {
                if (grocNode.getNodeName().equals("group")) {
                    return null;
                }
                return grocNode.getAttributeValue(c);
            } else {
                return grocNode.child(r).getAttributeValue(c);
            }
        }
        return null;
    }
     
    public void showContacts(TactsElement node) {
        if  (node.childCount() > 0 || node.getNodeName().equals("contact")) {
            grocNode = node;
            fireTableDataChanged();
        } else { 
            clearTable();
        }
    }

    public void showContacts(Object node) {
        showContacts((TactsElement)node);
    }

    public void clearTable() {
        grocNode = null;
        fireTableDataChanged();
    }
}
