package uet.oop.bomberman;

import uet.oop.bomberman.graphics.Render_Screen;
import uet.oop.bomberman.gui.CreateGame;
import uet.oop.bomberman.input.I_Keyboard;
import uet.oop.bomberman.output.Audio;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class GameLoop extends Canvas {
    public static final
            int TILES_SIZE = 16,
            WIDTH = TILES_SIZE * 15,
            HEIGHT = 12 * TILES_SIZE;
    public static int SCALE = 3;
    public static final String TITLE = "BTL Bomberman";
    private static final int BOMBRATE = 1;
    private static final int BOMBRADIUS = 1;
    private static final double BOMBERSPEED = 1.0;
    public static final int TIME = 100;
    public static final int POINTS = 0;

    protected static int SCREENDELAY = 4;
    protected static int bombRate = BOMBRATE;
    protected static int bombRadius = BOMBRADIUS;
    protected static double bomberSpeed = BOMBERSPEED;
    protected int _screenDelay = SCREENDELAY;
    private I_Keyboard _input;
    private boolean _running = false;
    private boolean _paused = true;
    private Board_Game _board;
    private Render_Screen screen;
    private CreateGame _frame;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public GameLoop(CreateGame frame) {
        _frame = frame;
        _frame.setTitle(TITLE);

        screen = new Render_Screen(WIDTH, HEIGHT);
        _input = new I_Keyboard();

        _board = new Board_Game(this, _input, screen);
        addKeyListener(_input);
    }
    private void renderGame() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        screen.clear();
        _board.render(screen);
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen._pixels[i];
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        _board.renderMessages(g);
        g.dispose();
        bs.show();
    }

    private void renderScreen() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        screen.clear();
        Graphics g = bs.getDrawGraphics();
        _board.drawScreen(g);
        g.dispose();
        bs.show();
    }

    private void update() {
        _input.update();
        _board.update();
    }

    public void start() {
        _running = true;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0; //60 frames per second
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while (_running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            if (_paused) {
                if (_screenDelay <= 0) {
                    _board.setShow(-1);
                    _paused = false;
                }
                renderScreen();
            } else {
                renderGame();
                Audio.playBackground();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                _frame.setTime(_board.subtractTime());
                _frame.setPoints(_board.getPoints());
                timer += 1000;
                _frame.setTitle(TITLE);
                updates = 0;
                frames = 0;
                if (_board.getShow() == 2)
                    --_screenDelay;
            }
        }
    }

    public static double getBomberSpeed() {
        return bomberSpeed;
    }

    public static int getBombRate() {
        return bombRate;
    }

    public static int getBombRadius() {
        return bombRadius;
    }

    public static void addBomberSpeed(double i) {
        bomberSpeed += i;
    }

    public static void addBombRadius(int i) {
        bombRadius += i;
    }

    public static void addBombRate(int i) {
        bombRate += i;
    }

    public void resetScreenDelay() {
        _screenDelay = SCREENDELAY;
    }

    public Board_Game getBoard() {
        return _board;
    }
    public boolean isPaused() {
        return _paused;
    }

    public void pause() {
        _paused = true;
    }

    public void play() {
        _paused = false;
    }
}
