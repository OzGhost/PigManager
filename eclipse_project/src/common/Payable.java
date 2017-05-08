package common;

public interface Payable {
    
    public Double cost = 0d;
    public String note = "";
    
    public String getId();
    public String getType();
    public String getDescription();
}
