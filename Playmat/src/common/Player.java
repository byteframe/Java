// Player.java
//
//
//

package common;

import java.util.*;
import java.io.*;

public class Player implements Serializable, Comparable<Player> {

    private boolean bCardDrawn;
    private int discardSize;
    private int deckSize;
    private int handSize;
    private int state;
    private String username;
    private Card topDiscardedCard;

    public Player() {
        this("#unauthenticated");
    }

    public Player(String n) {
        bCardDrawn = false;
        discardSize = 0;
        deckSize = 0;
        handSize = 0;
        state = 0; // 0=unathenicated,1=inputneeded,2=notactiveturn,3=activeturn
        topDiscardedCard = null;
        username = n;
    }

    public boolean isCardDrawn() { return bCardDrawn; }
    public int getDiscardSize() { return discardSize; }
    public int getDeckSize() { return deckSize; }
    public int getHandSize() { return handSize; }
    public int getState() { return state; }
    public Card getTopDiscardedCard() { return topDiscardedCard; }
    public String getUsername() { return username; }

    public void setCardDrawn(boolean b) { bCardDrawn = b; }
    public void setDiscardSize(int s) { discardSize = s; }
    public void setDeckSize(int s) { deckSize = s; }
    public void setHandSize(int s) { handSize = s; }
    public void setState(int s) { state = s; }
    public void setTopDiscardedCard(Card t) { topDiscardedCard = t; }
    public void setUsername(String s) { username = s; }

    public int compareTo(Player p) {
        return username.compareToIgnoreCase(p.getUsername());
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getUsername().equals(((Player)o).getUsername())) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return username.hashCode();    
    }

    public String toString() {
        return username;
    }
}
