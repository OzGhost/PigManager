package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import common.Constants;
import common.Payable;
import common.Watcher;
import model.CashFlowModel;
import view.CashFlowView;

/**
 * Cash flow controller
 * @author oz
 * create: 12-05-2017
 */
public class CashFlowController
        extends ControllerBase<CashFlowModel, CashFlowView> {
    
    /**
     * Initial class: set pay objects for model
     * @param payObjs
     */
    public void init(List<Payable> payObjs){
        if (model != null){
            model.setPayObjs(payObjs);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get command of event fire object
        String cmd = e.getActionCommand();
        // Save button clicked case
        if (Constants.AC_DONE.equals(cmd)){
            model.setNote(view.getNote());
            Object[][] cfd = view.getCashFlowDetail();
            if (cfd == null || cfd.length < 1){
                return;
            }
            model.setCashFlowDetail(cfd);
            if (model.saveDown()) {
                view.noticeSaveResult(true);
                view.dispose();
                view = null;
                model = null;
                System.gc();
            } else {
                view.noticeSaveResult(false);
            }
            return;
        }
        // Cancel button clicked case
        if (Constants.AC_CANCEL.equals(cmd)){
            this.view.setVisible(false);
            this.view.dispose();
            return;
        }
        // Pay in method choice case
        if (CashFlowView.PAYIN_SELECTED_COMMAND.equals(cmd)){
            model.setPayLogType(false);
            return;
        }
        // Pay out method choice case
        if (CashFlowView.PAYOUT_SELECTED_COMMAND.equals(cmd)){
            model.setPayLogType(true);
            return;
        }
        // Add button clicked case
        if (Constants.AC_ADD.equals(cmd)){
            return;
        }
        // Remove button clicked case
        if (Constants.AC_RM.equals(cmd)){
            model.removeVictims( view.getVictims() );
            return;
        }
        if (CashFlowView.AC_FLOOR_COST.equals(cmd)) {
            view.floorTheCost(true);
            return;
        }
        if (CashFlowView.AC_EST_TOTAL.equals(cmd)) {
            view.floorTheCost(false);
            return;
        }
        super.actionPerformed(e);
    }
}
