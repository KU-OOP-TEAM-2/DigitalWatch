package GUI;
import java.awt.*;
import javax.swing.*;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;


public class watchGUI extends JFrame{
	
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 320;
	private static final int BUTTON_WIDTH = 10;
	private static final int BUTTON_HEIGHT = 45;

	private JPanel framePane;

	private JPanel watchBodyPane;

	private JLabel nameLabel;

	private JButton adjustB;
	private JButton modeB;
	private JButton forwardB;
	private JButton reverseB;


	public watchGUI(JPanel watchBodyPane) {

		//set window's name
		this.setTitle("Digital Watch");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		framePane = new JPanel();

		nameLabel = new JLabel("Digital Watch #2");

		adjustB = new JButton();
		modeB = new JButton();
		forwardB = new JButton();
		reverseB = new JButton();

		this.watchBodyPane = new JPanel();

		setWatchBodyPane(watchBodyPane);

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

		this.repaint();
		this.revalidate();

//		modeB.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				framePane.remove(watchBodyPane);
//				setWatchBodyPane(alarmPane);
//				framePane.add(watchBodyPane);
//				revalidate();
//				repaint();
//			}
//		});
	}

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

}
