package Sys;

import org.junit.Test;

import static org.junit.Assert.*;

public class ModeManagerTest {


    @Test
    public void enterEditMode() {
        ModeManager m = new ModeManager();
        m.enterEditMode();
        int result = m.getCurrentMode();
        assertEquals(8, result);
    }

    @Test
    public void changeCursor() {
        ModeManager m = new ModeManager();
        m.changeCursor();
        int result = m.getCurrentCursor();
        assertEquals(1, result);

        m.changeCursor();
        m.changeCursor();
        m.changeCursor();
        m.changeCursor();
        m.changeCursor();
        result = m.getCurrentCursor();
        assertEquals(0, result);

    }

    @Test
    public void changeStatus() {
        ModeManager m = new ModeManager();
        m.enterEditMode();
        m.changeStatus();
        boolean result = m.getEditStatus()[0];
        assertEquals(false, result);

        assertEquals(3, m.getActiveModeCounter());
    }

    @Test
    public void saveModeData() {
        ModeManager m = new ModeManager();

        m.enterEditMode();
        //1 active deactive설정이 제대로 modes에 반영되는지 test, currentMode를 active한것들 중 첫번째 것으로 설정하는지 확인
        m.changeStatus();
        m.changeCursor();
        m.changeCursor();
        m.changeCursor();
        m.changeCursor();
        m.changeStatus();

        m.saveModeData();

        boolean result = m.getmodes()[0].getActive();
        int mode = m.getCurrentMode();
        assertEquals(false, result);
        assertEquals(1, mode);

        //activemodecounter가 4개가 아닐 때 editmode 탈출안하는지 확인
        m.enterEditMode();
        m.changeStatus();
        m.saveModeData();
        result = m.isEditMode();
        assertEquals(true, result);

    }

    @Test
    public void changeMode() {
        ModeManager manager = new ModeManager();
        manager.changeMode();
        manager.changeMode();
        assertEquals(2, manager.getCurrentMode());
        manager.changeMode();
        manager.changeMode();
        manager.changeMode();
        assertEquals(1, manager.getCurrentMode());

    }
}