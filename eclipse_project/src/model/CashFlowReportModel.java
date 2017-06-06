package model;

import java.io.File;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static List<Object> getData (
            Date from,
            Date to,
            String period
    ) {
        if (to.before(from) || ! Constants.TIME_STAGE.containsKey(period)) {
            System.out.println("---- WARNING: Invalid input");
            return new ArrayList<>(0);
        }
        
        final List<Object> rs = new ArrayList<>();
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

    public static void BuildReport (
            Date from,
            Date to,
            String period,
            File outputFile
    ) throws Exception {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String template = new File("").getAbsolutePath() + "/src/res/CashFlowReport.jrxml";
        
        // pull data from database
        List<Object> data = getData(from, to, period);
        
        // set label display inside report
        List<String> label = new ArrayList<>(9);
        label.addAll(Arrays.asList("BÁO CÁO THU CHI", 
                "Ngày kết xuất:", 
                "Từ ngày:", 
                "Đến ngày:", 
                "Cộng dồn theo:", 
                period, 
                "Loại", 
                "Ngày phát sinh", 
                "Số tiền"));
        
        // parameters mapping
        Map<String, Object> param = new HashMap<>();
        param.put("date4mat", df);
        param.put("from", from);
        param.put("to", to);
        param.put("label", label);
        param.put("number4mat", NumberFormat.getInstance());
        
        ReportModel.reportPrinter(
                template, 
                outputFile, 
                data, 
                param
        );
    }
}
