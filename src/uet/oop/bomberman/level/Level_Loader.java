package uet.oop.bomberman.level;

import uet.oop.bomberman.Board_Game;

public abstract class Level_Loader {

    protected int _width = 20, _height = 20;
    public int _level;
    protected Board_Game _board;
    public Level_Loader(Board_Game board, int level)  {
        _board = board;
        loadLevel(level);
    }

    public abstract void loadLevel(int level) ;

    public abstract void createEntities();

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public int getLevel() {
        return _level;
    }

}
