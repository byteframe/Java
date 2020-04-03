// RunningGame.java
//
// class that stores information and functions regarding players, propertys, etc
// deals with logic for the gui objects in the game from JavaMonopoly

package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import gui.MainFrame;
import gui.NewGameDialog;

import game.RealEstate;

public class RunningGame {
    private boolean[] rules;
    private Player[] players;
    private Random pairOfDice;
    private int lastRolled, playerTurn, doublesThrown, pos;
    private Place[] places;
    private Card[] easyDeck;
    private Card[] hardDeck; 
    private MainFrame mainframe;
    private NewGameDialog newgamedialog;   

    public RunningGame(NewGameDialog n, MainFrame m) {
        mainframe = m;
        newgamedialog = n;
        rules = new boolean[3];
        rules[0] = n.getRule(0); 
        rules[1] = n.getRule(1);
        rules[2] = n.getRule(2);
        int[] playerInfo = n.getPlayerInfo();
        players = new Player[playerInfo[4]];
        int plx = 0;
        for (int at = 0; at < 4; at ++) {
           if (playerInfo[at] == 1) {
               players[plx] = new Player(n.getPlayerName(at));
               plx++;
           } else if (playerInfo[at] == 2) {
               players[plx] = new ComputerPlayer(n.getPlayerName(at)); 
               plx++;                                                              
           }                                                        
        }
        pairOfDice = new Random();
        playerTurn = pairOfDice.nextInt(players.length - 1);                  
        try {
           BufferedReader brdr = new BufferedReader(new FileReader(new File(n.getProfile())));  
           String line;
           String[] grepped;
           int placeEle = 0, easyEle = 0, hardEle = 0;
           places = new Place[40];
           //easyDeck = new Card[16];
           //hardDeck = new Card[16];
           for (int cnt = 0; placeEle < 40; cnt++) { // maybe get num of lines?
               line = brdr.readLine().trim();
               grepped = line.substring(2).split(";");
               switch(line.charAt(0)) {
                   case '0':
                       places[placeEle] = new RealEstate(grepped[0], new Integer(grepped[1]), new Integer(grepped[8]), new int[]{new Integer(grepped[2]),new Integer(grepped[3]),new Integer(grepped[4]),new Integer(grepped[5]),new Integer(grepped[6]),new Integer(grepped[7])}); placeEle++; break;
                   case '1':
                       places[placeEle] = new Transportation(grepped[0], new Integer(grepped[1])); placeEle++; break;
                   case '2':
                       places[placeEle] = new Utility(grepped[0], new Integer(grepped[1])); placeEle++; break;
                   case '3':
                       places[placeEle] = new ActionSquare(grepped[0], new Integer(grepped[1])); placeEle++; break;
                   case 'e':
                       //easyDeck[easyEle] = new Card(); easyEle++; break;
                   case 'h':
                       //hardDeck[hardEle] = new Card(); hardEle++; break;
                   default: break;
               }
           }  
        } catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void playGame() {
        boolean bGameOver = false;
        mainframe.getConsole().setText("New Game Started"); 
        if (playerTurn == players.length) {
            playerTurn = 0;
        } else {
            playerTurn++;
        }
        mainframe.getConsole().addLine("It is "+players[playerTurn].getName()+"'s turn.");
        doublesThrown = 0;
        findActions(playerTurn, 0);
    }

    public void rollDice() {
        int die1 = pairOfDice.nextInt(5)+1;
        int die2 = pairOfDice.nextInt(5)+1;
        lastRolled = die1 + die2;
        if (die1 == die2) {
            doublesThrown++;
            mainframe.getConsole().addLine(players[playerTurn].getName()+" rolls a " + lastRolled + " (Doubles!)");   
        } else {
            mainframe.getConsole().addLine(players[playerTurn].getName()+" rolls a " + lastRolled);
        }
        pos = players[playerTurn].getBoardPos() + lastRolled;
        if (pos > 39) {
            pos -= 40; 
        }
        if (doublesThrown == 3) {
            players[playerTurn].setBoardPos(10);
            players[playerTurn].setJailed(true);
            nextTurn();
        } else {
            pos = players[playerTurn].getBoardPos();
            mainframe.getGameBoard().movePlayer(playerTurn, lastRolled);
            if (places[pos].getPlaceType() == 0) {
                RealEstate tempProp = (RealEstate)places[pos];
                findActions(playerTurn, pos);
                if (tempProp.getOwner().getName().length() > 1) {
                    findActions(playerTurn, pos);
                } else if (!tempProp.getOwner().getName().equals(players[playerTurn])) {
                    findActions(playerTurn, pos);
                } else if (!tempProp.isMorgaged()) {
                    players[playerTurn].decFunds(tempProp.findBill());
                    tempProp.getOwner().incFunds(tempProp.findBill());
                    mainframe.getConsole().addLine(players[playerTurn].getName()+" pays " +tempProp.findBill()+" to "+tempProp.getOwner());
                }
            }
        }
        if (doublesThrown == 0) {  
            nextTurn();       
        }
    }

    public void buyProperty() {
        ;
    }

    public void sellProperty() {
        ;
    }

    //String[] buttonNames = {"Roll Dice", "Trading", "Assets", "Use Card", "Pay Fine", "Try for Doubles", "Purchase", "Decline Purchase"};

    public void findActions(int pl, int pos) {
        boolean[] toggles = new boolean[8];
        if (players[pl].isJailed()) {
            if (players[pl].isEasyTicket() || players[pl].isHardTicket()) {
                toggles[3] = true;
            }
            toggles[4] = true;
            toggles[5] = true;
        } else {      
            if (lastRolled == 0) {
                toggles[0] = true;
                toggles[1] = true;
                toggles[2] = true;
            } else if (places[pos].getPlaceType() == 0 ) {
                toggles[6] = true; System.out.println("Got her2e");
                toggles[7] = true;
            }
        }
        mainframe.getActions().setAvailable(toggles);
    }

    public void nextTurn() {
        lastRolled = 0;
        doublesThrown = 0;
        if (playerTurn == players.length-1) {
            playerTurn = 0;
        } else {
            playerTurn++;
        }
    }

    public Place getPlace(int p) { return places[p]; }
    public Player[] getPlayers() { return players; }
}
