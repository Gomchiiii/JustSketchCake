package jsc.scenario;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import jsc.JSCScene;
import x.XApp;
import x.XScenario;

public class JSCEmptyScenario extends XScenario {
    // singleton pattern
    private static JSCEmptyScenario mSingleton = null;
    public static JSCEmptyScenario getSingleton() {
        assert(JSCEmptyScenario.mSingleton != null);
        return JSCEmptyScenario.mSingleton;
    }
    public static JSCEmptyScenario createSingleton(XApp app) {
        assert(JSCEmptyScenario.mSingleton == null);
        JSCEmptyScenario.mSingleton = new JSCEmptyScenario(app);
        return JSCEmptyScenario.mSingleton;
    }
    private JSCEmptyScenario(XApp app) {
        super(app);
    }
    
    @Override
    protected void addScenes() {
        this.addScene(
            JSCEmptyScenario.EmptyScene.createSingleton(this));
    }

    public static class EmptyScene extends JSCScene {
        // singleton pattern
        private static EmptyScene mSingelton = null;
        public static EmptyScene getSingleton() {
            assert(EmptyScene.mSingelton != null);
            return EmptyScene.mSingelton;
        }
        public static EmptyScene createSingleton(XScenario scenario) {
            assert(EmptyScene.mSingelton == null);
            EmptyScene.mSingelton = new EmptyScene(scenario);
            return EmptyScene.mSingelton;
        }
        private EmptyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {

        }

        @Override
        public void handleMouseDrag(MouseEvent e) {       
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
        }

        @Override
        public void handleKeyDown(KeyEvent e) {

        }

        @Override
        public void handleKeyUp(KeyEvent e) {

        }

        @Override
        public void updateSupportObjects() {
        
        }

        @Override
        public void renderWorldObjects(Graphics2D g2) {

        }

        @Override
        public void renderScreenObjects(Graphics2D g2) {
        
        }

        @Override
        public void getReady() {
        
        }    
        @Override
        public void wrapUp() {
        
        }

        @Override
        public void handleMouseScroll(MouseWheelEvent e) {
        }

        @Override
        public void handleMouseMove(MouseEvent e) {
        }
    }
}
