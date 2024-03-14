package org.example.Server;

public class Message {
    private final int clientID;
    private final String messageText;

    public Message(int clientID, String messageText) {
        this.clientID = clientID;
        this.messageText = messageText;
    }

    @Override
    public String toString(){
        return "Client: " + clientID + " message: " + messageText;
    }

    public int getClientID() {
        return clientID;
    }
}
