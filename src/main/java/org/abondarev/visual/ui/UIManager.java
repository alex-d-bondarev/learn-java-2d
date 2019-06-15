package org.abondarev.visual.ui;

import org.abondarev.visual.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class UIManager {

    private Handler handler;
    private List<UIObject> objects;

    public UIManager(Handler handler) {
        this.handler = handler;
        objects = new ArrayList<UIObject>();
    }

    public void tick(){
        objects.forEach(UIObject::tick);
    }

    public void render(Graphics g){
        objects.forEach(o -> o.render(g));
    }

    public void onMouseMove(MouseEvent e){
        objects.forEach(o -> o.onMouseMove(e));
    }

    public void onMouseRelease(MouseEvent e){
        objects.forEach(o -> o.onMouseRelease(e));
    }

    public void addObject(UIObject o){
        objects.add(o);
    }

    public void removeObject(UIObject o){
        objects.remove(o);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public List<UIObject> getObjects() {
        return objects;
    }

    public void setObjects(List<UIObject> objects) {
        this.objects = objects;
    }
}

