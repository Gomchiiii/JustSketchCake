package jsc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class JSCCanvas2D extends JPanel {
    // constants
    private static final Color COLOR_ICING_OPTIONS_BOX =
        Color.BLACK;
    private static final Color COLOR_DECO_OPTIONS_BOX =
        Color.BLACK;

    private static final double ICING_OPTIONS_BOX_TOP_ALLIGNMENT_X = 730.0;
    private static final double ICING_OPTIONS_BOX_TOP_ALLIGNMENT_Y = 60.0;
    private static final double ICING_OPTIONS_BOX_WIDTH = 220.0;
    private static final double ICING_OPTIONS_BOX_HEIGHT = 150.0;

    private static final double DECO_OPTIONS_BOX_TOP_ALLIGNMENT_X = 730.0;
    private static final double DECO_OPTIONS_BOX_TOP_ALLIGNMENT_Y = 230.0;
    private static final double DECO_OPTIONS_BOX_WIDTH = 290.0;
    private static final double DECO_OPTIONS_BOX_HEIGHT = 220.0;

    public static final Rectangle2D.Double DECO_BOX = new Rectangle2D.Double(
        JSCCanvas2D.DECO_OPTIONS_BOX_TOP_ALLIGNMENT_X, 
        JSCCanvas2D.DECO_OPTIONS_BOX_TOP_ALLIGNMENT_Y, 
        JSCCanvas2D.DECO_OPTIONS_BOX_WIDTH, 
        JSCCanvas2D.DECO_OPTIONS_BOX_HEIGHT);

    public static final Rectangle2D.Double ICING_BOX = new Rectangle2D.Double(
        JSCCanvas2D.ICING_OPTIONS_BOX_TOP_ALLIGNMENT_X, 
        JSCCanvas2D.ICING_OPTIONS_BOX_TOP_ALLIGNMENT_Y, 
        JSCCanvas2D.ICING_OPTIONS_BOX_WIDTH, 
        JSCCanvas2D.ICING_OPTIONS_BOX_HEIGHT);

        
    // fields
    private JSC mJSC = null;
    
    // constructor
    public JSCCanvas2D(JSC jsc) {
        this.mJSC = jsc;  
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        // turn on anti-alliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);
        
        // render common objects
        this.drawIcingOptions(g2);
        this.drawDecoOptions(g2);
        
        // render the current scene's objects
        JSCScene curScene = (JSCScene) this.mJSC.getScenarioMgr().getCurScene();
        curScene.renderWorldObjects(g2);
        curScene.renderScreenObjects(g2);
    }
    

    private void drawIcingOptions(Graphics2D g2) {
        g2.setColor(JSCCanvas2D.COLOR_ICING_OPTIONS_BOX);
        g2.draw(this.ICING_BOX);
        ArrayList<BufferedImage> icingOptions = 
            this.mJSC.getIcingOption().mIcingOptions;
        for (int i = 0; i < icingOptions.size(); i++) {
            if (i < 3) {
                g2.drawImage(icingOptions.get(i), 
                    740 + i * 70, 70, this);
            } else if (i < 6) {
                g2.drawImage(icingOptions.get(i), 740 + (i - 3) * 70, 
                    140, this);
            } else {   
            }
        }
    }
        
    private void drawDecoOptions(Graphics2D g2) {
        g2.setColor(JSCCanvas2D.COLOR_DECO_OPTIONS_BOX);
        g2.draw(this.DECO_BOX);
        ArrayList<BufferedImage> decoOptions = 
            this.mJSC.getDecoOption().mDecoOptions;
        for (int i = 0; i < decoOptions.size(); i++) {
            if (i < 4) {
                g2.drawImage(decoOptions.get(i), 740 + i * 70, 
                    240, this);
            } else if (i < 8) {
                g2.drawImage(decoOptions.get(i), 740 + (i - 4) * 70, 
                    310, this);
            } else if (i < 12) {
                g2.drawImage(decoOptions.get(i), 740 + (i - 8) * 70, 
                    380, this);
            } else {  
            }
        }
    }
}
