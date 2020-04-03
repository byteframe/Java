// GameContainer.java
//
// VooDoo...
//

package client.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import client.GameClient;
import common.*;

public class GameContainer extends JPanel implements Observer {

    protected final Image imageToken = makeIcon("/image/Bean24.gif").getImage();
    private final Font fontGameButton = new Font("SansSerif", Font.BOLD, 10);
    private final Font fontTextArea = new Font("Monospaced", Font.PLAIN, 10);
    private final Insets insetsNone = new Insets(0,0,0,0);

    protected GameClient client;
    protected GameState gameState;
    protected Deck hand = new Deck();
    protected Card lsc;
    private DefaultComboBoxModel comboBoxModel;
    private PlayersTableModel players;

    protected JButton buttonCancel;
    private JButton buttonDiscard;
    private JButton buttonDrawCard;
    private JButton buttonEndTurn;
    private JButton buttonMoveCard;
    private JButton buttonPlayCard;
    private JButton buttonSendChat;
    private JButton buttonToggleChat;
    private JButton buttonToken;
    protected CardPanel cardPanel;
    private JComboBox comboBox;
    private JLabel labelGameName;
    private JPanel panelChat;
    private JSplitPane splitPane;
    private JTable tablePlayers;
    private TableSideScrollPane tableSideHim;
    private TableSideScrollPane tableSideYou;
    private JTabbedPane tabPane;
    private JTextArea textAreaChatAll;
    private JTextField textField;

