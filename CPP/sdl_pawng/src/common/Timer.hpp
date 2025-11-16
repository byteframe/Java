#ifndef __TIMER_HPP_
#define __TIMER_HPP_

#include <iostream>
#include "SDL/SDL.h"

class Timer
{
    bool bPaused;
    bool bStarted;
    int pauseTicks;
    int startTicks;
public:
    Timer();
    int getTicks() const;
    bool isPaused() const { return bPaused; }
    bool isStarted() const { return bStarted; }
    void pause();
    void start();
    void stop();
    void unpause();
};

#endif
