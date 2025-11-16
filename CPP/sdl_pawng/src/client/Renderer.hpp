#ifndef __RENDERER_HPP_
#define __RENDERER_HPP_

#include <string>
#include "SDL/SDL_image.h"
#include "SDL/SDL_ttf.h"
#include "Client.hpp"
#include "../common/Console.hpp"
#include "../common/Game.hpp"

extern Console console;

class Renderer
{
	Client& client;
	Game& game;
	SDL_Color txtColor;
	SDL_Rect dest;
	SDL_Rect src;
	SDL_Surface *screen;
	SDL_Surface *background;
	SDL_Surface *player1_surf;
	SDL_Surface *player2_surf;
	SDL_Surface *ball;
	SDL_Surface *netstate_surf;
	TTF_Font *font;
	SDL_Surface* loadImage(std::string);
	//int screen_w;
	//int screen_h;
	//SDL_Surface* createNullImage(const int& w, const int& h);
public:
	Renderer(Client&, Game&);
	~Renderer();
	void render();
	//void setScreenSize(const int, const int);
};

#endif
