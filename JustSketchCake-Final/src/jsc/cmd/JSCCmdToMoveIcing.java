package jsc.cmd;

import java.awt.Point;
import jsc.JSC;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToMoveIcing extends XLoggableCmd {
    // fields
    private Point mCurCursorPt = null;
    
    // private constructor
    private JSCCmdToMoveIcing(XApp app, Point pt) {
        super(app);
        this.mCurCursorPt = pt;
    }
    
    // JSCCmdToMoveIcing.execute(jsc, pt);
    public static boolean execute(XApp app, Point pt) {
        JSCCmdToMoveIcing cmd = new JSCCmdToMoveIcing(app, pt);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        jsc.getCursorMgr().setCurCursorPt(this.mCurCursorPt);
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mCurCursorPt);
        return sb.toString();
    }
    
}
