package uet.oop.bomberman.graphics;

public class Sprites {

    public final int SIZE;
    private int _x, _y;
    public int[] _pixels;
    protected int _realWidth;
    protected int _realHeight;
    private Sprite_Sheet _sheet;

    public static Sprites grass = new Sprites(16, 6, 0, Sprite_Sheet.tiles, 16, 16);
    public static Sprites brick = new Sprites(16, 7, 0, Sprite_Sheet.tiles, 16, 16);
    public static Sprites wall = new Sprites(16, 5, 0, Sprite_Sheet.tiles, 16, 16);
    public static Sprites portal = new Sprites(16, 4, 0, Sprite_Sheet.tiles, 16, 16);

    public static Sprites player_up = new Sprites(16, 0, 0, Sprite_Sheet.tiles, 12, 16);
    public static Sprites player_down = new Sprites(16, 2, 0, Sprite_Sheet.tiles, 12, 15);
    public static Sprites player_left = new Sprites(16, 3, 0, Sprite_Sheet.tiles, 10, 15);
    public static Sprites player_right = new Sprites(16, 1, 0, Sprite_Sheet.tiles, 10, 16);

    public static Sprites player_up_1 = new Sprites(16, 0, 1, Sprite_Sheet.tiles, 12, 16);
    public static Sprites player_up_2 = new Sprites(16, 0, 2, Sprite_Sheet.tiles, 12, 15);

    public static Sprites player_down_1 = new Sprites(16, 2, 1, Sprite_Sheet.tiles, 12, 15);
    public static Sprites player_down_2 = new Sprites(16, 2, 2, Sprite_Sheet.tiles, 12, 16);

    public static Sprites player_left_1 = new Sprites(16, 3, 1, Sprite_Sheet.tiles, 11, 16);
    public static Sprites player_left_2 = new Sprites(16, 3, 2, Sprite_Sheet.tiles, 12, 16);

    public static Sprites player_right_1 = new Sprites(16, 1, 1, Sprite_Sheet.tiles, 11, 16);
    public static Sprites player_right_2 = new Sprites(16, 1, 2, Sprite_Sheet.tiles, 12, 16);

    public static Sprites player_dead1 = new Sprites(16, 4, 2, Sprite_Sheet.tiles, 14, 16);
    public static Sprites player_dead2 = new Sprites(16, 5, 2, Sprite_Sheet.tiles, 13, 15);
    public static Sprites player_dead3 = new Sprites(16, 6, 2, Sprite_Sheet.tiles, 16, 16);


    public static Sprites balloom_left1 = new Sprites(16, 9, 0, Sprite_Sheet.tiles, 16, 16);
    public static Sprites balloom_left2 = new Sprites(16, 9, 1, Sprite_Sheet.tiles, 16, 16);
    public static Sprites balloom_left3 = new Sprites(16, 9, 2, Sprite_Sheet.tiles, 16, 16);

    public static Sprites balloom_right1 = new Sprites(16, 10, 0, Sprite_Sheet.tiles, 16, 16);
    public static Sprites balloom_right2 = new Sprites(16, 10, 1, Sprite_Sheet.tiles, 16, 16);
    public static Sprites balloom_right3 = new Sprites(16, 10, 2, Sprite_Sheet.tiles, 16, 16);
    public static Sprites balloom_dead = new Sprites(16, 9, 3, Sprite_Sheet.tiles, 16, 16);

    public static Sprites doll_left1 = new Sprites(16, 13, 0, Sprite_Sheet.tiles, 16, 16);
    public static Sprites doll_left2 = new Sprites(16, 13, 1, Sprite_Sheet.tiles, 16, 16);
    public static Sprites doll_left3 = new Sprites(16, 13, 2, Sprite_Sheet.tiles, 16, 16);

    public static Sprites doll_right1 = new Sprites(16, 14, 0, Sprite_Sheet.tiles, 16, 16);
    public static Sprites doll_right2 = new Sprites(16, 14, 1, Sprite_Sheet.tiles, 16, 16);
    public static Sprites doll_right3 = new Sprites(16, 14, 2, Sprite_Sheet.tiles, 16, 16);
    public static Sprites doll_dead = new Sprites(16, 13, 3, Sprite_Sheet.tiles, 16, 16);


    public static Sprites kondoria_left1 = new Sprites(16, 10, 5, Sprite_Sheet.tiles, 16, 16);
    public static Sprites kondoria_left2 = new Sprites(16, 10, 6, Sprite_Sheet.tiles, 16, 16);
    public static Sprites kondoria_left3 = new Sprites(16, 10, 7, Sprite_Sheet.tiles, 16, 16);

