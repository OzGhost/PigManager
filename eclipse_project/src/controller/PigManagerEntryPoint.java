package controller;

import db.db;
import model.CashFlowListModel;
import view.CashFlowListView;
import view.HomeView;

public class PigManagerEntryPoint {
    
    private static final HomeController controller = new HomeController();
    private static final HomeView view = new HomeView(controller);
    
    public static void main (String[] args) {
        applicationConstruct();
    }

    private static void applicationConstruct() {
        // Database global connection initial
        db.init("orcbase", "c##oz", "ngaymai");

        // View initiall
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
