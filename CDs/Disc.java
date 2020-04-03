import java.awt.*;

public class Disc
{
    private String title = new String();
    private String artist = new String();
    private int tracks = 0;
    private int length = 0;
    
    public Disc(String t, String a, int r, int l)
    {
        title = t;
        artist = a;
        tracks = r;
        length = l;
    }
    
    public void addDiscInfo(Graphics g)
    {
        g.drawString(title, 100, 200);
        g.drawString(artist, 100, 230);
        g.drawString(""+tracks, 193, 260);
        g.drawString(""+length, 153, 290);
    }
    
    public String avLength()
    {
        double averageSongLength = 0;
        String returnString = new String();
        averageSongLength = (double)length/(double)tracks;
        averageSongLength *= 100;
        averageSongLength = (int)averageSongLength;
        averageSongLength /= 100;
        returnString = "Average Song Length: " + averageSongLength;
        return returnString;
    }
}