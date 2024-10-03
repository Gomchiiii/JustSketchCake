
package jsc;

import Scalr.Scalr;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import static jsc.JSCUtil.imageToShape;
import static jsc.JSCUtil.readImage;

public class JSCCakeUpperMgr extends JSCSurfaceMgr {
    // constant
    // fields
    private JSC mJSC = null;
    private BufferedImage mUpperSurface = null;    
    private BufferedImage mUpperSurfaceToDisplay = null;
    public BufferedImage getUpperSurfaceToDisplay() {
        return mUpperSurfaceToDisplay;
    }
    public void setUpperSurfacetodisplay(BufferedImage upperSurfaceToDisplay) {
        this.mUpperSurfaceToDisplay = upperSurfaceToDisplay;
    }
    
    private BufferedImage mSketchedUpperSurface = null;
    public BufferedImage getSketchedUpperSurface() {
        return this.mSketchedUpperSurface;
    }
    
    private Shape mUpperSurfaceShape = null;
    public Shape getUpperSurfaceShape() {
        return this.mUpperSurfaceShape;
    }
    
    private JSCIcingPtCurveMgr mUpperIcingPtCurveMgr = null;
    public JSCIcingPtCurveMgr getUpperIcingPtCurveMgr() {
        return mUpperIcingPtCurveMgr;
    }
    
    private JSCDecoStampMgr mUpperDecoStampMgr = null;
    public JSCDecoStampMgr getUpperDecoStampMgr() {
        return this.mUpperDecoStampMgr;
    }
    
    // constructor
    public JSCCakeUpperMgr(JSC jsc) {
        super(jsc);
        this.mJSC = jsc;
        this.mUpperIcingPtCurveMgr = new JSCIcingPtCurveMgr();
        this.mUpperDecoStampMgr = new JSCDecoStampMgr();
        this.mUpperSurface = readImage("Images/cakeupperbasic.png");
        this.mSketchedUpperSurface = this.mUpperSurface;
    }
    
    //method 
    public void sketchUpperSurface() {
        double r = JSC.CAKE_R;
        Graphics2D g2ForUpper = this.mSketchedUpperSurface.createGraphics();
        if (this.mUpperIcingPtCurveMgr.getCurPtCurve() != null) {
            JSCUtil.drawIcingPtCurve(g2ForUpper, 
            this.mUpperIcingPtCurveMgr.getCurPtCurve());
        }
        for (JSCIcingPtCurve ptCurve : 
            this.mUpperIcingPtCurveMgr.getPtCurves()) {
            JSCUtil.drawIcingPtCurve(g2ForUpper, ptCurve);
        }
        
        for (JSCDecoStamp decoStamp:this.mUpperDecoStampMgr.getDecoStamps()) {
            BufferedImage scaledDecoStamp = Scalr.resize(
                decoStamp.getDecoImage(), Scalr.Method.BALANCED, 
                decoStamp.getDecoSize(), 
                decoStamp.getDecoSize());
            g2ForUpper.drawImage(scaledDecoStamp, 
                decoStamp.getDecoLocation().x - decoStamp.getDecoSize() / 2, 
                decoStamp.getDecoLocation().y - decoStamp.getDecoSize() / 2, 
                null);
        }
        g2ForUpper.dispose();
    }
    public void UpperRotateAnimationPreset(){
        BufferedImage rotatedImage = 
            new BufferedImage(480, 480, 2);
        for (int x = 0; x < this.mSketchedUpperSurface.getWidth(); x++) {
            for (int y = 0; y < this.mSketchedUpperSurface.getHeight(); y++) {
                Point worldPt = new Point(x - 240, y - 240);
                Point screenPt = new Point();
                this.mJSC.getXform().getXformFromUpperWorldToScreen().
                    transform(worldPt, screenPt);
                if ( -240 <= screenPt.x && screenPt.x < 240 && 
                    -240 <= screenPt.y && screenPt.y < 240) {
                    rotatedImage.setRGB(screenPt.x + 240, screenPt.y + 240, 
                        this.mSketchedUpperSurface.getRGB(x, y));
                }
            }
        }
        this.mUpperSurfaceToDisplay = rotatedImage;
        this.mUpperSurfaceShape = imageToShape(this.mUpperSurface);
    }
}
