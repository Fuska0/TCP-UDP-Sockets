package org.example.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientHandlerTCP implements Runnable {
    Client client;
    MessagesQueue messagesQueue;
    LoggedClients loggedClients;
    public ClientHandlerTCP(Client client, MessagesQueue messagesQueue, LoggedClients loggedClients) {
        this.client = client;
        this.messagesQueue = messagesQueue;
        this.loggedClients = loggedClients;
    }

    @Override
    public void run() {
        loggedClients.addClient(client);

        while(client.isConnected()){
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getSocket().getInputStream()));
                String receivedMsg = in.readLine();
                if(receivedMsg != null){
                    Message message = new Message(client.getID(), receivedMsg);
                    messagesQueue.addMessage(message);
                }
            } catch (IOException e) {
                client.setConnected(false);
            }
        }

        try {
            if(!this.client.getSocket().isClosed()){
                this.client.getSocket().close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        loggedClients.deleteClient(client.getID());
    }
}
