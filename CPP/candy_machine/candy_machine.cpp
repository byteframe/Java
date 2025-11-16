// candyMachine.cpp

#include "candyMachine.h"

cashRegister::cashRegister(short cashIn)
{
    if (cashIn >= 0)
    {
        cashOnHand = cashIn;
    }
}

short cashRegister::getCurrentBalance() const
{
    return cashOnHand;
}

void cashRegister::acceptAmount(short amountIn)
{
    cashOnHand += amountIn;
}

void cashRegister::makeChange(short amountIn)
{
    cashOnHand -= amountIn;
}

dispenserType::dispenserType(short setNoOfItems, short setCost)
{
    if (setNoOfItems >= 0)
    {
        numberOfItems = setNoOfItems;
    }
       
    if (setCost >= 0)
    {
        cost = setCost;
    }
}

short dispenserType::getNoOfItems() const
{
    return numberOfItems;
}

short dispenserType::getCost() const
{
    return cost;
}

void dispenserType::makeSale()
{
    numberOfItems--;
}