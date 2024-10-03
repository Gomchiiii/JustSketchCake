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
import jsc.cmd.JSCCmdToAddCurSideIcingPtCurveToSideIcingPtCurves;
import jsc.cmd.JSCCmdToCreateCurSideIcingPtCurve;
import jsc.cmd.JSCCmdToExport;
import jsc.cmd.JSCCmdToMoveDeco;
import jsc.cmd.JSCCmdToMoveIcing;
import jsc.cmd.JSCCmdToPickUpDeco;
import jsc.cmd.JSCCmdToPickUpIcing;
import jsc.cmd.JSCCmdToPutDecoOnCakeSide;
import jsc.cmd.JSCCmdToRotateSpinningWheelWithKey;
import jsc.cmd.JSCCmdToScaleDeco;
import jsc.cmd.JSCCmdToUpdateSideCurIcingPtCurve;
import x.XApp;
import x.XCmdToChangeScene;
import x.XScenario;

public class JSCSketchSideScenario extends XScenario {
    // singleton pattern
    private static JSCSketchSideScenario mSingleton = null;
    public static JSCSketchSideScenario getSingleton() {
        assert(JSCSketchSideScenario.mSingleton != null);
        return JSCSketchSideScenario.mSingleton;
    }
    public static JSCSketchSideScenario createSingleton(XApp app) {
        assert(JSCSketchSideScenario.mSingleton == null);
        JSCSketchSideScenario.mSingleton = new JSCSketchSideScenario(app);
        return JSCSketchSideScenario.mSingleton;
    }
    
    private JSCSketchSideScenario(XApp app) {
        super(app);
    }
    
    @Override
    protected void addScenes() {
        this.addScene(JSCSketchSideScenario.SideIcingReadyScene.
            createSingleton(this));
        this.addScene(JSCSketchSideScenario.SideIcingDrawScene.
            createSingleton(this));
        this.addScene(JSCSketchSideScenario.SideDecoScene.
            createSingleton(this));
    }

