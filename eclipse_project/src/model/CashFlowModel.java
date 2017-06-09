package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.CashFlow;
import db.Payable;
import db.db;

/**
 * Model for cash flow view
 * @author ducnh
 * create: 15-04-2017
 */
public class CashFlowModel extends ModelBase {

    public static final short PAY_OBJ_REMOVED = 1;
    
    private final CashFlow cashFlow = new CashFlow();
    private final List<Payable> payObjs = new ArrayList<>();
    private int[] victimInds;
    
    /**
     * Default constructor
     */
    public CashFlowModel(){
        cashFlow.setPayout(true);
        cashFlow.setDetail(payObjs);
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
                    break;
                }
            }
        }
    }
    
    public void setPayObjs(List<Payable> pObjs){
        if (pObjs != null) {
            this.payObjs.addAll(pObjs);
        }
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
            ) {
            System.out.println("invalid model store");
            return false;
        }
        boolean cashFlowSaveResult = CashFlow.save(cashFlow);

        // save cash flow failure case
        if (!cashFlowSaveResult) {
            System.out.println("query false");
            return false;
        }

        // get owe per provider
        boolean owe_log = true;
        int pointer = 0;
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
                db.send("UPDATE NhaCungCap SET NoPhaiTra = NoPhaiTra + "
                    + owe[ providerIndex.get(k) ]
                    + " WHERE MaNCC = " + k);
        }
        if (!owe_log) {
            System.out.println("update own false");
        }
        return owe_log;
    }

    public int[] getVictimIndexs () {
        return victimInds;
    }

    public void removeVictims (int[] victimIndexs) {
        if (victimIndexs.length < 1)
            return;

        victimInds = victimIndexs;
        Arrays.sort(victimInds);
        for (int i = victimInds.length - 1; i >= 0; i--) {
            payObjs.remove( victimInds[i] );
        }
        setChanged();
        notifyObservers(PAY_OBJ_REMOVED);
    }
    
    @Override
    public String toString(){
        if (cashFlow == null || cashFlow.getDetail() == null) return "";
        cashFlow.getDetail().forEach(System.out::println);
        return "";
    }
}
