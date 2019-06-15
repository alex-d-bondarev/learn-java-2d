package org.abondarev.visual.states;

import org.abondarev.visual.Game;
import org.abondarev.visual.Handler;
import org.abondarev.visual.gfx.Assets;
import org.abondarev.visual.ui.ClickListener;
import org.abondarev.visual.ui.UIImageButton;
import org.abondarev.visual.ui.UIManager;

import java.awt.*;

public class MenuState extends State{

    private UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(200, 200,
                Assets.MENU_BUTTON_WIDTH,
                Assets.MENU_BUTTON_HEIGHT,
                Assets.startGameButton,
                new ClickListener() {
                    @Override
                    public void onClick() {
                        switchStateToGame();
                    }
                }));
    }

    public void tick() {
        uiManager.tick();

        // Turn menu off for testing
        switchStateToGame();
    }

    private void switchStateToGame(){
        handler.getMouseManager().setUiManager(null);
        State.setState(handler.getGame().gameState);
    }

    public void render(Graphics g) {
        uiManager.render(g);
    }
}
