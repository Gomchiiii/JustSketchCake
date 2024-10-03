package jsc.scenario;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import jsc.JSC;
import jsc.JSCCanvas2D;
import jsc.JSCScene;
import jsc.cmd.JSCCmdToExport;
import jsc.cmd.JSCCmdToPickUpDeco;
import jsc.cmd.JSCCmdToPickUpIcing;
import jsc.cmd.JSCCmdToRotateSpinningWheelWithKey;
import x.XApp;
import x.XCmdToChangeScene;
import x.XScenario;

public class JSCDefaultScenario extends XScenario {
    // singleton pattern
    private static JSCDefaultScenario mSingleton = null;
    public static JSCDefaultScenario getSingleton() {
        assert(JSCDefaultScenario.mSingleton != null);
        return JSCDefaultScenario.mSingleton;
    }
    public static JSCDefaultScenario createSingleton(XApp app) {
        assert(JSCDefaultScenario.mSingleton == null);
        JSCDefaultScenario.mSingleton = new JSCDefaultScenario(app);
        return JSCDefaultScenario.mSingleton;
    }
    private JSCDefaultScenario(XApp app) {
        super(app);
    }
    
    @Override
    protected void addScenes() {
        this.addScene(JSCDefaultScenario.CakeUpperReadyScene.
            createSingleton(this));
        this.addScene(JSCDefaultScenario.CakeSideReadyScene.
            createSingleton(this));
    }

    public static class CakeUpperReadyScene extends JSCScene {
        // singleton pattern
        private static CakeUpperReadyScene mSingelton = null;
        public static CakeUpperReadyScene getSingleton() {
            assert(CakeUpperReadyScene.mSingelton != null);
            return CakeUpperReadyScene.mSingelton;
        }
        public static CakeUpperReadyScene createSingleton(
            XScenario scenario) {
            assert(CakeUpperReadyScene.mSingelton == null);
            CakeUpperReadyScene.mSingelton = 
                new CakeUpperReadyScene(scenario);
            return CakeUpperReadyScene.mSingelton;
        }
        private CakeUpperReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            JSCCanvas2D canvas = jsc.getCanvas2D();
            Point pt = e.getPoint();
            if (canvas.ICING_BOX.contains(pt)) {
                XCmdToChangeScene.execute(jsc, JSCSketchUpperScenario.
                    UpperIcingReadyScene.getSingleton(), null);
                JSCCmdToPickUpIcing.execute(jsc, pt);
            } else if (canvas.DECO_BOX.contains(pt)) {
                XCmdToChangeScene.execute(jsc, 
                    JSCSketchUpperScenario.UpperDecoScene.
                    getSingleton(), null);
                JSCCmdToPickUpDeco.execute(jsc,pt);
            }
//            else if (jsc.getImageRenderer().contains(pt)) {
//                
//            } else {
//                
//            }
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {       
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
        }

        @Override
        public void handleKeyDown(KeyEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_LEFT:
                    JSCCmdToRotateSpinningWheelWithKey.execute(
                        jsc, true);
                    break;
                case KeyEvent.VK_RIGHT:
                    JSCCmdToRotateSpinningWheelWithKey.execute(
                        jsc, false);
                    break;
                case KeyEvent.VK_H:
                    XCmdToChangeScene.execute(jsc, JSCHelpScenario.
                        HelpScene.getSingleton(), this);
                    break;
            }
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_DOWN:
                    XCmdToChangeScene.execute(jsc, JSCDefaultScenario.
                        CakeSideReadyScene.getSingleton(), null);
                    break;
                case KeyEvent.VK_ENTER:
                    JSCCmdToExport.execute(jsc);
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
            JSC jsc = (JSC) this.mScenario.getApp();
            jsc.getJSCCakeUpperMgr().sketchUpperSurface();
            jsc.getJSCCakeUpperMgr().UpperRotateAnimationPreset();
            g2.drawImage(jsc.getImageRenderer().drawImageForUpperScene(), 
                118, 60, jsc.getCanvas2D());
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
    
    public static class CakeSideReadyScene extends JSCScene {
        // singleton pattern
        private static CakeSideReadyScene mSingelton = null;
        public static CakeSideReadyScene getSingleton() {
            assert(CakeSideReadyScene.mSingelton != null);
            return CakeSideReadyScene.mSingelton;
        }
        public static CakeSideReadyScene createSingleton(
            XScenario scenario) {
            assert(CakeSideReadyScene.mSingelton == null);
            CakeSideReadyScene.mSingelton = 
                new CakeSideReadyScene(scenario);
            return CakeSideReadyScene.mSingelton;
        }
        private CakeSideReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            JSCCanvas2D canvas = jsc.getCanvas2D();
            Point pt = e.getPoint();
            if (canvas.ICING_BOX.contains(pt)) {
                XCmdToChangeScene.execute(jsc, JSCSketchSideScenario.
                    SideIcingReadyScene.getSingleton(), null);
                JSCCmdToPickUpIcing.execute(jsc, pt);
            } else if (canvas.DECO_BOX.contains(pt)) {
                XCmdToChangeScene.execute(jsc, 
                    JSCSketchSideScenario.SideDecoScene.
                    getSingleton(), null);
                JSCCmdToPickUpDeco.execute(jsc,pt);
            }
//            else if (jsc.getImageRenderer().contains(pt)) {
//                
//            } else {
//                
//            }
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {       
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
        }

        @Override
        public void handleKeyDown(KeyEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_LEFT:
                    JSCCmdToRotateSpinningWheelWithKey.execute(
                        jsc, true);
                    break;
                case KeyEvent.VK_RIGHT:
                    JSCCmdToRotateSpinningWheelWithKey.execute(
                        jsc, false);
                    break;
                case KeyEvent.VK_H:
                    XCmdToChangeScene.execute(jsc, JSCHelpScenario.
                        HelpScene.getSingleton(), this);
                    break;
            }
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_UP:
                    XCmdToChangeScene.execute(jsc, JSCDefaultScenario.
                        CakeUpperReadyScene.getSingleton(), null);
                    break;
                case KeyEvent.VK_ENTER:
                    JSCCmdToExport.execute(jsc);
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
            JSC jsc = (JSC) this.mScenario.getApp();
            jsc.getJSCCakeUpperMgr().sketchUpperSurface();
            jsc.getJSCCakeSideMgr().sketchSideSurface();
            jsc.getJSCCakeUpperMgr().UpperRotateAnimationPreset();
            jsc.getJSCCakeSideMgr().SideRotateAnimationPreset();
            g2.drawImage(jsc.getImageRenderer().drawImageForSideScene(), 
                118, 60, jsc.getCanvas2D());
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