    public static class SideIcingReadyScene extends JSCScene {
        // singleton pattern
        private static SideIcingReadyScene mSingelton = null;
        public static SideIcingReadyScene getSingleton() {
            assert(SideIcingReadyScene.mSingelton != null);
            return SideIcingReadyScene.mSingelton;
        }
        public static SideIcingReadyScene createSingleton(XScenario scenario) {
            assert(SideIcingReadyScene.mSingelton == null);
            SideIcingReadyScene.mSingelton = new SideIcingReadyScene(scenario);
            return SideIcingReadyScene.mSingelton;
        }
        private SideIcingReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            JSCCanvas2D canvas = jsc.getCanvas2D();
            Point pt = e.getPoint();
            Point translatedPt = new Point(pt.x + 400, pt.y - 90);
            if (canvas.ICING_BOX.contains(pt)) {
                JSCCmdToPickUpIcing.execute(jsc, pt);
            } else if (canvas.DECO_BOX.contains(pt)) {
                XCmdToChangeScene.execute(jsc, 
                    JSCSketchSideScenario.SideDecoScene.
                    getSingleton(), null);
                JSCCmdToPickUpDeco.execute(jsc,pt);
            } else if (jsc.getJSCCakeSideMgr().getSideSurfaceShape().
                contains(translatedPt)) {
                XCmdToChangeScene.execute(jsc, JSCSketchSideScenario.
                    SideIcingDrawScene.getSingleton(), this);
                JSCCmdToCreateCurSideIcingPtCurve.execute(jsc, pt);
            } else {
                
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
                case KeyEvent.VK_UP:
                    XCmdToChangeScene.execute(jsc, 
                        JSCSketchUpperScenario.UpperIcingReadyScene.
                        getSingleton(), null);
                    break;
                case KeyEvent.VK_ESCAPE:
                    XCmdToChangeScene.execute(jsc, JSCDefaultScenario.
                        CakeSideReadyScene.getSingleton(), null);
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
            jsc.getJSCCakeSideMgr().sketchSideSurface();
            jsc.getJSCCakeUpperMgr().UpperRotateAnimationPreset();
            jsc.getJSCCakeSideMgr().SideRotateAnimationPreset();
            g2.drawImage(jsc.getImageRenderer().drawImageForSideScene(), 
                118, 60, jsc.getCanvas2D());
            JSCSketchSideScenario scenario = 
                (JSCSketchSideScenario) this.mScenario;
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
       
    public static class SideIcingDrawScene extends JSCScene {
        // singleton pattern
        private static SideIcingDrawScene mSingelton = null;
        public static SideIcingDrawScene getSingleton() {
            assert(SideIcingDrawScene.mSingelton != null);
            return SideIcingDrawScene.mSingelton;
        }
        public static SideIcingDrawScene createSingleton(XScenario scenario) {
            assert(SideIcingDrawScene.mSingelton == null);
            SideIcingDrawScene.mSingelton = new SideIcingDrawScene(scenario);
            return SideIcingDrawScene.mSingelton;
        }
        private SideIcingDrawScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {

        }

        @Override
        public void handleMouseDrag(MouseEvent e) {      
            JSC jsc = (JSC) this.mScenario.getApp();
            Point pt = e.getPoint();
            Point translatedPt = new Point(pt.x + 400, pt.y - 90);
            if (jsc.getJSCCakeSideMgr().getSideSurfaceShape().
                contains(translatedPt)) {
                JSCCmdToUpdateSideCurIcingPtCurve.execute(jsc, pt);
            } else {
                JSCCmdToAddCurSideIcingPtCurveToSideIcingPtCurves.
                    execute(jsc);
                XCmdToChangeScene.execute(jsc, JSCSketchSideScenario.
                    SideIcingReadyScene.getSingleton(), null);
            }
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            JSCCmdToAddCurSideIcingPtCurveToSideIcingPtCurves.execute(jsc);
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
            jsc.getJSCCakeSideMgr().sketchSideSurface();
            jsc.getJSCCakeUpperMgr().UpperRotateAnimationPreset();
            jsc.getJSCCakeSideMgr().SideRotateAnimationPreset();
            g2.drawImage(jsc.getImageRenderer().drawImageForSideScene(), 
                118, 60, jsc.getCanvas2D());
            JSCSketchSideScenario scenario = 
                (JSCSketchSideScenario) this.mScenario;
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
    
    public static class SideDecoScene extends JSCScene {
        // singleton pattern
        private static SideDecoScene mSingelton = null;
        public static SideDecoScene getSingleton() {
            assert(SideDecoScene.mSingelton != null);
            return SideDecoScene.mSingelton;
        }
        public static SideDecoScene createSingleton(XScenario scenario) {
            assert(SideDecoScene.mSingelton == null);
            SideDecoScene.mSingelton = new SideDecoScene(scenario);
            return SideDecoScene.mSingelton;
        }
        private SideDecoScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            JSC jsc = (JSC) this.mScenario.getApp();
            JSCCanvas2D canvas = jsc.getCanvas2D();
            Point pt = e.getPoint();
            Point translatedPt = new Point(pt.x + 400, pt.y - 90);
            if (canvas.ICING_BOX.contains(pt)) {
                XCmdToChangeScene.execute(jsc, JSCSketchSideScenario.
                    SideIcingReadyScene.getSingleton(), null);
                JSCCmdToPickUpIcing.execute(jsc, pt);
            } else if (canvas.DECO_BOX.contains(pt)) {
                JSCCmdToPickUpDeco.execute(jsc,pt);
            } else if (jsc.getJSCCakeSideMgr().getSideSurfaceShape().
                contains(translatedPt)) {
                JSCCmdToPutDecoOnCakeSide.execute(jsc, pt);
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
                case KeyEvent.VK_UP:
                    XCmdToChangeScene.execute(jsc, 
                        JSCSketchUpperScenario.UpperDecoScene.
                        getSingleton(), null);
                    break;
                case KeyEvent.VK_ESCAPE:
                    XCmdToChangeScene.execute(jsc, JSCDefaultScenario.
                        CakeSideReadyScene.getSingleton(), null);
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
            jsc.getJSCCakeSideMgr().sketchSideSurface();
            jsc.getJSCCakeUpperMgr().UpperRotateAnimationPreset();
            jsc.getJSCCakeSideMgr().SideRotateAnimationPreset();
            
            g2.drawImage(jsc.getImageRenderer().drawImageForSideScene(), 
                118, 60, jsc.getCanvas2D());
            JSCSketchSideScenario scenario = 
                (JSCSketchSideScenario) this.mScenario;
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
