// Linux Game Programming, pg. 279
// Client that connects to tcp-server. Bogus/Doesn't work.
// 12. tcp-client.cpp

#include <iostream>
#include <errno.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <signal.h>

using namespace std;

int main(int argc, char* argv[]) {
    if (argc < 3) {
        cout << "Sockets TCP/IP Client" << endl;
        cout << "Usage: <hostname or ip> <port> " << argv[0] << endl;
        return 1;
    }
    
    // Looks up hostname with DNS.
    struct hostent *hostlist = gethostbyname(argv[1]);
    if (hostlist == NULL) {
        cout << "Unable to resolve" << argv[1] << endl;
        return 1;
    }
    
    // Skipping ipv6 check.
    
    // Converts a binary 32 bit addr from hostlist to dotted string notation
    // (<family of addr>, <src addr>, <dst string>, <?>);
    char dotted_ip[15];
    inet_ntop(AF_INET, hostlist->h_addr_list[0], dotted_ip, 15);
    cout << "Resolved " << argv[1] << " to " << dotted_ip << endl;
    
    // Struct detailing destination of connection attempt. in for inet.
    // [<sin_port "port in 16bit network byte order">,
    //  <sin_addr "ip addr in 32bit nbo, in an in_addr struct">]
    struct sockaddr_in connectSA;
    // "Zero Out" structure using memset (from string.h) "is weird, see docs"
    // (<"dest pointer">, <"value to fill">, <"num of bytes to be filled">);
    memset(&connectSA, 0, sizeof(struct sockaddr_in));

    // Set family to AF_INET for internet socket
    connectSA.sin_family = AF_INET;

    // Converts port system byte order of int type to network byte order
    // htons(16bit, ports) htonl (32bit, addresses) ntohs, ntohl, converts back
    connectSA.sin_port = htons(atoi(argv[2]));
    
    // Memcpy copies memory, and is used to fill; in sin_addr. 
    // (<"dest pointer">, <"src pointer">, <?>)
    memcpy(&connectSA.sin_addr, hostlist->h_addr_list[0], hostlist->h_length);
    
    // Returns a descriptor (short int) or -1 of a socket, used later with a 
    // sockaddr struct and connect();
    // (<"communications domain" `man socket` PF_INET4, or PF_INET6, 
    //  <SOCK_DGRAM for udp or SOCK_STREAM for tcp>, <IPPROTO_IP>);
    int connectSock = socket(PF_INET, SOCK_STREAM, IPPROTO_IP);
    if (connectSock < 0) {
        cout << "Unable to create connection socket" << strerror(errno) << endl;
        return 1;
    }
    
    // Connects the socket with detailed address, -1 on fail.
    // (<"socket">, <"sockaddr struct">, <"size of network structure">); (*)
    if (connect(connectSock, (struct sockaddr *)&connectSA, sizeof(connectSA)) < 0) {
        cout << "Unable to connect socket" << strerror(errno) << endl;
        return 1;
    }
    
    cout << "Connected! Reading data, CTRL-C quits." << endl; 
    for(;;) {
        char ch;
        int amt;
        
        // Reads one byte from socket, -1 on error, 0 on closed socket
        // (<"src socket">, <"dest buffer">, <"number of bytes to read">); (*)
        amt = read(connectSock, &ch, 1);
        if (amt < 0) {
            cout << "Read Error" << endl;
            break;
        } else if (amt == 0) {
            cout << "Connection closed by remote host" << endl;
            break;
        }
        
        cout << ch;
        //putchar(ch);
        //fflush(stdout);
    }
    
    close(connectSock);
    return 0;
}

// Notes:
//
// connect requires a struct sockaddr, so cast it from specific sockaddr_in
// Call to read one byte in at a time, and therefore sucks.
// 
