// Playing.java
//
// panel holding the current player statisticsc
// 

package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.*;

import game.Player;

public final class Playing extends JPanel {
    private Player[] p;

    public Playing(Player[] players) { p = players;    
	    setPreferredSize(new Dimension(180,250));
    }
    
    public void paintComponent(Graphics g) { super.paintComponent(g);
        for (int cnt = 0; cnt < p.length; cnt++) {
            g.setColor(Color.black);        
            g.fillRoundRect(5,5+55*cnt,170,50,8,8);
            g.setColor(Color.white);
            g.fillRoundRect(10,10+55*cnt,160,40,8,8);
            g.setColor(Color.black);
            g.setFont(new Font("Serif", Font.PLAIN, 12));
            g.drawString(p[cnt].getName(),15,25+55*cnt);
            if (p[cnt].isJailed()) {
                g.setFont(new Font("Serif", Font.ITALIC, 12));
                g.drawString("in jail...",15,45+55*cnt);
            }
            g.setColor(Color.green);
            g.drawString("$" + p[cnt].getFunds(), 130,25+55*cnt);
            if (p[cnt].isEasyTicket()){
                g.setColor(Color.yellow);
                g.fillRect(120,30+55*cnt, 20,15);
            }
            if (p[cnt].isHardTicket()){
                g.setColor(Color.orange);
                g.fillRect(145,30+55*cnt, 20,15);
            }
        }
    }
}
