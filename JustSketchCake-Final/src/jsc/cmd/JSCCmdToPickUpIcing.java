package jsc.cmd;

import java.awt.Point;
import jsc.JSC;
import jsc.JSCIcingStroke;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToPickUpIcing extends XLoggableCmd {
    // fields
    private Point mCurCursorPt = null;
    
    // private constructor
    private JSCCmdToPickUpIcing(XApp app, Point pt) {
        super(app);
        this.mCurCursorPt = pt;
    }
    
    // JSCCmdToPickUpIcing.execute(jsc, pt);
    public static boolean execute(XApp app, Point pt) {
        JSCCmdToPickUpIcing cmd = new JSCCmdToPickUpIcing(app, pt);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        if (this.mCurCursorPt != null) {
            double x = this.mCurCursorPt.x;
            double y = this.mCurCursorPt.y;

            double xs = 730.0;
            double xe = 950.0;
            double ys = 60.0;
            double ye = 210.0;
            double dx = 70.0;
            double dy = 70.0;

            int i = (int)((double)(y - ys) / dy);
            int j = (int)((double)(x - xs) / dx);

            jsc.getCursorMgr().setCurCursor(
                jsc.getIcingOption().mIcingOptions.get(i * 3 + j));
            jsc.getCursorMgr().setCurCursorPt(this.mCurCursorPt);
            JSCIcingStroke stroke = new JSCIcingStroke(jsc.
                getIcingOption().mIcingStrokeShapes.get(i * 3 + j), 10);
            jsc.getJSCCakeUpperMgr().getUpperIcingPtCurveMgr().
                setIcingStroke(stroke);
            jsc.getJSCCakeSideMgr().getSideIcingPtCurveMgr().
                setIcingStroke(stroke);
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
