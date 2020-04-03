// playmatd.java
//
// This is the 'main method' class for a playmat server instance.
// It parses command line switches, and writes to standard output.

import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import server.*;
import server.database.*;

public class playmatd {

    public static void main(String[] args) {
        System.out.println("playmatd starting ("
          + Calendar.getInstance().getTime() + ")");
        List<String> list = Arrays.asList(args);

        String dbUser = "playmat";
        String dbPass = "playmat";
        String dbUrl = "localhost:3306/playmat";
        int port = 4416;
        if (list.indexOf("-dbuser") > -1
          && list.get(list.indexOf("-dbuser") + 1) != null) {  
            dbUser = list.get(list.indexOf("-dbuser") + 1);        
        }
        if (list.indexOf("-dbpass") > -1
          && list.get(list.indexOf("-dbpass") + 1) != null) {  
            dbPass = list.get(list.indexOf("-dbpass") + 1);        
        }
        if (list.indexOf("-dburl") > -1
          && list.get(list.indexOf("-dburl") + 1) != null) {  
            dbUrl = list.get(list.indexOf("-dburl") + 1);
        }
        try {
            String[] a = dbUrl.split("[:/]");
            Database database = new Database(
              a[0], Integer.parseInt(a[1]), a[2], dbUser, dbPass);
            database.connect();

            PlaymatServer server;
            String host = "0.0.0.0";
            if (list.indexOf("-editor") > -1) {
                port = 4417;
                server = new EditorServer(database);
            } else {
                String name = "Unnamed Game";
                if (list.indexOf("-name") > -1
                  && list.get(list.indexOf("-name") + 1) != null) {
                    name = list.get(list.indexOf("-name") + 1);
                }
                server = new GameServer(database, name);
            }
            if (list.indexOf("-host") > -1
              && list.get(list.indexOf("-host") + 1) != null) {
                host = list.get(list.indexOf("-host") + 1);
            }
            if (list.indexOf("-imgdir") > -1
              && list.get(list.indexOf("-imgdir") + 1) != null) {
                server.setImageLocation(list.get(list.indexOf("-imgdir") + 1));
            }
            if (list.indexOf("-port") > -1
              && list.get(list.indexOf("-port") + 1) != null) {
                port = Integer.parseInt(list.get(list.indexOf("-port") + 1));
            }
            server.bind(port, host);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("fatal: invalid database url");        
        } catch (BindException e) {
            System.out.println("fatal: port " + port + " in use: ");        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
