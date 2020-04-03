import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorBox extends JPanel implements MouseListener
{
    private Color changingColor = new Color(255,255,255);
    public static void main(String[] args)
    {
        JFrame theFrame = new JFrame("ColorBox");
        theFrame.getContentPane().add(new ColorBox());
        theFrame.setSize(640,480);
        theFrame.setLocation(100,100);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setResizable(false);
        theFrame.setVisible(true);
    }
    public ColorBox()
    {
        addMouseListener(this);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.gray);
        g.fillRect(0,0,640,480);

        g.setColor(Color.red);
        g.fillRect(50,50,100,50);
        g.setColor(Color.green);
        g.fillRect(50,150,100,50);
        g.setColor(Color.blue);
        g.fillRect(50,250,100,50);

        g.setColor(changingColor);
        g.fillRect(300,50,150,250);
    }
    public void mousePressed(MouseEvent me){};
    public void mouseReleased(MouseEvent me)
    {
        if (me.getX() > 49 && me.getX() < 151 && me.getY() > 49 && me.getY() < 101 )
            changingColor = Color.red;
        else if (me.getX() > 49 && me.getX() < 151 && me.getY() > 149 && me.getY() < 199 )
            changingColor = Color.green;
        else if (me.getX() > 49 && me.getX() < 151 && me.getY() > 249 && me.getY() < 299 )
            changingColor = Color.blue;

        repaint();
    };
    public void mouseClicked(MouseEvent me){};
    public void mouseEntered(MouseEvent me){};
    public void mouseExited(MouseEvent me){};
}