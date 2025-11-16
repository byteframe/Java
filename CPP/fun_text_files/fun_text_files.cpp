#include "TextFile.h"
#include <iostream>
#include <iomanip>
#include <sstream>
#include <vector>

using namespace std;
const char* CLEARCMD = "cls"; // 'cls' for DOS, 'clear' for UNIX
const short TERMLENGTH = 70;  // gui terms usually 80, yet TERMLENGTH 80 causes newline issues
const short FNAMELENGTH = 20; // for set(w) in list buffers function

// Functions for console interface
void  ListAllBuffers (const string&, const vector<TextFile>&, string);
short WhichBuffer    (const string&, const vector<TextFile>&, string, string);
void  ShowBuffer     (const string&, const vector<TextFile>&, short);
void  Pause          (string, bool);

// Functions for vector manipulation
void  NewBuffer      (vector<TextFile>&);
void  ViewBuffer     (const string&, vector<TextFile>&);
void  EditBuffer     (const string&, vector<TextFile>&);
void  ClearBuffer    (const string&, vector<TextFile>&);
void  FileToBuffer   (const string&, vector<TextFile>&);
void  AssignBuffer   (const string&, vector<TextFile>&);
void  Correlate      (const string&, vector<TextFile>&);
void  SaveBuffer     (const string&, vector<TextFile>&);

int main()
{
    vector<TextFile> theBuffers;
    NewBuffer(theBuffers);
    string bar(TERMLENGTH,'-');
    short mainChoice;
    do {
        ListAllBuffers(bar, theBuffers, "Fun Fun Text Editing and Correllation");
        cout << "   1. Open new text buffer\n"
             << "   2. View open text buffer\n"
             << "   3. Manually Edit open text buffer\n"
             << "   4. Clear open buffer\n"
             << "   5. Replace open buffer with text from a file\n"
             << "   6. Assign one text buffer to another\n"
             << "   7. Correlate two text buffers\n"
             << "   8. Save buffer to file\n"
             << "   9. Quit Program...\n"
             << bar << "\n"
             << "   Select Option : ";
        cin >> mainChoice;
        switch(mainChoice) {
            case 1: NewBuffer(theBuffers);         break;
            case 2: ViewBuffer(bar, theBuffers);   break;
            case 3: EditBuffer(bar, theBuffers);   break;
            case 4: ClearBuffer(bar, theBuffers);  break;
            case 5: FileToBuffer(bar, theBuffers); break;
            case 6: AssignBuffer(bar, theBuffers); break;
            case 7: Correlate(bar, theBuffers);    break;
            case 8: SaveBuffer(bar, theBuffers);   break;
            default:                               break;
        }
    } while (mainChoice != 9);
    Pause("Press any key to return to the shell...", true); 
    return 0;
}

void ListAllBuffers(const string &bar, const vector<TextFile> &theBuffers, string title) {
    system(CLEARCMD);
    cout << bar << "\n"
         << setw(TERMLENGTH / 2 + title.length() / 2) << title << "\n"
         << bar << "\n"
         << "   #   File:                    Characters:         Words:\n"
         << "\n";        
    for (short cnt = 0; cnt < theBuffers.size(); cnt++)
        cout << " "   << setw(3)  << right << cnt + 1
             << "   " << setw(20) << left  << theBuffers[cnt].getLastFile()
             << "   " << setw(12) << right << theBuffers[cnt].getCharCnt()
             << "   " << setw(12) << right << theBuffers[cnt].getWordCnt() << "\n";           
    cout << "\n"
         << bar << "\n";
}

short WhichBuffer(const string &bar, const vector<TextFile> &theBuffers, string for_title, string prompt) {
    short choosen;
    do {
        ListAllBuffers(bar, theBuffers, for_title);
        cout << "   Select " << prompt << " : ";
        cin >> choosen;
    } while (choosen < 1 || choosen > theBuffers.size());
    return choosen - 1;
}

void ShowBuffer(const string &bar, vector<TextFile> &theBuffers, short toShow) {
    system(CLEARCMD);
    cout << bar << "\n"
         << "\n"
         << theBuffers[toShow].getTextBuffer()
         << "\n"
         << bar << "\n";   
}

void Pause(string message, bool willClear) { // occasionally needs an extra hit of the enter key, should fix this
    if (willClear)
        system(CLEARCMD);
    cout << message;
    cin.ignore(cin.rdbuf() -> in_avail() + 1);
    cin.get();
}

