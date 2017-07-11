package com.company;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Created by Joel.Bartlett18 on 6/9/2017.
 */
public class Mouse {

    //Dimension variables
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private double screenX = primaryScreenBounds.getMaxX();
    private double screenY = primaryScreenBounds.getMaxY();

    //Establish Singleton characteristic of Mouse
    private static Mouse sInstance = null;
    public static Mouse getInstance() throws AWTException {
        if(sInstance == null) {
            sInstance = new Mouse();
        }
        System.out.println("Singleton created");
        return sInstance;
    }


    //Create PC Bot for Mouse
    private static Robot mouseBot = null;
    private Mouse() throws AWTException {
        mouseBot = new Robot();
    }

    /**
     * Move mouse based off displacement calculated and sent from phone
     * @param x
     * @param y
     */
    public static void move( int x, int y) throws AWTException {
        //Get Current Position of Mouse
        Point currentPos = MouseInfo.getPointerInfo().getLocation();
        int curX = (int) currentPos.getX();
        int curY = (int) currentPos.getY();
        //Move mouse based off current position and displacement of phone
        mouseBot.mouseMove(curX + x,curY - y);
    }

    //presses left mouse
    public static void leftPress() {
        int mask = InputEvent.BUTTON1_MASK;
        mouseBot.mousePress(mask);
    }

    //releases left click
    public static void leftRelease() {
        int mask = InputEvent.BUTTON1_MASK;
        mouseBot.mouseRelease(mask);
    }

    //click left mouse
    public static void leftClick(){
        leftPress();
        leftPress();
    }

    //press and hold left mouse
    public static void leftHold() {
        do{
            leftPress();
        } while(true);
    }

    //presses right mouse
    public static void rightPress() {
        int mask = InputEvent.BUTTON3_MASK;
        mouseBot.mousePress(mask);
    }

    //releases right mouse
    public static void rightRelease() {
        int mask = InputEvent.BUTTON3_MASK;
        mouseBot.mouseRelease(mask);
    }

    //click left mouse
    public static void rightClick(){
        rightPress();
        rightRelease();
    }

    //press and hold left mouse
    public static void rightHold() {
        do{
            rightPress();
        } while(true);
    }





    //scroll
    public static void scroll(int displacment) {
        mouseBot.mouseWheel(displacment);
    }
}
