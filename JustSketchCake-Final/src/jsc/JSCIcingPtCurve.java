package jsc;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;


//custom data structure for saveing Icing Input Points 
public class JSCIcingPtCurve {

    //fields
    private ArrayList<Point> mPts = null;
    public ArrayList<Point> getPts(){
        return this.mPts;
    }
    private Color mColor = null;
    public Color getColor() {
        return this.mColor;
    }
    public void setColor(Color c) {
        this.mColor = c;
    }
    private JSCIcingStroke mIcingStroke = null;
    public JSCIcingStroke getIcingStroke() {
        return this.mIcingStroke;
    }
    //constructor
    public JSCIcingPtCurve(Point pt, Color c, JSCIcingStroke s) {
        this.mPts = new ArrayList<Point>();
        this.mPts.add(pt);
        this.mIcingStroke = s;
        this.mColor = c;
    }
    
    //methods
    public void addPt(Point pt) {
        this.mPts.add(pt);
    }
}