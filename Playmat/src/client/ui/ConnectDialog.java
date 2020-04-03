// ConnectDialog
//
//
//

package client.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import client.*;

public class ConnectDialog extends JDialog {

    private PlaymatClient client;
    private JButton buttonCancel;
    private JButton buttonConnect;
    private JTextField textFieldHost;
    private JTextField textFieldUser;
    private JPasswordField passwordField;

    public ConnectDialog(Frame f) {
        super(f, true);
        setResizable(false);
        setTitle("Connect");
        buttonCancel = new JButton("Cancel");
        buttonConnect = new JButton("Connect");
        textFieldHost = new JTextField("localhost:4416", 15);
        textFieldUser = new JTextField("Username", 15);
        passwordField = new JPasswordField();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_END;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 0.0;
        add(new JLabel("Host :"), c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        add(textFieldHost, c);
        c.gridy = 1;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.0;
        add(new JLabel("User :"), c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        add(textFieldUser, c);
        c.gridy = 2;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.0;
        add(new JLabel("Password :"), c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        add(passwordField, c);
        c.gridy = 3;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.0;
        c.anchor = GridBagConstraints.LINE_START;
        add(buttonCancel, c);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_END;
        add(buttonConnect, c);
        pack();
    }
    public ConnectDialog(Frame f, PlaymatClient c) {
        this(f);
        client = c;
        if (client instanceof EditorClient) {
            textFieldHost.setText("localhost:4417");
        }
        textFieldUser.setText(client.getUsername());
        buttonCancel.addActionListener(new HideAction());
        buttonConnect.addActionListener(new ConnectAction());
    }

    class HideAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ConnectDialog.this.setVisible(false);
        }
    }

    class ConnectAction implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            textFieldHost.getText().replace(" ", "");
            if (textFieldHost.getText().equals("")
              || textFieldUser.getText().equals("")
              || passwordField.getPassword().length < 1) {
                JOptionPane.showMessageDialog(ConnectDialog.this,
                  "Missing Input", "Missing Input", JOptionPane.ERROR_MESSAGE
                );
            } else {
                try {
                    String[] host = textFieldHost.getText().split(":");
                    client.setUsername(textFieldUser.getText());
                    client.setPassword(passwordField.getPassword());
                    client.connect(host[0], Integer.parseInt(host[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(ConnectDialog.this,
                      "Invalid Host", "Invalid Input",
                      JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(ConnectDialog.this,
                      "Invalid Port Number", "Invalid Input",
                      JOptionPane.ERROR_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(ConnectDialog.this,
                      e.toString().substring(e.toString().indexOf(":") + 2),
                      "Connection Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            ConnectDialog.this.setVisible(false);
        }
    }
}
