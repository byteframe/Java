// PlaymatClient.java
//
//
//

package client;

import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;

public class PlaymatClient extends Client {

    public Vector<BufferedImage> images;
    private int imageIndex;
    private char[] password;
    private String username;

    public PlaymatClient() {
        images = new Vector<BufferedImage>();
        password = "Password".toCharArray();
        username = "Username";
    }

    public void connect(String host, int port) throws IOException {
        super.connect(host, port);
        send("3auth$" + getUsername() + "$" + String.valueOf(password));
        java.util.Arrays.fill(password, '0');
    }

    public void disconnect() {
        super.disconnect();
        images.clear();
    }

    public String getUsername() { return username; }

    public void handle(Object o) {
        if (o instanceof String) {
            int args = Integer.parseInt(String.valueOf(((String)o).charAt(0)));
            String[] s = ((String)o).split("[$]", args);

            if (s[0].equals("2image")) {
                imageIndex = Integer.parseInt(s[1]);
                if (imageIndex > images.size()-1) {
                    images.setSize(imageIndex+1);
                }
            } else if (s[0].equals("2error")) {
                System.out.println("error: " + s[1]);

                setChanged();
                notifyObservers(s);
            } else if (s[0].endsWith("3chat")) {
                if (s[1].equals("#")) {
                    System.out.println(s[2]);
                } else {
                    String u = s[2].substring(0, s[2].indexOf(":"));
                    if (!u.equals(username)) {
                        System.out.println("(private) " + s[2]);
                    }
                }

                setChanged();
                notifyObservers(s);
            }
        } else if (o instanceof byte[]) {
            try {
                images.set(imageIndex,
                  ImageIO.read(new ByteArrayInputStream((byte[])o)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            setChanged();
            notifyObservers(new Integer(imageIndex));
        }
    }

    public void setUsername(String u) { username = u; }
    public void setPassword(char[] p) { password = p; }
}
