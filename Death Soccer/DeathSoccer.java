import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeathSoccer extends JPanel implements ActionListener
{
    field soccerField;
    board scoreBoard;
    meter forceMeter;

    ball soccerBall;
    defender[] soccerDefense = new defender[3];
    goalie soccerGoalie;

    public static void main(String[] args) // why static???
    {
        JFrame theFrame = new JFrame("Death Soccer");
        theFrame.getContentPane().add(new DeathSoccer());
        theFrame.setResizable(false);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(700,700);
        theFrame.setLocation(0,0);
        theFrame.setVisible(true);
    }

    public DeathSoccer()
    {
        Timer t = new Timer(50,this);
        t.start();

        soccerField = new field();
        scoreBoard = new board();
        forceMeter = new meter();

        soccerBall = new ball();
        for(int cnt = 0; cnt < soccerDefense.length; cnt++)
            soccerDefense[cnt] = new defender((cnt*50),200);
        soccerGoalie = new goalie();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.gray);
        g.fillRect(0,0,700,700); // master background

        soccerField.drawField(g);
        scoreBoard.drawBoard(g);
        forceMeter.drawMeter(g);

        soccerBall.drawBall(g);
        for(int cnt = 0; cnt < soccerDefense.length; cnt++)
            soccerDefense[cnt].drawDefender(g);
        soccerGoalie.drawGoalie(g);
    }

    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }
}
