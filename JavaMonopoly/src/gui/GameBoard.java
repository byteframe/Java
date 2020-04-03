// GameBoard.java
//
// swing panel for holding the game board, uses mouse listener for mouse over on the properties
// maybe+100: switch from board1.gif to Graphics draw, and mouse listener for

package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.*;

public final class GameBoard extends JPanel implements ActionListener, MouseMotionListener {
    private String[] boardInfo;
    private Color[] groupColors;
    private Rectangle[] placeBounds;
    private Magnifier magnifier;
    private RunningGame runninggame;
    private Timer moveTimer;
    private int toMove, left;
    private boolean finishedMoving;
    private MainFrame mainframe;
    
    public GameBoard(Magnifier m) {
        magnifier = m;
        placeBounds = new Rectangle[40];
        moveTimer = new Timer(300, this);

        placeBounds[0]=new Rectangle(398,398,55,55); // GO
        placeBounds[1]=new Rectangle(361,398,34,55);
        placeBounds[2]=new Rectangle(323,398,34,55);
        placeBounds[3]=new Rectangle(286,398,34,55);
        placeBounds[4]=new Rectangle(248,398,34,55);
        placeBounds[5]=new Rectangle(211,398,34,55);
        placeBounds[6]=new Rectangle(173,398,34,55);
        placeBounds[7]=new Rectangle(136,398,34,55);
        placeBounds[8]=new Rectangle(98,398,34,55);
        placeBounds[9]=new Rectangle(61,398,34,55);
        
        placeBounds[10]=new Rectangle(3,398,55,55); // JV
        placeBounds[11]=new Rectangle(3,361,55,34);
        placeBounds[12]=new Rectangle(3,323,55,34);
        placeBounds[13]=new Rectangle(3,286,55,34);
        placeBounds[14]=new Rectangle(3,248,55,34);
        placeBounds[15]=new Rectangle(3,211,55,34);
        placeBounds[16]=new Rectangle(3,173,55,34);
        placeBounds[17]=new Rectangle(3,138,55,34);
        placeBounds[18]=new Rectangle(3,98,55,34);
        placeBounds[19]=new Rectangle(3,61,55,34);
        
        placeBounds[20]=new Rectangle(3,3,55,55); // FP
        placeBounds[21]=new Rectangle(61,3,34,55);
        placeBounds[22]=new Rectangle(98,3,34,55);
        placeBounds[23]=new Rectangle(138,3,34,55);
        placeBounds[24]=new Rectangle(173,3,34,55);
        placeBounds[25]=new Rectangle(211,3,34,55);
        placeBounds[26]=new Rectangle(248,3,34,55);
        placeBounds[27]=new Rectangle(286,3,34,55);
        placeBounds[28]=new Rectangle(323,3,34,55);
        placeBounds[29]=new Rectangle(361,3,34,55);
        
        placeBounds[30]=new Rectangle(398,3,55,55); // GT   
        placeBounds[31]=new Rectangle(398,61,55,34);
        placeBounds[32]=new Rectangle(398,98,55,34);
        placeBounds[33]=new Rectangle(398,138,55,34);
        placeBounds[34]=new Rectangle(398,173,55,34);
        placeBounds[35]=new Rectangle(398,211,55,34);
        placeBounds[36]=new Rectangle(398,248,55,34);
        placeBounds[37]=new Rectangle(398,286,55,34);
        placeBounds[38]=new Rectangle(398,323,55,34);
        placeBounds[39]=new Rectangle(398,361,55,34);
        
        setPreferredSize(new Dimension(456,456));
        addMouseMotionListener(this);
        magnifier.setOutline(new Color(0,0,0));
        magnifier.setBacking(new Color(255,255,255));    
	}
	
