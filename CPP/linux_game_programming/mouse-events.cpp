// Linux Game Programming, pg. 108
// Processes some SDL mouse events.
// 8. mouse-events.cpp

#include <iostream>
#include "SDL/SDL.h"

using namespace std;

int main(int argc, char* argv[]) {
    atexit(SDL_Quit);
    
    if(SDL_Init(SDL_INIT_VIDEO) != 0) {
        cout << "Init Failure: " << SDL_GetError();
        return 1;
    }
    
    SDL_Surface *screen = SDL_SetVideoMode(640, 480, 16, 0);
    if(!screen) {
        cout << "Screen Failure: " << SDL_GetError();
        return 1;
    }

    SDL_Event event;
    while (SDL_WaitEvent(&event) != 0) {
        switch(event.type) {
        case SDL_MOUSEMOTION:
            cout << "Position: " << event.motion.x << "," << event.motion.y
                 << endl;
            break;
        case SDL_MOUSEBUTTONDOWN:
            cout << "Clicked: " << event.motion.x << "," << event.motion.y
                 << endl;
            break;
        case SDL_QUIT:
            cout << "Closing!" << endl;
            return 0;
        }
    }
    
    return 0;
}
