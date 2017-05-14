package db;

/**
 * Store query as vary part then rend to use
 * @author ducnh
 * create: 16-04-2017
 */
public class QueryHolder {

    public static final short MODE_SELECT = 0;
    public static final short MODE_UPDATE = 1;
    public static final short MODE_INSERT = 2;
    public static final short MODE_DELETE = 3;
    
    private String tableName;
    private String select;
    private String insert;
    private String update;
    private String where;
    private String order;
    private String group;
    private int limit;
    private int offset;
    private short mode;

    /**
     * Private constructor: prevent create instance of this class
     */
    public QueryHolder(){
        this.tableName = null;
        this.select = null;
        this.insert = null;
        this.update = null;
        this.where = null;
        this.group = null;
        this.order = null;
        this.limit = 50;
        this.offset = 0;
        this.mode = -1;
    }

    private String inputFilter(String[] in) {
       final StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (String c: in){
            String trimmed = c == null ? "" : c.trim();
            if (!trimmed.isEmpty()) {
                sb.append(prefix + trimmed);
                prefix = ", ";
            }
        }
        if (", ".equals(prefix)) {
            return sb.toString();
        }
        return null;
    }
    private String inputFilter(String in) {
        if (in == null || in.trim().isEmpty())
            return null;
        return in.trim();
    }

    // Setter
    public void setTableName(String tbName) {
        this.tableName = inputFilter(tbName);
    }
    public void setMode(short mode) {
        this.mode = mode;
    }
    public void setSelectColumns(String... col){
        this.select = inputFilter(col);
    }
    public void setInsertElement(String e){
        this.insert = inputFilter(e);
    }
    public void setUpdateSetClause(String setClause) {
        this.update = inputFilter(setClause);
    }
    public void setWhereCondition(String whereClause) {
        this.where = inputFilter(whereClause);
    }
    public void setGroupByColumns(String... gcol) {
        this.group = inputFilter(gcol);
    }
    public void setOrderByColumns(String... ocol) {
        this.order = inputFilter(ocol);
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        // invalid mode case
        if (mode < 0 || mode > 3)
            return "";
        // select mode case
        if (mode == MODE_SELECT) {
            // check input
            if ( select == null || tableName == null)
                return "";
            if (limit > 0) {
                if (offset > 0) {
                    where = "ROWCOUNT > " + offset +
                            " AND ROWCOUNT <= " + (offset + limit) +
                            " AND " + where;
                } else {
                    where = "ROWCOUNT <= " + limit +
                            " AND " + where;
                }
            }
            return "SELECT " + select +
                    " FROM " + tableName +
                    (where == null ? "" : " WHERE " + where) +
                    (group == null ? "" : " GROUP BY " + group) +
                    (order == null ? "" : " ORDER BY " + order)
                    ;
        }
        // update mode case
        if (mode == MODE_UPDATE) {
            // input check
            if (update == null || tableName == null)
                return "";
            return "UPDATE " + tableName +
                    " SET " + update +
                    (where == null ? "" : " WHERE " + where)
                    ;
        }
        return "";
    }

    /**
     * Send rended query to database
     */
    public boolean go() {
        final String cmd = this.toString();
        System.out.println(cmd);
        if (cmd.isEmpty()) {
            System.out.println(" ---- WARNING: Query empty or lack some part " +
                    " -> skiped ----");
            return false;
        }
        return db.send(cmd);
    }

    public QueryHolder set(String... setClause) {
        this.update = inputFilter(setClause);
        this.mode = MODE_UPDATE;
        return this;
    }
}
