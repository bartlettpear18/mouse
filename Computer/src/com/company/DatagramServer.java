package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Joel.Bartlett18 on 5/20/2017.
 */
public class DatagramServer extends Thread {
    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public DatagramServer() throws SocketException {
        socket = new DatagramSocket(8080);
    }

    public void run() {
        running = true;

        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf,buf.length,address,port);
            String received = new String(packet.getData(),0,packet.getLength());
            System.out.println(received);

            if(received.equals("end")){
                running = false;
                continue;
            }
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }

    public static void main (String[] args) throws SocketException {
        new DatagramServer().start();
    }
}
