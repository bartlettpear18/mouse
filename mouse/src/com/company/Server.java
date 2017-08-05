package com.company;

import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{


    private Thread thread = null;

    //Server variables
    private static ServerSocket server;
    private static Socket socket;
    private static int port = 5000;

    //Stream variables
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private byte[] buffer = new byte[1024];
    int read;


    private void setup() throws IOException {
        server = new ServerSocket(port);
        socket = server.accept();
        System.out.println("AndroidServer connected to Socket");
    }
    private void streams() throws IOException {
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
        System.out.println("Streams made");
    }

    private byte[] recieveArray() throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int read;
        byte[] data = new byte[1];

        while((read = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data,0,read);
            System.out.println(data[0]);
            byte statePacket = data[0];
            System.out.println(Integer.toBinaryString(statePacket));
        }
        buffer.flush();
        return data;
    }

    @Override
    public void run() {
        try {
            setup();
            streams();
            System.out.println("Checkpoint");

            while(true) {
                recieveArray();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start(String name) {
        if(thread == null) {
            thread = new Thread(this, name);
            thread.setDaemon(true);
            thread.start();
            System.out.println("Starting " + thread.getName());
        }
    }
}
