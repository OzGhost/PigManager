package db;

public class CashFlowDetail {
    private String payableObjectId;
    private String payableType;
    private int price;
    private String note;
    
    public CashFlowDetail(){}
    
    public CashFlowDetail(String id, String type, int price, String note) {
        this.payableObjectId = id;
        this.payableType = type;
        this.price = price;
        this.note = note;
    }

    // Getter
    public int getPrice() {
        return this.price;
    }
    public String getPayableObjectId() {
        return this.payableObjectId;
    }
    public String getPayableType() {
        return this.payableType;
    }
    public String getNote() {
        return this.note;
    }
    
    @Override
    public String toString() {
        return "oId: " + this.payableObjectId
                + "; oType: " + this.payableType
                + "; price: " + this.price
                + "; note: " + this.note;
    }
}
