
package jsc;

import Scalr.Scalr;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import static jsc.JSCUtil.imageToShape;
import static jsc.JSCUtil.indexingImgforRotate;
import static jsc.JSCUtil.mapImageOnCylinder;
import static jsc.JSCUtil.readImage;

public class JSCCakeSideMgr extends JSCSurfaceMgr {
    // fields
    private JSC mJSC = null;
    private BufferedImage mSideBasicSurface = null;    
    private BufferedImage mSideSurfacetodisplay = null;
    public BufferedImage getSideSurfaceToDisplay() {
        return mSideSurfacetodisplay;
    }
    public void setSideSurfacetodisplay(BufferedImage sideSurfaceToDisplay) {
        this.mSideSurfacetodisplay = sideSurfaceToDisplay;
    }
    
    private BufferedImage mSketchedSideSurface = null;
    public BufferedImage getSketchedSideSurface() {
        return this.mSketchedSideSurface;
    }
    
    private Shape mSideSurfaceShape = null;
    public Shape getSideSurfaceShape() {
        return this.mSideSurfaceShape;
    }
    
    private JSCIcingPtCurveMgr mSideIcingPtCurveMgr = null;
    public JSCIcingPtCurveMgr getSideIcingPtCurveMgr() {
        return mSideIcingPtCurveMgr;
    }
    
    private JSCDecoStampMgr mSideDecoStampMgr = null;
    public JSCDecoStampMgr getSideDecoStampMgr() {
        return this.mSideDecoStampMgr;
    }
    
    // constructor
    public JSCCakeSideMgr(JSC jsc) {
        super(jsc);
        this.mJSC = jsc;
        this.mSideIcingPtCurveMgr = new JSCIcingPtCurveMgr();
        this.mSideDecoStampMgr = new JSCDecoStampMgr();
        this.mSideBasicSurface = readImage("Images/cakesidebasic.png");
        this.mSketchedSideSurface = this.mSideBasicSurface;
    }
    
    //method 
    public void sketchSideSurface() {
        double r = JSC.CAKE_R;
        //put Ptcurves on sidesurface 
        Graphics2D g2ForSide = this.mSketchedSideSurface.createGraphics();
        if (this.mSideIcingPtCurveMgr.getCurPtCurve() != null) {
            JSCUtil.drawIcingPtCurve(g2ForSide, 
                this.mSideIcingPtCurveMgr.getCurPtCurve());
        }
        for (JSCIcingPtCurve ptCurve : 
            this.mSideIcingPtCurveMgr.getPtCurves()) {
            JSCUtil.drawIcingPtCurve(g2ForSide, ptCurve);
        }
        
        //put decos on sidesurfaces
        for (JSCDecoStamp decoStamp : this.mSideDecoStampMgr.getDecoStamps()) {
            BufferedImage scaledDecoStamp = Scalr.resize(
                decoStamp.getDecoImage(), Scalr.Method.BALANCED, 
                decoStamp.getDecoSize(), 
                decoStamp.getDecoSize());
            g2ForSide.drawImage(scaledDecoStamp, 
                decoStamp.getDecoLocation().x - decoStamp.getDecoSize() / 2, 
                decoStamp.getDecoLocation().y - decoStamp.getDecoSize() / 2, 
                null);
        }
        g2ForSide.dispose();
    }
    
    //preperation for animation 
    public void SideRotateAnimationPreset(){
        double r = JSC.CAKE_R;
        BufferedImage IndexedImage = indexingImgforRotate(
            this.mSketchedSideSurface, this.offset_x, 2*r);
        this.mSideSurfacetodisplay = mapImageOnCylinder(IndexedImage); 
        this.mSideSurfaceShape = imageToShape(this.mSideBasicSurface);
    }

}
