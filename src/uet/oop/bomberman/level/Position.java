package uet.oop.bomberman.level;

import uet.oop.bomberman.GameLoop;

public class Position {
	
	public static int pixelToTile(double i) {
		return (int)(i / GameLoop.TILES_SIZE);
	}
	
	public static int tileToPixel(int i) {
		return i * GameLoop.TILES_SIZE;
	}
	
	public static int tileToPixel(double i) {
		return (int)(i * GameLoop.TILES_SIZE);
	}
	
	
}
