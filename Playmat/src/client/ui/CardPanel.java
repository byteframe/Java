// CardPanel.java
//
//
//

package client.ui;

import java.awt.*;
import javax.swing.*;

public class CardPanel extends JPanel {

    private Image image;
    private Image imageBack;

    public void paintComponent(Graphics g) {
        if (isEnabled()) {
            if (image != null) {
                g.drawImage(image, 0, 0,
                  getMinimumSize().width, getMinimumSize().height, null);
            } else if (imageBack != null) {
                g.drawImage(imageBack, 0, 0, 
                  getMinimumSize().width, getMinimumSize().height, null);
            }
        }
    }

    public void setImage(Image i) {
        if (image != null) {
            image.flush();
        }
        image = i;
        repaint();
    }

    public void setImageBack(Image i) {
        imageBack = i;
        if (image == null) {
            repaint();
        }
    }
}
