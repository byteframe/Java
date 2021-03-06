// HighScoresDialog.java
//
// dialog for displaying dialog for sound options
//

package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;import javax.swing.JFrame;

public final class HighScoresDialog extends JDialog implements ActionListener {
    public HighScoresDialog(JFrame ownerFrame) { super(ownerFrame, true);
        JButton closeB = new JButton("Close");
        closeB.addActionListener(this);
        getContentPane().add(closeB);
        
        setModal(true);
        setTitle("High Scores");
        pack();
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if ("Close".equals(e.getActionCommand())) {
            setVisible(false);
        }
    }
}
