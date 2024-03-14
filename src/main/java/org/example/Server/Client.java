package org.example.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    private final int ID;
    private final Socket socket;
    private boolean connected;
    private final int port;
    private final InetAddress address;

    public Client(Socket socket, int ID) throws SocketException {
        this.socket = socket;
        this.connected = true;
        this.ID = ID;
        this.port = socket.getPort();
        this.address = socket.getInetAddress();
    }

    public int getID() {
        return ID;
    }

    public Socket getSocket() {
        return socket;
    }

    public int getPort() {return port;}
    public InetAddress getAddress(){return address;}
    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isConnected() {
        return connected;
    }

}
