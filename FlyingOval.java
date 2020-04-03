import java.awt.*;

public class flyingOval
{
    private int xPos, yPos, xToGo, yToGo, size;
    private Color colour = new Color(255,255,255);

    public flyingOval()
    {
        xPos = 100;
        yPos = 100;
        xToGo = 500;
        yToGo = 500;
        size = 10;
    }
    public flyingOval(int in_xPos, int in_yPos, int in_xToGo, int in_yToGo, int in_Size)
    {
        xPos = in_xPos;
        yPos = in_yPos;
        xToGo = in_xToGo;
        yToGo = in_yToGo;
        size = in_Size;
    }

    public void setColor(Color in_colour)
    {
        colour = in_colour;
    }

    public void drawFlyingOval(Graphics g)
    {
        if (xPos <= xToGo)
            xToGo = 560;
        if (xPos >= xToGo)
            xToGo = 25;
        if (xPos < xToGo)
            xPos += 5;
        if (xPos > xToGo)
            xPos -= 5;

        if (yPos <= yToGo)
            yToGo = 840;
        if (yPos >= yToGo)
            yToGo = 25;
        if (yPos < yToGo)
            yPos += 5;
        if (yPos > yToGo)
            yPos -= 5;

        g.setColor(colour);
        g.fillOval(xPos,yPos,size,size);
    }
}
