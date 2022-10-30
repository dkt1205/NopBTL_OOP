package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.entities.character.BomberMain;
import uet.oop.bomberman.entities.character.enemy.E_Enemy;
public class AIDodge extends AIMove {
    private E_Enemy _e;
    private Board_Game _board;
    public AIDodge(BomberMain bomber, E_Enemy e, Board_Game b) {
        _e = e;
        _board = b;
    }

    public int calculateDirection() {
        int directionBomb = directionHasBomb(2);
        if (directionBomb == 0) return random.nextInt(3) + 1;       // random: 1, 2, 3 not 0.
        if (directionBomb == 1) return (random.nextInt(3) + 2) % 4; // random: 0, 2, 3 not 1.
        if (directionBomb == 2) return (random.nextInt(3) + 3) % 4; // random: 0, 1, 3 not 2.
        if (directionBomb == 3) return random.nextInt(3);           // random: 0, 1, 2 not 3.
        return -1;
    }

    private int directionHasBomb(int distance) {
        int x = _e.getXTile();
        int y = _e.getYTile();
        for (int i = 0; i < distance; i++)
            if (_board.getBombAt(x, y - 1 - i) != null) return 0;
        for (int i = 0; i < distance; i++)
            if (_board.getBombAt(x + 1 + i, y) != null) return 1;
        for (int i = 0; i < distance; i++)
            if (_board.getBombAt(x, y + 1 + i) != null) return 2;
        for (int i = 0; i < distance; i++)
            if (_board.getBombAt(x - 1 - i, y) != null) return 3;
        return -1;
    }
}
