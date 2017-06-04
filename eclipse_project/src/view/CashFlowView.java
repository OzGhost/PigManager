package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import common.Constants;
import common.FinalTableModel;
import common.Genner;
import common.Layer;
import common.Payable;
import controller.CashFlowController;
import model.CashFlowModel;

/**
 * Cash flow view
 * @author ducnh
 * create: 15-04-2017
 */
public class CashFlowView extends ViewBase {
    private static final long serialVersionUID = 1L;
    
    public static final String PAYOUT_SELECTED_COMMAND = "payout_switched";
    public static final String PAYIN_SELECTED_COMMAND = "payin_switched";
    public static final String AC_FLOOR_COST = "floor_for_each";
    public static final String AC_EST_TOTAL = "one_for_all";
    
    private JRadioButton payOut;
    private JRadioButton payIn;
    private JTextArea note;
    private JTable detailtab;
    private JButton savebt;
    private JButton homebt;
    private JButton addbt;
    private JButton removebt;
    private JButton cancelbt;
    private JButton estimateTotalCost;
    private JButton floorCost;
    private JFormattedTextField estimatedValue;
    private final SpringLayout layout = new SpringLayout();
    private final JPanel panel = new JPanel(layout);
    
    /**
     * Construct view with list of pay object
     * @param payObjects
     */
    public CashFlowView (List<Payable> payObjects) {

        JPanel topLeft = initTypeChoice();
        JPanel topRight = initNote();
        JPanel center = initTable(payObjects);
        JPanel estimate = initEstimateCost();
        initButton();

        // put on layout
        panel.add(topLeft);
        panel.add(topRight);
        panel.add(center);
        panel.add(estimate);
        panel.add(homebt);
        panel.add(cancelbt);
        panel.add(removebt);
        panel.add(addbt);
        panel.add(savebt);
        
        Layer.put(topLeft).atTopLeft(panel).in(layout).withMargin(5);
        Layer.put(topRight).in(layout)
            .atTopRight(panel).withMargin(5)
            .leftOf(topLeft).withMargin(5)
            .topOf(estimate).withMargin(5);
        Layer.put(estimate).in(layout)
            .bottomOf(topLeft).withMargin(5)
            .atLeft(panel).withMargin(5)
            .atRight(panel).withMargin(5);
        Layer.put(center).in(layout)
            .topOf(savebt).withMargin(5)
            .atLeft(panel).withMargin(5)
            .atRight(panel).withMargin(5)
            .bottomOf(estimate).withMargin(10);
        Layer.put(homebt).atBottomLeft(panel).in(layout).withMargin(15, 5);
        Layer.put(cancelbt).in(layout)
            .atBottom(panel).withMargin(15)
            .rightOf(removebt).withMargin(5);
        Layer.put(removebt).in(layout)
            .atBottom(panel).withMargin(15)
            .rightOf(addbt).withMargin(5);
        Layer.put(addbt).in(layout)
            .atBottom(panel).withMargin(15)
            .rightOf(savebt).withMargin(5);
        Layer.put(savebt).atBottomRight(panel).in(layout).withMargin(5);

        setTitle("Ghi nhận thu chi");
        setContentPane(panel);
    }

    private JPanel initTypeChoice () {
        payOut = new JRadioButton("Pay out", true);
        payOut.setActionCommand(PAYOUT_SELECTED_COMMAND);
        payOut.setText("Phiếu chi");
        payIn = new JRadioButton("Pay in", false);
        payIn.setActionCommand(PAYIN_SELECTED_COMMAND);
        payIn.setText("Phiếu thu");

        final ButtonGroup bg = new ButtonGroup();
        bg.add(payOut);
        bg.add(payIn);

        final JPanel topLeft = new JPanel();
        final TitledBorder tlBorder = BorderFactory.createTitledBorder(
                Constants.BD_GREYLINE,
                " Loại phiếu "
        );

        topLeft.setBorder(tlBorder);
        topLeft.setLayout(new BoxLayout(topLeft, BoxLayout.Y_AXIS));
        topLeft.setPreferredSize(new Dimension(150, 80));
        topLeft.add(payOut);
        topLeft.add(payIn);
        
        return topLeft;
    }

    private JPanel initNote () {
        note = new JTextArea();
        note.setLineWrap(true);

        final JPanel topRight = new JPanel();
        final TitledBorder trBorder = BorderFactory.createTitledBorder(
                Constants.BD_GREYLINE,
                " Ghi chú thu chi "
        );
        topRight.setBorder(trBorder);
        topRight.setLayout(new BorderLayout());
        topRight.add(note, BorderLayout.CENTER);

        return topRight;
    }

    private JPanel initTable (List<Payable> payObjects) {
        // init table
        final String[] colNames = {"Mã ĐT", "Loại ĐT", "Mô tả", "Giá", "Ghi chú"};
        if (payObjects != null) {
            final Object[][] data = new Object[payObjects.size()][5];
            for (int i = 0; i < payObjects.size(); i++){
                data[i][0] = payObjects.get(i).getId();
                data[i][1] = payObjects.get(i).getType();
                data[i][2] = payObjects.get(i).getDescription();
                data[i][3] = "";
                data[i][4] = "";
            }
            detailtab = new JTable(
                    new FinalTableModel(data, colNames, new int[]{0,1,2})
            );
        } else {
            detailtab = new JTable(new Object[0][0], colNames);
        }
        detailtab.setFillsViewportHeight(true);
        
        TableColumnModel tcm = detailtab.getColumnModel();
        
        // Cell align center
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = colNames.length - 1; i >= 0 ; i--) {
            tcm.getColumn(i).setCellRenderer(cr);
        }
        
