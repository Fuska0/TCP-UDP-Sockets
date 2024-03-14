package org.example.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class WritingHandler implements Runnable {

    private Socket socketTCP;
    private Scanner scanner;
    private PrintWriter printWriter;
    private DatagramSocket socketUDP;
    private InetAddress addressUDP;
    private InetAddress addressMulticastGroup;
    private MulticastSocket multicastSocket;
    public WritingHandler(Socket socketTCP, Scanner scanner, DatagramSocket socketUDP, InetAddress addressMulticastGroup, MulticastSocket multicastSocket) throws IOException {
        this.socketTCP = socketTCP;
        this.scanner = scanner;
        this.socketUDP = socketUDP;
        this.printWriter = new PrintWriter(socketTCP.getOutputStream(), true);
        this.addressMulticastGroup = addressMulticastGroup;
        this.addressUDP = InetAddress.getByName("localhost");
        this.multicastSocket = multicastSocket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Remember, type 'exit' to end ");

            while (true) {
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("U")) {sendAsciiUDP(addressUDP);}
                else if (message.equalsIgnoreCase("exit")) {break;}
                else if (message.equalsIgnoreCase("M")) {sendAsciiMulticast(addressMulticastGroup);}
                else {printWriter.println(message);}
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (!socketTCP.isClosed()) {
                    socketTCP.close();
                }
                if (socketUDP != null && !socketUDP.isClosed()) {
                    socketUDP.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendAsciiUDP(InetAddress address) throws IOException {
        String asciiArt = "\n  _\\/_\n" +
                " / o o \\\n" +
                "(   \"   )\n" +
                " \\~(*)~/\n" +
                " _/   \\_\n";
        byte[] sendData = asciiArt.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 12370);
        socketUDP.send(sendPacket);
    }

    public void sendAsciiMulticast(InetAddress address) throws IOException {

        String asciiArt = socketTCP.getLocalPort() + "\n  _\\/_\n" +
                " / o o \\\n" +
                "(   \"   )\n" +
                " \\~(*)~/\n" +
                " _/   \\_\n";
        byte[] sendData = asciiArt.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 12371);
        multicastSocket.send(sendPacket);
    }
}
