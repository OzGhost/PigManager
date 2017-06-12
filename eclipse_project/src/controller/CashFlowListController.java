package controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.CashFlowListModel;
import view.CashFlowListView;

public class CashFlowListController
    extends ControllerBase<CashFlowListModel, CashFlowListView>
    implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;
        model.itemComplete( view.currentLine() );
    }

}
