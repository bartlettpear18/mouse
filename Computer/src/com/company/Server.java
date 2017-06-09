package com.company;

import java.io.*;
import java.net.*;

public class Server {
    private static final int port = 10000;

    public static void main(String argv[]) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            System.out.println("Server connected from port " + serverSocket.getLocalPort());
            Socket connectionSocket = serverSocket.accept();
            System.out.println("Connection made");

            //Send message to the client
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
            bw.write("Message from the server");
            bw.newLine();
            bw.flush();

            //Receive message fom the client
            BufferedReader br = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            String data;

            while ((data = br.readLine()) != null) {
                System.out.println("Message from client: " + data);
            }
        }
    }
}