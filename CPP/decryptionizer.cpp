#include <iostream>
#include <fstream>
#include <string>

using namespace std;

string Rotate(string&, short);
short TheGuess(string&);
short FindInAlphabet(char);

struct Single
{
    char letter;
    short distance;
};

string alphabet = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"; // 2 sets for array overrunning might need 3

int main()
{
    system("cls");
    string filename, encryption;  
    cout << "Input the file name to decrypt : ";
    cin >> filename;
    ifstream input(filename.c_str());
    if (input)
    {
        char currentChar;
        while (input.get(currentChar))
            encryption += currentChar;
        input.close();
        system("cls");
        cout << "\n" << encryption;
        cout << "\n----------------------------\n"
             << "That was the encrypted text.\n"
             << "----------------------------\n";
        system("pause");
	      short choice;
        do
        {
            cout << "\nChoose decryption method\n"
                 << "--------------------------------------\n"
                 << "1. Use your own character rotation key\n"
                 << "2. Have this dumb program try a quess\n\n"
                 << "\nChoose : ";
            cin >> choice;
        } while (choice < 1 || choice > 2);
        string decryption;
        if (choice == 1)
        {
            system("cls");
            short rotateBy;
            do
            {
                cout << "Enter the number character rotations (1-25) : ";
                cin >> rotateBy;
            } while (rotateBy < 0 || rotateBy > 25);
            decryption = Rotate(encryption, rotateBy);
        }
        else
            decryption = Rotate(encryption, TheGuess(encryption));
        system("cls");
        cout << "\n" << decryption;
        cout << "\n----------------------------\n"
             << "That was the decrypted text.\n"
             << "----------------------------\n";
        cout << "\nInput a file name to save : ";
        cin >> filename;
        ofstream output(filename.c_str()); // doesnt append
        output << decryption;
        output.close();
    }
    else
        cout << "That file does not exist!" << endl;
    system("pause");   
    return 0;
}

string Rotate(string &toRotate, short toGo)
{
    string toReturn;
    bool isLetter;
    for (int pos = 0; pos < toRotate.length(); pos++)
    {
        isLetter = false;
        for (short inA = 0; inA < 26; inA++)
            if (toRotate.at(pos) == alphabet.at(inA))
            {
                if (inA - toGo >= 0)   
                    toReturn += alphabet[inA - toGo];
                if (inA - toGo < 0)
                    toReturn += alphabet[52 - (toGo - inA)];
                isLetter = true;
                inA = 26;
            }
        if (!isLetter)
            toReturn += toRotate.at(pos);
    }
    return toReturn;
}

short TheGuess(string &encryption)
{
    string tempSingles = "";
    for (int pos = 0; pos < encryption.length(); pos++)
        if (encryption.at(pos) == ' ' && encryption.at(pos + 2) == ' ')
            if (tempSingles.find(encryption.at(pos + 1), 0) == string::npos)
                tempSingles += encryption.at(pos + 1);
    Single theSingles[tempSingles.length()];
    for (short cnt = 0; cnt < tempSingles.length(); cnt++)
        theSingles[cnt].letter = tempSingles.at(cnt);
    for (short num = 0; num < tempSingles.length(); num++)
        for (short inA = 0; inA < 26; inA++)
            if (theSingles[num].letter == alphabet.at(inA))
            {
                theSingles[num].distance = inA;
                inA = 30;
            } 
    string tempThe = "the";
    for (short num = 0; num < tempSingles.length(); num++)
    {
         string encThe = Rotate(tempThe, theSingles[num].distance - (theSingles[num].distance * 2));   
         if (short found = encryption.find(encThe, 0) != string::npos)
         {
             cout << "\nQuessed rotation key was : " << theSingles[num].distance;
             return theSingles[num].distance;
         }
    }
}

short FindInAlphabet(char toFind)
{
    bool isLetter;
    for (short inA = 0; inA < 25; inA++)
    {
        isLetter = false;
        if (toFind == alphabet.at(inA))
            return alphabet.at(inA);
    }
}
