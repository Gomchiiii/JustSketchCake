package jsc;


import java.awt.Color;
import java.util.ArrayList;

public class JSCIcingPtCurveMgr {
    // constants
    private static final Color DEFAULT_COLOR_FOR_ICING = 
        new Color(255, 255, 150);
    
    // fields
    private JSCIcingPtCurve mCurPtCurve = null;
    public JSCIcingPtCurve getCurPtCurve() {
        return this.mCurPtCurve;
    }
    public void setCurPtCurve(JSCIcingPtCurve ptCurve) {
        this.mCurPtCurve = ptCurve;
    }
    private ArrayList<JSCIcingPtCurve> mPtCurves = null;
    public ArrayList<JSCIcingPtCurve> getPtCurves() {
        return this.mPtCurves;
    }
    
    private Color mColorForCurIcingPtCurve = null;
    public Color getColorForCurIcingPtCurve() {
        return this.mColorForCurIcingPtCurve;
    }
    public void setColorForCurIcingPtCurve(Color color) {
        this.mColorForCurIcingPtCurve = color;
    }
    
    private JSCIcingStroke mIcingStroke = null;
    public JSCIcingStroke getIcingStroke() {
        return this.mIcingStroke;
    }
    public void setIcingStroke(JSCIcingStroke stroke) {
        this.mIcingStroke = stroke;
    }
    
    // constructor
    public JSCIcingPtCurveMgr() {
        this.mPtCurves = new ArrayList<JSCIcingPtCurve>();
        this.mColorForCurIcingPtCurve = 
            JSCIcingPtCurveMgr.DEFAULT_COLOR_FOR_ICING;
    }
}
