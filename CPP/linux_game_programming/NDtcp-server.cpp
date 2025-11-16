// Linux Game Programming, pg. 287
// Server that listens for requests from tcp-clientSock. Bogus/Doesn't work.
// 13. tcp-server.cpp

#include <iostream>
#include <errno.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <signal.h>

using namespace std;

int listenSock, clientSock;

void signal_handler(int signum) {
    switch (signum) {
    case SIGINT:
        cout << "Received interrupt signal. Exiting." << endl;
        close(clientSock);
        close(listenSock);
        exit(0);
    default:
        cout << "Unknown signal received. Ignoring." << endl;
    }
}

int main(int argc, char* argv[]) {
    if (argc < 2) {
        cout << "Sockets TCP/IP Uptime Server" << endl;
        cout << "Usage: <port> " << argv[0] << endl;
        return 1;
    }
    
    struct sockaddr_in listenSA;    
    socklen_t sa_len = sizeof(listenSA);
    memset(&listenSA, 0, sa_len);   
    listenSA.sin_family = AF_INET;
    listenSA.sin_port = htons(atoi(argv[1]));
    // All interfaces
    listenSA.sin_addr.s_addr = htonl(INADDR_ANY);
    
    // Create socket
    listenSock = socket(PF_INET, SOCK_STREAM, IPPROTO_IP);
    if (listenSock < 0) {
        cout << "Unable to create listen socket" << strerror(errno) << endl;
        return 1;    
    }
    
    // Bind socket to listening address
    if (bind(listenSock, (struct sockaddr *)&listenSA, sa_len) < 0) {
        cout << "Unable to bind to port " << argv[1] << strerror(errno) << endl;
        return 1;
    }
    
    // Informs networking to accept connections on this socket.
    // (<"listen socket">, <"max clientSocks(?)">);
    if (listen(listenSock, 4) < 0) {
        cout << "Unable to listen" << strerror(errno) << endl;
        return 1;
    }
    
    signal(SIGINT, signal_handler);
    cout << "Socket bound and listening for client until CTRL-C" << endl;
    bool waiting = true;
    while(waiting) {
        // Recieves incoming connection, 0 on success -1 on failure
        // (<"listen socket">, <"listen sock addr">, <"addr_len">);
        clientSock = accept(listenSock, (struct sockaddr *)&listenSA, &sa_len);
        if (clientSock < 0) {
            cout << "Unable to accept" << strerror(errno) << endl;
            close(listenSock);
            return 1;
        } else {
            waiting = false;
        }
    }
        
    char dotted_ip[15];
    inet_ntop(AF_INET, &listenSA.sin_addr, dotted_ip, 15);
    cout << "Recieved connection from " << dotted_ip << endl;   
    
    char sendbuf[1024];
    // Executes command
    // (<"command">, <"mode `man popen`">);
    FILE *uptime = popen("/usr/bin/uptime", "r");
    if (uptime == NULL) {
        //ss
        strcpy(sendbuf, "Unable to read system uptime");
    } else {
        sendbuf[0] = '\0';
        // Reads characters from stream into a C-string
        // (<"src string">, <"num of chars to read">, <"stream/FILE/stdin">);
        fgets(sendbuf, 1023, uptime);
    }
    pclose(uptime);
    
    //ss
    int length = strlen(sendbuf), sent = 0;
    
    while (sent < length) {
        int amt = write(clientSock, sendbuf+sent, length-sent);
        
        if(amt <= 0) {
            if (errno == EINTR) {
                continue;
            } else {
                cout << "Send Error: " << strerror(errno) << endl;
                break;
            }
            cout << "x";
            sent += amt;  
        }
        
        close(clientSock);
    }
    
    return 0;
}
