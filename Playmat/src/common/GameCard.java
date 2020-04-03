// GameCard.java
//
//
//

package common;

import java.awt.Point;
import java.util.Random;

public class GameCard extends Card {

    public enum Orientation { NORTH, SOUTH, EAST, WEST }
    private boolean facingUp;
    private Point location;
    private Orientation orientation;
    private int tokenCount;
    
    public GameCard() {
        this("GameCard.name");
    }  
    public GameCard(String n) {
        this(n, "GameCard.text");
    }    
    public GameCard(String n, String t) {
        this(n, t, "GameCard.imageId");
    }
    public GameCard(String n, String t, String ii) {
        this(n, t, ii, new Random().nextInt());
    }
    public GameCard(String n, String t, String ii, int i) {
        super(n, t, ii, i);
        facingUp = false;
        location = new Point(0, 0);
        orientation = GameCard.Orientation.NORTH;
        tokenCount = 0;
    }
    public GameCard(Card c) {
        this(c.getName(), c.getText(), c.getImageId(), c.getId());
    }
    
    public Orientation getOrientation() { return orientation; }
    public Point getLocation() { return location; }
    public int getTokenCount() { return tokenCount; }
    public int getX() { return location.x; }
    public int getY() { return location.y; }
    public boolean isFacingUp() { return facingUp; }
    
    public void setFacingUp(boolean b) { facingUp = b; }
    public void setLocation(int x, int y) { location.setLocation(x, y); }
    public void setOrientation(Orientation o) { orientation = o; }
    public void setTokenCount(int t) { tokenCount = t; }

    public String toString() {
        return name + "(" + location.x + "," + location.y + ")";
    }
}
