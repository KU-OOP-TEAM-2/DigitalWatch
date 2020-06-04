package GUI;

import Sys.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;


public class watchGUI extends JFrame implements Runnable{

	public static ModeManager _modeManager;
	public static Mode[] modes;
	
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 320;
	private static final int BUTTON_WIDTH = 10;
	private static final int BUTTON_HEIGHT = 45;

	private JPanel framePane;

	private JPanel watchBodyPane;

	private TimeKeeping_Pane timeKeepingPane;
	private Alarm_Pane alarmPane;
	private Timer_Pane timerPane;
	private Stopwatch_Pane swPane;
	private WorldTime_Pane wtPane;
	private CalorieCheck_Pane ccPane;

	private JLabel nameLabel;

	private JButton adjustB;
	private JButton modeB;
	private JButton forwardB;
	private JButton reverseB;

	private LocalDateTime currentTime;


	public watchGUI() {

		_modeManager = new ModeManager();

		this.currentTime = ((Time)(_modeManager.getmodes()[0])).getCurrentTime();
		//set window's name
		this.setTitle("Digital Watch");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		timeKeepingPane = new TimeKeeping_Pane(this.currentTime);
		alarmPane = new Alarm_Pane();
		timerPane = new Timer_Pane();
		swPane = new Stopwatch_Pane();
		wtPane = new WorldTime_Pane();
		ccPane = new CalorieCheck_Pane();

		framePane = new JPanel();

		nameLabel = new JLabel("Digital Watch #2");

		adjustB = new JButton();
		adjustB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_modeManager.clickButton(1, false);
			}
		});
		modeB = new JButton();
		modeB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_modeManager.clickButton(0, false);
			}
		});
		forwardB = new JButton();
		forwardB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_modeManager.clickButton(2, false);
			}
		});
		reverseB = new JButton();
		reverseB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_modeManager.clickButton(3, false);
			}
		});

		this.watchBodyPane = new JPanel();

		setWatchBodyPane(timeKeepingPane);

		framePane.setBackground(Color.WHITE);
		framePane.setLayout(null);

		nameLabel.setBounds(12, 10, 94, 15);

