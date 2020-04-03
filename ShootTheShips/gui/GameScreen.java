// GameScreen.java
//
//
//

package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import gui.Background;
import gui.event.ShotEvent;
import gui.event.ShotListener;
import game.Player;

public class GameScreen extends JPanel implements ShotListener {

    public GameScreen() {
        makePlayers();
        makeEnemies();
    }

    public void paintComponent(Graphics g) { super.paintComponent(g);
        g.setColor(Color.red);
        g.fillRect(0,0,200,200);
    }

    public void shotHappened(ShotEvent shote) {;}

    private makePlayers() {
        ;
    }
    private makeEnemys() {
        ;
    }
}