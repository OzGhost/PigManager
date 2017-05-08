package db;

public class Provider {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String description;
    private int owe;
    
    /**
     * Lite constructor for pay log
     * 
     * @param id
     * @param name
     */
    public Provider(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public String getId() {
        return this.id;
    }
}
