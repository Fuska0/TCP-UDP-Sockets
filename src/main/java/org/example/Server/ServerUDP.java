package org.example.Server;

import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerUDP {
    private DatagramSocket socket;
    private MessagesQueue messagesQueue;
    private LoggedClients loggedClients;
    MessageHandlerUDP messageHandlerUDP;
    ClientHandlerUDP clientHandlerUDP;

    public ServerUDP(MessagesQueue messagesQueue, LoggedClients loggedClients) throws SocketException {
        this.socket = new DatagramSocket(12370);
        this.messagesQueue = messagesQueue;
        this.loggedClients = loggedClients;
        this.clientHandlerUDP = new ClientHandlerUDP(messagesQueue, loggedClients, socket);
        this.messageHandlerUDP = new MessageHandlerUDP(messagesQueue, loggedClients, socket);
    }

    public void start() {
        Thread threadClientHandlerUDP = new Thread(clientHandlerUDP);
        Thread threadMessageHandlerUDP = new Thread(messageHandlerUDP);

        threadClientHandlerUDP.start();
        threadMessageHandlerUDP.start();
    }
}
