package jsc.scenario;

import Scalr.Scalr;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import jsc.JSC;
import jsc.JSCCanvas2D;
import jsc.JSCScene;
import jsc.cmd.JSCCmdToAddCurUpperIcingPtCurveToUpperIcingPtCurves;
import jsc.cmd.JSCCmdToCreateCurUpperIcingPtCurve;
import jsc.cmd.JSCCmdToExport;
import jsc.cmd.JSCCmdToMoveDeco;
import jsc.cmd.JSCCmdToMoveIcing;
import jsc.cmd.JSCCmdToPickUpDeco;
import jsc.cmd.JSCCmdToPickUpIcing;
import jsc.cmd.JSCCmdToPutDecoOnCakeUpper;
import jsc.cmd.JSCCmdToRotateSpinningWheelWithKey;
import jsc.cmd.JSCCmdToScaleDeco;
import jsc.cmd.JSCCmdToUpdateUpperCurIcingPtCurve;
import x.XApp;
import x.XCmdToChangeScene;
import x.XScenario;

public class JSCSketchUpperScenario extends XScenario {
    // singleton pattern
    private static JSCSketchUpperScenario mSingleton = null;
    public static JSCSketchUpperScenario getSingleton() {
        assert(JSCSketchUpperScenario.mSingleton != null);
        return JSCSketchUpperScenario.mSingleton;
    }
    public static JSCSketchUpperScenario createSingleton(XApp app) {
        assert(JSCSketchUpperScenario.mSingleton == null);
        JSCSketchUpperScenario.mSingleton = new JSCSketchUpperScenario(app);
        return JSCSketchUpperScenario.mSingleton;
    }
    
    private JSCSketchUpperScenario(XApp app) {
        super(app);
    }
    
    @Override
    protected void addScenes() {
        this.addScene(JSCSketchUpperScenario.UpperIcingReadyScene.
            createSingleton(this));
        this.addScene(JSCSketchUpperScenario.UpperIcingDrawScene.
            createSingleton(this));
        this.addScene(JSCSketchUpperScenario.UpperDecoScene.
            createSingleton(this));
    }

    public static class UpperIcingReadyScene extends JSCScene {
        // singleton pattern
        private static UpperIcingReadyScene mSingelton = null;
        public static UpperIcingReadyScene getSingleton() {
            assert(UpperIcingReadyScene.mSingelton != null);
            return UpperIcingReadyScene.mSingelton;
        }
        public static UpperIcingReadyScene createSingleton(XScenario scenario) {
            assert(UpperIcingReadyScene.mSingelton == null);
            UpperIcingReadyScene.mSingelton = 
                new UpperIcingReadyScene(scenario);
            return UpperIcingReadyScene.mSingelton;
        }
        private UpperIcingReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            JSCCanvas2D canvas = jsc.getCanvas2D();
            Point pt = e.getPoint();
            Point translatedPt = new Point(pt.x - 118, pt.y - 60);
            if (canvas.ICING_BOX.contains(pt)) {
                JSCCmdToPickUpIcing.execute(jsc, pt);
            } else if (canvas.DECO_BOX.contains(pt)) {
                XCmdToChangeScene.execute(jsc, 
                    JSCSketchUpperScenario.UpperDecoScene.
                    getSingleton(), null);
                JSCCmdToPickUpDeco.execute(jsc,pt);
            } else if (jsc.getJSCCakeUpperMgr().getUpperSurfaceShape().
                contains(translatedPt)) {
                XCmdToChangeScene.execute(jsc, JSCSketchUpperScenario.
                    UpperIcingDrawScene.getSingleton(), this);
                    JSCCmdToCreateCurUpperIcingPtCurve.execute(jsc, pt);
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
                case KeyEvent.VK_C:
                    XCmdToChangeScene.execute(jsc, JSCColorScenario.
                        ColorReadyScene.getSingleton(), this);
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
                    XCmdToChangeScene.execute(
                        jsc, JSCSketchSideScenario.SideIcingReadyScene.
                        getSingleton(), null);
                    break;
                case KeyEvent.VK_ESCAPE:
                    XCmdToChangeScene.execute(jsc, JSCDefaultScenario.
                        CakeUpperReadyScene.getSingleton(), null);
                    JSCCmdToPickUpIcing.execute(jsc,null);
                    break;
                case KeyEvent.VK_ENTER:
                    JSCCmdToExport.execute(jsc);
                    break;
            }
        }
        
        @Override
        public void handleMouseScroll(MouseWheelEvent e) {
        
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
            JSCSketchUpperScenario scenario = 
                (JSCSketchUpperScenario) this.mScenario;
            scenario.drawIcingCursor(g2);
        }

        @Override
        public void getReady() {
        
        }    
        @Override
        public void wrapUp() {
        
        }        

