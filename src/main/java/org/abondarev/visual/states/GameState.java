package org.abondarev.visual.states;

import org.abondarev.visual.Game;
import org.abondarev.visual.entities.creatures.Player;

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
    }
}
