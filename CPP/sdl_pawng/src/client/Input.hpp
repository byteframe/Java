#ifndef __INPUT_HPP_
#define __INPUT_HPP_

#include <string>
#include "SDL/SDL_events.h"

//big structure with SDL-keysyms to bind commands

class Input
{
	SDL_Event event;
	int keydown;
public:
	Input();
	std::string getInput();
};

#endif
