package org.example.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadingHandlerTCP implements Runnable {

    private final Socket socket;
    private final BufferedReader readBuffer;
    boolean connected = true;
    public ReadingHandlerTCP(Socket socket) throws IOException {
        this.socket = socket;
        this.readBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String responseTCP;
            while (connected) {
                if ((responseTCP = readBuffer.readLine()) != null) {
                    System.out.println("[TCP] " + responseTCP);
                }
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
