// EditorClient.java
//
//
//

package client;

import java.io.*;
import java.util.*;
import common.Card;
import common.Deck;

public class EditorClient extends PlaymatClient {

    private Deck cards;
    private Vector<Deck> decks;

    public void connect(String host, int port) throws IOException {
        super.connect(host, port);
        send("1editor");
    }

    public void handle(Object o) {
        if (o instanceof Vector) {
            decks = (Vector<Deck>)o;

            setChanged();
            notifyObservers(decks);
        } else if (o instanceof Deck) {
            cards = (Deck)o;

            setChanged();
            notifyObservers(cards);
        } else {
            super.handle(o);
        }
    }

    // move card from pool into deck
    public void addCard(int c, int d) {
        send("3acard$" + c + "$" + d);
    }

    // create a new empty deck
    public void newDeck(String n) {
        send("2newdeck$" + n);
    }

    // move card from a deck back to the card pool
    public void removeCard(int c, int d) {
        send("3rcard$" + c + "$" + d);
    }

    // move all cards in a deck back to the card pool
    public void removeDeck(int d) {
        send("2rdeck$" + d);
    }    
    
    // change the name of a deck
    public void renameDeck(int d, String n) {
        send("3rndeck$" + d + "$" + n);
    }
}
