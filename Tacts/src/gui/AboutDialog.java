// AboutDialog.java
//
//
//

package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public final class AboutDialog extends JDialog implements ActionListener {
	
    private JLabel aboutLogo, aboutText;
    private JButton close;
	
    public AboutDialog() {
        // CONTAINER
        JPanel content = new JPanel();
        content.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        content.setPreferredSize(new Dimension(300,300)); 
        setContentPane(content);    
        setModal(true);
        setResizable(false);
        setTitle("About");
        
        // LAYOUT
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        // DEFINE COMPONENTS
        aboutLogo = new JLabel(new ImageIcon("../img/aboutLogo.png"));
        aboutLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutText = new JLabel("Tacts - An Address Book Program");
        aboutText.setAlignmentX(Component.CENTER_ALIGNMENT);
        close = new JButton("Close");
        close.setAlignmentX(Component.CENTER_ALIGNMENT);
        close.addActionListener(this);
        
        // ADD COMPONENTS
        add(aboutLogo);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(aboutText);
        add(Box.createVerticalGlue());
        add(close);
        pack();
    }

    public void actionPerformed(ActionEvent e) {
        if("Close".equals(e.getActionCommand())) {
            setVisible(false);
        }
    }
}
