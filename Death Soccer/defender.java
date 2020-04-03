import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class defender implements ActionListener
{
    private int xPos;
    private int yPos;
    private int toXPos = xPos + 50;

    public defender()
    {
        xPos = 100;
        yPos = 100;
    }
    public defender(int in_xPos, int in_yPos)
    {
        Timer defenderTimer = new Timer(50,this);
        defenderTimer.start();

        xPos = in_xPos;
        yPos = in_yPos;
    }

    public int getX() { return xPos; }
    public int getY() { return yPos; }

    public void drawDefender(Graphics g)
    {
        if(xPos < toXPos)
            xPos++;
        else if(xPos >= toXPos)
            xPos--;

        g.setColor(Color.red);
        g.fillRect(xPos, yPos, 25, 60);

        if(xPos >= toXPos)
            toXPos = xPos - 50;
    }

    public void paintComponent(Graphics g)
    {
    }

    public void actionPerformed(ActionEvent e)
    {
        drawDefender(Graphics g);
    }
}
