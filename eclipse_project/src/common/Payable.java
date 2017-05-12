package common;

/**
 * Primary class use for cash flow log
 * If you need to use cash flow log feature
 * implement this class for object you intend to use
 * @author ducnh
 * create: 12-05-2017
 */
public interface Payable {
    // Getter
    public String getId();
    public String getType();
    public String getDescription();
}
