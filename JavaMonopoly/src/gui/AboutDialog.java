// AboutDialog.java
//
// dialog for displaying the about information
//

package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;import javax.swing.JDialog;
import javax.swing.JFrame;

public final class AboutDialog extends JDialog implements ActionListener {
    public AboutDialog(JFrame ownerFrame) { super(ownerFrame, true);
        JButton closeB = new JButton("Close");
        closeB.addActionListener(this);
        getContentPane().add(closeB);
        
        setModal(true);
        setTitle("About");
        pack();
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if ("Close".equals(e.getActionCommand())) {
	        setVisible(false);
        }
    }
}
