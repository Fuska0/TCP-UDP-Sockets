package org.example.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MessageHandlerUDP implements Runnable{
    private MessagesQueue queue;
    private LoggedClients loggedClients;
    DatagramPacket sendPacket;
    DatagramSocket socket;

    public MessageHandlerUDP(MessagesQueue queue, LoggedClients loggedClients, DatagramSocket socket) throws SocketException {
        this.queue = queue;
        this.loggedClients = loggedClients;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ArrayList<Message> messages = queue.getMessages();
                for (Message message : messages) {
                    ArrayList<Client> clients = loggedClients.getClientsList();
                    for (Client client : clients) {
                        if (client.getID() != message.getClientID()) {
                            sendMessage(client, message);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void sendMessage(Client client, Message message) {
        try {
            byte[] buffer = message.toString().getBytes(StandardCharsets.UTF_8);

            sendPacket =
                    new DatagramPacket(buffer, buffer.length, client.getAddress(), client.getPort());
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
