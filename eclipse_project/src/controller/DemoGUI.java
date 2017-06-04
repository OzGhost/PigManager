package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import common.CashFlowReportDto;
import db.db;
import model.CashFlowReportModel;

/**
 * For demo only
 * @author ducnh
 * create: 12-05-2017
 */
public class DemoGUI {
    public static void main (String[] args) {
        // Database global connection initial
        db.init("orcBase", "c##oz", "ngaymai");
        
        /*
        // Change look and feel
        try {
//            UIManager.setLookAndFeel("com.jtattoo.plaf" +
//                    ".aluminium.AluminiumLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        // Font setting
        FontUIResource f = new FontUIResource(
                Constants.globalFontName,
                Font.PLAIN,
                13
        );
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
          Object key = keys.nextElement();
          Object value = UIManager.get (key);
          if (value != null && value instanceof javax.swing.plaf.FontUIResource)
            UIManager.put (key, f);
        }

        // Prepare data for cash flow log
        List<Payable> pigs = new ArrayList<>();
        Provider pv = new Provider("201704120003", "Trai heo giong 9g");
        pigs.add(new Pig("201701010001", 250, "123asdb", pv));
        pigs.add(new Pig("201701010002", 210, "12as2db", pv));
        pigs.add(new Pig("201701010003", 150, "92ddb43", pv));
        pigs.add(new Pig("201701010004", 210, "12asddb", pv));
        pigs.add(new Pig("201701010005", 190, "123adfb", pv));
        
        // Pull up cash flow log
        CashFlowController cfc = new CashFlowController();
        CashFlowModel cfm = new CashFlowModel();
        CashFlowView cfv = new CashFlowView(pigs);
        
        cfc.setModel(cfm);
        cfc.setView(cfv);
        cfc.init(pigs);
        cfm.addObserver(cfv);
        cfv.setController(cfc);
        cfv.showUp();
        */

        /*
    	// block call Them chuong frame
        StablesView sv = new StablesView();
        StablesController sc = new StablesController();
        sv.setController(sc);
        sv.setVisible(true);
        sc.setView(sv);
        */

        
        // test report model
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Date from = df.parse("16-05-2017");
            Date to = df.parse("16-06-2017");
            List<CashFlowReportDto> l = CashFlowReportModel.getData(from, to, "Ngày");
            l.forEach(e -> System.out.println(String.format("%s, %s", e.getTime(), e.getCash())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
