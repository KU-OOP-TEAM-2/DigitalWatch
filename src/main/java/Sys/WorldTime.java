package Sys;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
//gmt 값->서울 9 ,파리1, 런던0,  시드니10,LA -7 ,뉴욕 -4
//서울 SEL, 파리PAR, 런던LON,시드니SYD,LA LAX , 뉴욕 NYC
public class WorldTime {
    private City worldTimes[];
    private int CurrentCity;//0~5


    public WorldTime(LocalDateTime currentTime) implements Mode{
        this.worldTimes[0]=new City(0,"SEL");
        this.worldTimes[1]=new City(1,"PAR");
        this.worldTimes[2]=new City(0,"LON");
        this.worldTimes[3]=new City(0,"SYD");
        this.worldTimes[4]=new City(0,"LAX");
        this.worldTimes[5]=new City(0,"NYC");

        this.CurrentCity=0;
    }


    public void changeTimezone() {
        if(this.CurrentCity==5)
            this.CurrentCity=0;
        else {
            this.CurrentCity+=1;
        }
    }

    //gui에 전달해줄 시간 modemanager로부터 time에 들어있는 시간에대한 정보를 전달받는다.
    public LocalDateTime getWorldTime(LocalDateTime currentTime,int currentGMT) {
        int gap=currentGMT-this.worldTimes[this.CurrentCity].getGMT();
        LocalDateTime returnTime=currentTime.plusHours(-gap);
        if(returnTime.getYear()>9999) {//9999이상일경우 0부터다시 시작
            returnTime.plusYears(-10000);
        }
        else if(returnTime.getYear()<0) {//음수일경우 9999부터
            returnTime.plusYears(10000);
        }

        return returnTime;
    }

}