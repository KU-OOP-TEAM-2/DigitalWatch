package Sys;

import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class AlarmTests extends TestCase {

    public void tearDown() throws Exception {
    }

    public void testIsAlarmTimeCheck() {

    }

    public void testChangeAlarm() {
        Buzzer buzzer = new Buzzer();
        Time time = new Time();

        Alarm alarm = new Alarm(buzzer, time);

        alarm.changeAlarm();
        alarm.changeAlarm();
        alarm.changeAlarm();
        alarm.changeAlarm();

        assertEquals(0, alarm.getCurrentAlarmIndex());
    }

    public void testTurnOnOffAlarm() {
        Buzzer buzzer = new Buzzer();
        Time time = new Time();

        Alarm alarm = new Alarm(buzzer, time);

        alarm.changeAlarm();
        alarm.turnOnOffAlarm();
        boolean isActivated = alarm.getCurrentAlarmTimerObject().isActivatedTimer();

        assertEquals(true, isActivated);

    }

    public void testEnterEditAlarm() {

    }

    public void testIncreaseAlarmTime() {
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

        assertEquals(0,  t.compareTo(LocalTime.of(3,2,0)));
    }

    public void testDecreaseAlarmTime() {
        Buzzer buzzer = new Buzzer();
        Time time = new Time();

        Alarm alarm = new Alarm(buzzer, time);

        alarm.enterEditAlarm();
        alarm.increaseAlarmTime(); alarm.increaseAlarmTime(); alarm.increaseAlarmTime();
        alarm.changeCursor();
        alarm.increaseAlarmTime();alarm.increaseAlarmTime();

        alarm.decreaseAlarmTime();
        alarm.changeCursor();
        alarm.decreaseAlarmTime();

        LocalDateTime s = alarm.getCopyOfAlarmTimer();
        LocalTime t = s.toLocalTime();

        assertEquals(0,  t.compareTo(LocalTime.of(2,1,0)));
    }

    public void testChangeCursor() {
        Buzzer buzzer = new Buzzer();
        Time time = new Time();

        Alarm alarm = new Alarm(buzzer, time);
        alarm.changeCursor(); alarm.changeCursor(); alarm.changeCursor(); alarm.changeCursor();
        assertEquals(true, alarm.isCursorHour());

    }

    public void testSaveAlarm() {
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
}