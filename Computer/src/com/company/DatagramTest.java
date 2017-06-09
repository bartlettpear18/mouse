package com.company;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Joel.Bartlett18 on 5/20/2017.
 */
public class DatagramTest {
    DatagramClient client;

    public void setup() throws SocketException, UnknownHostException {
        new DatagramServer().start();
        client = new DatagramClient();
    }

    public void test() throws IOException {
        String msg = client.sendMessage("pc test");
        msg = client.sendMessage("server is working");
    }

    public void end() throws IOException {
        client.sendMessage("end");
        client.close();
    }

    public static void main(String[] args) throws IOException {
        DatagramTest d1 = new DatagramTest();
        d1.setup();
        d1.test();
        d1.end();
    }
}
