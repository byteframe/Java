import java.net.*;
import java.io.*;
import java.util.*;

public class Marco {
	
    private static int PORT = 4324;

    public static void main(String [] args) throws IOException {
        System.out.println("MARCO\n");

        char c;
        String inputString = "", outputString = "";
        Scanner keys = new Scanner(System.in);
        Socket socket = new Socket("localhost", 4324); //to polo
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		
        while(true) {	
            // Get response from Polo
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
