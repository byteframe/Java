#include <iostream>
#include "SDL/SDL.h"
#include "SDL/SDL_image.h"

using namespace std;

static bool game_over = false;
static SDL_Surface *screen;

// passing parameters to threads????????????????
int render(void *data) {
    screen = SDL_SetVideoMode(640, 480, 24, 0);
    
    SDL_Rect src, dest;
    src.x = 0;
    src.y = 0;

    SDL_Surface *format = IMG_Load("resource/img/640bgtetris"); 
    SDL_Surface *background = SDL_DisplayFormat(format);    
    //format = IMG_Load
    // sdifonsdoifn

    while (!game_over) {
        src.w = background->w;
        src.h = background->h;
        dest.x = 0;
        dest.y = 0;
        //SDL_BlitSurface(background, &src, screen, &dest);
        //for() { blit played grid
        // blit piece in play
        //SDL_UpdateRect(screen, 0, 0, 0, 0);
        cout << "rect" << endl;
    } return 0;
}

int main(int argc, char* argv[]) {
    atexit(SDL_Quit);
    if (SDL_Init(SDL_INIT_VIDEO) != 0) {
        cout << "Init Failure: " << SDL_GetError();
    }

    // rendering thread
    SDL_Thread *renderThread = SDL_CreateThread(render, NULL);
/*
    // input
    SDL_Event loop;
    while (SDL_WaitEvent(&loop) != 0) {
        switch(loop.type) {
        case SDL_KEYDOWN:
            switch(loop.key.keysym.sym) {
            case SDLK_UP:
                //falling.rotate();
                break;
            case SDLK_DOWN:
                //falling.drop();
                break;
            case SDLK_LEFT:
                //falling.moveleft();
                break;
            case SDLK_RIGHT:
                //falling.moveright();
                break;
            default:
                break;
            };
        case SDL_QUIT:
            return 0;
        default:
            break;
        }
    }*/

    return 0;
}
/*
class TetrisBlock {
    private:
        int type;
    public:
        void rotate();
        void lower();
};

class BlockType {
    private
}*/
