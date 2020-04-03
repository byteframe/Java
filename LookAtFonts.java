import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class LookAtFonts extends JPanel
{
    public LookAtFonts()
    {
    }

    public static void main(String[] args)
    {
        JFrame theFrame = new JFrame("Look at Fonts");
        theFrame.getContentPane().add(new LookAtFonts());
        theFrame.setResizable(false);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(600,600);
        theFrame.setVisible(true);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0,600,600);

        g.setColor(Color.white);
        g.drawRect(5,5,583,564);

        g.setFont(new Font("Helvetica", Font.BOLD, 12));
        g.drawString("Helvetica, Bold, 12", 25, 50);

        g.setFont(new Font("Times new Roman", Font.PLAIN, 24));
        g.drawString("Times new Roman, Plain, 12", 25, 75);

        g.setFont(new Font("Courier", Font.ITALIC, 12));
        g.drawString("Courier, Italic, 12", 25, 100);

        g.setFont(new Font("Tahoma", Font.BOLD, 12));
        g.drawString("Tahoma, BOLD, 12", 25, 125);

        g.setFont(new Font("Helvetica", Font.BOLD, 24));
        g.drawString("Helvetica, Bold, 24", 25, 150);

        g.setFont(new Font("Times new Roman", Font.PLAIN, 24));
        g.drawString("Times new Roman, Plain, 24", 25, 175);

        g.setFont(new Font("Courier", Font.ITALIC, 24));
        g.drawString("Courier, Italic, 24", 25, 200);

        g.setFont(new Font("Tahoma", Font.BOLD, 24));
        g.drawString("Tahoma, BOLD, 24", 25, 225);
    }
}