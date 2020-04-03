// Actions.java
//
// shows the available actions for the user depending on the situation
// for instance, roll dice, trading or assets OR use card, pay fine, roll dice (jail...)

package gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.RunningGame;

public final class Actions extends JPanel implements ActionListener {
    private JButton[] actionBs;
    private RunningGame runninggame;
    private JPanel thisisstupid;
    
    public Actions(RunningGame r) {
        runninggame = r;
        String[] buttonNames = {"Roll Dice", "Trading", "Assets", "Use Card", "Pay Fine", "Try for Doubles", "Purchase", "Decline Purchase"};
        actionBs = new JButton[buttonNames.length];
        for(int cnt = 0; cnt < actionBs.length; cnt++) {
           actionBs[cnt] = new JButton(buttonNames[cnt]);
           actionBs[cnt].addActionListener(this);
           actionBs[cnt].setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    
    public void setAvailable(boolean[] toggles) {
        removeAll();
        validate();
        thisisstupid = new JPanel();
        thisisstupid.setLayout(new BoxLayout(thisisstupid, BoxLayout.Y_AXIS));
        add(thisisstupid);
        thisisstupid.add(Box.createRigidArea(new Dimension(0,8)));
        for(int cnt = 0; cnt < toggles.length; cnt++) {
            if (toggles[cnt]) {
                thisisstupid.add(actionBs[cnt]);
                thisisstupid.add(Box.createRigidArea(new Dimension(0,8)));
            }
        }
        validate();
        repaint();
    }
    
    public void lock() {
        for(int cnt = 0; cnt < actionBs.length; cnt++){
            actionBs[cnt].setEnabled(false);
        }
    }
    public void unlock() {
        for(int cnt = 0; cnt < actionBs.length; cnt++){
            actionBs[cnt].setEnabled(true);
        }
    }    

    public void actionPerformed(ActionEvent e) {
        if ("Roll Dice".equals(e.getActionCommand())) {
            lock();
            runninggame.rollDice();
        } else if ("Trading".equals(e.getActionCommand())) {
            ;
        } else if ("Assets".equals(e.getActionCommand())) {
            ;
        } else if ("Use Card".equals(e.getActionCommand())) {
            ;
        } else if ("Pay Fine".equals(e.getActionCommand())) {
            ;
        } else if ("Try for Doubles".equals(e.getActionCommand())) {
            ;
        } else if ("Purchase".equals(e.getActionCommand())) {
            ;
        } else if ("Decline Purchase".equals(e.getActionCommand())) {
            runninggame.nextTurn();
        }
    }
}
