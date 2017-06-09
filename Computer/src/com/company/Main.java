package com.company;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static com.company.Mouse.*;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        //create socket connection, constantly check for packets
        //perform static methods in main based off packets

        /*
        while(true) {
            runUDPServer();
        } */

        try{
            isHeld = true;
            leftClick();
            TimeUnit.SECONDS.sleep(3);
            move(0,-100);
            isHeld = false;
            leftClick();
        } catch (AWTException e){
            e.printStackTrace();
            System.out.println("Failed here");
        }



    }

}