import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExamApp extends JFrame implements ActionListener {

    public JPanel screenP = new JPanel();

    public static void main(String[] args) {
        ExamApp frame = new ExamApp();
        frame.setTitle("ExamApp");
        frame.setSize(640,480);
        frame.setLocation(100,50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    public ExamApp() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout(5,5));

        JButton blueP = new JButton("BLUE");
        JButton redP = new JButton("RED");
        JButton yellowP = new JButton("YELLOW");
        JButton greenP = new JButton("GREEN");

        blueP.addActionListener(this);
        redP.addActionListener(this);
        yellowP.addActionListener(this);
        greenP.addActionListener(this);

        blueP.setActionCommand("blue");
        redP.setActionCommand("red");
        yellowP.setActionCommand("yellow");
        greenP.setActionCommand("green");

        blueP.setBackground(Color.blue);
        redP.setBackground(Color.red);
        yellowP.setBackground(Color.yellow);
        greenP.setBackground(Color.green);
        screenP.setBackground(Color.black);

        container.add(blueP, BorderLayout.NORTH);
        container.add(redP, BorderLayout.WEST);
        container.add(yellowP, BorderLayout.EAST);
        container.add(greenP, BorderLayout.SOUTH);
        container.add(screenP, BorderLayout.CENTER);

    }

    public void actionPerformed(ActionEvent e) {
        if ("blue".equals(e.getActionCommand())) {
            screenP.setBackground(Color.blue);
        } else if ("red".equals(e.getActionCommand())) {
            screenP.setBackground(Color.red);
        } else if ("yellow".equals(e.getActionCommand())) {
            screenP.setBackground(Color.yellow);
        } else if ("green".equals(e.getActionCommand())) {
            screenP.setBackground(Color.green);
        }
    }
}