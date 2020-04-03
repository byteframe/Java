// GameFrame.java
//
//
//

package gui;

import javax.swing.JFrame;

import gui.GameScreen;

public class GameFrame extends JFrame implements KeyListener {
    private GameScreen gamescreen;

    public GameFrame() {
        getContentPane().add(gamescreen = new GameScreen());

        setTitle("Shoot the Ships");
        getContentPane().setPrefferedSize(640,480);
        setResizable(false);
        setVisible(true);
        setFocusable
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}