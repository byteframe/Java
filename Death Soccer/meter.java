import java.awt.*;

public class meter //implements MouseListener
{
    private int level;
    
    public int getLevel() { return level; }
    
    public void drawMeter(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRoundRect(545,450,30,150,10,10);
        g.setColor(Color.lightGray);
        g.fillRect(550,455,20,140);
    }
}
