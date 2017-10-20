package ru.feeleen.client.BLL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static final int PORT = 4646;

    public static void main(String[] args) {
        try(Socket clientSocket = new Socket("localhost", PORT)) {
            String sentence = "Hello!";
            String modifiedSentence;

            Scanner scn = new Scanner(System.in);
            while(true){
                sentence = scn.nextLine();
                DataOutputStream outputServer = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                outputServer.writeBytes(sentence + "\n");
                modifiedSentence = inFromServer.readLine();

                System.out.println("From Server" + modifiedSentence);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
