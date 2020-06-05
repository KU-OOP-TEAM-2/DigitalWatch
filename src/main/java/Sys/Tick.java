package Sys;
import java.util.*;
import java.util.concurrent.Callable;
import java.time.*;

public class Tick implements Callable<Void>{
    private static boolean endFlag;
    private ModeManager myModeManager;
    private Mode[] modes;

    Tick(ModeManager myModeManager){
        endFlag = false;
        this.myModeManager = myModeManager;
        modes = myModeManager.getmodes();
    }

    public void setEndFlag(boolean flag){endFlag = flag;}

    public Void call(){
        LocalDateTime currentTime,prevTime;

        //currentTime : 현재 시간 저장
        currentTime = LocalDateTime.now();

        //prevTime : 기준점 시간 저장
        prevTime = currentTime;
        int elapsedTime;
        int checkSecond = 0;
        int checkMinute = 0;
        while(true){
            if(endFlag){
                break;
            }
            //현재 시간 갱신
            currentTime = LocalDateTime.now();
            Duration duration = Duration.between(prevTime, currentTime);

            elapsedTime= duration.getNano()/1000000;

            //tick per 10millisecond
            if(elapsedTime >= 10) {
                //Timer
                ((Timer)modes[2]).decreaseTimer();
                //stopwatch : ms단위 갱신에 어색함이 있어서 따로 갱신 시간을 다르게 하였음
                myModeManager.plus_ElapsedTime(0.01f);
                ((Alarm)modes[1]).isAlarmTimeCheck();
                ((StopWatch)modes[3]).increaseCurrentTime();
                //calorie check
                ((CalorieCheck)modes[4]).increaseCalorieCheckTimer();
                //when duration = 1 second
                if (checkSecond == 100) {
                    checkSecond = 0;
                    //Time
                    ((Time)modes[0]).timeflow();

                    //alarm
                    //Buzzer를 여기서 울리는것이 아니라 Alarm객체 내부에서 울리도록 함.
                    //구현 방식의 차이에 따라 추후 바뀔 수 있는 부분입니다.
                    //((Alarm)modes[1]).isAlarmTimeCheck();
                }
                //not yet 1 second
                else {
                    checkSecond++;
                }

                //when duration = 1 minute
                /*사용하지 않을 부분..?*/
                if(checkMinute == 6000){
                    checkMinute = 0;
                    //alarm

                }
                //not yet 1 minute
                else{
                    checkMinute++;
                }

                //기준 시간점 새로 갱신
                prevTime = LocalDateTime.now();
            }
        }
        return null;
    }
}
