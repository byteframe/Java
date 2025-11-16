#include "PlayingCard.h"

const short VALUES[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
const std::string SUITS[] = { "Spades", "Clubs", "Hearts", "Diamonds" }; 
const std::string NAMES[] = { "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace" };
                             
class Deck
{   
    private:
        PlayingCard cards[52];
        short topOfDeck, onTable;
            
    public:
        Deck() {};
        short getTopOfDeck() const { return topOfDeck; }
        short getOnTable() const { return onTable; }
        PlayingCard getCard(short in_card) const { return cards[in_card]; } 
        void ShuffleDeck();
        void DrawCard(bool in_facingUp, short in_owner_toSet); 
};
