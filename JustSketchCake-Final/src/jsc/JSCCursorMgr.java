
package jsc;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class JSCCursorMgr {
    // constants
    public static final int DEFAULT_SCALE = 60;
    // fields
    private JSC mJSC = null;
    
    private BufferedImage mCurCursor = null;
    public BufferedImage getCurCursor() {
        return this.mCurCursor;
    }
    public void setCurCursor(BufferedImage image) {
        this.mCurCursor = image;
    }
    
    private Point mCurCursorPt = null;
    public Point getCurCursorPt() {
        return this.mCurCursorPt;
    }
    public void setCurCursorPt(Point pt) {
        this.mCurCursorPt = pt;
    }
    
    private int mCurCursorScale;
    public int getCurCursorScale() {
        return this.mCurCursorScale;
    }
    public void setCurCursorScale(int scale) {
        this.mCurCursorScale = scale;
    }
    
    // constructor
    public JSCCursorMgr(JSC jsc) {
        this.mJSC = jsc;
        this.mCurCursorScale = JSCCursorMgr.DEFAULT_SCALE;
    }
}
