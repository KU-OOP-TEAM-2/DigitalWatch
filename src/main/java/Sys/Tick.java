package Sys;
import java.util.*;
import java.util.concurrent.Callable;
import java.time.*;

public class Tick implements Callable<Void>{
    Tick(){

    }

    public Void call(){
        LocalDateTime currentTime,prevTime;

        //set base time
        prevTime = LocalDateTime.now();

        int elapesdTime;
        int checkSecond = 0;
        while(true){
            //set current time
            currentTime = LocalDateTime.now();

            // |base-currentTime|
            Duration duration = Duration.between(prevTime, currentTime);

            elapesdTime = duration.getNano()*1000000;

            //tick per 10millisecond
            if(elapesdTime == 10) {
                //stopwatch

                //when duration = 1 second
                if (checkSecond == 100) {
                    checkSecond = 0;
                    //time

                    //calorie check

                    //alarm
                }
                //not yet 1 second
                else {
                    checkSecond++;
                }

                //set new base time
                prevTime = LocalDateTime.now();
            }
        }
    }
}
