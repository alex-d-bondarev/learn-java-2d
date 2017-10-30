package org.abondarev.visual.entities.creatures;

import org.abondarev.visual.gfx.Assets;

import java.awt.*;

public class Player extends Creature{

    public Player(float x, float y) {
        super(x, y);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) x, (int) y, null);
    }
}
