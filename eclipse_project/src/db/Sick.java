package db;

public class Sick extends Entity {
    // Fields
    private String name;
    private String description;
    private String type;

    // Constructs
    public Sick (String id) {
        super(id);
    }

    // Getters
    public String getName () {
        return this.name;
    }

    // Setters
    public void setName (String name) {
        this.name = name;
    }

    // Methods
    @Override
    public String toString() {
        return String.format("id: %s; name: %s", id, name);
    }
}
