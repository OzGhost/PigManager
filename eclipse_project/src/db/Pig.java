package db;

import common.Constants;
import common.Payable;
/**
 * Store data for each row of pig table
 * table name: Heo
 * nested table: MoveHistory
 * @author ducnh
 * create: 15-04-2017
 */
public class Pig implements Payable {
    
    private String id;
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
    public Pig(String id, float height, float width, float weight, String inDate, String outDate, String earTag, Pig source){
        this.id = id;
        this.height = height;
        this.width = width;
        this.weight = weight;
        this.inDate = inDate;
        this.outDate = outDate;
        this.earTag = earTag;
        this.source = source;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getType() {
        return Constants.PAYABLE_OBJECT_TYPE_PIG;
    }

    @Override
    public String getDescription() {
        return "Ear tag: " + this.earTag
                + "; Weight: " + this.weight
                + "; Provider: " + (this.provider == null ? "null" : this.provider.getName());
    }

}
