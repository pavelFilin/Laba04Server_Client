package ru.feeleen.server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        try {
            server.severGo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
