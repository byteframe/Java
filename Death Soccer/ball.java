import java.awt.*;
import javax.swing.*;

public class ball
{
    private int xPos;
    private int yPos;
    private int toXPos;

    ImageIcon tempImageIcon = new ImageIcon("soccer.gif");
    Image ballImg = tempImageIcon.getImage();

    public ball()
    {
        xPos = 25;
        yPos = 550;
        toXPos = xPos + 350;
    }
    public ball(int in_xPos, int in_yPos)
    {
        xPos = in_xPos;
        yPos = in_yPos;
    }

    public int getX() { return xPos; }
    public int getY() { return yPos; }

    public void drawBall(Graphics g)
    {
        if (xPos < toXPos)
        {
            toXPos = toXPos +
            xPos++;
        }
        else if (xPos >= toXPos)
        {
            toXPos = toXPos - toXPos + 25;
            xPos--;
        }

        g.drawImage(ballImg, xPos, yPos, null);
    }
}
