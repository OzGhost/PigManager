package common;

public class RefPayload {
    private String tableName;
    private String fKeyField;
    private String fKeyVal;
    private String refField;
    
    /**
     * Tool for generate reference
     * @param tableName
     * @param fKeyField
     * @param fKeyVal
     * @param refField
     */
    public RefPayload (
            String tableName,
            String fKeyField,
            String fKeyVal,
            String refField
    ) {
       this.tableName = tableName;
       this.fKeyField = fKeyField;
       this.fKeyVal = fKeyVal;
       this.refField = refField;
    }
    
    @Override
    public String toString() {
        return String.format("SELECT REF(rf) INTO e.%s FROM %s rf WHERE rf.%s='%s';",
                this.refField,
                this.tableName,
                this.fKeyField,
                this.fKeyVal);
    }
}
