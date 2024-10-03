package jsc;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class JSCImageRenderer {
    // constants
    private static final int CAKE_R = (int) JSC.CAKE_R;
    // fields
    private JSC mJSC;
    private BufferedImage mImageForUpperScene = null;
    public BufferedImage getImageForUpperScene() {
        return this.mImageForUpperScene;
    }
    
    private BufferedImage mImageForSideScene = null;
    public BufferedImage getImageForSideScene() {
        return this.mImageForSideScene;
    }
    
    private BufferedImage mImageForExport = null;
    public BufferedImage getImageForExport() {
        return this.mImageForExport;
    }
    
    // constructor
    public JSCImageRenderer(JSC jsc) {
        this.mJSC = jsc;
        this.mImageForUpperScene = 
            new BufferedImage(CAKE_R * 2, CAKE_R * 2, 2);
        this.mImageForSideScene = 
            new BufferedImage(CAKE_R * 2, CAKE_R + 200, 2);
        this.mImageForExport = 
            new BufferedImage(1600, 800, 2);
    }
    
    // methods
    public BufferedImage drawImageForUpperScene() {
        this.mImageForUpperScene = 
            this.mJSC.getJSCCakeUpperMgr().getUpperSurfaceToDisplay();
        return this.mImageForUpperScene;
    }
    
    public BufferedImage drawImageForSideScene() {
        Graphics2D g2ForSideScene = this.mImageForSideScene.createGraphics();
        g2ForSideScene.drawImage(
            this.mJSC.getJSCCakeSideMgr().getSideSurfaceToDisplay(), null, 
            -518, 30);
        BufferedImage scaledUpperImage = 
            new BufferedImage(CAKE_R * 2, CAKE_R, 2);
        Graphics2D g2ForScale = scaledUpperImage.createGraphics();
        g2ForScale.scale(1, 0.5);
        g2ForScale.drawImage(this.mJSC.getJSCCakeUpperMgr().
            getUpperSurfaceToDisplay(), 0, 0, null);
        g2ForScale.dispose();
        g2ForSideScene.drawImage(scaledUpperImage, 0, 0, null);
        g2ForSideScene.dispose();
        return this.mImageForSideScene;
    }
    
    public BufferedImage drawImageForExport() {
        this.mJSC.getJSCCakeUpperMgr().sketchUpperSurface();
        this.mJSC.getJSCCakeSideMgr().sketchSideSurface();
        this.drawImageForSideScene();
        BufferedImage sideSurface = 
            new BufferedImage(1508, 200, 2);
        Graphics2D g2ForSideSurface = sideSurface.createGraphics();
        g2ForSideSurface.drawImage(this.mJSC.getJSCCakeSideMgr().
            getSketchedSideSurface(), 0, -200, null);
        g2ForSideSurface.dispose();
        Graphics2D g2ForExport = this.mImageForExport.createGraphics();
        g2ForExport.drawImage(this.mJSC.getJSCCakeUpperMgr().
            getSketchedUpperSurface(), 150, 0, null);
        g2ForExport.drawImage(
            this.mImageForSideScene, 800, 0, null);
        g2ForExport.drawImage(sideSurface, 0, 500, null);
        g2ForExport.dispose();
        return this.mImageForExport;
    }
}
