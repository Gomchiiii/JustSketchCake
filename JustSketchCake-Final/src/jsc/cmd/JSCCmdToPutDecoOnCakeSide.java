package jsc.cmd;

import java.awt.Point;
import jsc.JSC;
import jsc.JSCCursorMgr;
import jsc.JSCDecoStamp;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToPutDecoOnCakeSide extends XLoggableCmd {
    // fields
    private Point mScreenPt = null;
    private Point mWorldPt = null;
    
    // private constructor
    private JSCCmdToPutDecoOnCakeSide(XApp app, Point pt) {
        super(app);
        this.mScreenPt = pt;
    }
    
    // JSCCmdToPutDecoOnCakeUpper.execute(jsc, pt)
    public static boolean execute(XApp app, Point pt) {
        JSCCmdToPutDecoOnCakeSide cmd = new JSCCmdToPutDecoOnCakeSide(app, pt);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        JSCCursorMgr cursorMgr = jsc.getCursorMgr();
        this.mWorldPt = 
            jsc.getXform().calcPtFromScreenToSideWorld(this.mScreenPt);
        jsc.getJSCCakeSideMgr().getSideDecoStampMgr().getDecoStamps().
            add(new JSCDecoStamp(cursorMgr.getCurCursor(), 
            this.mWorldPt, cursorMgr.getCurCursorScale()));
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
