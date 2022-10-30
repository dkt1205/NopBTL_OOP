package uet.oop.bomberman.entities.tile.destroyable;

import uet.oop.bomberman.entities.All_Entity;
import uet.oop.bomberman.entities.bomb.ThreadFlame;
import uet.oop.bomberman.entities.tile.Tile_Item;
import uet.oop.bomberman.graphics.Sprites;

public class Destroyable extends Tile_Item {
	private final int MAX_ANIMATE = 7500;
	private int _animate = 0;
	protected boolean _destroyed = false;
	protected int _timeToDisapear = 20;
	protected Sprites _belowSprite = Sprites.grass;
	
	public Destroyable(int x, int y, Sprites sprite) {
		super(x, y, sprite);
	}

	public void update() {
		if(_destroyed) {
			if(_animate < MAX_ANIMATE)
				_animate++;
			else
				_animate = 0;

			if(_timeToDisapear > 0) 
				_timeToDisapear--;
			else
				remove();
		}
	}

	public void destroy() {
		_destroyed = true;
	}

	public boolean collide(All_Entity e) {
		// @todo: xử lý khi va chạm với Flame
		if (e instanceof ThreadFlame) destroy();
		return false;
	}
	
	public void addBelowSprite(Sprites sprite) {
		_belowSprite = sprite;
	}
	
	protected Sprites movingSprite(Sprites normal, Sprites x1, Sprites x2) {
		int calc = _animate % 30;
		
		if(calc < 10) {
			return normal;
		}
			
		if(calc < 20) {
			return x1;
		}
		return x2;
	}
	
}
