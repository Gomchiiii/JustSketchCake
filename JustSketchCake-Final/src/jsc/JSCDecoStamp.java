package jsc;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class JSCDecoStamp {
    // fields
    private BufferedImage mDecoImage = null;
    public BufferedImage getDecoImage() {
        return this.mDecoImage;
    }
    public void setDecoImage(BufferedImage image) {
        this.mDecoImage = image;
    }
    
    private Point mDecoLocation = null;
    public Point getDecoLocation() {
        return this.mDecoLocation;
    }
    public void setDecoLocation(Point pt) {
        this.mDecoLocation = pt;
    }
    
    private int mDecoSize = 0;
    public int getDecoSize() {
        return this.mDecoSize;
    }
    public void setDecoSize(int size) {
        this.mDecoSize = size;
    }
    
    // constructor
    public JSCDecoStamp(BufferedImage image, Point location, int size) {
        this.mDecoImage = image;
        this.mDecoLocation = location;
        this.mDecoSize = size;
    }
}
