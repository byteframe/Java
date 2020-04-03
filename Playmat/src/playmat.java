// playmat.java
//
// This could be much better in the future.
//

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import client.*;
import client.ui.*;

public class playmat extends JApplet {

    private PlaymatClient client;

    public void init() {
        System.out.println(
          "playmat starting (" + Calendar.getInstance().getTime() + ")");

        try {
            // all swing code should execute on the event dispath thread
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    int port;
                    if (getParameter("editor") !=  null) {
                        client = new EditorClient();
                        port = 4417;
                        add(new EditorContainer((EditorClient)client));
                    } else {
                        client = new GameClient();
                        port = 4416;
                        add(new GameContainer((GameClient)client));
                    }
                    if (getParameter("user") != null) {
                        client.setUsername(getParameter("user"));
                    }
                    if (getParameter("pass") != null) {
                        client.setPassword(getParameter("pass").toCharArray());
                        // todo: erase password, web page will need ssl anyways
                    }
                    if (getParameter("port") != null) {
                        port = Integer.parseInt(getParameter("port"));
                    }
                    String host = getCodeBase().getHost();
                    if (getParameter("host") != null) {
                        host = getParameter("host");
                    }
                    try {
                        client.connect(host, port);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (client.isConnected()) {
            client.disconnect();
        }
    }

    public static void main(String[] args) {
        System.out.println(
          "playmat starting (" + Calendar.getInstance().getTime() + ")");
        final List<String> list = Arrays.asList(args);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final PlaymatClient client;
                int port;
                MinimumFrame frame = new MinimumFrame();
                if (list.indexOf("-editor") > -1) {
                    client = new EditorClient();
                    port = 4417;
                    frame.getContentPane().add(
                      new EditorContainer((EditorClient)client));
                    frame.setTitle("Playmat Editor");
                } else {
                    client = new GameClient();
                    port = 4416;
                    frame.getContentPane().add(
                     new GameContainer((GameClient)client));
                    frame.setTitle("Playmat Game");
                }
                if (list.indexOf("-user") > -1
                  && list.get(list.indexOf("-user") + 1) != null) {
                    client.setUsername(list.get(list.indexOf("-user") + 1));
                }
                if (list.indexOf("-pass") > -1
                  && list.get(list.indexOf("-pass") + 1) != null) {
                    client.setPassword(
                      list.get(list.indexOf("-pass") + 1).toCharArray());
                    //list.remove(list.get(list.indexOf("-pass") + 1));
                }
                if (list.indexOf("-port") > -1
                  && list.get(list.indexOf("-port") + 1) != null) {
                    port = Integer.parseInt(
                      list.get(list.indexOf("-port") + 1));
                }
                JMenuBar menuBar = new JMenuBar();
                ConnectDialog connectDialog = new ConnectDialog(frame, client);
                ClientMenu clientMenu = new ClientMenu(connectDialog, client);
                clientMenu.addSeparator();
                JMenuItem menuItemExit = new JMenuItem("Exit");
                menuItemExit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (client.isConnected()) {
                            client.disconnect();
                        }
                        System.exit(0);
                    }
                });
                clientMenu.add(menuItemExit);
                menuBar.add(clientMenu);
                frame.setJMenuBar(menuBar);
                frame.setVisible(true);
                if (list.indexOf("-host") > -1
                  && list.get(list.indexOf("-host") + 1) != null) {
                    String host = list.get(list.indexOf("-host") + 1);
                    try {
                        client.connect(host, port);
                    } catch (java.io.IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }
        });
    }
}
