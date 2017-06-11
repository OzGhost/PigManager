package db;

import java.sql.ResultSet;

import common.Constants;
/**
 * Store data for each row of pig table
 * table name: Heo
 * nested table: MoveHistory
 * @author ducnh
 * create: 15-04-2017
 */
public class Pig extends Payable {

    private static final String RETRIEVE_LITE_VERSION_BY_ID
        = " SELECT MaHeo, MaTheTai"
        + " FROM Heo"
        + " WHERE MaHeo='%s'"
    ;

    public static Pig find(String id) {
        String lid = id.replaceAll("[^0-9]", "");
        Pig rs = new Pig();
        ResultSet qRs = null;
        try {
            qRs = db.sendForResult(
                String.format(RETRIEVE_LITE_VERSION_BY_ID, lid)
            );
            qRs.next();
            rs.setId(qRs.getString("MaHeo"));
            rs.setEarTag(qRs.getString("MaTheTai"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { qRs.close(); } catch (Exception e) {}
        }
        return rs;
    }
    
    private float height;
    private float width;
    private float weight;
    private String inDate;
    private String outDate;
    private String earTag;
    private Pig source;
    private Provider provider;
//    private FoodKind foodKind;
//    private Pasture pasture;
//    private List<MoveHistory> moveHistory;
    
    /**
     * Default constructor
     */
    public Pig() {
        this.id = "";
        this.weight = 0;
        this.earTag = "1230";
    }
    public Pig(String id) {
        super(id);
    }
    /**
     * Lite constructor for pay log
     * @param id
     * @param weight
     * @param eartag
     * @param provider
     */
    public Pig(String id, float weight, String eartag, Provider provider) {
        this.id = id;
        this.weight = 150;
        this.earTag = eartag;
        this.provider = provider;
    }
    
    /**
     * All argument constructor
     * @param id
     * @param height
     * @param width
     * @param weight
     * @param inDate
     * @param outDate
     * @param earTag
     * @param source
     */
    public Pig(String id, float height,
            float width, float weight, String inDate,
            String outDate, String earTag, Pig source){
        this.id = id;
        this.height = height;
        this.width = width;
        this.weight = weight;
        this.inDate = inDate;
        this.outDate = outDate;
        this.earTag = earTag;
        this.source = source;
    }

    // Getter
    @Override
    public String getId() {
        return this.id;
    }
    @Override
    public String getType() {
        return Constants.PAYABLE_OBJECT_TYPE_PIG;
    }
    @Override
    public String getProviderId() {
        return provider == null ? "" : provider.getId();
    }
    public String getEarTag() {
        return this.earTag;
    }

    // Setter
    public void setEarTag (String earTag) {
        this.earTag = earTag;
    }

    // Method
    @Override
    public String getDescription() {
        return "Ear tag: " + this.earTag
                + "; Weight: " + this.weight
                + "; Provider: " + (this.provider == null
                        ? "null"
                        : this.provider.getName());
    }

    public void clone (Pig p) {
        this.id = p.getId();
        this.earTag = p.getEarTag();
    }

    public void selfCompleteLiteVersion () {
        clone( find(this.id) );
    }

    @Override
    public String toString() {
        return String.format("id: %s; earTag: %s", this.id, this.earTag);
    }
}
