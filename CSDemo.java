import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CSDemo extends JPanel implements ActionListener
{
    private int MAXCOLORS = 510;
    int flag = 0;
    int test =0;
    String txt = new String("no answer");
    public Color[] c = new Color[MAXCOLORS];
    public makeMe[] tag = new makeMe[18];

    public static void main(String[] args)
        {
        JFrame frame = new JFrame("Computer Science Demo");
        frame.getContentPane().add(new CSDemo());
        frame.setSize(1000, 800);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public CSDemo()
    {
        int i;
        int r = 255;
        int g = 0;
        int b = 0;
        Timer t = new Timer(50, this);
        t.start();
        tag[0] = new makeMe("Java", 0, 0, 5, 3, 100, 1, this);
        tag[1] = new makeMe("C++", 0, 300, 2, 4, 200, 2, this);
        tag[2] = new makeMe("Networking", 1000, 1000, 4, 2, 100, 3, this);
        tag[3] = new makeMe("Robotics", 1000, 1200, 7, 2, 400, 1, this);
        tag[4] = new makeMe("Business", 0, 1000, 1, 1, 300, 1, this);
        tag[10] = new makeMe("Information", 50, 1080, 1, 1, 250, 1, this);
        tag[11] = new makeMe("Systems", 100, 1160, 1, 1, 200, 1, this);
        tag[5] = new makeMe("Programming", 1000, 0, 4, 2, 274, 4, this);
        tag[6] = new makeMe("Animation", 0, 0, 4, 8, 100, 15, this);
        tag[7] = new makeMe("Visual Basic", 900, 999, 2, 2, 400, 2, this);
        tag[8] = new makeMe("Intelligence", 180, 180, 2, 3, 0, 10, this);
        tag[9] = new makeMe("Artificial", 100, 100, 2, 3, 0, 10, this);
        tag[12] = new makeMe("XML", 300, 1000, 3, 4, 300, 5, this);
        tag[13] = new makeMe("ASP", 0, 1000, 5, 4, 300, 6, this);
        tag[14] = new makeMe("Perl", 500, 1000, 9, 5, 100, 7, this);
        tag[15] = new makeMe("HTML", 0, 500, 3, 2, 300, 9, this);
        tag[16] = new makeMe("SQL", 200, 200, 7, 7, 100, 19, this);
        tag[17] = new makeMe("CORBA", 400, 400, 7, 7, 300, 19, this);

        c[0]=new Color(r, g, b);
        for (i = 1; i < MAXCOLORS; i++)
        {
            if (r > 127)
            {
                r--;
                g++;
            }
            else if (r > 0)
            {
                r--;
                if (g<255)g++;
                b++;
            }
            else
            {
                if (g > 127)
                {
                    g--;
                    if (b<255)b++;
                }
                else if (g > 0)
                {
                    if (g>0)g--;
                }
                else
                {
                    txt = "max =" + i;
                    r = 255;
                    g = 0;
                    b = 0;
                }
            }
            if (r>=0&&r<=255&&g>=0&&g<=255&&b>=0&&b<=255)c[i] = new Color(r, g, b);
            else c[i] = Color.white;
        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0,1500,1250);
        g.setFont(new Font("times", 3, 92));
        tag[0].paintMe(g);
        tag[1].paintMe(g);
        tag[2].paintMe(g);
        tag[3].paintMe(g);
        tag[4].paintMe(g);
        tag[5].paintMe(g);
        tag[6].paintMe(g);
        tag[7].paintMe(g);
        tag[8].paintMe(g);
        tag[9].paintMe(g);
        tag[10].paintMe(g);
        tag[11].paintMe(g);
        tag[12].paintMe(g);
        tag[13].paintMe(g);
        tag[14].paintMe(g);
        tag[15].paintMe(g);
        tag[16].paintMe(g);
        tag[17].paintMe(g);
    }
    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }
}

