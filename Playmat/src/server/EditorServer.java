// EditorServer.java
//
//
//

package server;

import java.util.*;
import common.*;
import server.database.Database;

public class EditorServer extends PlaymatServer {

    private Vector<EditorClient> ec;

    public EditorServer(Database d) {
        this(d, "Playmat Editor Server");
    }
    public EditorServer(Database d, String n) {
        this(d, n, "cardimages/");
    }
    public EditorServer(Database d, String n, String i) {
        super(d, n, i);
        ec = new Vector<EditorClient>();
    }

    private class EditorClient {
        Deck cards;
        Vector<Deck> decks;
    }

    protected void disconnectClient(int c) {
        String listing = database.getPlayerDeckListing(getUsername(c));
        if (!listing.equals("")) {
            String[] names = listing.split("[,]");
            for (String n : names) {
                database.removeDeck(getUsername(c), n);
            }
        }
        for (Deck d : ec.get(c).decks) {
            database.addDeck(getUsername(c), d);
        }
        ec.remove(c);
        super.disconnectClient(c);
    }

    protected void handle(Object o, int c) {
        super.handle(o, c);
        if (o instanceof String) {
            int args = Integer.parseInt(String.valueOf(((String)o).charAt(0)));
            String[] s = ((String)o).split("[$]", args);

            if (ec.size() > c && ec.get(c) != null) {
                String u = getUsername(c);
                Vector<Deck> decks = ec.get(c).decks;
                Deck cards = ec.get(c).cards;

                if (s[0].endsWith("acard")) {
                    int ci = Integer.parseInt(s[1]);
                    int di = Integer.parseInt(s[2]);
                    if (ci > -1 && di > -1 &&
                      !decks.get(di).getCards().contains(cards.getCard(ci))) {
                        decks.get(di).addCard(cards.getCard(ci));
                        Collections.sort(decks.get(di).getCards());
                        updateEditorClient(c);
                        sendChat(u + " adds '" + cards.getCard(ci).getName()
                          + "' to deck '" + decks.get(di).getName() + "'", c);
                    } else {
                        send("2error$invalid indices: c" + ci + ",d" + di, c);
                    }
                } else if (s[0].endsWith("newdeck"))  {
                    s[1] = s[1].trim();
                    if (!s[1].matches("[a-zA-Z0-9 ]+")) {
                        send("2error$invalid deck name: " + s[1], c);
                    } else if (isDeckNameTaken(s[1], c)) {
                        send("2error$deck name taken: " + s[1], c);
                    } else {
                        decks.add(new Deck(s[1]));
                        Collections.sort(decks);
                        updateEditorClient(c);
                        sendChat(u + " creates deck '" + s[1] + "'", c);
                    }
                } else if (s[0].endsWith("rcard")) {
                    int ci = Integer.parseInt(s[1]);
                    int di = Integer.parseInt(s[2]);
                    if (ci > -1 && di > -1) {
                        String oldName = decks.get(di).getCard(ci).getName();
                        decks.get(di).removeCard(ci);
                        updateEditorClient(c);
                        sendChat(u + " removes '" + oldName + "' from deck '"
                          + decks.get(di).getName() + "'", c);
                    } else {
                        send("2error$invalid indices: c" + ci + ",d" + di, c);
                    }
                } else if (s[0].endsWith("rdeck")) {
                    int di = Integer.parseInt(s[1]);
                    if (di > -1) {
                        String oldName = decks.get(di).getName();
                        decks.remove(di);
                        updateEditorClient(c);
                        sendChat(u + " removes deck '" + oldName + "'", c);
                    } else {
                        send("2error$invalid deck index: " + di, c);
                    }
                } else if (s[0].endsWith("rndeck")) {
                    int di = Integer.parseInt(s[1]);
                    s[2] = s[2].trim();
                    if (!s[2].matches("[a-zA-Z0-9 ]+")) {
                        send("2error$invalid deck name: " + s[2], c);
                    } else if (isDeckNameTaken(s[2], c)) {
                        send("2error$deck name taken: " + s[2], c);
                    } else {
                        String oldName = decks.get(di).getName();
                        decks.get(di).setName(s[2]);
                        Collections.sort(decks);
                        updateEditorClient(c);
                        sendChat(u + " renames deck '" + oldName + "' to '"
                          + s[2] + "'", c);
                    }
                }
            } else if (s[0].equals("1editor")) {
                ec.add(c, new EditorClient());
                ec.get(c).cards = database.getPlayerCards(getUsername(c));
                ec.get(c).decks = database.getPlayerDecks(getUsername(c));
                Collections.sort(ec.get(c).cards.getCards());
                Collections.sort(ec.get(c).decks);
                ArrayList<String> imageIds = new ArrayList<String>();
                imageIds.add("cardback_small.jpg");
                sendImageData("cardback_small.jpg", 0, c);
                for (Card card : ec.get(c).cards.getCards()) {
                    if (!imageIds.contains(card.getImageId())) {
                        imageIds.add(card.getImageId());
                    }
                    card.setImageId(String.valueOf(
                      imageIds.indexOf((card.getImageId()))));
                }
                for (Deck deck : ec.get(c).decks) {
                    Collections.sort(deck.getCards());
                    for (Card card : deck.getCards()) {
                        card.setImageId(String.valueOf(
                          imageIds.indexOf((card.getImageId()))));
                    }
                }
                send(ec.get(c).cards, c);
                send(ec.get(c).decks, c);
                new Thread(new ImageSendRun(imageIds, c)).start();
            } else if (s[0].equals("1game")) {
                System.out.println(getUsername(c) + " is using game client");
                send("2error$this is an editor server", c);
                super.disconnectClient(c);
            }
        }
    }

    private boolean isDeckNameTaken(String n, int c) {
        for (Deck d : ec.get(c).decks) {
            if (d.getName().equals(n)) {
                return true;
            }
        }
        return false;
    }

    private void updateEditorClient(int c) {    
        resetClientStream(c);
        send(ec.get(c).decks, c);
    }

    private class ImageSendRun implements Runnable {
        
        private int client;
        private ArrayList<String> imageIds;
        
        public ImageSendRun(ArrayList<String> i, int c) {
            client = c;
            imageIds = i;
        }       

        public void run() {
            try {
                for (int u = 1, r = 0; u < imageIds.size(); u++) {
                    sendImageData(imageIds.get(u), u, client);
                    r++;
                    if (r == 10) {
                        resetClientStream(client);
                        r = 0;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("warning: image transfer interrupted");
            }
        }
    }
}
