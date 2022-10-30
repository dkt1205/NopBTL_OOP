package uet.oop.bomberman.entities.character;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.GameLoop;
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

	public abstract void render(Render_Screen screen);
	protected abstract void calculateMove();
	protected abstract void move(double xa, double ya);
	public abstract void kill();
	protected abstract void afterKill();
	protected abstract boolean canMove(double x, double y);
	protected double getXMessage() {
		return (_x * GameLoop.SCALE) + (_sprite.SIZE / 2 * GameLoop.SCALE);
	}
	protected double getYMessage() {
		return (_y* GameLoop.SCALE) - (_sprite.SIZE / 2 * GameLoop.SCALE);
	}
	
}
