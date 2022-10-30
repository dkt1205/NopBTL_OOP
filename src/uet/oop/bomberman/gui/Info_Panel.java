package uet.oop.bomberman.gui;

import uet.oop.bomberman.GameLoop;

import javax.swing.*;
import java.awt.*;


public class Info_Panel extends JPanel {
	
	private JLabel timeLabel;
	private JLabel pointsLabel;

	public Info_Panel(GameLoop game) {
		setLayout(new GridLayout());
		timeLabel = new JLabel("Time left: " + game.getBoard().getTime());
		timeLabel.setForeground(Color.white);
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		pointsLabel = new JLabel("Your Score: " + game.getBoard().getPoints());
		pointsLabel.setForeground(Color.white);
		pointsLabel.setHorizontalAlignment(JLabel.CENTER);
		add(timeLabel);
		add(pointsLabel);
		setBackground(Color.black);
		setPreferredSize(new Dimension(0, 40));
	}
	
	public void setTime(int time) {
		timeLabel.setText("Time left: " + time);
	}

	public void setPoints(int points) {
		pointsLabel.setText("Your Score: " + points);
	}
	
}
