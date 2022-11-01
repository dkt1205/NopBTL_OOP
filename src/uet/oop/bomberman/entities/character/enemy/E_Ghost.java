package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.InitGame;
import uet.oop.bomberman.entities.character.enemy.ai.MoveThroughWall;
import uet.oop.bomberman.graphics.Sprites;

public class E_Ghost extends Through_The_Wall {

    public E_Ghost(int x, int y, Board_Game board) {
        super(x, y, board, Sprites.ghost_dead, InitGame.getBomberSpeed()*0.5, 150);
        _sprite = Sprites.ghost_left1;
        _ai = new MoveThroughWall(_board.getBomber(), this);
        _direction = _ai.calculateDirection();
    }

    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                if (_moving)
                    _sprite = Sprites.movingSprite(Sprites.ghost_right1, Sprites.ghost_right2, Sprites.ghost_right3, _animate, 60);
                else
                    _sprite = Sprites.ghost_left1;
                break;
            case 2:
            case 3:
                if (_moving)
                    _sprite = Sprites.movingSprite(Sprites.ghost_left1, Sprites.ghost_left2, Sprites.ghost_left3, _animate, 60);
                else
                    _sprite = Sprites.ghost_left1;
                break;
        }
    }
}
