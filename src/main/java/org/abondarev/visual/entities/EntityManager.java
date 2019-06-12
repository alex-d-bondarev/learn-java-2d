package org.abondarev.visual.entities;

import org.abondarev.visual.Handler;
import org.abondarev.visual.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;

public class EntityManager {

    private Handler handler;
    private Player player;

    private ArrayList<Entity> entities;

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;

        entities = new ArrayList<Entity>();
        entities.add(player);
    }

    public void tick(){
        entities.forEach(Entity::tick);
    }

    public void render(Graphics g){
        entities.forEach(entity -> {
            entity.render(g);
        });
    }

    public void addEntity(Entity newEntity){
        entities.add(newEntity);
    }


    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
