package org.abondarev.visual.entities.creatures;

import org.abondarev.visual.Handler;
import org.abondarev.visual.entities.Entity;
import org.abondarev.visual.gfx.Animation;
import org.abondarev.visual.gfx.Assets;
import org.abondarev.visual.inventory.Inventory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature{

    private Animation downAnimation, upAnimation, leftAnimation, rightAnimation;
    private long lastAttackTimer, attackCoolDown = 250, attackTimer = attackCoolDown;
    private Inventory inventory;

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

        inventory = new Inventory(handler);
    }

    @Override
    public void die() {
        System.out.println("You lose!");
    }

    public void tick() {
        downAnimation.tick();
        upAnimation.tick();
        leftAnimation.tick();
        rightAnimation.tick();

        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);

        checkAttacks();

        inventory.tick();
    }

    private void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if(attackTimer < attackCoolDown){
            return;
        }

        Rectangle collisionBounds = getCollisionBounds(0f, 0f);
        Rectangle attackBounds = new Rectangle();
        int attackRange = 20;
        attackBounds.width = attackRange;
        attackBounds.height = attackRange;

        if(handler.getKeyManager().attackUp){
            attackBounds.x = collisionBounds.x + collisionBounds.width/2 - attackRange/2;
            attackBounds.y = collisionBounds.y - attackRange;
        } else if(handler.getKeyManager().attackDown){
            attackBounds.x = collisionBounds.x + collisionBounds.width/2 - attackRange/2;
            attackBounds.y = collisionBounds.y + collisionBounds.height;
        } else if(handler.getKeyManager().attackLeft){
            attackBounds.x = collisionBounds.x - attackRange;
            attackBounds.y = collisionBounds.y + collisionBounds.height/2 - attackRange/2;
        } else if(handler.getKeyManager().attackRight){
            attackBounds.x = collisionBounds.x + collisionBounds.width;
            attackBounds.y = collisionBounds.y + collisionBounds.height/2 - attackRange/2;
        } else {
            return;
        }

        attackTimer = 0;

        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)){
                continue;
            } else if (e.getCollisionBounds(0f, 0f).intersects(attackBounds)){
                e.hurt(1);
                return;
            }
        }
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

        inventory.render(g);

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

    public Inventory getInventory() {
        return inventory;
    }
}
