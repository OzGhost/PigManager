package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Constants;
import common.SickLogReportDto;
import db.SickLog;

public class SickReportModel {

    public static void BuildReport (
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
                "Sick Log Report",
                "Retrieve at: ",
                "Pig's Ear Tag",
                "Sick Name",
                "Occur Date",
                "Over Date"
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
}
