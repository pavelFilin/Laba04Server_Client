package ru.feeleen.server;

import java.io.*;
import java.net.Socket;

public class Connection implements Runnable {
    private Socket socket;
    private OutputStreamWriter out;
    private BufferedReader in;

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
            String clientSentence;
            while((clientSentence = in.readLine()) != null){
                outToClient.writeBytes(reverse(clientSentence));
            }
            outToClient.writeBytes(reverse(clientSentence));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection(Socket socket) {
        this.socket = socket;
    }

    private String reverse(String s) {
        System.out.println("Received: " + s);
        String resultString = new StringBuilder(s).reverse().toString().toUpperCase() + '\n';
        return resultString;
    }
}
