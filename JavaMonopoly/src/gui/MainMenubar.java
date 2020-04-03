// MainMenubar.java
//
// menu bar for the main frame
//

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import gui.AboutDialog;
import gui.RulesDialog;

public final class MainMenubar extends JMenuBar implements ActionListener {
    private AboutDialog aboutdialog;
    private RulesDialog rulesdialog;
    
    public MainMenubar(MainFrame mainframe) {
        JDialog.setDefaultLookAndFeelDecorated(true);
        rulesdialog = new RulesDialog(mainframe);
        aboutdialog = new AboutDialog(mainframe);                                        
        JMenu gameM = new JMenu("Game");
        JMenuItem newgameMI = new JMenuItem("New Game");
        JMenuItem scoresMI = new JMenuItem("High Scores");
        JMenuItem endgameMI = new JMenuItem("End Game");
        newgameMI.addActionListener(mainframe);
        scoresMI.addActionListener(mainframe);
        endgameMI.addActionListener(mainframe);
        gameM.add(newgameMI);
        gameM.add(scoresMI);
        gameM.add(new JSeparator());
        gameM.add(endgameMI);
        add(gameM);
        JMenu optionsM = new JMenu("Options");
        JMenuItem soundMI = new JMenuItem("Sound");
        soundMI.addActionListener(mainframe);
        optionsM.add(soundMI);
        add(optionsM);
        add(Box.createHorizontalGlue());
        JMenu helpM = new JMenu("Help");
        JMenuItem rulesMI = new JMenuItem("Rules");
        JMenuItem aboutMI = new JMenuItem("About");
        rulesMI.addActionListener(this);
        aboutMI.addActionListener(this);
        helpM.add(rulesMI);
        helpM.add(aboutMI);
        add(helpM);
    }

    public void actionPerformed(ActionEvent e) {
        if ("Rules".equals(e.getActionCommand())) {
            rulesdialog.setLocationRelativeTo(rulesdialog.getOwner());
            rulesdialog.setVisible(true);
        } else if("About".equals(e.getActionCommand())) {
           aboutdialog.setLocationRelativeTo(rulesdialog.getOwner());
           aboutdialog.setVisible(true);
        }
    }
}
