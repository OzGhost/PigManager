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

    // Getters
    public List<CashFlow> getEntryList() {
        return this.entryList;
    }

    public CashFlow getCurrentItem () {
        return entryList.get(chosingIndex);
    }

    // Methods
    public void loadEntryList () {
        this.entryList = CashFlow.findAllLiteVersion();
        setChanged();
        notifyObservers(LOAD_COMPLETE);
    }

    public void itemComplete (int i) {
        chosingIndex = i;
        CashFlow chosingItem = entryList.get(i);
        if (chosingItem.getDetail().isEmpty()) {
            chosingItem.selfCompleteFullVersion();
        }
        setChanged();
        notifyObservers(ITEM_CHANGE);
    }

    public void updateNote(String note) {
        CashFlow cf = entryList.get(chosingIndex);
        cf.setNote(note);
        cf.selfUpdateNote();
    }
}
