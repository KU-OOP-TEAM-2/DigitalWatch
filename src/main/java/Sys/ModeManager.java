package Sys;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 */
public class ModeManager {

    /**
     * Default constructor
     */
    public ModeManager() {

        buzzer = new Buzzer();


        modes = new Mode[5];
        modes[0] = new Time();
        modes[1] = new Alarm(buzzer, modes[0]);
        modes[2] = new Timer();
        modes[3] = new StopWatch();
        modes[4] = new CalorieCheck();
        modes[5] =new WorldTime();
        nowMode = modes[0];
        
        isEditMode = false;
    }

    //Time Alarm Timer Stopwatch Calorie Check 순
    //0     1       2       3       4       5
    private Boolean[] isModeActive;
    private Boolean[] editStatus;//set mode 중 return to default screen시 저장안하기 위해 이 변수로 편집.

    private Mode[] modes;
    private int currentMode;//0~5 Time Alarm Timer Stopwatch Calorie Check 순으로    *8일경우 setMode
    private int[] activeList;//활성된거 4개가 들어가는 배열 4개가 활성화되어있을 때 비활성화된걸 활성화시킬때 관리하기위함
    //각 mode에 대한 index값이 들어가게 됨.

    //private Button clickedButton;
    private Boolean longClickedFlag;
    private Boolean buzzerFlag;
    private int elapsedTime;
    private int ActiveModeCounter;//setMode때 4개의 mode가 활성화되어야만 탈출가능 그때 참조할 변수 active된 mode의 개수
    private int currentCursor;//setMode시에 status값을 바꿀 때 참조할 index

    //Mode  Adjust  Forward Reverse
    //0     1       2       3
    private int Button;
    private boolean isEditMode;

    //객체 생성 ms
    private Buzzer buzzer;

    //private CalorieCheck calorieCheck;

    private Mode nowMode;
    //ms

    public void setButton(int Button){this.Button = Button;}
    public int getButton() {return this.Button;}

    public void setLongClickedFlag(boolean flag){longClickedFlag = flag;}


    public void makeThread(){
        Tick task = new Tick();
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Void> future = service.submit(task);
    }




    /**
     *
     */
    public void changeMode() {
        while(true){
            currentMode =(currentMode+1)%6;
            if(modes[currentMode].getActive() == true){
                nowMode = modes[currentMode];
                break;
            }
        }
    }

    /**
     *
     */
    public void clcikButton() {
        // TODO implement here
        if(currentMode==0 && Button==0 && longClickedFlag==false && isEditMode==false){

        }


    }


    /*
    setMode
    currentMode를 8으로 설정(for display)
     */

    public void enterEditMode() {
        this.currentMode=8;
        this.currentCursor=0;
        this.editStatus=this.isModeActive;
    }


    public void changeCursor() {
        if(this.currentCursor==5) {
            this.currentCursor=0;
            return;
        }
        this.currentCursor+=1;
    }


    public void changeStatus() {// active->disable  disable->active
        if(this.editStatus[this.currentCursor]) {
            this.editStatus[this.currentCursor]=false;
            for(int i=0;i<4;i++) {
                if(this.activeList[i]==this.currentCursor) {
                    for(int j=i+1;j<4;j++) {
                        this.activeList[j-1]=this.activeList[j];
                    }
                    this.ActiveModeCounter--;
                }
            }
        }
        else {
            this.editStatus[this.currentCursor]=true;
            if(this.ActiveModeCounter==4) {
                for(int i=1;i<4;i++) {
                    this.activeList[i-1]=this.activeList[i];
                }
                this.activeList[3]=this.currentCursor;
            }
            else {
                this.activeList[this.ActiveModeCounter-1]=this.currentCursor;
                this.ActiveModeCounter++;
            }
        }
    }


    public void saveModeData() {
        this.isModeActive=this.editStatus;
    }

    //시퀀스 다이어그램 수정 사항. 없애도 되는 함수.
    public void changeToFirstMode() {
        // TODO implement here
    }

    /**
     *
     */

}
