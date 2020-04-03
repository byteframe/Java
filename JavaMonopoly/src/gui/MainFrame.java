// MainFrame.java
//
// initializes GUI, and controls code requests from the everything (change this)
// seperated from 'main' for organizational purposes

package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory; import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import game.RunningGame;

public final class MainFrame extends JFrame implements ActionListener {
    private MainMenubar mainmenubar;
    private Actions actions;
    private Console console;
    private GameBoard gameboard;
    private Magnifier magnifier;
    private Playing playing;
    private NewGameDialog newgamedialog;
    private SoundOptionsDialog soundoptionsdialog;
    private HighScoresDialog highscoresdialog;
    private RunningGame runninggame;

    public MainFrame(File[] in_profileFiles) {
        console = new Console();
        JDialog.setDefaultLookAndFeelDecorated(true);
        newgamedialog = new NewGameDialog(this, in_profileFiles);
        highscoresdialog = new HighScoresDialog(this);
        soundoptionsdialog = new SoundOptionsDialog(this);	                                           
        setTitle("Java Monopoly!");
        getContentPane().setPreferredSize(new Dimension(640,643));
        spawnTitle();
        setJMenuBar(mainmenubar = new MainMenubar(this));
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2, 
                  (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);    
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
	
    public void spawnTitle() {
        getContentPane().removeAll();
        getContentPane().add(new TitleScreen());
        getContentPane().validate();
    }
	
    public void spawnGui() {
        getContentPane().removeAll();  
        JPanel leftP = new JPanel(new BorderLayout());
        JPanel rightP = new JPanel(new BorderLayout());
        rightP.add(playing = new Playing(runninggame.getPlayers()), BorderLayout.PAGE_START);
        rightP.add(actions = new Actions(runninggame));
        rightP.add(magnifier = new Magnifier(runninggame), BorderLayout.PAGE_END);      
        leftP.add(gameboard = new GameBoard(magnifier, newgamedialog.getProfile(), runninggame, this), BorderLayout.PAGE_START);
        leftP.add(console);
        getContentPane().add(leftP, BorderLayout.LINE_START);
        getContentPane().add(rightP);
        getContentPane().validate();
    }	
	
    public void actionPerformed(ActionEvent e) {
        if("New Game".equals(e.getActionCommand())) {
            newgamedialog.setLocationRelativeTo(this);
            newgamedialog.setVisible(true);
        } else if("End Game".equals(e.getActionCommand())) {
            spawnTitle();
        } else if("High Scores".equals(e.getActionCommand())) {
            highscoresdialog.setLocationRelativeTo(this);
            highscoresdialog.setVisible(true);
        } else if("Sound".equals(e.getActionCommand())) {
            soundoptionsdialog.setLocationRelativeTo(this);
            soundoptionsdialog.setVisible(true);
        } else if("Start".equals(e.getActionCommand())) {
            runninggame = new RunningGame(newgamedialog, this);
            spawnGui();  
            runninggame.playGame();      
        }
    }
    
    public GameBoard getGameBoard() { return gameboard; }
    public Console getConsole() { return console; }
    public Playing getPlaying() { return playing; }
    public Actions getActions() { return actions; }
    public Magnifier getMagnifier() { return magnifier; }
    public NewGameDialog getNewGameDialog() { return newgamedialog; }
    public HighScoresDialog getHighScoresDialog() { return highscoresdialog; }
    public SoundOptionsDialog getSoundOptionsDialog() { return soundoptionsdialog; }
    public RunningGame getRunningGame() { return runninggame; }
}
