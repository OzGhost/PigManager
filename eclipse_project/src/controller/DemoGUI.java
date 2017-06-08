package controller;

import java.util.ArrayList;
import java.util.List;

import common.RefPayload;
import db.Entity;
import db.db;

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

        /*
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
        */
        
        // test report model 2
        try {
//            db.saveAutoId(
//                Entity.idGenner(
//                    "THUOC", 
//                    "MATHUOC", 
//                    "Thuoc_objtyp", 
//                    "Thuoc_objtyp('111', '1','1','1','1',1,null,"
//                    + "TO_DATE('2017-05-01', 'yyyy-mm-dd'),"
//                    + "TO_DATE('2017-08-01', 'yyyy-mm-dd'))",
//                    new RefPayload("NhaCungCap", "MaNCC", "201704120005", "NhaCungCap_ref")
//                )
//            );
            List<RefPayload> ref = new ArrayList<>();
            ref.add(new RefPayload("NhaCungCap", "MaNCC", "201704120007", "NhaCungCap_ref"));
            ref.add(new RefPayload("lichsuthaiky", "malstk", "201710109293", "lichsuthaiky_ref"));
            db.saveAutoId(
                    Entity.idGenner(
                        "TINH", 
                        "MATINH", 
                        "Tinh_objtyp", 
                        "Tinh_objtyp('111', 'men', 'vn', 'ko co', null,"
                        + "TO_DATE('2017-12-01', 'yyyy-mm-dd'),"
                        + "TO_DATE('2017-12-12', 'yyyy-mm-dd'), null)",
                        ref
                    )
            ); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
