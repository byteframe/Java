// AddTactDialog.java
//
//
//

package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

import xml.ContactList;

public final class AddTactDialog extends JDialog {

    private JTextField nField, eField, pField, aField, gField;
    private JButton close, addTact;
	
    public AddTactDialog() {
        // CONTAINER   	
        setModal(true);
        setTitle("Add Contact");
        setResizable(false);

        //LAYOUT
        GridBagConstraints labelGBC = new GridBagConstraints(); 	    	
        labelGBC.fill = GridBagConstraints.HORIZONTAL;
        labelGBC.insets = new Insets(5,5,5,5); 	
        GridBagConstraints fieldGBC = (GridBagConstraints)labelGBC.clone();
        fieldGBC.weightx = 1.0;
        fieldGBC.gridwidth = GridBagConstraints.REMAINDER;
        setLayout(new GridBagLayout());

        // DEFINE COMPONENTS
        nField = new JTextField(20);
        eField = new JTextField(20);        
        pField = new JTextField(20);
        aField = new JTextField(20);
        gField = new JTextField(20);        
        close = new JButton("Close");
        addTact = new JButton("Add Contact");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));                 
        buttonPanel.add(close);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(addTact);       
        
        // ADD COMPONENTS
        add(new JLabel("Name:"), labelGBC);
        add(nField, fieldGBC);
        add(new JLabel("E-mail:"), labelGBC);
        add(eField, fieldGBC);
        add(new JLabel("Phone:"), labelGBC);
        add(pField, fieldGBC);        
        add(new JLabel("Address:"), labelGBC);
        add(aField, fieldGBC);
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
        addTact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    	        if (nField.getText().equals("")) {
    	            JOptionPane.showMessageDialog(
    	              null, "Please input a contact name.", "Error", 2);
    	            return;
    	        }
    	        if (gField.getText().equals("")) {
    	            JOptionPane.showMessageDialog(
    	              null, "Please input a group name.", "Error", 2);
    	            return;
    	        } 
    	        ContactList.addContact(nField.getText(), eField.getText(),
    	          pField.getText(), aField.getText(),  gField.getText());	        
                try {
                    //possible hacking
                    //
                    //DefaultMutableTreeNode treeNode = 
                    //ContactPanel.tree.clearSelection();
                    DefaultTreeModel dft = (DefaultTreeModel)ContactPanel.tree.getModel();    
                    //TreeNode arg = (TreeNode)ContactPanel.tree.getSelectionPath().getLastPathComponent();
                    //DefaultMutableTreeNode TreeNode = new DefaultMutableTreeNode(ContactPanel.tree.getSelectionPath().getLastPathComponent().toString());
                    //ContactPanel.tree.setSelectionPath(
                    //    ContactPanel.tree.getSelectionPath().getLastPathComponent());
                    ////
                    dft.reload();
                } catch(NullPointerException npe) {
                    ;
                }
    	        setVisible(false);            
            }
        });
    }
    
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b) {
            nField.setText("");
            eField.setText("");
            pField.setText("");
            aField.setText("");
            gField.setText("");
        }
    }
}
