// PlayerTableModel.java
//
//
//

package client.ui;

import java.util.*;
import javax.swing.event.*;
import javax.swing.table.*;
import common.Player;

public class PlayersTableModel extends AbstractTableModel {

    private String[] columns = { "Players" };
    private Vector<Player> players;

    public PlayersTableModel() {
        players = new Vector<Player>();
    }

    public int getColumnCount() { return columns.length; }
    public String getColumnName(int c) { return columns[c]; }
    public int getRowCount() { return players.size(); }

    public Object getValueAt(int r, int c) {
        if (c == 0) {
            String p = players.get(r).getUsername();
            if (players.get(r).getState() == 3) {
                p = "* " + p;
            }
            return p;
        }
        return null;
    }

    public void clear() {
        players.clear();
        fireTableDataChanged();
    }

    public void setData(Vector<Player> v) {
        players = v;
        fireTableDataChanged();
    }
}
    
