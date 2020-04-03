// MinimumFrame.java
//
//
//

package client.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MinimumFrame extends JFrame {

    public MinimumFrame() {
        this("");
    }

    public MinimumFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().addContainerListener(new MinimumContainerAdapter());
    }

    class MinimumContainerAdapter extends ContainerAdapter {
        public void componentAdded(ContainerEvent e) {
            setMinimumSize(getContentPane().getMinimumSize());
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);
        }    
    }
}
