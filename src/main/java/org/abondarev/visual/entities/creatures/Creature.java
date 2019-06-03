package org.abondarev.visual.entities.creatures;

import org.abondarev.visual.Handler;
import org.abondarev.visual.entities.Entity;
import org.abondarev.visual.tiles.Tile;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 10
            , DEFAULT_CREATURE_WIDTH = 64
            , DEFAULT_CREATURE_HEIGHT = 64
            , COLLISION_BUFFER = 1;
    public static final float DEFAULT_SPEED = 3.0f;

    protected int health;
    protected float speed
            , xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move(){
        moveX();
        moveY();
    }

    private void moveX(){
        if(isMovingRight()) {
            int collisionX = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            tryHorizontalMove(collisionX);

        } else if (isMovingLeft()){
            int collisionX = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            tryHorizontalMove(collisionX);
        }
    }

    private boolean isMovingRight(){
        return xMove > 0;
    }

    private boolean isMovingLeft(){
        return xMove < 0;
    }

    private void tryHorizontalMove(int collisionX){
        if(!collisionWithTile(collisionX, (int) (y + bounds.y) / Tile.TILEHIGHT) &&
                !collisionWithTile(collisionX, (int) (y + bounds.y + bounds.height) / Tile.TILEHIGHT)){
            x += xMove;
        } else {
            x = horizontalMoveCloseToTile(collisionX);
        }
    }

    private int horizontalMoveCloseToTile(int collisionX){
        return isMovingRight() ?
                collisionX * Tile.TILEWIDTH - bounds.x - bounds.width - COLLISION_BUFFER:
                collisionX * Tile.TILEWIDTH + bounds.x + bounds.width;
    }

    private void moveY(){
        if(isMovingUp()) {
            int collisionY = (int) (y + yMove + bounds.y) / Tile.TILEHIGHT;
            tryVerticallMove(collisionY);

        } else if (isMovingDown()){
            int collisionY = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHIGHT;
            tryVerticallMove(collisionY);
        }
    }

    private boolean isMovingUp(){
        return yMove < 0;
    }

    private boolean isMovingDown(){
        return yMove > 0;
    }

    private void tryVerticallMove(int collisionY){
        if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, collisionY) &&
                !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, collisionY)){
            y += yMove;
        } else {
            y = verticalMoveCloseToTile(collisionY);
        }
    }

    private int verticalMoveCloseToTile(int collisionY){
        return isMovingUp() ?
                collisionY * Tile.TILEHIGHT + Tile.TILEHIGHT - bounds.y :
                collisionY * Tile.TILEHIGHT - bounds.y - bounds.height - COLLISION_BUFFER;
    }

    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }

    /////////////////////////
    // Getters and Setters //
    /////////////////////////

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
