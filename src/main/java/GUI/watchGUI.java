package GUI;

import Sys.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;


public class watchGUI extends JFrame implements Runnable{

	public ModeManager modeManager;
	public Mode[] modes;
	
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 320;
	private static final int BUTTON_WIDTH = 10;
	private static final int BUTTON_HEIGHT = 45;

	private static final int MODE_TIMEKEEPING = 0;
	private static final int MODE_ALARM = 1;
	private static final int MODE_TIMER = 2;
	private static final int MODE_STOPWATCH = 3;
	private static final int MODE_CCHECK = 4;
	private static final int MODE_WTIME = 5;

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

	//ImageIcon (will put into JLabel)
	private ImageIcon seg14DeadBigImg; //grey big seg image
	private ImageIcon seg14DeadImg; //grey seg image
	private ImageIcon numBigImgs[]; //big num image 0 ~ 9
	private ImageIcon numImgs[]; //small num image 0 ~ 9
	private ImageIcon colonBigImg; //big colon image
	private ImageIcon colonImg; //small colon image
	private ImageIcon clockImg; //black clock image
	private ImageIcon clockDeadImg; //grey clock image


	public watchGUI() {

		modeManager = new ModeManager();
		modeManager.makeThread();

		//load Images from resource folder
		seg14DeadBigImg = new ImageIcon(this.getClass().getResource(ImageDir.SegDead14Big_dir));
		seg14DeadImg = new ImageIcon(this.getClass().getResource(ImageDir.SegDead14_dir));
		colonImg = new ImageIcon(this.getClass().getResource(ImageDir.colon_dir));
		colonBigImg = new ImageIcon(this.getClass().getResource(ImageDir.colonBig_dir));
		clockImg = new ImageIcon(this.getClass().getResource(ImageDir.clock_dir));
		clockDeadImg = new ImageIcon(this.getClass().getResource(ImageDir.clockDead_dir));
		numBigImgs = new ImageIcon[10]; // first segment's numbers
		for(int i=0; i<10; i++){
			numBigImgs[i] = new ImageIcon(this.getClass().getResource(ImageDir.numBigdirs[i]));
		}
		numImgs = new ImageIcon[10]; // second segment's numbers
		for(int i=0; i<10; i++){
			numImgs[i] = new ImageIcon(this.getClass().getResource(ImageDir.numdirs[i]));
		}

		//set window's name
		this.setTitle("Digital Watch");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		timeKeepingPane = new TimeKeeping_Pane();
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
				modeManager.clickButton(1, false);
			}
		});
		modeB = new JButton();
		modeB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modeManager.clickButton(0, false);
			}
		});
		forwardB = new JButton();
		forwardB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modeManager.clickButton(2, false);
			}
		});
		reverseB = new JButton();
		reverseB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modeManager.clickButton(3, false);
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

	public ImageIcon getSeg14DeadBigImg() { return seg14DeadBigImg; }

	public ImageIcon getSeg14DeadImg() { return seg14DeadImg; }

	public ImageIcon[] getNumBigImgs() { return numBigImgs; }

	public ImageIcon[] getNumImgs() { return numImgs; }

	public ImageIcon getColonBigImg() { return colonBigImg; }

	public ImageIcon getColonImg() { return colonImg; }

	public ImageIcon getClockImg() { return clockImg; }

	public ImageIcon getClockDeadImg() { return clockDeadImg; }


	//GUI Update Methods
	private static void switchPanel(watchGUI mainGUI, JPanel newPanel){
		mainGUI.getFramePane().remove(mainGUI.getWatchBodyPane());
		mainGUI.setWatchBodyPane(newPanel);
		mainGUI.getFramePane().add(mainGUI.getWatchBodyPane());
		mainGUI.revalidate();
		mainGUI.repaint();
	}

	private void changeSecondImg(int currentMode, int index, int digitIndex){
		switch(currentMode) {
			case MODE_TIMEKEEPING:
				switch (digitIndex) {
					case 0:
						getTimeKeepingPane().getSecondSegs()[index].setIcon(getNumImgs()[0]);
						break;
					case 1:
						getTimeKeepingPane().getSecondSegs()[index].setIcon(getNumImgs()[1]);
						break;
					case 2:
						getTimeKeepingPane().getSecondSegs()[index].setIcon(getNumImgs()[2]);
						break;
					case 3:
						getTimeKeepingPane().getSecondSegs()[index].setIcon(getNumImgs()[3]);
						break;
					case 4:
						getTimeKeepingPane().getSecondSegs()[index].setIcon(getNumImgs()[4]);
						break;
					case 5:
						getTimeKeepingPane().getSecondSegs()[index].setIcon(getNumImgs()[5]);
						break;
					case 6:
						getTimeKeepingPane().getSecondSegs()[index].setIcon(getNumImgs()[6]);
						break;
					case 7:
						getTimeKeepingPane().getSecondSegs()[index].setIcon(getNumImgs()[7]);
						break;
					case 8:
						getTimeKeepingPane().getSecondSegs()[index].setIcon(getNumImgs()[8]);
						break;
					case 9:
						getTimeKeepingPane().getSecondSegs()[index].setIcon(getNumImgs()[9]);
						break;
				}
				break;
			case MODE_ALARM:
				switch (digitIndex) {
					case 0:
						getAlarmPane().getSecondSegs()[index].setIcon(getNumImgs()[0]);
						break;
					case 1:
						getAlarmPane().getSecondSegs()[index].setIcon(getNumImgs()[1]);
						break;
					case 2:
						getAlarmPane().getSecondSegs()[index].setIcon(getNumImgs()[2]);
						break;
					case 3:
						getAlarmPane().getSecondSegs()[index].setIcon(getNumImgs()[3]);
						break;
					case 4:
						getAlarmPane().getSecondSegs()[index].setIcon(getNumImgs()[4]);
						break;
					case 5:
						getAlarmPane().getSecondSegs()[index].setIcon(getNumImgs()[5]);
						break;
					case 6:
						getAlarmPane().getSecondSegs()[index].setIcon(getNumImgs()[6]);
						break;
					case 7:
						getAlarmPane().getSecondSegs()[index].setIcon(getNumImgs()[7]);
						break;
					case 8:
						getAlarmPane().getSecondSegs()[index].setIcon(getNumImgs()[8]);
						break;
					case 9:
						getAlarmPane().getSecondSegs()[index].setIcon(getNumImgs()[9]);
						break;
				}
				break;
			case MODE_TIMER:
				switch (digitIndex) {
					case 0:
						getTimerPane().getSecondSegs()[index].setIcon(getNumImgs()[0]);
						break;
					case 1:
						getTimerPane().getSecondSegs()[index].setIcon(getNumImgs()[1]);
						break;
					case 2:
						getTimerPane().getSecondSegs()[index].setIcon(getNumImgs()[2]);
						break;
					case 3:
						getTimerPane().getSecondSegs()[index].setIcon(getNumImgs()[3]);
						break;
					case 4:
						getTimerPane().getSecondSegs()[index].setIcon(getNumImgs()[4]);
						break;
					case 5:
						getTimerPane().getSecondSegs()[index].setIcon(getNumImgs()[5]);
						break;
					case 6:
						getTimerPane().getSecondSegs()[index].setIcon(getNumImgs()[6]);
						break;
					case 7:
						getTimerPane().getSecondSegs()[index].setIcon(getNumImgs()[7]);
						break;
					case 8:
						getTimerPane().getSecondSegs()[index].setIcon(getNumImgs()[8]);
						break;
					case 9:
						getTimerPane().getSecondSegs()[index].setIcon(getNumImgs()[9]);
						break;
				}
				break;
			case MODE_STOPWATCH:
				switch (digitIndex) {
					case 0:
						getSwPane().getSecondSegs()[index].setIcon(getNumImgs()[0]);
						break;
					case 1:
						getSwPane().getSecondSegs()[index].setIcon(getNumImgs()[1]);
						break;
					case 2:
						getSwPane().getSecondSegs()[index].setIcon(getNumImgs()[2]);
						break;
					case 3:
						getSwPane().getSecondSegs()[index].setIcon(getNumImgs()[3]);
						break;
					case 4:
						getSwPane().getSecondSegs()[index].setIcon(getNumImgs()[4]);
						break;
					case 5:
						getSwPane().getSecondSegs()[index].setIcon(getNumImgs()[5]);
						break;
					case 6:
						getSwPane().getSecondSegs()[index].setIcon(getNumImgs()[6]);
						break;
					case 7:
						getSwPane().getSecondSegs()[index].setIcon(getNumImgs()[7]);
						break;
					case 8:
						getSwPane().getSecondSegs()[index].setIcon(getNumImgs()[8]);
						break;
					case 9:
						getSwPane().getSecondSegs()[index].setIcon(getNumImgs()[9]);
						break;
				}
				break;
			case MODE_CCHECK:
				switch (digitIndex) {
					case 0:
						getCcPane().getSecondSegs()[index].setIcon(getNumImgs()[0]);
						break;
					case 1:
						getCcPane().getSecondSegs()[index].setIcon(getNumImgs()[1]);
						break;
					case 2:
						getCcPane().getSecondSegs()[index].setIcon(getNumImgs()[2]);
						break;
					case 3:
						getCcPane().getSecondSegs()[index].setIcon(getNumImgs()[3]);
						break;
					case 4:
						getCcPane().getSecondSegs()[index].setIcon(getNumImgs()[4]);
						break;
					case 5:
						getCcPane().getSecondSegs()[index].setIcon(getNumImgs()[5]);
						break;
					case 6:
						getCcPane().getSecondSegs()[index].setIcon(getNumImgs()[6]);
						break;
					case 7:
						getCcPane().getSecondSegs()[index].setIcon(getNumImgs()[7]);
						break;
					case 8:
						getCcPane().getSecondSegs()[index].setIcon(getNumImgs()[8]);
						break;
					case 9:
						getCcPane().getSecondSegs()[index].setIcon(getNumImgs()[9]);
						break;
				}
				break;
			case MODE_WTIME:
				switch (digitIndex) {
					case 0:
						getWtPane().getSecondSegs()[index].setIcon(getNumImgs()[0]);
						break;
					case 1:
						getWtPane().getSecondSegs()[index].setIcon(getNumImgs()[1]);
						break;
					case 2:
						getWtPane().getSecondSegs()[index].setIcon(getNumImgs()[2]);
						break;
					case 3:
						getWtPane().getSecondSegs()[index].setIcon(getNumImgs()[3]);
						break;
					case 4:
						getWtPane().getSecondSegs()[index].setIcon(getNumImgs()[4]);
						break;
					case 5:
						getWtPane().getSecondSegs()[index].setIcon(getNumImgs()[5]);
						break;
					case 6:
						getWtPane().getSecondSegs()[index].setIcon(getNumImgs()[6]);
						break;
					case 7:
						getWtPane().getSecondSegs()[index].setIcon(getNumImgs()[7]);
						break;
					case 8:
						getWtPane().getSecondSegs()[index].setIcon(getNumImgs()[8]);
						break;
					case 9:
						getWtPane().getSecondSegs()[index].setIcon(getNumImgs()[9]);
						break;
				}
				break;
		}
	}

	private void changefirstImg(int currentMode, int index, int digitIndex){
		switch(currentMode) {
			case MODE_TIMEKEEPING:
				switch (digitIndex) {
					case 0:
						getTimeKeepingPane().getFirstSegs()[index].setIcon(getNumBigImgs()[0]);
						break;
					case 1:
						getTimeKeepingPane().getFirstSegs()[index].setIcon(getNumBigImgs()[1]);
						break;
					case 2:
						getTimeKeepingPane().getFirstSegs()[index].setIcon(getNumBigImgs()[2]);
						break;
					case 3:
						getTimeKeepingPane().getFirstSegs()[index].setIcon(getNumBigImgs()[3]);
						break;
					case 4:
						getTimeKeepingPane().getFirstSegs()[index].setIcon(getNumBigImgs()[4]);
						break;
					case 5:
						getTimeKeepingPane().getFirstSegs()[index].setIcon(getNumBigImgs()[5]);
						break;
					case 6:
						getTimeKeepingPane().getFirstSegs()[index].setIcon(getNumBigImgs()[6]);
						break;
					case 7:
						getTimeKeepingPane().getFirstSegs()[index].setIcon(getNumBigImgs()[7]);
						break;
					case 8:
						getTimeKeepingPane().getFirstSegs()[index].setIcon(getNumBigImgs()[8]);
						break;
					case 9:
						getTimeKeepingPane().getFirstSegs()[index].setIcon(getNumBigImgs()[9]);
						break;
				}
				break;
			case MODE_ALARM:
				switch (digitIndex) {
					case 0:
						getAlarmPane().getFirstSegs()[index].setIcon(getNumBigImgs()[0]);
						break;
					case 1:
						getAlarmPane().getFirstSegs()[index].setIcon(getNumBigImgs()[1]);
						break;
					case 2:
						getAlarmPane().getFirstSegs()[index].setIcon(getNumBigImgs()[2]);
						break;
					case 3:
						getAlarmPane().getFirstSegs()[index].setIcon(getNumBigImgs()[3]);
						break;
					case 4:
						getAlarmPane().getFirstSegs()[index].setIcon(getNumBigImgs()[4]);
						break;
					case 5:
						getAlarmPane().getFirstSegs()[index].setIcon(getNumBigImgs()[5]);
						break;
					case 6:
						getAlarmPane().getFirstSegs()[index].setIcon(getNumBigImgs()[6]);
						break;
					case 7:
						getAlarmPane().getFirstSegs()[index].setIcon(getNumBigImgs()[7]);
						break;
					case 8:
						getAlarmPane().getFirstSegs()[index].setIcon(getNumBigImgs()[8]);
						break;
					case 9:
						getAlarmPane().getFirstSegs()[index].setIcon(getNumBigImgs()[9]);
						break;
				}
				break;
			case MODE_TIMER:
				switch (digitIndex) {
					case 0:
						getTimerPane().getFirstSegs()[index].setIcon(getNumBigImgs()[0]);
						break;
					case 1:
						getTimerPane().getFirstSegs()[index].setIcon(getNumBigImgs()[1]);
						break;
					case 2:
						getTimerPane().getFirstSegs()[index].setIcon(getNumBigImgs()[2]);
						break;
					case 3:
						getTimerPane().getFirstSegs()[index].setIcon(getNumBigImgs()[3]);
						break;
					case 4:
						getTimerPane().getFirstSegs()[index].setIcon(getNumBigImgs()[4]);
						break;
					case 5:
						getTimerPane().getFirstSegs()[index].setIcon(getNumBigImgs()[5]);
						break;
					case 6:
						getTimerPane().getFirstSegs()[index].setIcon(getNumBigImgs()[6]);
						break;
					case 7:
						getTimerPane().getFirstSegs()[index].setIcon(getNumBigImgs()[7]);
						break;
					case 8:
						getTimerPane().getFirstSegs()[index].setIcon(getNumBigImgs()[8]);
						break;
					case 9:
						getTimerPane().getFirstSegs()[index].setIcon(getNumBigImgs()[9]);
						break;
				}
				break;
			case MODE_STOPWATCH:
				switch (digitIndex) {
					case 0:
						getSwPane().getFirstSegs()[index].setIcon(getNumBigImgs()[0]);
						break;
					case 1:
						getSwPane().getFirstSegs()[index].setIcon(getNumBigImgs()[1]);
						break;
					case 2:
						getSwPane().getFirstSegs()[index].setIcon(getNumBigImgs()[2]);
						break;
					case 3:
						getSwPane().getFirstSegs()[index].setIcon(getNumBigImgs()[3]);
						break;
					case 4:
						getSwPane().getFirstSegs()[index].setIcon(getNumBigImgs()[4]);
						break;
					case 5:
						getSwPane().getFirstSegs()[index].setIcon(getNumBigImgs()[5]);
						break;
					case 6:
						getSwPane().getFirstSegs()[index].setIcon(getNumBigImgs()[6]);
						break;
					case 7:
						getSwPane().getFirstSegs()[index].setIcon(getNumBigImgs()[7]);
						break;
					case 8:
						getSwPane().getFirstSegs()[index].setIcon(getNumBigImgs()[8]);
						break;
					case 9:
						getSwPane().getFirstSegs()[index].setIcon(getNumBigImgs()[9]);
						break;
				}
				break;
			case MODE_CCHECK:
				switch (digitIndex) {
					case 0:
						getCcPane().getFirstSegs()[index].setIcon(getNumBigImgs()[0]);
						break;
					case 1:
						getCcPane().getFirstSegs()[index].setIcon(getNumBigImgs()[1]);
						break;
					case 2:
						getCcPane().getFirstSegs()[index].setIcon(getNumBigImgs()[2]);
						break;
					case 3:
						getCcPane().getFirstSegs()[index].setIcon(getNumBigImgs()[3]);
						break;
					case 4:
						getCcPane().getFirstSegs()[index].setIcon(getNumBigImgs()[4]);
						break;
					case 5:
						getCcPane().getFirstSegs()[index].setIcon(getNumBigImgs()[5]);
						break;
					case 6:
						getCcPane().getFirstSegs()[index].setIcon(getNumBigImgs()[6]);
						break;
					case 7:
						getCcPane().getFirstSegs()[index].setIcon(getNumBigImgs()[7]);
						break;
					case 8:
						getCcPane().getFirstSegs()[index].setIcon(getNumBigImgs()[8]);
						break;
					case 9:
						getCcPane().getFirstSegs()[index].setIcon(getNumBigImgs()[9]);
						break;
				}
				break;
			case MODE_WTIME:
				switch (digitIndex) {
					case 0:
						getWtPane().getFirstSegs()[index].setIcon(getNumBigImgs()[0]);
						break;
					case 1:
						getWtPane().getFirstSegs()[index].setIcon(getNumBigImgs()[1]);
						break;
					case 2:
						getWtPane().getFirstSegs()[index].setIcon(getNumBigImgs()[2]);
						break;
					case 3:
						getWtPane().getFirstSegs()[index].setIcon(getNumBigImgs()[3]);
						break;
					case 4:
						getWtPane().getFirstSegs()[index].setIcon(getNumBigImgs()[4]);
						break;
					case 5:
						getWtPane().getFirstSegs()[index].setIcon(getNumBigImgs()[5]);
						break;
					case 6:
						getWtPane().getFirstSegs()[index].setIcon(getNumBigImgs()[6]);
						break;
					case 7:
						getWtPane().getFirstSegs()[index].setIcon(getNumBigImgs()[7]);
						break;
					case 8:
						getWtPane().getFirstSegs()[index].setIcon(getNumBigImgs()[8]);
						break;
					case 9:
						getWtPane().getFirstSegs()[index].setIcon(getNumBigImgs()[9]);
						break;
				}
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

			switch (modeManager.getCurrentMode()) {
				case MODE_TIMEKEEPING://if current Mode is Time Keeping
					LocalDateTime temp = ((Time) (modeManager.getmodes()[0])).getCurrentTime();

					int year = temp.getYear();
					int month = temp.getMonthValue();
					int day = temp.getDayOfMonth();
					int hour = temp.getHour();
					int minute = temp.getMinute();
					int second = temp.getSecond();

					int yearNum[] = new int[4];
					int monthNum[] = new int[2];
					int dayNum[] = new int[2];
					int hourNum[] = new int[2];
					int minuteNum[] = new int[2];
					int secondNum[] = new int[2];

					for (int i = yearNum.length - 1; i >= 0; i--) {
						yearNum[i] = year % 10;
						year /= 10;
					}

					if (hour < 12) {
						getTimeKeepingPane().getMeridiemLabel().setText("AM");
					} else {
						getTimeKeepingPane().getMeridiemLabel().setText("PM");
					}
					getTimeKeepingPane().getDowLabel().setText(temp.getDayOfWeek().toString().substring(0, 3)); //display day of week only 3 words

					//split time value
					keepValueToArray(month, monthNum);
					keepValueToArray(day, dayNum);
					keepHourToArray(hour, hourNum);
					keepValueToArray(minute, minuteNum);
					keepValueToArray(second, secondNum);

					//set second segment Img (year, month, day)
					for (int i=0; i<10; i++) {
						if (i>=0 && i<4) changeSecondImg(MODE_TIMEKEEPING, i, yearNum[i]);
						else if (i>=5 && i<7) changeSecondImg(MODE_TIMEKEEPING, i, monthNum[i-5]);
						else if (i>=8 && i<10) changeSecondImg(MODE_TIMEKEEPING, i, dayNum[i-8]);
						else if(i==4 || i==7) getTimeKeepingPane().getSecondSegs()[i].setIcon(getColonImg());
					}

					//set first segment Img (hour, minute, sec)
					for (int i=0; i<8; i++) {
						if (i>=0 && i<2) changefirstImg(MODE_TIMEKEEPING, i, hourNum[i]);
						else if (i>=3 && i<5) changefirstImg(MODE_TIMEKEEPING, i, minuteNum[i-3]);
						else if (i>=6 && i<8) changefirstImg(MODE_TIMEKEEPING, i, secondNum[i-6]);
						else if(i==2 || i==5) getTimeKeepingPane().getFirstSegs()[i].setIcon(getColonBigImg());
					}

					//set default clock icon
						getTimeKeepingPane().getClockLabel().setIcon(getClockDeadImg());
					if(!(getWatchBodyPane().equals(timeKeepingPane))) switchPanel(this, timeKeepingPane);
					break;

				case MODE_ALARM: //if current Mode is Alarm
					LocalDateTime alarmTime = ((Alarm)(modeManager.getmodes()[1])).getCurrentAlarmTimer();

					int alarmHour = alarmTime.getHour();
					int alarmMin = alarmTime.getMinute();
					

					getAlarmPane().getClockLabel().setIcon(getClockDeadImg());
					if(!(getWatchBodyPane().equals(alarmPane))) switchPanel(this, alarmPane);
					break;

				case MODE_TIMER: //if current Mode is Timer
					LocalDateTime timerTime = ((Sys.Timer)(modeManager.getmodes()[2])).getTimerTime();

					int timerHour = timerTime.getHour();
					int timerMin = timerTime.getMinute();
					int timerSec = timerTime.getSecond();

					int thNum[] = new int[2];
					int tmNum[] = new int[2];
					int tsNum[] = new int[2];

					//split Time Value
					keepValueToArray(timerHour, thNum);
					keepValueToArray(timerMin, tmNum);
					keepValueToArray(timerSec, tsNum);

					//set second segment Img (year, month, day)
					for (int i = 0; i < 10; i++) {
						if (i>=0 && i<2) getTimerPane().getSecondSegs()[i].setIcon(getSeg14DeadImg());
						else if(i>=2 && i<4) changeSecondImg(MODE_TIMER, i, thNum[i-2]);
						else if(i>=5 && i<7) changeSecondImg(MODE_TIMER, i, tmNum[i-5]);
						else if(i>=8 && i<10) changeSecondImg(MODE_TIMER, i, tsNum[i-8]);
						else if(i==4 || i==7) getTimerPane().getSecondSegs()[i].setIcon(colonImg);
					}

					//set first segment Img (hour, minute, sec)
					for (int i = 0; i < 8; i++) {
						if (i>=0 && i<2) changefirstImg(MODE_TIMER, i, thNum[i]);
						else if (i>=3 && i<5) changefirstImg(MODE_TIMER, i, tmNum[i-3]);
						else if (i>=6 && i<8) changefirstImg(MODE_TIMER, i, tsNum[i-6]);
						else if(i==2 || i==5) getTimerPane().getFirstSegs()[i].setIcon(getColonBigImg());
					}

					//set default clock icon
					timerPane.getClockLabel().setIcon(getClockDeadImg());
					if(!(getWatchBodyPane().equals(timerPane))) switchPanel(this, timerPane);

					break;

				case MODE_STOPWATCH: //if current Mode is Stopwatch

					break;

				case MODE_CCHECK: //if current Mode is CalorieCheck

					break;

				case MODE_WTIME: //if current Mode is WorldTime

					break;



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
