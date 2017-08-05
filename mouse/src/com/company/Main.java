package com.company;

import javafx.application.Application;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;


public class Main {

    private static String ip_address;
    private Text text;

    public static Mouse mouse = null;
    public static Presenter presenter = null;


    public static void main(String[] args) throws AWTException, IOException {

        Server server = new Server();
        server.start("Android Server");

        Application.launch(Display.class,args);

    }

}
