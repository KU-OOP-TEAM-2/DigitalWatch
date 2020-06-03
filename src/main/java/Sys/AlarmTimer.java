package Sys;

import java.util.*;
import java.time.LocalTime;
/**
 *
 */
public class AlarmTimer {

    /**
     * Default constructor
     AlarmTimer 첫 생성시 expirationTime : 00시 00 분 00초
     isActivated == 0
     */
    public AlarmTimer() {
        LocalTime time = LocalTime.of(0,0,0);
        this.expirationTime = time;

        isActivated = false;
    }

    /**
     * LocalDateTime Type -> LocalTime.
     */
    private LocalTime expirationTime;

    /**
     *
     */
    private boolean isActivated;


    /**
     * void -> LocalTime, getter
     */
    public LocalTime requestExpirationTime() {
        return expirationTime;
    }

    /**
     *
     */
    public void toggleAlarmTimer() {
        if(this.isActivated == true)
            this.isActivated = false;
        else
            this.isActivated = true;
    }

    /**
     *
     */
    public void saveAlarmTime(LocalTime expirationTime) {
        this.expirationTime = expirationTime;
    }

}