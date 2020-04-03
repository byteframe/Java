// not entirely resusable at the moment

import javax.swing.*;
import java.awt.*;

public class paddle
{
    private int yAxis;
    private int length;
    private int height;
    private int spot;
    private ImageIcon tempImageIcon = new ImageIcon("stretcher.png");
    private Image paddleImg = tempImageIcon.getImage();

    public paddle(int con_yAxis, int con_length, int con_height) {
        yAxis = con_yAxis;
        length = con_length;
        height = con_height;
        spot = 1;
    }

    public int getSpot() { return spot; }
    public void setSpot(int to_spot) { spot = to_spot; }

    public void drawPaddle(Graphics g, int spotX) {
        g.drawImage(paddleImg, spotX, yAxis, null);
    }
}
