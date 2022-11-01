package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.entities.character.BomberMain;
import uet.oop.bomberman.entities.character.enemy.E_Enemy;


import java.util.Random;

public class MoveRandom extends Move {
    private BomberMain _bomber;
    private E_Enemy _e;
    private Board_Game _board;
    public int calculateDirection() {
        Random rand = new Random();
        int n = rand.nextInt(100);
        return n%4;
    }
}
