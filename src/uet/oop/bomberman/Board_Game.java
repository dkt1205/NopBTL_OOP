package uet.oop.bomberman;

import uet.oop.bomberman.entities.All_Entity;
import uet.oop.bomberman.entities.Display_Message;
import uet.oop.bomberman.entities.bomb.ThreadBomb;
import uet.oop.bomberman.entities.bomb.FlameLoad;
import uet.oop.bomberman.entities.character.BomberMain;
import uet.oop.bomberman.entities.character.C_Character;
import uet.oop.bomberman.graphics.I_Render;
import uet.oop.bomberman.graphics.Render_Screen;
import uet.oop.bomberman.input.I_Keyboard;
import uet.oop.bomberman.level.File_LevelLoader;
import uet.oop.bomberman.level.Level_Loader;
import uet.oop.bomberman.output.Audio;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static uet.oop.bomberman.output.Audio.playWin;

public class Board_Game implements I_Render {
    protected Level_Loader _levelLoader;
    protected GameLoop _game;
    protected I_Keyboard _input;
    protected Render_Screen _screen;

    public All_Entity[] _entities;
    public List<C_Character> _characters = new ArrayList<>();
    protected List<ThreadBomb> _bombs = new ArrayList<>();
    private List<Display_Message> _messages = new ArrayList<>();

    private int _screenToShow = -1;
    // 0:instruct, 1:endgame, 2:change level, 3:paused, 4:win

    private int _time = GameLoop.TIME;
    private int _points = GameLoop.POINTS;

    public Board_Game(GameLoop game, I_Keyboard input, Render_Screen screen) {
        _game = game;
        _input = input;
        _screen = screen;

        loadLevel(1);
    }

    public void update() {
        if (_game.isPaused()) {
            handleInputPaused();
            return;
        }
        updateEntities();
        updateCharacters();
        updateBombs();
        updateMessages();
        detectEndGame();
        detectPaused();
        detectWinGame();
        for (int i = 0; i < _characters.size(); i++) {
            C_Character a = _characters.get(i);
            if (a.isRemoved()) _characters.remove(i);
        }
    }


