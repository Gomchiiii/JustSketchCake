package jsc.cmd;

import java.awt.Color;
import jsc.JSC;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToChangeIcingColor extends XLoggableCmd {
    // fields
    private Color mColorForIcing = null;
    
    // private constructor
    private JSCCmdToChangeIcingColor(XApp app, Color c) {
        super(app);
        this.mColorForIcing = c;
    }
    
    // JSCCmdToChangeIcingColor.execute(jsc, c);
    public static boolean execute(XApp app, Color c) {
        JSCCmdToChangeIcingColor cmd = new JSCCmdToChangeIcingColor(app, c);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        jsc.getJSCCakeUpperMgr().getUpperIcingPtCurveMgr().
            setColorForCurIcingPtCurve(this.mColorForIcing);
        jsc.getJSCCakeSideMgr().getSideIcingPtCurveMgr().
            setColorForCurIcingPtCurve(this.mColorForIcing);
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mColorForIcing);
        return sb.toString();
    }
    
}
