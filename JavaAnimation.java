import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaAnimation extends JPanel implements ActionListener
{   
    int colour = 255, colourToGo = 0, 
         count = 1,
          xPos = 15, xPosToGo = 585,
          yPos = 15, yPosToGo = 585;
    
    public static void main(String[] args)
    {
        JFrame theFrame = new JFrame("Java Animation");
        theFrame.getContentPane().add(new JavaAnimation());
        theFrame.setResizable(false);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(600,600);
        theFrame.setLocation(0,0);
        theFrame.setVisible(true);
    }
    
    public JavaAnimation()
    {
        Timer t = new Timer(50,this);
        t.start();
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0,0,600,600);

        g.setColor(new Color(colour,colour,colour));
        
        for (int cnt = 1; cnt <= count; cnt++) 
            g.fillRect(xPos,15,15,15);        
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (colour > colourToGo) 
            colour--;
        else
        {
            colourToGo = 255;
            colour++;
        }
        if (xPos < xPosToGo)
            xPos = 15 * count;
        count++;
        repaint();
    }
}
