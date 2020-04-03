// Tacts.java
//
//
//

import gui.*;
import xml.*;

public class Tacts {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ContactList contactlist = new ContactList();
                TactsFrame tactsFrame = new TactsFrame();
                
                // Java doesn't have a destructor for ContactList,
                // and finalize() doesn't seem to get called on
                // System.exit(0), so use Runtime.
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    public void run() {
                        System.out.println("Tacts: Saved Contact List");
                        ContactList.saveContactList();
                    }
                });
            }
        });
    }
}
