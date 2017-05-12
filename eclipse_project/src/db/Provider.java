package db;

/**
 * Store data for a record of provider table
 * table name: NhaCungCap
 * @author ducnh
 * create: 15-04-2017
 */
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
    
    // Getter
    public String getName() {
        return this.name;
    }
    public String getId() {
        return this.id;
    }
}
