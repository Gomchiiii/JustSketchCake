package jsc;

import java.util.ArrayList;

public class JSCDecoStampMgr {
    // fields
    private ArrayList<JSCDecoStamp> mDecoStamps = null;
    public ArrayList<JSCDecoStamp> getDecoStamps() {
        return this.mDecoStamps;
    }
    
    // constructor
    public JSCDecoStampMgr() {
        this.mDecoStamps = new ArrayList<JSCDecoStamp>();
    }
}
