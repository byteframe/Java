import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BouncingBalls extends JPanel implements ActionListener, MouseListener
{
    public flyingOval[] theOvals = new flyingOval[5];
    private int rgb = 255;

    public static void main(String[] args)
    {
        JFrame theFrame = new JFrame("Bouncing Balls");
        theFrame.getContentPane().add(new BouncingBalls());
        theFrame.setResizable(false);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(600,900);
        theFrame.setLocation(0,0);
        theFrame.setVisible(true);
    }

    public BouncingBalls()
    {
        for (int cnt = 0; cnt < theOvals.length; cnt++)
            theOvals[cnt] = new flyingOval(50*cnt, 30*cnt, 560, 840, 10+(cnt*2));
        Timer t = new Timer(50,this);
        t.start();
        addMouseListener(this);
        Timer t1 = new Timer(50,mouse);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0,600,900);
        for (int cnt = 0; cnt < theOvals.length; cnt++)
            theOvals[cnt].drawFlyingOval(g);
    }
    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }
    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }
    public void mousePressed(MouseEvent m)
    {
        t1.start();
    }
    public void mouseReleased(MouseEvent m)
    {
        t1.stop();
    }
    public void mouseClicked(MouseEvent m){}
    public void mouseEntered(MouseEvent m){}
    public void mouseExited(MouseEvent m){}
}