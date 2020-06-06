package Sys;

import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class StopWatchTest {

    @Test
    public void teststartStopwatch() {
        StopWatch stopWatch = new StopWatch();
        int i;
        stopWatch.startStopwatch();
        for(i=0; i < 10002; i++)
            stopWatch.increaseCurrentTime();
        assertEquals(0, stopWatch.getCurrentStopWatchTime().compareTo(LocalTime.of(0,1,40,20000000)));

        assertEquals(false, stopWatch.getIsPaused());

    }
    @Test
    public void teststartStopwatch_overflow() {
        StopWatch stopWatch = new StopWatch();
        int i;
        stopWatch.startStopwatch();
        for(i=0; i < 60000000; i++)
            stopWatch.increaseCurrentTime();
        assertEquals(0, stopWatch.getCurrentStopWatchTime().compareTo(LocalTime.of(1,39,59,990000000)));

        assertEquals(true, stopWatch.getIsPaused());

    }

    @Test
    public void testresumeStopwatch() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.startStopwatch(); stopWatch.pauseStopwatch();

        stopWatch.resumeStopwatch();
        assertFalse(stopWatch.getIsPaused());
    }

    @Test
    public void testpauseStopwatch() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.startStopwatch();
        stopWatch.pauseStopwatch();
        assertTrue(stopWatch.getIsPaused());
    }

    @Test
    public void testresetStopwatch() {
        StopWatch stopWatch = new StopWatch();
        int i;
        stopWatch.startStopwatch();
        for(i=0; i < 10002; i++)
            stopWatch.increaseCurrentTime();

        stopWatch.resetStopwatch();
        assertEquals(0, stopWatch.getCurrentStopWatchTime().compareTo(LocalTime.of(0,0,0)));
    }

    @Test
    public void testlapStopwatch() {
        StopWatch stopWatch = new StopWatch();
        int i;
        stopWatch.startStopwatch();
        for(i=0; i < 10002; i++)
            stopWatch.increaseCurrentTime();
        stopWatch.lapStopwatch();
        for(i=0; i < 10002; i++)
            stopWatch.increaseCurrentTime();
        assertEquals(0,  stopWatch.getLapStopWatchTime().compareTo(LocalTime.of(0,1,40,20000000)));
    }

    @Test
    public void testincreaseCurrentTime() {

        StopWatch stopWatch = new StopWatch();

        stopWatch.startStopwatch();
        int i;


        for(i=0; i < 6; i++)
            stopWatch.increaseCurrentTime();
        assertFalse(stopWatch.getIsPaused());

        for(i=0; i < 70000000; i++)
            stopWatch.increaseCurrentTime();
        assertEquals(0, stopWatch.getCurrentStopWatchTime().compareTo(LocalTime.of(1,39,59,990000000)));

    }


}