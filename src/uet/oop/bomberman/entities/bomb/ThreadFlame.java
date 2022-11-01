package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.entities.All_Entity;
import uet.oop.bomberman.entities.character.BomberMain;
import uet.oop.bomberman.entities.character.enemy.E_Enemy;
import uet.oop.bomberman.entities.tile.Wall_Item;
import uet.oop.bomberman.entities.tile.destroyable.ThreadBrick;
import uet.oop.bomberman.graphics.Render_Screen;

public class ThreadFlame extends All_Entity {

    protected Board_Game _board;
    protected int _direction;
    private int _radius;
    protected int xOrigin, yOrigin;
    protected FlameLoad[] _flameSegments = new FlameLoad[0];
    public ThreadFlame(int x, int y, int direction, int radius, Board_Game board) {
        xOrigin = x;
        yOrigin = y;
        _x = x;
        _y = y;
        _direction = direction;
        _radius = radius;
        _board = board;
        createFlameSegments();
    }
    private void createFlameSegments() {
        _flameSegments = new FlameLoad[calculatePermittedDistance()];
        int xa = 0;
        int ya = 0;
        if (_direction == 0) ya = -1;
        if (_direction == 1) xa = 1;
        if (_direction == 2) ya = 1;
        if (_direction == 3) xa = -1;
        for (int i = 0; i < _flameSegments.length; i++) {
            int xf = (int) (_x + xa * (i + 1));
            int yf = (int) (_y + ya * (i + 1));
            if (i == _flameSegments.length - 1) {
                _flameSegments[i] = new FlameLoad(xf, yf, _direction, true);
            } else {
                _flameSegments[i] = new FlameLoad(xf, yf, _direction, false);
            }
        }
    }
    private int calculatePermittedDistance() {
        int xa = 0;
        int ya = 0;
        if (_direction == 0) ya = -1;
        if (_direction == 1) xa = 1;
        if (_direction == 2) ya = 1;
        if (_direction == 3) xa = -1;
        for (int i = 0; i < _radius; i++) {
            int xf = (int) (_x + xa * (i + 1));
            int yf = (int) (_y + ya * (i + 1));
            if (xf == _x && yf == _y) continue;
            All_Entity entity = _board.getEntity(xf, yf,null);
            entity.collide(this);
            if (entity instanceof Wall_Item)
                return i;
        }
        return _radius;
    }

    public FlameLoad flameSegmentAt(int x, int y) {
        for (int i = 0; i < _flameSegments.length; i++) {
            if (_flameSegments[i].getX() == x && _flameSegments[i].getY() == y)
                return _flameSegments[i];
        }
        return null;
    }

    public void update() {
    }

    public void render(Render_Screen screen) {
        for (int i = 0; i < _flameSegments.length; i++) {
            _flameSegments[i].render(screen);
        }
    }

    public boolean collide(All_Entity e) {
        if (e instanceof BomberMain) ((BomberMain) e).kill();
        if (e instanceof E_Enemy) ((E_Enemy) e).kill();
        if (e instanceof ThreadBrick) ((ThreadBrick) e).destroy();
        return false;
    }
}
