package com.company;

/**
 * Created by Joel.Bartlett18 on 6/8/2017.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;


public class Main extends Application {

    //Server instance variables
    private static final int serverPort = 3000;
    private static String ip_address;
    private static Server server;

    //Styling
    private String backgroundColor = "-fx-background-color: #007991;";
    private String whiteText = "-fx-text-fill: white;";
    private String font = "-fx-font: 22 calibri;";

    Button button;
    Text text;

    public static void main(String[] args) throws AWTException, IOException {
        server = new Server();
        System.out.println("Singletons created");

        //server.run(serverPort);
        ip_address = String.valueOf(InetAddress.getLocalHost());
        System.out.println(ip_address);
        System.out.println("Running server");

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {

        //Create Window
        primaryStage.setTitle("Mouse");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));


        button = new Button("Begin");
        button.setStyle(backgroundColor +  whiteText + font);

        text = new Text();
        text.setFont(Font.font("calibri",20));
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);


        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        button.setStyle("-fx-background-color: #0085A0;" + whiteText + font);
                    }
                });

        button.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        button.setStyle(backgroundColor +  whiteText + font);
                    }
                });

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                text.setText("IP Address: " + ip_address + "\n Port Number: " + serverPort);
                button.setVisible(false);
            }
        });


        StackPane layout = new StackPane();
        layout.setStyle(backgroundColor);
        layout.getChildren().add(button);
        layout.getChildren().add(text);
        Scene scene = new Scene(layout, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
