package uet.oop.bomberman.entities.character.enemy.ai;

public class AIMoveRandom extends AIMove {
    public int calculateDirection() {
        int randomInt = (int) (Math.random() * 4);
        return randomInt;
    }

}
