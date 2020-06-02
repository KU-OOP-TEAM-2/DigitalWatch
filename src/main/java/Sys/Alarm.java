package Sys;
import java.util.*;
import java.time.LocalTime;
import java.time.LocalDateTime;
/**
 *
 */

public class Alarm implements Mode{

    //ModeManager에서 Buzzer객체
    public Alarm(Buzzer buzzer, Mode time) {

        //ModeManager에서 사용중인 buzzer를 받아서 사용.
        this.buzzer = buzzer;
        //Time의 시간을 받아와야지 buzzer를 작동가능.
        this.time = (Time)time;
        //cursor==true : Hour || cursor==false: minute.
        isCursorOnHour = true;
        currentAlarmTimerIndex = 0;
        alarm = new AlarmTimer[4];
        for(int i=0; i < alarm.length; i++){
            alarm[i] = new AlarmTimer();
        }

        //Thread
        alarmThread = new AlarmThread("alarmThread");
    }

    private class AlarmThread implements Runnable{
        Thread t;
        
        AlarmThread(String name){
            t = new Thread(this, name);
            t.start();
        }

        public void run(){
            LocalDateTime traceCurrentTime;
            LocalTime currentTime;
            while(true){
                traceCurrentTime = time.getCurrentTime();
                currentTime = traceCurrentTime.toLocalTime();
                
                for(int i=0; i < 4; i++){
                    if(currentTime.compareTo(alarm[i].requestExpirationTime()) == 0){
                        buzzer.beepBuzzer();
                    }
                }
            }
        }
    }


    
    private AlarmTimer alarm[];

    private int currentAlarmTimerIndex;

    //추가 - 변수
    private boolean isCursorOnHour;

    //추가 - 변수 AlarmTimer 수정할 때...
    private LocalTime copyOfAlarmTimer;

    //추가 - 변수
    private AlarmThread alarmThread;

    //추가 - 변수
    private Buzzer buzzer;

    private Time time;

    //추가 - 변수
    private boolean isActivated; 
    
    
    //requestNextAlarm, requestFirstAlarm는 지울것임 (시퀀스 다이어그램 수정도 같이..)
    public LocalTime requestNextAlarm_2(){
        currentAlarmTimerIndex = (currentAlarmTimerIndex + 1) % 4;
        return alarm[currentAlarmTimerIndex].requestExpirationTime();
    }

    public LocalTime requestNextAlarm() {
        this.currentAlarmTimerIndex = (this.currentAlarmTimerIndex+1) % 4;
        LocalTime time = alarm[currentAlarmTimerIndex].requestExpirationTime();
        return time;

        //어떤 Type을 modeManager에 전달해줄지..?
        //여기가 display를 사용하는 주체가 되는것..?
    }


    public LocalTime requestFirstAlarm() {
        this.currentAlarmTimerIndex = 0;
        LocalTime time = alarm[currentAlarmTimerIndex].requestExpirationTime();
        return time;
    }


    public void turnOnOffAlarm() {
        alarm[currentAlarmTimerIndex].toggleAlarmTimer();
    }

    /**
     * AlarmTimer의 현재 시간을 복사 한 뒤..
     *
     */
    public void enterEditAlarm() {
        //
        copyOfAlarmTimer = this.alarm[currentAlarmTimerIndex].requestExpirationTime();

        //깜박이는 커서 display...
        //copyOfAlarmTimer를 사용해서.

    }

    /**
     *
     */
    public void increaseAlarmTime() {
        //커서가 '시'에 있을 때 LOCALDATETIME을 활용해서
        if(isCursorOnHour == true)
            copyOfAlarmTimer = copyOfAlarmTimer.plusHours(1);
        else
            copyOfAlarmTimer = copyOfAlarmTimer.plusMinutes(1);
    }

    /**
     *
     */
    public void decreaseAlarmTime() {
        if(isCursorOnHour == true)
            copyOfAlarmTimer = copyOfAlarmTimer.minusHours(1);
        else
            copyOfAlarmTimer = copyOfAlarmTimer.minusMinutes(1);
    }

    /**
     *GUI에서 사용할 커서.
     */

    //
    public boolean isCursorHour(){
        return isCursorOnHour;
    }

    public void changeCursor() {
        this.isCursorOnHour = !isCursorOnHour;
    }

    /**
     *
     */
    public void saveAlarm() {
        this.alarm[currentAlarmTimerIndex].saveAlarmTime(copyOfAlarmTimer);
    }

    //추가 - getter
    public int getCurrentAlarmIndex(){
        return this.currentAlarmTimerIndex;
    }

    //추가 - getter
    public LocalTime getCurrentAlarmTimer(){
        return alarm[currentAlarmTimerIndex].requestExpirationTime();
    }

    public void setActive(boolean act){
        isActivated = act;
    }
    public boolean getActive(){
        return isActivated;
    }

}