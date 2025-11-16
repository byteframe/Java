// Linux Game Programming, pg. 89
// Blits one image with an alpha channel, and one with per-surface blending.
// 5. alpha-blending.cpp

#include <iostream>
#include "SDL/SDL.h"
#include "SDL/SDL_image.h"

using namespace std;

int main(int argc, char* argv[]) {
    atexit(SDL_Quit);
    if (SDL_Init(SDL_INIT_VIDEO) != 0) {
        cout << "Init Failure: " << SDL_GetError();
        return 1;
    }
    SDL_Surface *screen = SDL_SetVideoMode(400, 400, 16, 0);
    if (!screen) {
        cout << "Screen Failure: " << SDL_GetError();
        return 1;
    }

    SDL_Surface *background = IMG_Load("resource/img/400x400bg.png"),
                *img_with_alpha = IMG_Load("resource/img/img_with_alpha.png"),
                *img_wout_alpha = IMG_Load("resource/img/img_wout_alpha.png");
    if (!img_with_alpha || !img_wout_alpha) {
        cout << "Screen Failure: " << SDL_GetError();
        return 1;
    }
    
    // enables alpha blending on surface, with the per-surface value not used 
    SDL_SetAlpha(img_with_alpha, SDL_SRCALPHA, 0);
    // images with no alpha channel will be doing per-surface blending (128)
    SDL_SetAlpha(img_wout_alpha, SDL_SRCALPHA, 128); 
    
    SDL_Rect src, dest;
    src.x = 0;
    src.y = 0;
    src.w = background->w;
    src.h = background->h;
    dest.x = 0;
    dest.y = 0;
    SDL_BlitSurface(background, &src, screen, &dest); 
    src.w = img_with_alpha->w;
    src.h = img_with_alpha->h;
    dest.x = 0;
    dest.y = 0;
    SDL_BlitSurface(img_with_alpha, &src, screen, &dest);   
    src.w = img_wout_alpha->w;
    src.h = img_wout_alpha->h;
    dest.x = 0;
    dest.y = 200;
    SDL_BlitSurface(img_wout_alpha, &src, screen, &dest);       
    
    SDL_UpdateRect(screen, 0, 0, 0, 0);
    SDL_Delay(4444);
    SDL_FreeSurface(background);
    SDL_FreeSurface(img_with_alpha);
    SDL_FreeSurface(img_wout_alpha);
    return 0;   
}

// Notes
//
// SetAlpha also has support for run length acceleration mode.
