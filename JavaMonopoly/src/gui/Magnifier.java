// Magnifier.java
//
// mouse over effects in GameBoard show the card magnified
// assuming my code organization overall isnt FUBAR...

package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;import javax.swing.JPanel;

import game.*;

public final class Magnifier extends JPanel {
    private ImageIcon easyCardBigII,
                      hardCardBigII,
                      startBigII,
                      detentionBigII,
                      freespotBigII,
                      penaltyBigII,
                      transportationBigII,
                      utility1BigII,
                      utility2BigII,
                      tax1BigII,
                      tax2BigII;
    private Color outline,
                  backing, 
                  bar;
    private int placeType, utilityOne;
    private RealEstate lastRealEstate; // 0   
    private Transportation lastTransportation; // 1                  
    private Utility lastUtility;// 2              
    private ActionSquare lastActionSquare;     // 3
    private RunningGame runninggame;         

    public Magnifier(RunningGame r) {
        runninggame = r;
        outline = Color.black;
        backing = Color.white;
        bar = Color.gray;
        lastRealEstate = new RealEstate();
        lastUtility = new Utility();
        lastTransportation = new Transportation();
        lastActionSquare = new ActionSquare();
        setBackground(Color.black);
        setPreferredSize(new Dimension(184,187));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(outline);
        g.fillRect(0,0,180,180);
        g.setColor(backing);
        g.fillRect(1,1,182,185);
        switch(placeType) {
        case 0:
            g.setColor(outline);
            g.fillRect(6,6,172,38);
            g.setColor(bar);
            g.fillRect(7,7,170,36);
            g.setColor(outline);
            g.setFont(new Font("Sans", Font.BOLD, 12));
            g.drawString("$"+lastRealEstate.getCost(), 10, 20);
            g.setFont(new Font("Sans", Font.PLAIN, 10));
            g.drawString("TITLEDEED", (getWidth()-g.getFontMetrics().stringWidth("TITLEDEED"))/2, 18);
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.drawString(lastRealEstate.getTitle(), (getWidth()-g.getFontMetrics().stringWidth(lastRealEstate.getTitle()))/2, 35);
            g.setFont(new Font("Sans", Font.PLAIN, 12));
            g.drawString("RENT $"+lastRealEstate.getRent(0), (getWidth()-g.getFontMetrics().stringWidth("RENT $"+lastRealEstate.getRent(0)))/2, 58);
            g.drawString("With 1 Houses      $" + lastRealEstate.getRent(1), (getWidth()-146)/2, 75);
            g.drawString("With 2 Houses      $" + lastRealEstate.getRent(2), (getWidth()-146)/2, 90);
            g.drawString("With 3 Houses      $" + lastRealEstate.getRent(3), (getWidth()-146)/2, 105);
            g.drawString("With 4 Houses      $" + lastRealEstate.getRent(4), (getWidth()-146)/2, 120);
            g.setFont(new Font("Arial", Font.PLAIN, 10));
            g.drawString("With Hotel $" + lastRealEstate.getRent(5), (getWidth()-g.getFontMetrics().stringWidth("With Hotel $" + lastRealEstate.getRent(5)))/2, 138);
            g.drawString("Morgage Value $" + lastRealEstate.findMorgageValue(), (getWidth()-g.getFontMetrics().stringWidth("Morgage Value $" + lastRealEstate.findMorgageValue()))/2, 152);
            g.drawString("Houses cost $" + lastRealEstate.getBuildCost()+". each", (getWidth()-g.getFontMetrics().stringWidth("Houses cost $" + lastRealEstate.getBuildCost()+". each"))/2, 166);
            g.drawString("Hotels, $" + lastRealEstate.getBuildCost()+". plus 4 houses", (getWidth()-g.getFontMetrics().stringWidth("Hotels, $" + lastRealEstate.getBuildCost()+". plus 4 houses"))/2, 180);                           
            break;
        case 1:
            g.drawImage(transportationBigII.getImage(), 1, 1, null); 
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(outline);
            g.drawString(lastTransportation.getTitle(), (getWidth()-g.getFontMetrics().stringWidth(lastTransportation.getTitle()))/2, 85);
            g.setFont(new Font("Sans", Font.PLAIN, 10));
            g.drawString("If 1 stations owned      $" + lastTransportation.findBill(1), (getWidth()-138)/2, 100);
            g.drawString("If 2 stations owned      $" + lastTransportation.findBill(2), (getWidth()-138)/2, 115);
            g.drawString("If 3 stations owned      $" + lastTransportation.findBill(3), (getWidth()-138)/2, 130);
            g.drawString("If 4 stations owned      $" + lastTransportation.findBill(4), (getWidth()-138)/2, 145);
            g.drawString("Morgage Value $" + lastTransportation.findMorgageValue(), (getWidth()-g.getFontMetrics().stringWidth("Morgage Value $" + lastTransportation.findMorgageValue()))/2, 160);
            break;
        case 2:
            if (utilityOne == 12) {
                g.drawImage(utility1BigII.getImage(), 1, 1, null); 
            } else if (utilityOne == 28) {
                g.drawImage(utility2BigII.getImage(), 1, 1, null); 
            }
            g.setColor(outline);
            g.setFont(new Font("Sans", Font.PLAIN, 9));
            g.drawString("If one Utility is owned rent", (getWidth()-g.getFontMetrics().stringWidth("If only Utility is owned rent"))/2, 90);
            g.drawString("is 4 times the amount on dice.", (getWidth()-g.getFontMetrics().stringWidth("is 4 times the amount on dice."))/2, 105);
            g.drawString("If both Utilities are owned rent", (getWidth()-g.getFontMetrics().stringWidth("If both Utilities are owned rent"))/2, 120);
            g.drawString("is 10 times the amount on dice.", (getWidth()-g.getFontMetrics().stringWidth("is 10 times the amount on dice."))/2, 135);
            g.drawString("Morgage Value $" + lastUtility.findMorgageValue(), (getWidth()-g.getFontMetrics().stringWidth("Morgage Value $" + lastUtility.findMorgageValue()))/2, 160);
            break;
        case 3:
            switch(lastActionSquare.getActionType()) {
            case 1:
                g.drawImage(easyCardBigII.getImage(), 1, 1, null); break;
            case 2:
                g.drawImage(hardCardBigII.getImage(), 1, 1, null); break;
            case 3:
                g.drawImage(startBigII.getImage(), 1, 1, null); break;
            case 4:
                g.drawImage(detentionBigII.getImage(), 1, 1, null); break;
            case 5:
                g.drawImage(freespotBigII.getImage(), 1, 1, null); break;
            case 6:
                g.drawImage(penaltyBigII.getImage(), 1, 1, null); break;
            case 7:
                g.drawImage(tax1BigII.getImage(), 1, 1, null);
                g.setColor(outline);
                g.setFont(new Font("Arial", Font.BOLD, 14));
                g.drawString(lastActionSquare.getTitle(), (getWidth()-g.getFontMetrics().stringWidth(lastActionSquare.getTitle()))/2, 105);
                g.setFont(new Font("Sans", Font.PLAIN, 10));
                g.drawString("Pay 10%", (getWidth()-g.getFontMetrics().stringWidth("Pay 10%"))/2, 120);
                g.drawString("or", (getWidth()-g.getFontMetrics().stringWidth("or"))/2, 135);
              g.drawString("200 dollars.", (getWidth()-g.getFontMetrics().stringWidth("200 dollars."))/2, 150);
                break;
            case 8:
                g.drawImage(tax2BigII.getImage(), 1, 1, null); 
                g.setColor(outline);
                g.setFont(new Font("Arial", Font.BOLD, 14));
                g.drawString(lastActionSquare.getTitle(), (getWidth()-g.getFontMetrics().stringWidth(lastActionSquare.getTitle()))/2, 85);                        
                g.setFont(new Font("Sans", Font.PLAIN, 10));
                g.drawString("Pay $75", (getWidth()-g.getFontMetrics().stringWidth("Pay $75"))/2, 105);                        
                break;
            default: break;
            }
            break;
        default: break;
        }
    }