    public void render(Render_Screen screen) {
        if (_game.isPaused()) return;
        int x0 = Render_Screen.xOffset >> 4;
        int x1 = (Render_Screen.xOffset + screen.getWidth() + GameLoop.TILES_SIZE) / GameLoop.TILES_SIZE;
        int y0 = Render_Screen.yOffset >> 4;
        int y1 = (Render_Screen.yOffset + screen.getHeight()) / GameLoop.TILES_SIZE;
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                _entities[x + y * _levelLoader.getWidth()].render(screen);
            }
        }
        renderBombs(screen);
        renderCharacter(screen);

    }

    public void handleInputPaused() {
        if (_screenToShow == 2) return;
        if (_input.enter) {
            _game.play();
            if (_screenToShow == 3) Audio.resumeAudioEffect();
            if (_screenToShow == 1 ||_screenToShow == 4) resetGame();
        }
    }

    public void resetGame() {
        resetProperties();
        loadLevel(1);
    }

    private void resetProperties() {
        _points = GameLoop.POINTS;
        GameLoop.bomberSpeed = 1.0;
        GameLoop.bombRadius = 1;
        GameLoop.bombRate = 1;
    }

    public void restartLevel() {
        loadLevel(_levelLoader.getLevel());
    }

    public void nextLevel() {
        if(_levelLoader.getLevel()<=2)
            loadLevel(_levelLoader.getLevel() + 1);
    }

    public void loadLevel(int level) {
        _time = GameLoop.TIME;
        _screenToShow = 2;
        _game.resetScreenDelay();
        _game.pause();
        _characters.clear();
        _bombs.clear();
        _messages.clear();
        _levelLoader = new File_LevelLoader(this, level);
        _entities = new All_Entity[_levelLoader.getHeight() * _levelLoader.getWidth()];
        _levelLoader.createEntities();
    }

    protected void detectEndGame() {
        if (_time <= 0)
            endGame();
    }

    public void endGame() {
        _screenToShow = 1;
        _game.resetScreenDelay();
        _game.pause();
    }
    protected void detectWinGame(){
        if(_levelLoader.getLevel()==3){
            winGame();
        }
    }
    public void winGame(){
        _screenToShow=4;
        _game.resetScreenDelay();
        playWin();
    }

    protected void detectPaused() {
        if (_input.backSpace)
            pausedGame();
    }

    public void pausedGame() {
        _screenToShow = 3;
        _game.resetScreenDelay();
        _game.pause();
        Audio.suspendAudioEffect();
    }

    public boolean detectNoEnemies() {
        int total = 0;
        for (int i = 0; i < _characters.size(); i++) {
            if (_characters.get(i) instanceof BomberMain == false)
                ++total;
        }
        return total == 0;
    }

    public void drawScreen(Graphics g) {
        switch (_screenToShow) {
            case 0:
                _screen.drawInstruction(g);
                break;
            case 1:
                _screen.drawEndGame(g, _points);
                Audio.playGameOver();
                break;
            case 2:
                _screen.drawChangeLevel(g, _levelLoader.getLevel());
                Audio.playWait();
                break;
            case 3:
                _screen.drawPaused(g);
                break;
            case 4:
                _screen.drawWin(g,_points);
                Audio.playWin();
                break;
        }
    }

    public All_Entity getEntity(double x, double y, C_Character m) {
        All_Entity res = null;
        res = getFlameSegmentAt((int) x, (int) y);
        if (res != null) return res;
        res = getBombAt(x, y);
        if (res != null) return res;
        res = getCharacterAtExcluding((int) x, (int) y, m);
        if (res != null) return res;
        res = getEntityAt((int) x, (int) y);
        return res;
    }

    public List<ThreadBomb> getBombs() {
        return _bombs;
    }

    public ThreadBomb getBombAt(double x, double y) {
        Iterator<ThreadBomb> bs = _bombs.iterator();
        ThreadBomb b;
        while (bs.hasNext()) {
            b = bs.next();
            if (b.getX() == (int) x && b.getY() == (int) y)
                return b;
        }

        return null;
    }

    public BomberMain getBomber() {
        Iterator<C_Character> itr = _characters.iterator();
        C_Character cur;
        while (itr.hasNext()) {
            cur = itr.next();

            if (cur instanceof BomberMain)
                return (BomberMain) cur;
        }
        return null;
    }

    public C_Character getCharacterAtExcluding(int x, int y, C_Character a) {
        Iterator<C_Character> itr = _characters.iterator();
        C_Character cur;
        while (itr.hasNext()) {
            cur = itr.next();
            if (cur == a) {
                continue;
            }
            if (cur.getXTile() == x && cur.getYTile() == y) {
                return cur;
            }
        }
        return null;
    }

    public FlameLoad getFlameSegmentAt(int x, int y) {
        Iterator<ThreadBomb> bs = _bombs.iterator();
        ThreadBomb b;
        while (bs.hasNext()) {
            b = bs.next();
            FlameLoad e = b.flameAt(x, y);
            if (e != null) {
                return e;
            }
        }
        return null;
    }

    public All_Entity getEntityAt(double x, double y) {
        return _entities[(int) x + (int) y * _levelLoader.getWidth()];
    }

    public void addEntity(int pos, All_Entity e) {
        _entities[pos] = e;
    }

    public void addCharacter(C_Character e) {
        _characters.add(e);
    }

    public void addMessage(Display_Message e) {
        _messages.add(e);
    }

    protected void renderCharacter(Render_Screen screen) {
        Iterator<C_Character> itr = _characters.iterator();

        while (itr.hasNext())
            itr.next().render(screen);
    }

    protected void renderBombs(Render_Screen screen) {
        Iterator<ThreadBomb> itr = _bombs.iterator();

        while (itr.hasNext())
            itr.next().render(screen);
    }

    public void renderMessages(Graphics g) {
        Display_Message m;
        for (int i = 0; i < _messages.size(); i++) {
            m = _messages.get(i);

            g.setFont(new Font("Arial", Font.PLAIN, m.getSize()));
            g.setColor(m.getColor());
            g.drawString(m.getMessage(), (int) m.getX() - Render_Screen.xOffset * GameLoop.SCALE, (int) m.getY());
        }
    }

    protected void updateEntities() {
        if (_game.isPaused()) return;
        for (int i = 0; i < _entities.length; i++) {
            _entities[i].update();
        }
    }

    protected void updateCharacters() {
        if (_game.isPaused()) return;
        Iterator<C_Character> itr = _characters.iterator();

        while (itr.hasNext() && !_game.isPaused())
            itr.next().update();
    }

    protected void updateBombs() {
        if (_game.isPaused()) return;
        Iterator<ThreadBomb> itr = _bombs.iterator();

        while (itr.hasNext())
            itr.next().update();
    }

    protected void updateMessages() {
        if (_game.isPaused()) return;
        Display_Message m;
        int left;
        for (int i = 0; i < _messages.size(); i++) {
            m = _messages.get(i);
            left = m.getDuration();

            if (left > 0)
                m.setDuration(--left);
            else
                _messages.remove(i);
        }
    }

    public int subtractTime() {
        if (_game.isPaused())
            return this._time;
        else
            return this._time--;
    }

    public I_Keyboard getInput() {
        return _input;
    }

    public Level_Loader getLevel() {
        return _levelLoader;
    }

    public GameLoop getGame() {
        return _game;
    }

    public int getShow() {
        return _screenToShow;
    }

    public void setShow(int i) {
        _screenToShow = i;
    }

    public int getTime() {
        return _time;
    }

    public int getPoints() {
        return _points;
    }

    public void addPoints(int points) {
        this._points += points;
    }

    public int getWidth() {
        return _levelLoader.getWidth();
    }

    public int getHeight() {
        return _levelLoader.getHeight();
    }

}
