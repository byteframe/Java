// ActionSquare.java
//
//
//

package game;

public class ActionSquare extends Place {
    private int actionType;
    {
        placeType = 3;
    }
    public ActionSquare() { super(); } 
    public ActionSquare(String in_title, int in_actionType) {
        super(in_title);
        actionType = in_actionType;
    }
    
    public void findAction() {
        switch(actionType) {
            case 0:
            default: break;
            case 1: // easyCard
            case 2: // hardCard
            case 3: // go
            case 4: // jv
            case 5: // fp
            case 6: // goto
            case 7: // income
            case 8: // luxary
        }
    }
    
    public int getActionType() { return actionType; }
}
