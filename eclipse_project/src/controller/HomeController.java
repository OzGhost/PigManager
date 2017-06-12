package controller;

import java.awt.event.ActionEvent;

import common.Constants;
import model.PigsManagerModel;
import model.StablesModel;
import model.WarehouseManagerModel;
import view.HomeView;
import view.PigsManagerView;
import view.StablesView;
import view.WarehouseManagerView;

public class HomeController extends ControllerBase<Object, HomeView> {
    @Override
    public void actionPerformed (ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd == null) {
            System.out.println("No signal");
            return;
        }
        if (Constants.AC_CANCEL.equals(cmd)) {
            System.exit(0);
        }
        if (Constants.AC_PIG_FEAT.equals(cmd)) {
            PigsManagerModel pmm = new PigsManagerModel();
            PigsManagerView pmv = new PigsManagerView();
            PigsManagerController pmc = new PigsManagerController();
            
            pmc.setView(pmv);
            pmc.setModel(pmm);
            pmv.setController(pmc);

            pmv.showUp(); 

            view.shadow();
            return;
        }
        if (Constants.AC_STOCK_FEAT.equals(cmd)) {
            WarehouseManagerModel whm = new WarehouseManagerModel();
            WarehouseManagerView whv = new WarehouseManagerView();
            WarehouseManagerController whc = new WarehouseManagerController();

            whc.setView(whv);
            whc.setModel(whm);
            whv.setController(whc);

            whv.showUp();

            view.shadow();
            return;
        }
        if (Constants.AC_PASTURE_FEAT.equals(cmd)) {
            StablesController sc = new StablesController();
            StablesView sv = new StablesView();
            StablesModel sm = new StablesModel();

            sc.setView(sv);
            sc.setModel(sm);

            sv.setController(sc);
            sv.showUp();
            
            view.shadow();
            return;
        }
        if (Constants.AC_PROVIDER_FEAT.equals(cmd)) {
        }
    }
}
