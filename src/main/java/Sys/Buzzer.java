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
        buzzerThread = new BuzzerThread("buzzerThread");
        isAlarmRinging = false;
    }

    /**
     *
     */
    private Boolean buzzerOn;
    private Boolean isAlarmRinging;
    //추가된 부분
    private final int BEEPCOUNT = 10;
    //추가된 부분
    private BuzzerThread buzzerThread;

    private class BuzzerThread implements Runnable{
        Thread t;

        BuzzerThread(String name){
            t = new Thread(this, name);
            t.start();
        }

        public void run(){
            beepBuzzer();
        }
    }

    /**
     *
     */
    public void beepBuzzer() {
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
    public boolean getBuzzerOn(){return buzzerOn;}
    public boolean getIsAlarmRinging(){return isAlarmRinging;}

}
