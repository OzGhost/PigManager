package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import common.Constants;
import common.FinalTableModel;
import common.Genner;
import common.Layer;

public class CashFlowListView extends ViewBase {

    private static final long serialVersionUID = 4381176712421848200L;
    
    // Fields
    private JTable tb_entryList;
    private FinalTableModel tm_entryList;
    private JTable tb_detail;
    private FinalTableModel tm_detail;
    private JTextField tf_note;
    private JButton bt_export;
    private JButton bt_updateNote;
    private final SpringLayout layout = new SpringLayout();
    private final JPanel panel = new JPanel(layout);

    // Constructors
    public CashFlowListView () {
        init();
    }

    // Methods
    private void init () {
        JPanel middle = middleInit();
        buttonInit();

        panel.add(middle);
        panel.add(bt_updateNote);
        panel.add(bt_export);

        Layer.put(bt_updateNote).in(layout)
            .atBottomRight(panel).withMargin(5);
        Layer.put(bt_export).in(layout)
            .atBottom(panel).withMargin(5)
            .rightOf(bt_updateNote).withMargin(5);
        Layer.put(middle).in(layout)
            .atTopLeft(panel).withMargin(10, 5)
            .atRight(panel).withMargin(5)
            .topOf(bt_updateNote).withMargin(5);
    }

    private void buttonInit() {
        bt_updateNote = Genner.createButton("Update Note!", Genner.BIG_SIZE);
        bt_export = Genner.createButton("Make Report", Genner.MEDIUM_SIZE);
    }

    private JPanel middleInit () {
        final JPanel left = leftMidInit ();
        final JPanel right = rightMidInit ();

        final JPanel rs = new JPanel(new GridLayout(0, 2));
        rs.add(left);
        rs.add(right);
        return rs;
    }

    private JPanel leftMidInit () {
        tm_entryList = new FinalTableModel(
            new Object[0][0], 
            new Object[]{"Ngay Thu Chi", "Loai", "Tri Gia"},
            new int[]{0,1,2}
        );
        tb_entryList = new JTable(tm_entryList);

        final JPanel rs = new JPanel (new BorderLayout());
        rs.setBorder(
            BorderFactory.createTitledBorder(
                Constants.BD_GREYLINE,
                "Phieu thu chi"
            )
        );
        rs.add(new JScrollPane(tb_entryList), BorderLayout.CENTER);
        
        return rs;
    }

    private JPanel rightMidInit () {
        final JPanel top = new JPanel(new BorderLayout());
        final JPanel bot = new JPanel(new BorderLayout());
        
        final SpringLayout l = new SpringLayout();
        final JPanel rs = new JPanel(l);

        rs.add(top);
        rs.add(bot);

        Layer.put(bot).in(l)
            .atBottomLeft(rs).withMargin(0)
            .atRight(rs).withMargin(0);
        Layer.put(top).in(l)
            .atTopLeft(rs).withMargin(0)
            .atRight(rs).withMargin(0)
            .topOf(bot).withMargin(10);

        return rs;
    }

}
