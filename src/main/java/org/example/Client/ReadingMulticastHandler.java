package org.example.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class ReadingMulticastHandler implements Runnable {
    private MulticastSocket socket;
    private boolean connected;
    private int port;

    public ReadingMulticastHandler(MulticastSocket socket, int port) {
        this.socket = socket;
        this.port = port;
        this.connected = true;
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[1024];
            while (connected) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());

                String portString = String.valueOf(port);
                if (!message.startsWith(portString)) {
                    String messageWithoutPort = message.replaceAll("\\d", "");

                    System.out.println("[UDP-M] " + messageWithoutPort);
                }
            }
        } catch (IOException e) {
            connected = false;
        } finally {
            socket.close();
        }
    }
}

