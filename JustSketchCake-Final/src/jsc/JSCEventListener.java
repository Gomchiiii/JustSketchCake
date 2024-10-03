package jsc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class JSCEventListener implements MouseListener, MouseMotionListener,
    MouseWheelListener, KeyListener{

    // fields
    private JSC mJSC = null;
    
    // constructor
    public JSCEventListener(JSC jsc) {
        this.mJSC = jsc;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        JSCScene curScene = (JSCScene) this.mJSC.getScenarioMgr().getCurScene();
        curScene.handleMousePress(e);
        this.mJSC.getCanvas2D().repaint();
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        JSCScene curScene = (JSCScene) this.mJSC.getScenarioMgr().getCurScene();
        curScene.handleMouseDrag(e);
        this.mJSC.getCanvas2D().repaint();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        JSCScene curScene = (JSCScene) this.mJSC.getScenarioMgr().getCurScene();
        curScene.handleMouseRelease(e);
        this.mJSC.getCanvas2D().repaint();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        JSCScene curScene = (JSCScene) this.mJSC.getScenarioMgr().getCurScene();
        curScene.handleKeyDown(e);
        this.mJSC.getCanvas2D().repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        JSCScene curScene = (JSCScene) this.mJSC.getScenarioMgr().getCurScene();
        curScene.handleKeyUp(e);
        this.mJSC.getCanvas2D().repaint();
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        JSCScene curScene = (JSCScene) this.mJSC.getScenarioMgr().getCurScene();
        curScene.handleMouseMove(e);
        this.mJSC.getCanvas2D().repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        JSCScene curScene = (JSCScene) this.mJSC.getScenarioMgr().getCurScene();
        curScene.handleMouseScroll(e);
        this.mJSC.getCanvas2D().repaint();
    }

    
}
