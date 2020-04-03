// main.java
//
// starts the Java Monopoly executable, taking file names of game profiles in 'profiles'
// TODO: allow any paths, research threads as payment for void/run/etc code theft...

import java.io.File;
import javax.swing.JFrame;

import gui.MainFrame;

class main {
    public static void main(String[] args) {
        final File[] profileFiles;
        if (args.length > 0) {
            profileFiles = new File[args.length];
        } else {
            profileFiles = new File[1];
        }
        profileFiles[0] = new File("../profiles/hasbro.monp");
        for (int cnt = 1; cnt < args.length; cnt++) {
            profileFiles[cnt] = new File("../profiles/" + args[cnt]);
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                spawnFrame(profileFiles);
            }
        });
    }
    
    private static void spawnFrame(File[] in_profileFiles) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        MainFrame mainframe = new MainFrame(in_profileFiles);
    }
}
