package uet.oop.bomberman.entities.character;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.entities.All_Entity;
import uet.oop.bomberman.entities.Layered_Entity;
import uet.oop.bomberman.entities.bomb.ThreadBomb;
import uet.oop.bomberman.entities.bomb.ThreadFlame;
import uet.oop.bomberman.entities.character.enemy.E_Enemy;
import uet.oop.bomberman.entities.tile.Wall_Item;
import uet.oop.bomberman.entities.tile.destroyable.ThreadBrick;
import uet.oop.bomberman.graphics.Render_Screen;
import uet.oop.bomberman.graphics.Sprites;
import uet.oop.bomberman.input.I_Keyboard;
import uet.oop.bomberman.level.Position;
import uet.oop.bomberman.output.Audio;

import java.util.Iterator;
import java.util.List;

public class BomberMain extends C_Character {
    private List<ThreadBomb> _bombs;
    protected I_Keyboard _input;
    protected int _timeBetweenPutBombs = 0;

    public BomberMain(int x, int y, Board_Game board) {
        super(x, y, board);
        _bombs = _board.getBombs();
        _input = _board.getInput();
        _sprite = Sprites.player_right;
    }

    public void update() {
        clearBombs();
        if (!_alive) {
            afterKill();
            return;
        }
        if (_timeBetweenPutBombs < -7500) _timeBetweenPutBombs = 0;
        else _timeBetweenPutBombs--;
        animate();
        calculateMove();
        detectPlaceBomb();
    }

    public void render(Render_Screen screen) {
        calculateXOffset();
        if (_alive)
            chooseSprite();
        else {
            _sprite = Sprites.movingSprite(Sprites.player_dead1, Sprites.player_dead2,Sprites.player_dead3,_animate, 100 );
        }
        screen.renderEntity((int) _x, (int) _y - _sprite.SIZE, this);
    }

    public void calculateXOffset() {
        int xScroll = Render_Screen.calculateXOffset(_board, this);
        Render_Screen.setOffset(xScroll, 0);
    }
    private void detectPlaceBomb() {
        int bombRate = GameLoop.getBombRate();
        if (_input.space && _timeBetweenPutBombs < 0 && bombRate >= 1) {
            double centerX = _x + _sprite.getRealWidth() / 2;
            double centerY = _y - _sprite.getRealHeight() / 2;
            placeBomb(Position.pixelToTile(centerX), Position.pixelToTile(centerY));
            GameLoop.addBombRate(-1);
            _timeBetweenPutBombs = 30;
        }
    }

    protected void placeBomb(int x, int y) {
        ThreadBomb bomb = new ThreadBomb(x, y, _board);
        _bombs.add(bomb);
    }

    private void clearBombs() {
        Iterator<ThreadBomb> bs = _bombs.iterator();
        ThreadBomb b;
        while (bs.hasNext()) {
            b = bs.next();
            if (b.isRemoved()) {
                bs.remove();
                GameLoop.addBombRate(1);
            }
        }
    }

    public void kill() {
        if (!_alive) return;
        _alive = false;
        Audio.playDeath();
    }

    protected void afterKill() {
        if (_timeAfter > 0) --_timeAfter;
        else {
            _board.endGame();
        }
    }

    protected void calculateMove() {
        _moving = true;
        if (_input.up) {
            move(0, -GameLoop.getBomberSpeed());
        } else if (_input.down) {
            move(0, GameLoop.getBomberSpeed());
        } else if (_input.left) {
            move(-GameLoop.getBomberSpeed(), 0);
        } else if (_input.right) {
            move(GameLoop.getBomberSpeed(), 0);
        } else {
            _moving = false;
        }
    }

    public boolean canMove(double x, double y) {
        int tileX = Position.pixelToTile(x);
        int tileY = Position.pixelToTile(y);
        All_Entity nextEntity = _board.getEntity(tileX, tileY, this);
        return collide(nextEntity);
    }
    private boolean canMoveNotCollide(double x, double y) {
        int tileX = Position.pixelToTile(x);
        int tileY = Position.pixelToTile(y);
        All_Entity nextEntity = _board.getEntity(tileX, tileY, this);
        if (nextEntity instanceof Wall_Item) return false;
        if (nextEntity instanceof Layered_Entity) {
            All_Entity topEntity = ((Layered_Entity) nextEntity).getTopEntity();
            if (topEntity instanceof ThreadBrick) return false;
        }
        return true;
    }


