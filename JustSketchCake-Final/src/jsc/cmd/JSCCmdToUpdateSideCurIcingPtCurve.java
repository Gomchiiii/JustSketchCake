package jsc.cmd;

import java.awt.Point;
import jsc.JSC;
import jsc.JSCIcingPtCurve;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToUpdateSideCurIcingPtCurve extends XLoggableCmd {
    // fields    
    Point mScreenPt = null;
    Point mWorldPt = null;
    JSCIcingPtCurve mCurPtCurve = null;
    
    // private constructor
    private JSCCmdToUpdateSideCurIcingPtCurve(XApp app, Point pt) {
        super(app);        
        this.mScreenPt = pt;
    }
    
    // JSCCmdToCreateCurPtCurve.execute(JSC, pt)
    public static boolean execute(XApp app, Point pt) {
        JSCCmdToUpdateSideCurIcingPtCurve cmd =
            new JSCCmdToUpdateSideCurIcingPtCurve(app, pt);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        jsc.getCursorMgr().setCurCursorPt(this.mScreenPt);
        this.mCurPtCurve = 
            jsc.getJSCCakeSideMgr().getSideIcingPtCurveMgr().getCurPtCurve();
        this.mWorldPt = 
            jsc.getXform().calcPtFromScreenToSideWorld(this.mScreenPt);
        this.mCurPtCurve.addPt(this.mWorldPt);
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append("").append("\t");
        return sb.toString();
    }
    
}
