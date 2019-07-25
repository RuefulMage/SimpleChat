package ru.kpfu.itis.Abdulkodir.ChatClient;

import ru.kpfu.itis.Abdulkodir.Network.TCPConnectionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientWindow3 extends JFrame implements ActionListener , TCPConnectionListener {
    private final String IPADRESS = "127.0.1.1";
    private final int PORT = 4444;
    private final int WIDTH = 600;
    private final int HEIGHT = 400;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientWindow3();
            }
        });
    }

    private TCPConnection connection;
    private final JTextArea LOG = new JTextArea();
    private final JTextField NAMEFIELD = new JTextField("guest");
    private final JTextField TEXTFIELD = new JTextField();

    private ClientWindow3(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH , HEIGHT);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        LOG.setEditable(false);
        LOG.setLineWrap(true);
        add(LOG , BorderLayout.CENTER);
        TEXTFIELD.addActionListener(this);
        add(TEXTFIELD , BorderLayout.SOUTH);
        add(NAMEFIELD , BorderLayout.NORTH);

        setVisible(true);
        try {
            connection = new TCPConnection(this , IPADRESS , PORT);
        } catch (IOException e) {
            printMsg("connection excepted " + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = TEXTFIELD.getText();
        if( msg.equals("")) return;
        TEXTFIELD.setText(null);
        connection.sendString(NAMEFIELD.getText() + ": " + msg);
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMsg("connection ready ...");
    }

    @Override
    public void onRecieveString(TCPConnection tcpConnection, String msg) {
        printMsg(msg);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        printMsg("connection close ...");
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        printMsg("connection excepted : " + e);
    }

    private synchronized void printMsg(String value){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LOG.append(value + "\n");
                LOG.setCaretPosition(LOG.getDocument().getLength());

            }
        });
    }
}