void NewBuffer(vector<TextFile> &theBuffers) {
    std::ostringstream fileNameStream;
    fileNameStream << "Untitled-" << theBuffers.size();
    theBuffers.push_back(fileNameStream.str());
}

void ViewBuffer(const string &bar, vector<TextFile> &theBuffers) {
    ShowBuffer(bar, theBuffers, WhichBuffer(bar, theBuffers, "View open text buffer", "Buffer"));
}

void EditBuffer(const string &bar, vector<TextFile> &theBuffers) {
    short toEdVu = WhichBuffer(bar, theBuffers, "Manually Edit Open Buffer", "Buffer");
    short edMenuChoice;
    do { 
        ShowBuffer(bar, theBuffers, toEdVu);
        cout << "   1. Edit Buffer by Appending a New Line\n"
             << "   2. Return to the main menu...\n"
             << bar << "\n"
             << "   Select Option : ";
        cin >> edMenuChoice;
    } while (edMenuChoice < 1 || edMenuChoice > 2);
}

void ClearBuffer(const string &bar, vector<TextFile> &theBuffers) {
    theBuffers[WhichBuffer(bar, theBuffers, "Clear Open TextBuffer", "Buffer")].setTextBuffer("", false);
}

void FileToBuffer(const string &bar, vector<TextFile> &theBuffers) { // doesn't ask for whether or not to append to the text, so as to make more use of ClearBuffer()
    short toManip = WhichBuffer(bar, theBuffers, "Replace Open Buffer with Text from a File", "Buffer");
    string filename;
    do {
        ShowBuffer(bar, theBuffers, toManip);
        cout << "   Input file name: ";
        cin >> filename;                 
    } while (!theBuffers[toManip].OpenFile(filename, false) || filename.length() > FNAMELENGTH);
}

void AssignBuffer(const string &bar, vector<TextFile> &theBuffers) {
    short assignFrom = WhichBuffer(bar, theBuffers, "Assign one text buffer to another", "Buffer to Assign from");
    short assignTo = WhichBuffer(bar, theBuffers, "Assign one text buffer to another", "Buffer to Assign to" );
    theBuffers[assignTo].setTextBuffer(theBuffers[assignFrom].getTextBuffer(), false);
}

void Correlate(const string &bar, vector<TextFile> &theBuffers) {
    short correlateTo = WhichBuffer(bar, theBuffers, "Text Buffer Correlation", "Buffer to Correlate To"); 
    short correlateWith = WhichBuffer(bar, theBuffers, "Text Buffer Correlation", "Buffer to Correlate With"); 
    ListAllBuffers(bar, theBuffers, "Text Buffer Correlation");
    if (theBuffers[correlateTo].getCharCnt() >= theBuffers[correlateWith].getCharCnt()) {
        short totalScore = 0;
        short ssLength = theBuffers[correlateWith].getTextBuffer().length() / 100;
        short ssScore;      
        bool stopedSS;
        for (short cnt = 0; cnt < 100; cnt++) {
            ssScore = 0;
            stopedSS = false;
            string currentSS = theBuffers[correlateTo].getTextBuffer().substr(cnt * ssLength, ssLength);
            for (unsigned int ctpos = 0; ctpos < theBuffers[correlateTo].getTextBuffer().length() - ssLength; ctpos++) {
                for (short sspos = 0; sspos < ssLength; sspos++)
                    if (currentSS.at(sspos) != theBuffers[correlateTo].getTextBuffer().at(ctpos + sspos)) {
                        sspos = ssLength;
                        stopedSS = true;
                    }
                if (stopedSS = false)
                    ssScore++;
            }
            totalScore += ssScore;
        }
        cout << "   Total Correlation Score : " << totalScore;
    } else
        cout << "   The Buffer to correlate with is larger than the one to correllate to...";
    Pause("\n\n   Press any key to return to the menu...", false);
}

void SaveBuffer(const string &bar, vector<TextFile> &theBuffers) { // include code to ask for "same file ?" akin to 'save', versus this 'save as' business
    short toSave = WhichBuffer(bar, theBuffers, "Save Buffer to File", "Buffer");
    string filename;    
    ShowBuffer(bar, theBuffers, toSave);
    cout << "   Input filename : ";
    cin >> filename;
    theBuffers[toSave].SaveFile(filename);
}
