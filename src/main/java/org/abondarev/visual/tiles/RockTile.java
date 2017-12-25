package org.abondarev.visual.tiles;

import org.abondarev.visual.gfx.Assets;

public class RockTile extends Tile{
    public RockTile(int id) {
        super(Assets.stone, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
