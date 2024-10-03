package jsc;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class JSCColorChooser {
    // constants
    private static final int CELL_NUM_H = 40;
    private static final int CELL_NUM_B = 10;
    private static final float SATURATION_DEFAULT = 1f;
    public static final float MIN_SATURATION = 0f;
    public static final float MAX_SATURATION = 1f;
    private static final float OPAQUENESS_DEFAULT = 1f;
    
    // fields
    private Color[][] mColors = null;
    private float mSaturation = Float.NaN;
    public float getSaturation() {
        return mSaturation;
    }
    public void setSaturation(Float f) {
        this.mSaturation = f;
    }
    private float mOpaqueness = Float.NaN;
    public float getOpaqueness() {
        return this.mOpaqueness;
    }
    public void setOpaqueness(Float f) {
        this.mOpaqueness = f;
    }
    
    // constructor
    public JSCColorChooser() {
        this.mColors = new Color[JSCColorChooser.CELL_NUM_B][];
        for (int i = 0; i < JSCColorChooser.CELL_NUM_B; i++) {
            this.mColors[i] = new Color[JSCColorChooser.CELL_NUM_H];
        }
        this.mSaturation = JSCColorChooser.SATURATION_DEFAULT;
        this.mOpaqueness = JSCColorChooser.OPAQUENESS_DEFAULT;
        
        this.createCellColors();
    }

    public void createCellColors() {
        float db = 1f / (float)(JSCColorChooser.CELL_NUM_B - 1);
        float dh = 1f / (float)(JSCColorChooser.CELL_NUM_H - 1);
        
        for (int i = 0; i < JSCColorChooser.CELL_NUM_B; i++) {
            float b = db * (float)i;
            for (int j = 0; j < JSCColorChooser.CELL_NUM_H; j++) {
                float h = dh * (float)j;
                Color hsb = Color.getHSBColor(h,this.mSaturation,b);
                this.mColors[i][j] = new Color(hsb.getRed(), hsb.getGreen(), 
                    hsb.getBlue(), (int)(this.mOpaqueness * 255f));
            }
        }
    }
    
    public void drawCells(Graphics2D g2, int w, int h) {
        double ys = (double)h / 3.0 * 1.0;
        double ye = (double)h / 3.0 * 2.0;
        double dx = (double)w / (double)JSCColorChooser.CELL_NUM_H;
        double dy = (ye - ys) / (double)JSCColorChooser.CELL_NUM_B;
        
        for (int i = 0; i < JSCColorChooser.CELL_NUM_B; i++) {
            double y = ys + dy * (double)i;
            for (int j = 0; j < JSCColorChooser.CELL_NUM_H; j++) {
                double x = dx * (double)j;
                Rectangle2D rect = new Rectangle2D.Double(x, y, dx, dy);
                g2.setColor(this.mColors[i][j]);
                g2.fill(rect);
            }
        }
    }

    public Color calcColor(Point pt, int w, int h) {
        double ys = (double)h / 3.0 * 1.0;
        double ye = (double)h / 3.0 * 2.0;
        double dx = (double)w / (double)JSCColorChooser.CELL_NUM_H;
        double dy = (ye - ys) / (double)JSCColorChooser.CELL_NUM_B;
        
        int i = (int)((double)(pt.y - ys) / dy);
        int j = (int)((double)pt.x / dx);
        
        if (i < 0 || i >= JSCColorChooser.CELL_NUM_B) {
            return null;
        } else {
            return this.mColors[i][j];
        }
    }
}
