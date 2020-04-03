// TableSideScrollPane.java
//
//
//

package client.ui;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import common.*;

public class TableSideScrollPane extends JScrollPane {

    private final int cardW = 80;
    private final int cardH = 112;
    private final int handH = 25;
    private final int highLight = 3;
    private final int padding = 12;
    private final Color colorLight = new Color(255,255,50);
    private final Color colorTable = new Color(0, 128, 0);
    private final Font font = new Font("SansSerif", Font.BOLD, 12);

    private GameContainer gui;
    private Deck hand; // you
    private Player player; // both

    private int sch = -1; // current index of card in hand from mouse over
    private int sc = -1; // current index of card from mouse over
    private Point mPoint; // mouse over point for static grid
    private int selectionMode = 0; // 0=none, 1=play, 2=move
    private TableSideViewPort viewPort;
    private TableSidePanel panel;

    public TableSideScrollPane(GameContainer g) {
        super(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
          ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        gui = g;
        mPoint = new Point(-1, -1);
        panel = new TableSidePanel();
        panel.setBackground(colorTable);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setMinimumSize(new Dimension(600,600));
        panel.setPreferredSize(new Dimension(600,600));
        viewPort = new TableSideViewPort();
        viewPort.setView(panel);
        setViewport(viewPort);
        setBorder(BorderFactory.createEmptyBorder());
        panel.addMouseListener(new TableSideMouse());
        panel.addMouseMotionListener(new TableSideMouse());
    }

    public Player getPlayer() { return player; }

    public void setHand(Deck d) {
        hand = d;
        viewPort.repaint();
    }
    public void setPlayer(Player p) {
        player = p;
        viewPort.repaint();
    }
    public void setSelectionMode(int s) {
        selectionMode = s;
        if (selectionMode == 0) {
            gui.buttonCancel.setVisible(false);
        }
    }

    class TableSideMouse extends MouseInputAdapter {

        private int dx = -1;
        private int dy = -1;
        private int x_pos;
        private int y_pos;

        public void mouseDragged(MouseEvent e) {
            int newX = viewPort.getViewPosition().x - (e.getX()-x_pos);
            int newY = viewPort.getViewPosition().y - (e.getY()-y_pos);
            int maxX = panel.getWidth()-viewPort.getWidth();
            int maxY = panel.getHeight()-viewPort.getHeight();
            if (newX < 0) {
                newX = 0;
            }
            if (newX > maxX) {
                newX = maxX;
            }
            if (newY < 0) {
                newY = 0;
            }
            if (newY > maxY) {
                newY = maxY;
            }
            viewPort.setViewPosition(new Point(newX, newY));
            viewPort.repaint();
        }
        public void mousePressed(MouseEvent e) {
            x_pos = e.getX();
            y_pos = e.getY();
            getTopLevelAncestor().setCursor(
              Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
        public void mouseReleased(MouseEvent e) {
            getTopLevelAncestor().setCursor(
              Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        public void mouseMoved(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (hand != null && x >= cardW && y >= viewPort.getHeight()-cardH) {
                for (int h = hand.getSize()-1; h > -1; h--) {
                    int xxx = handH;
                    if (h == hand.getCards().indexOf(gui.lsc)) {
                        xxx = cardH;
                    }
                    if (x >= (cardW/2)*h+cardW
                      && x <= (cardW/2)*h+cardW*2
                      && y >= viewPort.getHeight()-xxx
                      && y <= viewPort.getHeight()) {
                        int id = Integer.parseInt(hand.getCard(h).getImageId());
                        Image i = null;
                        if (id < gui.client.images.size()) {
                            i = gui.client.images.get(id);
                        }
                        gui.cardPanel.setImage(i);
                        sch = h;
                        break;
                    } else {
                        sch = -1;
                    }
                }
            } else if (player != null) {
                int p = gui.gameState.indexOfPlayer(player.getUsername());
                dx = x / (cardW+padding);
                dy = y / (cardH+padding);
                if (x >= dx*(cardW+padding)+padding
                  && x <= dx*(cardW+padding)+(cardW+padding)
                  && y >= dy*(cardH+padding)+padding
                  && y <= dy*(cardH+padding)+(cardH+padding)) {
                    mPoint.x = dx;
                    mPoint.y = dy;
                    for (int c = gui.gameState.getCards(p).size()-1; c > -1; c--) {
                        GameCard gc = gui.gameState.getCards(p).get(c);
                        if (mPoint.x == gc.getX() && mPoint.y == gc.getY()) {
                            int id = Integer.parseInt(gc.getImageId());
                            Image i = null;
                            if (id < gui.client.images.size()) {
                                i = gui.client.images.get(id);
                            }               
                            gui.cardPanel.setImage(i);
                            sc = c;
                            break;
                        } else {
                            sc = -1;
                        }
                    }
                    if (selectionMode > 0) {
                        viewPort.repaint();
                    }
                } else {
                    mPoint.x = -1;
                    mPoint.y = -1;
                }
            }
        }
        public void mouseClicked(MouseEvent e) {
            if (player != null && e.getX() >= cardW 
              && e.getY() >= viewPort.getHeight()-handH) {
                if (sch > -1 && selectionMode == 0) {
                    gui.selectHandCard(sch);
                }
            } else {
                if (hand != null && player.getState() == 3
                  && mPoint.x > -1 && mPoint.y > -1) {
                    int p = gui.gameState.indexOfPlayer(player.getUsername());
                    boolean bOccupado = false;
                    if (sc > -1) {
                        bOccupado = true;
                    }
                    if (selectionMode == 1 && !bOccupado) {
                        setSelectionMode(0);
                        gui.client.playCard(
                          hand.getCards().indexOf(gui.lsc), mPoint.x, mPoint.y);
                    } else if (selectionMode == 2 && !bOccupado) {
                        setSelectionMode(0);
                        gui.client.moveCard(gui.gameState.indexOfCard(
                          gui.lsc, p), mPoint.x, mPoint.y);
                    } else if (selectionMode == 0 && bOccupado) {
                        gui.selectCard(sc);
                    }
                }
            }
        }
    }

    private class TableSidePanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (gui.gameState == null || player == null) {
                return;
            }
            int p = gui.gameState.indexOfPlayer(player.getUsername());
            if (player.getState() == 3) {
                g.setColor(colorLight);
                g.fillRect(0, 0, viewPort.getWidth(), viewPort.getHeight());
                g.setColor(colorTable);
                g.fillRect(3, 3, viewPort.getWidth()-6, viewPort.getHeight()-6);
            }
            g.setColor(colorLight);
            if (selectionMode > 0 && mPoint.x > -1 && mPoint.y > -1) {
                g.fillRect(
                  (cardW+padding)*mPoint.x+(padding-highLight),
                  (cardH+padding)*mPoint.y+(padding-highLight),
                  cardW+highLight*2, cardH+highLight*2);
            }
            for (GameCard gc : gui.gameState.getCards(p)) {
                if (gui.lsc != null && gui.lsc.equals(gc)) {
                    g.fillRect((cardW+padding)*gc.getX()+(padding-highLight),
                      (cardH+padding)*gc.getY()+(padding-highLight),
                      cardW+highLight*2, cardH+highLight*2);
                }
                int id = Integer.parseInt(gc.getImageId());
                Image img = gui.client.images.get(0);
                if (id < gui.client.images.size()
                  && gui.client.images.get(id) != null) {
                    img = gui.client.images.get(id);
                }
                g.drawImage(img, (cardW+padding)*gc.getX()+padding,
                  (cardH+padding)*gc.getY()+padding, cardW, cardH, null);
                if (gc.getTokenCount() > 0) {
                    g.drawImage(gui.imageToken,
                      (cardW+padding)*gc.getX()+padding+3,
                      (cardH+padding)*gc.getY()+padding, 24, 24, null);
                    g.drawString(""+gc.getTokenCount(),
                      (cardW+padding)*gc.getX()+padding+30,
                      (cardH+padding)*gc.getY()+padding+16);
                }
            }
        }
    }

    private class TableSideViewPort extends JViewport {
        public void paintChildren(Graphics g) {
            super.paintChildren(g);
            if (gui.gameState == null || player == null) {
                return;
            }
            g.setColor(Color.WHITE);
            g.setFont(font);
            g.drawString(player.getUsername(), 10, 18);
            int s = player.getDeckSize() / 8;
            if (s == 0 && player.getDeckSize() > 0) {
                s++;
            }
            for (int c = 0; c < s; c++) {
                if (gui.client.images.get(0) != null) {
                    g.drawImage(gui.client.images.get(0),
                      45-2*c, getHeight()-90-2*c, 25, 35, null);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(45-2*c, getHeight()-90-2*c, 25, 35);
                    g.setColor(Color.BLUE);
                    g.fillRect(46-2*c, getHeight()-89-2*c, 23, 33);
                }
            }
            if (player.getTopDiscardedCard() != null) {
                int id = Integer.parseInt(
                  player.getTopDiscardedCard().getImageId());
                Image img = gui.client.images.get(0);
                if (id < gui.client.images.size()
                  && gui.client.images.get(id) != null) {
                    img = gui.client.images.get(id);
                }
                g.drawImage(img, 45, getHeight()-45, 25, 35, null);
            }
            int hmax = player.getHandSize();
            if (hand != null) {
                hmax = hand.getSize();
            }
            for (int h = 0; h < hmax; h++) {
                int x = (cardW/2)*h+cardW;
                if (hand != null) {
                    int y;
                    if (gui.lsc != null && hand.indexOfCard(gui.lsc) == h) {
                        y = getHeight() - cardH;
                    } else {
                        y = getHeight()-handH;
                    }
                    Image img = gui.client.images.get(0);
                    int id = Integer.parseInt(hand.getCard(h).getImageId());
                    if (id < gui.client.images.size()
                      && gui.client.images.get(id) != null) {
                        img = gui.client.images.get(id);
                    }
                    g.drawImage(img, x, y, cardW, cardH, null);
                } else {
                    g.drawImage(gui.client.images.get(0),
                      x, handH-cardH, cardW, cardH, null);
                }
            }
        }
    }
}
