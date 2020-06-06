package Sys;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class AlarmTest {

    public void tearDown() throws Exception {
    }

    @Test
    public void IsAlarmTimeCheck() {
        ModeManager man = new ModeManager();
        Buzzer buzzer = man.getBuzzer();

        Alarm alarm = ((Alarm)(man.getmodes()[1]));
        alarm.enterEditAlarm();
        alarm.increaseAlarmTime();
        alarm.changeCursor();
        alarm.increaseAlarmTime();
        alarm.saveAlarm();
        Time time = ((Time)(man.getmodes()[0]));
        time.setCurrentTime(LocalDateTime.of(2020,1,1,1,1));

        alarm.isAlarmTimeCheck();
        assertEquals(false, buzzer.getBuzzerOn());
        assertFalse(buzzer.getIsAlarmRinging());
    }

    @Test
    public void ChangeAlarm() {
        Buzzer buzzer = new Buzzer();
        Time time = new Time();

        Alarm alarm = new Alarm(buzzer, time);

        alarm.changeAlarm();
        alarm.changeAlarm();
        alarm.changeAlarm();
        alarm.changeAlarm();

        assertEquals(0, alarm.getCurrentAlarmIndex());
    }

    @Test
    public void TurnOnOffAlarm() {
        Buzzer buzzer = new Buzzer();
        Time time = new Time();

        Alarm alarm = new Alarm(buzzer, time);

        alarm.changeAlarm();
        alarm.turnOnOffAlarm();
        boolean isActivated = alarm.getCurrentAlarmTimerObject().isActivatedTimer();

        assertEquals(true, isActivated);

    }

    @Test
    public void IncreaseAlarmTime() {
        Buzzer buzzer = new Buzzer();
        Time time = new Time();
        int i;
        Alarm alarm = new Alarm(buzzer, time);

        alarm.enterEditAlarm();
        for(i=0; i < 25; i++)
            alarm.increaseAlarmTime();
        alarm.changeCursor();
        for(i=0; i < 70; i++)
            alarm.increaseAlarmTime();
        LocalDateTime s = alarm.getCopyOfAlarmTimer();
        LocalTime t = s.toLocalTime();

        assertEquals(0,  t.compareTo(LocalTime.of(2,10,0)));
    }


    @Test
    public void DecreaseAlarmTime() {
        Buzzer buzzer = new Buzzer();
        Time time = new Time();

        Alarm alarm = new Alarm(buzzer, time);

        alarm.enterEditAlarm();
        alarm.decreaseAlarmTime();
        alarm.changeCursor();
        alarm.decreaseAlarmTime();

        LocalDateTime s = alarm.getCopyOfAlarmTimer();
        LocalTime t = s.toLocalTime();

        assertEquals(0,  t.compareTo(LocalTime.of(22,59,0)));
    }


    @Test
    public void ChangeCursor() {
        Buzzer buzzer = new Buzzer();
        Time time = new Time();

        Alarm alarm = new Alarm(buzzer, time);
        alarm.changeCursor(); alarm.changeCursor(); alarm.changeCursor(); alarm.changeCursor();
        assertEquals(true, alarm.isCursorHour());

    }

    @Test
    public void SaveAlarm() {
        Buzzer buzzer = new Buzzer();
        Time time = new Time();

        Alarm alarm = new Alarm(buzzer, time);
        alarm.enterEditAlarm();
        alarm.increaseAlarmTime(); alarm.increaseAlarmTime(); alarm.increaseAlarmTime();
        alarm.changeCursor();
        alarm.increaseAlarmTime();
        alarm.increaseAlarmTime();
        LocalDateTime s = alarm.getCopyOfAlarmTimer();
        LocalTime t = s.toLocalTime();

        alarm.saveAlarm();
        assertEquals(0, alarm.getCopyOfAlarmTimer().toLocalTime().compareTo(alarm.getCurrentAlarmTimerObject().requestExpirationTime()));

    }
    
    @Test
    public void TurnOffAlarm(){
        ModeManager man = new ModeManager();
        Buzzer buzzer = man.getBuzzer();

        Alarm alarm = ((Alarm)(man.getmodes()[1]));
        alarm.enterEditAlarm();
        alarm.increaseAlarmTime();
        alarm.changeCursor();
        alarm.increaseAlarmTime();
        alarm.saveAlarm();
        Time time = ((Time)(man.getmodes()[0]));
        time.setCurrentTime(LocalDateTime.of(2020,1,1,1,1));

        alarm.isAlarmTimeCheck();
        assertEquals(false, buzzer.getBuzzerOn());
        assertFalse(buzzer.getIsAlarmRinging());

        alarm.turnOnOffAlarm();
        alarm.isAlarmTimeCheck();
        assertEquals(true, buzzer.getBuzzerOn());

    }

}