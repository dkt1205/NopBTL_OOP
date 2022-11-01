package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.InitGame;
import uet.oop.bomberman.entities.All_Entity;
import uet.oop.bomberman.entities.Display_Message;
import uet.oop.bomberman.entities.bomb.ThreadFlame;
import uet.oop.bomberman.entities.character.BomberMain;
import uet.oop.bomberman.entities.character.C_Character;
import uet.oop.bomberman.entities.character.enemy.ai.Move;
import uet.oop.bomberman.graphics.Render_Screen;
import uet.oop.bomberman.graphics.Sprites;
import uet.oop.bomberman.level.Position;
import uet.oop.bomberman.output.Audio;

import java.awt.*;


public  class Through_The_Wall extends C_Character {
    protected int _points;
    protected double _speed;
    protected Move _ai;
    protected final double MAX_STEPS;
    protected final double rest;
    protected double _steps;
    protected int _finalAnimation = 30;
    protected Sprites _deadSprite;
    public Through_The_Wall(int x, int y, Board_Game board, Sprites dead, double speed, int points) {
        super(x, y, board);
        _points = points;
        _speed = speed;
        MAX_STEPS = InitGame.TILES_SIZE / _speed;
        rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
        _steps = MAX_STEPS;
        _timeAfter = 30;
        _deadSprite = dead;
    }

    public void update() {
        animate();
        if (!_alive) {
            afterKill();
            return;
        }
        if (_alive)
            calculateMove();
    }

    public void render(Render_Screen screen) {
        if (_alive)
            chooseSprite();
        else {
            if (_timeAfter > 0) {
                _sprite = _deadSprite;
                _animate = 0;
            } else {
                _sprite = Sprites.movingSprite(Sprites.mob_dead1, Sprites.mob_dead2, Sprites.mob_dead3, _animate, 60);
            }
        }
        screen.renderEntity((int) _x, (int) _y - _sprite.SIZE, this);
    }

    public void calculateMove() {
        int xa = 0, ya = 0;
        if (_steps <= 0) {
            _direction = _ai.calculateDirection();
            _steps = MAX_STEPS;
        }
        if (_direction == 0) ya--;
        if (_direction == 2) ya++;
        if (_direction == 3) xa--;
        if (_direction == 1) xa++;
//        if (canMove(xa, ya)) {
            _steps -= 1 + rest;
            move(xa * _speed, ya * _speed);
            _moving = true;
//        } else {
//            _steps = 0;
//            _moving = false;
//            }
    }
    public void move(double xa, double ya) {
        if (!_alive) return;
        _y += ya;
        _x += xa;
    }

    public boolean canMove(double x, double y) {
        double xr = _x, yr = _y - 16;
        if (_direction == 0) {
            yr += _sprite.getSize() - 1;
            xr += _sprite.getSize() / 2;
        }
        if (_direction == 1) {
            yr += _sprite.getSize() / 2;
            xr += 1;
        }
        if (_direction == 2) {
            xr += _sprite.getSize() / 2;
            yr += 1;
        }
        if (_direction == 3) {
            xr += _sprite.getSize() - 1;
            yr += _sprite.getSize() / 2;
        }
        int xx = Position.pixelToTile(xr) + (int) x;
        int yy = Position.pixelToTile(yr) + (int) y;
        All_Entity a = _board.getEntity(xx, yy, this);
        return a.collide(this);
    }

    public boolean collide(All_Entity e) {
        if (e instanceof ThreadFlame) kill();
        if (e instanceof BomberMain) {
            ((BomberMain) e).kill();
            System.out.println("15378");
        }
        return true;
    }

    public void kill() {
        if (!_alive) return;
        _alive = false;
        _board.addPoints(_points);
        Display_Message msg = new Display_Message("+" + _points, getXMessage(), getYMessage(), 2, Color.white, 14);
        _board.addMessage(msg);
    }

    protected void afterKill() {
        if (_timeAfter > 0)
            --_timeAfter;
        else {
            if (_finalAnimation > 0)
                --_finalAnimation;
            else{
                remove();
                Audio.playKill();
            }
        }
    }

    protected  void chooseSprite(){

    }
}
