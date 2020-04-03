// GamePanel.java
//
// handles the drawing of the entire active game screen in paintComponent
// listens for input from keyboard for player actions with KeyListener

package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.Enemy;
import game.Player;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private int playerScore, gameStatus;
    private Player human;   
    private Enemy[] enemies;
    private Timer actionTimer;
    
    public GamePanel() {
        actionTimer = new Timer(50, this);  
        makePlayer();
        makeEnemies(20);
        setBackground(Color.black);  
        setFocusable(true);
        addKeyListener(this);
    }

    private void makePlayer() {
        human = new Player();
    }
    private void makeEnemies(int amount) {
        enemies = new Enemy[amount];
        for (int arr = amount - 1; arr > 0; arr--) {
            enemies[arr] = new Enemy(/* FIX: Include constructor args*/);
        }
    }
    
    public void newGame() {
        gameStatus = 1;
        playerScore = 0;
        makePlayer();
        makeEnemies(20);
        actionTimer.start();
    }   
    public void flipPause() {
        if (gameStatus == 1) {
            gameStatus++;
        } else {
            gameStatus = 1;
        }
    }
    public void endGame() {
        gameStatus = 0;
    }
    
    public void actionPerformed(ActionEvent ae) {
        // update game information
        repaint();
    }
    
    public void keyPressed(KeyEvent ke) {
        if (gameStatus == 1) {
            switch(ke.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    System.out.println("LEFT Pressed"); break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("RIGHT Pressed"); break;  
                case KeyEvent.VK_SPACE:
                    gameStatus = 3; break;
                default: break;
            }
        } 
    }
    public void keyReleased(KeyEvent ke) {}
    public void keyTyped(KeyEvent ke) {}
    
    private ImageIcon titleII = new ImageIcon("../images/title.png");
    private Image titleI = titleII.getImage();
    public void paintComponent(Graphics g) { super.paintComponent(g);
        if (gameStatus == 0) {
            g.drawImage(titleI, 0, 0, null);
        } else {
            g.setColor(Color.white); /* bottom line, score, lifes */
            g.fillRect(0,440,640,4); 
            g.setFont(new Font("Serif", Font.BOLD, 16));
            g.drawString("Score : " + playerScore, 4, 460);
            for (int cnt = 3 /* FIX : human.getHitPoints() */; cnt > 0; cnt--) {
                g.fillRect(520 + 30*cnt,455,20,15);
                g.fillRect(525 + 30*cnt,450,10,5);
            }
            g.setColor(Color.yellow); /* player */
            //g.fillRect();
            //g.fillRect();
            g.setColor(Color.red); /* enemies */
            for (int cnt = enemies.length - 1; cnt > 0; cnt--) { 
                //g.fillOval();
                //g.fillOval();
                //g.fillOval();
            }
        }
        if (gameStatus == 2) {
            g.setFont(new Font("Times New Roman", Font.BOLD, 32));
            FontMetrics fm = g.getFontMetrics();
            g.setColor(Color.lightGray);
            g.drawString("PAUSE", getWidth() / 2 - fm.stringWidth("PAUSE") / 2, getWidth() / 2 + fm.getAscent() / 2);
        } else if (gameStatus == 3) {
            g.setFont(new Font("Arial", Font.BOLD, 32));
            FontMetrics fm = g.getFontMetrics();
            g.setColor(Color.red);
            g.drawString("GAME OVER", getWidth() / 2 - fm.stringWidth("GAME OVER") / 2, getWidth() / 2 + fm.getAscent() / 2);
        }
    }
    
    public int getGameStatus() { return gameStatus; }
}
