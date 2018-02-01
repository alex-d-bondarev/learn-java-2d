package org.abondarev.visual.worlds;

import org.abondarev.visual.tiles.Tile;

import java.awt.*;

public class World {
    private int width, height;
    private int[][] tiles;

    public World(String path){
        loadWorld(path);
    }

    public void tick(){

    }

    public void render(Graphics g){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHIGHT);
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
        width = 5;
        height = 5;
        tiles = new int[width][height];

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                tiles[x][y] = 0;
            }
        }
    }
}
