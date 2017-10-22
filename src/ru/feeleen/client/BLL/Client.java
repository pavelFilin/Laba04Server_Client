package ru.feeleen.client.BLL;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    private static final int PORT = 4646;

    private DataOutputStream outputServer;
    private BufferedReader inFromServer;
    private Socket clientSocket;

    public void clientGo() throws IOException {
        clientSocket = new Socket("localhost", PORT);
        outputServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void disconnect() throws IOException {
        clientSocket.close();
    }

    public void MessengeSend(String massenge) throws IOException {
        outputServer.writeBytes(massenge + "\n");
    }

    public String ReportOfServer() throws IOException {
        return inFromServer.readLine();
    }

    public boolean getKeepAlive() throws SocketException {
        return clientSocket.getKeepAlive();
    }

    public boolean isClosed() throws SocketException {
        return clientSocket.isClosed();
    }

}
