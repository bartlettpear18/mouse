package com.company;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String argv[]) throws Exception {
        Socket clientSocket = new Socket("0.0.0.0", 10000);
        InetAddress i = InetAddress.getLocalHost();
        System.out.println(i.toString());
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes("Laptop test" + '\n');
        clientSocket.close();
    }
}