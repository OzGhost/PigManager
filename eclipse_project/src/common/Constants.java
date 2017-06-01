package common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * Class store general configurations, constants
 * 
 * @author ducnh
 * create: 12-05-2017
 */
public class Constants {
    
    // icon App
    public static Image APP_ICON;

    // view size
    private static final int vheight = 800;
    private static final int vwidth = 600;
    public static final Dimension VIEW_SIZE = new Dimension(vheight, vwidth);
        
    // Font configuration
    public static final String globalFontName = "Monospace";

    // Size
    public static final int MEDIUM_HEIGHT = 32;
    public static final int MEDIUM_LONG_WIDTH = 256;
    
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
    
    // Observable change code
    public static final short OBSERVABLE_STATE_CHANGED = 1;

    // Action command
    public static final String AC_HOME = "go_home";
    public static final String AC_DONE = "finish";
    public static final String AC_CANCEL = "terminated";
    public static final String AC_ADD = "one_more";
    public static final String AC_RM = "one_less";

    // Border style
    public static final Border BD_GREYLINE = BorderFactory
        .createLineBorder(Color.gray);

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

    static {
        // load app icon
        try {
            URL url = ClassLoader.getSystemResource("res/icon.png");
            APP_ICON = Toolkit.getDefaultToolkit().createImage(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
