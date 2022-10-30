package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.entities.All_Entity;
import uet.oop.bomberman.graphics.Render_Screen;
import uet.oop.bomberman.graphics.Sprites;

public class FlameLoad extends All_Entity {

	protected boolean _last;

	public FlameLoad(int x, int y, int direction, boolean last) {
		_x = x;
		_y = y;
		_last = last;

		switch (direction) {
			case 0:
				if(!last) {
					_sprite = Sprites.explosion_vertical2;
				} else {
					_sprite = Sprites.explosion_vertical_top_last2;
				}
			break;
			case 1:
				if(!last) {
					_sprite = Sprites.explosion_horizontal2;
				} else {
					_sprite = Sprites.explosion_horizontal_right_last2;
				}
				break;
			case 2:
				if(!last) {
					_sprite = Sprites.explosion_vertical2;
				} else {
					_sprite = Sprites.explosion_vertical_down_last2;
				}
				break;
			case 3: 
				if(!last) {
					_sprite = Sprites.explosion_horizontal2;
				} else {
					_sprite = Sprites.explosion_horizontal_left_last2;
				}
				break;
		}
	}

	public void render(Render_Screen screen) {
		int xt = (int)_x << 4;
		int yt = (int)_y << 4;
		
		screen.renderEntity(xt, yt , this);
	}

	public void update() {}

	public boolean collide(All_Entity e) {
		// xử lý khi FlameSegment va chạm với Character
		return true;
	}


}