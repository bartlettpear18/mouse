package com.company;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import static com.company.Server.log;

/**
 * Created by Joel.Bartlett18 on 5/25/2017.
 */
public class Client {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String ip;
    private Socket connection;
    private int port;

    public void cilentRun(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;

        //while(true) {
            try{
                connect();
                streams();
                send("test");
            } catch (EOFException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        //}
    }

    private void connect() throws IOException {
        //log("Attempting connection");
        connection = new Socket(InetAddress.getByName(ip), port);
        //log("Connected to: " + connection.getInetAddress().getHostName());
    }

    private void streams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        //log("Streams initialized");
    }

    private String readInput() throws IOException, ClassNotFoundException {
        String msg = (String) input.readObject();
        return msg;
    }

    private void send (String msg) throws IOException {
        output.writeObject(msg);
        output.flush();
        log("Message sent: " + msg);
    }

    private void close() throws IOException {
        output.close();
        input.close();
        connection.close();
        //log("Connection closed");
    }
}
