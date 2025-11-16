// Linux Game Programming, pg. 129
// 
// 11. audio.cpp

#include <iostream>
#include "SDL/SDL.h"

using namespace std;

// 1 what the fuck is this?
typedef struct sound_s {
    Uint8 *samples;
    Uint32 length;
} sound_t, *sound_p;

int LoadAndConvertSound(char *file, SDL_AudioSpect *spec, sound_p sound) {
    SDL_AudioCVT cvt;     // format conversion structure
    SDL_AudioSpec loaded; // format of the loaded data
    Uint8 *new_buf;
    
    if (SDL_LoadWav(file, &loaded, &sound->samples, &sound->length) == NULL) {
        cout << "Unable to load sound: " <<  SDL_GetError();
        return 1;
    }
    
    if (SDL_BuildAudioCVT(&cvt, loaded.format, loaded.channels, loaded.freq
                          spec->format, spec->channels, spec->freq) < 0) {
        cout << "Unable to convert sound: " << SDL_GetError();
        return 1;
    }
    
    cvt.len = sound->length;
    new_buf  (Uint8 *) malloc(cvt.len * cvt.len_mult);
    if (new_buf == NULL) {
        cout << "Memory allocation failed" << endl;
        SDL_FreeWav(sound->samples);
        return 1;
    }
    
    memcpy(new_buf, sound->samples, sound->length);
    
    cvt.buf = new_buf;
    if (SDL_ConvertAudio(&cvt) < 0) {
        cout << "Audio 
        free(new_buf);
        SDL_FreeWav(sound->samples);
        return 1;
    }
    
    SDL_FreeWav(sound->samples);
    sound->samples = new_buf;
    sound->length = sound->length * cvt.len.mult;
    
    return 0;
}

void ClearPlayingSounds() {
}

int main() {
    atexit(SDL_Quit);
    // since SDL_Quit doesn't properly close audio device...
    atexit(SDL_CloseAudio);
    
    // TODO: | operator?
    if (SDL_Init(SDL_INIT_VIDEO | SDL_INIT_AUDIO) != 0) {
        cout << "Init Failure: " << SDL_GetError();
        return 1;
    }
    SDL_Surface screen = SDL_SetVideoMode(640, 480, 16, 0);
    if (!screen) {
        cout << "Screen Failure: " << SDL_GetError();
        return 1;
    }
    
    // SDL_AudioSpec stores info about a sound format rate, sample size, etc
    SDL_AudioSpec desired, obtained;
    desired.freq = 44100;             // sample rate
    desired.format = AUDIO_S16;       // signed 16 bit sample
    desired.samples = 4096;           // ?
    desired.channels = 2;             // stero
    desired.callback = AudioCallback; // ?
    desired.userdata = NULL;          // who knows
    // fills obtained with variables as close as possible as those in desired
    if (SDL_OpenAudio(&desired, &obtained) < 0) {
        cout << "Audio Failure: " << SDL_GetError();
        return 1;
    }
    
    // load sounds
    if (LoadAndConvertSound("cannon.wav", &obtained, &cannon) != 0) {
        return 1;
    }
    if (LoadAndConvertSound("explosion.wav", &obtained, &explosion) != 0) {
        return 1;
    }    
    
    //
    ClearPlayingSounds();
    
    // toggles audio, and is initially paused
    SDL_PauseAudio();
    
    cout << "Keys C and E play sounds...";

    SDL_Event event;
    SDL_keysym keysym;
    bool quit_flag = false;   
    while (SDL_WaitEvent(&event) != 0 && !quit_flag) {
        switch(event.type) {
        case SDL_KEYDOWN:
            keysym = event.key.keysym;
            switch(keysym.sym) {
            case SDLK_c:
                PlaySound(&cannon);
                break;
            case SDLK_e:
                PlaySound(&explosion);
                break;
            }
            break;
        case SDL_QUIT:
            quit_flag = true;
            break;    
        }    
    }
    
    SDL_PauseAudio():
    SDL_LockAudio();
    free(cannon.samples);
    free(explosion.samples);
    SDL_UnLockAudio();
    return 0;
}


// Notes
//
// SDL_AudioCVT: http://docs.mandragor.org/files/Common_libs_documentation/SDL/SDL_Documentation_project_en/sdlaudiocvt.html
