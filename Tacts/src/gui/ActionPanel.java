// ActionPanel.java
//
//
//

package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public final class ActionPanel extends JPanel {

    private JButton addContact, addGroup;

    public ActionPanel() {
        // CONTAINER
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // DEFINE COMPONENTS
        addContact = new JButton(new ImageIcon("../img/add_contact.png"));
        addContact.setPreferredSize(new Dimension(34, 34));
        addGroup = new JButton(new ImageIcon("../img/add_group.png"));
        addGroup.setPreferredSize(new Dimension(34, 34));

        // ADD COMPONENTS
        add(addContact);
        add(addGroup);
        
        // LISTENERS
        addContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    		    TactsFrame tactsFrame = (TactsFrame)getTopLevelAncestor(); 
    		    tactsFrame.addTactDialog.setLocationRelativeTo(getTopLevelAncestor());
    		    tactsFrame.addTactDialog.setVisible(true);
            }
        });
        addGroup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    		    TactsFrame tactsFrame = (TactsFrame)getTopLevelAncestor(); 
    		    tactsFrame.addGroupDialog.setLocationRelativeTo(getTopLevelAncestor());
    		    tactsFrame.addGroupDialog.setVisible(true);
            }
        });  
    }
}
