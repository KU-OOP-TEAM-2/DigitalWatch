package Sys;

import java.util.*;
import java.time.LocalTime;

/**
 * 
 */
public class StopWatch implements Mode{

    /**
     * Default constructor
     */
    //객체를 만들 때 Time, Timer, Alarm, Stopwatch를 Activate 시키고 그 외 2개는 Deactivate.
    public StopWatch() {
        currentStopWatchTime.of(0,0,0,0);
        isActivated = true;
        lapTime.of(0,0,0,0);
        isPaused = true;
        overflowStopWatchTime.of(1,39,59,99000000);

    }


    private LocalTime currentStopWatchTime;
    private LocalTime lapTime;
    private LocalTime overflowStopWatchTime;

    private Boolean isPaused;


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
        currentStopWatchTime.of(0,0,0,0);
        lapTime.of(0,0,0,0);
    }

    public void lapStopwatch() {
        lapTime = currentStopWatchTime;
    }

    /**
     * TimeFlow.
     * Tick에서 사용할 함수.
     * Paused되지 않았다면 틱마다 1ms == 1e7만큼 증가.
     * Paused되어있다면 증가시키지 않는다.
     * 증가되는 함수는 GUI Display부분이나 중간 단계에서 01:39:59:99를 99:59:00으로 표현하도록 parsing필요.
     * 01:39:59:99를 지난다면 overflow가 발생하도록, 00:00:00:00부터 시작하도록 함.
    **/
    public void increaseCurrentTime() {
        if(!isPaused) {
            if (this.currentStopWatchTime.compareTo(overflowStopWatchTime) == 0) {
                currentStopWatchTime.of(0, 0, 0, 0);
            } else {
                //millisecond의 앞 두자리만 보여줄것이므로 1e7
                currentStopWatchTime.plusNanos(10000000);
            }
        }
        else
            return;
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
        currentStopWatchTime.of(0,0,0,0);
        lapTime.of(0,0,0,0);
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

}