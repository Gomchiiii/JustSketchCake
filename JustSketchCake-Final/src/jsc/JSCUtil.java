package jsc;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class JSCUtil {
    //constructor
    public JSCUtil() {};
    
    //methods 
    public static BufferedImage readImage(String filename){        
        BufferedImage Image = null;

        try {
            InputStream resourceStream = JSCUtil.class.
                getClassLoader().getResourceAsStream(filename);
            Image = (BufferedImage) ImageIO.read(resourceStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return Image;
    }
    
    public static Shape imageToShape(BufferedImage image) {
        Area area = new Area();
        Rectangle rectangle = new Rectangle();
        int y1, y2;
        for (int x = 0; x < image.getWidth(); x++) {
            y1 = Integer.MAX_VALUE;
            y2 = -1;
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                rgb = rgb >>> 24;
                if (rgb > 0) {
                    if (y1 == Integer.MAX_VALUE) {
                        y1 = y;
                        y2 = y;
                    }
                    if (y > (y2 + 1)) {
                        rectangle.setBounds(x, y1, 1, y2 - y1 + 1);
                        area.add(new Area(rectangle));
                        y1 = y;
                    }
                    y2 = y;
                }
            }
            if ((y2 - y1) >= 0) {
                rectangle.setBounds(x, y1, 1, y2 - y1 + 1);
                area.add(new Area(rectangle));
            }
        }
        return area;
    }
    
    public static BufferedImage mapImageOnCylinder(BufferedImage Image) {
        double r = JSC.CAKE_R;
        int w = Image.getWidth();
        int h = 400;
        BufferedImage CylindericalMappedImg = 
            new BufferedImage(w, h, 
            BufferedImage.TYPE_INT_ARGB);

        int x0 = (int)Math.floor(w / 2); // O(x0, y0) 
        
        double theta, sin;
        int x, y;

        for (int t = (int)(x0 - r); t < x0 + r; t++) {
            theta = Math.acos((t -x0) / r);
            sin = Math.sin(theta);
            
            x = (int)Math.round(t / sin - x0 / sin) +  x0;
            
            for (int v = 0; v < h; v++) {
                y = (int)Math.round((v + ((r - sin * r)) / 2 ));
                if (x >= 0 && y >= 200 && x < w && y < 400){
                    CylindericalMappedImg.setRGB(t, v, 
                        Image.getRGB(x, y));
                }
            }
        }
    return CylindericalMappedImg;
    }
    
    public static BufferedImage indexingImgforRotate(
        BufferedImage Image, int initial_index, double rotate_r) {
        
        int r = (int)Math.round(rotate_r);
        int w = Image.getWidth();
        int h = Image.getHeight();
        BufferedImage indexedImg = 
            new BufferedImage(w, h, 
            BufferedImage.TYPE_INT_ARGB);
        
        for (int i = 0; i < Math.PI*r; i++){
            for (int j = 0; j < h; j++){
                if (initial_index + i < w && initial_index + i >=0){
                    indexedImg.setRGB(initial_index + i, j, 
                        Image.getRGB(i, j));
                } else if (initial_index + i < 0) {
                    indexedImg.setRGB(initial_index + i + w, j, 
                        Image.getRGB(i, j));
                } else {
                    indexedImg.setRGB(initial_index + i - w, j, 
                        Image.getRGB(i, j));
                }
            }
        }
        return indexedImg;
    }
    
    public static void drawIcingPtCurve(
        Graphics2D g2, JSCIcingPtCurve ptCurve) {
        Path2D.Double path = new Path2D.Double();
        ArrayList<Point> pts = ptCurve.getPts();
        if (pts.size() < 2){
            return;
        }
        
        Point pt0 = pts.get(0);
        path.moveTo(pt0.x,pt0.y);
        for (int i = 1; i < pts.size(); i++) {
            Point pt = pts.get(i);
            path.lineTo(pt.x,pt.y);
        }
        
        g2.setColor(ptCurve.getColor());
        g2.setStroke(ptCurve.getIcingStroke());
        g2.draw(path);
    }
    
}
