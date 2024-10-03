package jsc.cmd;

import java.awt.Point;
import jsc.JSC;
import jsc.JSCColorChooser;
import jsc.scenario.JSCColorScenario;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToRecalSaturationForColorCells extends XLoggableCmd {
    // fields
    private Point mCurPointForRecalSaturation = null;
    private float mSaturation = Float.NaN;
    
    // private constructor
    private JSCCmdToRecalSaturationForColorCells(XApp app, Point p) {
        super(app);
        this.mCurPointForRecalSaturation = p;
    }
    
    // JSICmdToRecalSaturationForColorCells.execute(jsi, p);
    public static boolean execute(XApp app, Point p) {
        JSCCmdToRecalSaturationForColorCells cmd = 
            new JSCCmdToRecalSaturationForColorCells(app, p);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        JSCColorChooser colorChooser = jsc.getColorChooser();
        JSCColorScenario colorScenario = JSCColorScenario.getSingleton();
        float w = jsc.getCanvas2D().getWidth();
        float curSaturation = colorScenario.getStartSaturation();
        this.mSaturation = curSaturation + 
            (((float) this.mCurPointForRecalSaturation.x - 
            (float) colorScenario.getStartCalForSaturation().x) / w);
        if (this.mSaturation > 1f) {
            this.mSaturation = 1f;
        } else if (this.mSaturation < 0f) {
            this.mSaturation = 0f;
        }
        colorChooser.setSaturation(this.mSaturation);
        colorChooser.createCellColors();
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mCurPointForRecalSaturation).append("\t");
        sb.append(this.mSaturation);
        return sb.toString();
    }
    
}
