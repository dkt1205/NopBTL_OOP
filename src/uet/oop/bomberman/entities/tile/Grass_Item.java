package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.entities.All_Entity;
import uet.oop.bomberman.graphics.Sprites;

public class Grass_Item extends Tile_Item {
	public Grass_Item(int x, int y, Sprites sprite) {
		super(x, y, sprite);
	}
	public boolean collide(All_Entity e) {
		return true;
	}
}
