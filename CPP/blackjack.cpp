// blackjack.cpp

#include <iostream>
#include <cstdlib>
#include <ctime>
#include <string>

using namespace std;

class Cards
{
    public:
        Cards();
        int DealCard();
        void TellCard();
        int Val;
        string Suit, Name;
        bool Marked;
};

Cards::Cards()
{
    Val = 0;
    Suit = "Null";
    Name = "Null";
    Marked = false;
}

int Cards::DealCard()
{
    static bool RandReady = false;
    static int CardDrawn;

    if (!RandReady) 
    {
        srand (time(0));
        RandReady = true;  
        CardDrawn = (rand() % 52);
    }
    while (Deck[CardDrawn].Marked = true)
    {
        if (!RandReady) 
        {
            srand (time(0));
            RandReady = true;  
            CardDrawn = (rand() % 52);
        }
    }
    bool Deck[CardDrawn].Marked = true;
    return CardDrawn;
}

void Cards::TellCard()
{ 
    if (CardDrawn == 51 || CardDrawn == 45 || CardDrawn == 38 || CardDrawn == 32 || 
        CardDrawn == 25 || CardDrawn == 19 || CardDrawn == 12 || CardDrawn == 6)
    {
        cout << "You were dealt an " << Deck[CardDrawn].Name << " of " << Deck[CardDrawn].Suit << ".\n";
    }
    else
    {
        cout << "You were dealt a " << Deck[CardDrawn].Name << " of " << Deck[CardDrawn].Suit << ".\n";
    }
}

