import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public final class ChatD {	

    private ServerSocket serverSocket;
    private LinkedList<ChatClient> clients;
    private String clientList;

    public ChatD(int port) {	
        try {		
            serverSocket = new ServerSocket(port);
            clients = new LinkedList<ChatClient>();
            clientList = "/list ";

            while (true) {
                clients.add(new ChatClient(serverSocket.accept()));
                new Thread(clients.getLast()).start();
            }
        } catch (BindException be) {
            System.out.println(be.getMessage());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /*
    public ChatD(int port, printStream) {
        super(port);
        System.setOut(printStream);
    }*/

    private class ChatClient implements Runnable {

        private String name;
        private Socket socket;
        private DataInputStream inStream;
        private DataOutputStream outStream;

        public String getName() { return name; }

        public ChatClient(Socket s) {
            socket = s;
            name = "";
        }

        public void run() {
            try {
                inStream = new DataInputStream(socket.getInputStream());
                outStream = new DataOutputStream(socket.getOutputStream());
                setName(inStream.readUTF());

                writeToClients("* " + name + " connected.");                                
                while (true) {
                    String read = inStream.readUTF();

                    if (read.charAt(0) == '/') {
                        if (read.startsWith("name ", 1)) {
                            setName(read.substring(6));
                        } else if (read.startsWith("whisper ", 1)) {
                            writeToClients(name + " whispers: "  // Fugly.
                              + read.substring(9, read.indexOf('`'))
                              , read.substring(read.indexOf('`') + 1)); 
                        } else if (read.startsWith("word", 1)) {
                            writeToClients("* word.");
                        }
                    } else {
                        writeToClients(name + ": " + read);
                    }
                }
            } catch (EOFException eofe) {
                writeToClients("* " + name + " dropped on client disconnect.");                    
            } catch (IOException ioe) {
                writeToClients("* " + name + " dropped on " + ioe.toString());
            } finally {
                clientList = clientList.replaceFirst(name + "`", "");
                clients.remove(this);
                if (!clients.isEmpty()) {
                    writeToClients(clientList);
                }
            }
        }

        private void setName(String n) {
            if (n.length() > 0) {
                n = n.replace(' ' ,'_');
                if (!n.equals(name)) { // Doesn't check if random name is taken.
                    for (int c = clients.size() - 1; c > -1; c--) {
                        if (clients.get(c).getName().equals(n)) {
                            n += new Random().nextInt(4444);
                        }
                    }
                    if (name.equals("")) {
                        clientList += n + "`"; 
                    } else {
                        writeToClients("* " + name + " is now known as " + n);
                        clientList = clientList.replaceAll(name + "`", n + "`");                                                        
                    }
                    name = n;                  
                    writeToClients(clientList);
                }               
            } else {
                setName("Unknown MoFugga");
            }
        }

        private void writeToClient(String s) throws IOException {
            outStream.writeUTF(s);
        }
    }
    
    private void writeToClients(String msg) {
        try {
            System.out.println(msg);
            for (int c = clients.size() - 1; c > -1; c--) {
                clients.get(c).writeToClient(msg);
            }
        } catch (SocketException se) {
        } catch (IOException ioe) {
            ioe.printStackTrace();        
        }
    }
    
    private void writeToClients(String msg, String recv) { // Fugly.
        try {     
            String[] recievers = recv.split("`");
          for (int c = clients.size() - 1; c > -1; c--) {
              for (int r = recievers.length - 1; r > -1; r--) {
              if (clients.get(c).getName().equals(recievers[r])) {
                clients.get(c).writeToClient(msg);
              }	
              }         		
          }                
        } catch (SocketException se) {
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }    	
    }
      
    public static void main(String[] args) { // Fugly.
        List argList = Arrays.asList(args); 
        int port = 4444;
        if (argList.indexOf("-port") > -1 
         && argList.get(argList.indexOf("-port") + 1) != null) {
            port = Integer.parseInt
             ((String)argList.get(argList.indexOf("-port") + 1));
        }
        
        System.out.println("starting ChatD on port " + port);
        new ChatD(port);
    }
}
