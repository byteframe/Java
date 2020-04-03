import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorBall extends JFrame
{
    public ColorBallPanel ballArea = new ColorBallPanel();

    public static void main(String[] args)
    {
        ColorBall theFrame = new ColorBall();
        theFrame.setLocation(50,50);
        theFrame.setSize(500,500);
        theFrame.setTitle("Color Ball");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setResizable(false);
        theFrame.setVisible(true);
    }
    public ColorBall()
    {
        getContentPane().add(ballArea);
        ballArea.setFocusable(true);
    }
}

class ColorBallPanel extends JPanel implements KeyListener, MouseListener, ActionListener
{
    private int ballXPos;
    private int ballYPos;
    private int xToGo;
    private int yToGo;
    private Color ballColor;
    private boolean timerRunning;
    private Timer ballTimer = new Timer(50,this);

    public ColorBallPanel()
    {
        ballXPos = 75;
        ballYPos = 125;
        xToGo = 430; // ball is 20 long
        yToGo = 430;
        ballColor = Color.red;
        timerRunning = true;

        addMouseListener(this);
        addKeyListener(this);
        ballTimer.start();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0,500,500);
        g.setColor(Color.white);
        g.drawRect(50,50,400,400);
        g.setColor(ballColor);
        g.fillOval(ballXPos, ballYPos, 20,20);
    }

    public void changeColor() // ugly but fast
    {
        if (ballColor == Color.red)
            ballColor = Color.blue;
        else if (ballColor == Color.blue)
            ballColor = Color.green;
        else if (ballColor == Color.green)
            ballColor = Color.cyan;
        else if (ballColor == Color.cyan)
            ballColor = Color.magenta;
        else if (ballColor == Color.magenta)
            ballColor = Color.white;
        else if (ballColor == Color.white)
            ballColor = Color.yellow;
        else if (ballColor == Color.yellow)
            ballColor = Color.gray;
        else if (ballColor == Color.gray)
            ballColor = Color.red;
    }

    /*************************************************/
    public void actionPerformed(ActionEvent e)
    {
        // x
        if (ballXPos <= xToGo)
        {
            if (ballXPos == xToGo)
                changeColor();
            xToGo = 430;
            ballXPos += 5;
        }
        else if (ballXPos >= xToGo)
        {
            if (ballXPos == xToGo)
                changeColor();
            xToGo = 50;
            ballXPos -= 5;
        }

        // y
        if (ballYPos <= yToGo)
        {
            if (ballYPos == yToGo)
                changeColor();
            yToGo = 430;
            ballYPos += 5;
        }
        else if (ballYPos >= yToGo)
        {
            if (ballYPos == yToGo)
                changeColor();
            yToGo = 50;
            ballYPos -= 5;
        }

        repaint();
    }

    /*************************************************/
    public void keyPressed(KeyEvent ke)
    {
        if (ke.getKeyChar() == 's' || ke.getKeyChar() == 'S')
        {
            if (timerRunning)
            {
                timerRunning = false;
                ballTimer.stop();
                System.out.println("Timer Stopped...");
            }
            else
            {
                timerRunning = true;
                ballTimer.start();
                System.out.println("Timer Restarted...");
            }
        }
    }
    public void keyReleased(KeyEvent ke)             {}
    public void keyTyped(KeyEvent ke)                {}

    /*************************************************/
    public void mouseClicked(MouseEvent me)
    {
        if (me.getX() >= ballXPos && me.getX() < ballXPos + 20
                                 && me.getY() >= ballYPos && me.getY() < ballYPos + 20)
        {
            System.out.println("Clicked on the Ball...");
            changeColor();
            repaint();
        }
    }
    public void mousePressed(MouseEvent me)            {}
    public void mouseReleased(MouseEvent me)         {}
    public void mouseEntered(MouseEvent me)            {}
    public void mouseExited(MouseEvent me)            {}
}