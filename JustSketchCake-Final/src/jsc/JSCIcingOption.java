package jsc;

import Scalr.Scalr;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static jsc.JSCUtil.imageToShape;

public class JSCIcingOption {
    // constants
    private static final int IMG_WIDTH = 60;
    private static final int IMG_HEIGHT = 60;
    
    private static final int STROKE_WIDTH = 20;
    private static final int STROKE_HEIGHT = 20;
    
    private static final BufferedImage ICING_OPTION_1_IMG = Scalr.resize(
        JSCUtil.readImage("Images/icing_1.png"), 
        Scalr.Method.BALANCED, 
        JSCIcingOption.IMG_WIDTH, JSCIcingOption.IMG_HEIGHT);
    private static final BufferedImage ICING_OPTION_2_IMG = Scalr.resize(
        JSCUtil.readImage("Images/icing_2.png"), 
        Scalr.Method.BALANCED, 
        JSCIcingOption.IMG_WIDTH, JSCIcingOption.IMG_HEIGHT);
    private static final BufferedImage ICING_OPTION_3_IMG = Scalr.resize(
        JSCUtil.readImage("Images/icing_3.png"), 
        Scalr.Method.BALANCED, 
        JSCIcingOption.IMG_WIDTH, JSCIcingOption.IMG_HEIGHT);
    private static final BufferedImage ICING_OPTION_4_IMG = Scalr.resize(
        JSCUtil.readImage("Images/icing_4.png"), 
        Scalr.Method.BALANCED, 
        JSCIcingOption.IMG_WIDTH, JSCIcingOption.IMG_HEIGHT);
    private static final BufferedImage ICING_OPTION_5_IMG = Scalr.resize(
        JSCUtil.readImage("Images/icing_5.png"), 
        Scalr.Method.BALANCED, 
        JSCIcingOption.IMG_WIDTH, JSCIcingOption.IMG_HEIGHT);
    private static final BufferedImage ICING_OPTION_6_IMG = Scalr.resize(
        JSCUtil.readImage("Images/icing_6.png"), 
        Scalr.Method.BALANCED, 
        JSCIcingOption.IMG_WIDTH, JSCIcingOption.IMG_HEIGHT);
    
    private static final BufferedImage ICING_STROKE_1_IMG = Scalr.resize(
        JSCUtil.readImage("Images/icing_stroke_1.png"), 
        Scalr.Method.BALANCED, 
        JSCIcingOption.STROKE_WIDTH, 
        JSCIcingOption.STROKE_HEIGHT);
    private static final BufferedImage ICING_STROKE_2_IMG = Scalr.resize(
        JSCUtil.readImage("Images/icing_stroke_2.png"), 
        Scalr.Method.BALANCED, 
        JSCIcingOption.STROKE_WIDTH, 
        JSCIcingOption.STROKE_HEIGHT);
    private static final BufferedImage ICING_STROKE_3_IMG = Scalr.resize(
        JSCUtil.readImage("Images/icing_stroke_3.png"), 
        Scalr.Method.BALANCED, 
        JSCIcingOption.STROKE_WIDTH, 
        JSCIcingOption.STROKE_HEIGHT);
    private static final BufferedImage ICING_STROKE_4_IMG = Scalr.resize(
        JSCUtil.readImage("Images/icing_stroke_4.png"), 
        Scalr.Method.BALANCED, 
        JSCIcingOption.STROKE_WIDTH, 
        JSCIcingOption.STROKE_HEIGHT);
    private static final BufferedImage ICING_STROKE_5_IMG = Scalr.resize(
        JSCUtil.readImage("Images/icing_stroke_5.png"), 
        Scalr.Method.BALANCED, 
        JSCIcingOption.STROKE_WIDTH, 
        JSCIcingOption.STROKE_HEIGHT);
    private static final BufferedImage ICING_STROKE_6_IMG = Scalr.resize(
        JSCUtil.readImage("Images/icing_stroke_6.png"), 
        Scalr.Method.BALANCED, 
        JSCIcingOption.STROKE_WIDTH, 
        JSCIcingOption.STROKE_HEIGHT);
    
    // fields
    public ArrayList<BufferedImage> mIcingOptions = null;
    public ArrayList<Shape> mIcingStrokeShapes = null;
     
    // constructor
    public JSCIcingOption() {
        this.mIcingOptions = new ArrayList<BufferedImage>();
        this.mIcingStrokeShapes = new ArrayList<Shape>();
        
        this.mIcingOptions.add(JSCIcingOption.ICING_OPTION_1_IMG);
        this.mIcingOptions.add(JSCIcingOption.ICING_OPTION_2_IMG);
        this.mIcingOptions.add(JSCIcingOption.ICING_OPTION_3_IMG);
        this.mIcingOptions.add(JSCIcingOption.ICING_OPTION_4_IMG);
        this.mIcingOptions.add(JSCIcingOption.ICING_OPTION_5_IMG);
        this.mIcingOptions.add(JSCIcingOption.ICING_OPTION_6_IMG);
        
        this.mIcingStrokeShapes.add(
            imageToShape(JSCIcingOption.ICING_STROKE_1_IMG));
        this.mIcingStrokeShapes.add(
            imageToShape(JSCIcingOption.ICING_STROKE_2_IMG));
        this.mIcingStrokeShapes.add(
            imageToShape(JSCIcingOption.ICING_STROKE_3_IMG));
        this.mIcingStrokeShapes.add(
            imageToShape(JSCIcingOption.ICING_STROKE_4_IMG));
        this.mIcingStrokeShapes.add(
            imageToShape(JSCIcingOption.ICING_STROKE_5_IMG));
        this.mIcingStrokeShapes.add(
            imageToShape(JSCIcingOption.ICING_STROKE_6_IMG));
    }
}
