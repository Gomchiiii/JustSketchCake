package jsc.cmd;

import java.awt.Point;
import jsc.JSC;
import jsc.JSCCursorMgr;
import jsc.JSCDecoStamp;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToPutDecoOnCakeUpper extends XLoggableCmd {
    // fields
    private Point mScreenPt = null;
    private Point mWorldPt = null;
    
    // private constructor
    private JSCCmdToPutDecoOnCakeUpper(XApp app, Point pt) {
        super(app);
        this.mScreenPt = pt;
    }
    
    // JSCCmdToPutDecoOnCakeUpper.execute(jsc, pt)
    public static boolean execute(XApp app, Point pt) {
        JSCCmdToPutDecoOnCakeUpper cmd = 
            new JSCCmdToPutDecoOnCakeUpper(app, pt);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        JSCCursorMgr cursorMgr = jsc.getCursorMgr();
        this.mWorldPt = 
            jsc.getXform().calcPtFromScreenToUpperWorld(this.mScreenPt);
        jsc.getJSCCakeUpperMgr().getUpperDecoStampMgr().getDecoStamps().
            add(new JSCDecoStamp(cursorMgr.getCurCursor(), 
            this.mWorldPt, cursorMgr.getCurCursorScale()));
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mScreenPt).append("\t");
        sb.append(this.mWorldPt).append("WorldPt");
        return sb.toString();
    }
    
}
