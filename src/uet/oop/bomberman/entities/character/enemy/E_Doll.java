package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.InitGame;
import uet.oop.bomberman.entities.character.enemy.ai.AISmart;
import uet.oop.bomberman.graphics.Sprites;

public class E_Doll extends E_Enemy {

    public E_Doll(int x, int y, Board_Game board) {
        super(x, y, board, Sprites.doll_dead, InitGame.getBomberSpeed(), 300);
        _sprite = Sprites.doll_left1;
        _ai = new AISmart(_board.getBomber(), this,board);
        _direction = _ai.calculateDirection();
    }

    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                if (_moving)
                    _sprite = Sprites.movingSprite(Sprites.doll_right1, Sprites.doll_right2, Sprites.doll_right3, _animate, 60);
                else
                    _sprite = Sprites.doll_left1;
                break;
            case 2:
            case 3:
                if (_moving)
                    _sprite = Sprites.movingSprite(Sprites.doll_left1, Sprites.doll_left2, Sprites.doll_left3, _animate, 60);
                else
                    _sprite = Sprites.doll_left1;
                break;
        }
    }
}
