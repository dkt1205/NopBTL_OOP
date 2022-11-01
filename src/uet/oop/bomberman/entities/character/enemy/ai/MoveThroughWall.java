package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.entities.character.BomberMain;
import uet.oop.bomberman.entities.character.enemy.E_Enemy;
import uet.oop.bomberman.entities.character.enemy.Through_The_Wall;
import uet.oop.bomberman.entities.tile.Wall_Item;
import uet.oop.bomberman.entities.tile.destroyable.ThreadBrick;

import java.util.Random;

public class MoveThroughWall extends Move {
    private BomberMain _bomber;
    private Through_The_Wall _e;

    public MoveThroughWall(BomberMain bomber, Through_The_Wall e) {
        _bomber = bomber;
        _e = e;

    }
    //2: xuống,0: lên,1: phải,3: trái

    public int calculateDirection() {
        if (_bomber.getXTile() < _e.getXTile()) return 3;
        if (_bomber.getXTile() > _e.getXTile()) return 1;
        if (_bomber.getYTile() < _e.getYTile()) return 0;
        if (_bomber.getYTile() > _e.getYTile()) return 2;
        if (_bomber.getXTile() == _e.getXTile() &&_bomber.getYTile() == _e.getYTile()){
            _bomber.kill();
        }
        return 0;
    }
}