    public void moveCenterX() {
        int pixelOfEntity = Position.tileToPixel(1);
        double centerX = _x + _sprite.getRealWidth() / 2;
        int tileCenterX = Position.pixelToTile(centerX);
        _x = Position.tileToPixel(tileCenterX) + pixelOfEntity / 2 - _sprite.getRealWidth() / 2;
    }

    public void moveCenterY() {
        int pixelOfEntity = Position.tileToPixel(1);
        double centerY = _y - _sprite.getRealHeight() / 2;
        int tileCenterY = Position.pixelToTile(centerY);
        _y = Position.tileToPixel(tileCenterY) + pixelOfEntity / 2 + _sprite.getRealHeight() / 2;
    }

    private void autoMoveCenter() {
        int pixelOfEntity = Position.tileToPixel(1);
        double centerX = _x + _sprite.getRealWidth() / 2;
        double centerY = _y - _sprite.getRealHeight() / 2;
        boolean contactTop = !canMoveNotCollide(centerX, centerY - pixelOfEntity / 2);
        boolean contactDown = !canMoveNotCollide(centerX, centerY + pixelOfEntity / 2);
        boolean contactLeft = !canMoveNotCollide(centerX - pixelOfEntity / 2, centerY);
        boolean contactRight = !canMoveNotCollide(centerX + pixelOfEntity / 2, centerY);
        if (_direction != 0 && contactDown) moveCenterY();
        if (_direction != 1 && contactLeft) moveCenterX();
        if (_direction != 2 && contactTop) moveCenterY();
        if (_direction != 3 && contactRight) moveCenterX();
    }

    public void move(double xa, double ya) {
        double centerX = _x + _sprite.getRealWidth() / 2;
        double centerY = _y - _sprite.getRealHeight() / 2;
        if (xa > 0) _direction = 1;
        if (xa < 0) _direction = 3;
        if (ya > 0) _direction = 2;
        if (ya < 0) _direction = 0;
        if (canMove(centerX + xa, centerY + ya)) {
            _x += xa;
            _y += ya;
        }
        autoMoveCenter();
    }

    public boolean handleCollidePortal() {
        if (_board.detectNoEnemies()) {
            _board.nextLevel();
            return true;
        }
        return false;
    }

    public boolean collide(All_Entity e) {
        if (e instanceof ThreadFlame) {
            this.kill();
            return true;
        }
        if (e instanceof E_Enemy) {
            this.kill();
            return true;
        }
        if (e instanceof Wall_Item) return false;
        if (e instanceof ThreadBrick) return false;
        if (e instanceof Layered_Entity) return e.collide(this);
        return true;
    }

    private void chooseSprite() {
        switch (_direction) {
            case 0:
                _sprite = Sprites.player_up;
                if (_moving) {
                    _sprite = Sprites.movingSprite(Sprites.player_up_1, Sprites.player_up_2, _animate, 20);
                }
                break;
            case 1:
                _sprite = Sprites.player_right;
                if (_moving) {
                    _sprite = Sprites.movingSprite(Sprites.player_right_1, Sprites.player_right_2, _animate, 20);
                }
                break;
            case 2:
                _sprite = Sprites.player_down;
                if (_moving) {
                    _sprite = Sprites.movingSprite(Sprites.player_down_1, Sprites.player_down_2, _animate, 20);
                }
                break;
            case 3:
                _sprite = Sprites.player_left;
                if (_moving) {
                    _sprite = Sprites.movingSprite(Sprites.player_left_1, Sprites.player_left_2, _animate, 20);
                }
                break;
            default:
                _sprite = Sprites.player_right;
                if (_moving) {
                    _sprite = Sprites.movingSprite(Sprites.player_right_1, Sprites.player_right_2, _animate, 20);
                }
                break;
        }
    }
}
