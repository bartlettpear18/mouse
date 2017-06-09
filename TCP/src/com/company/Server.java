package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Joel.Bartlett18 on 5/25/2017.
 */
public class Server {

    private ServerSocket server;
    private Socket connection;
    private int port;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    //quick print method
    public static void log (String str) {
        System.out.println(str);
    }

    public void run(int port) throws IOException{
        this.port = port;
        server = new ServerSocket(port, 1);
        while(true) {
            try{
                connectServer();
                streams();
                log(readInput());
            } catch (EOFException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }
    }

    /**
     * server accepts client socket
     * @throws IOException
     */
    private void connectServer() throws IOException {
        //log("Server connected from port " + server.getLocalPort());
        connection = server.accept();
        //log("Connection made");
    }

    /**
     * Creates input and output streams
     * @throws IOException
     */
    private void streams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        //log("Streams initialized");
    }

    /**
     * reads input from cilent
     * @return string message from client
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private String readInput() throws IOException, ClassNotFoundException {
        String str = (String) input.readObject();
        return str;
    }

    /**
     * closes io streams and connection socket
     * @throws IOException
     */
    private void close() throws IOException {
        output.close();
        input.close();
        connection.close();
        //log("Connection closed");
    }

    /**
     * send message to client socket
     * @param msg
     * @throws IOException
     */
    private void sendMsg (String msg) throws IOException {
        output.writeObject(msg);
        output.flush();
        //log("Message sent: " + msg);
    }
}
