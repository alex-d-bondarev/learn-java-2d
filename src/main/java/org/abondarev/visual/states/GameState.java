package org.abondarev.visual.states;

import org.abondarev.visual.Handler;
import org.abondarev.visual.entities.creatures.Player;
import org.abondarev.visual.worlds.World;

import java.awt.*;

public class GameState extends State{

    private Player player;
    private World world;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world2.txt");

        handler.setWorld(world);
        player = new Player(handler,100, 100);
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
