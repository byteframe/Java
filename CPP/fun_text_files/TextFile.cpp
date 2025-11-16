// TextFile Class Definition

#include "TextFile.h"
#include <fstream>

TextFile::TextFile(std::string in_lastfile) {    
    textBuffer = "";
    lastFile = in_lastfile;
    wordCnt = 0;
    charCnt = 0;
}
       
bool TextFile::OpenFile(std::string in_filename, bool in_willAppend) {
    std::ifstream inTextFile(in_filename.c_str());
    if (inTextFile) {
        std::string forToSet;
        char currentChar;
        while(inTextFile.get(currentChar))
            forToSet += currentChar;
        setTextBuffer(forToSet, in_willAppend);
        lastFile = in_filename;
        inTextFile.close();
        return true;  
    } else
        return false;
}

void TextFile::SaveFile(std::string in_filename) {
    std::ofstream outTextFile(in_filename.c_str());
    outTextFile << textBuffer;
    outTextFile.close();
    lastFile = in_filename;
}  
      
void TextFile::setTextBuffer(std::string toSet, bool willAppend) {
    if (willAppend)
        textBuffer += toSet;
    else {
        textBuffer = toSet;
        wordCnt = 0;
    }    
    charCnt = textBuffer.length() - 1;
    bool lastWasSpace = true;
    for (unsigned int cnt = 0; cnt < toSet.length() - 1; cnt++) {
        if (toSet.at(cnt) != ' ' && lastWasSpace) {
            wordCnt++;
            lastWasSpace = false;
        }
        else if (toSet.at(cnt) == ' ')
            lastWasSpace = true;
    }
}

void TextFile::ClearBuffer() { 
    textBuffer = "";
    charCnt = 0;
    wordCnt = 0;
}