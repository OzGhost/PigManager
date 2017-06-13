package db;

import common.Constants;

public class Tool extends Payable {

    // Fields
    private String name;
    private int cost;
    private String description;
    private Provider provider;
//    private Pasture pasture;

    // Constructors
    public Tool (String id) {
        super(id);
    }

    // Methods
    @Override
    public String getType () {
        return Constants.FULL_TYPE_NAME.get("V");
    }

    @Override
    public String getProviderId () {
        return provider == null ? null : provider.getId();
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
