import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BouncingBall extends JPanel implements ActionListener
{   
    int xPos = 25, yPos = 25, xToGo = 560, yToGo = 840;

    public static void main(String[] args)
    {
        JFrame theFrame = new JFrame("Bouncing Ball");
        theFrame.getContentPane().add(new BouncingBall());
        theFrame.setResizable(false);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(600,900);
        theFrame.setLocation(0,0);
        theFrame.setVisible(true);
    }
    
    public BouncingBall()
    {
        Timer t = new Timer(50,this);
        t.start();
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        g.setColor(Color.black);
        g.fillRect(0,0,600,900);
        
        g.setColor(Color.red);
        g.fillOval(xPos,yPos,15,15);   
    }
    
    public void actionPerformed(ActionEvent e)
    {
        // sketchy at best
        if (xPos <= xToGo)
            xToGo = 560;
        if (xPos >= xToGo)
            xToGo = 25;
            
        if (xPos < xToGo)
        {
            xPos += 5;
        }
        if (xPos > xToGo)
        {
            xPos -= 5;
        }
        
        if (yPos <= yToGo)
            yToGo = 840;
        if (yPos >= yToGo)
            yToGo = 25;        
        if (yPos < yToGo)
        {
            yPos += 5;
        }
        if (yPos > yToGo)
        {
            yPos -= 5;
        }
        
        repaint();
    }
}
