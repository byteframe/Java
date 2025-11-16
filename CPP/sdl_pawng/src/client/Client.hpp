#ifndef __CLIENT_HPP_
#define __CLIENT_HPP_

#include <string>
#include "SDL/SDL_net.h"
#include "../common/Console.hpp"

extern Console console;

class Client
{
	UDPpacket *packet;
	UDPsocket socket;
    bool bConnected;
	int lastPacketTime;
public:
	Client();
	~Client();
	void connect(const std::string, const int);
	void disconnect();
	bool isConnected() const { return bConnected; }
	void processInput(std::string&);
	std::string read();
	void send(const std::string&);
};

#endif
