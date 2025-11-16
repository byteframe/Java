#include "Client.hpp"

Client::Client()
{
    if (!(socket = SDLNet_UDP_Open(0))) {
		console.printError(SDLNet_GetError());
	} else if (!(packet = SDLNet_AllocPacket(512))) {
		console.printError(SDLNet_GetError());
	} else {
		bConnected = false;
	    lastPacketTime = 0;
		return;
	}
	console.setCommand("quit");
}

Client::~Client()
{
	disconnect();
	SDLNet_UDP_Close(socket);
	SDLNet_FreePacket(packet);
}

void Client::connect(const std::string host, const int port)
{
    disconnect();
    if (SDLNet_ResolveHost(&packet->address, host.c_str(), port) == -1) {
		console.printOutput(std::string("error: ")+SDLNet_GetError());
	}
	send("connect");
}

void Client::disconnect()
{
	if (isConnected()) {
		//resolve ip to localhost?	if (client.lastPacketTime/1000 - timer.getTicks()/1000 >= 5) {					client.send("ping");				}
		send("disconnect");
		bConnected = false;
	} else {
		console.printOutput("debug: can't disconnect, not connected");
	}
}

void Client::processInput(std::string& input)
{
	if (input == "disconnect") {
		disconnect();
	} else if (input == "connect") {
		connect("localhost", 4444);
	} else if (input == "close") {
		;
	} else if (input == "bind") {
		;
	} else if (input == "quit") {
		console.setCommand("quit");
	}
}

std::string Client::read()
{
	if (SDLNet_UDP_Recv(socket, packet)) {
		std::string message = std::string((char *)packet->data);
		if (message == "connect") {
			bConnected = true;
		} else if (message == "disconnect") {
			bConnected = false;
		} else {
			console.printOutput(std::string("Client.read(): ")+message);
		}
		return (char *)packet->data;
	}
	return "";
}

void Client::send(const std::string& data)
{
	memcpy(packet->data, data.c_str(), data.length()+1);
	packet->len = data.length()+1;
	SDLNet_UDP_Send(socket, -1, packet);
	//lastPacketTime = XXX;
}