    public static Sprites kondoria_right1 = new Sprites(16, 11, 5, Sprite_Sheet.tiles, 16, 16);
    public static Sprites kondoria_right2 = new Sprites(16, 11, 6, Sprite_Sheet.tiles, 16, 16);
    public static Sprites kondoria_right3 = new Sprites(16, 11, 7, Sprite_Sheet.tiles, 16, 16);
    public static Sprites kondoria_dead = new Sprites(16, 10, 8, Sprite_Sheet.tiles, 16, 16);


    //ALL
    public static Sprites mob_dead1 = new Sprites(16, 15, 0, Sprite_Sheet.tiles, 16, 16);
    public static Sprites mob_dead2 = new Sprites(16, 15, 1, Sprite_Sheet.tiles, 16, 16);
    public static Sprites mob_dead3 = new Sprites(16, 15, 2, Sprite_Sheet.tiles, 16, 16);

    public static Sprites bomb = new Sprites(16, 0, 3, Sprite_Sheet.tiles, 15, 15);
    public static Sprites bomb_1 = new Sprites(16, 1, 3, Sprite_Sheet.tiles, 13, 15);
    public static Sprites bomb_2 = new Sprites(16, 2, 3, Sprite_Sheet.tiles, 12, 14);

    public static Sprites bomb_exploded = new Sprites(16, 0, 4, Sprite_Sheet.tiles, 16, 16);
    public static Sprites bomb_exploded1 = new Sprites(16, 0, 5, Sprite_Sheet.tiles, 16, 16);
    public static Sprites bomb_exploded2 = new Sprites(16, 0, 6, Sprite_Sheet.tiles, 16, 16);

    public static Sprites explosion_vertical2 = new Sprites(16, 3, 5, Sprite_Sheet.tiles, 16, 16);

    public static Sprites explosion_horizontal2 = new Sprites(16, 1, 9, Sprite_Sheet.tiles, 16, 16);

    public static Sprites explosion_horizontal_left_last2 = new Sprites(16, 0, 9, Sprite_Sheet.tiles, 16, 16);

    public static Sprites explosion_horizontal_right_last2 = new Sprites(16, 2, 9, Sprite_Sheet.tiles, 16, 16);

    public static Sprites explosion_vertical_top_last2 = new Sprites(16, 3, 4, Sprite_Sheet.tiles, 16, 16);

    public static Sprites explosion_vertical_down_last2 = new Sprites(16, 3, 6, Sprite_Sheet.tiles, 16, 16);


    public static Sprites brick_exploded = new Sprites(16, 7, 1, Sprite_Sheet.tiles, 16, 16);
    public static Sprites brick_exploded1 = new Sprites(16, 7, 2, Sprite_Sheet.tiles, 16, 16);
    public static Sprites brick_exploded2 = new Sprites(16, 7, 3, Sprite_Sheet.tiles, 16, 16);

    public static Sprites powerup_bombs = new Sprites(16, 0, 10, Sprite_Sheet.tiles, 16, 16);
    public static Sprites powerup_flames = new Sprites(16, 1, 10, Sprite_Sheet.tiles, 16, 16);
    public static Sprites powerup_speed = new Sprites(16, 2, 10, Sprite_Sheet.tiles, 16, 16);
   public Sprites(int size, int x, int y, Sprite_Sheet sheet, int rw, int rh) {
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        _x = x * SIZE;
        _y = y * SIZE;
        _sheet = sheet;
        _realWidth = rw;
        _realHeight = rh;
        load();
    }

    public Sprites(int size, int color) {
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    private void setColor(int color) {
        for (int i = 0; i < _pixels.length; i++) {
            _pixels[i] = color;
        }
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                _pixels[x + y * SIZE] = _sheet._pixels[(x + _x) + (y + _y) * _sheet.SIZE];
            }
        }
    }

    public static Sprites movingSprite(Sprites normal, Sprites x1, Sprites x2, int animate, int time) {
        int calc = animate % time;
        int diff = time / 3;
        if (calc < diff) {
            return normal;
        }
        if (calc < diff * 2) {
            return x1;
        }
        return x2;
    }

    public static Sprites movingSprite(Sprites x1, Sprites x2, int animate, int time) {
        int diff = time / 2;
        return (animate % time > diff) ? x1 : x2;
    }

    public int getRealWidth() {
        return _realWidth;
    }

    public int getRealHeight() {
        return _realHeight;
    }

    public int getSize() {
        return SIZE;
    }

    public int getPixel(int i) {
        return _pixels[i];
    }

}
