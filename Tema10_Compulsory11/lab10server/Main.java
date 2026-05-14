package com.compulsory10.lab10server;

public class Main {

    public static void main(String[] args) {

        GameServer server = new GameServer();

        server.start(1234);
    }
}