package model;

import java.util.List;

import common.Payable;
import db.CashFlow;
import db.CashFlowDetail;

/**
 * Model for cash flow view
 * @author ducnh
 * create: 15-04-2017
 */
public class CashFlowModel extends ModelBase {
    
    private CashFlow cashFlow;
    private List<Payable> payObjs;
    
    /**
     * Default constructor
     */
    public CashFlowModel(){
        cashFlow = new CashFlow();
        cashFlow.setPayout(true);
    }
    
    // Setter
    public void setNote(String note){
        cashFlow.setNote(note);
    }
    
    public void setPayLogType(boolean payout){
        cashFlow.setPayout(payout);
    }
    
    public void setCashFlowDetail(List<CashFlowDetail> cfd){
        if (cashFlow != null) {
            cashFlow.setDetail(cfd);
        }
    }
    
    public void setPayObjs(List<Payable> pObjs){
        this.payObjs = pObjs;
    }
    
    /**
     * Save cash flow info to database
     * @return
     */
    public boolean saveDown() {
        if (cashFlow == null)
            return false;
        return CashFlow.save(cashFlow);
    }
    
    @Override
    public String toString(){
        if (cashFlow == null || cashFlow.getDetail() == null) return "";
        cashFlow.getDetail().forEach(System.out::println);
        return "";
    }
}
