#ifndef __SERVER_HPP_
#define __SERVER_HPP_

#include <string>
#include <vector>
#include "SDL/SDL_net.h"
#include "../common/Console.hpp"
#include "../common/Game.hpp"
#include "../common/Timer.hpp"

extern Console console;

class Server
{
	Game& game;
	class InnerClient
	{
	public:
		IPaddress ip;
		int lastPacketTime;
		// random int (qport)
		// UDPpacket packet;
		InnerClient(IPaddress i)
		{
			ip = i;
			lastPacketTime = 0;
		}
	};
    std::vector<InnerClient> clients;
    UDPpacket* packet;
    UDPsocket socket;
public:
    Server(Game&);
    ~Server();
    void bind(const int);
    void close();
	bool isBound() const { return (socket != NULL); }
    std::string read();
    void send(const std::string);
    void send(const std::string, const int);
	void update();
};

#endif
