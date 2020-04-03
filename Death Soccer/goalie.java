import java.awt.*;

public class goalie
{
    private int xPos;
    private int yPos;

    public goalie()
    {
        xPos = 100;
        yPos = 100;
    }
    public goalie(int in_xPos, int in_yPos)
    {
        xPos = in_xPos;
        yPos = in_yPos;
    }

    public int getX() { return xPos; }
    public int getY() { return yPos; }

    public void drawGoalie(Graphics g)
    {
        g.setColor(Color.blue);
        g.fillRect(xPos, yPos, 50, 50);
    }
}