    public GameContainer() {
        super(new GridBagLayout());
        comboBoxModel = new DefaultComboBoxModel();
        players = new PlayersTableModel();
        buttonCancel = makeGameActionButton("Cancel",
          makeIcon("/image/Stop16.gif"));
        buttonDiscard = makeGameActionButton("Discard",
          makeIcon("/image/Import16.gif"));
        buttonDrawCard = makeGameActionButton("Draw Card",
          makeIcon("/image/Export16.gif"));
        buttonEndTurn = makeGameActionButton("End Turn",
          makeIcon("/image/Redo16.gif"));
        buttonMoveCard = makeGameActionButton("Move",
          makeIcon("/image/FindAgain16.gif"));
        buttonPlayCard = makeGameActionButton("Play",
          makeIcon("/image/Play16.gif"));
        buttonSendChat = new JButton("Send");
        buttonToggleChat = new JButton("Show Chat");
        buttonToken = makeGameActionButton("Token",
          makeIcon("/image/BeanAdd16.gif"));
        cardPanel = new CardPanel();
        cardPanel.setMinimumSize(new Dimension(160, 224));
        cardPanel.setPreferredSize(cardPanel.getMinimumSize());
        comboBoxModel.addElement("All");
        comboBox = new JComboBox(comboBoxModel);
        labelGameName = new JLabel("labelGameName", SwingConstants.CENTER);
        labelGameName.setBorder(BorderFactory.createBevelBorder(1));
        tablePlayers = new JTable(players);
        tablePlayers.setDragEnabled(false);
        tablePlayers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePlayers.getTableHeader().setReorderingAllowed(false);
        tableSideHim = new TableSideScrollPane(this);
        tableSideYou = new TableSideScrollPane(this);
        tabPane = new JTabbedPane();
        tabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        textAreaChatAll = new JTextArea();
        textAreaChatAll.setEditable(false);
        textAreaChatAll.setFont(fontTextArea);
        textAreaChatAll.setLineWrap(true);
        tabPane.addTab("All", new JScrollPane(textAreaChatAll));
        textField = new JTextField();
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setEnabled(false);
        splitPane.setBorder(BorderFactory.createEmptyBorder());
        splitPane.setDividerLocation(0);
        splitPane.setDividerSize(0);
        splitPane.setResizeWeight(0.5);
        splitPane.setMinimumSize(new Dimension(500, 500));
        splitPane.setLeftComponent(tableSideHim);
        splitPane.setRightComponent(tableSideYou);
        panelChat = new JPanel(new GridBagLayout()) {
            {
                setMinimumSize(new Dimension(180, 100));
                setVisible(false);

                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.gridwidth = 2;
                c.insets = new Insets(0, 0, 4, 0);
                c.weightx = 1;
                c.weighty = 1;
                add(tabPane, c);

                c.gridy = 1;
                c.weighty = 0;
                add(textField, c);

                c.gridy++;
                c.gridwidth = GridBagConstraints.RELATIVE;
                c.insets = new Insets(0, 0, 0, 4);
                add(comboBox, c);

                c.gridx = 1;
                c.insets = new Insets(0, 0, 0, 0);
                c.weightx = 0;
                add(buttonSendChat, c);
            }
        };

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(0, 4, 2, 0);
        c.weighty = 1;
        add(new JPanel(new GridBagLayout()) {
            {
                setMinimumSize(cardPanel.getMinimumSize());

                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.insets = new Insets(4, 0, 0, 0);
                c.weightx = 1;
                add(cardPanel, c);

                c.fill = GridBagConstraints.BOTH;
                c.gridy = 1;
                c.weighty = 1;
                add(new JScrollPane(new JPanel(new GridBagLayout()) {
                    {
                        GridBagConstraints c = new GridBagConstraints();
                        c.insets = new Insets(0, 0, 4, 0);
                        add(buttonMoveCard, c);
                        c.gridy = 1;
                        add(buttonPlayCard, c);
                        c.gridy++;
                        add(buttonToken, c);
                        c.gridy++;
                        add(buttonDiscard, c);
                        c.gridy++;
                        add(buttonDrawCard, c);
                        c.gridy++;
                        add(buttonEndTurn, c);
                        c.gridy++;
                        add(buttonCancel, c);
                    }
                }), c);

                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridy++;
                c.weighty = 0;
                add(labelGameName, c);

                c.gridy++;
                add(tablePlayers.getTableHeader(), c);

                c.gridy++;
                c.insets = new Insets(0, 0, 4, 0);
                add(tablePlayers, c);

                c.gridy++;
                c.insets = new Insets(0, 0, 0, 0);
                add(buttonToggleChat, c); 
            }
        }, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.insets = new Insets(4, 4, 2, 4);
        c.weightx = 1;
        add(splitPane, c);

        c.fill = GridBagConstraints.VERTICAL;
        c.gridx++;
        c.weightx = 0;
        c.insets = new Insets(4, 0, 2, 4);
        add(panelChat, c);
    }
    public GameContainer(GameClient c) {
        this();
        client = c;
        client.addObserver(this);
        update(client, client.isConnected());
        GameAction gameAction = new GameAction();
        buttonCancel.addActionListener(gameAction);
        buttonDiscard.addActionListener(gameAction);
        buttonDrawCard.addActionListener(gameAction);
        buttonEndTurn.addActionListener(gameAction);
        buttonMoveCard.addActionListener(gameAction);
        buttonToken.addActionListener(gameAction);
        buttonPlayCard.addActionListener(gameAction);
        SendChatAction sendChatAction = new SendChatAction();
        buttonSendChat.addActionListener(sendChatAction);
        buttonToggleChat.addActionListener(new ToggleChatAction());
        textField.addActionListener(sendChatAction);
        tabPane.addChangeListener(new TabChange());
        tablePlayers.getSelectionModel().
          addListSelectionListener(new TableListSelection());
    }

    class GameAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("End Turn")) {
                client.endTurn();
            } else if (e.getActionCommand().equals("Discard")) {
                client.discardCard(gameState.indexOfCard(lsc,
                  gameState.indexOfPlayer(client.getUsername())));
                buttonDiscard.setVisible(false);
            } else if (e.getActionCommand().equals("Draw Card")) {
                client.drawCard();
            } else if (e.getActionCommand().equals("Move")) {
                tableSideYou.setSelectionMode(2);
                hideGameButtons();
                buttonCancel.setVisible(true);
            } else if (e.getActionCommand().equals("Play")) {
                tableSideYou.setSelectionMode(1);
                hideGameButtons();
                buttonCancel.setVisible(true);
            } else if (e.getActionCommand().equals("Token")) {
                client.token(gameState.indexOfCard(lsc, 
                  gameState.indexOfPlayer(client.getUsername())));
            } else if (e.getActionCommand().equals("Cancel")) {
                tableSideYou.setSelectionMode(0);
                tableSideYou.repaint();
                buttonCancel.setVisible(false);
                buttonEndTurn.setVisible(true);
                if (tableSideYou.getPlayer().getDeckSize() > 0) {
                    buttonDrawCard.setVisible(true);
                }
                if (hand.getCards().contains(lsc)) {
                    buttonPlayCard.setVisible(true);
                } else if (gameState.getCards(gameState.indexOfPlayer(client.getUsername())).contains(lsc)) {
                    buttonDiscard.setVisible(true);
                    buttonMoveCard.setVisible(true);
                    buttonToken.setVisible(true);
                }
            }
        }
    }

    class SendChatAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!textField.getText().equals("")) {
                String p = (String)comboBoxModel.getSelectedItem();
                if (!p.equals("All")) {
                    client.chat(textField.getText(), p);
                } else {
                    client.chat(textField.getText());
                }
                textField.setText(null);
            }
        }
    }

    class TabChange implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            int ti = tabPane.getSelectedIndex();
            comboBoxModel.setSelectedItem(tabPane.getTitleAt(ti));
            tabPane.setBackgroundAt(ti, tabPane.getBackground());
            if (comboBoxModel.getIndexOf(tabPane.getTitleAt(ti)) > -1) {
                textField.setEnabled(true);
                comboBox.setEnabled(true);
            } else {
                textField.setEnabled(false);
                comboBox.setEnabled(false);
            }
        }
    }

    class TableListSelection implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            int i = tablePlayers.getSelectedRow();
            if (!e.getValueIsAdjusting() && i > -1) {
                String p = 
                  ((String)players.getValueAt(i, 0)).replaceAll("[* ]", "");
                if (!p.equals(client.getUsername())) {
                    tableSideHim.setPlayer(gameState.getPlayer(i));
                    splitPane.setDividerLocation(0.5);
                    splitPane.setDividerSize(2);
                } else {
                    splitPane.setDividerLocation(0.0);
                    splitPane.setDividerSize(0);
                }
            }
        }
    }

    class ToggleChatAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (panelChat.isVisible()) {
                panelChat.setVisible(false);
                buttonToggleChat.setText("Show Chat");                
            } else {
                panelChat.setVisible(true);
                buttonToggleChat.setText("Hide Chat");
            }
        }
    }

    private void hideGameButtons() {
        buttonCancel.setVisible(false);
        buttonDiscard.setVisible(false);
        buttonDrawCard.setVisible(false);
        buttonEndTurn.setVisible(false);
        buttonMoveCard.setVisible(false);
        buttonPlayCard.setVisible(false);
        buttonToken.setVisible(false);
    }

    private JButton makeGameActionButton(String n, ImageIcon i) {
        JButton j = new JButton(n, i);
        j.setActionCommand(n);
        j.setFont(fontGameButton);
        j.setMargin(insetsNone);
        return j;
    }

    private ImageIcon makeIcon(String u) {
        return new ImageIcon(getClass().getResource(u));
    }

    protected void selectCard(int c) {
        lsc = gameState.getCard(
          c, gameState.indexOfPlayer(client.getUsername()));
        buttonDiscard.setText("Discard " + lsc.getName());
        buttonDiscard.setVisible(true);
        buttonMoveCard.setText("Move " + lsc.getName());
        buttonMoveCard.setVisible(true);
        buttonPlayCard.setVisible(false);
        buttonToken.setVisible(true);
        buttonToken.setText("Token " + lsc.getName());
        tableSideYou.repaint();
    }

    protected void selectHandCard(int h) {
        lsc = hand.getCard(h);
        buttonDiscard.setVisible(false);
        buttonMoveCard.setVisible(false);
        buttonPlayCard.setText("Play " + lsc.getName());
        buttonPlayCard.setVisible(true);
        buttonToken.setVisible(false);
        tableSideYou.repaint();
    }

    public void update(Observable obs, Object o) {
        if (o instanceof String[]) {
            String[] s = (String[])o;
            if (s[0].endsWith("error")) {
                JOptionPane.showMessageDialog(this, s[1], "Error",
                  JOptionPane.ERROR_MESSAGE);
            } else if (s[0].endsWith("chat")) {
                if (s[1].equals("#")) {
                    textAreaChatAll.append(s[2] + "\n");
                    if (tabPane.getSelectedIndex() > 0) {
                        tabPane.setBackgroundAt(0, new Color(255,255,225));
                    }
                } else {
                    if (tabPane.indexOfTab(s[1]) < 0) {
                        JTextArea newTextArea = new JTextArea();
                        newTextArea.setEditable(false);
                        newTextArea.setLineWrap(true);
                        newTextArea.setFont(fontTextArea);
                        tabPane.addTab(s[1], new JScrollPane(newTextArea));
                    }
                    if (s[2].substring(0, s[2].indexOf(":")).equals(client.getUsername())) {
                        tabPane.setSelectedIndex(tabPane.getTabCount()-1);
                    } else if (tabPane.getSelectedIndex() !=
                      tabPane.indexOfTab(s[1])) {
                        tabPane.setBackgroundAt(
                          tabPane.indexOfTab(s[1]), new Color(255,255,225));
                    }
                    JViewport viewPort = ((JScrollPane)tabPane.
                      getComponentAt(tabPane.indexOfTab(s[1]))).getViewport();
                    ((JTextArea)viewPort.getView()).append(s[2] + "\n");
                }
            } else if (s[0].endsWith("reqin")) {
                String[] v = s[2].split("[,]");
                String i = (String)JOptionPane.showInputDialog(this, s[1],
                  "Input", JOptionPane.QUESTION_MESSAGE, null, v, v[0]);
                if (i != null) {
                    client.send("2deckchoice$" + i);
                } else {
                    client.disconnect();
                }
            } else if (s[0].equals("2name")) {
                labelGameName.setText(s[1]);
            }
        } else if (o instanceof Deck) {
            int oldSize = hand.getSize();
            hand = (Deck)o;
            if (oldSize < hand.getSize()) {
                selectHandCard(hand.getSize()-1);
            } else if (hand.getCards().contains(lsc)) {
                selectHandCard(hand.getCards().indexOf(lsc));
            }
            tableSideYou.setHand(hand);
        } else if (o instanceof GameState) {
            Player playerTurn = null;
            if (gameState != null) {
                for (Player p : gameState.getPlayers()) { //mill
                    if (p.getState() == 3) {
                        playerTurn = p;
                        break;
                    }
                }
            }
            gameState = (GameState)o;
            String lsp = (String)comboBoxModel.getSelectedItem();
            comboBoxModel.removeAllElements();
            comboBoxModel.addElement("All");
            players.setData(gameState.getPlayers());
            boolean bNewPlayer = false;
            for (Player p : gameState.getPlayers()) {
                if (p.getUsername().equals(client.getUsername())) {
                    if (p.getState() == 3) {
                        if (p.getDeckSize() > 0) {
                            buttonDrawCard.setVisible(true);
                        } else {
                            buttonDrawCard.setVisible(false);
                        }
                        buttonEndTurn.setVisible(true);
                        int lsci = gameState.indexOfCard(
                          lsc, gameState.indexOfPlayer(p));
                        if (lsci > -1) {
                            selectCard(lsci);
                        } else {
                            buttonDiscard.setVisible(false);
                            buttonMoveCard.setVisible(false);
                            buttonToken.setVisible(false);
                        }
                        playerTurn = p;
                    }
                    tableSideYou.setPlayer(p);
                } else {
                    comboBoxModel.addElement(p.getUsername());
                    if (p.getUsername().equals(lsp)) {
                        comboBoxModel.setSelectedItem(p.getUsername());
                    }
                    if (p.getUsername().equals(
                        tabPane.getTitleAt(tabPane.getSelectedIndex()))) {
                        textField.setEnabled(false);
                    }
                    if (p.getState() == 3 && !p.equals(playerTurn)) {  
                        if (lsc != null) {
                            lsc = null;
                        }
                        hideGameButtons();
                        bNewPlayer = true;
                        playerTurn = p;
                        tablePlayers.getSelectionModel().
                          setSelectionInterval(0, gameState.indexOfPlayer(p));
                    }
                }
            }
            if (gameState.getPlayers().contains(tableSideHim.getPlayer())
             && !bNewPlayer) {
                tablePlayers.getSelectionModel().setSelectionInterval(0, 
                  gameState.indexOfPlayer(tableSideHim.getPlayer()));
            } else if (gameState.getPlayers().size() > 1) {
                int p = gameState.indexOfPlayer(tableSideYou.getPlayer());
                if (p+1 < gameState.getPlayers().size()) {
                    p++;
                } else {
                    p--;
                }
                tablePlayers.getSelectionModel().setSelectionInterval(0, p);
            } else {
                Player pnull = null;
                tableSideHim.setPlayer(pnull);
                splitPane.setDividerLocation(0.0);
                splitPane.setDividerSize(0);
            }
        } else if (o instanceof Integer) {
            int imageId = ((Integer)o).intValue();
            if (imageId == 0) {
                cardPanel.setImageBack(client.images.get(0));
            } else {
                tableSideHim.repaint();
                tableSideYou.repaint();
            }
	    } else if (o instanceof Boolean) {
            boolean b = ((Boolean)o).booleanValue();
            buttonSendChat.setEnabled(b);
            buttonToggleChat.setEnabled(b);
            cardPanel.setEnabled(b);
            comboBox.setEnabled(b);
            tablePlayers.setEnabled(b);
            tabPane.setEnabled(b);
            textField.setEnabled(b);
            textField.setText(null);
            textAreaChatAll.setEnabled(b);
            textAreaChatAll.setText(null);
            if (!b) {
                hideGameButtons();
                cardPanel.setImage(null);
                comboBoxModel.removeAllElements();
                labelGameName.setText("Disconnected");
                players.clear();
                splitPane.setDividerLocation(0.0);
                splitPane.setDividerSize(0);
                tableSideHim.setHand(null);
                tableSideYou.setHand(null);
                tableSideHim.setPlayer(null);
                tableSideYou.setPlayer(null);
                for (int i = tabPane.getTabCount() - 1; i > 0; i--) {
                    tabPane.removeTabAt(i);
                }
            }
        }
    }
}
