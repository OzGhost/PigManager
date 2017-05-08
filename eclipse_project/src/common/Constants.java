package common;

import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final String globalFontName = "Dejavu Sans";
    public static final Dimension buttonSizeBg = new Dimension(120, 50);
    public static final Dimension buttonSizeMd = new Dimension(90, 32);
    public static final Dimension buttonSizeSm = new Dimension(50, 25);
    
    public static final Font fontSizeMd = new Font(globalFontName, Font.PLAIN, 13);
    public static final Font fontSizeSm = new Font(globalFontName, Font.PLAIN, 11);
    public static final Font fontSizeBg = new Font(globalFontName, Font.BOLD, 18);
    
    public static final String PAYABLE_OBJECT_TYPE_PIG = "Heo";
    
    public static final String HOME_COMMAND = "home";

    public static final Map<String, String> PAYABLE_TYPE = new HashMap<>();

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
