package com.company;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Created by Joel.Bartlett18 on 6/9/2017.
 */
public class Mouse {

    //Establish Singleton characteristic of Mouse
    private static Mouse sInstance = null;
    public static Mouse getInstance() throws AWTException {
        if(sInstance == null) {
            sInstance = new Mouse();
        }
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

    //presses right mouse
    public static void rightClick() {
        int mask = InputEvent.BUTTON3_MASK;
        mouseBot.mousePress(mask);
    }

    //releases right mouse
    public static void rightRelease() {
        int mask = InputEvent.BUTTON3_MASK;
        mouseBot.mouseRelease(mask);
    }

    //scroll
    public static void scroll(int displacment) {
        mouseBot.mouseWheel(displacment);
    }
}
