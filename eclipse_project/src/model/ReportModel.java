package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportModel {
    
    public static void reportPrinter (
            String template,
            File outputFile,
            List<Object> data,
            Map<String, Object> param
    ) throws Exception {

        // get printer from template, data and params
        final JasperPrint jPrint = JasperFillManager.fillReport(
                JasperCompileManager.compileReport(template),
                param,
                new JRBeanCollectionDataSource(data));

        // file name process
        final String fileName = outputFile.getName();
        final int s = fileName.length();
        final String absoFile = outputFile.getAbsolutePath();
        final int ss = absoFile.length();
        String prefix = null;
        String suffix = null;
        if (fileName.endsWith(".pdf") && s > 4) {
            prefix = fileName.substring(0, s - 4);
            suffix = ".pdf";
        } else {
            prefix = fileName;
            suffix = ".pdf";
        }

        final File f = new File(absoFile.substring(0, ss - s) + prefix + suffix);

        // create file by write some text to file
        final PrintWriter pw = new PrintWriter(f, "UTF-8");
        pw.write("init file");
        pw.flush();
        pw.close();

        // Print to file
        JasperExportManager.exportReportToPdfStream(
                jPrint,
                new FileOutputStream(f)
                );

    }
}
