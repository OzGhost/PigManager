package db;

import java.util.List;
import common.Constants;
import db.db;

/**
 * Store data for each row of cash flow info
 * table in database: ThuChi
 * object type in database: ThuChi_objtyp
 * nested table: cash flow detail (ChiTietThuChi_objtyp)
 * @author duchn
 * create: 12-05-2017
 */
public class CashFlow {

    public static String TABLE_NAME = "ThuChi";
    public static String ID_COLUMN = "MaThuChi";
    public static String OBJ_TYPE_NAME = "ThuChi_objtyp";

    /**
     * Save new data into database
     * generate object relation constructor code first
     * @param cf
     * @return
     */
    public static boolean save(CashFlow cf) {
        if (cf == null)
            return false;

        // cost sum & detail cmd gen
        int cost = 0;
        String prefix = "";
        StringBuffer detailBuffer = new StringBuffer();
        if (cf.getDetail() != null) {
            for (Payable p: cf.getDetail()) {
                cost += p.getPrice();
                detailBuffer.append( prefix +
                    "ChiTietThuChi_objtyp(" +
                        "'" + p.getId() + "', " +
                        "'" + Constants.getPayableCode(
                                p.getType()
                            )+ "', " +
                        p.getPrice() + ", " +
                        "'" + p.getPayNote() + "'" +
                    ")"
                );
                prefix = ", ";
            }
        }
        String cmd = OBJ_TYPE_NAME + "( " +
            "'" + cf.getId() + "', " +
            "CURRENT_DATE, " +
            "'" + cf.getNote() + "', " +
            (cf.getPayout() ? "1" : "0") + ", " +
            cost + ", " +
            "ChiTietThuChi_ntabtyp("+ detailBuffer.toString() +")" +
        ")";

        // insert to table
        db.saveAutoId(
            Entity.idGenner(
                TABLE_NAME,
                ID_COLUMN,
                OBJ_TYPE_NAME,
                cmd
            )
        );
        return true;
    }

    private String id;
    private String occurDate;
    private String note;
    private boolean payout;
    private int cost;
    private List<Payable> detail;
    

    // Getter
    public String getId() {
        return this.id;
    }
    public String getNote() {
        return this.note;
    }
    public boolean getPayout(){
        return this.payout;
    }
    public List<Payable> getDetail() {
        return this.detail;
    }
    
    // Setter
    public void setNote(String note){
        this.note = note;
    }
    public void setPayout(boolean payout){
        this.payout = payout;
    }
    public void setDetail(List<Payable> detail) {
        this.detail = detail;
    }

}
