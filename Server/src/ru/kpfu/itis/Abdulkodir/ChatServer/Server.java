package ru.kpfu.itis.Abdulkodir.ChatServer;

import ru.kpfu.itis.Abdulkodir.ChatClient.TCPConnection;
import ru.kpfu.itis.Abdulkodir.Network.TCPConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server implements TCPConnectionListener {
    public static void main(String[] args) {
        new Server();
    }

    private final ArrayList<TCPConnection> connections = new ArrayList<>();

    private Server(){
        System.out.println("Server running ...");
        try {
            ServerSocket serverSocket = new ServerSocket(4444);
            while(true){
                try{
                    new TCPConnection(this , serverSocket.accept());
                } catch (IOException e){
                    System.out.println("TCPException: " + e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onConnectionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        sendToAllConnections("ClientConnected: " + tcpConnection);
    }

    @Override
    public synchronized void onRecieveString(TCPConnection tcpConnection, String msg) {
        sendToAllConnections(msg);
    }

    @Override
    public synchronized void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendToAllConnections("Client disconnected: " + tcpConnection);
    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCPConnection exception: " + e);
    }

    private void sendToAllConnections(String value){
        System.out.println(value);
        final int SIZE = connections.size();
        for( int i = 0 ; i < SIZE ; i++){
            connections.get(i).sendString(value);
        }
    }
}
