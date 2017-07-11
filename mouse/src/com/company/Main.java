package com.company;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;

import static com.company.Mouse.getInstance;

public class Main extends Application {

    private static String ip_address;
    private Text text;

    private static Mouse mMouse;


    public static void main(String[] args) throws AWTException, IOException {

        mMouse = Mouse.getInstance();

        (new Thread(new Server())).start();
        ip_address = String.valueOf(InetAddress.getLocalHost());
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {

        //Create Window
        primaryStage.setTitle("Mouse");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));

        text = new Text();
        text.setFont(Font.font("calibri",20));
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText(ip_address);

        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: #007991;");
        layout.getChildren().add(text);
        Scene scene = new Scene(layout, 300, 100);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
