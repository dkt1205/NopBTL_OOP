package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.entities.character.BomberMain;
import uet.oop.bomberman.entities.character.enemy.E_Enemy;

public class AIDodge extends Move {
    private E_Enemy _e;
    private Board_Game _board;

    public AIDodge(BomberMain bomber, E_Enemy e, Board_Game b) {
        _e = e;
        _board = b;
    }

    // hướng lên/phải/xuống/trái tương ứng với các giá trị 0/1/2/3
    public int calculateDirection() {
        int directionBomb = directionHasBomb(2);
        if (directionBomb == 0) return random.nextInt(3) + 1;
        if (directionBomb == 1) return (random.nextInt(3) + 2) % 4;
        if (directionBomb == 2) return (random.nextInt(3) + 3) % 4;
        if (directionBomb == 3) return random.nextInt(3);
        if (directionBomb == 11) {
            int res = random.nextInt(2);
            if (res == 0) return 1;
            return 2;
        }
        if (directionBomb == 12) {
            int res = random.nextInt(2);
            if (res == 0) return 3;
            return 2;
        }
        if (directionBomb == 13) {
            int res = random.nextInt(2);
            if (res == 1) return 1;
            return 0;
        }
        if (directionBomb == 14) {
            int res = random.nextInt(2);
            if (res == 1) return 0;
            return 3;
        }
        int res = random.nextInt(100);
        if (res < 40) return res % 4;
        return -1;
    }

    public int calculateDirection1() {
        int directionBomb = directionHasBomb(1);
        if (directionBomb == 0) return random.nextInt(3) + 1;
        if (directionBomb == 1) return (random.nextInt(3) + 2) % 4;
        if (directionBomb == 2) return (random.nextInt(3) + 3) % 4;
        if (directionBomb == 3) return random.nextInt(3);
        if (directionBomb == 11) {
            int res = random.nextInt(2);
            if (res == 0) return 1;
            return 2;
        }
        if (directionBomb == 12) {
            int res = random.nextInt(2);
            if (res == 0) return 3;
            return 2;
        }
        if (directionBomb == 13) {
            int res = random.nextInt(2);
            if (res == 1) return 1;
            return 0;
        }
        if (directionBomb == 14) {
            int res = random.nextInt(2);
            if (res == 1) return 0;
            return 3;
        }
        return -1;
    }

    private int directionHasBomb(int distance) {
        int x = _e.getXTile();
        int y = _e.getYTile();
        if (_board.getBombAt(x - 1, y - 1) != null) return 11;
        if (_board.getBombAt(x + 1, y - 1) != null) return 12;
        if (_board.getBombAt(x - 1, y + 1) != null) return 13;
        if (_board.getBombAt(x + 1, y + 1) != null) return 14;
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
