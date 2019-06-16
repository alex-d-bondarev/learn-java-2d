package org.abondarev.visual.entities.statics;

import org.abondarev.visual.Handler;
import org.abondarev.visual.gfx.Assets;
import org.abondarev.visual.items.Item;
import org.abondarev.visual.tiles.Tile;

import java.awt.*;

public class Rock extends StaticEntity {

    public Rock(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHIGHT);
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x, (int) y));
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(Assets.rock,
                (int) (x - handler.getGameCamera().getXOffset()),
                (int) (y - handler.getGameCamera().getYOffset()),
                width,
                height,
                null);
    }
}
