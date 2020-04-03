// Server.java
//
//
//

package server;

import java.io.*;
import java.net.*;
import java.util.*;
import server.database.Database;

public class PlaymatServer extends Server {

    protected Database database;
    private String imgDir;
    private String serverName;
    private Vector<User> users;

    public PlaymatServer(Database d) {
        this(d, "Playmat Server");
    }
    public PlaymatServer(Database d, String n) {
        this(d, n, "cardimages/");
    }
    public PlaymatServer(Database d, String n, String i) {
        database = d;
        imgDir = i;
        serverName = n;
        users = new Vector<User>();
    }

    private class User {
        String name = "Username";
    }

    protected void disconnectClient(int c) {
        System.out.println(users.get(c).name + " left");
        users.remove(c);
        super.disconnectClient(c);
    }

    protected String getUsername(int c) { return users.get(c).name; }

    protected void handle(Object o, int c) {
        if (o instanceof String && users.size() <= c || users.get(c) == null) {
            int args = Integer.parseInt(String.valueOf(((String)o).charAt(0)));
            String[] s = ((String)o).split("[$]", args);

            if (s[0].equals("1query")) {
                System.out.println(getClientHost(c) + " ran a query");
                String q = "$" + serverName + "$";
                for (User u : users) {
                    q += u.name + ",";
                }
                send(q, c);
                super.disconnectClient(c);
            } else if (s[0].equals("3admin")) {
                if (database.authorizeAdmin(s[1], s[2])) {
                    System.out.println("admin stopped the server.");
                    System.exit(0);
                } else {
                    System.out.println("bad authentication '" + s[1] + "' from "
                      + getClientHost(c));
                    super.disconnectClient(c);
                }
            } else if (s[0].equals("3auth")) {
                boolean bDuplicateLogin = false;
                for (User u : users) {
                    if (u.name.equals(s[1])) {
                        bDuplicateLogin = true;
                        break;
                    }
                }
                if (bDuplicateLogin) {
                    System.out.println("duplicate login '" + s[1] + "' from " 
                      + getClientHost(c));
                    send("2error$duplicate login", c);
                    super.disconnectClient(c);
                } else if (!database.authorizeUser(s[1], s[2])) {
                    System.out.println("bad authentication '" + s[1] + "' from "
                      + getClientHost(c));
                    send("2error$bad authentication", c);
                    super.disconnectClient(c);
                } else {
                    users.add(c, new User());
                    users.get(c).name = s[1];
                    System.out.println(getClientHost(c)
                      + " authenticated as '" + s[1] + "'");
                    send("2name$" + serverName, c);
                }
            }
        }
    }

    protected void sendChat(String m) {
        System.out.println(m);
        send("3chat$#$" + m);
    }
    protected void sendChat(String m, int c) {
        System.out.println(m);
        send("3chat$#$" + m, c);
    }
    protected void sendChat(String s, String m) {
        System.out.println(m);
        send("3chat$" + s + "$" + m);
    }
    protected void sendChat(String s, String m, int c) {
        System.out.println(m);
        send("3chat$" + s + "$" + m, c);
    }

    protected void sendImageData(String n, int i) {
        for (int c = 0; c < users.size(); c++) {
            sendImageData(n, i, c);
        }
    }
    protected void sendImageData(String n, int i, int c) {
        File imgfile = new File(imgDir + n);
        try {
            FileInputStream fis = new FileInputStream(imgfile);
            send("2image$" + i, c);
            byte[] imgdata = new byte[(int)imgfile.length()];
            fis.read(imgdata);
            fis.close();
            send(imgdata, c);
        } catch (FileNotFoundException e) {
            System.out.println("image not found: " + n);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setImageLocation(String i) {
        if (!i.endsWith("/")) {
            i = i + "/";
        }
        imgDir = i;
    }
}
