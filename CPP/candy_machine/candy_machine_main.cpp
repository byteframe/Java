// candy_machine_main.cpp

#include <iostream>
#include "candy_machine.h"

using namespace std;

void showSelection();
void sellProduct(dispenserType& product, cashRegister& pCounter);

int main()
{
    cashRegister counter;
    dispenserType candy(20, 50); 
    dispenserType chips(15, 65);
    dispenserType gum(35, 45);
    dispenserType cookies(25, 85);
    dispenserType aspirin(15, 200); 
    dispenserType niquill(10, 250);
    dispenserType csyrup(10, 135);
    dispenserType sugar(950, 10);
    short choice;
    bool finished = false;
    do
    {
        do
        {
            system("cls");
            showSelection();
            cin >> choice;
            switch (choice)
            {
                default:
                    cout << "Invalid selection." << endl;
                    break;
                case 1: 
                    sellProduct(candy, counter);
                    break;
                case 2:
                    sellProduct(chips, counter);
                    break;
                case 3:
                    sellProduct(gum, counter);
                    break;
                case 4: 
                    sellProduct(cookies, counter);
                    break;
                case 5: 
                    sellProduct(aspirin, counter);
                    break;
                case 6: 
                    sellProduct(niquill, counter);
                    break;
                case 7: 
                    sellProduct(csyrup, counter);
                    break;
                case 8: 
                    sellProduct(sugar, counter);
                    break;
                case 9:
                    finished = true;
                    break;
            }
        } while (choice < 0 || choice > 9);
    } while (!finished);
    	
    system("cls");
    cout << "Thank you for using the Junkfood PitStop!\n"
            "Please come again!\n\n" << endl;
    system("pause");
    return 0;
}

void showSelection()
{
    cout << "** Welcome to the Junkfood Pitstop! **\n"
         << "--------------------------------------\n"
         << "1. Sweet n' Sour Candy\n"
         << "2. Potato Chips\n"
         << "3. Bubble Gum\n"
         << "4. Cookies\n"
         << "5. Aspirin\n"
         << "6. Ni-Quill\n"
         << "7. Cough Syrup\n"
         << "8. Sugar Packet\n"
         << "--------------------------------------\n"
         << "9. Exit\n\n"
         << "Select: ";
}

void sellProduct(dispenserType& product, cashRegister& pCounter)
{
    short amount;
    short amount2;
    if (product.getNoOfItems() > 0) // makes sure product is available
    {
        cout << "Please deposit " << product.getCost() << " cents." << endl;
        cin >> amount;
        while (amount < product.getCost())
        {
            cout << "You still need " << product.getCost()- amount << " cents." << endl;             
            cin >> amount2;
            amount += amount2;
        }
        system("cls");
        pCounter.acceptAmount(amount);
        product.makeSale();
        cout << "Your selection is ready at the bottom." << endl;                
        if (amount > product.getCost())
        {
            pCounter.makeChange(amount - product.getCost());
            cout << "But don't forget your " << amount - product.getCost() << " cents in change." << endl;
        }
    }
    else
    {
        cout << "Sorry, this item is sold out." << endl;
    }
    cout << "--------------------------------------------\n" << endl;
    system("pause");
}
