package Sys;

import java.util.*;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * 
 */
public class StopWatch implements Mode{

    /**
     * Default constructor
     */
    //객체를 만들 때 Time, Timer, Alarm, Stopwatch를 Activate 시키고 그 외 2개는 Deactivate.
    public StopWatch() {
        LocalDate temp = LocalDate.now();
        currentStopWatchTime = LocalTime.of(0,0,0,0);
        isActivated = true;
        lapTime= LocalTime.of(0,0,0,0);
        isPaused = true;
        overflowStopWatchTime= LocalTime.of(1,39,59,990000000);


        isEnd = false;
    }

    private LocalTime currentStopWatchTime;
    private LocalTime lapTime;
    private LocalTime overflowStopWatchTime;
    private int is100ms = 0;
    private Boolean isPaused;
    private Boolean isEnd;


    public void startStopwatch() {
        isPaused = false;
    }

    /**
     *
     */
    public void resumeStopwatch() {
        isPaused = false;
    }

    /**
     * 
     */
    public void pauseStopwatch() {
        isPaused = true;
    }

    //버튼 검사를 해서 paused상태에서만 기능이 작동하는지 검사.
    public void resetStopwatch() {
        currentStopWatchTime = LocalTime.of(0,0,0,0);
        lapTime = LocalTime.of(0,0,0,0);
        isEnd = false;
    }

    public void lapStopwatch() {
        if(isPaused != true) lapTime = currentStopWatchTime;
    }

    /**
     * TimeFlow.
     * Tick에서 사용할 함수.
     * Paused되지 않았다면 틱마다 1ms == 1e7만큼 증가.
     * Paused되어있다면 증가시키지 않는다.
     * 최대 시간이 되면 정지시킨다.
    **/
    public void increaseCurrentTime() {
        is100ms = (is100ms + 1) % 10;
            if(!isPaused && isEnd!=true) {
                if (this.currentStopWatchTime.compareTo(overflowStopWatchTime) >= 0) {
                    pauseStopwatch();
                    isEnd = true;
                } else {
                    currentStopWatchTime = currentStopWatchTime.plusNanos(10000000);
                }
            }
        }


    //이게 왜 필요한지 모르곘습니다... 일단은 그냥 두겠습니다.
    public void stopCurrentTime() {
        lapTime = currentStopWatchTime;
    }

    //이게 왜 필요한지 모르겠습니다... 일단은 그냥 두겠습니다.
    public void saveLapTime() {
        lapTime = currentStopWatchTime;
    }
    //이게 왜 필요한지 모르겠습니다... 일단은 그냥 두겠습니다.
    public void resetCurrentTime() {
        currentStopWatchTime = LocalTime.of(0,0,0,0);
        lapTime = LocalTime.of(0,0,0,0);
    }

    private boolean isActivated;
    public void setActive(boolean act) {
        this.isActivated=act;
    }
    public boolean getActive() {
        return this.isActivated;
    }
    public boolean getIsPaused(){
        return isPaused;
    }

    public LocalTime getCurrentStopWatchTime(){return currentStopWatchTime;}
    public LocalTime getLapStopWatchTime(){return lapTime;}


}
