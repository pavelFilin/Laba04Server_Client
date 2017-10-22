package ru.feeleen.server;

import ru.feeleen.Helpers.MyFormatter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Server {
    private static Logger log = Logger.getLogger(Server.class.getName());

    ServerSocket socket;
    public static final int PORT = 4646;

    private HashSet<Connection> connections = new HashSet<>();

    public Server() throws IOException {
        FileHandler fileHandler = new FileHandler("logs/JavaLog.log");
        fileHandler.setFormatter(new MyFormatter());
        log.addHandler(fileHandler);
    }

    public void severGo() throws Exception {
        log.info("SERVER START");
        try (ServerSocket welcomeSocket = new ServerSocket(PORT)) {
            while (true) {
                try {
                    Socket connectionSocket = welcomeSocket.accept();
                    log.info("new Connect " + connectionSocket.getRemoteSocketAddress().toString() + "\n");
                    System.out.println("has connection");
                    Connection connection = new Connection(connectionSocket, this);
                    Thread t = new Thread(connection);
                    t.start();
                    connections.add(connection);
                } catch (Exception e) {
                    log.severe("error connecting");
                    e.printStackTrace();
                }
            }
        }
    }

    public void closeConnection(Connection connection) {
        connections.remove(connection);
        log.info("connection: " + connection.getName() + " removed");
    }
}
