import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HamSandwich extends JPanel implements MouseListener
{
    int clicks = 0;

    public static void main(String[] args)
    {
        JFrame theFrame = new JFrame("HamSandwich");
        theFrame.setResizable(false);
        theFrame.getContentPane().add(new HamSandwich());
        theFrame.setSize(640,480);
        theFrame.setLocation(0,0);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0,0,640,480);

        g.setColor(Color.white);
        for (int cnt = 0; cnt < clicks; cnt++)
        {
            g.fillRect(50*cnt,50,25,25);
        }
    }

    public void mouseClicked(MouseEvent e)
    {
        clicks++;
        repaint();
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
