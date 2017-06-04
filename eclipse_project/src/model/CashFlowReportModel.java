package model;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.CashFlowReportDto;
import common.Constants;
import db.db;

public class CashFlowReportModel {

    private static String PrepareRetrieveQuery (
            Date from,
            Date to,
            String timeStage
    ) {
        DateFormat df = new SimpleDateFormat("dd-MMM-yy");
        return "SELECT time, SUM(TriGia) as cash"
            + " FROM ("
            + String.format("SELECT TRUNC(NgayThuChi,'%s') as time,", timeStage)
            + "     TriGia"
            + "     FROM ThuChi "
            + String.format(
                    "WHERE NgayThuChi BETWEEN '%s' AND '%s'",
                    df.format(from),
                    df.format(to)
            )
            + " )"
            + " GROUP BY time"
            + " ORDER BY time"
        ;
    }

    public static List<CashFlowReportDto> getData (
            Date from,
            Date to,
            String period
    ) {
        if (to.before(from) || ! Constants.TIME_STAGE.containsKey(period)) {
            System.out.println("---- WARNING: Invalid input");
            return new ArrayList<>(0);
        }
        
        final List<CashFlowReportDto> rs = new ArrayList<>();
        final String q = PrepareRetrieveQuery(
                from,
                to,
                Constants.TIME_STAGE.get(period)
        );
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final ResultSet result = db.sendForResult(q);
        try {
            while (result.next()) {
                CashFlowReportDto cfrd = new CashFlowReportDto();
                cfrd.setTime( df.parse(result.getString("time")) );
                cfrd.setCash(result.getLong("cash"));
                rs.add(cfrd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }


}
