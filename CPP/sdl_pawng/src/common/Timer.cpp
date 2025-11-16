#include "Timer.hpp"

Timer::Timer()
{
    bPaused = false;
    bStarted = true;
    pauseTicks = 0;
    startTicks = SDL_GetTicks();
}

int Timer::getTicks() const
{
    if (bStarted) {
		if (bPaused) {
			return pauseTicks;
		} else {
			return SDL_GetTicks() - startTicks;
		}
    } else {
        return 0;
    }
}

void Timer::pause()
{
    if (bStarted && !bPaused) {
        pauseTicks = SDL_GetTicks() - startTicks;
        bPaused = true;
    }
}

void Timer::start()
{
    bPaused = false;
    bStarted = true;
    startTicks = SDL_GetTicks();
}

void Timer::stop()
{
    bPaused = false;
    bStarted = false;
}

void Timer::unpause()
{
    if (bPaused) {
        bPaused = false;
        startTicks = SDL_GetTicks() - pauseTicks;
        pauseTicks = 0;
    }
}
