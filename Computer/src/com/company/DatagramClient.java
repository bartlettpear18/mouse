package com.company;

import java.io.IOException;
import java.net.*;

/**
 * Created by Joel.Bartlett18 on 5/20/2017.
 */
public class DatagramClient {
    private DatagramSocket socket;
    private InetAddress address;
    private String ipAddress = "10.0.0.138";
    private final int port = 8080;

    private byte[] buf = new byte[256];

    public DatagramClient() throws UnknownHostException, SocketException {
        socket = new DatagramSocket();
        address = InetAddress.getByName(ipAddress);
    }

    public String sendMessage(String msg) throws IOException {
        buf = msg.getBytes();
        DatagramPacket packet;
        packet = new DatagramPacket(buf,buf.length,address,port);

        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData(),0,packet.getLength());
        return received;
    }

    public void close() {
        socket.close();
    }

}
