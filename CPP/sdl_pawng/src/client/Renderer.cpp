#include "Renderer.hpp"

Renderer::Renderer(Client& _client, Game& _game) :
	client(_client),
	game(_game)
{
	if (SDL_InitSubSystem(SDL_INIT_VIDEO) == -1) {
        console.printError(std::string("SDL_InitSubSystem: ")+SDL_GetError());
	} else if (!(screen = SDL_SetVideoMode(640, 480, 32, SDL_SWSURFACE))) {
        console.printError(std::string("SDL_SetVideoMode: ")+SDL_GetError());
	} else if (!(background = loadImage("data/img/sdl_pawng-bg.png"))) {
	// || !(player1_surf = loadImage("data/img/sdl_pawng-p1.png"))
	// || !(player2_surf = loadImage("data/img/sdl_pawng-p2.png"))) {
		;
	/*} else if (TTF_Init() == -1) {
        std::cout << "Unable to initialize ttf: " << SDL_GetError() << std::endl;
	} else if (!(font = TTF_OpenFont("data/ttf/courbd.ttf", 50))
	 || !(netstate_surf = TTF_RenderText_Solid(font, "Client is: ", txtColor))
		std::cout << "Unable to render fonts: " << TTF_GetError() << std::endl;*/
	} else {
		src.x = 0;
		src.y = 0;
		SDL_WM_SetCaption("sdl_pawng", NULL);
		return;
	}
	console.setCommand("quit");
}

Renderer::~Renderer()
{
	if (background != NULL) {
		SDL_FreeSurface(background);
	}
    /*SDL_FreeSurface(ball);
	SDL_FreeSurface(player1_surf);
	SDL_FreeSurface(player2_surf);
	SDL_FreeSurface(netstate_surf);
	TTF_CloseFont(font);*/
	TTF_Quit();
	SDL_QuitSubSystem(SDL_INIT_VIDEO);
}

/*SDL_Surface* Renderer::createNullImage(const int& w, const int& h)
{
	SDL_Surface* null_image;
    SDL_LockSurface(null_image);
    for (int x = 0; x < w; x++) {
        for (int y = 0; y < h; y++) {
            pixel_color = CreateHicolorPixel(null_image->format, x, 0, y);
            raw_pixels[null_image->pitch / 2 * y + x] = pixel_color;
		}
	}
    SDL_UnlockSurface(null_image);
}*/

SDL_Surface* Renderer::loadImage(std::string filename)
{	
	SDL_Surface* final_surf;
	SDL_Surface* image_surf = IMG_Load(filename.c_str());
	if (image_surf) {
		final_surf = SDL_DisplayFormat(image_surf);
	} else {
		console.printError(SDL_GetError());
		final_surf = NULL;
	}
	SDL_FreeSurface(image_surf);
	return final_surf;
}

void Renderer::render()
{
	src.w = background->w;
	src.h = background->h;
	dest.x = 0;
	dest.y = 0;
	SDL_BlitSurface(background, &src, screen, &dest);
/*
	dest.x = 150;
	dest.y = 150;
	switch (connection.getNetstate()) {
	case 0:
		SDL_BlitSurface(netstate_surf, &src, screen, &dest);
		break;
	case 1:
		SDL_BlitSurface(netstate_surf1, &src, screen, &dest);
		break;
	case 2:
		SDL_BlitSurface(netstate_surf2, &src, screen, &dest);
	}

	src.w = player1_surf->w;
	src.h = player1_surf->h;
	dest.x = actors[0].getx();
	dest.y = actors[0].gety();
	SDL_BlitSurface(player1_surf, &src, screen, &dest);

	dest.x = actors[1].getx();
	dest.y = actors[1].gety();
	SDL_BlitSurface(player2_surf, &src, screen, &dest);
*/
	SDL_Flip(screen);
}
