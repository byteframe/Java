// Console.java
//
// swing panel for holding the console, which lists everything that happens in the game
// TODO: clean code, maybe have it extend JScrollPane(l?) once I fiqure out what that IS

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;import javax.swing.JTextArea;

public final class Console extends JPanel {
    private JTextArea consoleTA;

    public Console() {
        setLayout(new BorderLayout(0,0));

        consoleTA = new JTextArea("New Game Started...\n", 10,39);
        consoleTA.setEditable(false);
        consoleTA.setLineWrap(true);
        add(consoleTA);
        JScrollPane consoleSP = new JScrollPane(consoleTA, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                                           JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        consoleSP.setBorder(BorderFactory.createEmptyBorder());
        add(consoleSP);
    }

    public JTextArea getConsoleTA() { return consoleTA; }
    
    public void setText(String to_add) { consoleTA.setText(to_add); }
    
    public void add(String to_add) {
        consoleTA.append(to_add);
        validate();
    }
    public void addLine(String to_add) {
        consoleTA.append("\n" + to_add);
        validate();
    }
}
