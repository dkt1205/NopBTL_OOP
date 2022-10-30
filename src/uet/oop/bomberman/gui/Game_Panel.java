package uet.oop.bomberman.gui;

import uet.oop.bomberman.GameLoop;

import javax.swing.*;
import java.awt.*;


public class Game_Panel extends JPanel {

	private GameLoop _game;
	
	public Game_Panel(CreateGame frame) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(GameLoop.WIDTH * GameLoop.SCALE, GameLoop.HEIGHT * GameLoop.SCALE));
		_game = new GameLoop(frame);
		add(_game);
		_game.setVisible(true);
		setVisible(true);
		setFocusable(true);
	}

	public GameLoop getGame() {
		return _game;
	}
	
}
