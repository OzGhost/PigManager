package controller;

import javax.swing.UIManager;

import common.CashFlowReportDto;
import common.Util;
import db.db;
import model.CashFlowReportModel;
import view.HomeView;

public class PigManagerEntryPoint {
    
    public static void main (String[] args) {
//        applicationConstruct();
        try {
            CashFlowReportModel.buildReport(
                CashFlowReportDto.cast(
                    Util.cashFlowGenner(50)
                )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void applicationConstruct() {
        // Database global connection initial
        db.init("orcbase", "c##oz", "ngaymai");

        // Change look and feel
        try {
           UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
           //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            e.printStackTrace();
        }
/*
        res.jf myjf = new res.jf();
        myjf.setVisible(true);

        */

        // View initiall
        final HomeController controller = new HomeController();
        final HomeView view = new HomeView(controller);
        controller.setView(view);
        view.showUp();
        /*
        CashFlowListModel cflm =  new CashFlowListModel();

        CashFlowListView cflv = new CashFlowListView();

        CashFlowListController cflc = new CashFlowListController();

        cflm.addObserver(cflv);

        cflc.setModel(cflm);
        cflc.setView(cflv);
        cflv.setController(cflc);

        cflv.showUp();
        cflm.loadEntryList();
         */
    }
}
