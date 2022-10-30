package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Render_Screen;

import java.awt.*;

public class Display_Message extends All_Entity {

	protected String _message;
	protected int _duration;
	protected Color _color;
	protected int _size;
	public Display_Message(String message, double x, double y, int duration, Color color, int size) {
		_x =x;
		_y = y;
		_message = message;
		_duration = duration * 60;
		_color = color;
		_size = size;
	}

	public int getDuration() {
		return _duration;
	}

	public void setDuration(int _duration) {
		this._duration = _duration;
	}

	public String getMessage() {
		return _message;
	}

	public Color getColor() {
		return _color;
	}

	public int getSize() {
		return _size;
	}

	public void update() {
	}

	public void render(Render_Screen screen) {
	}

	public boolean collide(All_Entity e) {
		return true;
	}
}
