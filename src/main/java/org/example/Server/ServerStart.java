package org.example.Server;

import java.io.IOException;

public class ServerStart {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerTCP serverTCP = new ServerTCP();
        serverTCP.start();
    }
}
