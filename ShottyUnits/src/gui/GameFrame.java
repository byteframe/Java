// GameFrame.java
//
//
//

package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import gui.GamePanel;

public class GameFrame extends JFrame implements ActionListener {
    private GamePanel gamepanel;

    public GameFrame() {
        setTitle("Shotty Units");
        setJMenuBar(spawnMenuBar());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setPreferredSize(new Dimension(640,480));
        getContentPane().add(gamepanel = new GamePanel());
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2, 
                    (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);                
        setVisible(true);
    }
	
	public void actionPerformed(ActionEvent ae) {
     if ("New Game".equals(ae.getActionCommand())) {
         pauseMI.setEnabled(true);
         gamepanel.newGame();
     } else if ("End Game".equals(ae.getActionCommand())) {
         pauseMI.setEnabled(false);
         gamepanel.endGame();
	   } else if ("Quit".equals(ae.getActionCommand())) {
	       Runtime.getRuntime().exit(0);
	   } else if ("Pause".equals(ae.getActionCommand())) {
	       gamepanel.flipPause();
	       pauseMI.setText("Unpause");
	   } else if ("Unpause".equals(ae.getActionCommand())) {
	       gamepanel.flipPause();
	       pauseMI.setText("Pause");
	   }
	}
	
	private JMenuItem pauseMI; /* needed for setEnabled toggles, and setText calls */
    private JMenuBar spawnMenuBar() {                                
        JMenuBar gamemenubar = new JMenuBar();
        JMenu gameM = new JMenu("Game");
        JMenuItem newgameMI = new JMenuItem("New Game");
        JMenuItem endgameMI = new JMenuItem("End Game");
        JMenuItem quitMI = new JMenuItem("Quit");
        newgameMI.addActionListener(this);
        endgameMI.addActionListener(this);
        quitMI.addActionListener(this);
        gameM.add(newgameMI);
        gameM.add(endgameMI);
        gameM.add(new JSeparator());
        gameM.add(quitMI);
        gamemenubar.add(gameM);
        pauseMI = new JMenuItem("Pause");
        pauseMI.setEnabled(false);
        pauseMI.addActionListener(this);
        gamemenubar.add(pauseMI);
        return gamemenubar;
    }	
}
