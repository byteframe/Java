// studentGrader2.cpp

#include <iostream>
#include <fstream>
#include <string>
#include <iomanip>

using namespace std;

struct Stdnt
{
    string name;
    short grd1, grd2, grd3;
    float avg;
};

void ListAllInfo1(Stdnt [], short);
void ReturnAvg2(Stdnt [], short);
void ShowAbove3(Stdnt [], short);
void ShowBelow4(Stdnt [], short);
void ShowReceiving5(Stdnt [], short);
void SortByName6(Stdnt [], short);
void SortByAvg7(Stdnt [], short);
void Continue();
void TitlePrinter(string);

int main()
{
    system("cls");
    TitlePrinter("Student Grader Program");
    cout << "This program will perform various operations on a\n"
         << "list of fifty students. Each student has three\n"
    	   << "recorded test grades, as well as their own ID\n"
         << "number. The operations available will be shown\n"
         << "in the following menu, but first a file containing\n"
         << "all of the relevant student data is needed.\n"
         << "--------------------------------------------------\n";
    string filename;
    ifstream inFile;
    do
    {	
        inFile.clear();
        cout << "Input the filename: ";
        cin >> filename;
        inFile.open(filename.c_str());
        if (!inFile)
        {
            cout << "*** File not found, retry or hit Ctrl-C to quit. ***\n\n";
        }
    } while (!inFile);
    Stdnt sData[50];
    short sCount = 0;
    while (!inFile.eof())
    {
        inFile >> sData[sCount].name;
        inFile >> sData[sCount].grd1;
        inFile >> sData[sCount].grd2;
        inFile >> sData[sCount].grd3;
        sData[sCount].avg = (float)(sData[sCount].grd1 + sData[sCount].grd2 + sData[sCount].grd3) / 3;
        if (!inFile.eof())
        {
            sCount++;
        }
    }
    inFile.close();
    bool finished = false;
    short menu;
    while (!finished)
    {
        do
        {
            system("cls");
            TitlePrinter("Student Grade Operations");
            cout << "1. List all ids, test grades, and averages\n"
                 << "2. Return the class average\n"
                 << "3. Show students receiving above a certain number\n"
                 << "4. Show students receiving below a certain number\n"
                 << "5. Show all students receiving the higest average\n"
                 << "6. Sort the data table by student name\n"
                 << "7. Sort the data table by average grade\n"
                 << "8. Exit\n"
                 << "--------------------------------------------------\n\n"
                 << "Select: ";
            cin >> menu;
        } while (menu < 1 || menu > 8);
        system("cls");
        switch(menu)
        {
            default:
                break;
            case 1:
                ListAllInfo1(sData, sCount);
                break;
            case 2:
                ReturnAvg2(sData, sCount);
                break;
            case 3:
                ShowAbove3(sData, sCount);
                break;
            case 4:
                ShowBelow4(sData, sCount);
                break;
            case 5:
                ShowReceiving5(sData, sCount);
                break;
            case 6:
                SortByName6(sData, sCount);
                break;
            case 7:
                SortByAvg7(sData, sCount);
                break;
            case 8:
                finished = true;
                break;
        }
    }
    return 0;
}

void ListAllInfo1(Stdnt sData[], short fsCount)
{
    short pageNum = 1;
    short tillNewPage = 0;
    bool allDone = false;
    do
    {
        system("cls");
        TitlePrinter("Current Class Information");
        cout << setw(28) << "Page: " << pageNum << endl << endl
             << setw(47) << "Student ID:      Test Scores:      Average:"
             << endl << endl;
        for (short count = tillNewPage; count < tillNewPage + 10; count++)
        {
            cout << setw(11) << sData[count].name
                 << setw(13) << sData[count].grd1 << setw(4) << sData[count].grd2 << setw(4) << sData[count].grd3
                 << setw(12) << fixed << setprecision(1) << sData[count].avg << endl;
            if (count >= fsCount - 1)
            {
                count = 50;
            }
        }
        cout << endl;
        if (tillNewPage + 10 >= fsCount)
        {
            allDone = true;
        }
        else
        {
            pageNum++;
            tillNewPage += 10;
            cout << "\nPress <enter> to print the next page...";
            cout << endl;
            Continue();
        }
    } while (!allDone);
    cout << "\nPress <enter> to return to the main menu...\n\n";
    Continue();
}

