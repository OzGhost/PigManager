package db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.Constants;

public class SickLog extends Entity {

    private static final String RETRIEVE_ALL_LITE_VERSION
        = " SELECT b.MaBenhAn mba, DEREF(b.Heo_ref).MaHeo mh, b.NgayTao nt"
        + " FROM BenhAn b"
    ;

    private static final String RETRIEVE_LITE_VERSION_BY_PIG_IDS
        = RETRIEVE_ALL_LITE_VERSION + "\n"
        + "WHERE DEREF(b.Heo_ref).MaHeo in %s"
    ;

    private static final String RETRIEVE_SICK_DETAIL_REPORT_VERSION_BY_ID
        = " SELECT DEREF(s.Benh_ref).MaBenh mb,\n"
        + "     DEREF(s.Benh_ref).TenBenh tb,\n"
        + "     s.NgayPhatBenh npb,\n"
        + "     s.NgayHetBenh nhb\n"
        + " FROM BenhAn b, TABLE(b.ChiTietBenh_ntab) s\n"
        + " WHERE b.MaBenhAn = '%s'\n"
        + " ORDER BY s.NgayPhatBenh"
    ;
    
    private static String listToQList (List<String> pigIds) {
        if (pigIds == null || pigIds.isEmpty()) {
            return null;
        }
        String prefix = "";
        final StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (String id: pigIds) {
            sb.append(prefix);
            sb.append("'");
            sb.append(id);
            sb.append("'");
            prefix = ",";
        }
        sb.append(")");
        return sb.toString();
    }

    private static List<SickLog> findLiteVersion (String cmd) {
        List<SickLog> rs = new ArrayList<>();
        ResultSet qResult = null;
        try {
            qResult = db.sendForResult(cmd);
            while (qResult.next()) {
                rs.add(
                    new SickLog(
                        qResult.getString("mba"),
                        qResult.getString("mh"),
                        Constants.DATE4MAT.parse( qResult.getString("nt") )
                    )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { qResult.close(); } catch (Exception e) {}
        }
        return rs;
    }

    public static List<SickLog> findAllLiteVersion () {
        return findLiteVersion(RETRIEVE_ALL_LITE_VERSION);
    }

    public static List<SickLog> findLiteVersionByPigIds (List<String> pigIds) {
        String qList = listToQList(pigIds);
        if (qList == null) {
            return null;
        }
        return findLiteVersion(
            String.format(
                RETRIEVE_LITE_VERSION_BY_PIG_IDS,
                qList
            )
        );
    }

    private static List<SickDetail> findSickDetailsReportVersion (String id) {
        String sickLogId = id.replaceAll("[^0-9]", "");
        List<SickDetail> rs = new ArrayList<>();
        ResultSet qRs = null;
        try {
            qRs = db.sendForResult(String.format(RETRIEVE_SICK_DETAIL_REPORT_VERSION_BY_ID, id));
            while (qRs.next()) {
                Sick s = new Sick(qRs.getString("mb"));
                s.setName(qRs.getString("tb"));
                rs.add(
                    new SickDetail(
                        s,
                        Constants.DATE4MAT.parse( qRs.getString("npb") ),
                        Constants.DATE4MAT.parse( qRs.getString("nhb") )
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

    // Fields
    private Pig pig;
    private Date createDate;
    private List<MedicineTakeLog> medicinesTake;
    private List<SickDetail> sickDetails;

    // Constructors
    public SickLog () {}
    public SickLog (String id, String pigId, Date createDate) {
        this.id = id;
        this.pig = new Pig(pigId);
        this.createDate = createDate;
        this.medicinesTake = new ArrayList<>();
        this.sickDetails = new ArrayList<>();
    }
    
    // Getters
    public List<SickDetail> getSickDetails () {
        if (!isFullState()) {
            System.out.println("---- WARNING: Data maybe not ready yet");
        }
        return this.sickDetails;
    }
    public Pig getPig() {
        return this.pig;
    }

    // Methods
    @Override
    public String toString() {
        sickDetails.forEach(System.out::println);
        return "\nid: " + id
            + " pigId: " + pig.getId()
            + " createDate: " + createDate;
    }

    public void selfCompleteReportVersion () {
        pig.selfCompleteLiteVersion();
        sickDetails.addAll( findSickDetailsReportVersion(this.id) );
    }
}
