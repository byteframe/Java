// PlaymatServerQuery.java
//
//
//

import java.io.*;
import java.net.*;
import java.util.*;

public class PlaymatServerQuery extends Observable {

    private ObjectOutputStream objectOut = null;

    public PlaymatServerQuery(String[] args) {
        try {
            new Thread(new ConnectRun(new Socket(args[0], Integer.parseInt(args[1])))).start();
            List<String> list = Arrays.asList(args);
            String input = "1query";
            if (list.indexOf("-user") > -1
              && list.get(list.indexOf("-user") + 1) != null) {  
                String user = list.get(list.indexOf("-user") + 1);  
                if (list.indexOf("-pass") > -1
                  && list.get(list.indexOf("-pass") + 1) != null) {  
                    String pass = list.get(list.indexOf("-pass") + 1);    
                    input = "3admin$" + user + "$" + pass;
                }      
            }
            objectOut.writeObject(input);
            objectOut.flush();
        } catch (ConnectException e) {
            System.out.println("refused");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ConnectRun implements Runnable {

        private ObjectInputStream objectIn = null;
        private Socket socket = null;

        public ConnectRun(Socket s) throws IOException {
            socket = s;
            socket.setSoTimeout(10000);
            objectOut = new ObjectOutputStream(socket.getOutputStream());
            objectIn = new ObjectInputStream(socket.getInputStream());
        }

        private void disconnect() {
            try {
                objectIn.close();
                objectOut.close();
                socket.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        public void run() {
            try {
                while (true) {
                    System.out.println((String)objectIn.readObject()+"\n");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (EOFException e) {
                disconnect();
            } catch (SocketException e) {
                e.printStackTrace();
                disconnect();
            } catch (SocketTimeoutException e) {
                System.out.println("timeout");
                disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new PlaymatServerQuery(args);
    }
}
