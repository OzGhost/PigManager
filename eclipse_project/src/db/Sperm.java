package db;

import java.util.Date;

import common.Constants;

public class Sperm extends Payable {

    // Fields
    private String name;
    private String source;
    private String property;
    private Provider provider;
    private Date createDate;
    private Date bestBefore;

    // Constructors
    public Sperm (String id) {
        super(id);
    }

    // Getters
    @Override
    public String getType () {
        return Constants.FULL_TYPE_NAME.get("T");
    }

    @Override
    public String getDescription () {
        return source + ", " + property;
    }

    @Override
    public String getProviderId() {
        return provider == null ? null : provider.getId();
    }

    // Setters
    // Methods
}
