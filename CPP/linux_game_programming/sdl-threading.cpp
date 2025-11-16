// Linux Game Programming, pg. 123
// Runs three threads that increment a counter and then closes them all.
// 10. sdl-threading.cpp

#include <iostream>
#include "SDL/SDL.h"
#include "SDL/SDL_thread.h"

using namespace std;

SDL_mutex *counter_mutex;
static int counter = 0;

int ThreadEntryPoint(void *data) {
    char *threadname = (char *)data;
    while (counter < 20) {      
        // in an effort to avoid race hazards we lock the mutex
        SDL_LockMutex(counter_mutex);
        counter++;
        cout << threadname << ": " << counter << endl;
        SDL_UnlockMutex(counter_mutex);

        SDL_Delay(rand() % 2000);
    }
    
    cout << threadname << " is now exiting..." << endl;
    return 0;
}

int main(int argc, char* argv[]) {
    counter_mutex = SDL_CreateMutex();
    
    // see url, learn pointers soon...
    SDL_Thread *thread1 = SDL_CreateThread(ThreadEntryPoint, const_cast<char*>("Thread1"));
    SDL_Thread *thread2 = SDL_CreateThread(ThreadEntryPoint, const_cast<char*>("Thread2"));
    SDL_Thread *thread3 = SDL_CreateThread(ThreadEntryPoint, const_cast<char*>("Thread3"));
    
    while (counter < 20) {
        SDL_Delay(1);
    }

    // give time for all threads to finish
    SDL_Delay(2000);
    SDL_DestroyMutex(counter_mutex);
    return 0;
}


// Notes
//
// http://en.wikipedia.org/wiki/Thread_(computer_science)
// c-style casting http://www.thescripts.com/forum/thread490531.html
