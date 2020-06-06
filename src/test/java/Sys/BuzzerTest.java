package Sys;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class BuzzerTest {

    @Test
    public void testExpiredAlarmBuzzer() {
        ModeManager man = new ModeManager();
        Buzzer buzzer = man.getBuzzer();

        Alarm alarm = ((Alarm)(man.getmodes()[1]));
        alarm.enterEditAlarm();
        alarm.increaseAlarmTime();
        alarm.changeCursor();
        alarm.increaseAlarmTime();
        alarm.saveAlarm();
        alarm.turnOnOffAlarm();
        Time time = ((Time)(man.getmodes()[0]));
        time.setCurrentTime(LocalDateTime.of(2020,1,1,1,1));

        alarm.isAlarmTimeCheck();
        assertEquals(true, buzzer.getBuzzerOn());
        assertTrue(buzzer.getIsAlarmRinging());
    }

    @Test
    public void teststopBuzzer() {
        ModeManager man = new ModeManager();
        Buzzer buzzer = man.getBuzzer();

        Alarm alarm = ((Alarm)(man.getmodes()[1]));
        alarm.enterEditAlarm();
        alarm.increaseAlarmTime();
        alarm.changeCursor();
        alarm.increaseAlarmTime();
        alarm.saveAlarm();
        alarm.turnOnOffAlarm();
        Time time = ((Time)(man.getmodes()[0]));
        time.setCurrentTime(LocalDateTime.of(2020,1,1,1,1));

        alarm.isAlarmTimeCheck();
        assertTrue(buzzer.getBuzzerOn());
        buzzer.stopBuzzer();
        assertFalse(buzzer.getBuzzerOn());
        assertFalse(buzzer.getIsAlarmRinging());

    }
}