        @Override
        public void handleMouseMove(MouseEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            Point pt = e.getPoint();
            JSCCmdToMoveIcing.execute(jsc, pt);
        }
    }
       
    public static class UpperIcingDrawScene extends JSCScene {
        // singleton pattern
        private static UpperIcingDrawScene mSingelton = null;
        public static UpperIcingDrawScene getSingleton() {
            assert(UpperIcingDrawScene.mSingelton != null);
            return UpperIcingDrawScene.mSingelton;
        }
        public static UpperIcingDrawScene createSingleton(XScenario scenario) {
            assert(UpperIcingDrawScene.mSingelton == null);
            UpperIcingDrawScene.mSingelton = new UpperIcingDrawScene(scenario);
            return UpperIcingDrawScene.mSingelton;
        }
        private UpperIcingDrawScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {

        }

        @Override
        public void handleMouseDrag(MouseEvent e) {      
            JSC jsc = (JSC) this.mScenario.getApp();
            Point pt = e.getPoint();
            Point translatedPt = new Point(pt.x - 118, pt.y - 60);
            if (jsc.getJSCCakeUpperMgr().getUpperSurfaceShape().
                contains(translatedPt)) {
                JSCCmdToUpdateUpperCurIcingPtCurve.execute(jsc, pt);
            } else {
                JSCCmdToAddCurUpperIcingPtCurveToUpperIcingPtCurves.
                    execute(jsc);
                XCmdToChangeScene.execute(jsc, JSCSketchUpperScenario.
                    UpperIcingReadyScene.getSingleton(), null);
            }
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            JSCCmdToAddCurUpperIcingPtCurveToUpperIcingPtCurves.execute(jsc);
            XCmdToChangeScene.execute(jsc, 
                this.mReturnScene, null);
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
            }
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
            JSC jsc = (JSC) this.mScenario.getApp();
            jsc.getJSCCakeUpperMgr().sketchUpperSurface();
            jsc.getJSCCakeUpperMgr().UpperRotateAnimationPreset();
            g2.drawImage(jsc.getImageRenderer().drawImageForUpperScene(), 
                118, 60, jsc.getCanvas2D());
            JSCSketchUpperScenario scenario = 
                (JSCSketchUpperScenario) this.mScenario;
            scenario.drawIcingCursor(g2);
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
    
    public static class UpperDecoScene extends JSCScene {
        // singleton pattern
        private static UpperDecoScene mSingelton = null;
        public static UpperDecoScene getSingleton() {
            assert(UpperDecoScene.mSingelton != null);
            return UpperDecoScene.mSingelton;
        }
        public static UpperDecoScene createSingleton(XScenario scenario) {
            assert(UpperDecoScene.mSingelton == null);
            UpperDecoScene.mSingelton = new UpperDecoScene(scenario);
            return UpperDecoScene.mSingelton;
        }
        private UpperDecoScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            JSCCanvas2D canvas = jsc.getCanvas2D();
            Point pt = e.getPoint();
            Point translatedPt = new Point(pt.x - 118, pt.y - 60);
            if (canvas.ICING_BOX.contains(pt)) {
                XCmdToChangeScene.execute(jsc, JSCSketchUpperScenario.
                    UpperIcingReadyScene.getSingleton(), null);
                JSCCmdToPickUpIcing.execute(jsc, pt);
            } else if (canvas.DECO_BOX.contains(pt)) {
                JSCCmdToPickUpDeco.execute(jsc,pt);
            } else if (jsc.getJSCCakeUpperMgr().getUpperSurfaceShape().
                contains(translatedPt)) {
                JSCCmdToPutDecoOnCakeUpper.execute(jsc, pt);
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
                    XCmdToChangeScene.execute(
                        jsc, JSCSketchSideScenario.SideDecoScene.
                        getSingleton(), null);
                    break;
                case KeyEvent.VK_ESCAPE:
                    XCmdToChangeScene.execute(jsc, JSCDefaultScenario.
                        CakeUpperReadyScene.getSingleton(), null);
                    JSCCmdToPickUpDeco.execute(jsc, null);
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
            JSCSketchUpperScenario scenario = 
                (JSCSketchUpperScenario) this.mScenario;
            scenario.drawDecoCursor(g2);
        }

        @Override
        public void getReady() {
        
        }    
        @Override
        public void wrapUp() {
        
        }

        @Override
        public void handleMouseScroll(MouseWheelEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            int rotation = e.getWheelRotation();
            JSCCmdToScaleDeco.execute(jsc, rotation);
        }

        @Override
        public void handleMouseMove(MouseEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            Point pt = e.getPoint();
            JSCCmdToMoveDeco.execute(jsc, pt);
        }
    }
    
    
    public void drawDecoCursor(Graphics2D g2) {
        JSC jsc = (JSC) this.mApp;
        int scale = jsc.getCursorMgr().getCurCursorScale();
        BufferedImage image = Scalr.resize(jsc.getCursorMgr().getCurCursor(), 
            Scalr.Method.BALANCED, scale, scale);
        g2.drawImage(image, 
            jsc.getCursorMgr().getCurCursorPt().x - scale / 2, 
            jsc.getCursorMgr().getCurCursorPt().y - scale / 2, 
            jsc.getCanvas2D());
    }
    
    public void drawIcingCursor(Graphics2D g2) {
        JSC jsc = (JSC) this.mApp;
        g2.drawImage(jsc.getCursorMgr().getCurCursor(), 
            jsc.getCursorMgr().getCurCursorPt().x, 
            jsc.getCursorMgr().getCurCursorPt().y - 60, 
            jsc.getCanvas2D());
    }
}