void ReturnAvg2(Stdnt sData[], short fsCount)
{
    system("cls");
    float classAvg = 0;
    for (short count = 0; count < fsCount; count++)
    {
        classAvg += sData[count].avg;
    }
    classAvg /= fsCount;
    cout << "The class average of all " << fsCount << " students is " << setprecision(1) << fixed << classAvg << " percent.\n\n"
         << "Press <enter> to return to the main menu...";
    Continue();
}

void ShowAbove3(Stdnt sData[], short fsCount)
{
    system("cls");
    short toCheck;
    do
    {
        cout << "Choose a grade to list the students whos average is above that number.\n"
             << "Input: ";
        cin >> toCheck;
    } while (toCheck < 0 || toCheck > 1000); 
    system("cls");
    short tillNewLine = 0;
    TitlePrinter("Higher Ranking Students");
    cout << setw(27) << "Average: " << toCheck << "%\n\n"
         << "       ";
    for (short count = 0; count < fsCount; count++)
    {
        if (sData[count].avg > toCheck)
        {
            cout << "   " << sData[count].name;
            tillNewLine++;
        }
        if (tillNewLine == 4)
        {
            cout << "\n\n       ";
            tillNewLine = 0;
        }
    }
    cout << "\n\n\nPress <enter> to return to the main menu...";
    Continue();
}

void ShowBelow4(Stdnt sData[], short fsCount)
{
    system("cls");
    unsigned int toCheck;
    do
    {
        cout << "Choose a grade to list the students whos average is below that number.\n"
             << "Input: ";
        cin >> toCheck;
    } while (toCheck < 0 || toCheck > 1000);
    system("cls");
    short tillNewLine = 0;
    TitlePrinter("Lower Ranking Students");
    cout << setw(27) << "Average: " << toCheck << "%\n\n"
         << "       ";
    for (short count = 0; count < fsCount; count++)
    {
        if (sData[count].avg < toCheck)
        {
            cout << "   " << sData[count].name;
            tillNewLine++;
        }
        if (tillNewLine == 4)
        {
            cout << "\n\n       ";
            tillNewLine = 0;
        }
    }
    cout << "\n\n\nPress <enter> to return to the main menu...";
    Continue();
}

void ShowReceiving5(Stdnt sData[], short fsCount)
{
    system("cls");
    short highestAvg;
    for (short count = 0; count < fsCount; count++)
    {
        if (highestAvg < sData[count].avg)
        {
            highestAvg = sData[count].avg;
        }
    }
    short tillNewLine = 0;
    TitlePrinter("Highest Averaging Students");
    cout << setw(27) << "Average: " << highestAvg << "%\n\n"
         << "       ";
    for (short count = 0; count < fsCount; count++)
    {
        if (sData[count].avg == highestAvg)
        {
            cout << "   " << sData[count].name;
            tillNewLine++;
        }
        if (tillNewLine == 4)
        {
            cout << "\n\n       "; 
            tillNewLine = 0;
        }
    }
    cout << "\n\nPress <enter> to return to the main menu...";
    Continue();
}

void SortByName6(Stdnt sData[], short fsCount)
{
    system("cls");
    Stdnt temp;
    for (short p = 0; p < fsCount - 1; p++) 
    {
        for (short elem = 0; elem < fsCount - 1; elem++)      
        {
            if (sData[elem].name > sData[elem + 1].name) 
            {
                temp = sData[elem];
                sData[elem] = sData[elem + 1];
                sData[elem + 1] = temp;
            }
        }
    }
    cout << "The student data table has been sorted by ID number.\n"
         << "All operations performed from the menu will reflect this.\n\n"
         << "Press <enter> to return to the main menu...";
    Continue();
}

void SortByAvg7(Stdnt sData[], short fsCount)
{
    system("cls");
    Stdnt temp;
    for (short p = 0; p < fsCount - 1; p++) 
    {
        for (short elem = 0; elem < fsCount - 1; elem++)      
        {
            if (sData[elem].avg < sData[elem + 1].avg) 
            {
                temp = sData[elem];
                sData[elem] = sData[elem + 1];
                sData[elem + 1] = temp;
            }
        }
    }
    cout << "The student data table has been sorted by average grade.\n"
         << "All operations performed from the menu will reflect this.\n\n"
         << "Press <enter> to return to the main menu...";
    Continue();
}

void Continue()
{
    cin.ignore(cin.rdbuf() -> in_avail() + 1);
    cin.get();
}

void TitlePrinter(string ftitle)
{
    string spcLen(((48 - ftitle.length()) / 2), ' ');
    ftitle.insert(0, spcLen + '(');
    cout << "--------------------------------------------------\n"
         << ftitle << ")\n" 
         << "--------------------------------------------------\n";
}
