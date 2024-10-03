package jsc;

public class JSCSurfaceMgr {
    // constants
    public static final int OFFSET_UPDATE_INCREMENT = 3; 
    
    //fields 
    private JSC mJSC = null;
    protected int offset_x = 0;
    public int getOffset_x() {
        return this.offset_x;
    }
    public void setOffset_x(int offset) {
        this.offset_x = offset;
    }
    
    
    //constructor
    public JSCSurfaceMgr (JSC jsc) {
        this.mJSC = jsc;
        this.offset_x = 0;
    }
    
    //methods
    public void updateOffset(int increment){
        this.offset_x += increment;
        if (Math.abs(this.offset_x) > 2*JSC.CAKE_R*Math.PI){
            this.offset_x = 0;
        }
    }

}
