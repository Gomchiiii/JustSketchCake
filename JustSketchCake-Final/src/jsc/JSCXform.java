package jsc;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

public class JSCXform {
    // constants
    JSC mjsc = null;
    public static final Point CENTER_PT = new Point(100,100);

    private AffineTransform mXformFromUpperWorldToScreen = null;
    public AffineTransform getXformFromUpperWorldToScreen() {
        return mXformFromUpperWorldToScreen;
    }    
    public void setXformFromUpperWorldtoScreen(
        AffineTransform CurXformFromUpperWorldtoScreen) {
        this.mXformFromUpperWorldToScreen = CurXformFromUpperWorldtoScreen;
    }
  
    private AffineTransform mXformFromScreenToUpperWorld = null;
    public AffineTransform getXformFromScreenToUpperWorld() {
        return mXformFromScreenToUpperWorld;
    }

    //constructor
    public JSCXform(JSC jsc) {
        this.mjsc = jsc;
        this.mXformFromScreenToUpperWorld = new AffineTransform();
        this.mXformFromUpperWorldToScreen = new AffineTransform();
    }
    
    public void updateXformFromScreenToWorld() {
        try {
            this.mXformFromScreenToUpperWorld = 
                this.mXformFromUpperWorldToScreen.createInverse();
        } catch (NoninvertibleTransformException ex) {
            System.out.println("NoninvertibleTransformException");
        }
    }
    
    public boolean updateXformFromUpperWoldToScreenfromOffset() {
            //Orientation Point (x0, y0) = (358.0, 300.0)

            double ang = 
                - this.mjsc.getJSCCakeUpperMgr().getOffset_x() / JSC.CAKE_R ;
            this.mXformFromUpperWorldToScreen.setToRotation(ang);
            
            //this.mXformFromUpperWorldToScreen.translate(JSC.CAKE_R, JSC.CAKE_R);
            this.updateXformFromScreenToWorld();

            return true;
        }
    
    public Point calcPtFromUpperWorldToScreen(Point worldPt) {
        Point screenPt = new Point();
        Point worldPtfromCtr = 
            new Point(worldPt.x - (int)JSC.CAKE_R, worldPt.y - (int)JSC.CAKE_R);
        this.mXformFromUpperWorldToScreen.transform(
            worldPtfromCtr, screenPt);
        return screenPt; 
    }
    
    public Point calcPtFromScreenToUpperWorld(Point screenPt) {
        Point worldPt = new Point();
        Point screenPtfromCtr = new Point(screenPt.x - 358, screenPt.y - 300);
        this.mXformFromScreenToUpperWorld.transform(
            screenPtfromCtr, worldPt);
        Point worldPtPolarCoordinate = new Point(worldPt.x + (int) JSC.CAKE_R, 
            worldPt.y + (int) JSC.CAKE_R);
        return worldPtPolarCoordinate;
    }

    public Point calcPtFromScreenToSideWorld(Point Pt) {
        //Orientation Point (x0, y0) = (358.0, 100.0)
        double theta = Math.acos(((double)Pt.x - 358.0)/JSC.CAKE_R);
        double sin = Math.sin(theta);

        int SideSurface_x = ((int)Math.round(- theta * JSC.CAKE_R) - 
            this.mjsc.getJSCCakeUpperMgr().getOffset_x() - 358 + 1508) % 1508;
        int SideSurface_y =  
            (int)Math.round((double)Pt.y - JSC.CAKE_R / 2 * sin) + 30 ;

        Point SideSurfacePt = new Point(SideSurface_x, SideSurface_y);
 
        return SideSurfacePt;
    }
}
