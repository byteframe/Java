// Player.java
//
// this struct stores everything an attentive particpiant would keep track of:
// their pos on the board, their funds, jail time. name is for the program...

package game;

public class Player {
	private String name;
	private int funds;
	private int boardPos;
	private boolean bIsJailed, bEasyTicket, bHardTicket;

    public Player(String in_name) {
      name = in_name;
      funds = 1500;
    }
    
    public String getName() { return name; }
    public int getFunds() { return funds; }
    public int getBoardPos() { return boardPos; }
    public boolean isJailed() { return bIsJailed; }
    public boolean isEasyTicket() { return bEasyTicket; }
    public boolean isHardTicket() { return bHardTicket; }

    public void setFunds(int in_funds) { funds = in_funds; }
    public void decFunds(int toDec) { funds -= toDec; }
    public void incFunds(int toInc) { funds += toInc; }
    public void setBoardPos(int in_boardPos) { boardPos = in_boardPos; }
    public void setJailed(boolean inbool) { bIsJailed = inbool; }
    
    public void moveForward(int to_move) {
        boardPos += to_move;
        if (boardPos > 39) {
            boardPos = boardPos - 40; 
        }
    }
}
