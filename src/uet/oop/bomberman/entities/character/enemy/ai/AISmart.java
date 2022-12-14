package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.entities.character.BomberMain;
import uet.oop.bomberman.entities.character.enemy.E_Enemy;
import uet.oop.bomberman.entities.tile.Wall_Item;
import uet.oop.bomberman.entities.tile.destroyable.ThreadBrick;

import java.util.Random;

public class AISmart extends Move {
    private BomberMain _bomber;
    private Board_Game _board;
    private E_Enemy _e;
    private Wall_Item _w;
    private ThreadBrick _b;

    private AIDodge aiDodge;

    public AISmart(BomberMain bomber, E_Enemy e, Board_Game b) {
        _bomber = bomber;
        _e = e;
        aiDodge = new AIDodge(bomber, e, b);
    }

    //2: xuống,0: lên,1: phải,3: trái

    public int calculateDirection() {
        Random rand = new Random();
        int n = rand.nextInt(100);
        if(n<=3) return n;
        int dodge = aiDodge.calculateDirection1();
        if (dodge!=-1) return dodge;
        if (_bomber.getXTile() < _e.getXTile()) return 3;
        if (_bomber.getXTile() > _e.getXTile()) return 1;
        if (_bomber.getYTile() < _e.getYTile()) return 0;
        if (_bomber.getYTile() > _e.getYTile()) return 2;
        return 0;
    }
}
