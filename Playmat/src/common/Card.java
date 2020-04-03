// Card.java
//
//
//

package common;

import java.io.*;
import java.util.*;

public class Card implements Serializable, Comparable<Card> {

    private int id;
    protected String imageId;
    protected String name;
    protected String text;

    public Card() {
        this("Card.name");
    }    
    public Card(String n) {
        this(n, "Card.text");
    }    
    public Card(String n, String t) {
        this(n, t, "Card.imageId");
    }
    public Card(String n, String t, String ii) {
        this(n, t, ii, new Random().nextInt());
    }
    public Card(String n, String t, String ii, int i) {
        name = n;
        text = t;
        imageId = ii;
        id = i;
    }

    public String getImageId() {  return imageId; }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getText() { return text; }
    public void setImageId(String i) { imageId = i; }

    public int compareTo(Card c) {
        return name.compareToIgnoreCase(c.getName());
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        return this.getId() == ((Card)o).getId();
    }

    public int hashCode() {
        return id;    
    }
}
