package jsc.cmd;

import java.awt.Point;
import jsc.JSC;
import jsc.JSCIcingPtCurve;
import jsc.JSCIcingPtCurveMgr;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToCreateCurUpperIcingPtCurve extends XLoggableCmd {
    // fields
    private Point mScreenPt = null;
    private Point mWolrdPt = null;
    
    // private constructor
    private JSCCmdToCreateCurUpperIcingPtCurve(XApp app, Point pt) {
        super(app);
        this.mScreenPt = pt;
    }
    
    // JSICmdToCreateCurPtCurve.execute(jsi, pt)
    public static boolean execute(XApp app, Point pt) {
        JSCCmdToCreateCurUpperIcingPtCurve cmd = 
            new JSCCmdToCreateCurUpperIcingPtCurve(app, pt);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        JSCIcingPtCurveMgr mgr = 
            jsc.getJSCCakeUpperMgr().getUpperIcingPtCurveMgr();
        jsc.getCursorMgr().setCurCursorPt(this.mScreenPt);
        this.mWolrdPt = 
            jsc.getXform().calcPtFromScreenToUpperWorld(this.mScreenPt);
        JSCIcingPtCurve ptCurve = new JSCIcingPtCurve(this.mWolrdPt, 
            mgr.getColorForCurIcingPtCurve(), mgr.getIcingStroke());
        mgr.setCurPtCurve(ptCurve);
        
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mScreenPt).append("\t");
        sb.append(this.mWolrdPt);
        return sb.toString();
    }
    
}