    public void setEasyCardBigII(String file) { easyCardBigII = new ImageIcon(file); }
    public void setHardCardBigII(String file) { hardCardBigII = new ImageIcon(file); }
    public void setStartBigII(String file) { startBigII = new ImageIcon(file); }
    public void setDetentionBigII(String file) { detentionBigII = new ImageIcon(file); }	
    public void setFreespotBigII(String file) { freespotBigII = new ImageIcon(file); }	
    public void setPenaltyBigII(String file) { penaltyBigII = new ImageIcon(file); }
    public void setTransportationBigII(String file) { transportationBigII = new ImageIcon(file); }
    public void setUtility1BigII(String file) { utility1BigII = new ImageIcon(file); }	
    public void setUtility2BigII(String file) { utility2BigII = new ImageIcon(file); }	
    public void setTax1BigII(String file) { tax1BigII = new ImageIcon(file); }	
    public void setTax2BigII(String file) { tax2BigII = new ImageIcon(file); }	
    
    public void setOutline(Color in_outline) { outline = in_outline; }
    public void setBacking(Color in_backing) { backing = in_backing; }
    public void setBar(Color in_bar) { bar = in_bar; }
    
    public void setCurrentPlace(int p) {
        if (runninggame.getPlace(p) instanceof RealEstate) {
            lastRealEstate = (RealEstate)runninggame.getPlace(p);
            placeType = 0;
        } else if (runninggame.getPlace(p) instanceof Transportation) {
            lastTransportation = (Transportation)runninggame.getPlace(p);
            placeType = 1;
        } else if (runninggame.getPlace(p) instanceof Utility) {
            lastUtility = (Utility)runninggame.getPlace(p);
            placeType = 2;
            utilityOne = p;
        } else if (runninggame.getPlace(p) instanceof ActionSquare) {
            lastActionSquare = (ActionSquare)runninggame.getPlace(p);
            placeType = 3;
        }
    }
}
