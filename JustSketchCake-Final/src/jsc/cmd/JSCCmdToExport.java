package jsc.cmd;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jsc.JSC;
import x.XApp;
import x.XLoggableCmd;

public class JSCCmdToExport extends XLoggableCmd {
    // fields
    private BufferedImage mImage = null;
    // private constructor
    private JSCCmdToExport(XApp app) {
        super(app);
    }
    
    // JSICmdToCreateCurPtCurve.execute(jsi, pt)
    public static boolean execute(XApp app) {
        JSCCmdToExport cmd = new JSCCmdToExport(app);
        return cmd.execute();
    }
    @Override
    protected boolean defineCmd() {
        JSC jsc = (JSC) this.mApp;
        this.mImage = jsc.getImageRenderer().drawImageForExport();
        JFileChooser fc = new JFileChooser(".");
        int action = fc.showSaveDialog(null);
        if (action == JFileChooser.APPROVE_OPTION && 
            fc.getSelectedFile() != null) {
            File f = fc.getSelectedFile();
            if (f.getPath().toLowerCase().endsWith(
                "." + "png".toLowerCase()) == false) {
                f = new File(f.getPath() + "." + "png");
            }
            if (f.exists()) {
                int click = JOptionPane.showConfirmDialog(null,
                    "File '" + f.getPath() + "' already exists. Replace it?");
                if (click == JOptionPane.CANCEL_OPTION) {
                    return true;
                }
            }
            try {
                ImageIO.write(this.mImage, "png", f);
            } catch (IOException ex) {
                Logger.getLogger(JSCCmdToExport.class.getName()).
                    log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append("").append("\t");
        return sb.toString();
    }
    
}
