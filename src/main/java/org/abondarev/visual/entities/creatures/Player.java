package org.abondarev.visual.entities.creatures;

import org.abondarev.visual.Game;
import org.abondarev.visual.gfx.Assets;

import java.awt.*;

public class Player extends Creature{

    private Game game;

    public Player(Game game, float x, float y) {
        super(x, y);
        this.game = game;
    }

    public void tick() {
        if(game.getKeyManager().up)
            y -= 3;
        if(game.getKeyManager().down)
            y += 3;
        if(game.getKeyManager().left)
            x -= 3;
        if(game.getKeyManager().right)
            x += 3;

    }

    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) x, (int) y, null);
    }
}
