#include <csignal>
#include <iostream>
#include "SDL/SDL.h"
#include "SDL/SDL_net.h"
#ifndef __DEDICATED__
#include "client/Client.hpp"
#include "client/Input.hpp"
#include "client/Renderer.hpp"
#endif
#include "common/Console.hpp"
#include "common/Game.hpp"
#include "server/Server.hpp"

Console console;

void quit(int parameter)
{
	console.setCommand("quit");
}

#ifdef __DEDICATED__
int watch_stdin(void* parameter)
{
	std::string line;
	while (std::cin >> line) {
		console.printOutput(line);
	}
	return 0;
}
#endif

int main(int argc, char* argv[])
{
	console.printOutput("sdl_pawng started");
	if (SDL_Init(0) == -1 || SDLNet_Init() == -1) {
		console.printError(SDL_GetError());
	} else {
		for (int i = 1; i+1 < argc; i += 2) {
			console.setCommand(argv[i], argv[i+1]);
		}
		Game game;
		Server server(game);
server.bind(4444);
#ifndef __DEDICATED__
		Client client;
		Input input;
		Renderer renderer(client, game);
#else
		SDL_Thread* watch_stdin_thread;
		watch_stdin_thread = SDL_CreateThread(watch_stdin, NULL);
#endif
		signal(SIGINT, quit);
		while (!console.isCommandSet("quit")) {
#ifndef __DEDICATED__
			std::string input_cmd = input.getInput();
			client.processInput(input_cmd);
			//game.predictInput(input_cmd); // pther thread!!!!
			if (client.isConnected()) {
				client.send(input_cmd);
				client.read();
			}
			renderer.render();
#endif
			if (server.isBound()) {
				server.read();
				server.update();
				//game.update();
			}
		}
#ifdef __DEDICATED__
		SDL_KillThread(watch_stdin_thread);
#endif
	}
	SDLNet_Quit();
	SDL_Quit();
	console.printOutput("sdl_pawng stopped");
	return 0;
}
