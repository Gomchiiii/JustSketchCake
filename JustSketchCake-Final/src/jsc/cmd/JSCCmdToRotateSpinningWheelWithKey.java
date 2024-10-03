package jsc.cmd;

import jsc.JSC;
import jsc.JSCSurfaceMgr;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToRotateSpinningWheelWithKey extends XLoggableCmd {
    // fields
    boolean rotdirection;
    
    // private constructor
    private JSCCmdToRotateSpinningWheelWithKey(XApp app, boolean isClockwise) {
        super(app);
        this.rotdirection = isClockwise;
    }
    
    // JSICmdToCreateCurPtCurve.execute(jsi, pt)
    public static boolean execute(XApp app, boolean isClockwise) {
        JSCCmdToRotateSpinningWheelWithKey cmd = 
            new JSCCmdToRotateSpinningWheelWithKey(app, isClockwise);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        if (this.rotdirection) {
            jsc.getJSCCakeSideMgr().updateOffset(
                JSCSurfaceMgr.OFFSET_UPDATE_INCREMENT);
            jsc.getJSCCakeUpperMgr().updateOffset(
                JSCSurfaceMgr.OFFSET_UPDATE_INCREMENT);
        } else { 
            jsc.getJSCCakeSideMgr().updateOffset(
                - JSCSurfaceMgr.OFFSET_UPDATE_INCREMENT);
            jsc.getJSCCakeUpperMgr().updateOffset(
                - JSCSurfaceMgr.OFFSET_UPDATE_INCREMENT);
        }
        
        jsc.getXform().updateXformFromUpperWoldToScreenfromOffset();
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
