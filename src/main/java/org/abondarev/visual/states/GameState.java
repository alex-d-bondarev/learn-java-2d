package org.abondarev.visual.states;

import org.abondarev.visual.Game;
import org.abondarev.visual.entities.creatures.Player;
import org.abondarev.visual.worlds.World;

import java.awt.*;

public class GameState extends State{

    private Player player;
    private World world;

    public GameState(Game game){
        super(game);
        player = new Player(game,100, 100);
        world = new World(game, "res/worlds/world2.txt");
    }

    public void tick() {
        world.tick();
        player.tick();
    }

    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }
}
