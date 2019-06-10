package org.abondarev.visual.states;

import org.abondarev.visual.Handler;
import org.abondarev.visual.worlds.World;

import java.awt.*;

public class GameState extends State{

    private World world;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world2.txt");

        handler.setWorld(world);
    }

    public void tick() {
        world.tick();
    }

    public void render(Graphics g) {
        world.render(g);
    }
}
