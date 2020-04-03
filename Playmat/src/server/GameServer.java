// GameServer.java
//
//
//

package server;

import java.util.*;
import common.*;
import server.database.Database;

public class GameServer extends PlaymatServer {

    private GameState gs;
    private Vector<String> imageIds;
    private Vector<String> invitedUsers;
    private Vector<Deck> userDecks;
    private Vector<Deck> userDiscards;
    private Vector<Deck> userHands;

    public GameServer(Database d) {
        this(d, "Playmat Game Server");
    }
    public GameServer(Database d, String n) {
        this(d, n, "cardimages/");
    }
    public GameServer(Database d, String n, String i) {
        super(d, n, i);
        gs = new GameState();
        imageIds = new Vector<String>();
        imageIds.add("cardback_small.jpg");
        invitedUsers = new Vector<String>();
        userDecks = new Vector<Deck>();
        userDiscards = new Vector<Deck>();
        userHands = new Vector<Deck>();
    }

    protected void disconnectClient(int c) {
        sendChat(gs.getPlayer(c).getUsername() + " stopped playing");
        if (gs.getPlayer(c).getState() == 3) {
            if (c + 1 >= gs.getPlayers().size()) {
                gs.getPlayer(0).setState(3);
            } else {
                gs.getPlayer(c + 1).setState(3);
            }
        }
        gs.removePlayer(c);
        userDecks.remove(c);
        userDiscards.remove(c);
        userHands.remove(c);
        super.disconnectClient(c);
        updateGameClients();
    }

