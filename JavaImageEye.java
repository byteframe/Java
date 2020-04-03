import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaImageEye extends JFrame {
    public static void main(String[] args) {
        JavaImageEye frame = new JavaImageEye();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        JMenu menu1 = new JMenu("Image");
        JMenu menu2 = new JMenu("Help");
        menubar.add(menu1);
        menubar.add(menu2);

        frame.setTitle("Java Image Eye");
        frame.setSize(800,600);
        frame.setLocation(44,44);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    public JavaImageEye() {
        // Panel and Container Shaith
        Container container = getContentPane();
        container.setLayout(new BorderLayout(5,5));

        JPanel imgSelect = new JPanel();
        JPanel showImage = new JPanel();
        JPanel imgStats = new JPanel();
        JPanel operations = new JPanel(new GridLayout(3,1));
        JPanel colors = new JPanel();
        JPanel rotater = new JPanel();
        JPanel scale = new JPanel();

        imgSelect.setBackground(Color.white);
        imgSelect.setBorder(BorderFactory.createTitledBorder("Open Images"));
        showImage.setBorder(BorderFactory.createLoweredBevelBorder());
        imgStats.setBackground(Color.white);
        imgStats.setBorder(BorderFactory.createLoweredBevelBorder());
        operations.setBorder(BorderFactory.createLoweredBevelBorder());
        colors.setBackground(Color.gray);
        rotater.setBackground(Color.magenta);
        scale.setBackground(Color.orange);

        container.add(imgSelect, BorderLayout.WEST);
        container.add(showImage, BorderLayout.CENTER);
        container.add(imgStats, BorderLayout.SOUTH);
        container.add(operations, BorderLayout.EAST);
        operations.add(colors);
        operations.add(rotater);
        operations.add(scale);

        imgSelect.setFocusable(true);
        showImage.setFocusable(true);
        imgStats.setFocusable(true);
        operations.setFocusable(true);
        colors.setFocusable(true);
        imgSelect.setFocusable(true);
        scale.setFocusable(true);

        // Open Image List
        String tempCrap[] = {"aaa","bbb","ccc"};
        JList openImages = new JList(tempCrap);
        imgSelect.add(openImages);

        // Image Showing

        // Current Image Information
        JTextArea imgInfo = new JTextArea("And das a way to determine density",1,7);
        imgStats.add(imgInfo);
    }
}

/*class BasicPanel extends JPanel implements ActionListener, MouseListener {
    public BasicPanel() {
    }

    public void paintComponent(Graphics g) {
    }

    // ActionEvent Handler
    public void actionPerformed(ActionEvent e) {
      System.out.println("Button 1 Pressed");
    }

    // MouseEvent Handlers
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}

    // KeyEvent Handlers
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
}*/
