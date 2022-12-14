package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.InitGame;
import uet.oop.bomberman.entities.All_Entity;
import uet.oop.bomberman.entities.character.BomberMain;
import uet.oop.bomberman.graphics.Sprites;
import uet.oop.bomberman.output.Audio;

public class Speed_Item extends I_Item {

	public Speed_Item(int x, int y, Sprites sprite) {
		super(x, y, sprite);
	}

	public boolean collide(All_Entity e) {
		if (e instanceof BomberMain) {
			Audio.playEat();
			InitGame.addBomberSpeed(0.6);
			this.remove();
		}
		return true;
	}
}
