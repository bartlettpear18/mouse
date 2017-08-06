package com.company;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Joel.Bartlett18 on 6/9/2017.
 */
public class Mouse implements MouseListener{

    //Robot for mouse
    private static Robot mouseBot = null;

    //Boolean states of mouse
    private boolean leftState = false;
    private boolean rightState = false;
    private boolean middleState = false;

    public Mouse() throws AWTException { mouseBot = new Robot(); }

    /**
     * Move mouse based off displacement calculated and sent from phone
     * @param xDisplacment
     * @param yDisplacment
     */
    public static void move( int xDisplacment, int yDisplacment) throws AWTException {
        Point currentPos = MouseInfo.getPointerInfo().getLocation();
        int currentX = (int) currentPos.getX();
        int currentY = (int) currentPos.getY();
        mouseBot.mouseMove(currentX + xDisplacment,currentY - yDisplacment);
    }

    //Change left button state
    public void toggleLeft() {
        if(leftState) {
            leftRelease();
        } else {
            leftPress();
        }
    }
    //Change right button state
    public void toggleRight() {
        if(rightState) {
            rightPress();
        } else {
            rightRelease();
        }
    }
    //Change middle button state
    public void toggleMiddle() {
        if(middleState) {
            middlePress();
        } else {
            middleRelease();
        }

    }


    //presses left mouse
    private void leftPress() {
        int mask = InputEvent.BUTTON1_MASK;
        mouseBot.mousePress(mask);
    }
    //releases left click
    private void leftRelease() {
        int mask = InputEvent.BUTTON1_MASK;
        mouseBot.mouseRelease(mask);
    }
    //presses right mouse
    private void rightPress() {
        int mask = InputEvent.BUTTON3_MASK;
        mouseBot.mousePress(mask);
    }
    //releases right mouse
    private void rightRelease() {
        int mask = InputEvent.BUTTON3_MASK;
        mouseBot.mouseRelease(mask);
    }
    //presses middle mouse
    private void middlePress() {
        int mask = InputEvent.BUTTON2_MASK;
        mouseBot.mousePress(mask);
    }
    //releases middle mouse
    private void middleRelease() {
        int mask = InputEvent.BUTTON2_MASK;
        mouseBot.mouseRelease(mask);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftState = true;
            System.out.println("Left state set to true");
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightState = true;
            System.out.println("Right state set to true");
        } else {
            middleState = true;
            System.out.println("Middle state set to true;");
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftState = false;
            System.out.println("Left state set to false");
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightState = false;
            System.out.println("Right state set to false");
        } else {
            middleState = false;
            System.out.println("Middle state set to false;");
        }
    }

}
