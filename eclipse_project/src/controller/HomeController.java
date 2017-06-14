package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import common.Constants;
import common.Watcher;
import model.BenhAnModel;
import model.BenhModel;
import model.CashFlowListModel;
import model.CashFlowModel;
import model.FoodModel;
import model.ModelBase;
import model.PigsManagerModel;
import model.ProviderManagerModel;
import model.StablesModel;
import model.ToolModel;
import model.WarehouseManagerModel;
import view.BenhAnView;
import view.BenhView;
import view.CashFlowListView;
import view.CashFlowView;
import view.FoodView;
import view.HomeView;
import view.PigsManagerView;
import view.ProviderManagerView;
import view.StablesView;
import view.ToolView;
import view.ViewBase;
import view.WarehouseManagerView;

public class HomeController
    extends ControllerBase<ModelBase, HomeView>
    implements Watcher
{

    private static void assemble (
            ViewBase v, 
            ModelBase m, 
            ControllerBase<ModelBase, ViewBase> c, 
            Watcher w
    ) {
        c.setView(v);
        c.setModel(m);
        c.setWatcher(w);

        m.addObserver(v);
        v.setController(c);

        v.showUp();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
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
            
            assemble ((ViewBase) pmv, (ModelBase) pmm, (ControllerBase) pmc, this);
            view.shadow();
            return;
        }
        if (Constants.AC_STOCK_FEAT.equals(cmd)) {
            WarehouseManagerModel whm = new WarehouseManagerModel();
            WarehouseManagerView whv = new WarehouseManagerView();
            WarehouseManagerController whc = new WarehouseManagerController();

            assemble ((ViewBase) whv, (ModelBase) whm, (ControllerBase) whc, this);
            view.shadow();
            return;
        }
        if (Constants.AC_PASTURE_FEAT.equals(cmd)) {
            StablesController sc = new StablesController();
            StablesView sv = new StablesView();
            StablesModel sm = new StablesModel();

            assemble ((ViewBase) sv, (ModelBase) sm, (ControllerBase) sc, this);
            view.shadow();
            return;
        }
        if (Constants.AC_PROVIDER_FEAT.equals(cmd)) {
            ProviderManagerView v = new ProviderManagerView();
            ProviderManagerModel m = new ProviderManagerModel();
            ProviderManagerController c = new ProviderManagerController();

            assemble ((ViewBase)v, (ModelBase)m, (ControllerBase)c, this);
            view.shadow();
            return;
        }
        if (Constants.AC_CASHFLOW_FEAT.equals(cmd)) {
            CashFlowListView v = new CashFlowListView();
            CashFlowListModel m = new CashFlowListModel();
            CashFlowListController c = new CashFlowListController();
            assemble ((ViewBase)v, (ModelBase)m, (ControllerBase)c, this);
            view.shadow();
            m.loadEntryList();
            return;
        }
        if (Constants.AC_CASHLOG_FEAT.equals(cmd)) {
            CashFlowView v = new CashFlowView(new ArrayList<>());
            CashFlowModel m = new CashFlowModel();
            CashFlowController c = new CashFlowController();
            assemble ((ViewBase)v, (ModelBase)m, (ControllerBase)c, this);
            view.shadow();
            return;
        }
        if (Constants.AC_FOOD_FEAT.equals(cmd)) {
            FoodView v = new FoodView();
            FoodModel m = new FoodModel();
            FoodController c = new FoodController();
            assemble ((ViewBase)v, (ModelBase)m, (ControllerBase)c, this);
            view.shadow();
            return;
        }
        if (Constants.AC_SICK_FEAT.equals(cmd)) {
            BenhView v = new BenhView();
            BenhModel m = new BenhModel();
            BenhController c = new BenhController();
            assemble ((ViewBase)v, (ModelBase)m, (ControllerBase)c, this);
            view.shadow();
            return;
        }
        if (Constants.AC_SICKLOG_FEAT.equals(cmd)) {
            BenhAnView v = new BenhAnView();
            BenhAnModel m = new BenhAnModel();
            BenhAnController c = new BenhAnController();
            assemble ((ViewBase)v, (ModelBase)m, (ControllerBase)c, this);
            view.shadow();
            return;
        }
        if (Constants.AC_TOOL_FEAT.equals(cmd)) {
            ToolView v = new ToolView();
            ToolModel m = new ToolModel();
            ToolController c = new ToolController();
            assemble ((ViewBase)v, (ModelBase)m, (ControllerBase)c, this);
            view.shadow();
            return;
        }
        super.actionPerformed(e);
    }

    @Override
    public void beNoticed(String var, int num) {
        view.setVisible(true);
    }
}
