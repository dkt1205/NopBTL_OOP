package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.InitGame;
import uet.oop.bomberman.entities.character.enemy.ai.AIDodge;
import uet.oop.bomberman.graphics.Sprites;

public class E_Kondoria extends E_Enemy {

    public E_Kondoria(int x, int y, Board_Game board) {
        super(x, y, board, Sprites.kondoria_dead, InitGame.getBomberSpeed()*0.75, 200);
        _sprite = Sprites.kondoria_left1;
        _ai = new AIDodge(_board.getBomber(), this,_board);
        _direction = _ai.calculateDirection();
    }
    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                if (_moving)
                    _sprite = Sprites.movingSprite(Sprites.kondoria_right1, Sprites.kondoria_right2, Sprites.kondoria_right3, _animate, 60);
                else
                    _sprite = Sprites.kondoria_left1;
                break;
            case 2:
            case 3:
                if (_moving)
                    _sprite = Sprites.movingSprite(Sprites.kondoria_left1, Sprites.kondoria_left2, Sprites.kondoria_left3, _animate, 60);
                else
                    _sprite = Sprites.kondoria_left1;
                break;
        }
    }
}
