package Sys;

import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class CalorieCheckTest {


    @Test
    public void changeCursor() {
        CalorieCheck calorieCheck = new CalorieCheck();
        boolean cursor = calorieCheck.getCursor();
        calorieCheck.changeCursor();

        //커서를 바꿔보고 실제로 바뀌었는지 확인
        assertEquals(!cursor, calorieCheck.getCursor());
    }

    /*@Test
    public void increaseData() {
        CalorieCheck calorieCheck = new CalorieCheck();

        //cursor의 기본값은 true
        //cursor = true, tempWeight 증가
        assert

        //cursor = false, Speed 증가
    }

    @Test
    public void decreaseData() {
    }*/

    @Test
    public void saveCalorieSetting() {
        CalorieCheck calorieCheck = new CalorieCheck();

        int expectedSpeed = 10;
        int expectedWeight = 66;
        //temp speed, weight 설정
        calorieCheck.setTempSpeed(expectedSpeed);
        calorieCheck.setTempWeight(expectedWeight);

        calorieCheck.saveCalorieSetting();

        //temp speed, weight가 실제 speed, weight에 저장됐는지 확인
        assertEquals(expectedSpeed, calorieCheck.getSpeed());
        assertEquals(expectedWeight, calorieCheck.getWeight());

        //cursor=true로 변했는지 확인
        assertTrue(calorieCheck.getCursor());
    }

    @Test
    public void enterSetSpeedandWeight() {
        CalorieCheck calorieCheck = new CalorieCheck();

        calorieCheck.enterSetSpeedandWeight();
        //실제 speed, weight가 tempSpeed, tempWeight에 들어갔는지 확인
        assertEquals(calorieCheck.getSpeed(), calorieCheck.getTempSpeed());
        assertEquals(calorieCheck.getWeight(), calorieCheck.getTempWeight());
    }

    @Test
    public void startCalorieCheck() {
        CalorieCheck calorieCheck = new CalorieCheck();

        calorieCheck.startCalorieCheck();
        //flag 설정 제대로 됐는지 확인
        assertFalse(calorieCheck.getIsPause());
        assertTrue(calorieCheck.getIsStart());
    }

    @Test
    public void resumeCaloreCheck() {
        CalorieCheck calorieCheck = new CalorieCheck();

        calorieCheck.resumeCaloreCheck();
        //flag 설정 제대로 됐는지 확인
        assertEquals(false, calorieCheck.getIsPause());
    }

    @Test
    public void pauseCalorieCheck() {
        CalorieCheck calorieCheck = new CalorieCheck();

        //flag 설정 제대로 됐는지 확인
        assertEquals(true, calorieCheck.getIsPause());
    }

    @Test
    public void endCalorieCheck() {
        CalorieCheck calorieCheck = new CalorieCheck();

        //flag 설정 제대로 됐는지 확인
        assertEquals(true, calorieCheck.getIsPause());
        assertEquals(false, calorieCheck.getIsStart());
    }

    @Test
    public void increaseCalorieCheckTimer() {
        CalorieCheck calorieCheck = new CalorieCheck();

        LocalTime time = calorieCheck.getCalorieTime();
        calorieCheck.startCalorieCheck();
        //pause = false, start = true
        //정상적일 때
        calorieCheck.increaseCalorieCheckTimer();
        time = time.plusNanos(10000000);
        assertEquals(time, calorieCheck.getCalorieTime());

        //23시 59분 59초가 됐을 때
        /*
        1s = 10^3ms

        increaseCalorieCheckTimer 1번 당 10ms
        = 100번당 1초

        23시 59분 59초를 초로 환산하면
        23 * 3600 + 59 * 60 + 59 = n

        즉, CalorieTime이 초기화된 상태일 때
        increaseCalorieCheckTimer를 n*100번만큼 호출하면
        23시 59분 59초까지 간다.

        단, 처음에 한번 호출했으니 n*100 - 1만큼 호출한다.
        */
        double n = 23 * 3600 + 59 * 60 + 59;
        n = n*100;
        for(int i=0; i<n-1; i++){
            calorieCheck.increaseCalorieCheckTimer();
        }
        time = LocalTime.of(23, 59, 59);

        //시간이 23시 59분 59초까지 늘어났는지 체크
        //assertEquals(time, calorieCheck.getCalorieTime());

        assertTrue(calorieCheck.getIsPause());
        assertFalse(calorieCheck.getIsStart());
    }

    @Test
    public void resetCalorieCheck() {
        CalorieCheck calorieCheck = new CalorieCheck();

        calorieCheck.resetCalorieCheck();

        assertFalse(calorieCheck.getCursor());
        assertEquals(0, calorieCheck.getCalorie());
        assertEquals(LocalTime.of(0,0,0,0)
            , calorieCheck.getCalorieTime());
    }
}