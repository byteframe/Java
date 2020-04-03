// Title.java
//
//
//

package gui;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public final class TitleScreen extends JPanel {
    public void paintComponent(Graphics g) {
        g.drawImage(titleII.getImage(), (getWidth() - titleII.getIconWidth()) / 2,
                                        (getHeight() - titleII.getIconHeight()) / 2, null);
    }
    private ImageIcon titleII = new ImageIcon("../images/title.png");
}
