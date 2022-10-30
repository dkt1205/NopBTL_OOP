package uet.oop.bomberman.level;

import uet.oop.bomberman.Board_Game;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.entities.Layered_Entity;
import uet.oop.bomberman.entities.character.BomberMain;
import uet.oop.bomberman.entities.character.enemy.*;
import uet.oop.bomberman.entities.tile.Grass_Item;
import uet.oop.bomberman.entities.tile.Portal_Item;
import uet.oop.bomberman.entities.tile.Wall_Item;
import uet.oop.bomberman.entities.tile.destroyable.ThreadBrick;
import uet.oop.bomberman.entities.tile.item.Bomb_Item;
import uet.oop.bomberman.entities.tile.item.Flame_Item;
import uet.oop.bomberman.entities.tile.item.Speed_Item;
import uet.oop.bomberman.graphics.Render_Screen;
import uet.oop.bomberman.graphics.Sprites;

import java.io.*;

public class File_LevelLoader extends Level_Loader {

    private static char[][] _map;

    public File_LevelLoader(Board_Game board, int level)  {
        super(board, level);
    }

    public void loadLevel(int level) {
        try {
            InputStream inputStream = new FileInputStream("res/levels/level" + level + ".txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String currentLine = bufferedReader.readLine();
            String[] firstLine = currentLine.split(" ");
            int dataHeight = Integer.parseInt(firstLine[1]);
            int dataWidth = Integer.parseInt(firstLine[2]);
            _map = new char[dataHeight][dataWidth];
            for (int i = 0; i < dataHeight; i++) {
                String dataLine = bufferedReader.readLine();
                char[] charInLines = dataLine.toCharArray();
                for (int j = 0; j < dataWidth; j++) {
                    _map[i][j] = charInLines[j];
                }
            }
            _width = dataWidth;
            _height = dataHeight;
            _level = level;
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR]: File level not found.");
        } catch (IOException e) {
            System.out.println("[ERROR]: Load file level.");
        }
    }

    public void createEntities() {
        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                int pos = x + y * _width;
                // thêm Grass
                _board.addEntity(pos, new Grass_Item(x, y, Sprites.grass));
                // thêm Wall
                if (_map[y][x] == '#') {
                    _board.addEntity(pos, new Wall_Item(x, y, Sprites.wall));
                }
                // thêm Bomber
                if (_map[y][x] == 'p') {
                    int xBomber = x, yBomber = y;
                    _board.addCharacter(new BomberMain(Position.tileToPixel(xBomber), Position.tileToPixel(yBomber) + GameLoop.TILES_SIZE, _board));
                    Render_Screen.setOffset(0, 0);
                    _board.addEntity(xBomber + yBomber * _width, new Grass_Item(xBomber, yBomber, Sprites.grass));
                }
                // thêm Enemy
                if (_map[y][x] == '1') {
                    int xE = x, yE = y;
                    _board.addCharacter(new E_Balloon(Position.tileToPixel(xE), Position.tileToPixel(yE) + GameLoop.TILES_SIZE, _board));
                    _board.addEntity(xE + yE * _width, new Grass_Item(xE, yE, Sprites.grass));
                }
                if (_map[y][x] == '2') {
                    int xE = x, yE = y;
                    _board.addCharacter(new E_Doll(Position.tileToPixel(xE), Position.tileToPixel(yE) + GameLoop.TILES_SIZE, _board));
                    _board.addEntity(xE + yE * _width, new Grass_Item(xE, yE, Sprites.grass));
                }
                if (_map[y][x] == '3') {
                    int xE = x, yE = y;
                    _board.addCharacter(new E_Kondoria(Position.tileToPixel(xE), Position.tileToPixel(yE) + GameLoop.TILES_SIZE, _board));
                    _board.addEntity(xE + yE * _width, new Grass_Item(xE, yE, Sprites.grass));
                }
                if (_map[y][x] == '*') {
                    int xB = x, yB = y;
                    _board.addEntity(xB + yB * _width,
                            new Layered_Entity(xB, yB,
                                    new Grass_Item(xB, yB, Sprites.grass),
                                    new ThreadBrick(xB, yB, Sprites.brick)
                            )
                    );
                }
                // thêm Brick che phủ ở trên Item
                if (_map[y][x] == 'x') {
                    int xI = x, yI = y;
                    _board.addEntity(xI + yI * _width,
                            new Layered_Entity(xI, yI,
                                    new Grass_Item(xI, yI, Sprites.grass),
                                    new Portal_Item(xI, yI, Sprites.portal),
                                    new ThreadBrick(xI, yI, Sprites.brick)
                            )
                    );
                }
                if (_map[y][x] == 'b') {
                    int xI = x, yI = y;
                    _board.addEntity(xI + yI * _width,
                            new Layered_Entity(xI, yI,
                                    new Grass_Item(xI, yI, Sprites.grass),
                                    new Bomb_Item(xI, yI, Sprites.powerup_bombs),
                                    new ThreadBrick(xI, yI, Sprites.brick)
                            )
                    );
                }
                if (_map[y][x] == 's') {
                    int xI = x, yI = y;
                    _board.addEntity(xI + yI * _width,
                            new Layered_Entity(xI, yI,
                                    new Grass_Item(xI, yI, Sprites.grass),
                                    new Speed_Item(xI, yI, Sprites.powerup_speed),
                                    new ThreadBrick(xI, yI, Sprites.brick)
                            )
                    );
                }
                if (_map[y][x] == 'f') {
                    int xI = x, yI = y;
                    _board.addEntity(xI + yI * _width,
                            new Layered_Entity(xI, yI,
                                    new Grass_Item(xI, yI, Sprites.grass),
                                    new Flame_Item(xI, yI, Sprites.powerup_flames),
                                    new ThreadBrick(xI, yI, Sprites.brick)
                            )
                    );
                }
            }
        }
    }
}
