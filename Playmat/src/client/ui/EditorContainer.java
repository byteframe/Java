// EditorContainer.java
//
//
//

package client.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import client.EditorClient;
import common.Card;
import common.Deck;

public class EditorContainer extends JPanel implements Observer {

    private EditorClient client;
    private DefaultComboBoxModel decks;
    private DeckListModel cards;
    private DeckListModel selected;

    private JButton buttonAddCard;
    private JButton buttonNewDeck;
    private JButton buttonRemoveCard;
    private JButton buttonRemoveDeck;
    private JButton buttonRenameDeck;
    private CardPanel cardPanel;
    private JComboBox comboBox;
    private JList listCards;
    private JList listSelected;
    private JTextArea textArea;

    public EditorContainer() {
        super(new GridBagLayout());
        cards = new DeckListModel();
        decks = new DefaultComboBoxModel();
        selected = new DeckListModel();

        buttonAddCard = new JButton(makeIcon("/image/Back16.gif"));
        buttonAddCard.setToolTipText("Add Card");
        buttonNewDeck = new JButton(makeIcon("/image/Add16.gif"));
        buttonNewDeck.setToolTipText("New Deck");
        buttonNewDeck.setPreferredSize(new Dimension(26, 26));
        buttonRemoveCard = new JButton(makeIcon("/image/Forward16.gif"));
        buttonRemoveCard.setToolTipText("Remove Card");
        buttonRemoveDeck = new JButton(makeIcon("/image/Delete16.gif"));
        buttonRemoveDeck.setToolTipText("Remove Deck");
        buttonRemoveDeck.setPreferredSize(new Dimension(26, 26));
        buttonRenameDeck = new JButton(makeIcon("/image/Edit16.gif"));
        buttonRenameDeck.setToolTipText("Rename Deck");
        buttonRenameDeck.setPreferredSize(new Dimension(26, 26));
        comboBox = new JComboBox(decks);
        cardPanel = new CardPanel();
        cardPanel.setMinimumSize(new Dimension(180, 252));
        cardPanel.setPreferredSize(cardPanel.getMinimumSize());
        textArea = new JTextArea();
        textArea.setBackground(null);
        textArea.setBorder(BorderFactory.createEmptyBorder());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        listSelected = new JList(selected);
        listSelected.setName("listSelected");
        listSelected.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listCards = new JList(cards);
        listCards.setName("listCards");
        listCards.setCellRenderer(new CardsCellRenderer());
        listCards.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(3, 3, 2, 0);
        c.weighty = 1;
        add(new JPanel(new GridBagLayout()) {
            {
                setMinimumSize(new Dimension(180, 600));
                setPreferredSize(getMinimumSize());

                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.weightx = 1;
                add(comboBox, c);

                c.gridy = 1;
                add(new JPanel() {
                    {
                        add(buttonNewDeck);
                        add(buttonRenameDeck);
                        add(buttonRemoveDeck);
                    }
                }, c);

                c.gridy++;
                c.fill = GridBagConstraints.BOTH;
                c.weighty = 1;
                add(new JScrollPane(listSelected, 
                  ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), c);
            }
        }, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.weightx = 1;c.weighty = 1;
        add(new JPanel(new GridBagLayout()) {
            {
                setMinimumSize(new Dimension(188, 0));
                setPreferredSize(getMinimumSize());

                GridBagConstraints c = new GridBagConstraints();
                add(cardPanel, c);

                c.gridy = 1;
                add(new JPanel() {
                    {
                        add(buttonAddCard);
                        add(buttonRemoveCard);
                    }
                }, c);

                c.fill = GridBagConstraints.BOTH;
                c.gridy++;
                c.weightx = 1;
                c.weighty = 1;
                add(new JScrollPane(textArea), c);
            }
        }, c);

        c.fill = GridBagConstraints.VERTICAL;
        c.gridx++;
        c.insets = new Insets(3, 3, 2, 3);
        c.weightx = 0;
        add(new JPanel(new GridBagLayout()) {
            {
                setMinimumSize(new Dimension(180, 600));
                setPreferredSize(getMinimumSize());

                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.weightx = 1;
                c.weighty = 1;
                add(new JScrollPane(listCards, 
                  ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), c);
            }
        }, c);
    }
    public EditorContainer(EditorClient c) {
        this();
        client = c;
        client.addObserver(this);
        update(client, client.isConnected());
        buttonAddCard.addActionListener(new AddCardAction());
        buttonNewDeck.addActionListener(new NewDeckAction());
        buttonRemoveCard.addActionListener(new RemoveCardAction());
        buttonRemoveDeck.addActionListener(new RemoveDeckAction());
        buttonRenameDeck.addActionListener(new RenameDeckAction());
        comboBox.addItemListener(new SelectDeckItem());
        listSelected.addMouseListener(new RemoveCardMouse());
        listSelected.addListSelectionListener(new SelectCardList());
        listCards.addMouseListener(new AddCardMouse());
        listCards.addListSelectionListener(new SelectCardList());
    }

    private class AddCardAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            client.addCard(listCards.getSelectedIndex(),
              comboBox.getSelectedIndex());
        }
    }

    private class RemoveCardAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            client.removeCard(listSelected.getSelectedIndex(),
              comboBox.getSelectedIndex());
        }
    }

    private class AddCardMouse extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            int u = listCards.getSelectedIndex();
            int d = comboBox.getSelectedIndex();
            if (e.getClickCount() == 2 && u > -1 && d > -1 &&
              !selected.getDeck().getCards().contains(cards.getCard(u))) {
                client.addCard(u, d);
            }
        }
    }

    private class RemoveCardMouse extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            int s = listSelected.getSelectedIndex();
            int d = comboBox.getSelectedIndex();
            if (e.getClickCount() == 2 && s > -1) {
                client.removeCard(s, d);
            }
        }
    }

    private class NewDeckAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(EditorContainer.this,
              "Deck Name:", "New Deck", JOptionPane.QUESTION_MESSAGE);
            if (input != null) {
                client.newDeck(input);
            }
        }
    }

    private class RemoveDeckAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (JOptionPane.showConfirmDialog(
                EditorContainer.this,
                "Are you sure you want to remove "
                  + comboBox.getSelectedItem() + "?", "Remove Deck",
                  JOptionPane.YES_NO_OPTION) == 0) {
                client.removeDeck(comboBox.getSelectedIndex());
            }
        }
    }

    private class RenameDeckAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = (String)JOptionPane.showInputDialog(
              EditorContainer.this, "Deck Name:", "Rename Deck", 
              JOptionPane.QUESTION_MESSAGE, null, null, 
              ((Deck)comboBox.getSelectedItem()).getName());
            if (input != null) {
                client.renameDeck(comboBox.getSelectedIndex(), input);
            }
        }   
    }

    private class SelectDeckItem implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (listSelected.getSelectedIndex() > -1) {
                    listSelected.clearSelection();
                    if (((Deck)e.getItem()).getSize() < 1) {
                        listCards.setSelectedIndex(0);
                    } else {
                        selected.setData((Deck)e.getItem());
                        listSelected.setSelectedIndex(0);
                    }
                } else {
                    selected.setData((Deck)e.getItem());
                }
                listCards.repaint();
            }
        }
    }

    private class SelectCardList implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String name = ((JList)e.getSource()).getName();
                int c = listCards.getSelectedIndex();
                int s = listSelected.getSelectedIndex();
                if (name.equals("listCards") && c > -1) {
                    Card card = cards.getCard(c);
                    int i = Integer.parseInt(card.getImageId());
                    if (i < client.images.size()) {
                        cardPanel.setImage(client.images.get(i));
                    } else {
                        cardPanel.setImage(null);
                    }
                    textArea.setText(card.getText());
                    buttonRemoveCard.setEnabled(false);
                    if (decks.getSize() > 0 &&
                      !selected.getDeck().getCards().contains(card)) {
                        buttonAddCard.setEnabled(true);
                    } else {
                        buttonAddCard.setEnabled(false);
                    }
                    if (s > -1) {
                        listSelected.clearSelection();
                    }
                } else if (name.equals("listSelected") && s > -1) {
                    Card card = selected.getCard(s);
                    int i = Integer.parseInt(card.getImageId());
                    if (i < client.images.size()) {
                        cardPanel.setImage(client.images.get(i));
                    } else {
                        cardPanel.setImage(null);
                    }
                    textArea.setText(card.getText());
                    buttonAddCard.setEnabled(false);
                    buttonRemoveCard.setEnabled(true);
                    if (c > -1) {
                        listCards.clearSelection();
                    }
                }
            }
        }
    }

    private class CardsCellRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(
          JList l, Object v, int i, boolean bs, boolean bc) {
            JLabel label = 
              (JLabel)super.getListCellRendererComponent(l, v, i, bs, bc);
            if (selected.getDeck().indexOfCard(cards.getCard(i)) > -1) {
                label.setEnabled(false);
            }
            return label;
        }
    }

    private ImageIcon makeIcon(String u) {
        return new ImageIcon(getClass().getResource(u));
    }

    public void update(Observable obs, Object o) {
        if (o instanceof String[]) {
            String[] s = (String[])o;
            if (s[0].equals("2error")) {
                JOptionPane.showMessageDialog(this, s[1], "Error",
                  JOptionPane.ERROR_MESSAGE);
            }
        } else if (o instanceof Integer) {
            int imageId = ((Integer)o).intValue();
            if (imageId == 0) {
                cardPanel.setImageBack(client.images.get(0));
            } else {
                int c = listCards.getSelectedIndex();
                int s = listSelected.getSelectedIndex();
                if (s > -1) {
                    int i = Integer.parseInt(selected.getCard(s).getImageId());
                    if (i == imageId) {
                        cardPanel.setImage(client.images.get(imageId));
                    }
                } else if (c > -1) {
                    int i = Integer.parseInt(cards.getCard(c).getImageId());
                    if (i == imageId) {
                        cardPanel.setImage(client.images.get(imageId));
                    }
                }        
            }
        } else if (o instanceof Vector) {
            Vector<Deck> v = (Vector<Deck>)o;
            Card lsc = null;
            if (listSelected.getSelectedIndex() > -1) {
                lsc = selected.getCard(listSelected.getSelectedIndex());
            } else if (listCards.getSelectedIndex() > -1) {
                lsc = cards.getCard(listCards.getSelectedIndex());
            }
            listCards.clearSelection();
            listSelected.clearSelection();
            Deck lsd = selected.getDeck();
            if (decks.getSize() < v.size()) {
                for (Deck d : v) {
                    if (decks.getIndexOf(d) == -1) {
                        lsd = d;
                    }
                }
            } else if (decks.getSize() > v.size()) {
                int i = decks.getIndexOf(lsd);
                if (i > 0) {
                    lsd = v.get(i-1);
                }
            }
            decks.removeAllElements();
            for (Deck d : v) {
                decks.addElement(d);
                if (lsd.equals(d)) {
                    decks.setSelectedItem(d);
                }
            }
            if (decks.getSize() > 0) {
                if (selected.indexOf(lsc) > -1) {
                    listSelected.setSelectedIndex(selected.indexOf(lsc));
                } else {
                    listCards.setSelectedIndex(cards.indexOf(lsc));
                }
                buttonRemoveDeck.setEnabled(true);
                buttonRenameDeck.setEnabled(true);
            } else {
                selected.clear();
                if (listCards.getSelectedIndex() == -1) {
                    listCards.setSelectedIndex(0);
                }
                buttonAddCard.setEnabled(false);
                buttonRemoveDeck.setEnabled(false);
                buttonRenameDeck.setEnabled(false);
            }
            listCards.repaint();
        } else if (o instanceof Deck) {
            cards.setData((Deck)o);
            listCards.setSelectedIndex(0);
        } else if (o instanceof Boolean) {
            boolean b = ((Boolean)o).booleanValue();
            buttonNewDeck.setEnabled(b);
            cardPanel.setEnabled(b);
            comboBox.setEnabled(b);
            listCards.setEnabled(b);
            listSelected.setEnabled(b);
            textArea.setEnabled(b);
            if (!b) {
                buttonAddCard.setEnabled(b);
                buttonRemoveCard.setEnabled(b);
                buttonRemoveDeck.setEnabled(b);
                buttonRenameDeck.setEnabled(b);
                cardPanel.setImage(null);
                decks.removeAllElements();
                listCards.clearSelection();
                listSelected.clearSelection();
                cards.clear();
                selected.clear();
                textArea.setText(null);
            }
        }
    }
}   
