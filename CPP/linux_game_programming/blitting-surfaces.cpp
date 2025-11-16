// Linux Game Programming, pg. 84
// Blits a bitmap on a SDL_Surface.
// 3. blitting-surfaces.cpp

#include <iostream>
#include "SDL/SDL.h"

using namespace std;

int main(int argc, char *argv[]) {
	cout << argv[0] << endl;

    atexit(SDL_Quit);
	
	if (SDL_Init(SDL_INIT_VIDEO) != 0) {
	    cout << "Init Failure: " << SDL_GetError() << endl;
      return 1;
	}
	cout << argv[1];
	
    SDL_Surface *image;
    if (argv[1]) {
        image = SDL_LoadBMP(argv[1]);   
    } else {
        image = SDL_LoadBMP("resource/img/test-img.bmp");
    }
	// returns a point to a new surface containing the loaded bmp	
	if (!image) {
	    cout << "Image Failure: " << SDL_GetError() << endl;
        return 1;
	}
	
	SDL_Surface *screen = SDL_SetVideoMode((int)image->w, (int)image->h, 16, 0);
	if (!screen) {
	    cout << "Screen Failure: " << SDL_GetError() << endl;
        return 1;
	}

	// rect structs are used to specify the region ot the image to blit
	// as well as the destination point for blitting on the screen
	SDL_Rect src, dest;
	src.x = 0;
	src.y = 0;
	src.w = image->w;
	src.h = image->h;
	dest.x = 0;
	dest.y = 0;
    
	// src surface to blit, src region to blit, dest surface, dest point 
	SDL_BlitSurface(image, &src, screen, &dest);
	SDL_UpdateRect(screen, 0, 0, 0, 0);

	SDL_Delay(4444);
	SDL_FreeSurface(image);
	return 0;
}
