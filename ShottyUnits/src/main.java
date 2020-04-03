// main.java
//
//
//

import javax.swing.JFrame;

import gui.GameFrame;

class main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].charAt(0) == '1') {
            JFrame.setDefaultLookAndFeelDecorated(true);
        }
        GameFrame gameframe = new GameFrame();
    }
}
