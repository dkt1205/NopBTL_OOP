package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.entities.All_Entity;
import uet.oop.bomberman.entities.character.BomberMain;
import uet.oop.bomberman.graphics.Sprites;

public class Portal_Item extends Tile_Item {

    public Portal_Item(int x, int y, Sprites sprite) {
        super(x, y, sprite);
    }

    public boolean collide(All_Entity e) {
        if (e instanceof BomberMain ) {
            return ((BomberMain) e).handleCollidePortal();
        }
        return false;
    }

}
