package jsc.cmd;

import java.awt.Point;
import jsc.JSC;
import jsc.JSCCursorMgr;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToPickUpDeco extends XLoggableCmd {
    // fields
    private Point mCurCursorPt = null;
    
    // private constructor
    private JSCCmdToPickUpDeco(XApp app, Point pt) {
        super(app);
        this.mCurCursorPt = pt;
    }
    
    // JSCCmdToPickUpDeco.execute(jsc, pt);
    public static boolean execute(XApp app, Point pt) {
        JSCCmdToPickUpDeco cmd = new JSCCmdToPickUpDeco(app, pt);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        if (this.mCurCursorPt != null) {
            double x = this.mCurCursorPt.x;
            double y = this.mCurCursorPt.y;

            double xs = 730.0;
            double xe = 1020.0;
            double ys = 230.0;
            double ye = 450.0;
            double dx = 70.0;
            double dy = 70.0;

            int i = (int)((double)(y - ys) / dy);
            int j = (int)((double)(x - xs) / dx);

            jsc.getCursorMgr().setCurCursor(
                jsc.getDecoOption().mDecoOptions.get(i * 4 + j));
            jsc.getCursorMgr().setCurCursorPt(this.mCurCursorPt);
            jsc.getCursorMgr().setCurCursorScale(
                JSCCursorMgr.DEFAULT_SCALE);
        } else {
            jsc.getCursorMgr().setCurCursor(null);
            jsc.getCursorMgr().setCurCursorPt(null);
        }
        
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mCurCursorPt);
        return sb.toString();
    }
    
}
