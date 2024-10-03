package jsc.cmd;

import java.awt.Point;
import jsc.JSC;
import jsc.JSCIcingPtCurve;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToUpdateUpperCurIcingPtCurve extends XLoggableCmd {
    // fields    
    Point mScreenPt = null;
    Point mWorldPt = null;
    JSCIcingPtCurve mCurPtCurve = null;
    
    // private constructor
    private JSCCmdToUpdateUpperCurIcingPtCurve(XApp app, Point pt) {
        super(app);        
        this.mScreenPt = pt;
    }
    
    // JSCCmdToCreateCurPtCurve.execute(JSC, pt)
    public static boolean execute(XApp app, Point pt) {
        JSCCmdToUpdateUpperCurIcingPtCurve cmd =
            new JSCCmdToUpdateUpperCurIcingPtCurve(app, pt);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        jsc.getCursorMgr().setCurCursorPt(this.mScreenPt);
        this.mCurPtCurve = 
            jsc.getJSCCakeUpperMgr().getUpperIcingPtCurveMgr().getCurPtCurve();
        this.mWorldPt = 
            jsc.getXform().calcPtFromScreenToUpperWorld(this.mScreenPt);
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
