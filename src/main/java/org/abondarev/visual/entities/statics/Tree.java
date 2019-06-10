package org.abondarev.visual.entities.statics;

import org.abondarev.visual.Handler;
import org.abondarev.visual.gfx.Assets;
import org.abondarev.visual.tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHIGHT);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(Assets.tree,
                (int) (x - handler.getGameCamera().getXOffset()),
                (int) (y - handler.getGameCamera().getYOffset()), width, height, null);
    }
}
