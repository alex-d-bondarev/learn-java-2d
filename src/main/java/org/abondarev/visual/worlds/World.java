package org.abondarev.visual.worlds;

import org.abondarev.visual.Handler;
import org.abondarev.visual.entities.EntityManager;
import org.abondarev.visual.entities.creatures.Player;
import org.abondarev.visual.entities.statics.BigTree;
import org.abondarev.visual.entities.statics.Rock;
import org.abondarev.visual.items.ItemManager;
import org.abondarev.visual.tiles.Tile;
import org.abondarev.visual.utils.Utils;

import java.awt.*;

public class World {
    private static final int NUMBER_OF_RESERVED_FILE_VALUES = 4;
    private static final int RESERVED_WIDTH_VALUE = 0;
    private static final int RESERVED_HEIGHT_VALUE = 1;
    private static final int RESERVED_SPAWN_X_VALUE = 2;
    private static final int RESERVED_SPAWN_Y_VALUE = 3;

    private static final String ALL_DELIMETERS = "\\s+";

    private EntityManager entityManager;
    private ItemManager itemManager;

    private Handler handler;
    private int width, height, spawnX, spawnY;
    private int[][] tiles;

    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        entityManager.addEntity(new BigTree(handler, 100, 300));
        entityManager.addEntity(new Rock(handler, 100, 200));

        itemManager = new ItemManager(handler);

        loadWorld(path);
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public void tick(){
        itemManager.tick();
        entityManager.tick();
    }

    public void render(Graphics g){
        int xStart = getScreenStartX();
        int xEnd = getScreenEndX();
        int yStart = getScreenStartY();
        int yEnd = getScreenEndY();

        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                getTile(x, y).render(g,
                        (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getXOffset()),
                        (int) (y * Tile.TILEHIGHT - handler.getGameCamera().getYOffset()));
            }
        }

        itemManager.render(g);

        entityManager.render(g);
    }

    private int getScreenStartX() {
        return (int) Math.max(0, handler.getGameCamera().getXOffset() / Tile.TILEWIDTH);
    }

    private int getScreenEndX() {
        return (int) Math.min(width, (handler.getGameCamera().getXOffset() + handler.getWidth()) /Tile.TILEWIDTH + 1 );
    }

    private int getScreenStartY(){
        return (int) Math.max(0, handler.getGameCamera().getYOffset() / Tile.TILEHIGHT);
    }

    private int getScreenEndY(){
        return (int) Math.min(height, (handler.getGameCamera().getYOffset() + handler.getHeight()) / Tile.TILEHIGHT + 1 );
    }

    public Tile getTile(int x, int y){
        if(isOutsideOfTheMap(x, y)){
            return Tile.grassTile;
        }

        Tile t = Tile.tiles[this.tiles[x][y]];
        if(t == null)
            return Tile.dirtTile;
        return t;
    }

    private boolean isOutsideOfTheMap(int x, int y){
        return x < 0 || y < 0 || x >= width || y >= height;
    }

    private void loadWorld(String path){
        String file = Utils.loadFileAsAString(path);
        String[] tokens = file.split(ALL_DELIMETERS);

        // NUMBER_OF_RESERVED_FILE_VALUES:
        width = Utils.parseInt(tokens[RESERVED_WIDTH_VALUE]);
        height = Utils.parseInt(tokens[RESERVED_HEIGHT_VALUE]);

        spawnX = Utils.parseInt(tokens[RESERVED_SPAWN_X_VALUE]);
        spawnY = Utils.parseInt(tokens[RESERVED_SPAWN_Y_VALUE]);


        tiles = new int[width][height];

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tiles[x][y] = getTileTokenValue(x, y, tokens);
            }
        }
    }

    private int getTileTokenValue(int x, int y, String[] tokens){
        return Utils.parseInt(tokens[(x + y * width) + NUMBER_OF_RESERVED_FILE_VALUES]);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }
}
