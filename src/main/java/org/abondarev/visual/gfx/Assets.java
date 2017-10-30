package org.abondarev.visual.gfx;

import net.coobird.thumbnailator.Thumbnails;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {

    private static final int height = 16, width = 16;

    public static BufferedImage player, dirt, grass, stone, tree;

    public static void init(){

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/OracleOfAges.gif"));

        player = getScaledImageOf(sheet.crop(16*width-3, 5, width, height));
        dirt = getScaledImageOf(sheet.crop(35*width, height+10, width, height));
        grass = getScaledImageOf(sheet.crop(36*width+5, 4*height+4, width, height));
        tree = getScaledImageOf(sheet.crop(37*width+7, 3*height-4, width, height));
        stone = getScaledImageOf(sheet.crop(38*width+7, 7, width, height));



    }

    private static BufferedImage getScaledImageOf(BufferedImage img){
        try {
            return Thumbnails.of(img).size(2*width, 2*height).asBufferedImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
