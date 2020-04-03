import javax.swing.*;
import java.awt.*;

public class TOTEBS extends JPanel
{
    public static void main(String[] args)
    {
        JFrame theFrame = new JFrame("TOTEBS");
        theFrame.getContentPane().add(new TOTEBS());
        theFrame.setSize(640,480);
        theFrame.setResizable(false);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
    }
    
    public void paintComponent(Graphics gfx)
    {
        super.paintComponent(gfx);
        gfx.setColor(Color.black);
        gfx.fillRect(0,0,640,300); // background color of the whole frame/panel

        gfx.setColor(Color.lightGray);
        gfx.fillRect(0,0,91,300);
        gfx.setColor(Color.yellow);
        gfx.fillRect(91,0,91,300);
        gfx.setColor(Color.cyan);
        gfx.fillRect(182,0,91,300);
        gfx.setColor(Color.green);
        gfx.fillRect(273,0,91,300);
        gfx.setColor(Color.magenta);
        gfx.fillRect(364,0,91,300);
        gfx.setColor(Color.red);
        gfx.fillRect(455,0,91,300);
        gfx.setColor(Color.blue);
        gfx.fillRect(545,0,94,300); 

        gfx.setColor(Color.blue);        
        gfx.fillRect(0,300,91,35);
        gfx.setColor(Color.black);
        gfx.fillRect(91,300,91,35);
        gfx.setColor(Color.magenta);
        gfx.fillRect(182,300,91,35);
        gfx.setColor(Color.black);
        gfx.fillRect(273,300,91,35);
        gfx.setColor(Color.cyan);
        gfx.fillRect(364,300,91,35);
        gfx.setColor(Color.black);
        gfx.fillRect(455,300,91,35);
        gfx.setColor(Color.lightGray);
        gfx.fillRect(545,300,94,35);

        // bottom has colors that dont look like they came from an EGA card in 1975...
        Color navyishBlue = new Color(8,62,89);
        Color darkishPurple = new Color(58,0,126);
        Color notAsLightGray = new Color(38,38,38);

        gfx.setColor(navyishBlue);
        gfx.fillRect(0,335,113,145);
        gfx.setColor(Color.white);
        gfx.fillRect(113,335,115,145);      
        gfx.setColor(darkishPurple);
        gfx.fillRect(228,335,114,145);       
        gfx.setColor(notAsLightGray);
        gfx.fillRect(342,335,113,145);
        gfx.setColor(Color.black);
        gfx.fillRect(455,335,31,145);      
        gfx.setColor(notAsLightGray);
        gfx.fillRect(486,335,29,145);     
        gfx.setColor(Color.darkGray);
        gfx.fillRect(515,335,30,145);
        gfx.setColor(Color.black);
        gfx.fillRect(545,335,94,145);  

        gfx.setColor(Color.black);
        gfx.fillRect(220,100,200,100);
        gfx.setColor(Color.gray); 
        gfx.fillRect(224,104,192,92);       
        gfx.setColor(Color.black);
        gfx.fillRect(230,110,180,80); 
        gfx.setColor(Color.lightGray);
        gfx.fillRect(234,114,172,72);

        Font theFont = new Font("Courier",Font.ITALIC + Font.BOLD,25);
        gfx.setColor(Color.black);
        gfx.setFont(theFont);
        gfx.drawString("tcarroll",260,155);
    }
}
