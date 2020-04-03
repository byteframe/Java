// GameState.java
//
//
//

package common;

import java.io.*;
import java.util.*;

public class GameState implements Serializable {

    private Vector<Vector<GameCard>> cards;
    private Vector<Player> players;

    public GameState() {
        cards = new Vector<Vector<GameCard>>();
        players = new Vector<Player>();
    }

    public synchronized void addPlayer(String n) {
        players.add(new Player(n));
        cards.add(new Vector<GameCard>());
    }
    public synchronized void addPlayer(String n, int c) {
        players.add(c, new Player(n));
        cards.add(new Vector<GameCard>());
    }

    public synchronized void addCard(GameCard c, int p) {
        cards.get(p).add(c);
    }

    public GameCard getCard(int c, int p) { return cards.get(p).get(c); }
    public Vector<GameCard> getCards(int p) { return cards.get(p); }
    public Player getPlayer(int i) { return players.get(i); }
    public Vector<Player> getPlayers() { return players; }

    public int indexOfPlayer(String n) {
        for (int p = 0; p < players.size(); p++) {
            if (players.get(p).getUsername().equals(n)) {
                return p;
            }
        }
        return -1;
    }
    public int indexOfPlayer(Player p) {
        return players.indexOf(p);
    }

    public int indexOfCard(Card c, int p) {
        return cards.get(p).indexOf(c);
    }

    public synchronized void removeCard(GameCard c, int p) {
        cards.get(p).remove(c);
    }

    public synchronized void removePlayer(int p) {
        players.remove(p);
        cards.remove(p);
    }
}
