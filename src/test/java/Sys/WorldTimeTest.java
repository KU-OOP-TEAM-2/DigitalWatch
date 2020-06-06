package Sys;

import org.junit.Test;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class WorldTimeTest {

    @Test
    public void changeTimezone1() {
        WorldTime w= new WorldTime();
        w.changeTimezone();
        int result=w.getCurrentCity();
        assertEquals(1,result);


        w.changeTimezone();
        w.changeTimezone();
        w.changeTimezone();
        w.changeTimezone();
        w.changeTimezone();

        result=w.getCurrentCity();
        assertEquals(0,result);
    }


    @Test
    public void getWorldTime() {
        Time t=new Time();
        WorldTime w= new WorldTime();
        w.changeTimezone();

        LocalDateTime tm=t.getCurrentTime();//set gmt to paris
        int gmt=t.getGMT();
        int result=w.getWorldTime(tm,gmt).getHour();

        assertEquals(16,result);
    }
}