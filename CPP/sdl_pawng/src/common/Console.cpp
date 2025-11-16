#include "Console.hpp"

Console::Console()
{
	print_mutex = SDL_CreateMutex();
	/*FILE* config_file = fopen("data/config.cfg", "r");
	if (config_file != NULL) {
		//load config
	} else {
		config_file = fopen("data/default_config.cfg", "r");
		if (config_file != NULL) {
			;//load config
		} else {
			printError("default config file not found!");
			setCommand("quit");
			return;
		}
	}
	fclose(config_file);*/
}

Console::~Console()
{
	/*FILE* config_file = fopen("data/config.cfg", "w");
	if (config_file != NULL) {
		//save config
		fclose(config_file);
	} else {
		printError("can't output config");
	}*/
	SDL_DestroyMutex(print_mutex);
}

std::string Console::getCommand(const std::string& command)
{
	return convars[command];
}

bool Console::isCommandSet(const std::string& command)
{
	return !(convars.find(command) == convars.end());
}

void Console::parseCommandLine(const std::string& line)
{
	size_t i = 0;
	while (i < line.size()) {
		std::string command = line.substr(i, line.find(' ', i+1)-i);
		i += command.size()+1;
		if (line.at(i) == '"') {
			size_t j = line.find('"', i+1);
			setCommand(command, line.substr(i+1, j-i-1));
			i = j+2;
		} else {
			size_t j = line.find(" ", i+1);
			setCommand(command, line.substr(i, j-i));
			i = j+1;
		} 
	}
}

void Console::printError(const std::string& message)
{
	SDL_LockMutex(print_mutex);
	std::cerr << "error: " << message << std::endl;
	SDL_UnlockMutex(print_mutex);
}

void Console::printOutput(const std::string& message)
{
	SDL_LockMutex(print_mutex);
	std::cout << message << std::endl;
	SDL_UnlockMutex(print_mutex);
}

void Console::setCommand(const std::string& command)
{
	setCommand(command, "1");
}
void Console::setCommand(const std::string& command, const std::string& value)
{
	convars[command] = value;
}
