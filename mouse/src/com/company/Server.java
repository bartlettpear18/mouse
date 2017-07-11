package com.company;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Joel.Bartlett18 on 6/17/2017.
 */
public class Server implements Runnable{

    //Server variables
    private static ServerSocket server;
    private static Socket connection;
    private static int port = 5000;

    //Stream variables
    private static InputStreamReader inputStreamReader;
    private static BufferedReader bufferedReader;

    //testing string
    private static String message;

    /**
     * Create input stream
     * @throws IOException
     */
    private static void stream() throws IOException {

        inputStreamReader = new InputStreamReader(connection.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        message = bufferedReader.readLine();

        switch(message) {
            case "Left Click":
                Mouse.leftClick();
                break;
            case "Right Click":
                Mouse.rightClick();
                break;
            case "Move":
                try {
                    Mouse.move(1, 1);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
                break;
            case "Forward":
                Presenter.next();
                break;
            case "Previous":
                Presenter.previous();
                break;
            default:
        }

        //Testing check
        if (message.equals("Android Test")) {
            try {
                Mouse.move(200,200);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * close stream and connection
     * @throws IOException
     */
    private static void close() throws IOException {
        inputStreamReader.close();
        bufferedReader.close();
        server.close();
        connection.close();
    }

    @Override
    public void run() {
        while (true) {
            try {
                server = new ServerSocket(port);
                connection = server.accept();

                stream();
                System.out.println(message);
                close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