	public GameBoard(Magnifier m, String profile, RunningGame r, MainFrame ma) {
      this(m);
      runninggame = r;
      mainframe = ma;
      try {
          BufferedReader brdr = new BufferedReader(new FileReader(new File(profile))); 
          String line = brdr.readLine(); /* Skipping past header */
            line = brdr.readLine();        /* Skipping past description */   
            boardInfo = new String[30];
            for(int cnt = 0; cnt < boardInfo.length; cnt++) {
                line = brdr.readLine();
                boardInfo[cnt] = line.substring(line.indexOf('=') + 1, line.lastIndexOf(';'));
            }
            groupColors = new Color[8];
            String[] grepped;
            for(int cnt = 0; cnt < groupColors.length; cnt++) {
                line = brdr.readLine();
                line = line.substring(line.indexOf('=') + 1, line.lastIndexOf(';'));                                     
                grepped = line.split(";");
                groupColors[cnt] = new Color(new Integer(grepped[0]), new Integer(grepped[1]), new Integer(grepped[2]));
            }	
            magnifier.setEasyCardBigII("../images/magnifier/" + boardInfo[2]);
            magnifier.setHardCardBigII("../images/magnifier/" + boardInfo[5]);
            magnifier.setStartBigII("../images/magnifier/" + boardInfo[8]);
            magnifier.setDetentionBigII("../images/magnifier/" + boardInfo[11]);
            magnifier.setFreespotBigII("../images/magnifier/" + boardInfo[14]);
            magnifier.setPenaltyBigII("../images/magnifier/" + boardInfo[17]);
            magnifier.setTransportationBigII("../images/magnifier/" + boardInfo[19]);
            magnifier.setUtility1BigII("../images/magnifier/" + boardInfo[21]);
            magnifier.setUtility2BigII("../images/magnifier/" + boardInfo[23]);
            magnifier.setTax1BigII("../images/magnifier/" + boardInfo[26]);
            magnifier.setTax2BigII("../images/magnifier/" + boardInfo[29]);
            magnifier.setCurrentPlace(0); 
            pieces = new ImageIcon[4];
            pieces[0] = new ImageIcon("../images/pieces/piece1.gif");
            pieces[1] = new ImageIcon("../images/pieces/piece2.gif");
            pieces[2] = new ImageIcon("../images/pieces/piece3.gif");
            pieces[3] = new ImageIcon("../images/pieces/piece4.gif");                                                                                                            
        } catch(FileNotFoundException fndex) {
            System.out.println("file");
        } catch(IOException ioex) {
            System.out.println("io");
        }      
    }

    public void movePlayer(int p, int toGo) {
        left = toGo;
        toMove = p;
        moveTimer.start();
    }
    public void actionPerformed(ActionEvent ae) {
        if (left == 0) {
            moveTimer.stop();
            mainframe.getActions().unlock();
            mainframe.getConsole().addLine(runninggame.getPlayers()[toMove].getName()+" lands on " + runninggame.getPlace(runninggame.getPlayers()[toMove].getBoardPos()).getTitle());
        } else {
            left--;
            runninggame.getPlayers()[toMove].moveForward(1);
            repaint();
        }              
    }

    public void mouseDragged(MouseEvent me) {}
    public void mouseMoved(MouseEvent me) {
        for(int cnt = 0; cnt < 40; cnt++) {
            if(me.getX() >= placeBounds[cnt].x && me.getX() <= placeBounds[cnt].x + placeBounds[cnt].width &&
               me.getY() >= placeBounds[cnt].y && me.getY() <= placeBounds[cnt].y + placeBounds[cnt].height) {
                if(cnt == 1 || cnt == 3) {
                    magnifier.setBar(groupColors[0]);
                }
                else if(cnt == 6 || cnt == 8 || cnt == 9) {
                    magnifier.setBar(groupColors[1]);
                }
                else if(cnt == 11 || cnt == 13 || cnt == 14) {
                    magnifier.setBar(groupColors[2]);
                }
                else if(cnt == 16 || cnt == 18 || cnt == 19) { 
                    magnifier.setBar(groupColors[3]);
                }
                else if(cnt == 21 || cnt == 23 || cnt == 24) {
                    magnifier.setBar(groupColors[4]);
                }
                else if(cnt == 26 || cnt == 27 || cnt == 29) {
                    magnifier.setBar(groupColors[5]);
                }
                else if(cnt == 32 || cnt == 32 || cnt == 34) {
                    magnifier.setBar(groupColors[6]);
                }
                else if(cnt == 37 || cnt == 39) {
                    magnifier.setBar(groupColors[7]);
                }
                magnifier.setCurrentPlace(cnt);
                magnifier.repaint();
            }
        }
    }
	
    public void paintComponent(Graphics g) { super.paintComponent(g);
        g.drawImage(titleII.getImage(), 0, 0, null);
        for(int cnt = 0; cnt < runninggame.getPlayers().length; cnt++) {
            g.drawImage(pieces[cnt].getImage(), 
                        placeBounds[runninggame.getPlayers()[cnt].getBoardPos()].x + (placeBounds[runninggame.getPlayers()[cnt].getBoardPos()].width/2 - (pieces[cnt].getIconWidth() / 2)),
                        placeBounds[runninggame.getPlayers()[cnt].getBoardPos()].y + (placeBounds[runninggame.getPlayers()[cnt].getBoardPos()].height/2 - (pieces[cnt].getIconHeight() / 2)), null);
        } 
    }
    private ImageIcon[] pieces;
    private ImageIcon titleII = new ImageIcon("../images/gameboard/hasbroBoard.png");
    
    public boolean isFinishedMoving() { return finishedMoving; }
}
