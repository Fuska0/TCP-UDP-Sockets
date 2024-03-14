package org.example.Client;

import java.io.IOException;

public class ClientStart {
    public static void main(String[] args) throws IOException, InterruptedException {
        Client clientTCP = new Client();
        clientTCP.start();
    }
}
