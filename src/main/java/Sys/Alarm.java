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
        isActivated=true;
        //Thread
        //alarmThread = new AlarmThread("alarmThread");
    }

    /*
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
//                traceCurrentTime = time.getCurrentTime();/
                currentTime = traceCurrentTime.toLocalTime();
                
                for(int i=0; i < 4; i++){
                    if(currentTime.compareTo(alarm[i].requestExpirationTime()) == 0){
                        buzzer.beepBuzzer();
                    }
                }
            }
        }
    }*/
    //추가 - 변수(buzzer객체와 time객체를 받아서 사용하기 위해서)
    private Buzzer buzzer;

    private Time time;


    private AlarmTimer alarm[];

    //현재 Display할 AlarmTimer의 index.
    private int currentAlarmTimerIndex;

    //추가 - 변수
    //현재 Display할, 그리고 현재 보고 있는 Cursor의 위치: (시간, 분 中 택 1)
    private boolean isCursorOnHour;

    //추가 - 변수 AlarmTimer 수정할 때 임시변수.
    private LocalTime copyOfAlarmTimer;
    //추가 - 변수
    private boolean isActivated; 


    public boolean isAlarmTimeCheck(){
        LocalDateTime traceCurrentTime;
        LocalTime currentTime;
        traceCurrentTime = time.getCurrentTime();
        currentTime = traceCurrentTime.toLocalTime();

        for(int i=0;i<4;i++){
            //LocalTime이 xx:xx:00이고 현재시간과 expirationTime을 비교해서 두 조건 충족.
            if(currentTime.getSecond() == 0 && currentTime.compareTo(alarm[i].requestExpirationTime())==-0) {
                buzzer.beepBuzzer();
                return true;
            }
        }
        return false;
    }

    public LocalTime changeAlarm(){
        currentAlarmTimerIndex = (currentAlarmTimerIndex + 1) % 4;
        return alarm[currentAlarmTimerIndex].requestExpirationTime();
    }

    public void turnOnOffAlarm() {
        alarm[currentAlarmTimerIndex].toggleAlarmTimer();
    }

    /**
     * AlarmTimer의 현재 시간을 복사 한 뒤..
     *
     */
    public void enterEditAlarm() {
        copyOfAlarmTimer = this.alarm[currentAlarmTimerIndex].requestExpirationTime();
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

    //현재 보여줄 AlarmTimer.
    public LocalTime getCopyOfAlarmTimer(){
        return copyOfAlarmTimer;
    }

    /**
     *
     */

    //GUI에서 사용할 커서.
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