    protected void handle(Object o, int c) {
        super.handle(o, c);
        if (o instanceof String) {
            int args = Integer.parseInt(String.valueOf(((String)o).charAt(0)));
            String[] s = ((String)o).split("[$]", args);

            if (gs.getPlayers().size() > c && gs.getPlayer(c) != null) {
                String u = getUsername(c);
                Player p = gs.getPlayer(c);
                switch(p.getState()) {
                case 3:
                    if (s[0].endsWith("discardcard")) {
                        int g = Integer.parseInt(s[1]);
                        if (g > -1) {
                            GameCard gc = gs.getCard(g, c);
                            gs.removeCard(gc, c);
                            userDiscards.get(c).addCard(gc);
                            p.setDiscardSize(userDiscards.get(c).getSize());
                            p.setTopDiscardedCard(gc);
                            int i = Integer.parseInt(gc.getImageId());
                            sendImageData(imageIds.get(i), i);
                            sendChat(u + " discards " + gc.getName());
                            updateGameClients();
                        } else {
                            send("2error$invalid index g: " + g, c);
                        }
                    } else if (s[0].endsWith("drawcard")) {
                        int size = userDecks.get(c).getSize();
                        if (size > 0) {
                            Card cd = userDecks.get(c).removeCard(size-1);
                            userHands.get(c).addCard(cd);
                            p.setCardDrawn(true);
                            p.setDeckSize(userDecks.get(c).getSize());
                            p.setHandSize(userHands.get(c).getSize());
                            sendChat(u + " draws a card");
                            updateGameClients();
                            int i = Integer.parseInt(cd.getImageId());
                            sendImageData(imageIds.get(i), i, c);
                            send(userHands.get(c), c);
                        } else if (!p.isCardDrawn()) {
                            send("2error$your deck is empty", c);
                        }
                    } else if (s[0].endsWith("endturn")) {
                        p.setState(2);
                        String y = u + " yields to ";
                        if (c + 1 >= gs.getPlayers().size()) {
                            gs.getPlayer(0).setState(3);
                            gs.getPlayer(0).setCardDrawn(false);
                            y += gs.getPlayer(0).getUsername();
                        } else {
                            gs.getPlayer(c + 1).setState(3);
                            gs.getPlayer(c + 1).setCardDrawn(false);
                            y += gs.getPlayer(c + 1).getUsername();
                        }
                        sendChat(y);
                        updateGameClients();
                    } else if (s[0].endsWith("playcard")) {
                        int h = Integer.parseInt(s[1]);
                        int x = Integer.parseInt(s[2]);
                        int y = Integer.parseInt(s[3]);
                        if (!isSpaceTaken(x, y, c)
                          && h > -1 && x > -1 && y > -1) {
                            GameCard gc = new GameCard(
                              userHands.get(c).removeCard(h));
                            gc.setLocation(x, y);
                            gs.addCard(gc, c);
                            p.setHandSize(userHands.get(c).getSize());
                            int i = Integer.parseInt(gc.getImageId());
                            sendImageData(imageIds.get(i), i);
                            sendChat(u + " plays " + gc.getName());
                            updateGameClients();
                            send(userHands.get(c), c);
                        } else {
                            send("2error$invalid indices xyh: " + x + y + h, c);
                        }
                    } else if (s[0].endsWith("movecard")) {
                        int g = Integer.parseInt(s[1]);
                        int x = Integer.parseInt(s[2]);
                        int y = Integer.parseInt(s[3]);
                        if (!isSpaceTaken(x, y, c)
                          && g > -1 && x > -1 && y > -1) {
                            GameCard card = gs.getCards(c).get(g);
                            card.setLocation(x, y);
                            sendChat(u + " moves " + card.getName());
                            updateGameClients();
                        } else {
                            send("2error$invalid indices xyg: " + x + y + g, c);
                        }
                    } else if (s[0].endsWith("token")) {
                        int g = Integer.parseInt(s[1]);
                        if (g > -1) {
                            GameCard card = gs.getCards(c).get(g);
                            card.setTokenCount(card.getTokenCount()+1);
                            sendChat(u + " tokens " + card.getName());
                            updateGameClients(); 
                        } else {
                            send("2error$invalid index g: " + g);
                        }
                    }
                case 2:
                    if (s[0].endsWith("chat")) {
                        if (s[1].equals("#")) {
                            sendChat(u + ": " + s[2]);
                        } else {
                            int target = gs.indexOfPlayer(s[1]);
                            send("3chat$" + s[1] + "$" + u + ": " + s[2], c);
                            send("3chat$" + u + "$" + u + ": " + s[2], target);
                        }
                    }
                    break;
                case 1:
                    if (s[0].endsWith("deckchoice")) {
                        userDecks.set(c, database.getPlayerDeck(getUsername(c),
                          s[1]));
                        p.setDeckSize(userDecks.get(c).getSize());
                        if (gs.getPlayers().size() > 1) {
                            p.setState(2);
                        } else {
                            p.setState(3);
                            p.setCardDrawn(false);
                        }
                        for (Card card : userDecks.get(c).getCards()) {
                            if (!imageIds.contains(card.getImageId())) {
                                imageIds.add(card.getImageId());
                            }
                            card.setImageId(String.valueOf(
                              imageIds.indexOf((card.getImageId()))));
                        }
                        Collections.shuffle(userDecks.get(c).getCards());
                        sendChat(u + " joined the game");
                        updateGameClients();
                    }
                    break;
                }
            } else if (s[0].equals("1game")) {
                String list = database.getPlayerDeckListing(getUsername(c));
                if (list.equals("")) {
                    System.out.println(getUsername(c) + " has no decks");
                    send("2error$you do not have any decks", c);
                    super.disconnectClient(c);
                } else if (invitedUsers.contains(getUsername(c))) { //!!
                    System.out.println(getUsername(c) + " is not invited");
                    send("2error$not invited to this game", c);
                    super.disconnectClient(c);
                } else {
                    sendImageData("cardback_small.jpg", 0, c);
                    for (int b = 0; b < gs.getPlayers().size(); b++) {
                        if (gs.getPlayer(b).getTopDiscardedCard() != null) {
                            int i = Integer.parseInt(gs.getPlayer(b)
                              .getTopDiscardedCard().getImageId());
                            sendImageData(imageIds.get(i), i, c);
                        }
                        for (GameCard gc : gs.getCards(b)) {
                            int i = Integer.parseInt(gc.getImageId());
                            sendImageData(imageIds.get(i), i, c); 
                        }
                    }
                    gs.addPlayer(getUsername(c), c);
                    userDecks.add(c, new Deck("#deck"));
                    userDiscards.add(c, new Deck("#discard"));
                    userHands.add(c, new Deck("#hand"));
                    gs.getPlayer(c).setState(1);
                    send("3reqin$" + "Select Deck$" + list, c);
                }
            } else if (s[0].equals("1editor")) {
                System.out.println(getUsername(c) + " is using editor client");
                send("2error$this is a game server", c);
                super.disconnectClient(c);
            }
        }
    }

    private boolean isSpaceTaken(int x, int y, int c) {
        for (GameCard gc : gs.getCards(c)) {
            if (gc.getX() == x && gc.getY() == y) {
                return true;
            }
        }
        return false;
    }

    private void updateGameClients() {
        for (int c = 0; c < gs.getPlayers().size(); c++) {
            resetClientStream(c);
            send(gs, c);
        }
    }
}
