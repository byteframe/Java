import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScrewyGui extends JFrame {
    public static void main(String[] args) {
        ScrewyGui frame = new ScrewyGui();
        frame.setTitle("Screwy-Gui");
        frame.setSize(800,600);
        frame.setLocation(44,44);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    public ScrewyGui() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout(5,5));
        JPanel colors = new JPanel(new GridLayout(10,1));
        String[] peanuts = {"Cyan",
                            "Green",
                            "Red",
                            "Blue",
                            "Orange",
                            "Magenta",
                            "Black",
                            "Gray",
                            "Pink",
                            "Yellow"};
        ButtonGroup colorGroup = new ButtonGroup();
        JRadioButton[] colorButtons = new JRadioButton[10];
        for (int cb = 0; cb < 10; cb++) {
            colorButtons[cb] = new JRadioButton(peanuts[cb]);
            colorGroup.add(colorButtons[cb]);
            colors.add(colorButtons[cb]);
            colorsButtons[cb].addActionListener(this);
        }
        JPanel screen = new JPanel();
        colors.setBackground(Color.white);
        screen.setBackground(Color.blue);
        container.add(colors, BorderLayout.LINE_START);
        container.add(screen, BorderLayout.CENTER);
    }
}