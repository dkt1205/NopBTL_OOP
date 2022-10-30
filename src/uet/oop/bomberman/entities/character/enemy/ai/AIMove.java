package uet.oop.bomberman.entities.character.enemy.ai;

import java.util.Random;

public abstract class AIMove {
	protected Random random = new Random();

// hướng đi xuống/phải/trái/lên tương ứng với các giá trị 0/1/2/3
	public abstract int calculateDirection();
}
