package controller;

import java.awt.event.ActionEvent;

import common.Constants;
import common.Watcher;
import model.CashFlowReportOptionModel;
import view.CashFlowReportOptionView;

public class CashFlowReportOptionController
    extends ControllerBase<CashFlowReportOptionModel, CashFlowReportOptionView>
    implements Watcher
{
    @Override
    public void actionPerformed (ActionEvent e) {
        final String cmd = e.getActionCommand();
        if (cmd == null)
            return;
        if (Constants.AC_CANCEL.equals(cmd)) {
            view.dispose();
            watcher.beNoticed("", 0);
        }
        if (Constants.AC_BROWSE.equals(cmd)) {
            view.specificFile();
        }
        if (Constants.AC_DONE.equals(cmd)) {
            view.silent();
            model.go(view.getMetaData(), this);
        }
        super.actionPerformed(e);
    }

    @Override
    public void beNoticed(String s, int n) {
        view.noticeResult(n == 0);
        view.release();
    }
}
