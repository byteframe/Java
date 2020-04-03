// Database.java
//
//
//

package server.database;

import java.sql.*;
import java.util.Vector;
import common.Card;
import common.Deck;
import com.mysql.jdbc.exceptions.jdbc4.*;

public class Database {

    private Connection connection;
    private String jdbcUrl;

    public Database() {
        this("localhost", 3306, "playmat", "playmat", "playmat");
    }
    public Database(String host, int port, String db, String user, String pw) {
        jdbcUrl = "jdbc:mysql://" + host + ":" + port  + "/" + db + "?" +
          "user=" + user + "&password=" + pw;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("fatal: mysql driver must be in your classpath");
            System.exit(1);
        }
    }

    public void connect() {
        if (connection != null) {
            disconnect();
        }
        try {
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            System.out.println("fatal: database error : " + e.getMessage());            
        }
        System.out.println("connected to database: "
          + jdbcUrl.substring(jdbcUrl.indexOf("//")+2, jdbcUrl.indexOf("?")));
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean authorizeUser(String u, String p) {
        boolean b = false;
        try {
            CallableStatement portal = connection.prepareCall(
              "{? = CALL UserValidate(?,?)}");
            portal.registerOutParameter(1, Types.BOOLEAN);
            portal.setString(2, u);
            portal.setString(3, p);
            portal.execute();
            b = portal.getBoolean(1);
            portal.close();
        } catch (MySQLNonTransientConnectionException e) {
            connect();
            return authorizeUser(u, p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean authorizeAdmin(String u, String p) {
        boolean b = false;
        try {
            if (authorizeUser(u, p)) {
                CallableStatement portal = connection.prepareCall(
                  "{CALL UserRoleGet(?,?,?)}");
                portal.setString(1, u);
                portal.setString(2, "Admin");
                portal.setInt(3, 1);
                portal.execute();
                ResultSet result = portal.getResultSet();
                result.next();
                if (result.getString(2).equals("Admin")) {
                    b = true;
                }
                portal.close();
            }
        } catch (MySQLNonTransientConnectionException e) {
            connect();
            return authorizeAdmin(u, p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    private Card getCard(String cardId, int q) {
        Card card = null;
        try {
            CallableStatement portal = connection.prepareCall(
              "{CALL CardDataGet(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            portal.setString(1, cardId);
            portal.setString(2, null);
            portal.setString(3, null);
            portal.setString(4, null);
            portal.setString(5, null);
            portal.setString(6, null);
            portal.setString(7, null);
            portal.setString(8, null);
            portal.setString(9, null);
            portal.setString(10, null);
            portal.setString(11, null);
            portal.setString(12, null);
            portal.setString(13, null);
            portal.setInt(14, 0);
            portal.setString(15, null);
            portal.execute();
            ResultSet result = portal.getResultSet();
            result.next();
            String name = result.getString(2);
            String text = name + "\n\n"
              + result.getString(4) + " " + result.getString(3);
            if (!result.getString(12).equals("NULL")) {
                text += "\n\n" + result.getString(12);
            }
            if (!result.getString(13).equals("NULL")) {
                text += "\n\n" + result.getString(13);
            }
            String imageURL = result.getString(15);
            card = new Card(name, text, imageURL, (name+q).hashCode());
            portal.close();
        } catch (MySQLNonTransientConnectionException e) {
            connect();
            return getCard(cardId, q);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }

    public Deck getPlayerCards(String user) {
        Deck cards = new Deck("#cards");
        try {
            CallableStatement portal = connection.prepareCall(
              "{CALL PlayerCardsGet(?,?)}");
            portal.setString(1, user);
            portal.setString(2, null);
            portal.execute();
            ResultSet result = portal.getResultSet();
            while (result.next()) {
                for (int i = 0; i < result.getInt(3); i++) {
                    cards.addCard(getCard(result.getString(2), i));
                }
            }
            portal.close();
        } catch (MySQLNonTransientConnectionException e) {
            connect();
            return getPlayerCards(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public Deck getPlayerDeck(String user, String name) {
        Deck deck = new Deck();
        try {
            CallableStatement portal = connection.prepareCall(
              "{CALL PlayerDecksGet(?,?)}");
            portal.setString(1, user);
            portal.setString(2, name);
            portal.execute();
            ResultSet result = portal.getResultSet();
            while (result.next()) {
                deck.setName(result.getString(2));
                for(int i = 0; i < result.getInt(4); i++) {
                    deck.addCard(getCard(result.getString(3), i));
                }
            }
            portal.close();
        } catch (MySQLNonTransientConnectionException e) {
            connect();
            return getPlayerDeck(user, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deck;
    }

    public String getPlayerDeckListing(String user) {
        String listing = "";
        try {
            CallableStatement portal = connection.prepareCall(
              "{CALL PlayerDecksGet(?,?)}");
            portal.setString(1, user);
            portal.setString(2, null);
            portal.execute();
            ResultSet result = portal.getResultSet();
            while (result.next()) {
                if (!listing.contains(result.getString(2))) {
                    listing += result.getString(2) + ",";
                }
            }
            portal.close();
        } catch (MySQLNonTransientConnectionException e) {
            connect();
            return getPlayerDeckListing(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listing;
    }

    public Vector<Deck> getPlayerDecks(String user) {
        Vector<Deck> decks = new Vector<Deck>();
        String listing = getPlayerDeckListing(user);
        if (!listing.equals("")) {
            String[] names = listing.split("[,]");
            for (String n : names) {
                decks.add(getPlayerDeck(user, n));
            }
        }
        return decks;
    }

    public void addDeck(String user, Deck d) {
        try {
            for (Card c : d.getCards()) {
                CallableStatement portal = connection.prepareCall(
                  "{CALL PlayerDecksUpdate(?,?,?,?)}");
                portal.setString(1, user);
                portal.setString(2, d.getName());
                String[] ctc = c.getText().split("[\n]")[2].split("[ ]");
                portal.setString(3, c.getName() + " " + ctc[1] + " " + ctc[0]);
                portal.setInt(4, 1);
                portal.execute();
                portal.close();
            }
        } catch (MySQLNonTransientConnectionException e) {
            connect();
            addDeck(user, d);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeDeck(String user, String name) {
        try {
            CallableStatement portal = connection.prepareCall(
              "{CALL PlayerDecksDelete(?,?)}");
            portal.setString(1, user);
            portal.setString(2, name);
            portal.execute();
            portal.close();
        } catch (MySQLNonTransientConnectionException e) {
            connect();
            removeDeck(user, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
