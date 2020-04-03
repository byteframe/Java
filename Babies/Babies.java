import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Babies extends JFrame {
    public BabiesPanel babyGamePanel = new BabiesPanel();

    public static void main(String[] args) {
        Babies theFrame = new Babies();
        theFrame.setLocation(24,50);
        theFrame.setSize(1000,600);
        theFrame.setTitle("Babies");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setResizable(false);
        theFrame.setVisible(true);
    }
    public Babies() {
        getContentPane().add(babyGamePanel);
        babyGamePanel.setFocusable(true);
    }
}

class BabiesPanel extends JPanel implements ActionListener, KeyListener {
    private int killCount, saveCount, thrown, sendSeconds, forSecond, numSeconds, babySpeed;
    private boolean stillPlaying, deadAt1, deadAt2, deadAt3;
    private int spotX[] = {0,205,287,315,325,330,338,383,420,471,506,523,534,530,562,592,635,688,704,723,730,730,785,838,893};
    private int spotY[] = {0,105,172,253,328,415,356,289,228,169,222,284,354,415,306,239,177,150,217,281,350,415,369,352,377};

    private baby[] maternityWard = new baby[30];
    private paddle stretcher;

    private ImageIcon tempImageIconB = new ImageIcon("blood.png");
    private Image bloodImg = tempImageIconB.getImage();
    private ImageIcon tempImageIconF = new ImageIcon("fire.png");
    private Image fireImg = tempImageIconF.getImage();
    private ImageIcon tempImageIconbg = new ImageIcon("bg.png");
    private Image bgImg = tempImageIconbg.getImage();


    private Timer mainTimer = new Timer(50, this);

    public BabiesPanel() {
        killCount = 0;
        saveCount = 0;
        thrown = 0;
        forSecond = 0;
        numSeconds = 2;
        sendSeconds = 3;
        babySpeed = 0;
        stillPlaying = true;
        deadAt1 = false;
        deadAt2 = false;
        deadAt3 = false;

        stretcher = new paddle(460, 100, 20);
        for(int cnt = 0; cnt < maternityWard.length; cnt++)
            maternityWard[cnt] = new baby();

        addKeyListener(this);
        mainTimer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(bgImg, 0, 0, null);

        // death and save tolls
        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.BOLD, 16));
        g.drawString("Death Toll : " + killCount, 250, 540);
        g.drawString("Saved Toll : " + saveCount, 250, 557);

        // paddle
        switch(stretcher.getSpot()) {
            case 1:
                stretcher.drawPaddle(g, 300); break;
            case 2:
                stretcher.drawPaddle(g, 500); break;
            case 3:
                stretcher.drawPaddle(g, 700); break;
        }

        // blood
        if (deadAt1)
            g.drawImage(bloodImg, 325, 495, null);
        if (deadAt2)
            g.drawImage(bloodImg, 525, 495, null);
        if (deadAt3)
            g.drawImage(bloodImg, 725, 495, null);

        // babies or game over
        if (stillPlaying)
            for(int cnt = 0; cnt < thrown; cnt++) {
                if(!maternityWard[cnt].getDead() && !maternityWard[cnt].getSaved())
                    maternityWard[cnt].drawBaby(g, spotX[maternityWard[cnt].getSpot()], spotY[maternityWard[cnt].getSpot()]);
            }
        else {
            g.setColor(Color.red);
            g.setFont(new Font("Serif", Font.BOLD, 32));
            g.drawString("GAME OVER", 400, 275);
        }
    }

    public void actionPerformed(ActionEvent e) {
        forSecond += 50;
        if (forSecond == 1000) {
            forSecond = 0;
            numSeconds++;
            if(numSeconds == sendSeconds) {
                thrown++;
                if(thrown == 10 || thrown == 20)
                    sendSeconds--;
                numSeconds = 0;
            }
            if(thrown == 30)
                stillPlaying = false;
        }

        babySpeed += 50;
        if(babySpeed == 100) {
            for(int cnt = 0; cnt < thrown; cnt++) {
                if(!maternityWard[cnt].getDead() && !maternityWard[cnt].getSaved()) {
                    maternityWard[cnt].incrementPos();

                    if(maternityWard[cnt].getSpot() == 5 && stretcher.getSpot() != 1) {
                        maternityWard[cnt].wasKilled();
                        deadAt1 = true;
                    }
                    else if(maternityWard[cnt].getSpot() == 13 && stretcher.getSpot() != 2) {
                        maternityWard[cnt].wasKilled();
                        deadAt2 = true;
                    }
                    else if(maternityWard[cnt].getSpot() == 21 && stretcher.getSpot() != 3) {
                        maternityWard[cnt].wasKilled();
                        deadAt3 = true;
                    }
                    else if(maternityWard[cnt].getSpot() == 24)
                    {
                        maternityWard[cnt].wasSaved();
                        saveCount++;
                    }
                    if(maternityWard[cnt].getDead())
                        killCount++;

                    if(killCount > 4) {
                        stillPlaying = false;
                        mainTimer.stop();
                    }
                }
            }
            babySpeed = 0;
        }

        repaint();
    }

    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT && stretcher.getSpot() != 1)
            stretcher.setSpot(stretcher.getSpot() - 1);
        else if(ke.getKeyCode() == KeyEvent.VK_RIGHT && stretcher.getSpot() != 3)
            stretcher.setSpot(stretcher.getSpot() + 1);
    }
    public void keyReleased(KeyEvent ke) {}
    public void keyTyped(KeyEvent ke)    {}
}
