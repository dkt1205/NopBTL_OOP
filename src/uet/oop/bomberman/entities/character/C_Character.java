package uet.oop.bomberman.entities.character;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.InitGame;
import uet.oop.bomberman.entities.ThreadAnimated;
import uet.oop.bomberman.graphics.Render_Screen;
	public abstract class C_Character extends ThreadAnimated {
	
	protected Board_Game _board;
	protected int _direction = -1;
	protected boolean _alive = true;
	protected boolean _moving = false;
	public int _timeAfter = 40;
	
	public C_Character(int x, int y, Board_Game board) {
		_x = x;
		_y = y;
		_board = board;
	}

	public abstract void update();

	public abstract void render(Render_Screen s);
	protected abstract void calculateMove();
	protected abstract void move(double xx, double yy);
	public abstract void kill();
	protected abstract void afterKill();
	protected abstract boolean canMove(double xx, double yy);
	protected double getXMessage() {
		return (_x * InitGame.SCALE) + (_sprite.SIZE / 2 * InitGame.SCALE);
	}
	protected double getYMessage() {
		return (_y* InitGame.SCALE) - (_sprite.SIZE / 2 * InitGame.SCALE);
	}
	
}
