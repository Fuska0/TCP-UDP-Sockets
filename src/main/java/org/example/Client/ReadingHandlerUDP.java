package org.example.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

public class ReadingHandlerUDP implements  Runnable{
    private final Socket socket;
    private byte[] receiveBuffer;
    private final DatagramSocket socketUDP;
    private DatagramPacket receivePacket;
    boolean connected = true;
    public ReadingHandlerUDP(Socket socket, DatagramSocket socketUDP) throws IOException {
        this.socket = socket;
        this.socketUDP = socketUDP;
        this.receiveBuffer = new byte[1024];
    }

    @Override
    public void run() {
        try {
            String responseUDP;
            while (connected) {
                receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socketUDP.receive(receivePacket);
                responseUDP = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("[UDP] " + responseUDP);
            }
        } catch (IOException e) {
            connected = false;
        } finally {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
