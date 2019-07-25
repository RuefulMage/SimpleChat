package ru.kpfu.itis.Abdulkodir.Network;

import ru.kpfu.itis.Abdulkodir.ChatClient.TCPConnection;

public interface TCPConnectionListener {
    void onConnectionReady(TCPConnection tcpConnection);
    void onRecieveString(TCPConnection tcpConnection , String msg);
    void onDisconnect(TCPConnection tcpConnection);
    void onException(TCPConnection tcpConnection , Exception e);
}
