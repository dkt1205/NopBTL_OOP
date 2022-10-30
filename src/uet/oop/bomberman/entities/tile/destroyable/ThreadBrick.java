package uet.oop.bomberman.entities.tile.destroyable;

import uet.oop.bomberman.graphics.Render_Screen;
import uet.oop.bomberman.graphics.Sprites;
import uet.oop.bomberman.level.Position;
public class ThreadBrick extends Destroyable {
	
	public ThreadBrick(int x, int y, Sprites sprite) {
		super(x, y, sprite);
	}

	public void update() {
		super.update();
	}

	public void render(Render_Screen screen) {
		int x = Position.tileToPixel(_x);
		int y = Position.tileToPixel(_y);
		if(_destroyed) {
			_sprite = movingSprite(Sprites.brick_exploded, Sprites.brick_exploded1, Sprites.brick_exploded2);
			screen.renderEntityWithBelowSprite(x, y, this, _belowSprite);
		}
		else{
			screen.renderEntity( x, y, this);
		}
	}
	
}
