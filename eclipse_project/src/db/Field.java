package db;

public class Field {
    
    public static final short DATA_TYPE_INT = 4;
    public static final short DATA_TYPE_SHORT = 2;
    public static final short DATA_TYPE_LONG = 8;
    public static final short DATA_TYPE_FLOAT = 16;
    public static final short DATA_TYPE_DOUBLE = 32;
    public static final short DATA_TYPE_STRING = 64;
    
    private String label;
    private short dataTypeCode;
    private Class<?> dataType;
    private String functionName;
    
    /**
     * No argument constructor
     */
    public Field() {
        this.label = "";
        this.dataTypeCode = -1;
        this.dataType = null;
        this.functionName = "";
    }
    
    /**
     * All argument constructor
     * 
     * @param label
     * @param dataTypeCode
     * @param dataType
     * @param functionName
     */
    public Field(String label, short dataTypeCode, Class<?> dataType, String functionName) {
        this.label = label;
        this.dataTypeCode = dataTypeCode;
        this.dataType = dataType;
        this.functionName = functionName;
    }
    
    // Getter
    public String getLabel() {
        return this.label;
    }
    public short getDataTypeCode() {
        return this.dataTypeCode;
    }
    public Class<?> getDataType() {
        return this.dataType;
    }
    public String getFunctionName() {
        return this.functionName;
    }
    
    // Setter
    public void setLabel(String label) {
        this.label = label;
    }
    public void setDataTypeCode(short dataTypeCode) {
        this.dataTypeCode = dataTypeCode;
    }
    public void setDataType(Class<?> dataType) {
        this.dataType = dataType;
    }
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}
