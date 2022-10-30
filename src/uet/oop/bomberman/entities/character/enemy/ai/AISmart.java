package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.character.BomberMain;
import uet.oop.bomberman.entities.character.enemy.E_Enemy;

public class AISmart extends AIMove {
    private BomberMain _bomber;
    private E_Enemy _e;
    private AIMoveRandom aiLow;

    public AISmart(BomberMain bomber, E_Enemy e) {
        _bomber = bomber;
        _e = e;
        aiLow = new AIMoveRandom();
    }

    public int calculateDirection() {
        int random = (int) (Math.random() * 3);
        return calculateDirection(random);
    }

    public int calculateDirection(int random) {
        if (random == 0) return calculateColDirection();
        if (random == 1) return calculateRowDirection();
        return aiLow.calculateDirection();
    }

    protected int calculateColDirection() {
        if (_bomber.getXTile() < _e.getXTile()) return 3;
        if (_bomber.getXTile() > _e.getXTile()) return 1;
        return -1;
    }

    protected int calculateRowDirection() {
        if (_bomber.getYTile() < _e.getYTile()) return 0;
        if (_bomber.getYTile() > _e.getYTile()) return 2;
        return -1;
    }
}