int main()
{
    Cards Deck[52];
    Deck[0].Val = 2; Deck[0].Suit = "spades"; Deck[0].Name = "duece"; Deck[0].Marked = false;
    Deck[1].Val = 3; Deck[1].Suit = "spades"; Deck[1].Name = "three"; Deck[1].Marked = false;
    Deck[2].Val = 4; Deck[2].Suit = "spades"; Deck[2].Name = "four"; Deck[2].Marked = false;
    Deck[3].Val = 5; Deck[3].Suit = "spades"; Deck[3].Name = "five"; Deck[3].Marked = false;
    Deck[4].Val = 6; Deck[4].Suit = "spades"; Deck[4].Name = "six"; Deck[4].Marked = false;
    Deck[5].Val = 7; Deck[5].Suit = "spades"; Deck[5].Name = "seven"; Deck[5].Marked = false;
    Deck[6].Val = 8; Deck[6].Suit = "spades"; Deck[6].Name = "eight"; Deck[6].Marked = false;
    Deck[7].Val = 9; Deck[7].Suit = "spades"; Deck[7].Name = "nine"; Deck[7].Marked = false;
    Deck[8].Val = 10; Deck[8].Suit = "spades"; Deck[8].Name = "ten"; Deck[8].Marked = false;
    Deck[9].Val = 10; Deck[9].Suit = "spades"; Deck[9].Name = "jack"; Deck[9].Marked = false;
    Deck[10].Val = 10; Deck[10].Suit = "spades"; Deck[10].Name = "queen"; Deck[10].Marked = false;
    Deck[11].Val = 10; Deck[11].Suit = "spades"; Deck[11].Name = "king"; Deck[11].Marked = false;
    Deck[12].Val = 11; Deck[12].Suit = "spades"; Deck[12].Name = "ace"; Deck[12].Marked = false;
    Deck[13].Val = 2; Deck[13].Suit = "clubs"; Deck[13].Name = "duece"; Deck[13].Marked = false;
    Deck[14].Val = 3; Deck[14].Suit = "clubs"; Deck[14].Name = "three"; Deck[14].Marked = false;
    Deck[15].Val = 4; Deck[15].Suit = "clubs"; Deck[15].Name = "four"; Deck[15].Marked = false;
    Deck[16].Val = 5; Deck[16].Suit = "clubs"; Deck[16].Name = "five"; Deck[16].Marked = false;
    Deck[17].Val = 6; Deck[17].Suit = "clubs"; Deck[17].Name = "six"; Deck[17].Marked = false;
    Deck[18].Val = 7; Deck[18].Suit = "clubs"; Deck[18].Name = "seven"; Deck[18].Marked = false;
    Deck[19].Val = 8; Deck[19].Suit = "clubs"; Deck[19].Name = "eight"; Deck[19].Marked = false;
    Deck[20].Val = 9; Deck[20].Suit = "clubs"; Deck[20].Name = "nine"; Deck[20].Marked = false;
    Deck[21].Val = 10; Deck[21].Suit = "clubs"; Deck[21].Name = "ten"; Deck[21].Marked = false;
    Deck[22].Val = 10; Deck[22].Suit = "clubs"; Deck[22].Name = "jack"; Deck[22].Marked = false;
    Deck[23].Val = 10; Deck[23].Suit = "clubs"; Deck[23].Name = "queen"; Deck[23].Marked = false;
    Deck[24].Val = 10; Deck[24].Suit = "clubs"; Deck[24].Name = "king"; Deck[24].Marked = false;
    Deck[25].Val = 11; Deck[25].Suit = "clubs"; Deck[25].Name = "ace"; Deck[25].Marked = false;
    Deck[26].Val = 2; Deck[26].Suit = "hearts"; Deck[26].Name = "duece"; Deck[26].Marked = false;
    Deck[27].Val = 3; Deck[27].Suit = "hearts"; Deck[27].Name = "three"; Deck[27].Marked = false;
    Deck[28].Val = 4; Deck[28].Suit = "hearts"; Deck[28].Name = "four"; Deck[28].Marked = false;
    Deck[29].Val = 5; Deck[29].Suit = "hearts"; Deck[29].Name = "five"; Deck[29].Marked = false;
    Deck[30].Val = 6; Deck[30].Suit = "hearts"; Deck[30].Name = "six"; Deck[30].Marked = false;
    Deck[31].Val = 7; Deck[31].Suit = "hearts"; Deck[31].Name = "seven"; Deck[31].Marked = false;
    Deck[32].Val = 8; Deck[32].Suit = "hearts"; Deck[32].Name = "eight"; Deck[32].Marked = false;
    Deck[33].Val = 9; Deck[33].Suit = "hearts"; Deck[33].Name = "nine"; Deck[33].Marked = false;
    Deck[34].Val = 10; Deck[34].Suit = "hearts"; Deck[34].Name = "ten"; Deck[34].Marked = false;
    Deck[35].Val = 10; Deck[35].Suit = "hearts"; Deck[35].Name = "jack"; Deck[35].Marked = false;
    Deck[36].Val = 10; Deck[36].Suit = "hearts"; Deck[36].Name = "queen"; Deck[36].Marked = false;
    Deck[37].Val = 10; Deck[37].Suit = "hearts"; Deck[37].Name = "king"; Deck[37].Marked = false;
    Deck[38].Val = 11; Deck[38].Suit = "hearts"; Deck[38].Name = "ace"; Deck[38].Marked = false;
    Deck[39].Val = 2; Deck[39].Suit = "diamonds"; Deck[39].Name = "duece"; Deck[39].Marked = false;
    Deck[40].Val = 3; Deck[40].Suit = "diamonds"; Deck[40].Name = "three"; Deck[40].Marked = false;
    Deck[41].Val = 4; Deck[41].Suit = "diamonds"; Deck[41].Name = "four"; Deck[41].Marked = false;
    Deck[42].Val = 5; Deck[42].Suit = "diamonds"; Deck[42].Name = "five"; Deck[42].Marked = false;
    Deck[43].Val = 6; Deck[43].Suit = "diamonds"; Deck[43].Name = "six"; Deck[43].Marked = false;
    Deck[44].Val = 7; Deck[44].Suit = "diamonds"; Deck[44].Name = "seven"; Deck[44].Marked = false;
    Deck[45].Val = 8; Deck[45].Suit = "diamonds"; Deck[45].Name = "eight"; Deck[45].Marked = false;
    Deck[46].Val = 9; Deck[46].Suit = "diamonds"; Deck[46].Name = "nine"; Deck[46].Marked = false;
    Deck[47].Val = 10; Deck[47].Suit = "diamonds"; Deck[47].Name = "ten"; Deck[47].Marked = false;
    Deck[48].Val = 10; Deck[48].Suit = "diamonds"; Deck[48].Name = "jack"; Deck[48].Marked = false;
    Deck[49].Val = 10; Deck[49].Suit = "diamonds"; Deck[49].Name = "queen"; Deck[49].Marked = false;
    Deck[50].Val = 10; Deck[50].Suit = "diamonds"; Deck[50].Name = "king"; Deck[50].Marked = false;
    Deck[51].Val = 11; Deck[51].Suit = "diamonds"; Deck[51].Name = "ace"; Deck[51].Marked = false;
    system("clear");
    cout << "                       Welcome to Crappy BlackJack Aproximation!\n";    
    cout << "---------------------------------------------------------------------------------------------------\n";
    cout << "This program attempts to look like a Blackjack game, as in all two player Blackjack games, the\n";
    cout << "user goes first, and is dealt two cards. The object of the game is to choose whether or not to\n";
    cout << "accept more individual cards, and to either arrive at 21, thereby winning the game, or choosing\n";
    cout << "to 'stay' and hope the dealer/computer goes over 21. If you go over 21 you lose, automatically,\n";
    cout << "and the dealer will win. Suits are displayed in this program, but will not affect the score.\n\n"; 
    cout << "Type 'y' and press enter to run the game, or hit CTRL-C to quit...\n";
    cout << "Do you want to play?\n"; 
    string FirstChoice;
    cin >> FirstChoice;
    cout << endl;
    while (FirstChoice != "y")
    {
        cout << "Invalid response.\n";
        cout << "Do you want to play?\n\n";
        cin >> FirstChoice;
        cout << endl;
    }
    bool GameLost;
    while (GameLost == false)
    {
        system("clear"); 
        cout << "---------------------------------------------------------------------------------------------------\n";
     	  cout << "---------------------------------------------------------------------------------------------------\n\n";
        GameLost = true;
    }
}