        // Cell set with
        tcm.getColumn(1).setPreferredWidth(10);
        tcm.getColumn(2).setPreferredWidth(200);
        tcm.getColumn(3).setPreferredWidth(10);
        tcm.getColumn(4).setPreferredWidth(200);

        final JPanel center = new JPanel();
        final TitledBorder ctBorder = BorderFactory.createTitledBorder(
                Constants.BD_GREYLINE,
                " Đối tượng thu chi ");
        center.setBorder(ctBorder);
        center.setLayout(new BorderLayout());
        center.add(new JScrollPane(detailtab), BorderLayout.CENTER);
        return center;
    }

    private void initButton () {
        savebt = Genner.createButton("Lưu", Genner.BIG_SIZE);
        savebt.setActionCommand(Constants.AC_DONE);
        homebt =  Genner.createButton("Trang chủ", Genner.MEDIUM_SIZE);
        homebt.setActionCommand(Constants.AC_HOME);
        cancelbt = Genner.createButton("Huỷ", Genner.MEDIUM_SIZE);
        cancelbt.setActionCommand(Constants.AC_CANCEL);
        addbt = Genner.createButton("Thêm", Genner.MEDIUM_SIZE);
        addbt.setActionCommand(Constants.AC_ADD);
        removebt = Genner.createButton("Loại bỏ", Genner.MEDIUM_SIZE);
        removebt.setActionCommand(Constants.AC_RM);
    }

    private JPanel initEstimateCost () {
        estimateTotalCost = Genner.createButton("Mao", Genner.MEDIUM_SIZE);
        estimateTotalCost.setActionCommand(AC_EST_TOTAL);
        floorCost = Genner.createButton("Cao bang", Genner.MEDIUM_SIZE);
        floorCost.setActionCommand(AC_FLOOR_COST);
        estimatedValue = Genner.createNumberField(Genner.MEDIUM_SIZE);

        final JPanel ePane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final TitledBorder tbd = BorderFactory.createTitledBorder(
                Constants.BD_GREYLINE,
                "Cost estimate");
        ePane.setBorder(tbd);
        ePane.setPreferredSize(new Dimension(100, Constants.MEDIUM_HEIGHT + 32));

        ePane.add(estimatedValue);
        ePane.add(new JLabel(",000 vnd "));
        ePane.add(floorCost);
        ePane.add(estimateTotalCost);
        return ePane;
    }
    
    /**
     * Add controller (aka listener) for components of view
     * @param cfc
     */
    public void setController(CashFlowController cfc){
        payOut.addActionListener(cfc);
        payIn.addActionListener(cfc);
        savebt.addActionListener(cfc);
        homebt.addActionListener(cfc);
        addbt.addActionListener(cfc);
        removebt.addActionListener(cfc);
        cancelbt.addActionListener(cfc);
        estimateTotalCost.addActionListener(cfc);
        floorCost.addActionListener(cfc);
    }
    
    /**
     * Get text which user input to note frame
     * @return
     */
    public String getNote(){
        return this.note.getText();
    }
    
    /**
     * Get payable object list on center table
     * @return
     */
    public Object[][] getCashFlowDetail(){

        final TableModel tm = detailtab.getModel();
        final int nrow = tm.getRowCount();
        final Object[][] rs = new Object[nrow][4];

        for (int i = 0; i < nrow; i++){
            
            rs[i][0] = tm.getValueAt(i, 0);
            rs[i][1] = tm.getValueAt(i, 1);
            rs[i][3] = tm.getValueAt(i, 4);
            
            Object c3 = tm.getValueAt(i,  3);
            
            if (c3 instanceof String) {
                try {
                    rs[i][2] = Integer.parseInt(((String)c3).trim());
                } catch(Exception e) {
                    noticeError("Enter number only into 'Cost' column please!");
                    return null;
                }
            } else if
            (c3 instanceof Integer) {
                rs[i][2] = c3;
            } else {
                noticeError("Cost column value unacceptable!");
                System.out.println("c3 type: " + c3.getClass().toString());
            }
        }
        return rs;
    }

    public void noticeSaveResult(boolean rs) {
        noticeResult(
                rs,
                "Save done",
                "Save failse!"
        );
    }

    public int[] getVictims () {
        return detailtab.getSelectedRows();
    }

    public void floorTheCost(boolean flag) {
        final DefaultTableModel tabModel = (DefaultTableModel) detailtab.getModel();
        final int nrow = tabModel.getRowCount();
        final String estimated = estimatedValue.getText()
            .replaceAll("[^0-9]", "");
        final int est = estimated.isEmpty()
            ? 0
            : Integer.parseInt(estimated)
        ;
        final int val = flag
            ? est
            : est / nrow
        ;
        // push value in cost column (column 3)
        for (int i = 0; i < nrow; i++) {
            tabModel.setValueAt(val, i, 3);
        }
        estimatedValue.setText(estimated);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (! (o instanceof CashFlowModel))
            return;

        short code = (short) arg;
        CashFlowModel model = (CashFlowModel) o;
        if (code == CashFlowModel.PAY_OBJ_REMOVED) {
            int[] victimIndexs = model.getVictimIndexs();
            DefaultTableModel tabModel = (DefaultTableModel) detailtab.getModel();
            for (int i = victimIndexs.length - 1; i >= 0; i--) {
                tabModel.removeRow( victimIndexs[i] );
            }
        }
    }
}
