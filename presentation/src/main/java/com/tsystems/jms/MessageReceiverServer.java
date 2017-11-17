package com.tsystems.jms;

import org.glassfish.tyrus.server.Server;

public class MessageReceiverServer {

    public static void main(String[] args) {
        runServer();
    }

    public static void runServer() {
        Server server = new Server("localhost", 8025, "/receiver", MessageReceiver.class);

        try {
            while(true){
                server.start(); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}
