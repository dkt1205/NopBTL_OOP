package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.InitGame;
import uet.oop.bomberman.entities.ThreadAnimated;
import uet.oop.bomberman.entities.All_Entity;
import uet.oop.bomberman.entities.character.C_Character;
import uet.oop.bomberman.graphics.Render_Screen;
import uet.oop.bomberman.graphics.Sprites;
import uet.oop.bomberman.output.Audio;
public class ThreadBomb extends ThreadAnimated {
    protected double _timeToExplode = 90;
    public int _timeAfter = 20;

    protected Board_Game _board;
    protected ThreadFlame[] _flames;
    protected boolean _exploded = false;

    public ThreadBomb(int x, int y, Board_Game board) {
        _x = x;
        _y = y;
        _board = board;
        _sprite = Sprites.bomb;
        _flames = new ThreadFlame[0];
    }

    public void update() {
        if (_timeToExplode > 0)
            _timeToExplode--;
        else {
            if (!_exploded) {
                explode();
            } else
                updateFlames();

            if (_timeAfter > 0)
                _timeAfter--;
            else
                remove();
        }
        animate();
    }

    public void render(Render_Screen screen) {
        if (_exploded) {
            _sprite = Sprites.movingSprite(Sprites.bomb_exploded, Sprites.bomb_exploded1, Sprites.bomb_exploded2, _animate, 60);
            renderFlames(screen);
        } else
            _sprite = Sprites.movingSprite(Sprites.bomb, Sprites.bomb_1, Sprites.bomb_2, _animate, 60);
        int xt = (int) _x << 4;
        int yt = (int) _y << 4;
        screen.renderEntity(xt, yt, this);
    }

    public void renderFlames(Render_Screen screen) {
        for (int i = 0; i < _flames.length; i++) {
            _flames[i].render(screen);
        }
    }
    public void updateFlames() {
        for (int i = 0; i < _flames.length; i++) {
            _flames[i].update();
        }
    }
    protected void explode() {
        _exploded = true;
        C_Character character = _board.getCharacterAtExcluding((int) _x, (int) _y, null);
        if (character != null) character.kill();
        int radius = InitGame.getBombRadius();
        ThreadFlame flame0 = new ThreadFlame((int) _x, (int) _y, 0, radius, _board);
        ThreadFlame flame1 = new ThreadFlame((int) _x, (int) _y, 1, radius, _board);
        ThreadFlame flame2 = new ThreadFlame((int) _x, (int) _y, 2, radius, _board);
        ThreadFlame flame3 = new ThreadFlame((int) _x, (int) _y, 3, radius, _board);
        _flames = new ThreadFlame[]{flame0, flame1, flame2, flame3};
        Audio.playExplosion();
    }

    public FlameLoad flameAt(int x, int y) {
        if (!_exploded) return null;
        for (int i = 0; i < _flames.length; i++) {
            if (_flames[i] == null) return null;
            FlameLoad e = _flames[i].flameSegmentAt(x, y);
            if (e != null) return e;
        }
        return null;
    }

    public boolean collide(All_Entity e) {
        if (e instanceof ThreadFlame && !_exploded) explode();
        return false;
    }
}
