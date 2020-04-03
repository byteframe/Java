// Server.java
//
//
//

package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private Vector<Client> clients;
    private ServerSocket serverSocket;
    private Timer timer;

    public Server() {
        clients = new Vector<Client>();
    }

    private class Client implements Runnable {

        private ObjectInputStream objectIn;
        private ObjectOutputStream objectOut;
        private Socket socket;

        public Client(Socket s) throws IOException {
            socket = s;
            objectOut = new ObjectOutputStream(socket.getOutputStream());
            objectIn = new ObjectInputStream(socket.getInputStream());
        }

        public void run() {
            try {
                while (!socket.isClosed()) { 
                    handle(objectIn.readObject(), clients.indexOf(this));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (EOFException e) {
                disconnectClient(clients.indexOf(this));
            } catch (SocketException e) {
                e.printStackTrace();
                disconnectClient(clients.indexOf(this));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void bind(int port) throws IOException {
        bind(port, null);
    }
    public void bind (int port, String host) throws IOException {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                send(null);
            }
        }, 10000, 10000);
        serverSocket = new ServerSocket(port, -1, InetAddress.getByName(host));
        System.out.println("bound to " +
          serverSocket.getInetAddress().getHostAddress() + ":" + port);
        while (true) {
            try {
                connectClient(serverSocket.accept());
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private void connectClient(Socket s) throws IOException {
        clients.add(new Client(s));
        System.out.println(s.getInetAddress().getHostAddress() + " connected");
        new Thread(clients.lastElement()).start();
    }

    protected void disconnectClient(int c) {
        try {
            clients.get(c).objectIn.close();
            clients.get(c).objectOut.close();
            clients.get(c).socket.close();
        } catch (SocketException e) {
            if (!e.getMessage().equals("Socket closed")) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(getClientHost(c) + " disconnected");
        clients.remove(c);
    }

    public String getClientHost(int c) { 
        return clients.get(c).socket.getInetAddress().getHostAddress(); 
    }

    protected void handle(Object o, int c) { 
        System.out.println("Server.handle: " + o.toString());
    }

    protected synchronized void resetClientStream(int c) { 
        try {
            clients.get(c).objectOut.reset();
        } catch (IOException e) { 
            e.printStackTrace();
        }
    }

    protected void send(Object o) {
        for (int c = clients.size() - 1; c > -1; c--) {
            send(o, c);
        }
    }
    protected synchronized void send(Object o, int c) {
        try {
            clients.get(c).objectOut.writeObject(o);
            clients.get(c).objectOut.flush();
        } catch (SocketException e) {
            if (e.getMessage().equals("Broken pipe")) {
                System.out.println("error: broken pipe on " + getClientHost(c));
            } else if (!e.getMessage().equals("Socket closed")) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
