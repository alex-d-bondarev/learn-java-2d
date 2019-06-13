package org.abondarev.visual.states;

import org.abondarev.visual.Game;
import org.abondarev.visual.Handler;

import java.awt.*;

public class MenuState extends State{

    public MenuState(Handler handler){
        super(handler);
    }

    public void tick() {
        if(handler.getMouseManager().isLeftPressed() || handler.getMouseManager().isRightPressed()){
            State.setState(handler.getGame().gameState);
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 30, 30);
    }
}
