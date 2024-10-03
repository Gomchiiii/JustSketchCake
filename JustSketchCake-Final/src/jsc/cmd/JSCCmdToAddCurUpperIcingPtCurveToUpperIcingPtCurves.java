package jsc.cmd;

import jsc.JSC;
import jsc.JSCIcingPtCurveMgr;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToAddCurUpperIcingPtCurveToUpperIcingPtCurves 
    extends XLoggableCmd {
    // fields
    
    // private constructor
    private JSCCmdToAddCurUpperIcingPtCurveToUpperIcingPtCurves(XApp app) {
        super(app);
    }
    
    // JSCCmdToAddCurIcingPtCurveToUpperIcingPtCurves.execute(jsc);
    public static boolean execute(XApp app) {
        JSCCmdToAddCurUpperIcingPtCurveToUpperIcingPtCurves cmd = 
            new JSCCmdToAddCurUpperIcingPtCurveToUpperIcingPtCurves(app);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        JSCIcingPtCurveMgr mgr = 
            jsc.getJSCCakeUpperMgr().getUpperIcingPtCurveMgr(); 
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
