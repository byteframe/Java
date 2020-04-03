import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PongForOne extends JFrame {
    public PongForOnePanel thePanel = new PongForOnePanel();

    public static void main(String[] args) {
        PongForOne theFrame = new PongForOne();
        theFrame.setLocation(100,100);
        theFrame.setSize(500,500);
        theFrame.setTitle("Pong for One");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setResizable(false);
        theFrame.setVisible(true);
    }

    public PongForOne() {
        getContentPane().add(thePanel);
        thePanel.setFocusable(true);
    }
}

class PongForOnePanel extends JPanel implements ActionListener, KeyListener {

    private Timer mainTimer = new Timer(50, this);
    private int paddleYpos, ballX, ballY, xToGo, yToGo, boinks;
    private boolean gameOver;

    public PongForOnePanel() {
        paddleYpos = 225;
        ballX = 100;
        ballY = 100;
        xToGo = 500;
        yToGo = 405;
        boinks = 0;
        gameOver = false;
        addKeyListener(this);
        mainTimer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!gameOver) {
            //ground
            g.setColor(Color.black);
            g.fillRect(0,0,500,500);
            g.setColor(Color.white);
            g.fillRect(25,25,475,400);
            g.setColor(Color.black);
            g.fillRect(45,45,455,360);
            //boinks
            g.setColor(Color.white);
            g.setFont(new Font("Serif", Font.BOLD, 16));
            g.drawString("Boinks : " + boinks, 15, 15);
            //paddle
            g.setColor(Color.white);
            g.fillRect(450,paddleYpos,20,50);
            //ball
            g.setColor(Color.white);
            g.fillOval(ballX,ballY,25,25);
        } else {
            g.setColor(Color.red);
            g.fillRect(0,0,500,500);
        }
    }

    public void actionPerformed(ActionEvent e) {
        // ball movement
        if (ballX <= xToGo)
            xToGo = 500;
        if (ballX >= xToGo)
            xToGo = 45;
        if (ballX < xToGo)
            ballX += 10;
        if (ballX > xToGo)
            ballX -= 10;

        if (ballY <= yToGo)
            yToGo = 380;
        if (ballY >= yToGo)
            yToGo = 45;
        if (ballY < yToGo)
            ballY += 10;
        if (ballY > yToGo)
            ballY -= 10;

        // ball collision with paddle
        if (ballX > 440 && ballX < 465 && ballY > paddleYpos - 10 && ballY < paddleYpos + 60) {
            xToGo = 45;
            boinks++;
        }

        // ball goes into goal you lose
        if (ballX == 500) {
            gameOver = true;
            mainTimer.stop();
        }

        repaint();
    }

    public void keyPressed(KeyEvent ke)  {
        if (ke.getKeyCode() == KeyEvent.VK_UP && paddleYpos > 50) {
            paddleYpos -= 10;
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN && paddleYpos < 350) {
            paddleYpos += 10;
        }
    }
    public void keyReleased(KeyEvent ke) {}
    public void keyTyped(KeyEvent ke)    {}
}