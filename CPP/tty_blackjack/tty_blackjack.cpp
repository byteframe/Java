#include <iostream>
#include "Deck.h"

using namespace std;

void Pause(string, bool);
void DrawBjackTable(Deck&, bool);
short WorkTotal(Deck&, short);
void Game(Deck&);
void Simulation(Deck&);

int main()
{
    Deck bJackDeck;
    short choice;
    do
    {
        system("clear");
        cout << "          TTY BlackJack\n"
             << "---------------------------------\n"
             << "1. Play a game\n"
             << "2. Automatic 2 player Simulation\n"
             << "3. Quit\n"
             << "---------------------------------\n\n"
             << "Select: ";
        cin >> choice;
        if (choice == 1)
            Game(bJackDeck);
        else if (choice == 2)
            Simulation(bJackDeck);
    } while (choice != 3);
    Pause("Press any key to quit the program.", true);
    cout << endl;
    return 0;
}

void Pause(string message, bool willClear)
{
    if (willClear)
        system("clear");
    cout << message;
    cin.ignore(cin.rdbuf() -> in_avail() + 1);
    cin.get();
}

void DrawBjackTable(Deck &bJackDeck, bool reveal)
{
    system("clear");
    cout << "\n\n"
         << "{ Dealer's Cards }\n"
         << "------------------\n";
    for (short cnt = 0; cnt < bJackDeck.getOnTable(); cnt++)
        if (bJackDeck.getCard(cnt).getOwner() == 0)
            if (!bJackDeck.getCard(cnt).getFacingUp() && !reveal)
                cout << "[xxxxx]\n";
            else
                cout << bJackDeck.getCard(cnt).getTitle() << "\n";
    cout << "\n\n"
         << "{ Player's Cards }\n"
         << "------------------\n";
    for (short cnt = 0; cnt < bJackDeck.getOnTable(); cnt++)
        if (bJackDeck.getCard(cnt).getOwner() == 1)
            if (!bJackDeck.getCard(cnt).getFacingUp() && !reveal)
                cout << "[xxxxx]\n";
            else
                cout << bJackDeck.getCard(cnt).getTitle() << "\n";
    cout << "\n\n------------------\n";
}

short WorkTotal(Deck &bJackDeck, short forPlayer)
{
    short aces = 0, total = 0;
    for (short cnt = 0; cnt < bJackDeck.getOnTable(); cnt++) 
        if (bJackDeck.getCard(cnt).getOwner() == forPlayer)
        {
            if (bJackDeck.getCard(cnt).getValue() != 11)
                total += bJackDeck.getCard(cnt).getValue();
            else
            {
                total += bJackDeck.getCard(cnt).getValue();
                aces++;
            }
        }
    while (total > 21 && aces > 0)
    {
        total -= 10;
        aces--;
    }
    return total;
}

void Game(Deck &bJackDeck)
{
    bJackDeck.ShuffleDeck();
    bJackDeck.DrawCard(false, 0);
    bJackDeck.DrawCard(true, 1);
    bJackDeck.DrawCard(true, 0);
    bJackDeck.DrawCard(true, 1);
    short dealerTotal = WorkTotal(bJackDeck, 0);
    if (dealerTotal == 21)
    {
        DrawBjackTable(bJackDeck, true);
        Pause("Dealer got 21 on the flop!", false);
    }
    else
    {
        bool playerWon = false, dealerWon = false, stayed = true;
        short playerTotal = WorkTotal(bJackDeck, 1);
        while (!playerWon && !dealerWon && stayed)
        {
            DrawBjackTable(bJackDeck, false);
     
            if (playerTotal == 21)
            {
                playerWon = true;
                Pause("You have 21!", false);
            }
            else if (playerTotal > 21)
            {
                dealerWon = true;
                Pause("You have busted!", false);
            }
            else
            {
                char hitPlayer;
                cout << "Another card? (y/n): ";
                cin >> hitPlayer;
                if (hitPlayer == 'y')
                {    
                    bJackDeck.DrawCard(true, 1);
                    playerTotal = WorkTotal(bJackDeck, 1);
                }
                else if (hitPlayer == 'n')
                    stayed = false;
            } 
        }
        while (!playerWon && !dealerWon)
        {
            if (dealerTotal > 21)
            {
                playerWon = true;
                DrawBjackTable(bJackDeck, true); 
                Pause("Dealer busted!", false);
            }
            else if (dealerTotal > playerTotal)
            {
                dealerWon = true;
                DrawBjackTable(bJackDeck, true); 
                Pause("Dealer has more...", false);
            }
            else
            {
                DrawBjackTable(bJackDeck, false);
                bJackDeck.DrawCard(true, 0);
                dealerTotal = WorkTotal(bJackDeck, 0);
                Pause("Dealer hits...", false);
            }
        }
    }  
}

void Simulation(Deck &bJackDeck)
{
    short numSim, toStay;
    do
    {
        system("clear");
        cout << "How many simulations would you like to run? (2-30000): ";
        cin >> numSim;
        cout << "What would you like to stay on? (3-20): ";
        cin >> toStay;  
    } while (numSim < 2 || numSim > 30000 || toStay < 3 || toStay > 20);
    system("clear");
    short dealerWins = 0, playerWins = 0, dealerTotal, playerTotal;
    bool playerWon = false, dealerWon = false, stayed = true;
    for (short cnt = 0; cnt < numSim; cnt++)
    {
        bJackDeck.ShuffleDeck();
        bJackDeck.DrawCard(true, 0);
        bJackDeck.DrawCard(true, 1);
        bJackDeck.DrawCard(true, 0);
        bJackDeck.DrawCard(true, 1);
        dealerTotal = WorkTotal(bJackDeck, 0);
        if (dealerTotal == 21)                
            dealerWins++;
        else
        {
            playerWon = false, dealerWon = false, stayed = true;
            playerTotal = WorkTotal(bJackDeck, 1);

            while (!playerWon && !dealerWon && !stayed)
            {
                if (playerTotal == 21)
                    playerWon = true;
                else if (playerTotal > 21)
                    dealerWon = true;
                else if (playerTotal < toStay)  
                {          
                    bJackDeck.DrawCard(true, 1);
                    playerTotal = WorkTotal(bJackDeck, 1);
                }
                else
                    stayed = true;
            }
            while (!playerWon && !dealerWon)
            {   
                if (dealerTotal > 21)
                    playerWon = true;
                else if (dealerTotal > playerTotal)
                    dealerWon = true;
                else
                {
                    bJackDeck.DrawCard(true, 0);
                    dealerTotal = WorkTotal(bJackDeck, 0);
                }
            }
            if (dealerWon)
                dealerWins++;
            else
                playerWins++;
        }
    }
    system("clear");
    cout << "Statistics for " << numSim << " games:\n"
         << "---------------------------\n"
         << "Player Wins: " << playerWins << "\n"
         << "Dealer Wins: " << dealerWins << "\n\n";
    Pause("Press enter to return to the main menu...", false);
}
