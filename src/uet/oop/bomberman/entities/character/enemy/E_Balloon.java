package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.entities.character.enemy.ai.AIMoveRandom;
import uet.oop.bomberman.graphics.Sprites;

public class E_Balloon extends E_Enemy {

    public E_Balloon(int x, int y, Board_Game board) {
        super(x, y, board, Sprites.balloom_dead, GameLoop.getBomberSpeed() / 2, 100);
        _sprite = Sprites.balloom_left1;
        _ai = new AIMoveRandom();
        _direction = _ai.calculateDirection();
    }

    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                _sprite = Sprites.movingSprite(Sprites.balloom_right1, Sprites.balloom_right2, Sprites.balloom_right3, _animate, 60);
                break;
            case 2:
            case 3:
                _sprite = Sprites.movingSprite(Sprites.balloom_left1, Sprites.balloom_left2, Sprites.balloom_left3, _animate, 60);
                break;
        }
    }
}
