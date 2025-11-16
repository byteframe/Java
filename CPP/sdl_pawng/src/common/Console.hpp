#ifndef __CONSOLE_HPP_
#define __CONSOLE_HPP_

#include <iostream>
#include <map>
#include "SDL/SDL_thread.h"
#include "Timer.hpp"

class Console
{
	std::map<std::string, std::string> convars;
	SDL_mutex* print_mutex;
	Timer timer;
public:
	Console();
	~Console();
	std::string getCommand(const std::string&);
	Timer& getTimer() { return timer; }
	bool isCommandSet(const std::string&);
	void setCommand(const std::string&);
	void setCommand(const std::string&, const std::string&);
	void parseCommandLine(const std::string&);
	void printError(const std::string&);
	void printOutput(const std::string&);
};

#endif
