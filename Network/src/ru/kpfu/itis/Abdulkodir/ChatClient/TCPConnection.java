package ru.kpfu.itis.Abdulkodir.ChatClient;

import ru.kpfu.itis.Abdulkodir.Network.TCPConnectionListener;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

public class TCPConnection {
    private Socket socket;
    private Thread rxThread;
    private TCPConnectionListener eventListener;
    private BufferedReader in;
    private BufferedWriter out;

    public TCPConnection(TCPConnectionListener eventListener , String ipAdress , int port) throws IOException {
        this(eventListener , new Socket(ipAdress , port));
    }

    public TCPConnection(TCPConnectionListener eventListener , Socket socket) throws IOException {
        this.eventListener = eventListener;
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream() , Charset.forName("UTF-8") ));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream() , Charset.forName("UTF-8")));
        rxThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    eventListener.onConnectionReady(TCPConnection.this);
                    while(!rxThread.isInterrupted()){
                        String mesg = in.readLine();
                        eventListener.onRecieveString(TCPConnection.this , mesg);
                    }

                } catch (IOException e) {
                    eventListener.onException(TCPConnection.this , e);
                } finally{
                    eventListener.onDisconnect(TCPConnection.this);
                }
            }
        });
        rxThread.start();
    }

    public synchronized void sendString(String value) {
        try {
            out.write(value + "\r\n");
            out.flush();
        } catch (IOException e) {
            eventListener.onException(TCPConnection.this , e);
            disconnect();
        }
    }

    public synchronized void disconnect(){
        rxThread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            eventListener.onException(TCPConnection.this , e);
        }
    }

    @Override
    public String toString() {
        return "TCPConnetion: " + socket.getInetAddress() + ":" + socket.getPort();
    }
}
