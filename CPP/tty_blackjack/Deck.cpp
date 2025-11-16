#include "Deck.h"
#include <cstdlib>
#include <ctime>

void Deck::ShuffleDeck()
{
    short eleOrder[52];
    bool usedYet[52];
    for (short cnt = 0; cnt < 52; cnt++) 
        usedYet[cnt] = false;
    for (short cnt = 0; cnt < 52; cnt++)
    {
        short randCard = 0; 
        do
        {
            static bool init = false;
            if (!init)
            {
                init = true;
                srand(time(NULL));
            }
            randCard = rand() % 52;
        } while (usedYet[randCard]);
        usedYet[randCard] = true;
        eleOrder[cnt] = randCard;
    }
    short whereEle = 0;
    for (short suitCnt = 0; suitCnt < 4; suitCnt++)
        for (short ele = 0; ele < 13; ele++)
        {        
            cards[eleOrder[whereEle]].setSuit(SUITS[suitCnt]);
            cards[eleOrder[whereEle]].setValue(VALUES[ele]);
            cards[eleOrder[whereEle]].setName(NAMES[ele]);
            cards[eleOrder[whereEle]].setTitle(NAMES[ele] + " of " + SUITS[suitCnt]);
            whereEle++;
        }
    topOfDeck = -1;
    onTable = 0;
}

void Deck::DrawCard(bool in_facingUp_toSet, short in_owner_toSet)
{
    topOfDeck++;
    onTable++;
    cards[topOfDeck].setFacingUp(in_facingUp_toSet);
    cards[topOfDeck].setOwner(in_owner_toSet);
}
