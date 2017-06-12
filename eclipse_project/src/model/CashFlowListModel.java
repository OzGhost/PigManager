package model;

import java.util.List;

import db.CashFlow;

public class CashFlowListModel extends ModelBase {

    public static final short LOAD_COMPLETE = 0;
    public static final short ITEM_CHANGE = 1;

    // Fields
    private List<CashFlow> entryList;
    private int chosingIndex;

    // Constructors
    public CashFlowListModel () {

    }

    // Methods
    public void loadEntryList () {
        this.entryList = CashFlow.findAllLiteVersion();
        setChanged();
        notifyObservers(LOAD_COMPLETE);
    }

    public void itemComplete (int i) {
        System.out.println(i);
        chosingIndex = i;
        CashFlow chosingItem = entryList.get(i);
        if (chosingItem.getDetail().isEmpty()) {
//            chosingItem.selftComplete();
        }
        setChanged();
        notifyObservers(ITEM_CHANGE);
    }

    public List<CashFlow> getEntryList() {
        return this.entryList;
    }
}
