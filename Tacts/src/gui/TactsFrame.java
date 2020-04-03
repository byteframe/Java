// TactsFrame.java
//
//
//

package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public final class TactsFrame extends JFrame {

    protected AddTactDialog addTactDialog;
    protected AddGroupDialog addGroupDialog;
    protected AboutDialog aboutDialog;
    private ActionPanel actionPanel;
    private ContactPanel contactPanel;
    private SearchPanel searchPanel;

    public TactsFrame() {
        // CONTAINER
        getContentPane().setPreferredSize(new Dimension(640,400));
        setTitle("Tacts beta");
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - 640) / 2,
                  (Toolkit.getDefaultToolkit().getScreenSize().height - 400) / 2);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new TactsMenubar());

        // DEFINE COMPONENTS
        addTactDialog = new AddTactDialog();
        addGroupDialog = new AddGroupDialog();
        aboutDialog = new AboutDialog();
        actionPanel = new ActionPanel();
        contactPanel = new ContactPanel();
        searchPanel = new SearchPanel();

        // ADD COMPONENTS
        add(actionPanel, BorderLayout.PAGE_START);
        add(contactPanel);
        add(searchPanel, BorderLayout.PAGE_END);
        pack();
        setVisible(true);
    }
}
