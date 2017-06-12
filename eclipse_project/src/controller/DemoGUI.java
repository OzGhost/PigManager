package controller;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import common.Constants;
import db.db;
import model.PigsManagerModel;
import view.*;
import model.*;

/**
 * For demo only
 * @author ducnh
 * create: 12-05-2017
 */
public class DemoGUI {
    public static void main (String[] args) {
        // Database global connection initial
         db.init("xdb", "c##java", "1807");
        
        // Change look and feel
        try {
           UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
           //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
/*
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
        StablesModel sm= new StablesModel();
        sc.setView(sv);
        sc.setModel(sm);
        sv.setController(sc); 
	sv.showUp();
*/      
        //block call vatdung frame
        ToolView tv= new ToolView();
        ToolController tc = new ToolController();
        ToolModel tm = new ToolModel();
        tc.setView(tv);
        tc.setModel(tm);
        tv.setController(tc);
        tv.showUp();
       
//        WarehouseManagerModel whm = new WarehouseManagerModel();
//        WarehouseManagerView whv = new WarehouseManagerView();
//        WarehouseManagerController whc = new WarehouseManagerController();
//        whc.setView(whv);
//        whc.setModel(whm);
//        whv.setController(whc);
//        whv.showUp();
        
/*       
        PigsManagerModel pmm = new PigsManagerModel();
        PigsManagerView pmv = new PigsManagerView();
        PigsManagerController pmc = new PigsManagerController();
        pmc.setView(pmv);
        pmc.setModel(pmm);
        pmv.setController(pmc);
        pmv.showUp(); 
*/        
        
//        BenhAnView bav=new BenhAnView();
//        BenhAnController bac =new BenhAnController();
//        bav.setController(bac);
//        bac.setView(bav);
//        bac.loadData();
//        bav.showUp();
        
        
//        ThaTinhController ttc= new ThaTinhController();
//        ThaTinhView ttv=new ThaTinhView();
//        ttv.setController(ttc);
//        ttc.setView(ttv);
//        ttc.loaddata();
//        ttv.showUp();
        
        
//        LichSuThaiKyController lsc=new LichSuThaiKyController();
//        LichSuThaiKyView lsv=new LichSuThaiKyView();
//        lsv.setController(lsc);
//        lsc.setView(lsv);
//        lsc.loaddata();
//        lsv.showUp();
    }
}
