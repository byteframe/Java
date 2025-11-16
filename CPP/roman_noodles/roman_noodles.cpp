// roman_noodles.cpp

#include <iostream>
#include <sstream>
#include "Roman.h"

using namespace std;
using std::ostringstream;
typedef string str; 

void ShowNumerals(Roman []);
void DefineNumerals(Roman []);
void AlterNumerals(Roman [], short);
void DisplayHelp();
void Continue();
void BoxPrinter(str, str, str, str, str, str, str, str);

int main()
{   
    Roman both[2];
    bool finished = false;  
    short choice;
    while (!finished)
    {
        do
        {
            BoxPrinter("Operations Menu",
                       "1. Show both numerals",
                       "2. Define a numeral",
                       "3. Add two numerals together",
                       "4. Subtract two numberals",
                       "5. Display Help Information","",
                       "6. Quit...");
            cout << "Select: ";
            cin >> choice;
        } while (choice < 1 || choice > 6);
        switch(choice)
        {
            default:
                break;
            case 1:
                ShowNumerals(both);
                break;
            case 2:
                DefineNumerals(both);
                break;
            case 3:
            case 4:
                AlterNumerals(both, choice);
                break;
            case 5:
                DisplayHelp();
                break;
            case 6:
                finished = true;
                break;
        }
    }
    system("cls");
    cout << "Program terminated.\n" << endl;
    system("pause");
    return 0;
}

void ShowNumerals(Roman both[])
{
    ostringstream dec0, dec1;
    dec0 << both[0].GetDecimal();
    dec1 << both[1].GetDecimal();
    BoxPrinter("Current Statistics", "",
               "Number 1: " + both[0].GetNumeral(),
               "          " + dec0.str(), "",
               "Number 2: " + both[1].GetNumeral(),
               "          " + dec1.str(), "");
    Continue();
}

void DefineNumerals(Roman both[])
{
    short whoTo;
    do
    {
        BoxPrinter("Definition Selection",
                   "", "Which of the two numerals would you like to define?","",
                   "1. The first Roman Numeral",
                   "2. The second Roman Numeral","","");
        cout << "Select: ";
        cin >> whoTo;
    } while (whoTo < 1 || whoTo > 2);
    whoTo--;
    short howTo;
    do
    {
        BoxPrinter("Definition Method","",
                   "How would you like to define the numeral?", "",
                   "1. By choosing a Decimal number",
                   "2. By inputting a Roman numeral",
                   "","");
        cout << "Select: ";
        cin >> howTo;
    } while (howTo < 1 || howTo > 2);
    if (howTo == 1)
    {
        ostringstream cst0, cst1;
        cst0 << MIN_VAL;
        cst1 << MAX_VAL;
        short decTake;
        do
        {
            BoxPrinter("Definition by Decimal","",
                       "Choose a decimal value between " + cst0.str() + " and " + cst1.str() + '.',
                       "","","","","");
            cout << "Input: ";
            cin >> decTake;
        } while (!both[whoTo].TakeDecimal(decTake));
    }
    else
    {
        str numTake;
        do
        {
            BoxPrinter("Definition by Numeral","",
                       "Enter your roman numeral on one line.",
                       "","","","","");
            cout << "Input: ";
            cin >> numTake;
        } while (!both[whoTo].TakeNumeral(numTake));
    }
    BoxPrinter("All Finished","",
               "Your changes have been made!",
               "","","","","");
    Continue();
}

void AlterNumerals(Roman both[], short whatDo)
{
    if (both[0].GetDecimal() > 0 && both[1].GetDecimal() > 0)
    {
        short whoTo;
        ostringstream dec0, dec1;
        dec0 << both[0].GetDecimal();
        dec1 << both[1].GetDecimal();
        do
        {
            BoxPrinter("Alteration Selection",
                       "Which numeral would you like to " + str(whatDo == 3 ? "add to?" : "subtract from?"),
                       "","",
                       "1. " + both[0].GetNumeral() + "    (" + dec0.str() + ")",
                       "2. " + both[1].GetNumeral() + "    (" + dec1.str() + ")",
                       "","");
            cout << "Choose: ";
            cin >> whoTo;
        } while (whoTo < 1 || whoTo > 2);
        whoTo--;
        if (whatDo == 3)
        {
            if (both[whoTo].GetDecimal() + both[whoTo == 1 ? 0 : 1].GetDecimal() > MAX_VAL)
            {
                ostringstream cst1;
                cst1 << MAX_VAL;
                BoxPrinter("Alterations Failed",
                           "","Both numerals will add up above " + cst1.str() + "!",
                           "","","","","");
            }
            else
            {
                both[whoTo].Add(both[whoTo == 1 ? 0 : 1]);
                BoxPrinter("Alterations Completed",
                       "","Both numerals were added together",
                       "","","","","");
            }
        }
        else
        {
            if (both[whoTo].GetDecimal() < both[whoTo == 1 ? 0 : 1].GetDecimal())
                BoxPrinter("Alterations Failed",
                       "","You cannot subtract from a smaller numeral!",
                       "","","","","");
            else
            {
                both[whoTo].Sub(both[whoTo == 1 ? 0 : 1]);
                BoxPrinter("Alterations Completed",
                       "","One numeral was substracted from the other!",
                       "Huzzahs are in order!","","","","");
            }
        }
    }
    else
        BoxPrinter("Error",
                   "","First you must create create both numerals!",
                   "","","","","");
    Continue();
}

void DisplayHelp()
{
    ostringstream cst1;
    cst1 << MAX_VAL;
    BoxPrinter("Help Information",
               "This program is created in the hopes that it will be",
               "useful for playing around with roman numerals up to",
               "an arabic equivalent of " + cst1.str() + ". To begin, make sure you",
               "define the numerals. In addition if you redfine them",
               "or you change them with the addition or subtraction ",
               "operations, remember that you must choose the first",
               "option in the menu to show the updated statistics.");
    Continue();
}

void Continue()
{
    cout << "Press [enter] to return to the main menu...";
    cin.ignore(cin.rdbuf() -> in_avail() + 1);
    cin.get();
}

void BoxPrinter(str t, str l1, str l2, str l3, str l4, str l5, str l6, str l7)
{
    system("cls");
    str spcLen((56 - t.length()) / 2, ' ');
    cout << "------------------------------------------------------------\n"
         << spcLen << "** " << t << " **\n"
         << "------------------------------------------------------------\n"
         << "    " << l1 << "\n"
         << "    " << l2 << "\n"
         << "    " << l3 << "\n"
         << "    " << l4 << "\n"
         << "    " << l5 << "\n"
         << "    " << l6 << "\n"
         << "    " << l7 << "\n"
         << "------------------------------------------------------------\n"
         << "    ";
}
