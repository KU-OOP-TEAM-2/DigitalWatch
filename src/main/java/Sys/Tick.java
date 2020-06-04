package Sys;
import java.util.*;
import java.util.concurrent.Callable;
import java.time.*;

public class Tick implements Callable<Void>{
    private static boolean endFlag;

    Tick(){
        endFlag = false;
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
            if(elapsedTime == 10) {
                //stopwatch
                ((StopWatch)ModeManager.SingletonModeManager.getmodes()[3]).increaseCurrentTime();
                ((Timer)ModeManager.SingletonModeManager.getmodes()[2]).decreaseTimer();
                //when duration = 1 second
                if (checkSecond == 100) {
                    checkSecond = 0;
                    //time

                    //calorie check

                    //Timer

                    //alarm
                    //Buzzer를 여기서 울리는것이 아니라 Alarm객체 내부에서 울리도록 함.
                    //구현 방식의 차이에 따라 추후 바뀔 수 있는 부분입니다.
                    ((Alarm)ModeManager.SingletonModeManager.getmodes()[1]).isAlarmTimeCheck();
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
