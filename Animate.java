import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Animate extends JPanel implements ActionListener
{
    int xPos,yPos = 0; // starts at 0,0 aka upper left
    int xEdge = 615;
    int yEdge = 455;

    public static void main(String[] args)
    {
        JFrame theFrame = new JFrame("Bouncing Thing");
        theFrame.getContentPane().add(new Animate());
        theFrame.setSize(640,480);
        theFrame.setLocation(0,0);
        theFrame.setResizable(false);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
    }

    public Animate()
    {
        Timer t = new Timer(50,this);
        t.start();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // for the frame
        g.setColor(Color.black);
        g.fillRect(0,0,640,480);

        g.setColor(Color.red);
        g.fillRect(xPos,yPos,25,25);
    }

    public void actionPerformed(ActionEvent e)
    {
        // X
        if (xPos <= xEdge) // not till right edge
            xPos++; // continue to the right
        else if (xPos >= xEdge)
        {
            xEdge = 0;
            xPos++;
        }
        if (xPos <= xEdge && xEdge == 0)
        {
            xEdge = 640 - 25;
            xPos++;
        }

        // Y
        if (yPos <= yEdge) // not till right edge
            yPos++; // continue to the right
        else if (yPos >= yEdge)
        {
          yEdge = 0;
          yPos++;
        }
        if (yPos <= yEdge && yEdge == 0)
        {
            yEdge = 480 - 25;
            yPos++;
        }
        repaint();
    }
}