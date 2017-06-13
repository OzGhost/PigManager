package model;

import java.sql.ResultSet;
import java.util.Date;

import common.Constants;
import common.Watcher;
import db.db;

public class CashFlowReportOptionModel extends ModelBase {

    private static final String TAKE_PAYLOG_TIME_BORDER
        = " SELECT MAX(NgayThuChi) max, MIN(NgayThuChi) min"
        + " FROM ThuChi"
    ;
    
    // Fields
    private Date max;
    private Date min;

    // Constructors
    public CashFlowReportOptionModel () {}

    // Getters
    public Date getMaxDate () {
        return max;
    }

    public Date getMinDate () {
        return min;
    }

    // Methods
    public void prepare () {
        ResultSet qRs = null;
        try {
            qRs = db.sendForResult(TAKE_PAYLOG_TIME_BORDER);
            qRs.next();
            max = Constants.DATE4MAT.parse( qRs.getString("max") );
            min = Constants.DATE4MAT.parse( qRs.getString("min") );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { qRs.close(); } catch (Exception e) {}
        }
        setChanged();
        notifyObservers();
    }

    public void go(Object[] metadata, Watcher w) {
        if (metadata == null) {
            return;
        }
        CashFlowReportModel t = new CashFlowReportModel(
            (Date) metadata[0],
            (Date) metadata[1],
            (String) metadata[2],
            (String) metadata[3],
            w
        );
        new Thread(t).start();
    }
}
