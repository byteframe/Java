#ifndef __GAME_HPP_
#define __GAME_HPP_

#include <string>
#include "Entity.hpp"

class Game
{
	Entity ball;
	Entity p1;
	Entity p2;
public:
	Game();
	//update();?
	void predictInput(const std::string&);
};

#endif
