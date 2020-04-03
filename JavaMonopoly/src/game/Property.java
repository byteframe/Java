// Property.java
//
//
//

package game;

public abstract class Property extends Place {
    protected Player owner;
    protected boolean bMorgaged;
    protected int cost;
    
    public Property() { super(); } 
    public Property(String in_title, int in_cost) {
      super(in_title);
      cost = in_cost;
    }

    public Player getOwner() { return owner; }
    public boolean isMorgaged() { return bMorgaged; }
    public int getCost() { return cost; }
    
    public int findBill() { return 0; }
    public int findMorgageValue() { return cost / 2; }
}
