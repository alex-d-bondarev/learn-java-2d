package org.abondarev.visual.input;

import org.abondarev.visual.ui.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    private UIManager uiManager;

    public MouseManager(){

    }

    public void setUiManager(UIManager uiManager){
        this.uiManager = uiManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        updateMousePressedWith(e, true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        updateMousePressedWith(e, false);

        if(uiManager != null) {
            uiManager.onMouseRelease(e);
        }
    }

    private void updateMousePressedWith(MouseEvent e, boolean newState){
        if (e.getButton() == MouseEvent.BUTTON1){
            leftPressed = newState;
        } else if (e.getButton() == MouseEvent.BUTTON3){
            rightPressed = newState;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if(uiManager != null) {
            uiManager.onMouseMove(e);
        }
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
