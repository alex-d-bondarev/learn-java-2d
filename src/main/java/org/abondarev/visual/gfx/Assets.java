package org.abondarev.visual.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int height = 16, width = 16;

    public static BufferedImage player, dirt, grass, stone, tree;

    public static void init(){

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/OracleOfAges.gif"));

        player = sheet.crop(16*width-3, 5, width, height);
        dirt = sheet.crop(35*width, height+10, width, height);
        grass = sheet.crop(36*width+5, 4*height+4, width, height);
        tree = sheet.crop(37*width+7, 3*height-4, width, height);
        stone = sheet.crop(38*width+7, 7, width, height);

    }
}
