package jsc.cmd;

import jsc.JSC;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdEmpty extends XLoggableCmd {
    // fields
    
    // private constructor
    private JSCCmdEmpty(XApp app) {
        super(app);
    }
    
    // JSICmdToCreateCurPtCurve.execute(jsi, pt)
    public static boolean execute(XApp app) {
        JSCCmdEmpty cmd = new JSCCmdEmpty(app);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
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
