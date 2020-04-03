// GameClient.java
//
//
//

package client;

import java.io.*;
import common.Deck;
import common.GameState;

public class GameClient extends PlaymatClient {

    private GameState gameState;
    private Deck hand;

    public void connect(String host, int port) throws IOException {
        super.connect(host, port);
        send("1game");
    }

    public void handle(Object o) {
        if (o instanceof String) {
            int args = Integer.parseInt(String.valueOf(((String)o).charAt(0)));
            String[] s = ((String)o).split("[$]", args);

            if (s[0].equals("3reqin")) {
                System.out.println(s[1] += "?");

                setChanged();
                notifyObservers(s);
            } else if (s[0].equals("2name")) {
                setChanged();
                notifyObservers(s);   
            } else {
                super.handle(o);
            }
        } else if (o instanceof GameState) {
            gameState = (GameState)o;

            setChanged();
            notifyObservers(gameState);
        } else if (o instanceof Deck) {
            hand = (Deck)o;

            setChanged();
            notifyObservers(hand);
        } else {
            super.handle(o);
        }
    }

    // chat to all players in the game
    public void chat(String m) {
        send("3chat$#$" + m);
    }

    // chat to a single player
    public void chat(String m, String p) {
        send("3chat$" + p + "$" + m);
    }

    // discard a card in play
    public void discardCard(int c) { 
        send("2discardcard$" + c);
    }

    // draw a card from the deck into your hand
    public void drawCard() { 
        send("1drawcard");
    }

    // end your turn and yield to the next player
    public void endTurn() {
        send("1endturn");
    }

    // put a selected card from your hand into play
    public void moveCard(int gi, int x, int y) { 
        send("4movecard$" + gi + "$" + x + "$" + y);
    }

    // put a selected card from your hand into play
    public void playCard(int hi, int x, int y) { 
        send("4playcard$" + hi + "$" + x + "$" + y);
    }

    // put a token on a card
    public void token(int g) {
        send("2token$" + g);
    }
}
