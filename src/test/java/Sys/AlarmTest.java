package Sys;

import junit.framework.TestCase;
import static org.junit.Assert.*;

public class AlarmTest extends TestCase {

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
        alarm.changeCursor();
        alarm.increaseAlarmTime();
        alarm.increaseAlarmTime();
    }

    public void testDecreaseAlarmTime() {
    }

    public void testGetCopyOfAlarmTimer() {
    }

    public void testIsCursorHour() {
    }

    public void testChangeCursor() {
    }

    public void testSaveAlarm() {
    }

    public void testGetCurrentAlarmIndex() {
    }

    public void testGetCurrentAlarmTimer() {
    }

    public void testGetCurrentAlarmisActivated() {
    }

    public void testSetActive() {
    }

    public void testGetActive() {
    }
}