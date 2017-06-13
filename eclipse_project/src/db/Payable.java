package db;

/**
 * Primary class use for cash flow log
 * If you need to use cash flow log feature
 * implement this class for object you intend to use
 * @author ducnh
 * create: 12-05-2017
 */
public abstract class Payable extends Entity {

    public static Object[] toObjects (Payable p) {
        Object[] rs = new Object[4];
        rs[0] = p.getId();
        rs[1] = p.getType();
        rs[2] = p.getPrice();
        rs[3] = p.getPayNote();
        return rs;
    }

    protected String payNote;
    protected int price;

    // Getter
    public abstract String getType();
    public abstract String getDescription();
    public abstract String getProviderId();
    public String getPayNote() {
        return this.payNote;
    }
    public int getPrice() {
        return this.price;
    }
    
    protected Payable(){}
    protected Payable(String id) {
        super(id);
    }

    // Setter
    public void setPayNote(String payNote) {
        this.payNote = payNote;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
