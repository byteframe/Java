#include <string>

class PlayingCard
{
    private:
        std::string name, suit, title;
        short value, owner;
        bool facingUp;
        
    public:
        PlayingCard();
        std::string getName() const { return name; }
        std::string getSuit() const { return suit; }
        std::string getTitle() const { return title; }
        short getValue() const { return value; }
        short getOwner() const { return owner; }
        bool getFacingUp() const { return facingUp; }
        void setName(std::string in_name) { name = in_name; }
        void setSuit(std::string in_suit) { suit = in_suit; }
        void setTitle(std::string in_title) { title = in_title; }
        void setValue(short in_value) { value = in_value; }
        void setOwner(short in_owner) { owner = in_owner; }
        void setFacingUp(bool in_facingUp) { facingUp = in_facingUp; }
};
