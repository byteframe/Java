// random_game.cpp

#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

int rnd();

int main()
{
    char start = 'z';
    system("clear");
    cout << "In this fascinatingly fun game, I the computer will" << endl;
    cout << "attempt to randomly quess a number between 1-1000," << endl;
    cout << "then if I get it wrong I will try again! I'll I ask" << endl;
    cout << "is that you tell me if I need to go higher or lower!" << endl;
    cout << "Let's begin!" << endl << endl;
    cout << "Have you thought of a number yet?" << endl;
    cout << "-----------------------------------------------------" << endl;
    while (start != 'y' && start != 'n')
    {
        cout << "Please choose (y or n): ";
        cin >> start;
    }
    if (start == 'n')
    {
        system("clear");
        cout << "Then come back when you've chosen you dope!" << endl << endl;
    }
    else
    {
        int lowend = 1;
        int highend = 1000;
        int quess = 1 + rnd() & 1000;
        int tries = 0;
        bool finished = false;
        char question = 'z';
        char location = 'z';
        while (!finished) 
        {
            tries++;
            quess = lowend + rnd() & highend;
            system("clear");
            cout << "-----------------------------------------------------" << endl;
            cout << "Quess #" << tries << " is: " << quess << endl;
            cout << "-----------------------------------------------------" << endl;
            while (question != 'y' && question != 'n')
            {	
                cout << "Is " << quess << " your number? (y or n): ";
                cin >> question;
            }               
            if (question == 'y')
            {
                finished = true;
                question = 'z';
            }
            else if (question == 'n')
            {
                while (location != 'h' && location != 'l')
                {
                    cout << "Is the number you chose (h)igher or (l)ower than " << quess << " ?";
                    cin >> location;
                }
                if (location == 'h')
                {
                    cout << "Okay, I'll quess higher..." << endl;
                    lowend = quess + 1;
                    location = 'z';
                }
                else if (location == 'l') 
                {
                    cout << "Okay, I'll quess lower..." << endl;
                    highend = quess - 1;
                    location = 'z';
                }
            }
            question = 'z';
        }
        system("clear");
        cout << "I quessed your supposed number, (" << quess << ") in " << tries << " tries!" << endl;
        cout << "Please come again! We had fun!" << endl << endl;
    }
    return 0;
}

int rnd()
{
    static bool init = false;
    if (!init)
    {
        init = true;
        srand(time(NULL));
    }
    return rand();    
}
