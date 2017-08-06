package com.company;

import javafx.application.Application;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;


public class Main {

    private static String ip_address;
    private Text text;


    public static void main(String[] args) throws AWTException, IOException {

        Server server = new Server();
        server.start("Driver server");

        Application.launch(Display.class,args);

    }

}
