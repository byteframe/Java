// SearchPanel.java
//
//
//

package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import xml.ContactList;

public final class SearchPanel extends JPanel {

    protected JTextField searchField;

    public SearchPanel() {
        // LAYOUT
        GridBagConstraints GBC_l = new GridBagConstraints(); 	    	
        GBC_l.fill = GridBagConstraints.HORIZONTAL;
        GBC_l.insets = new Insets(5,5,5,5); 	
        GridBagConstraints GBC_tf = (GridBagConstraints)GBC_l.clone();
        GBC_tf.weightx = 1.0;
        GBC_tf.gridwidth = GridBagConstraints.REMAINDER;
        setLayout(new GridBagLayout());

        // DEFINE COMPONENTS
        searchField = new JTextField(25);
        
        // ADD COMPONENTS
        add(new JLabel("Search:"), GBC_l);
        add(searchField, GBC_tf);
        
        //LISTENERS
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { ; }
            public void insertUpdate(DocumentEvent e) { changed(); }
            public void removeUpdate(DocumentEvent e) { changed(); }

            public void changed() {
                if (searchField.getText().equals("") == true) {
                    ContactPanel.tableModel.clearTable();
                } else {
                    ContactPanel.tableModel.showContacts(
                        ContactList.search(searchField.getText()));
                }
            }
        });
    }
}
