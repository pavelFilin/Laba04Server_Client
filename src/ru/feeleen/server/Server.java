package ru.feeleen.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;

public class Server {
    ServerSocket socket;
    public static final int PORT = 4646;
    private static HashSet<Connection> connections = new HashSet<>();

    public static void main(String argv[]) throws Exception {
        System.out.println("serverStart");
        try (ServerSocket welcomeSocket = new ServerSocket(PORT)) {
            while (true) {
                try  {
                    Socket connectionSocket = welcomeSocket.accept();
                    System.out.println("has connection");
                    Connection connection = new Connection(connectionSocket);
                    Thread t = new Thread(connection);
                    t.start();
                    connections.add(connection);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /*private static void serverClient(Socket socket) throws Exception {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());

        String clientSentence = inFromClient.readLine();

        System.out.println("Received: " + clientSentence);
        String capitalizedSentence = new StringBuilder(clientSentence).reverse().toString().toUpperCase() + '\n';

        outToClient.writeBytes(capitalizedSentence);
    }*/
}
