package jsc.cmd;

import java.awt.Point;
import jsc.JSC;
import jsc.JSCIcingPtCurve;
import jsc.JSCIcingPtCurveMgr;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToCreateCurSideIcingPtCurve extends XLoggableCmd {
    // fields
    private Point mScreenPt = null;
    private Point mWorldPt = null;
    
    // private constructor
    private JSCCmdToCreateCurSideIcingPtCurve(XApp app, Point pt) {
        super(app);
        this.mScreenPt = pt;
    }
    
    // JSICmdToCreateCurPtCurve.execute(jsi, pt)
    public static boolean execute(XApp app, Point pt) {
        JSCCmdToCreateCurSideIcingPtCurve cmd = 
            new JSCCmdToCreateCurSideIcingPtCurve(app, pt);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        jsc.getCursorMgr().setCurCursorPt(this.mScreenPt);
        JSCIcingPtCurveMgr mgr = 
            jsc.getJSCCakeSideMgr().getSideIcingPtCurveMgr();
        this.mWorldPt =
            jsc.getXform().calcPtFromScreenToSideWorld(this.mScreenPt);
        JSCIcingPtCurve ptCurve = new JSCIcingPtCurve(this.mWorldPt, 
            mgr.getColorForCurIcingPtCurve(), mgr.getIcingStroke());
        mgr.setCurPtCurve(ptCurve);
        
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mScreenPt).append("\t");
        sb.append(this.mWorldPt);
        return sb.toString();
    }
    
}
