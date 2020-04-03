// probably usable only for BabiesGame

import javax.swing.*;
import java.awt.*;

public class baby {
    private int spot;

    private boolean dead;
    private boolean saved;

    private ImageIcon tempImageIcon = new ImageIcon("baby.png");
    private Image babyImg = tempImageIcon.getImage();

    public baby() {
        spot = 0;
        dead = false;
        saved = false;
    }

    public int getSpot() { return spot; }
    public boolean getDead() { return dead; }
    public boolean getSaved() { return saved; }

    public void wasKilled() { dead = true; }
    public void wasSaved() { saved = true; }

    public void incrementPos() {
        spot++;
    }

    public void drawBaby(Graphics g, int in_x, int in_y) {
        if(!saved)
            g.drawImage(babyImg, in_x, in_y, null);
    }
}

