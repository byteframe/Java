// NewGameDialog.java
//
// dialog for starting a new game
// has options for number of players, selectable houserules, and game profile

package gui;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class NewGameDialog extends JDialog implements ActionListener {
    private JTextField[] playerNameTFs;
    private JCheckBox[] rulesBs;
    private JComboBox profileSelectorCB;
    private String[] profileConcats;

    public NewGameDialog(MainFrame ownerFrame, File[] in_profileFiles) {
        super(ownerFrame, true);
        profileConcats = new String[in_profileFiles.length];
        BufferedReader brdr;
        String line;
        for (int cnt = 0; cnt < in_profileFiles.length; cnt++) {
           try {
               brdr = new BufferedReader(new FileReader(in_profileFiles[cnt]));
               line = brdr.readLine();
               if (line.compareTo("[JavaMonopoly profile]") == 0) {
                   line = brdr.readLine();
                   profileConcats[cnt] = line.substring(line.indexOf('=') + 1, line.lastIndexOf(';')) + " -- ( " + in_profileFiles[cnt].getName() + " )";                    
               } else {
                   System.out.println("File not a valid monopoly profile : " + in_profileFiles[cnt].getName());
               }                                       
           } catch(FileNotFoundException ex) {
               System.out.println("File not Found : " + in_profileFiles[cnt]);
           } catch(IOException ex) {
               System.out.println(ex.getMessage());
           }
        }
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(Box.createRigidArea(new Dimension(16,16)));
        JPanel playersP = new JPanel();
        playersP.setLayout(new BoxLayout(playersP, BoxLayout.Y_AXIS));
        String[] s_playerTypes = { "Human", "Computer", "Inactive"};
        JComboBox[] playerTypeCBs = new JComboBox[4];
        playerTypeCBs[0] = new JComboBox(s_playerTypes);
        playerTypeCBs[0].setSelectedIndex(0);
        playerTypeCBs[0].setEnabled(false);
        playerTypeCBs[1] = new JComboBox(s_playerTypes);
        playerTypeCBs[1].setSelectedIndex(1);
        playerTypeCBs[2] = new JComboBox(s_playerTypes);
        playerTypeCBs[2].setSelectedIndex(2); 
        playerTypeCBs[3] = new JComboBox(s_playerTypes);
        playerTypeCBs[3].setSelectedIndex(2);    
        playerNameTFs = new JTextField[4];
        playerNameTFs[0] = new JTextField(15);
        playerNameTFs[0].setText("Player 1");
        playerNameTFs[1] = new JTextField(15);
        playerNameTFs[1].setText("CPU Player 1");
        playerNameTFs[1].setEditable(false);
        playerNameTFs[2] = new JTextField(15);
        playerNameTFs[2].setText("Inactive");
        playerNameTFs[2].setEditable(false);
        playerNameTFs[3] = new JTextField(15);
        playerNameTFs[3].setText("Inactive");
        playerNameTFs[3].setEditable(false);  
        JPanel[] playerSelectionsP = new JPanel[4];
        for (int cnt = 0; cnt < 4; cnt++) {
            playerTypeCBs[cnt].addActionListener(this);
            playerTypeCBs[cnt].setName("cb" + cnt);
            playerSelectionsP[cnt] = new JPanel();
            playerSelectionsP[cnt].setLayout(new BoxLayout(playerSelectionsP[cnt], BoxLayout.X_AXIS));
            playerSelectionsP[cnt].add(Box.createRigidArea(new Dimension(16,0)));
            playerSelectionsP[cnt].add(playerNameTFs[cnt]);
            playerSelectionsP[cnt].add(Box.createRigidArea(new Dimension(8,0)));
            playerSelectionsP[cnt].add(playerTypeCBs[cnt]);
            playerSelectionsP[cnt].add(Box.createRigidArea(new Dimension(16,0)));
            playersP.add(playerSelectionsP[cnt]);
            playersP.add(Box.createRigidArea(new Dimension(0,8)));
        }
        getContentPane().add(playersP);

        getContentPane().add(Box.createRigidArea(new Dimension(0,8)));
        JPanel rulesP = new JPanel();
        rulesP.setLayout(new BoxLayout(rulesP, BoxLayout.Y_AXIS));
        rulesBs = new JCheckBox[3];
        rulesBs[0] = new JCheckBox("Auctions");
        rulesBs[1] = new JCheckBox("Bonus on Free Parking");
        rulesBs[2] = new JCheckBox("Double Salary on GO"); 
        JPanel[] rulesTogglesP = new JPanel[3];    
        for(int cnt = 0; cnt < 3; cnt++) {   
            rulesBs[cnt].addActionListener(this);
            rulesTogglesP[cnt] = new JPanel();
            rulesTogglesP[cnt].setLayout(new BoxLayout(rulesTogglesP[cnt], BoxLayout.X_AXIS));
            rulesTogglesP[cnt].add(Box.createRigidArea(new Dimension(16,0)));
            rulesTogglesP[cnt].add(rulesBs[cnt]);
            rulesTogglesP[cnt].add(Box.createHorizontalGlue());
            rulesTogglesP[cnt].add(Box.createRigidArea(new Dimension(16,0)));
            rulesP.add(rulesTogglesP[cnt]);
        }
        getContentPane().add(rulesP);

        getContentPane().add(Box.createRigidArea(new Dimension(0,16)));
        JPanel profilesP = new JPanel();
        profilesP.setLayout(new BoxLayout(profilesP, BoxLayout.X_AXIS));
        profileSelectorCB = new JComboBox(profileConcats);
        profileSelectorCB.setSelectedIndex(0);
        profileSelectorCB.addActionListener(this);
        profilesP.add(Box.createRigidArea(new Dimension(16,0)));
        profilesP.add(profileSelectorCB);
        profilesP.add(Box.createRigidArea(new Dimension(16,0)));
        getContentPane().add(profilesP);

        getContentPane().add(Box.createRigidArea(new Dimension(0,16)));
        JPanel cancelOrOkP = new JPanel();
        cancelOrOkP.setLayout(new BoxLayout(cancelOrOkP, BoxLayout.X_AXIS));
        JButton cancelB = new JButton("Cancel");
        JButton startB = new JButton("Start");
        cancelB.addActionListener(this);
        startB.addActionListener(this);
        startB.addActionListener(ownerFrame);
        cancelOrOkP.add(Box.createRigidArea(new Dimension(16,0)));
        cancelOrOkP.add(cancelB);
        cancelOrOkP.add(Box.createHorizontalGlue());
        cancelOrOkP.add(startB);
        cancelOrOkP.add(Box.createRigidArea(new Dimension(16,0)));
        getContentPane().add(cancelOrOkP);
        getContentPane().add(Box.createRigidArea(new Dimension(16,16)));
        
        setModal(true);
        setTitle("New Game");
        pack();
        setResizable(false);
    }

    public void actionPerformed(ActionEvent ae) {
        if ("Cancel".equals(ae.getActionCommand()) || "Start".equals(ae.getActionCommand())) {
	        setVisible(false);
        } else {
            JComboBox test_CB = (JComboBox)ae.getSource();
            if("Human".equals(test_CB.getSelectedItem())) {
                if("cb1".equals(test_CB.getName())) {
                    playerNameTFs[1].setEditable(true);
                    playerNameTFs[1].setText("Player 2");
                } else if("cb2".equals(test_CB.getName())) {
                    playerNameTFs[2].setEditable(true);
                    playerNameTFs[2].setText("Player 3");
                } else if("cb3".equals(test_CB.getName())) {
                    playerNameTFs[3].setEditable(true);
                    playerNameTFs[3].setText("Player 4");
                }
            } else if("Computer".equals(test_CB.getSelectedItem())) {
                if("cb1".equals(test_CB.getName())) {
                    playerNameTFs[1].setText("CPU Player 2");
                    playerNameTFs[1].setEditable(false);
                } else if("cb2".equals(test_CB.getName())) {
                    playerNameTFs[2].setText("CPU Player 3");
                    playerNameTFs[2].setEditable(false);
                } else if("cb3".equals(test_CB.getName())) {
                    playerNameTFs[3].setText("CPU Player 4");
                    playerNameTFs[3].setEditable(false);
                }
            } else if("Inactive".equals(test_CB.getSelectedItem())) {
                if("cb1".equals(test_CB.getName())) {
                    playerNameTFs[1].setEditable(false);
                    playerNameTFs[1].setText("Inactive");
                } else if("cb2".equals(test_CB.getName())) {
                    playerNameTFs[2].setEditable(false);
                    playerNameTFs[2].setText("Inactive");
                } else if("cb3".equals(test_CB.getName())) {
                    playerNameTFs[3].setEditable(false);
                    playerNameTFs[3].setText("Inactive");
                }
            }
        }
    }
	
    public boolean getRule(int arr) {
        return rulesBs[arr].isSelected();
    }
    public String getProfile() {
        String profile = "";
        for(int cnt = 0; cnt < profileConcats.length; cnt++) {
            if(profileConcats[cnt] == profileSelectorCB.getSelectedItem()) {
                profile = "../profiles/" + profileConcats[cnt].substring(profileConcats[cnt].indexOf("( ") + 2, profileConcats[cnt].indexOf(" )"));
                cnt = profileConcats.length;
            }
        }
        return profile;
    }
    public int[] getPlayerInfo() {
        int[] toReturn = new int[5];
        int ammoPlayers = 0;
        for (int at = 0; at < 4; at ++) {
            if (playerNameTFs[at].isEditable()) {
                ammoPlayers++; /* human */
                toReturn[at] = 1; 
            } else if (playerNameTFs[at].getText().compareTo( "Inactive") != 0) {
                ammoPlayers++; /* computer */
                toReturn[at] = 2;
            }
        }
        toReturn[4] = ammoPlayers;
        return toReturn;
    }
    public String getPlayerName(int arr) {
        return playerNameTFs[arr].getText();
    }
}
