// Linux Game Programming, pg. 74
// Initializes SDL video system, and creates a surface pointing to fullscreen.
// 1. initializing-sdl.cpp

#include <iostream>
#include "SDL/SDL.h"
using namespace std;

int main(int argc, char* argv[]) {
    // stdlib call to run SDL_Quit (safely close sdl) on process exit.
    atexit(SDL_Quit);

    // sets up a SDL_Surface (chunk of video data, with width, height, etc)
    // used for tiles, others, and even framebuffer/display or window.
    SDL_Surface *myScreen;

    // initializes the sdl video subsystem.
    if (SDL_Init(SDL_INIT_VIDEO) != 0) {
        cout << "Unable to initialize SDL: " << SDL_GetError();
        return 1;
    }

    // returns pointer to surface struct or fb, mode flag is fullscreen, 0 win
    myScreen = SDL_SetVideoMode(1024, 768, 32, SDL_FULLSCREEN);
    if (myScreen == NULL) {
        cout << "Unable to set video mode: " << SDL_GetError();
        return 1;
    }

    // since only a quick window will flash, we need some console love.
    cout << "Success!" << endl;

    return 0;
}
