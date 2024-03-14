package org.example.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class ClientHandlerUDP implements Runnable {
    private MessagesQueue queue;
    private LoggedClients loggedClients;
    private DatagramSocket socket;

    public ClientHandlerUDP(MessagesQueue queue, LoggedClients loggedClients,
                            DatagramSocket socket) throws SocketException {
        this.socket = socket;
        this.queue = queue;
        this.loggedClients = loggedClients;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        while(true) {
            try {
                socket.receive(packet);
                String receivedMsg = new String(packet.getData(), 0,packet.getLength());

                addToQueue(receivedMsg,packet.getPort());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addToQueue(String receivedMsg, int port){
        for(Client client: loggedClients.getClientsList()){
            if(client.getPort() == port){
                queue.addMessage(new Message(client.getID(), receivedMsg));
            }
        }
    }
}
