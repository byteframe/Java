// Linux Game Programming, pg. 113
// Processes some SDL keyboard events.
// 9. keyboard-events.cpp

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
    SDL_keysym keysym;    
    while (SDL_WaitEvent(&event) != 0) {
        switch (event.type) {
        case SDL_KEYDOWN:
            keysym = event.key.keysym;
            cout << "Pressed: " << SDL_GetKeyName(keysym.sym) << " ";
            
            // fiqure out & operator
            if (event.key.keysym.mod & KMOD_LSHIFT 
             || keysym.sym == SDLK_LSHIFT) {
                cout << "(Left Shift is down)" << endl;
            } else {
                cout << "(Left Shift is up)" << endl;
            }
            
            if (keysym.sym == SDLK_q) {
                cout << "Closing!" << endl;
                return 0;       
            }
            break;
        case SDL_KEYUP:
            cout << "Released" << endl;
            break;
        case SDL_QUIT:
            cout << "Closing!" << endl;
            return 0;
        }
    }
    
    return 0;
}    
