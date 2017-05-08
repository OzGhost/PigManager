package db;

import java.util.List;
import common.Constants;
import db.db;

public class CashFlow {

    public static boolean save(CashFlow cf) {
        if (cf == null)
            return false;

        // cost sum & detail cmd gen
        int cost = 0;
        String prefix = "";
        StringBuffer detailBuffer = new StringBuffer();
        if (cf.getDetail() != null) {
            for (CashFlowDetail cfd: cf.getDetail()) {
                cost += cfd.getPrice();
                detailBuffer.append( prefix +
                    "ChiTietThuChi_objtyp(" +
                        "'" + cfd.getPayableObjectId() + "', " +
                        "'" + Constants.getPayableCode(
                                cfd.getPayableType()
                            )+ "', " +
                        cfd.getPrice() + ", " +
                        "'" + cfd.getNote() + "'" +
                    ")"
                );
                prefix = ", ";
            }
        }
        String cmd = "INSERT INTO ThuChi VALUES ( " +
            "'" + cf.getId() + "', " +
            "CURRENT_DATE, " +
            "'" + cf.getNote() + "', " +
            (cf.getPayout() ? "1" : "0") + ", " +
            cost + ", " +
            "ChiTietThuChi_ntabtyp("+ detailBuffer.toString() +")" +
        ")";

        db.send(cmd);
        return true;
    }

    private String id;
    private String occurDate;
    private String note;
    private boolean payout;
    private int cost;
    private List<CashFlowDetail> detail;
    

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
    public List<CashFlowDetail> getDetail() {
        return this.detail;
    }
    
    // Setter
    public void setNote(String note){
        this.note = note;
    }
    public void setPayout(boolean payout){
        this.payout = payout;
    }
    public void setDetail(List<CashFlowDetail> detail) {
        this.detail = detail;
    }

}
