package Sys;


import java.util.*;
import java.awt.*;
/**
 * 
 */
public class Buzzer {

    /**
     * Default constructor
     */
    public Buzzer() {
        buzzerOn = false;
    }

    /**
     * 
     */
    private Boolean buzzerOn;

    //추가된 부분
    private final int BEEPCOUNT = 10;
    //추가된 부분

    public void beepBuzzer() {
        //buzzer가 울리고 있었다면 씹는다.
        if(buzzerOn == true)
            return;
        buzzerOn = true;
        int i = 0;
        for(i=0; i < BEEPCOUNT && buzzerOn; i++){
            java.awt.Toolkit.getDefaultToolkit().beep();
            try {
				Thread.sleep(1000); // introduce delay
			} catch (InterruptedException e) {
			}
        }
    }

    /**
     * 
     */
    public void stopBuzzer() {
        buzzerOn = false;
    }

}