#include "Input.hpp"

Input::Input()
{
	keydown = 0;
}

std::string Input::getInput()
{
	if (SDL_PollEvent(&event)) {
		switch (event.type) {
		case SDL_KEYDOWN:
			switch (event.key.keysym.sym) {
case SDLK_UP:
	return "moving_UP";
case SDLK_DOWN:
	return "moving_DOWN";
case SDLK_F1:
	return "disconnect";
case SDLK_F2:
	return "connect";
case SDLK_F3:
	return "close";
case SDLK_F4:
	return "bind";
case SDLK_F5:
	return "quit";
			default:
				;//return value of input_KEY
			}
			break;
		case SDL_KEYUP:
			break;//return "stop_moving";
		case SDL_QUIT:
			return "quit";
		}
	}
	return "";
}