//		watchBodyPane.setLocation(45, 30);

		adjustB.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		adjustB.setLocation(watchBodyPane.getLocation().x - BUTTON_WIDTH, watchBodyPane.getLocation().y + 55);
		adjustB.setBackground(Color.BLACK);

		modeB.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		modeB.setLocation(watchBodyPane.getLocation().x - BUTTON_WIDTH, watchBodyPane.getLocation().y + 135);
		modeB.setBackground(Color.BLACK);

		forwardB.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		forwardB.setLocation((watchBodyPane.getLocation().x + watchBodyPane.getWidth()), watchBodyPane.getLocation().y + 55);
		forwardB.setBackground(Color.BLACK);

		reverseB.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		reverseB.setLocation((watchBodyPane.getLocation().x + watchBodyPane.getWidth()), watchBodyPane.getLocation().y + 135);
		reverseB.setBackground(Color.BLACK);
		

		this.add(framePane);

		framePane.add(nameLabel);
		framePane.add(adjustB);
		framePane.add(modeB);
		framePane.add(forwardB);
		framePane.add(reverseB);
		framePane.add(watchBodyPane);

		revalidate();
		repaint();
	}

	//getters
	public TimeKeeping_Pane getTimeKeepingPane() { return timeKeepingPane; }

	public Alarm_Pane getAlarmPane() { return alarmPane; }

	public Timer_Pane getTimerPane() { return timerPane; }

	public Stopwatch_Pane getSwPane() { return swPane; }

	public WorldTime_Pane getWtPane() { return wtPane; }

	public CalorieCheck_Pane getCcPane() { return ccPane; }

	public JPanel getFramePane() {
		return framePane;
	}

	public JPanel getWatchBodyPane() {
		return watchBodyPane;
	}

	public void setWatchBodyPane(JPanel watchBodyPane) {
		this.watchBodyPane = watchBodyPane;
	}

	public JButton getAdjustB() {
		return adjustB;
	}

	public JButton getModeB() {
		return modeB;
	}

	public JButton getForwardB() {
		return forwardB;
	}

	public JButton getReverseB() {
		return reverseB;
	}


	//GUI Update Methods
	private static void switchPanel(watchGUI mainGUI, JPanel newPanel){
		mainGUI.getFramePane().remove(mainGUI.getWatchBodyPane());
		mainGUI.setWatchBodyPane(newPanel);
		mainGUI.getFramePane().add(mainGUI.getWatchBodyPane());
		mainGUI.revalidate();
		mainGUI.repaint();
	}

	private void changeSecondImg(int index, int digitIndex){
		switch (digitIndex){
			case 0:
				getTimeKeepingPane().getSecondSegs()[index].setIcon(getTimeKeepingPane().getNumImgs()[0]);
				break;
			case 1:
				getTimeKeepingPane().getSecondSegs()[index].setIcon(getTimeKeepingPane().getNumImgs()[1]);
				break;
			case 2:
				getTimeKeepingPane().getSecondSegs()[index].setIcon(getTimeKeepingPane().getNumImgs()[2]);
				break;
			case 3:
				getTimeKeepingPane().getSecondSegs()[index].setIcon(getTimeKeepingPane().getNumImgs()[3]);
				break;
			case 4:
				getTimeKeepingPane().getSecondSegs()[index].setIcon(getTimeKeepingPane().getNumImgs()[4]);
				break;
			case 5:
				getTimeKeepingPane().getSecondSegs()[index].setIcon(getTimeKeepingPane().getNumImgs()[5]);
				break;
			case 6:
				getTimeKeepingPane().getSecondSegs()[index].setIcon(getTimeKeepingPane().getNumImgs()[6]);
				break;
			case 7:
				getTimeKeepingPane().getSecondSegs()[index].setIcon(getTimeKeepingPane().getNumImgs()[7]);
				break;
			case 8:
				getTimeKeepingPane().getSecondSegs()[index].setIcon(getTimeKeepingPane().getNumImgs()[8]);
				break;
			case 9:
				getTimeKeepingPane().getSecondSegs()[index].setIcon(getTimeKeepingPane().getNumImgs()[9]);
				break;
		}
	}

	private void changefirstImg(int index, int digitIndex){
		switch (digitIndex){
			case 0:
				getTimeKeepingPane().getFirstSegs()[index].setIcon(getTimeKeepingPane().getNumBigImgs()[0]);
				break;
			case 1:
				getTimeKeepingPane().getFirstSegs()[index].setIcon(getTimeKeepingPane().getNumBigImgs()[1]);
				break;
			case 2:
				getTimeKeepingPane().getFirstSegs()[index].setIcon(getTimeKeepingPane().getNumBigImgs()[2]);
				break;
			case 3:
				getTimeKeepingPane().getFirstSegs()[index].setIcon(getTimeKeepingPane().getNumBigImgs()[3]);
				break;
			case 4:
				getTimeKeepingPane().getFirstSegs()[index].setIcon(getTimeKeepingPane().getNumBigImgs()[4]);
				break;
			case 5:
				getTimeKeepingPane().getFirstSegs()[index].setIcon(getTimeKeepingPane().getNumBigImgs()[5]);
				break;
			case 6:
				getTimeKeepingPane().getFirstSegs()[index].setIcon(getTimeKeepingPane().getNumBigImgs()[6]);
				break;
			case 7:
				getTimeKeepingPane().getFirstSegs()[index].setIcon(getTimeKeepingPane().getNumBigImgs()[7]);
				break;
			case 8:
				getTimeKeepingPane().getFirstSegs()[index].setIcon(getTimeKeepingPane().getNumBigImgs()[8]);
				break;
			case 9:
				getTimeKeepingPane().getFirstSegs()[index].setIcon(getTimeKeepingPane().getNumBigImgs()[9]);
				break;
		}
	}

	private void keepValueToArray(int timeValue, int valueArray[]){
		if(timeValue < 10){
			valueArray[0] = 0;
			valueArray[1] = timeValue;
		}else{
			for(int i=valueArray.length-1; i>=0; i--){
				valueArray[i] = timeValue%10;
				timeValue /= 10;
			}
		}
	}

	private void keepHourToArray(int hour, int hourArray[]){
		if(hour == 0){
			hourArray[0] = 1;
			hourArray[1] = 2;
		}else if(hour >=1 && hour <=12){ //AM
			if(hour < 10){
				hourArray[0] = 0;
				hourArray[1] = hour;
			}else {
				for (int i = hourArray.length - 1; i >= 0; i--) {
					hourArray[i] = hour % 10;
					hour /= 10;
				}
			}
		}
		else if(hour >= 13 && hour <=23){ //PM
			hour -= 12;
			if(hour < 10){
				hourArray[0] = 0;
				hourArray[1] = hour;
			}else{
				for(int i=hourArray.length-1; i>=0; i--){
					hourArray[i] = hour%10;
					hour /= 10;
				}
			}
		}
	}

	@Override
	public void run() { // update GUI
		while(true) {

			LocalDateTime temp = getTimeKeepingPane().getCurrentTime();

			int year = temp.getYear();
			int month = temp.getMonthValue();
			int day = temp.getDayOfMonth();
			int hour = temp.getHour();
			int minute = temp.getMinute();
			int second = temp.getSecond();

			int yearNum[] = new int[4];
			int monthNum[] = new int[2];
			int dayNum[] = new int[2];
			int hourNum[]  = new int[2];
			int minuteNum[] = new int[2];
			int secondNum[] = new int[2];

			for(int i=yearNum.length - 1; i>=0; i--){
				yearNum[i] = year%10;
				year /= 10;
			}

			if(hour < 12){
				getTimeKeepingPane().getMeridiemLabel().setText("AM");
			}else{
				getTimeKeepingPane().getMeridiemLabel().setText("PM");
			}
			getTimeKeepingPane().getDowLabel().setText(temp.getDayOfWeek().toString().substring(0,3)); //display day of week only 3 words


			keepValueToArray(month, monthNum);
			keepValueToArray(day, dayNum);
			keepHourToArray(hour, hourNum);
			keepValueToArray(minute, minuteNum);
			keepValueToArray(second, secondNum);


			//set second segment Img (year, month, day)
			for(int i=0; i<10; i++) {
				if(i>=0 && i<4)changeSecondImg(i, yearNum[i]);
				else if(i>=5 && i<7) changeSecondImg(i, monthNum[i-5]);
				else if(i>=8 && i<10) changeSecondImg(i, dayNum[i-8]);
			}

			//set first segment Img (hour, minute, sec)
			for(int i=0; i<8; i++){
				if(i>=0 && i<2) changefirstImg(i, hourNum[i]);
				if(i>=3 && i<5) changefirstImg(i, minuteNum[i-3]);
				if(i>=6 && i<8) changefirstImg(i, secondNum[i-6]);
			}

			revalidate();
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
