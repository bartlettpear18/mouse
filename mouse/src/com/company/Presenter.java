package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Joel.Bartlett18 on 6/9/2017.
 */
public class Presenter {

    //Establish Singleton characteristic of Presenter
    private static Presenter sInstance = null;
    public static Presenter getInstance() throws AWTException {
        if(sInstance == null) {
            sInstance = new Presenter();
        }
        return sInstance;
    }

    //Create PC Bot for Presenter
    private static Robot presentorBot = null;
    private Presenter() throws AWTException {
        presentorBot = new Robot();
    }

    //forward slide
    public void next(){
        presentorBot.keyPress(KeyEvent.VK_RIGHT);
    }

    //back solide
    public void previous() { presentorBot.keyPress(KeyEvent.VK_LEFT); }

    // --------- Implement laser pointer functionality in future update ---------//

}
