package ru.kpfu.itis.Abdulkodir.ChatClient;//package ru.kpfu.itis.Abdulkodir.ChatClient;
//
//import ru.kpfu.itis.Abdulkodir.Network.TCPConnectionListener;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//
//public class ClientWindow2 extends JFrame implements ActionListener , TCPConnectionListener {
//    private final String IPADRESS = "127.0.1.1";
//    private final int PORT = 4444;
//    private final int WIDTH = 600;
//    private final int HEIGHT = 400;
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new ClientWindow2();
//            }
//        });
//    }
//
//    private TCPConnection connection;
//    private final JTextArea LOG = new JTextArea();
//    private final JTextField NAMEFIELD = new JTextField("guest");
//    private final JTextField TEXTFIELD = new JTextField();
//
//    private ClientWindow2(){
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setSize(WIDTH , HEIGHT);
//        setLocationRelativeTo(null);
//        setAlwaysOnTop(true);
//        LOG.setEditable(false);
//        LOG.setLineWrap(true);
//        add(LOG , BorderLayout.CENTER);
//        TEXTFIELD.addActionListener(this);
//        add(TEXTFIELD , BorderLayout.SOUTH);
//        add(NAMEFIELD , BorderLayout.NORTH);
//
//        setVisible(true);
//        try {
//            connection = new TCPConnection(this , IPADRESS , PORT);
//        } catch (IOException e) {
//            printMsg("connection excepted " + e);
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String msg = TEXTFIELD.getText();
//        if( msg.equals("")) return;
//        TEXTFIELD.setText(null);
//        connection.sendString(NAMEFIELD.getText() + ": " + msg);
//    }
//
//    @Override
//    public void onConnectionReady(TCPConnection tcpConnection) {
//        printMsg("connection ready ...");
//    }
//
//    @Override
//    public void onRecieveString(TCPConnection tcpConnection, String msg) {
//        printMsg(msg);
//    }
//
//    @Override
//    public void onDisconnect(TCPConnection tcpConnection) {
//        printMsg("connection close ...");
//    }
//
//    @Override
//    public void onException(TCPConnection tcpConnection, Exception e) {
//        printMsg("connection excepted : " + e);
//    }
//
//    private synchronized void printMsg(String value){
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                LOG.append(value + "\n");
//                LOG.setCaretPosition(LOG.getDocument().getLength());
//
//            }
//        });
//    }
//}

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TestFrame extends JFrame {

    public static void createGUI() {

        JFrame frame = new JFrame("Test frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Verdana", Font.PLAIN, 11);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(font);

        JMenu newMenu = new JMenu("New");
        newMenu.setFont(font);
        fileMenu.add(newMenu);

        JMenuItem txtFileItem = new JMenuItem("Text file");
        txtFileItem.setFont(font);
        newMenu.add(txtFileItem);

        JMenuItem imgFileItem = new JMenuItem("Image file");
        imgFileItem.setFont(font);
        newMenu.add(imgFileItem);

        JMenuItem folderItem = new JMenuItem("Folder");
        folderItem.setFont(font);
        newMenu.add(folderItem);

        JMenuItem openItem = new JMenuItem("Open");
        openItem.setFont(font);
        fileMenu.add(openItem);

        JMenuItem closeItem = new JMenuItem("Close");
        closeItem.setFont(font);
        fileMenu.add(closeItem);

        JMenuItem closeAllItem = new JMenuItem("Close all");
        closeAllItem.setFont(font);
        fileMenu.add(closeAllItem);

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(font);
        fileMenu.add(exitItem);

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);

        frame.setPreferredSize(new Dimension(270, 225));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                createGUI();
            }
        });
    }
}
