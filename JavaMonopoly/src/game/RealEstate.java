// RealEstate.java
//
//
//

package game;

import game.Place;

public class RealEstate extends Property {
    private int group, ammoHouses; 
    private int[] rents;
    {
        placeType = 0;
    }
    public RealEstate() {
        super();
        rents = new int[]{0,0,0,0,0,0};             
    }
    public RealEstate(String in_title, int in_cost, int in_group, int[] in_rents) {
      super(in_title, in_cost);
      rents = in_rents;
      group = in_group;
    }
    
    public int getGroup() { return group; }    
    public int getAmmoHouses() { return ammoHouses; }
    public int getRent() { return rents[ammoHouses]; }    
    public int getRent(int findit) { return rents[findit]; }
    public int getBuildCost() { 
        switch(group) { // ugly
            case 1:
            case 2:
                return 50;
            case 3:
            case 4:
                return 100;
            case 5:
            case 6:
                return 150;
            case 7:
            case 8:
                return 200;
            default: 
                return 0;
        }               
    }

    public int findBill() { return rents[ammoHouses];}
    public int findUpgradeCost() { return group * 50; }
}
