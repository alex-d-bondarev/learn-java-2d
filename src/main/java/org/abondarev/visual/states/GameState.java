package org.abondarev.visual.states;

import org.abondarev.visual.entities.creatures.Player;
import org.abondarev.visual.gfx.Assets;

import java.awt.*;

public class GameState extends State{

    private Player player;

    public GameState(){
        player = new Player(100, 100);
    }

    public void tick() {
        player.tick();
    }

    public void render(Graphics g) {
        player.render(g);
    }
}
