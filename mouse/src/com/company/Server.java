package com.company;

import java.awt.*;
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

    //Robot
    private Mouse mouse = null;
    private char[] states = new char[3];

    //Constructor
    public Server() throws AWTException { mouse = new Mouse(); }

    //Thread start
    public void start(String name) {
        if(thread == null) {
            thread = new Thread(this, name);
            thread.setDaemon(true);
            thread.start();
            System.out.println("Starting " + thread.getName());
        }
    }

    //Server setup
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
    private void close() throws IOException {
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        socket.close();
        server.close();
        System.out.println("Streams and sockets closed");
    }

    //Data transfer
    private byte[] receiveArray() throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int read;
        byte[] data = new byte[3];

        while((read = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data,0,read);
            for (byte state : data) {
                System.out.print(String.valueOf(state) + " ");
            }
            System.out.println();
        }
        buffer.flush();
        return data;
    }
    private void readPacket (byte[] packet) throws AWTException {
        char[] tempStates = Integer.toBinaryString(packet[0]).toCharArray();
        if(tempStates[0] != states[0]) { mouse.toggleLeft(); }
        if(tempStates[1] != states[1]) { mouse.toggleRight(); }
        if(tempStates[2] != states[2]) { mouse.toggleMiddle(); }
        states = tempStates;

        mouse.move((int) states[1], (int) states[2]);
    }

    @Override
    public void run() {
        try {
            setup();
            streams();

            //Constantly sending packets while running
            while(true) {
                readPacket(receiveArray());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
