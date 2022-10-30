package uet.oop.bomberman.entities;

import uet.oop.bomberman.entities.tile.destroyable.Destroyable;
import uet.oop.bomberman.graphics.Render_Screen;

import java.util.LinkedList;

public class Layered_Entity extends All_Entity {

    protected LinkedList<All_Entity> _entities = new LinkedList<>();

    public Layered_Entity(int x, int y, All_Entity... entities) {
        _x = x;
        _y = y;

        for (int i = 0; i < entities.length; i++) {
            _entities.add(entities[i]);

            if (i > 1) {
                if (entities[i] instanceof Destroyable)
                    ((Destroyable) entities[i]).addBelowSprite(entities[i - 1].getSprite());
            }
        }
    }

    public void update() {
        clearRemoved();
        getTopEntity().update();
    }

    public void render(Render_Screen screen) {
        getTopEntity().render(screen);
    }

    public All_Entity getTopEntity() {

        return _entities.getLast();
    }

    private void clearRemoved() {
        All_Entity top = getTopEntity();

        if (top.isRemoved()) {
            _entities.removeLast();
        }
    }

    public boolean collide(All_Entity e) {
        All_Entity topEntity = getTopEntity();
        return topEntity.collide(e);
    }

}
