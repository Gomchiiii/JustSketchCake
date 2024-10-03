package jsc;

import javax.swing.JFrame;
import x.XApp;
import x.XLogMgr;
import x.XScenarioMgr;

public class JSC extends XApp {
    public static final double CAKE_R = 240.0;
    
    // fields
    private JFrame mFrame = null;
    private JSCCanvas2D mCanvas2D = null;
    public JSCCanvas2D getCanvas2D() {
        return this.mCanvas2D;
    }
    
    private JSCDecoOption mDecoOption = null;
    public JSCDecoOption getDecoOption() {
        return this.mDecoOption;
    }
    
    private JSCIcingOption mIcingOption = null;
    public JSCIcingOption getIcingOption() {
        return this.mIcingOption;
    }
    
    private JSCXform mXform = null;
    public JSCXform getXform() {
        return this.mXform;
    }
    
    private JSCEventListener mEventListener = null;
    
    private JSCColorChooser mColorChooser = null;
    public JSCColorChooser getColorChooser() {
        return this.mColorChooser;
    }
    
    private JSCCakeUpperMgr mJSCCakeUpperMgr = null;
    public JSCCakeUpperMgr getJSCCakeUpperMgr() {
        return this.mJSCCakeUpperMgr;
    }
    
    private JSCCakeSideMgr mJSCCakeSideMgr = null;
    public JSCCakeSideMgr getJSCCakeSideMgr() {
        return this.mJSCCakeSideMgr;
    }
    
    private XScenarioMgr mScenarioMgr = null;
    @Override
    public XScenarioMgr getScenarioMgr() {
        return this.mScenarioMgr;
    }
    
    private XLogMgr mLogMgr = null;
    @Override
    public XLogMgr getLogMgr() {
        return this.mLogMgr;
    }
    
    private JSCCursorMgr mCursorMgr = null;
    public JSCCursorMgr getCursorMgr() {
        return this.mCursorMgr;
    }
    
    private JSCImageRenderer mImageRenderer = null;
    public JSCImageRenderer getImageRenderer() {
        return this.mImageRenderer;
    }
    
    
    public static void main(String[] args) {
        new JSC();
    }
    
    //constructor
    public JSC()    {
        // create components
        // 1) frame, 2) canvas, 3) other components, 4) event listeners,
        // 5) managers
        this.mFrame = new JFrame("JustSketchCake");
        this.mCanvas2D = new JSCCanvas2D(this);
        this.mDecoOption = new JSCDecoOption();
        this.mIcingOption = new JSCIcingOption();
        this.mXform = new JSCXform(this);
        this.mColorChooser = new JSCColorChooser();
        this.mEventListener = new JSCEventListener(this);
        this.mJSCCakeUpperMgr = new JSCCakeUpperMgr(this);
        this.mJSCCakeSideMgr = new JSCCakeSideMgr(this);
        this.mScenarioMgr = new JSCScenarioMgr(this);
        this.mLogMgr = new XLogMgr();
        this.mLogMgr.setPrintOn(true);
        this.mCursorMgr = new JSCCursorMgr(this);
        this.mImageRenderer = new JSCImageRenderer(this);
        
        // create event listener
        this.mCanvas2D.addMouseListener(this.mEventListener);
        this.mCanvas2D.addMouseMotionListener(this.mEventListener);
        this.mCanvas2D.addMouseWheelListener(mEventListener);
        this.mCanvas2D.setFocusable(true);
        this.mCanvas2D.addKeyListener(this.mEventListener);
        
        // build and show visual components
        this.mFrame.add(this.mCanvas2D);
        this.mFrame.setSize(1080, 720);
        this.mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mFrame.setVisible(true);
        
    }

}
