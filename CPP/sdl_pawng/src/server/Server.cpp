#include "Server.hpp"

Server::Server(Game& _game) :
    game(_game)
{
	socket = NULL;
	if (!(packet = SDLNet_AllocPacket(512))) {
		console.printError(SDLNet_GetError());
		console.setCommand("quit");
	}
}

Server::~Server()
{
	SDLNet_UDP_Close(socket);
	SDLNet_FreePacket(packet);
}

void Server::bind(const int port)
{
	close();
	if (!(socket = SDLNet_UDP_Open(port))) {
		console.printError(std::string("SDLNet_UDP_Open: ")+SDLNet_GetError());
	}
}

void Server::close()
{
/*	if (socket != NULL) {
		SDLNet_UDP_Close(socket);
		socket = NULL;
	}*/
}

std::string Server::read()
{
	if (SDLNet_UDP_Recv(socket, packet)) {
		int cl_index = -1;
		const std::string host = SDLNet_ResolveIP(&packet->address);
		const std::string message = std::string((char *)packet->data);
		for (int i = clients.size()-1; i > -1 && cl_index == -1; i--) {
			if (clients.at(i).ip.host == packet->address.host) {
				cl_index = i;
			}
		}
		if (cl_index == -1 && message == "connect") {
			clients.push_back(InnerClient(packet->address));
			clients.back().lastPacketTime = console.getTimer().getTicks();
			console.printOutput(host+" connected");
			send("connect", clients.size()-1);
		} else if (cl_index > -1) {
			clients.at(cl_index).lastPacketTime = console.getTimer().getTicks();
			if (message == "disconnect") {
				console.printOutput(host+" disconnected");
				clients.erase(clients.begin()+cl_index);
			} else {
				send(message, cl_index);
			}
		}
	}
	return "";
}

void Server::send(const std::string data)
{
	memcpy(packet->data, data.c_str(), data.length()+1);
	packet->len = data.length()+1;
	for (int i = clients.size()-1; i > -1; i--) {
		packet->address = clients.at(i).ip;
		SDLNet_UDP_Send(socket, -1, packet);
	}
}
void Server::send(const std::string data, const int client)
{
	memcpy(packet->data, data.c_str(), data.length()+1);
	packet->len = data.length()+1;
	packet->address = clients.at(client).ip;
	SDLNet_UDP_Send(socket, -1, packet);
}

void Server::update()
{
	for (int i = clients.size()-1; i > -1; i--) {
		if (console.getTimer().getTicks()/1000 - clients.at(i).lastPacketTime/1000 >= 10) {
			//std::cout << i << " timed out" << std::endl;
			send("disconnect");
			clients.erase(clients.begin()+i);
		}
	}
}
