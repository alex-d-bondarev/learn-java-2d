package org.abondarev.visual.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int HEIGHT = 16, WIDTH = 16;
    private static final int MENU_FIRST_COLUMN_PADDING = 8;
    private static final int MENU_THIRD_COLUMN_PADDING = 434;
    private static final int MENU_THIRD_LINE_PADDING = 192;
    public static final int MENU_BUTTON_WIDTH = 182;
    public static final int MENU_BUTTON_HEIGHT = 45;

    public static BufferedImage dirt, grass, stone, tree;
    public static BufferedImage[] playerDown, playerUp, playerLeft, playerRight;
    public static BufferedImage[] startGameButton;

    public static void init(){
        initZeldaAssets();
        initMenuAssets();
    }


    private static void initMenuAssets(){
        SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/MainMenuButtons.png"));

        startGameButton = new BufferedImage[2];
        startGameButton[0] = menuSheet.crop(MENU_THIRD_COLUMN_PADDING, MENU_THIRD_LINE_PADDING, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        startGameButton[1] = menuSheet.crop(MENU_FIRST_COLUMN_PADDING, MENU_THIRD_LINE_PADDING, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
    }

    private static void initZeldaAssets(){
        SpriteSheet oracleOfAgesSheet = new SpriteSheet(ImageLoader.loadImage("/textures/OracleOfAges-Transparent-background.png"));

        dirt = oracleOfAgesSheet.crop(35* WIDTH, HEIGHT +10, WIDTH, HEIGHT);
        grass = oracleOfAgesSheet.crop(36* WIDTH +5, 4* HEIGHT +4, WIDTH, HEIGHT);
        tree = oracleOfAgesSheet.crop(35* WIDTH +3, 6* HEIGHT -5, WIDTH *2, HEIGHT *2);
        stone = oracleOfAgesSheet.crop(38* WIDTH +7, 7, WIDTH, HEIGHT);

        playerDown = new BufferedImage[2];
        playerDown[0] = oracleOfAgesSheet.crop(16* WIDTH -2, 5, WIDTH, HEIGHT);
        playerDown[1] = oracleOfAgesSheet.crop(15* WIDTH -2, HEIGHT +6, WIDTH, HEIGHT);

        playerUp = new BufferedImage[2];
        playerUp[0] = oracleOfAgesSheet.crop(16* WIDTH -2, HEIGHT +6, WIDTH, HEIGHT);
        playerUp[1] = oracleOfAgesSheet.crop(17* WIDTH -2, HEIGHT +6, WIDTH, HEIGHT);

        playerLeft = new BufferedImage[2];
        playerLeft[0] = oracleOfAgesSheet.crop(15* WIDTH -4, 4, WIDTH, HEIGHT);
        playerLeft[1] = oracleOfAgesSheet.crop(18* WIDTH -2, 5, WIDTH, HEIGHT);

        playerRight = new BufferedImage[2];
        playerRight[0] = oracleOfAgesSheet.crop(17* WIDTH -2, 5, WIDTH, HEIGHT);
        playerRight[1] = oracleOfAgesSheet.crop(19* WIDTH -2, 5, WIDTH, HEIGHT);
    }
}
