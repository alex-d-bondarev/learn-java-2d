package org.abondarev.visual.states;

import org.abondarev.visual.Game;
import org.abondarev.visual.entities.creatures.Player;
import org.abondarev.visual.tiles.Tile;

import java.awt.*;

public class GameState extends State{

    private Player player;

    public GameState(Game game){
        super(game);
        player = new Player(game,100, 100);
    }

    public void tick() {
        player.tick();
    }

    public void render(Graphics g) {
        player.render(g);
        Tile.tiles[2].render(g, 0, 0);
    }
}
