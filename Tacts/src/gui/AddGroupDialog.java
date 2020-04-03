// AddGroupDialog.java
//
//
//

package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

import xml.ContactList;

public final class AddGroupDialog extends JDialog {
    private JTextField gField;
    private JButton close, addGroup;
	
    public AddGroupDialog() {
        // CONTAINER   	
        setModal(true);
        setTitle("Add Group");
        setResizable(false);

        // LAYOUT
        GridBagConstraints labelGBC = new GridBagConstraints(); 	    	
        labelGBC.fill = GridBagConstraints.HORIZONTAL;
        labelGBC.insets = new Insets(5,5,5,5);
        GridBagConstraints fieldGBC = (GridBagConstraints)labelGBC.clone();
        fieldGBC.weightx = 1.0;
        fieldGBC.gridwidth = GridBagConstraints.REMAINDER;
        GridBagConstraints buttonGBC = (GridBagConstraints)labelGBC.clone();
        setLayout(new GridBagLayout());
        
        // DEFINE COMPONENTS
        gField = new JTextField(20);
        close = new JButton("Close");
        addGroup = new JButton("Add Group");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));                 
        buttonPanel.add(close);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(addGroup);
        
        // ADD COMPONENTS
        add(new JLabel("Group:"), labelGBC);
        add(gField, fieldGBC);
        add(buttonPanel, fieldGBC);
        pack();
        
        // LISTENERS
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        addGroup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gField.getText().equals("")) { 
                    JOptionPane.showMessageDialog(
                      null, "Please input a group name.", "Error", 2);
                } else {
                    ContactList.addGroup(gField.getText());
                    try {
                        DefaultTreeModel dft = (DefaultTreeModel)ContactPanel.tree.getModel();   
                        dft.reload();
                    } catch(NullPointerException npe) {
                        ;
                    }
                }
                setVisible(false); 
            }
        });
    }
    
    public void setVisible(boolean b) {
        super.setVisible(b);
        if(b) {
            gField.setText("");
        }
    }
}
