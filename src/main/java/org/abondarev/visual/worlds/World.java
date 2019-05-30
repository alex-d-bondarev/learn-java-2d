package org.abondarev.visual.worlds;

import org.abondarev.visual.Game;
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

    private Game game;
    private int width, height, spawnX, spawnY;
    private int[][] tiles;

    public World(Game game, String path){
        this.game = game;
        loadWorld(path);
    }

    public void tick(){

    }

    public void render(Graphics g){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                getTile(x, y).render(g,
                        (int) (x * Tile.TILEWIDTH - game.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHIGHT - game.getGameCamera().getyOffset()));
            }
        }
    }

    public Tile getTile(int x, int y){
        Tile t = Tile.tiles[this.tiles[x][y]];
        if(t == null)
            return Tile.dirtTile;
        return t;
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
}
