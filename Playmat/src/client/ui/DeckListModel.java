// DeckListModel.java
//
//
//

package client.ui;

import javax.swing.*;
import common.Card;
import common.Deck;

public class DeckListModel extends DefaultListModel {

    private Deck deck;
    private final Deck emptyDeck;

    public DeckListModel() {
        emptyDeck = new Deck("null");
        deck = emptyDeck;
    }
    public DeckListModel(Deck d) {
        emptyDeck = new Deck("null");
        deck = d;
    }

    public void clear() {
        setData(emptyDeck);
    }

    public Object getElementAt(int index) {
        try {
            return deck.getCard(index).getName();
        } catch (Exception e) {
            System.out.println(deck.getName() + "FUCK UP size: " + deck.getSize() + ",index: " + index);
        }
        return null;
    }

    public int getSize() { return deck.getSize(); }
    public Deck getDeck() { return deck; }
    public Card getCard(int c) { return deck.getCard(c); }

    public int indexOf(Object c) { return deck.getCards().indexOf(c); }

    public void setData(Deck d) {
        deck = d;
        fireIntervalRemoved(this, 0,  getSize());
        fireIntervalAdded(this, 0, getSize());
    }
}
