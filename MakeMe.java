import java.awt.*;
public class MakeMe
{
    private int x;
    private int y;
    private int xs;
    private int ys;
    private int c;
    private int cs;
    private CSDemo d;
    private boolean down = true;
    private boolean right = true;
    private String s = new String();
    public MakeMe(String tagline, int xpos, int ypos, int mxspeed, int myspeed, int startcolor, int cspeed, CSDemo csd)
    {
        s = tagline;
        x = xpos;
        y = ypos;
        xs = mxspeed;
        ys = myspeed;
        c = startcolor;
        cs = cspeed;
        d = csd;
    }
    public void paintMe(Graphics g)
    {
        if (c+cs < 508)c=c+cs;
        else c = 0;
        if (c>=0 && c<=508)g.setColor(d.c[c]);
        if (right)
        {
            if (x<1200)
            {
                x=x+xs;
            }
            else
            {
                right = false;
            }
        }
        else
        {
            if (x>10)
            {
                x=x-xs;
            }
            else
            {
                right = true;
            }
        }
        if (down)
        {
            if (y<1000)
            {
                y=y+ys;
            }
            else
            {
                down = false;
            }
        }
        else
        {
            if (y>30)
            {
                y=y-ys;
            }
            else
            {
                down = true;
            }
        }
        g.drawString(s, x, y);
    }
}
