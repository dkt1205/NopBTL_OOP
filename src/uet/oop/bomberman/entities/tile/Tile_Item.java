package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.entities.All_Entity;
import uet.oop.bomberman.graphics.Render_Screen;
import uet.oop.bomberman.graphics.Sprites;
import uet.oop.bomberman.level.Position;

public abstract class Tile_Item extends All_Entity {
	
	public Tile_Item(int x, int y, Sprites sprite) {
		_x = x;
		_y = y;
		_sprite = sprite;
	}

	public boolean collide(All_Entity e) {
		return false;
	}

	public void render(Render_Screen screen) {
		screen.renderEntity( Position.tileToPixel(_x), Position.tileToPixel(_y), this);
	}
	
	@Override
	public void update() {}
}
