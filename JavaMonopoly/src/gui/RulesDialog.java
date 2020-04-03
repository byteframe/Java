// RulesDialog.java
//
// dialog for displaying the help
//

package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public final class RulesDialog extends JDialog implements ActionListener {
    public RulesDialog(JFrame ownerFrame) { super(ownerFrame, true);
        JButton closeB = new JButton("Close");
        closeB.addActionListener(this);
        getContentPane().add(closeB);
        
        setModal(true);
        setTitle("Rules");
        pack();
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if("Close".equals(e.getActionCommand())) {
	        setVisible(false);
	    }
    }
}
