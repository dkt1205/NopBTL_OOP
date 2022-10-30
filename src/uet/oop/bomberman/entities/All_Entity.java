package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.I_Render;
import uet.oop.bomberman.graphics.Render_Screen;
import uet.oop.bomberman.graphics.Sprites;
import uet.oop.bomberman.level.Position;

public abstract class All_Entity implements I_Render {

	protected double _x;
	protected double _y;
	protected boolean _removed = false;
	protected Sprites _sprite;
	public abstract void update();

	public abstract void render(Render_Screen screen);
	
	public void remove() {
		_removed = true;
	}
	
	public boolean isRemoved() {
		return _removed;
	}
	
	public Sprites getSprite() {
		return _sprite;
	}

	public abstract boolean collide(All_Entity e);
	
	public double getX() {
		return _x;
	}
	
	public double getY() {
		return _y;
	}
	
	public int getXTile() {
		return Position.pixelToTile(_x + _sprite.SIZE / 2);
	}
	
	public int getYTile() {
		return Position.pixelToTile(_y - _sprite.SIZE / 2);
	}
}
