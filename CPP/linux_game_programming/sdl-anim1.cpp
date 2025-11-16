// Linux Game Programming, pg. 98
// Blits squares at random locations and moves them randomly for xxx frames.
// 6. sdl-anim1.cpp

#include <iostream>
#include "SDL/SDL.h"
#include "SDL/SDL_image.h"

using namespace std;

const int NUM_ACTORS = 4;
const int MAX_SPEED = 8;

class Actor {
    private:
        int x, y, dx, dy;
    
    public:
        void setx(int a) { x = a; }
        void sety(int a) { y = a; }
        void setdx(int a) { dx = a; }
        void setdy(int a) { dy = a; } 

        int getx() { return x; }
        int gety() { return y; }
        int getdx() { return dx; }
        int getdy() { return dy; }
};

static Actor actors[NUM_ACTORS];
static SDL_Surface *screen, *actor;

static void init_actors() {
    for (int a = 0; a < NUM_ACTORS; a++) {
        // modulus operator precedence...
        actors[a].setx(rand() % (screen->w - actor->w));
        actors[a].sety(rand() % (screen->h - actor->h));
        actors[a].setdx(rand() % (MAX_SPEED * 2) - MAX_SPEED);
        actors[a].setdy(rand() % (MAX_SPEED * 2) - MAX_SPEED);
    }
}

static void move_actors() {
    for (int a = 0; a < NUM_ACTORS; a++) {
        actors[a].setx(actors[a].getx() + actors[a].getdx());
        actors[a].sety(actors[a].gety() + actors[a].getdy());
        
        if (actors[a].getx() <= 0 || actors[a].getx() >= screen->w - actor->w) {
            actors[a].setdx(-actors[a].getdx());
        }
        if (actors[a].gety() <= 0 || actors[a].gety() >= screen->h - actor->h) {
            actors[a].setdy(-actors[a].getdy());        
        }
    }
}

static void draw_actors() {
    SDL_Rect src, dest;
    src.x = 0;
    src.y = 0;
    src.w = actor->w;
    src.h = actor->h;    
    
    for (int a = 0; a < NUM_ACTORS; a++) {
        dest.x = actors[a].getx();
        dest.y = actors[a].gety();
        SDL_BlitSurface(actor, &src, screen, &dest);
    }
}

int main(int argc, char* argv[]) {
    atexit(SDL_Quit);
    if (SDL_Init(SDL_INIT_VIDEO) != 0) {
        cout << "Init Failure: " << SDL_GetError();
        return 1;
    }
    screen = SDL_SetVideoMode(640, 480, 16, 0);
    if (!screen) {
        cout << "Screen Failure: " << SDL_GetError();
        return 1;
    }

    SDL_Surface *background = IMG_Load("resource/img/640bg.bmp");
    actor = IMG_Load("resource/img/actor.png");
    if (!background || !actor) {
        cout << "Image Error: " << SDL_GetError();
        return 1;
    }
    
    init_actors();
    
    SDL_Rect src, dest;
    src.x = 0;
    src.y = 0;   
    src.w = background->w;
    src.h = background->h;
    dest.x = 0;
    dest.y = 0;    
    for (int frames = 0; frames < 100; frames++) {
        SDL_BlitSurface(background, &src, screen, &dest);
        
        draw_actors();
        SDL_UpdateRect(screen, 0, 0, 0, 0);
        
        move_actors();
    }
    
    SDL_FreeSurface(background);
    SDL_FreeSurface(actor);
    return 0;
}

// Notes
//
// Learn c structs
// http://www.cppreference.com/operator_precedence.html
