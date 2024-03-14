package org.example.Client;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket socketTCP;
    private final Scanner scanner;
    private final DatagramSocket socketUDP;
    private final InetAddress multicastGroup;
    MulticastSocket multicastSocket;

    public Client() throws IOException {
        this.socketTCP = new Socket("localhost", 12370);
        this.socketUDP = new DatagramSocket(socketTCP.getLocalPort());
        this.scanner = new Scanner(System.in);
        this.multicastGroup = InetAddress.getByName("230.0.0.0");
        this.multicastSocket = new MulticastSocket(12371);
        multicastSocket.joinGroup(multicastGroup);
    }

    public void start() {
        try {
            ReadingHandlerTCP readingHandler = new ReadingHandlerTCP(socketTCP);
            WritingHandler writingHandler = new WritingHandler(socketTCP, scanner, socketUDP, multicastGroup, multicastSocket);
            ReadingHandlerUDP readingHandlerUDP = new ReadingHandlerUDP(socketTCP, socketUDP);
            ReadingMulticastHandler multicastHandler = new ReadingMulticastHandler(multicastSocket, socketTCP.getLocalPort());


            Thread readingThread = new Thread(readingHandler);
            Thread writingThread = new Thread(writingHandler);
            Thread readingThreadUDP = new Thread(readingHandlerUDP);
            Thread multicastThread = new Thread(multicastHandler);


            readingThread.start();
            writingThread.start();
            readingThreadUDP.start();
            multicastThread.start();

            readingThread.join();
            writingThread.join();
            readingThreadUDP.join();
            multicastThread.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketTCP != null) socketTCP.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
