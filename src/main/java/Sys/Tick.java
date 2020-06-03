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
        currentTime = LocalDateTime.now();
        prevTime = currentTime;

        while(true){
            if(endFlag){
                break;
            }
            Duration duration = Duration.between(prevTime, currentTime);

            int elapesdTime = duration.getNano()*1000000;
            int checkSecond = 0;
            int checkMinute = 0;
            //tick per 10millisecond
            if(elapesdTime == 10) {
                //stopwatch

                //when duration = 1 second
                if (checkSecond == 100) {
                    checkSecond = 0;
                    //time

                    //calorie check

                    //Timer
                    ((Timer)ModeManager.SingletonModeManager.getmodes()[2]).decreaseTimer();

                    //alarm
                }
                //not yet 1 second
                else {
                    checkSecond++;
                }

                //when duration = 1 minute
                if(checkMinute == 6000){
                    checkMinute = 0;
                    //alarm

                }
                //not yet 1 minute
                else{
                    checkMinute++;
                }
            }
        }
        return null;
    }
}
