package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Constants;
import common.SickLogReportDto;
import common.Watcher;
import db.SickLog;

public class SickReportModel implements Runnable {

    public static void buildReport (
            List<String> pigIds,
            File outputFile
    ) throws Exception {

        // data prepare
        List<SickLog> raw = SickLog.findLiteVersionByPigIds(pigIds);
        if (raw == null) {
            throw new Exception("Invalid pig ids");
        }
        raw.forEach(SickLog::selfCompleteReportVersion);
        List<Object> data = new ArrayList<>();
        raw.forEach(r -> data.addAll(SickLogReportDto.cast(r)));

        // get template file
        String template = new File("").getAbsolutePath()
            + "/src/res/SickReport.jrxml";

        // Param prepare
        List<String> label = new ArrayList<>(
            Arrays.asList(
                "BÁO CÁO BỆNH ÁN",
                "Kết xuất ngày: ",
                "Mã thẻ tai",
                "Tên bệnh",
                "Ngày phát hiện",
                "Ngày hết bệnh"
            )
        );

        // Param build
        Map<String, Object> param = new HashMap<>();
        param.put("label", label);
        param.put("date4mat", Constants.DATE4MAT);

        // Print report
        ReportModel.reportPrinter(
                template, 
                outputFile, 
                data, 
                param
        );
    }

    // Fields
    private List<String> pigIds;
    private File out;
    private Watcher watcher;

    // Constructors
    public SickReportModel (List<String> pigIds, File out, Watcher w) {
        this.pigIds = pigIds;
        this.out = out;
        this.watcher = w;
    }

    @Override
    public void run() {
        if (pigIds == null || pigIds.isEmpty()) {
            return;
        }
        int rs = 0;
        try {
            buildReport(this.pigIds, this.out);
        } catch (Exception e) {
            rs = -1;
            e.printStackTrace();
        }
        this.watcher.beNoticed("rrs", rs);
    }
}
