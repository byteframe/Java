import java.awt.event*;
import java.awt*;
import java.applet*;

public class CDs extends Applet implements MouseListener
{
    private Disc[]d = new Disc[10];
    private int CDIndex = 0;
    public Font mainFont = new Font("Times", Font.BOLD, 16);
    
    public void init()
    {
        addMouseListener(this);
        d[0] = newDisc("Ultra Zone", "Steve Vai", 13, 70);
        d[1] = newDisc("Ultra Zone", "Steve Vai", 13, 70);
        d[2] = newDisc("Ultra Zone", "Steve Vai", 13, 70);
        d[3] = newDisc("Ultra Zone", "Steve Vai", 13, 70);
        d[4] = newDisc("Ultra Zone", "Steve Vai", 13, 70);
        d[5] = newDisc("Ultra Zone", "Steve Vai", 13, 70);
        d[6] = newDisc("Ultra Zone", "Steve Vai", 13, 70);
        d[7] = newDisc("Ultra Zone", "Steve Vai", 13, 70);
        d[8] = newDisc("Ultra Zone", "Steve Vai", 13, 70);
        d[9] = newDisc("Ultra Zone", "Steve Vai", 13, 70);
    }
    
    public void mouseReleased(MouseEvent e)
    {
        if (e.getX() > 100 && e.getX() < 130 && e.getY() > 100
        && e.getY() < 130 && CDIndex != 0)
            CDIndex = CDIndex -1;
        if (e.getX() > 370 && e.getX() < 400 && e.getY() > 100
        && e.getY() < 130 && CDIndex != 9)
            CDIndex = CDIndex +1;
        repaint();
    }
    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    
    public void paint(Graphics g)
    {
        g.setFont(mainFont);
        
        g.setColor(Color.cyan);
        g.fillRect(0,0,499,399);
        g.setColor(Color.black);
        g.drawLine(10,70,490,70);
        g.drawString("CDs"),200,30);
        
        g.drawString("Title:",50,200);
        g.drawString("Artist:",50,230);
        g.drawString("Number of Tracks:",50,260);
        g.drawString("Disc Length:",50,290);
        
        d[CDIndex].addDiscInfo(g);
        
        g.drawString(dpCDIndex].avLength(),50,320);
        
        g.setColor(Color.red);
        g.fillRoundRect(100, 100, 30, 30 ,10 ,10);
        g.fillRoundRect(370, 100, 30, 30 ,10 ,10);
    }
    
    public void update (Graphics g)
    {
        paint(g);
    }
}
    	