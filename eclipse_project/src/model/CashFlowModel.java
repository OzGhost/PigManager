package model;

import java.util.List;

import common.Payable;
import db.CashFlow;
import db.CashFlowDetail;

public class CashFlowModel extends ModelBase {
    
    private CashFlow cashFlow;
    private List<Payable> payObjs;
    
    public CashFlowModel(){
        cashFlow = new CashFlow();
        cashFlow.setPayout(true);
    }
    
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
