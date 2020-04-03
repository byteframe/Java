// Transportation.java
//
// inherits from Place, but does not have any extra variables
// i quess this is use for an overloaded constructor, and overloaded getCost

package game;

import game.Place;

public class Transportation extends Property {
    {
        placeType = 1;
    }
    public Transportation() { super(); }
    public Transportation(String in_title, int in_cost) { super(in_title, in_cost); }

    public int findBill(int owned) { return 25*owned; }
}
