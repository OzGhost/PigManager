package common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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
    public static final Dimension buttonSizeLBg = new Dimension(180, 50);
    public static final Dimension buttonSizeMd = new Dimension(90, 32);
    public static final Dimension buttonSizeLMd = new Dimension(120, 32);
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
    public static final String AC_BROWSE = "browse";
    public static final String AC_CANCEL = "terminated";
    public static final String AC_ADD = "one_more";
    public static final String AC_RM = "one_less";
    public static final String AC_PIG_FEAT = "pig_features";
    public static final String AC_STOCK_FEAT = "stock_features";
    public static final String AC_PASTURE_FEAT = "pasture_features";
    public static final String AC_PROVIDER_FEAT = "provider_features";
    public static final String AC_REPORT_FEAT = "report_features";
    public static final String AC_MAKE_REPORT = "make_some_report";

    // Date format
    public static final String FORMAT_OF_DATE = "yyyy-MM-dd HH:mm:ss";
    public static final DateFormat DATE4MAT = new SimpleDateFormat(FORMAT_OF_DATE);

    // Border style
    public static final Border BD_GREYLINE = BorderFactory
        .createLineBorder(Color.gray);

    // Time period parser
    public static final Map<String, String> TIME_STAGE = new HashMap<>();

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

    public static final Map<String, String> FULL_TYPE_NAME
        = new HashMap<>();

    static {
        // load app icon
        try {
            URL url = ClassLoader.getSystemResource("res/icon.png");
            APP_ICON = Toolkit.getDefaultToolkit().createImage(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // load time period
        TIME_STAGE.put("Năm", "Y");
        TIME_STAGE.put("Quý", "Q");
        TIME_STAGE.put("Tháng","MON");
        TIME_STAGE.put("Tuần","WW");
        TIME_STAGE.put("Ngày","J");

        // load type name
        FULL_TYPE_NAME.put("H", "Heo");
        FULL_TYPE_NAME.put("M", "Tien");
        FULL_TYPE_NAME.put("V", "Vat Dung");
        FULL_TYPE_NAME.put("T", "Tinh");
    }

}
