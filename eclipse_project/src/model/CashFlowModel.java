package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Payable;
import db.CashFlow;
import db.db;

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
    
    public void setCashFlowDetail(Object[][] cfd){
        if (payObjs == null)
            return;
        for (Payable p: payObjs) {
            for (Object[] o: cfd) {
                if (
                        p.getId().equals((String) o[0]) &&
                        p.getType().equals((String) o[1])
                   ) {
                    p.setPrice((Integer) o[2]);
                    p.setPayNote((String) o[3]);
                }
            }
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
        if (
                cashFlow == null ||
                cashFlow.getDetail() == null ||
                cashFlow.getDetail().isEmpty()
            )
            return false;
        boolean cashFlowSaveResult = CashFlow.save(cashFlow);

        // save cash flow failure case
        if (!cashFlowSaveResult)
            return false;

        // get owe per provider
        boolean owe_log = true;
        int pointer = 1;
        final Map<String, Integer> providerIndex = new HashMap<>();
        final int nrow = cashFlow.getDetail().size();
        final int[] owe = new int[nrow];
        for (Payable p: cashFlow.getDetail()) {
            String prvId = p.getProviderId();
            if (providerIndex.containsKey(prvId)) {
                owe[ providerIndex.get(prvId) ] += p.getPrice();
            } else {
                providerIndex.put(prvId, pointer);
                owe[ pointer ] = p.getPrice();
                pointer++;
            }
        }
        for (String k: providerIndex.keySet()) {
            owe_log = owe_log &&
                db.send("UPDATE NhaCungCap SET owe = owe + "
                    + owe[ providerIndex.get(k) ]
                    + " WHERE MaNCC = " + k);
        }
        return owe_log;
    }
    
    @Override
    public String toString(){
        if (cashFlow == null || cashFlow.getDetail() == null) return "";
        cashFlow.getDetail().forEach(System.out::println);
        return "";
    }
}
