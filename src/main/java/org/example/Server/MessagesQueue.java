package org.example.Server;

import java.util.ArrayList;

public class MessagesQueue {
    private ArrayList<Message> queue;

    public MessagesQueue() {
        this.queue = new ArrayList<>();
    }

    public synchronized void addMessage(Message message){
        queue.add(message);
        notify();
    }

    public synchronized ArrayList<Message> getMessages() throws InterruptedException {
        if(queue.size() == 0){ wait();}
        ArrayList<Message> tmpQueue = new ArrayList<>(queue);
        clearQueue();
        return tmpQueue;
    }

    public synchronized void clearQueue(){
        queue.clear();
    }
}
