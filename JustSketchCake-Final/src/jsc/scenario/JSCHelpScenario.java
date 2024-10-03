package jsc.scenario;

import Scalr.Scalr;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import jsc.JSC;
import jsc.JSCDecoOption;
import jsc.JSCScene;
import x.XApp;
import x.XCmdToChangeScene;
import x.XScenario;

public class JSCHelpScenario extends XScenario {
    // singleton pattern
    private static JSCHelpScenario mSingleton = null;
    public static JSCHelpScenario getSingleton() {
        assert(JSCHelpScenario.mSingleton != null);
        return JSCHelpScenario.mSingleton;
    }
    public static JSCHelpScenario createSingleton(XApp app) {
        assert(JSCHelpScenario.mSingleton == null);
        JSCHelpScenario.mSingleton = new JSCHelpScenario(app);
        return JSCHelpScenario.mSingleton;
    }
    private JSCHelpScenario(XApp app) {
        super(app);
    }
    
    @Override
    protected void addScenes() {
        this.addScene(
            JSCHelpScenario.HelpScene.createSingleton(this));
    }

    public static class HelpScene extends JSCScene {
        // singleton pattern
        private static HelpScene mSingelton = null;
        public static HelpScene getSingleton() {
            assert(HelpScene.mSingelton != null);
            return HelpScene.mSingelton;
        }
        public static HelpScene createSingleton(XScenario scenario) {
            assert(HelpScene.mSingelton == null);
            HelpScene.mSingelton = new HelpScene(scenario);
            return HelpScene.mSingelton;
        }
        private HelpScene(XScenario scenario) {
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
            JSC jsc = (JSC) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_H:
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
            JSCHelpScenario scenario = (JSCHelpScenario) this.mScenario;
            scenario.drawHelp(g2);
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
    JSC jsc = (JSC) this.mApp;
    private void drawHelp(Graphics2D g2) {
        BufferedImage helpUI = null;
        try {
            InputStream resourceStream = getClass().
                getClassLoader().getResourceAsStream("Images/JSCHelpUI.png");
            helpUI = (BufferedImage) ImageIO.read(resourceStream);
        } catch (IOException ex) {
            Logger.getLogger(JSCDecoOption.class.getName()).
                log(Level.SEVERE, null, ex);
        }
        g2.drawImage(helpUI, 180, 120, jsc.getCanvas2D());
    }
}
