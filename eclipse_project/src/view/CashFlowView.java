package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import common.Constants;
import common.Genner;
import common.Payable;
import controller.CashFlowController;
import db.CashFlowDetail;

public class CashFlowView extends ViewBase {
    private static final long serialVersionUID = 1L;
    
    public static final String SAVE_COMMAND = "save";
    public static final String CANCEL_COMMAND = "cancel";
    public static final String ADD_COMMAND = "add";
    public static final String REMOVE_COMMAND = "remove";
    public static final String PAYOUT_SELECTED_COMMAND = "payout_switched";
    public static final String PAYIN_SELECTED_COMMAND = "payin_switched";
    
    private JRadioButton payOut;
    private JRadioButton payIn;
    private JTextArea note;
    private JTable detailtab;
    private JButton savebt;
    private JButton homebt;
    private JButton addbt;
    private JButton removebt;
    private JButton cancelbt;
    private JPanel panel;
    
    public CashFlowView (List<Payable> payObjects) {
        payOut = new JRadioButton("Pay out", true);
        payOut.setActionCommand(PAYOUT_SELECTED_COMMAND);
        payOut.setText("Phiếu chi");
        payIn = new JRadioButton("Pay in", false);
        payIn.setActionCommand(PAYIN_SELECTED_COMMAND);
        payIn.setText("Phiếu thu");
        note = new JTextArea();
        note.setLineWrap(true);
        
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
            detailtab = new JTable(data, colNames);
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
        
        
        savebt = Genner.createButton("Lưu", Genner.BIG_SIZE);
        savebt.setActionCommand(SAVE_COMMAND);
        homebt =  Genner.createButton("Trang chủ", Genner.MEDIUM_SIZE);
        homebt.setActionCommand(Constants.HOME_COMMAND);
        cancelbt = Genner.createButton("Huỷ", Genner.MEDIUM_SIZE);
        cancelbt.setActionCommand(CANCEL_COMMAND);
        addbt = Genner.createButton("Thêm", Genner.MEDIUM_SIZE);
        addbt.setActionCommand(ADD_COMMAND);
        removebt = Genner.createButton("Loại bỏ", Genner.MEDIUM_SIZE);
        removebt.setActionCommand(REMOVE_COMMAND);
        
        panel = new JPanel();
        
        final ButtonGroup bg = new ButtonGroup();
        bg.add(payOut);
        bg.add(payIn);
        
        // layout component
        final Border bd = BorderFactory.createLineBorder(Color.gray);
        
        final JPanel topLeft = new JPanel();
        final TitledBorder tlBorder = BorderFactory.createTitledBorder(bd, " Loại phiếu ");
        topLeft.setBorder(tlBorder);
        topLeft.setLayout(new BoxLayout(topLeft, BoxLayout.Y_AXIS));
        topLeft.setPreferredSize(new Dimension(150, 80));
        topLeft.add(payOut);
        topLeft.add(payIn);

        final JPanel topRight = new JPanel();
        final TitledBorder trBorder = BorderFactory.createTitledBorder(bd, " Ghi chú thu chi ");
        topRight.setBorder(trBorder);
        topRight.setLayout(new BorderLayout());
        topRight.add(note, BorderLayout.CENTER);
        
        final JPanel center = new JPanel();
        final TitledBorder ctBorder = BorderFactory.createTitledBorder(bd, " Đối tượng thu chi ");
        center.setBorder(ctBorder);
        center.setLayout(new BorderLayout());
        center.add(new JScrollPane(detailtab), BorderLayout.CENTER);
        
        final SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        panel.add(topLeft);
        panel.add(topRight);
        panel.add(center);
        panel.add(homebt);
        panel.add(cancelbt);
        panel.add(removebt);
        panel.add(addbt);
        panel.add(savebt);
        
        layout.putConstraint(SpringLayout.NORTH, topLeft, 5, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, topLeft, 5, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, topRight, 5, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, topRight, 5, SpringLayout.EAST, topLeft);
        layout.putConstraint(SpringLayout.EAST, topRight, -5, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.SOUTH, savebt, -5, SpringLayout.SOUTH, panel);
        layout.putConstraint(SpringLayout.EAST, savebt, -5, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.SOUTH, addbt, -15, SpringLayout.SOUTH, panel);
        layout.putConstraint(SpringLayout.EAST, addbt, -5, SpringLayout.WEST, savebt);
        layout.putConstraint(SpringLayout.SOUTH, removebt, -15, SpringLayout.SOUTH, panel);
        layout.putConstraint(SpringLayout.EAST, removebt, -5, SpringLayout.WEST, addbt);
        layout.putConstraint(SpringLayout.SOUTH, cancelbt, -15, SpringLayout.SOUTH, panel);
        layout.putConstraint(SpringLayout.EAST, cancelbt, -5, SpringLayout.WEST, removebt);
        layout.putConstraint(SpringLayout.SOUTH, homebt, -15, SpringLayout.SOUTH, panel);
        layout.putConstraint(SpringLayout.WEST, homebt, 5, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, center, 5, SpringLayout.SOUTH, topLeft);
        layout.putConstraint(SpringLayout.SOUTH, center, -5, SpringLayout.NORTH, savebt);
        layout.putConstraint(SpringLayout.WEST, center, 5, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.EAST, center, -5, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.SOUTH, topRight, -5, SpringLayout.NORTH, center);
        
        setSize(size);
        setTitle("Ghi nhận thu chi");
        setContentPane(new JScrollPane(panel));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void setController(CashFlowController cfc){
        this.payOut.addActionListener(cfc);
        this.payIn.addActionListener(cfc);
        this.savebt.addActionListener(cfc);
        this.homebt.addActionListener(cfc);
        this.addbt.addActionListener(cfc);
        this.removebt.addActionListener(cfc);
        this.cancelbt.addActionListener(cfc);
    }
    
    public String getNote(){
        return this.note.getText();
    }
    
    public List<CashFlowDetail> getCashFlow(){
        List<CashFlowDetail> rs = new ArrayList<>();
        TableModel tm = detailtab.getModel();
        for (int i = 0; i < tm.getRowCount(); i++){
            String c3 = (String) tm.getValueAt(i,  3);
            c3.trim();
            try {
                rs.add(new CashFlowDetail(
                    (String) tm.getValueAt(i, 0),
                    (String) tm.getValueAt(i, 1),
                    (c3.isEmpty() ? 0 : Integer.parseInt(c3)),
                    (String) tm.getValueAt(i, 4)
                ));
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Enter number only into 'Cost' column please!",
                        "Input invalid",
                        JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        return rs;
    }
    
    @Override
    public void update(Observable o, Object arg) {
    }
}
