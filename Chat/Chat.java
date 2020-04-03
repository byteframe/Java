import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;

public final class Chat extends JFrame {

    private String name;
    private Socket socket;
    private DataOutputStream outStream;
    private DataInputStream inStream;   
    private JMenuItem disconnectMI; // Fugly.   
    private JTextArea chatArea;
    private JTextField chatField;
    private JList chatList;

    public Chat() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                disconnect();
            }
        });

        try { 
            name = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException une) {
            name = "";
            System.out.println("Error: Couldn't determine local hostname.");        
        }
        socket = new Socket();

        spawnGui(); 
    }
    
    private class Connected implements Runnable {

        public Connected(String a, String p) throws IOException {
            socket = new Socket(a, Integer.parseInt(p));
            outStream = new DataOutputStream(socket.getOutputStream());
            inStream = new DataInputStream(socket.getInputStream());
        }

        public void run() {
            try {
                disconnectMI.setEnabled(true);
                chatField.setEnabled(true);

                writeToServer(name);
                
                while (true) {
                    String read = inStream.readUTF();                 
                    if (read.charAt(0) == '/') {
                        if (read.startsWith("list ", 1)) {
                            chatList.setListData(read.substring(6).split("`"));
                        }
                    } else {
                        chatArea.append(read + "\n");
                    }
                }
            } catch (SocketException se) {
                chatArea.append("* Client disconnects.\n");
            } catch (EOFException eofe) {
                chatArea.append("* Connection to server lost.\n");                  
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                disconnectMI.setEnabled(false);
                chatField.setEnabled(false);
                chatList.setListData(new Object[0]);
            }
        }
    }
    
    private void connect(String a, String p) throws IOException {
        new Thread(new Connected(a, p)).start();    
    }
      
    private void disconnect() {
        try {
            socket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void writeToServer(String s) {
        try {
            outStream.writeUTF(s);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }        
    }

    private void spawnGui() {
        getContentPane().setPreferredSize(new Dimension(640,480));
        setTitle("Chat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(
            (Toolkit.getDefaultToolkit().getScreenSize().width - 640) / 2,
            (Toolkit.getDefaultToolkit().getScreenSize().height - 480) / 2);
        setResizable(true);

        setJMenuBar(new JMenuBar() {
            {
                add(new JMenu("Server") {
                    {
                        add(new JMenuItem("Connect") { 
                            {
                                addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        String ap = JOptionPane.showInputDialog(
                                            ((JComponent)e.getSource())
                                            , "Input: <server:ip>"
                                            , "localhost:4444"
                                        );
                                        if (ap != null) {
                                            String[] svr = 
                                                    ap.trim().split("[:]", 2);
                                            try {
                                                disconnect();
                                                if (svr.length == 2 
                                                  && svr[1].matches("[0-9]+")) {
                                                    connect(svr[0], svr[1]);
                                                } else {
                                                    connect(svr[0], "4444");
                                                }
                                            } catch (IOException ioe) {
                                                JOptionPane.showMessageDialog(
                                                    ((JComponent)e.getSource())
                                                    , "Connection Failed"
                                                    , "Connection Failed"
                                                    , JOptionPane.ERROR_MESSAGE
                                                );
                                            }
                                        }
                                    }                
                                });
                            }
                        });
                        add(disconnectMI = new JMenuItem("Disconnect") {
                            {
                                setEnabled(false);
                                addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        disconnect();           
                                    }                
                                });
                            }
                        });
                        addSeparator();
                        add(new JMenuItem("Host") {
                            {
                                addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
    									String p = JOptionPane.showInputDialog(
                                            ((JComponent)e.getSource())
                                            , "Input: <port>", "4444");
                                        if (p != null && p.matches("[0-9]+")) {
                                            //PipedOutputStream
                                            //new ChatD(p, somethingr);
                                        	try {
                                                connect("localhost", p);                                        		
                                        	} catch (IOException ioe) {
                                        	    ioe.printStackTrace();	
                                     	    }
                                        }              
                                    }                
                                });
                            }
                        });
                        addSeparator();
                        add(new JMenuItem("Exit") {
                            {
                                addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        System.exit(0);                 
                                    }                
                                });
                            }
                        });
                    }                
                });
                add(new JMenu("Settings") {
                    {
                        add(new JMenuItem("Name") {
                            {
                                addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        name = JOptionPane.showInputDialog(
                                            ((JComponent)e.getSource())
                                            , "Input: <name>", name);
                                        if (socket.isConnected()
                                         && !socket.isClosed()) {
                                            writeToServer("/name " + name);
                                        }     
                                    }                
                                });
                            }
                        });    
                    }
                });
            }
        });
        add(new JSplitPane(JSplitPane.VERTICAL_SPLIT) {
            {
                setDividerLocation(400);
                setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                setLeftComponent(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT) {
                    {
                        setDividerLocation(486);
                        setLeftComponent(
                            new JScrollPane(
                                chatArea = new JTextArea() {
                                    {
                                        setEditable(false);
                                    }
                                }
                            )
                        );                       
                        setRightComponent(
                            new JScrollPane(chatList = new JList()));
                    }
                });
                setRightComponent(chatField = new JTextField() {
                    {
                        addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                int i = chatList.getSelectedValues().length - 1;
                                if (i >= 0) {
                                    for (; i >= 0; i--) {
                                        setText(getText() + "`"
                                           + chatList.getSelectedValues()[i]);
                                    }
                                    writeToServer("/whisper " + getText());                                  
                                } else {
                                    writeToServer(getText());
                                }
                                setText("");
                                chatList.clearSelection();
                            }
                        });
                    }
                });
            }
        });
        pack();
        setVisible(true);
    }

    public static void main(String[] args) { new Chat(); }
}
