// Client.java
//
//
//

package client;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client extends Observable {

    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private Socket socket;

    public Client() {
        socket = new Socket();
    }

    private class ConnectRun implements Runnable {
        public void run() {
            try {
                while (!socket.isClosed()) {
                    handle(objectIn.readObject());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (EOFException e) {
                disconnect();
            } catch (SocketException e) {
                if (!e.getMessage().equals("Socket closed")) {
                    e.printStackTrace();
                    disconnect();
                }
            } catch (SocketTimeoutException e) {
                System.out.println("error: connection timed out");
                disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void connect(String host, int port) throws IOException {
        if (isConnected()) {
            disconnect();
        }
        socket = new Socket(host, port);
        socket.setSoTimeout(20000);
        objectOut = new ObjectOutputStream(socket.getOutputStream());
        objectIn = new ObjectInputStream(socket.getInputStream());
        new Thread(new ConnectRun()).start();
        System.out.println("connected to " + getServerHost());

        setChanged();
        notifyObservers(isConnected());
    }

    public void disconnect() {
        try {
            objectIn.close();
            objectOut.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("disconnected from " + getServerHost());
        socket = new Socket();

        setChanged();
        notifyObservers(isConnected());
    }

    public String getServerHost() {
        return socket.getInetAddress().getHostAddress();
    }

    public void handle(Object o) {
        System.out.println("Client.handle :" + o.toString());
    }

    public boolean isConnected() { return socket.isConnected(); }

    public void send(Object o) {
        try {
            objectOut.writeObject(o);
            objectOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
