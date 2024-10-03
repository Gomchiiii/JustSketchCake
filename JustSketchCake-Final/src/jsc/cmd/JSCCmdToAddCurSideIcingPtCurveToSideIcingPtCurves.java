package jsc.cmd;

import jsc.JSC;
import jsc.JSCIcingPtCurveMgr;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToAddCurSideIcingPtCurveToSideIcingPtCurves 
    extends XLoggableCmd {
    // fields
    
    // private constructor
    private JSCCmdToAddCurSideIcingPtCurveToSideIcingPtCurves(XApp app) {
        super(app);
    }
    
    // JSCCmdToAddCurSideIcingPtCurveToSideIcingPtCurves.execute(jsc);
    public static boolean execute(XApp app) {
        JSCCmdToAddCurSideIcingPtCurveToSideIcingPtCurves cmd = 
            new JSCCmdToAddCurSideIcingPtCurveToSideIcingPtCurves(app);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        JSCIcingPtCurveMgr mgr = 
            jsc.getJSCCakeSideMgr().getSideIcingPtCurveMgr(); 
        mgr.getPtCurves().add(mgr.getCurPtCurve());
        mgr.setCurPtCurve(null);
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        return sb.toString();
    }
    
}
