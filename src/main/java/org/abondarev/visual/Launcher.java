package org.abondarev.visual;

/**
 * See https://www.youtube.com/watch?v=dEKs-3GhVKQ&list=PLah6faXAgguMnTBs3JnEJY0shAc18XYQZ
 *
 */
public class Launcher{
    public static void main( String[] args )
    {
        Game game = new Game("Tile Game", 640, 480);
        game.start();
    }
}
