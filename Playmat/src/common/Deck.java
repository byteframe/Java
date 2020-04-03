// Deck.java
//
//
//

package common;

import java.util.*;
import java.io.*;

public class Deck implements Serializable, Comparable<Deck> {

    private Vector<Card> cards;
    private int id;
    private String name;

    public Deck() {
        this("Deck.name");
    }
    public Deck(String n) {
        this(n, new Random().nextInt());
    }
    public Deck(String n, int i) {
        cards = new Vector<Card>();
        name = n;
        id = i;
    }

    public Card getCard(int c) { return cards.get(c); }
    public Vector<Card> getCards() { return cards; }
    public int getId() { return id; }
    public String getName() { return name; }
    public int getSize() { return cards.size(); }
    public int indexOfCard(Card c) { return cards.indexOf(c); }

    public void addCard(Card c) { cards.add(c); }
    public void combine(Deck d) { cards.addAll(d.getCards()); }
    public Card removeCard(int c) { return cards.remove(c); }
    public void setName(String n) { name = n; }

    public int compareTo(Deck d) {
        return name.compareToIgnoreCase(d.getName());
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        return this.getId() == ((Deck)o).getId();
    }

    public int hashCode() {
        return id;
    }

    public String toString() { return name + " - (" + getSize() + ")"; }
}
