package org.abondarev.visual.items;

import org.abondarev.visual.Handler;

import java.awt.*;
import java.util.ArrayList;

public class ItemManager {

    private Handler handler;
    private ArrayList<Item> items;

    public ItemManager(Handler handler) {
        this.handler = handler;
        items = new ArrayList<>();
    }

    public void tick(){
        ArrayList<Item> tempItems = new ArrayList<Item>(items);

        tempItems.forEach(i -> {
            i.tick();

            if(i.isPickedUp()){
                items.remove(i);
            }
        });
    }

    public void render(Graphics g){
        items.forEach(i -> i.render(g));
    }

    public void addItem(Item i){
        i.setHandler(handler);
        items.add(i);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
