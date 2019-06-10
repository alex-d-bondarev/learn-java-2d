package org.abondarev.visual.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int height = 16, width = 16;

    public static BufferedImage dirt, grass, stone, tree;
    public static BufferedImage[] playerDown, playerUp, playerLeft, playerRight;

    public static void init(){

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/OracleOfAges-Transparent-background.png"));

        dirt = sheet.crop(35*width, height+10, width, height);
        grass = sheet.crop(36*width+5, 4*height+4, width, height);
        tree = sheet.crop(37*width+7, 3*height-4, width, height);
        stone = sheet.crop(38*width+7, 7, width, height);

        playerDown = new BufferedImage[2];
        playerDown[0] = sheet.crop(16*width-2, 5, width, height);
        playerDown[1] = sheet.crop(15*width-2, height+6, width, height);

        playerUp = new BufferedImage[2];
        playerUp[0] = sheet.crop(16*width-2, height+6, width, height);
        playerUp[1] = sheet.crop(17*width-2, height+6, width, height);

        playerLeft = new BufferedImage[2];
        playerLeft[0] = sheet.crop(15*width-4, 4, width, height);
        playerLeft[1] = sheet.crop(18*width-2, 5, width, height);

        playerRight = new BufferedImage[2];
        playerRight[0] = sheet.crop(17*width-2, 5, width, height);
        playerRight[1] = sheet.crop(19*width-2, 5, width, height);

    }
}
