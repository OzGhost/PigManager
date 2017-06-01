package common;

import javax.swing.table.DefaultTableModel;

public class FinalTableModel extends DefaultTableModel {
    private static final long serialVersionUID = 5651486261249102714L;
    private int[] isolateCol;

    public FinalTableModel(Object[][] data, Object[] colNames, int[] isolateCol) {
        super(data, colNames);
        this.isolateCol = isolateCol;
    }

    @Override
    public boolean isCellEditable (int row, int column) {
        for (int i: isolateCol) {
            if (column == i)
                return false;
        }
        return true;
    }
}

