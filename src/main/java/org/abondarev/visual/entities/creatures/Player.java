package org.abondarev.visual.entities.creatures;

import org.abondarev.visual.Handler;
import org.abondarev.visual.gfx.Animation;
import org.abondarev.visual.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature{

    private Animation downAnimation, upAnimation, leftAnimation, rightAnimation;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        downAnimation = new Animation(500, Assets.playerDown);
        upAnimation = new Animation(500, Assets.playerUp);
        leftAnimation = new Animation(500, Assets.playerLeft);
        rightAnimation = new Animation(500, Assets.playerRight);
    }

    public void tick() {
        downAnimation.tick();
        upAnimation.tick();
        leftAnimation.tick();
        rightAnimation.tick();

        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }

    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(),
                (int) (x - handler.getGameCamera().getXOffset()),
                (int) (y - handler.getGameCamera().getYOffset()),
                width,
                height,
                null);

//        showCollisionBoundary(g);
    }

    private void showCollisionBoundary(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getXOffset()),
                (int) (y + bounds.y - handler.getGameCamera().getYOffset()),
                bounds.width,
                bounds.height);
    }

    private BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0){
            return leftAnimation.getCurrentFrame();
        } else if (xMove > 0) {
            return rightAnimation.getCurrentFrame();
        } else if (yMove < 0) {
            return upAnimation.getCurrentFrame();
        } else {
            return downAnimation.getCurrentFrame();
        }
    }
}
