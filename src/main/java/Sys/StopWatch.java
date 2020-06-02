package Sys;

import java.util.*;
import java.time.*;

/**
 * 
 */
public class StopWatch implements Mode{

    /**
     * Default constructor
     */
    public StopWatch() {
    }
    private boolean isActivated;
    @Override
    public void setActive(boolean act) {
        this.isActivated=act;
    }

    @Override
    public boolean getActive() {
        return this.isActivated;
    }
    /**
     * 
     */
    private LocalDateTime currentTime;

    /**
     * 
     */


    /**
     * 
     */
    private Boolean isPaused;




    /**
     * 
     */
    public void startStopwatch() {
        // TODO implement here
    }

    /**
     * 
     */
    public void resumeStopwatch() {
        // TODO implement here
    }

    /**
     * 
     */
    public void pauseStopwatch() {
        // TODO implement here
    }

    /**
     * 
     */
    public void resetStopwatch() {
        // TODO implement here
    }

    /**
     * 
     */
    public void lapStopwatch() {
        // TODO implement here
    }

    /**
     * 
     */
    public void increaseCurrentTime() {
        // TODO implement here
    }

    /**
     * 
     */
    public void stopCurrentTime() {
        // TODO implement here
    }

    /**
     * 
     */
    public void saveLapTime() {
        // TODO implement here
    }

    /**
     * 
     */
    public void resetCurrentTime() {
        // TODO implement here
    }

}