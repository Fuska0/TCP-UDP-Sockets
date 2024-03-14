package org.example.Server;

import java.util.ArrayList;

public class LoggedClients {
    ArrayList<Client> clientsMap;

    public LoggedClients() {
        this.clientsMap = new ArrayList<>();
    }

    public synchronized void addClient(Client client){
        clientsMap.add(client);
    }

    public synchronized void deleteClient(int ID){
        clientsMap.remove(ID);
    }

    public ArrayList<Client> getClientsList() {
        return clientsMap;
    }
}
