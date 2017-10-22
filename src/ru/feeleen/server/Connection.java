package ru.feeleen.server;

import ru.feeleen.Helpers.MyFormatter;

import java.io.*;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Connection implements Runnable {
    private static Logger log = Logger.getLogger(Server.class.getName());

    private Server server;
    private Socket socket;
    private OutputStreamWriter out;
    private BufferedReader in;

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
            String clientSentence;
            while ((clientSentence = in.readLine()) != null) {
                log.info("FROM " + getName() + " MESSAGE: " + clientSentence + "\n");
                String reportString = BusinessLogic.reverse(clientSentence);
                log.info("SEND TO " + getName() + " MESSAGE: " + reportString + "\n");
                outToClient.writeBytes(reportString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            server.closeConnection(this);
        }
    }

    public Connection(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        FileHandler fileHandler = new FileHandler("logs/JavaLog.log");
        fileHandler.setFormatter(new MyFormatter());
        log.addHandler(fileHandler);
    }

    public String getName() {
        return socket.getRemoteSocketAddress().toString();
    }
}
