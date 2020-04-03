import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // evidentally neccessary for addWindowsListiner

public class ColorFrame 
{
    public static void main(String[] args)
    {    
        // Creates a non-resizable somewhat large frame
        JFrame six40 = new JFrame("Dancing with Colors");
        six40.setSize(640, 480);
        six40.setResizable(false);
        // evidentally makes it so closing finishes the process
        six40.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        // Text lable with name in it
        JLabel nameLabel = new JLabel("byteframe");
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.blue);
        nameLabel.setPreferredSize(new Dimension(320,240));
        //nameLabel.setHorizontalTextPosition(JLabel.CENTER);
        //nameLabel.setVerticalTextPosition(JLabel.CENTER);

        JLabel blackLabel = new JLabel("Shuold be using a \n panel maybe?");
        blackLabel.setHorizontalTextPosition(JLabel.RIGHT);
        blackLabel.setVerticalTextPosition(JLabel.CENTER);
        blackLabel.setPreferredSize(new Dimension(175,100));
        blackLabel.setOpaque(true);
        blackLabel.setBackground(Color.black);

        // Makes the Frame, doesn't 'pack'
        six40.getContentPane().add(blackLabel);
        six40.getContentPane().add(nameLabel);
        six40.setVisible(true);
    }
}