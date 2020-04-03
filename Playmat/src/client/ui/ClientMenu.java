// ClientMenu.java
//
//
//

package client.ui;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import client.*;

public class ClientMenu extends JMenu implements Observer {

    private PlaymatClient client;
    private ConnectDialog connectDialog;
    private JMenuItem menuItemConnect;
    private JMenuItem menuItemDisconnect;

    public ClientMenu(ConnectDialog cd) {
        connectDialog = cd;
        menuItemConnect = new JMenuItem("Connect");
        menuItemDisconnect = new JMenuItem("Disconnect");
        setText("Client");
        add(menuItemConnect);
        add(menuItemDisconnect);
        menuItemConnect.addActionListener(new ConnectListener());
        menuItemDisconnect.addActionListener(new DisconnectListener());
    }
    public ClientMenu(ConnectDialog cd, PlaymatClient c) {
        this(cd);
        setClient(c);
    }

    public void setClient(PlaymatClient c) {
        client = c;
        client.addObserver(this);
        update(client, client.isConnected());      
    }

    class ConnectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            connectDialog.setLocationRelativeTo(
              JOptionPane.getFrameForComponent(ClientMenu.this));
            connectDialog.setVisible(true);
        }
    }

    class DisconnectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            client.disconnect();    
        }
    }

    public void update(Observable obs, Object o) {
        if (o instanceof Boolean) {
            if (((Boolean)o).booleanValue()) {
                menuItemDisconnect.setEnabled(true);           
            } else {
                menuItemDisconnect.setEnabled(false);                        
            }
        }
    }
}
