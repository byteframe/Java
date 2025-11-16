// candy_machine.h

class cashRegister
{
    public:
        cashRegister(short cashIn = 500); 
        short getCurrentBalance() const;
        void acceptAmount(short amountIn);
        void makeChange(short amountIn);
    private:
        short cashOnHand;
};

class dispenserType
{
    public:
        dispenserType(short setNoOfItems = 50, short setCost = 50); 
        short getNoOfItems() const;
        short getCost() const;
        void makeSale();
    private:
        short numberOfItems;
        short cost;
};