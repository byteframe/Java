#include <string>

class TextFile {
    private:
        std::string lastFile;
        std::string textBuffer;
        short wordCnt;
        short charCnt;
        
    public:
        TextFile();
        TextFile(std::string);
        
        std::string getLastFile() const { return lastFile; }        
        std::string getTextBuffer() const { return textBuffer; }
        int getCharCnt() const { return charCnt; }
        int getWordCnt() const { return wordCnt; }

        void setTextBuffer(std::string toSet, bool willAppend);
        void ClearBuffer();
        
        bool OpenFile(std::string in_filename, bool willAppend);
        void SaveFile(std::string in_filename);
};
