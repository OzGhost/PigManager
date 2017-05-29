package controller;

import java.sql.ResultSet;

import db.db;

/**
 * For demo only
 * @author ducnh
 * create: 12-05-2017
 */
public class DemoGUI {
    public static void main (String[] args) {
        // Database global connection initial
//        db.init("orcBase", "c##oz", "ngaymai");
        
        db.init("orcBase", "c##tester", "ngaymai");
        ResultSet rs = db.sendForResult("select * from taikhoan");
        try {
            rs.next();
            System.out.println(rs.getString(2) + rs.getString(1));
        } catch (Exception e){
            e.printStackTrace();
        }
        /*
        
        // Change look and feel
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf" +
                    ".aluminium.AluminiumLookAndFeel");
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
        pigs.add(new Pig("201701029292", 250, "123asdb", pv));
        pigs.add(new Pig("201712291923", 210, "12as2db", pv));
        pigs.add(new Pig("201702939232", 150, "92ddb43", pv));
        pigs.add(new Pig("201701229292", 210, "12asddb", pv));
        pigs.add(new Pig("201701023292", 190, "123adfb", pv));
        
        // Pull up cash flow log
        CashFlowController cfc = new CashFlowController();
        CashFlowModel cfm = new CashFlowModel();
        CashFlowView cfv = new CashFlowView(pigs);
        
        cfc.setModel(cfm);
        cfc.setView(cfv);
        cfc.init(pigs);
        cfm.addObserver(cfv);
        cfv.setController(cfc);
        cfv.setVisible(true);

        boolean rs = Provider.find("20171023").set("TenNCC = 'noob'").go();
        if (!rs) {
            System.out.println("Query failure");
        } else {
            System.out.println("Query success");
        }

        */
	// block call Them chuong frame
        //StablesView sv = new StablesView();
        //StablesController sc = new StablesController();
        //sv.setController(sc);
        //sv.setVisible(true);
        //sc.setView(sv);
        // Close connection
	
        db.destroy();
    }
}
