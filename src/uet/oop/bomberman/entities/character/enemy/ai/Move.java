package uet.oop.bomberman.entities.character.enemy.ai;

import java.util.Random;

public class Move {
	protected Random random = new Random();

	// hướng xuống/phải/trái/lên tương ứng với các giá trị 0/1/2/3
	public int calculateDirection(){
		return -1;
	}
}
