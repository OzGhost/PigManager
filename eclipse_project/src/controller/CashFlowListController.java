package controller;

import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import common.Constants;
import common.Watcher;
import model.CashFlowListModel;
import model.CashFlowReportOptionModel;
import view.CashFlowListView;
import view.CashFlowReportOptionView;

public class CashFlowListController
    extends ControllerBase<CashFlowListModel, CashFlowListView>
    implements ListSelectionListener, Watcher {

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;
        model.itemComplete( view.currentLine() );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final String cmd = e.getActionCommand();
        if (cmd == null) {
            return;
        }
        if (Constants.AC_DONE.equals(cmd)) {
            model.updateNote( view.takeNote() );
        }
        if (Constants.AC_MAKE_REPORT.equals(cmd)) {
            CashFlowReportOptionView cfrov = new CashFlowReportOptionView();
            CashFlowReportOptionModel cfrom = new CashFlowReportOptionModel();
            CashFlowReportOptionController cfroc
                = new CashFlowReportOptionController();

            cfroc.setView(cfrov);
            cfroc.setModel(cfrom);
            cfroc.setWatcher(this);
            cfrov.setController(cfroc);

            cfrom.addObserver(cfrov);
            cfrov.showUp();
            cfrom.prepare();

            view.setVisible(false);
        }
    }

    @Override
    public void beNoticed(String var, int num) {
        view.setVisible(true);
    }
}
