package org.abondarev.visual;

import org.abondarev.visual.display.Display;
import org.abondarev.visual.gfx.Assets;
import org.abondarev.visual.gfx.GameCamera;
import org.abondarev.visual.input.KeyManager;
import org.abondarev.visual.input.MouseManager;
import org.abondarev.visual.states.GameState;
import org.abondarev.visual.states.MenuState;
import org.abondarev.visual.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private Display display;

    private int width, height;
    private boolean running = false;
    private String title;

    private BufferStrategy bs;
    private Graphics g;

    private Thread thread;

    public State gameState;
    public State menuState;

    private GameCamera gameCamera;
    private Handler handler;
    private KeyManager keyManager;
    private MouseManager mouseManager;

    public Game(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);

        // Hint:
        // One of JFrame or Canvas can be focused at a time,
        // so its better to add mouse listeners to both of them
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Assets.init();

        handler = new Handler(this);

        gameCamera = new GameCamera(handler,0,0);
        gameState = new GameState(handler);
        menuState = new MenuState(handler);

        State.setState(menuState);
    }

    private void tick(){
        keyManager.tick();
        if(State.getState() != null){
            State.getState().tick();
        }
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        if(State.getState() != null){
            State.getState().render(g);
        }

        bs.show();
        g.dispose();
    }

    public void run(){
        init();

        int fps = 60;
        long nanoSecond = 1000000000;
        double timePerTick = nanoSecond / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= nanoSecond){
//                System.out.println("FPS: " + ticks);
                ticks = 0;
                timer -= nanoSecond;
            }
        }

        stop();
    }

    public synchronized void start(){
        if(running) return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running) return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
