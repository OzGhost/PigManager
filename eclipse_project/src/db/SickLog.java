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

    public static List<SickLog> findAllLiteVersion () {
        List<SickLog> rs = new ArrayList<>();
        ResultSet qResult = null;
        try {
            qResult = db.sendForResult(RETRIEVE_ALL_LITE_VERSION);
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

    private static List<SickDetail> findSickDetails (String id) {
        String sickLogId = id.replaceAll("[^0-9]", "");
        List<SickDetail> rs = new ArrayList<>();
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

    // Methods
    @Override
    public String toString() {
        return "\nid: " + id
            + " pigId: " + pig.getId()
            + " createDate: " + createDate;
    }

    @Override
    public void selfCompleteLiteVersion () {
        pig.selfCompleteLiteVersion();
        sickDetails.addAll( findSickDetails(this.id) );
    }
}
