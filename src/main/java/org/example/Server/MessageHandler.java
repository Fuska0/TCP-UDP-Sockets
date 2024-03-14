package org.example.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MessageHandler implements Runnable {
    private MessagesQueue queue;
    private LoggedClients loggedClients;

    public MessageHandler(MessagesQueue queue, LoggedClients loggedClients) {
        this.queue = queue;
        this.loggedClients = loggedClients;
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
            PrintWriter out = new PrintWriter(client.getSocket().getOutputStream(), true);
            out.println(message.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}