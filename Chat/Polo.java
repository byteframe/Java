import java.net.*;
import java.io.*;
import java.util.*;

public class Polo {
	
    private static int PORT = 4324;

    public static void main(String [] args) throws IOException {
        System.out.println("POLOd\n");
        char c;
        String inputString = "", outputString = "";
        Scanner keys = new Scanner(System.in);
        ServerSocket server = new ServerSocket(PORT);
        Socket socket = server.accept();
      	DataInputStream in = new DataInputStream(socket.getInputStream());
      	DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        while(true) {
            // Get message from Marco
            c = in.readChar();
            inputString = "" + c;
            while(c != '@') {
                c = in.readChar();
                if (c == '@') {
                    break;
                }
                inputString += c;
            }
            
            System.out.println("Received: " + inputString);

            // Get input from user
            System.out.print("Type:" );
            outputString = keys.nextLine();

            // Send message to Polo
            out.writeChars(outputString + "@");
            System.out.println("Sent: " + outputString);
        }
    }
}
