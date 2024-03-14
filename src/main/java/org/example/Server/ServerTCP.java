package org.example.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerTCP {
    private static int idCounter = 0;
    MessagesQueue messagesQueue;
    LoggedClients loggedClients;
    MessageHandler messageHandler;
    MessagesQueue messagesQueueUDP;
    ServerUDP serverUDP;
    ServerSocket serverSocket;

    public ServerTCP() throws IOException {
        this.messagesQueue = new MessagesQueue();
        this.loggedClients = new LoggedClients();
        this.messageHandler = new MessageHandler(messagesQueue, loggedClients);
        this.messagesQueueUDP = new MessagesQueue();
        this.serverUDP = new ServerUDP(messagesQueueUDP, loggedClients);
        this.serverSocket = new ServerSocket(12370);

    }

    public void start() {
        try {
            serverUDP.start();
            System.out.println("Server is running...");

            Thread messageHandlerThread = new Thread(messageHandler);
            messageHandlerThread.start();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                idCounter++;

                System.out.println("New client connected: Client" + idCounter);
                System.out.println(clientSocket.getInetAddress() + " " + clientSocket.getPort());

                Client client = new Client(clientSocket, idCounter);

                Thread clientThread = new Thread(new ClientHandlerTCP(client, messagesQueue, loggedClients));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
