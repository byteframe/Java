// Utility.java
//
// inherits from Place, but does not have any extra variables
// i quess this is use for an overloaded constructor, and overloaded getCost

package game;

import game.Place;

public class Utility extends Property {
    {
        placeType = 2;
    }
    public Utility() { super();}
    public Utility(String in_title, int in_cost) { super(in_title, in_cost); }

    public int findBill(int diceRoll) { return 4545; }
}
