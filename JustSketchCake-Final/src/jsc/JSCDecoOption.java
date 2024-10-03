package jsc;

import Scalr.Scalr;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class JSCDecoOption {
    // constants
    private static final int IMG_WIDTH = 60;
    private static final int IMG_HEIGHT = 60;
    
    private static final BufferedImage MANDARIN_IMG = Scalr.resize(JSCUtil.
        readImage("Images/mandarin.png"), 
        Scalr.Method.BALANCED, 
        JSCDecoOption.IMG_WIDTH, JSCDecoOption.IMG_HEIGHT);
    private static final BufferedImage STRAWBERRY_SIDE_IMG = 
        Scalr.resize(JSCUtil.readImage("Images/strawberry_side.png"), 
        Scalr.Method.BALANCED, 
        JSCDecoOption.IMG_WIDTH, JSCDecoOption.IMG_HEIGHT);
    private static final BufferedImage STRAWBERRY_UP_IMG = 
        Scalr.resize(JSCUtil.readImage("Images/strawberry_up.png"), 
        Scalr.Method.BALANCED, 
        JSCDecoOption.IMG_WIDTH, JSCDecoOption.IMG_HEIGHT);
    private static final BufferedImage BLUEBERRY_IMG = 
        Scalr.resize(JSCUtil.readImage("Images/blueberry.png"), 
        Scalr.Method.BALANCED, 
        JSCDecoOption.IMG_WIDTH, JSCDecoOption.IMG_HEIGHT);
    private static final BufferedImage CHERRY_IMG = 
        Scalr.resize(JSCUtil.readImage("Images/cherry.png"), 
        Scalr.Method.BALANCED, 
        JSCDecoOption.IMG_WIDTH, JSCDecoOption.IMG_HEIGHT);
    private static final BufferedImage GRAPE_SIDE_IMG = 
        Scalr.resize(JSCUtil.readImage("Images/grape_side.png"), 
        Scalr.Method.BALANCED, 
        JSCDecoOption.IMG_WIDTH, JSCDecoOption.IMG_HEIGHT);
    private static final BufferedImage GRAPE_UP_IMG = 
        Scalr.resize(JSCUtil.readImage("Images/grape_up.png"), 
        Scalr.Method.BALANCED, 
        JSCDecoOption.IMG_WIDTH, JSCDecoOption.IMG_HEIGHT);
    private static final BufferedImage SPRINKLE_1_IMG = 
        Scalr.resize(JSCUtil.readImage("Images/sprinkle_1.png"), 
        Scalr.Method.BALANCED, 
        JSCDecoOption.IMG_WIDTH, JSCDecoOption.IMG_HEIGHT);
    private static final BufferedImage SPRINKLE_2_IMG = 
        Scalr.resize(JSCUtil.readImage("Images/sprinkle_2.png"), 
        Scalr.Method.BALANCED, 
        JSCDecoOption.IMG_WIDTH, JSCDecoOption.IMG_HEIGHT);
    private static final BufferedImage CHOCOLATE_CIRCLE_IMG = 
        Scalr.resize(JSCUtil.readImage("Images/chocolate_circle.png"), 
        Scalr.Method.BALANCED, 
        JSCDecoOption.IMG_WIDTH, JSCDecoOption.IMG_HEIGHT);
    private static final BufferedImage CHOCOLATE_HEART_IMG = 
        Scalr.resize(JSCUtil.readImage("Images/chocolate_heart.png"), 
        Scalr.Method.BALANCED, 
        JSCDecoOption.IMG_WIDTH, JSCDecoOption.IMG_HEIGHT);
    private static final BufferedImage CHOCOLATE_RECTANGLE_IMG = 
        Scalr.resize(JSCUtil.readImage(
        "Images/chocolate_rectangle.png"), 
        Scalr.Method.BALANCED, 
        JSCDecoOption.IMG_WIDTH, JSCDecoOption.IMG_HEIGHT);
    
    // fields
    public ArrayList<BufferedImage> mDecoOptions = null;
    
    
    // constructor
    public JSCDecoOption() {
        this.mDecoOptions = new ArrayList<BufferedImage>();
        mDecoOptions.add(MANDARIN_IMG);
        mDecoOptions.add(STRAWBERRY_SIDE_IMG);
        mDecoOptions.add(STRAWBERRY_UP_IMG);
        mDecoOptions.add(BLUEBERRY_IMG);
        mDecoOptions.add(CHERRY_IMG);
        mDecoOptions.add(GRAPE_SIDE_IMG);
        mDecoOptions.add(GRAPE_UP_IMG);
        mDecoOptions.add(SPRINKLE_1_IMG);
        mDecoOptions.add(SPRINKLE_2_IMG);
        mDecoOptions.add(CHOCOLATE_CIRCLE_IMG);
        mDecoOptions.add(CHOCOLATE_HEART_IMG);
        mDecoOptions.add(CHOCOLATE_RECTANGLE_IMG);
        
    }

}
