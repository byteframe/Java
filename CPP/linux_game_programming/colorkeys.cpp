// Linux Game Programming, pg. 89
// Blits two images, one with transparency, via a colorkey.
// 4. colorkeys.cpp

#include <iostream>
#include "SDL/SDL.h"
#include <string>
using namespace std;

int main(int argc, char *argv[]) {
    atexit(SDL_Quit);
    
    if (SDL_Init(SDL_INIT_VIDEO) != 0) {
        cout << "Init Failure: " << SDL_GetError();
        return 1;
    }

    SDL_Surface *screen;
    if (argv[1]) {
        screen = SDL_SetVideoMode(640, 480, 16, SDL_FULLSCREEN);
    } else {
        screen = SDL_SetVideoMode(640, 480, 16, 0);
    }
    if (!screen) {
        cout << "Screen Failure: " << SDL_GetError();
        return 1;
    }
    
    SDL_Surface *background = SDL_LoadBMP("resource/img/640bg.bmp");
    SDL_Surface *image = SDL_LoadBMP("resource/img/colorkeys.bmp");
    if (!background || !image) {
        cout << "Image Failure: " << SDL_GetError();
        return 1;
    }

    SDL_Rect src, dest;
    src.x = 0;
    src.y = 0;
    
    src.w = background->w;
    src.h = background->h;
    dest.x = 0;
    dest.y = 0;
    SDL_BlitSurface(background, &src, screen, &dest);
    
    src.w = image->w;
    src.h = image->h;
    dest.x = 50;
    dest.y = 50;
    SDL_BlitSurface(image, &src, screen, &dest);
    
    // gets the correct pixel value of #ff00ff
    Uint32 colorkey = SDL_MapRGB(image->format, 255, 0, 255);
    // sets colorkey on image, with mode for color
    SDL_SetColorKey(image, SDL_SRCCOLORKEY, colorkey);
    dest.x = 380;
    dest.y = 330;
    SDL_BlitSurface(image, &src, screen, &dest);
    
    SDL_UpdateRect(screen, 0, 0, 0, 0);
    SDL_Delay(4444);
    SDL_FreeSurface(background);
    SDL_FreeSurface(image);
    return 0;
}

// Notes:
//
// Flags for SetColorKey are 0 disables, SDL_SRCCOLORKEY enables blitting and
// SDL_RLEACCEL enables faster run-length acceleration.
