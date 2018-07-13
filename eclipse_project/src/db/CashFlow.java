package db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.CashFlowReportDto;
import common.Constants;

/**
 * Store data for each row of cash flow info
 * table in database: ThuChi
 * object type in database: ThuChi_objtyp
 * nested table: cash flow detail (ChiTietThuChi_objtyp)
 * @author duchn
 * create: 12-05-2017
 */
public class CashFlow extends Entity {

    public static String TABLE_NAME = "ThuChi";
    public static String ID_COLUMN = "MaThuChi";
    public static String OBJ_TYPE_NAME = "ThuChi_objtyp";


    private static final String RETRIEVE_ALL_LITE_VERSION
        = " SELECT MaThuChi mtc, NgayThuChi ntc,\n"
        + "     GhiChu gc, LoaiThuChi ltc, TriGia tg\n"
        + " FROM " + TABLE_NAME
        + " ORDER BY NgayThuChi"
    ;

    private static final String RETRIEVE_DETAIL_BY_ID
        = " SELECT d.MaDoiTuongThuChi id, d.LoaiDoiTuongThuChi type,\n"
        + "         d.Gia cost, d.GhiChu note\n"
        + " FROM ThuChi t, TABLE(t.ChiTiet_ntab) d\n"
        + " WHERE t.MaThuChi = '%s'"
    ;
    
    private static final String UPDATE_NOTE_BY_ID
        = " UPDATE ThuChi\n"
        + " SET GhiChu = '%s'\n"
        + " WHERE MaThuChi = '%s'"
    ;

    private static List<Payable> loadDetailById (String id) {
        List<Payable> rs = new ArrayList<>();
        ResultSet qRs = null;
        try {
            qRs = db.sendForResult(
                String.format(
                    RETRIEVE_DETAIL_BY_ID,
                    id
                )
            );

            while (qRs.next()) {
                String eId = qRs.getString("id");
                int eCost = qRs.getInt("cost");
                String eNote = qRs.getString("note");
                switch (qRs.getString("type")) {
                    case "H":
                        Pig p = new Pig(eId);
                        p.setPayNote(eNote);
                        p.setPrice(eCost);
                        rs.add(p);
                        break;
                    case "M":
                        Money m = new Money();
                        m.setPayNote(eNote);
                        m.setPrice(eCost);
                        rs.add(m);
                        break;
                    case "V":
                        Tool v = new Tool(eId);
                        v.setPayNote(eNote);
                        v.setPrice(eCost);
                        rs.add(v);
                        break;
                    case "T":
                        Sperm t = new Sperm(eId);
                        t.setPayNote(eNote);
                        t.setPrice(eCost);
                        rs.add(t);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                qRs.close();
            } catch (Exception e) {}
        }
        return rs;
    }

    public static List<CashFlow> findAllLiteVersion () {
        List<CashFlow> rs = new ArrayList<>();
        ResultSet qRs = null;
        try {
            qRs = db.sendForResult(RETRIEVE_ALL_LITE_VERSION);
            while (qRs.next()) {
                rs.add(
                    new CashFlow(
                        qRs.getString("mtc"),
                        Constants.DATE4MAT.parse( qRs.getString("ntc") ),
                        qRs.getString("gc"),
                        (qRs.getInt("ltc") == 1),
                        qRs.getInt("tg")
                    )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { qRs.close(); } catch (Exception e) {}
        }
        return rs;
    }

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
    private Date occurDate;
    private String note;
    private boolean payout;
    private int cost;
    private List<Payable> detail;

    // Constructors
    public CashFlow () {}
    public CashFlow (
        String id,
        Date occurDate,
        String note,
        boolean payout,
        int cost
    ) {
        this.id = id;
        this.occurDate = occurDate;
        this.note = note;
        this.payout = payout;
        this.cost = cost;
        this.detail = new ArrayList<>(0);
    }
    

    // Getter
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

    // Methods
    public Object[] toObjects () {
        Object[] rs = new Object[3];
        rs[0] = Constants.DATE4MAT.format(this.occurDate);
        rs[1] = payout ? "Chi" : "Thu";
        rs[2] = cost;
        return rs;
    }

    public void selfUpdateNote () {
        db.send(String.format(UPDATE_NOTE_BY_ID, this.note, this.id));
    }
    
    public CashFlowReportDto toReportDto () {
        return new CashFlowReportDto(this.occurDate, this.cost, this.payout ? "Chi" : "Thu");
    }

    @Override
    public void selfCompleteFullVersion () {
        if (isFullState()) {
            return;
        }
        this.detail = loadDetailById(this.id);
        super.selfCompleteFullVersion();
    }

    @Override
    public String toString () {
        return "\n id: " + id
            + "\n occurDate: " + occurDate
            + "\n note: " + note
            + "\n payout: " + payout
            + "\n cost: " + cost
        ;
    }
}
