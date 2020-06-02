package Sys;

import java.time.*;
import java.util.*;

/*
import GUI class;
import Time class;
 */
public class CalorieCheck implements Mode{

    /**
     * Default constructor
     */
    public CalorieCheck() {
        pauseCalorieCheck = true;
        isActivated = false;
        cursor = false;
        Speed = 5;
        Weight = 60;
        Calorie = 0;
//        CalorieTime = call system time library
    }

    /**
     *
     */
    private int Speed;
    private int tempSpeed;
    public int getSpeed() {return Speed;};
    public void setSpeed(int Speed) {this.Speed = Speed;}
    /**
     *
     */
    private int Weight;
    private int tempWeight;
    public int getWeight() {return Weight;}
    public void setWeight(int Weight){this.Weight = Weight;}
    /**
     *
     */
    private double Calorie;
    public double getCalorie() {return Calorie;}
    public void setCalorie(int Calorie){this.Calorie = Calorie;}

    /**
     *
     */
    private LocalDateTime CalorieTime;
    public LocalDateTime getCalorieTime() {return CalorieTime;}
//    flag

    private boolean pauseCalorieCheck;
    private boolean isActivated;

    //    false = speed, true = weight
    private boolean cursor;



    /**
     *
     */
    public void changeCursor() {
        cursor = !cursor;
    }

    public void setActive(boolean act) {isActivated = act;};
    public boolean getActive() {return isActivated;};
    /**
     *
     */
    public void increaseData() {
        if(cursor){
            if(Weight == 999){
                Weight = 0;
            } else{
                Weight += 1;
            }
        }else{
            if(Speed == 99){
                Speed = 0;
            } else {
                Speed += 1;
            }
        }
    }

    /**
     *
     */
    public void decreaseData() {
        if(cursor){
            if(tempWeight == 0){
                tempWeight = 999;
            } else{
                tempWeight -= 1;
            }
        }else{
            if(tempSpeed == 0){
                tempSpeed = 99;
            } else {
                tempSpeed -= 1;
            }
        }
    }

    /**
     *
     */
    public void saveCalorieSetting() {
        tempSpeed = Speed;
        tempWeight = Weight;
    }

    /**
     *
     */
    public void enterSetSpeedandWeight() {
//        do some display logic
        tempWeight = Weight;
        tempSpeed = Speed;
    }

    /**
     *
     */
    public void startCalorieCheck() {
        cursor = false;
    }

    /**
     *
     */
    public void resumeCaloreCheck() {
        startCalorieCheck();
    }

    /**
     *
     */
    public void pauseCalorieCheck() {
        pauseCalorieCheck = false;
    }

    /**
     *
     */
    public void increaseCalorieCheckTimer() {
        CalorieTime.plusSeconds(1);
    }

    public void resetCalorieCheck(){
//        pauseCalorieCheck = true;
        cursor = false;
        Speed = 5;
        Weight = 60;
        Calorie = 0;
    }

    private void calculateCalorie(){
        Calorie = 0.0157 * ( ( 0.1 * Speed + 3.5 ) /3.5 ) * Weight * CalorieTime.getSecond();
    }
}
