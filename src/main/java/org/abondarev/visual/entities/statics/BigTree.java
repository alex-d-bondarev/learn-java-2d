package org.abondarev.visual.entities.statics;

import org.abondarev.visual.Handler;
import org.abondarev.visual.gfx.Assets;
import org.abondarev.visual.tiles.Tile;

import java.awt.*;

public class BigTree extends StaticEntity {

    public BigTree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH*2, Tile.TILEHIGHT*2);
    }

    @Override
    public void die() {

    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(Assets.tree,
                (int) (x - handler.getGameCamera().getXOffset()),
                (int) (y - handler.getGameCamera().getYOffset()), width, height, null);
    }

    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        return super.checkEntityCollisions(xOffset, yOffset);
    }
}
