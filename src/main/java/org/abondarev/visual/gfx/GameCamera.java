package org.abondarev.visual.gfx;

import org.abondarev.visual.Handler;
import org.abondarev.visual.entities.Entity;
import org.abondarev.visual.tiles.Tile;

public class GameCamera {

    private Handler handler;
    private float xOffset,yOffset;

    public GameCamera(Handler handler, float xOffset, float yOffset){
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void removekBlankSpace(){
        if(xOffset < 0){
            xOffset = 0;
        } else if (xOffset > getRightBorder()){
            xOffset = getRightBorder();
        }

        if(yOffset < 0){
            yOffset = 0;
        } else if (yOffset > getBottomBorder()) {
            yOffset = getBottomBorder();
        }
    }

    private int getRightBorder(){
        return handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
    }

    private int getBottomBorder(){
        return handler.getWorld().getHeight() * Tile.TILEHIGHT - handler.getHeight();
    }

    public void centerOnEntity(Entity entity){
        xOffset = entity.getX() - handler.getWidth() / 2 + entity.getWidth() / 2;
        yOffset = entity.getY() - handler.getHeight() / 2 + entity.getHeight() / 2;

        removekBlankSpace();
    }

    public void move(float xAmount, float yAmount){
        xOffset += xAmount;
        yOffset += yAmount;

        removekBlankSpace();
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
