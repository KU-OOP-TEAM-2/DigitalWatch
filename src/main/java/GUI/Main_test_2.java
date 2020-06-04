package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import Sys.*;

public class Main_test_2{

  public static ModeManager _modeManager;

  public static void main(String[] args) {

    _modeManager = new ModeManager();

    watchGUI mainGUI = new watchGUI(); //initialized with TimeKeeping mode
    Thread updateGUI = new Thread(mainGUI);
    updateGUI.start();

    //test thread
    Thread tickCount = new Thread(new Runnable() {
      @Override
      public void run() {
        LocalDateTime ldt = LocalDateTime.of(2020,01,01,00,00,00,00);
        while(true){
          ldt = ldt.plusSeconds(1);
          mainGUI.getTimeKeepingPane().setCurrentTime(ldt);
          System.out.println(mainGUI.getTimeKeepingPane().getCurrentTime());
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });
    tickCount.start();

    mainGUI.getModeB().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        switch(_modeManager.getCurrentMode()){
          case 1:
            switchPanel(mainGUI, mainGUI.getTimerPane());
            break;
          case 2:
            switchPanel(mainGUI, mainGUI.getAlarmPane());
            break;
          case 3:
            switchPanel(mainGUI, mainGUI.getSwPane());
            break;
          case 4:
            switchPanel(mainGUI, mainGUI.getTimeKeepingPane());
            break;
          default:
            break;
        }
      }
    });

  }

  public static void switchPanel(watchGUI mainGUI, JPanel newPanel){
    mainGUI.getFramePane().remove(mainGUI.getWatchBodyPane());
    mainGUI.setWatchBodyPane(newPanel);
    mainGUI.getFramePane().add(mainGUI.getWatchBodyPane());
    mainGUI.revalidate();
    mainGUI.repaint();
  }

}
