package common;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;

/**
 * Class store general configurations, constants
 * 
 * @author ducnh
 * create: 12-05-2017
 */
public class Constants {
    
    public static Image APP_ICON;
    
    static {
        // app icon
        try {
            URL url = ClassLoader.getSystemResource("res/icon.png");
            APP_ICON = Toolkit.getDefaultToolkit().createImage(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Font configuration
    public static final String globalFontName = "Monospace";
    
    // Button size configuration template
    public static final Dimension buttonSizeBg = new Dimension(120, 50);
    public static final Dimension buttonSizeMd = new Dimension(90, 32);
    public static final Dimension buttonSizeSm = new Dimension(50, 25);
    
    // Font size configuration template
    public static final Font fontSizeMd = new Font(globalFontName, Font.PLAIN, 13);
    public static final Font fontSizeSm = new Font(globalFontName, Font.PLAIN, 11);
    public static final Font fontSizeBg = new Font(globalFontName, Font.BOLD, 18);
    
    // Payable object types
    public static final String PAYABLE_OBJECT_TYPE_PIG = "Heo";
    
    public static final String HOME_COMMAND = "home";
    
    // Observable change code
    public static final short OBSERVABLE_STATE_CHANGED = 1;

    // Action command
    public static final String AC_HOME = "go_home";

    /**
     * Get payable represent code by full code
     * 
     * @param pt
     * @return
     */
    public static String getPayableCode(String pt) {
        if (pt == null)
            return "";
        if ("Heo".equals(pt))
            return "H";
        if ("Tien".equals(pt))
            return "M";
        if ("Vat Dung".equals(pt))
            return "V";
        if ("Tinh".equals(pt))
            return "T";
        return "";
    }
}
