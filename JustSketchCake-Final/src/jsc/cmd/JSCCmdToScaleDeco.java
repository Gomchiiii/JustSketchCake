package jsc.cmd;

import jsc.JSC;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToScaleDeco extends XLoggableCmd {
    // fields
    private int mRotation = 0;
    private int mScale = 0;
    
    // private constructor
    private JSCCmdToScaleDeco(XApp app, int rotation) {
        super(app);
        this.mRotation = rotation;
    }
    
    // JSICmdToScaleDeco.execute(jsc, rotation);
    public static boolean execute(XApp app, int rotation) {
        JSCCmdToScaleDeco cmd = new JSCCmdToScaleDeco(app, rotation);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        this.mScale = 
            jsc.getCursorMgr().getCurCursorScale() + this.mRotation * 4;
        if (this.mScale < 20) {
            this.mScale = 20;
        } else if (this. mScale > 120) {
            this.mScale = 120;
        }
        jsc.getCursorMgr().setCurCursorScale(this.mScale);
        
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
