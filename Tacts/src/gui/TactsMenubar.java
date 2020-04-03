// Menubar.java
//
//
//

package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public final class TactsMenubar extends JMenuBar {

    private JMenu fileMenu, helpMenu;
    private JMenuItem addTact, addGroup, exit, manual, about;
        
    public TactsMenubar() { 	
        // DEFINE COMPONENTS                                   
        fileMenu = new JMenu("Contacts");
        addTact = new JMenuItem("Add Contact");
        addGroup = new JMenuItem("Add Group");
        exit = new JMenuItem("Exit");
        helpMenu = new JMenu("Help");    
        about = new JMenuItem("About");	    
        fileMenu.add(addTact);
        fileMenu.add(addGroup);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        helpMenu.add(about);
	    
        // ADD COMPONENTS	    
        add(fileMenu);
        add(Box.createHorizontalGlue());
        add(helpMenu);
        
        // LISTENERS
        addTact.addActionListener(new ActionListener() {
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
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  
            }
        });
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TactsFrame tactsFrame = (TactsFrame)getTopLevelAncestor();     
                tactsFrame.aboutDialog.setLocationRelativeTo(getTopLevelAncestor());
                tactsFrame.aboutDialog.setVisible(true);  
            }
        });      
    }
}
