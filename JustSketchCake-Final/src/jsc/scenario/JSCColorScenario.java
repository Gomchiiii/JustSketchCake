package jsc.scenario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import jsc.JSC;
import jsc.JSCScene;
import jsc.cmd.JSCCmdToChangeIcingColor;
import jsc.cmd.JSCCmdToRecalSaturationForColorCells;
import x.XApp;
import x.XCmdToChangeScene;
import x.XScenario;

public class JSCColorScenario extends XScenario {
    // singleton pattern
    private static JSCColorScenario mSingleton = null;
    public static JSCColorScenario getSingleton() {
        assert(JSCColorScenario.mSingleton != null);
        return JSCColorScenario.mSingleton;
    }
    public static JSCColorScenario createSingleton(XApp app) {
        assert(JSCColorScenario.mSingleton == null);
        JSCColorScenario.mSingleton = new JSCColorScenario(app);
        return JSCColorScenario.mSingleton;
    }
    private JSCColorScenario(XApp app) {
        super(app);
    }
    
    @Override
    protected void addScenes() {
        this.addScene(JSCColorScenario.
            ColorReadyScene.createSingleton(this));
        this.addScene(JSCColorScenario.
            ChangeColorScene.createSingleton(this));
        this.addScene(JSCColorScenario.
            ChangeSaturationScene.createSingleton(this));
    }

    public static class ColorReadyScene extends JSCScene {
        // singleton pattern
        private static ColorReadyScene mSingelton = null;
        public static ColorReadyScene getSingleton() {
            assert(ColorReadyScene.mSingelton != null);
            return ColorReadyScene.mSingelton;
        }
        public static ColorReadyScene createSingleton(XScenario scenario) {
            assert(ColorReadyScene.mSingelton == null);
            ColorReadyScene.mSingelton = new ColorReadyScene(scenario);
            return ColorReadyScene.mSingelton;
        }
        private ColorReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            JSCColorScenario scenario = (JSCColorScenario) this.mScenario;
            JSC jsc = (JSC) scenario.getApp();
            Point p = e.getPoint();
            int h = jsc.getCanvas2D().getHeight();
            if (h /3 < p.y && p.y < h * 2 / 3) {
                XCmdToChangeScene.execute(jsc, 
                JSCColorScenario.ChangeColorScene.getSingleton(), 
                this.mReturnScene);
            } else {
                XCmdToChangeScene.execute(jsc, JSCColorScenario.
                    ChangeSaturationScene.getSingleton(), 
                    this.mReturnScene);
                scenario.mStartCalForSaturation = p;
                scenario.mStartSaturation = 
                    jsc.getColorChooser().getSaturation();
            } 
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
            JSC jsc = (JSC) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_C:
                    XCmdToChangeScene.execute(jsc, this.mReturnScene, 
                        null);
                    break;
            }
        }

        @Override
        public void updateSupportObjects() {
        
        }

        @Override
        public void renderWorldObjects(Graphics2D g2) {

        }

        @Override
        public void renderScreenObjects(Graphics2D g2) {
            JSCColorScenario scenario = (JSCColorScenario) this.mScenario;
            scenario.drawColorChooser(g2);
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
    public static class ChangeColorScene extends JSCScene {
        // singleton pattern
        private static ChangeColorScene mSingelton = null;
        public static ChangeColorScene getSingleton() {
            assert(ChangeColorScene.mSingelton != null);
            return ChangeColorScene.mSingelton;
        }
        public static ChangeColorScene createSingleton(XScenario scenario) {
            assert(ChangeColorScene.mSingelton == null);
            ChangeColorScene.mSingelton = new ChangeColorScene(scenario);
            return ChangeColorScene.mSingelton;
        }
        private ChangeColorScene(XScenario scenario) {
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
            JSC jsc = (JSC) this.mScenario.getApp();
            Point pt = e.getPoint();
            Color c = jsc.getColorChooser().calcColor(
                pt, jsc.getCanvas2D().getWidth(), 
                jsc.getCanvas2D().getHeight());
            JSCCmdToChangeIcingColor.execute(jsc, c);
            XCmdToChangeScene.execute(
                jsc, this.mReturnScene, null);
        }

        @Override
        public void handleKeyDown(KeyEvent e) {

        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_C:
                    XCmdToChangeScene.execute(jsc, this.mReturnScene, 
                        null);
                    break;
            }
        }

        @Override
        public void updateSupportObjects() {
        
        }

        @Override
        public void renderWorldObjects(Graphics2D g2) {

        }

        @Override
        public void renderScreenObjects(Graphics2D g2) {
            JSCColorScenario scenario = (JSCColorScenario) this.mScenario;
            scenario.drawColorChooser(g2);
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
    public static class ChangeSaturationScene extends JSCScene {
        // singleton pattern
        private static ChangeSaturationScene mSingelton = null;
        public static ChangeSaturationScene getSingleton() {
            assert(ChangeSaturationScene.mSingelton != null);
            return ChangeSaturationScene.mSingelton;
        }
        public static ChangeSaturationScene createSingleton(
            XScenario scenario) {
            assert(ChangeSaturationScene.mSingelton == null);
            ChangeSaturationScene.mSingelton = 
                new ChangeSaturationScene(scenario);
            return ChangeSaturationScene.mSingelton;
        }
        private ChangeSaturationScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {

        }

        @Override
        public void handleMouseDrag(MouseEvent e) {   
            JSC jsc = (JSC) this.mScenario.getApp();
            Point p = e.getPoint();
            JSCCmdToRecalSaturationForColorCells.execute(jsc, p);
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            XCmdToChangeScene.execute(jsc, JSCColorScenario.
                ColorReadyScene.getSingleton(), this.mReturnScene);
        }

        @Override
        public void handleKeyDown(KeyEvent e) {

        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_C:
                    XCmdToChangeScene.execute(jsc, this.mReturnScene, 
                        null);
                    break;
            }
        }

        @Override
        public void updateSupportObjects() {
        
        }

        @Override
        public void renderWorldObjects(Graphics2D g2) {

        }

        @Override
        public void renderScreenObjects(Graphics2D g2) {
            JSCColorScenario scenario = (JSCColorScenario) this.mScenario;
            scenario.drawColorChooser(g2);
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
    
    private void drawColorChooser(Graphics2D g2) {
        JSC jsc = (JSC) this.mApp;
        jsc.getColorChooser().drawCells(g2, jsc.getCanvas2D().getWidth(), 
            jsc.getCanvas2D().getHeight());
    }
    
    // fields
    private Point mStartCalForSaturation = null;
    public Point getStartCalForSaturation() {
        return this.mStartCalForSaturation;
    }
    private float mStartSaturation = Float.NaN;
    public float getStartSaturation() {
        return this.mStartSaturation;
    }
}
