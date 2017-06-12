package db;

public class Money extends Payable {
    // Fields
    // Constructors
    public Money () {}

    // Methods
    @Override
    public String getType () {
        return Constants.get("M");
    }

    @Override
    public String getDescription() {
        return "Payback";
    }

    @Override
    public String getProviderId() {
        return "";
    }
}
