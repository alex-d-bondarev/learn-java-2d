package org.abondarev.visual.states;

import org.abondarev.visual.Game;
import org.abondarev.visual.Handler;

import java.awt.*;

public abstract class State {

    private static State currentState = null;

    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public static void setState(State state){
        currentState = state;
    }

    public static State getState(){
        return currentState;
    }
}
