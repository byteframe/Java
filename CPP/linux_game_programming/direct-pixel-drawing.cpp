// Linux Game Programming, pg. 79
// Directly inserts pixel color data into a window surface.
// 2. direct-pixel-drawing.cpp

#include <iostream>
#include <iomanip>
#include "SDL/SDL.h"

const int bppmode = 16;

using namespace std;

// takes in SDL_PixelFormat (struct detailing surfaces pixel composition),
// 8 bits per component (r,g,b) and returns a 16bit pixel value from logical 
// bit shifts (due to them being unsigned)
Uint16 CreateHicolorPixel(SDL_PixelFormat *fmt, Uint8 r, Uint8 g, Uint8 b) {
    Uint16 value = ((r >> fmt->Rloss) << fmt->Rshift) +
                   ((g >> fmt->Gloss) << fmt->Gshift) +
                   ((b >> fmt->Bloss) << fmt->Bshift);
    return value;
}

int main(int argc, char* argv[]) {
    atexit(SDL_Quit);
    
    if (SDL_Init(SDL_INIT_VIDEO) != 0) {
        cout << "Unable to initialize SDL: " << SDL_GetError();
        return 1;
    }
    
    // sets up the surface for some kind of 16 bbp mode, but we dont yet know
    // the exact pixel format that will be used...
    SDL_Surface *myScreen = SDL_SetVideoMode(256, 256, bppmode, 0);
    if (myScreen == NULL) {
        cout << "Unable to set video mode: " << SDL_GetError();
        return 1;
    }

    cout << "Pixel Format for " << bppmode << "-bit mode:" << endl;
    // bits to remove from an 8-bit value to fit it in the mode
    cout << "RGBA Losses: " <<
             setw(3) << (int)myScreen->format->Rloss <<
             setw(3) << (int)myScreen->format->Gloss <<
             setw(3) << (int)myScreen->format->Bloss <<
             setw(3) << (int)myScreen->format->Aloss << endl;
	  // each color value (rgb and alpha) are 8 bits originally
    cout << "RGBA Format: " <<
	           setw(3) << 8 - myScreen->format->Rloss <<
             setw(3) << 8 - myScreen->format->Gloss <<
             setw(3) << 8 - myScreen->format->Bloss <<
             setw(3) << 8 - myScreen->format->Aloss << endl;
    // bits to shift the value to position it in the correct bit field
    cout << "RGBA Shifts: " <<
             setw(3) << (int)myScreen->format->Rshift <<
             setw(3) << (int)myScreen->format->Gshift <<
             setw(3) << (int)myScreen->format->Bshift << 
             setw(3) << (int)myScreen->format->Ashift << endl;

    // points to array containing surface's pixels member
    Uint16 *raw_pixels = (Uint16 *)myScreen->pixels;

    // writing to pixels requires locking before rw, unlock right after
    SDL_LockSurface(myScreen);
    Uint16 pixel_color;
    for (int x = 0; x < 256; x++) {
        for (int y = 0; y < 256; y++) {
            pixel_color = CreateHicolorPixel(myScreen->format, x, 0, y);
            raw_pixels[myScreen->pitch / 2 * y + x] = pixel_color;
        }
    }
    SDL_UnlockSurface(myScreen);
    
    // updates a specific region of a surface, or all 0's for the entirety
    SDL_UpdateRect(myScreen, 0, 0, 0, 0);
    
    // keeps window open for ms delay.
    SDL_Delay(4444);
    return 0;
}

// Notes:
//
// -i810 driver supports only 24 bit mode
//    http://en.wikipedia.org/wiki/Color_depth#32-bit_color
// -SDL 32bpp on 24bpp conversions?
// -Uint16 is a typedef from SDL_stdinc.h to uint16_t, non cout-able, ints cast
// -Be sure to unlock pixels member after writing...
//    http://www.libsdl.org/docs/html/sdllocksurface.html
// -BitShifts
//    http://en.wikipedia.org/wiki/Bitwise_operation#Logical_shift
