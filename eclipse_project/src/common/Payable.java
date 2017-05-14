package common;

/**
 * Primary class use for cash flow log
 * If you need to use cash flow log feature
 * implement this class for object you intend to use
 * @author ducnh
 * create: 12-05-2017
 */
public abstract class Payable {

    protected String payNote;
    protected int price;

    // Getter
    public abstract String getId();
    public abstract String getType();
    public abstract String getDescription();
    public abstract String getProviderId();
    public String getPayNote() {
        return this.payNote;
    }
    public int getPrice() {
        return this.price;
    }

    // Setter
    public void setPayNote(String payNote) {
        this.payNote = payNote;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
