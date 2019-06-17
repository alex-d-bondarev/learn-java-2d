package org.abondarev.visual.inventory;

import org.abondarev.visual.Handler;
import org.abondarev.visual.gfx.Assets;
import org.abondarev.visual.gfx.Text;
import org.abondarev.visual.items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;

    private int invX = 64, invY = 48,
            invWidth = 512, invHeight = 384,
            invListCenterX = invX + 171,
            invListCenterY = invY + invHeight / 2 + 5,
            invListSpacing = 30;

    private int invImageX = 452, invImageY = 82,
            invImageWidth = 64, invImageHeight = 64;

    private int invCountX = 484, invCountY = 172;

    private int selectedItem = 0;

    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<>();
    }

    public void addItem(Item item){
        for(Item i : inventoryItems){
            if(i.getId() == item.getId()){
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }

    public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I)){
            active = !active;
        }

        if(!active){
            return;
        }

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)){
            selectedItem--;
        } else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)){
            selectedItem++;
        }

        int inventorySize = inventoryItems.size();

        if(selectedItem < 0){
            selectedItem = inventorySize -1;
        } else if (selectedItem >= inventorySize){
            selectedItem = 0;
        }
    }

    public void render(Graphics g){
        if(!active){
            return;
        }

        g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);

        int inventorySize = inventoryItems.size();
        if(inventorySize == 0){
            return;
        }

        for (int i = -5; i < 6; i++) {
            if(selectedItem + i < 0 || selectedItem + i >= inventorySize){
                continue;
            }

            if(i == 0){
                Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <",
                        invListCenterX, invListCenterY + i*invListSpacing,
                        true, Color.YELLOW, Assets.font28);
            } else {
                Text.drawString(g, inventoryItems.get(selectedItem + i).getName(),
                        invListCenterX, invListCenterY + i * invListSpacing,
                        true, Color.WHITE, Assets.font28);
            }
        }

        Item item = inventoryItems.get(selectedItem);
        g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
        Text.drawString(g, Integer.toString(item.getCount()),
                invCountX, invCountY,
                true, Color.WHITE, Assets.font28);
    }

    public boolean isActive() {
        return active;
    }
}
