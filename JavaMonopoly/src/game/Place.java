// Place.java
//
// for any spot on the board
// 

package game;

import game.Player;

public abstract class Place {
    protected String title;
    protected int placeType = 0;
    
    public Place() { title = "Blank Place"; } 
    public Place(String in_title) { title = in_title; }
    
    //public void 
    
    public String getTitle() { return title; }
    public int getPlaceType() { return placeType; }
}
