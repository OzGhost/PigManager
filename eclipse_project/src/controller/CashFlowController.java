package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import common.Payable;
import db.CashFlow;
import model.CashFlowModel;
import view.CashFlowView;

public class CashFlowController
        extends ControllerBase<CashFlowModel, CashFlowView>
        implements ActionListener {
    
    public void init(List<Payable> payObjs){
        model.setPayObjs(payObjs);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (CashFlowView.SAVE_COMMAND.equals(cmd)){
            model.setNote(view.getNote());
            model.setCashFlowDetail(view.getCashFlow());
            model.saveDown();
            return;
        }
        if (CashFlowView.CANCEL_COMMAND.equals(cmd)){
            this.view.setVisible(false);
            this.view.dispose();
            return;
        }
        if (CashFlowView.PAYIN_SELECTED_COMMAND.equals(cmd)){
            model.setPayLogType(false);
            return;
        }
        if (CashFlowView.PAYOUT_SELECTED_COMMAND.equals(cmd)){
            model.setPayLogType(true);
            return;
        }
        if (CashFlowView.ADD_COMMAND.equals(cmd)){
            return;
        }
        if (CashFlowView.REMOVE_COMMAND.equals(cmd)){
            return;
        }
    }
}
