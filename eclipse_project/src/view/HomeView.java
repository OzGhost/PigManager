package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import common.Constants;
import common.Genner;
import controller.HomeController;

public class HomeView extends ViewBase {

    private static final long serialVersionUID = 5337391590871863765L;
    //Fields
    private JButton bt_pigFeature;
    private JButton bt_stockFeature;
    private JButton bt_pastureFeature;
    private JButton bt_providerFeature;
    private JButton bt_sickLogFeature;
    private JButton bt_sickFeature;
    private JButton bt_cashFlowFeature;
    private JButton bt_cashFlowLogFeature;
    private JButton bt_foodFeature;
    private JButton bt_toolFeature;
    private JButton bt_quit;
    private final JPanel panel = new JPanel(new GridLayout(0, 3));
    
    //Constructors
    public HomeView (HomeController ctl) {
        initButton();

        bt_pigFeature.addActionListener(ctl);
        bt_stockFeature.addActionListener(ctl);
        bt_pastureFeature.addActionListener(ctl);
        bt_providerFeature.addActionListener(ctl);
        bt_sickLogFeature.addActionListener(ctl);
        bt_sickFeature.addActionListener(ctl);
        bt_cashFlowFeature.addActionListener(ctl);
        bt_cashFlowLogFeature.addActionListener(ctl);
        bt_foodFeature.addActionListener(ctl);
        bt_toolFeature.addActionListener(ctl);
        bt_quit.addActionListener(ctl);

        panel.add(bt_pigFeature);
        panel.add(bt_stockFeature);
        panel.add(bt_pastureFeature);
        panel.add(bt_providerFeature);
        panel.add(bt_sickLogFeature);
        panel.add(bt_sickFeature);
        panel.add(bt_cashFlowFeature);
        panel.add(bt_cashFlowLogFeature);
        panel.add(bt_foodFeature);
        panel.add(bt_toolFeature);
        panel.add(bt_quit);

        setContentPane(panel);
    }

    //Methods
    private void initButton () {
        bt_pigFeature = Genner.createButton("Quản lý heo", Genner.BIG_SIZE);
        bt_pigFeature.setActionCommand(Constants.AC_PIG_FEAT);
        
        bt_stockFeature = Genner.createButton("Quản lý kho", Genner.BIG_SIZE);
        bt_stockFeature.setActionCommand(Constants.AC_STOCK_FEAT);

        bt_pastureFeature = Genner.createButton("Quản lý chuồng", Genner.BIG_SIZE);
        bt_pastureFeature.setActionCommand(Constants.AC_PASTURE_FEAT);

        bt_providerFeature = Genner.createButton("Quản lý nhà cung cấp", Genner.BIG_SIZE);
        bt_providerFeature.setActionCommand(Constants.AC_PROVIDER_FEAT);

        bt_cashFlowFeature = Genner.createButton("Lich su thu chi", Genner.BIG_SIZE);
        bt_cashFlowFeature.setActionCommand(Constants.AC_CASHFLOW_FEAT);

        bt_cashFlowLogFeature = Genner.createButton("Ghi nhan thu chi", Genner.BIG_SIZE);
        bt_cashFlowLogFeature.setActionCommand(Constants.AC_CASHLOG_FEAT);

        bt_foodFeature = Genner.createButton("Quan ly thuc an", Genner.BIG_SIZE);
        bt_foodFeature.setActionCommand(Constants.AC_FOOD_FEAT);

        bt_sickFeature = Genner.createButton("Quan ly benh", Genner.BIG_SIZE);
        bt_sickFeature.setActionCommand(Constants.AC_SICK_FEAT);

        bt_sickLogFeature = Genner.createButton("Quan ly benh an", Genner.BIG_SIZE);
        bt_sickLogFeature.setActionCommand(Constants.AC_SICKLOG_FEAT);

        bt_toolFeature = Genner.createButton("Quan ly vat dung", Genner.BIG_SIZE);
        bt_toolFeature.setActionCommand(Constants.AC_TOOL_FEAT);

        bt_quit = Genner.createButton("Thoát", Genner.BIG_SIZE);
        bt_quit.setActionCommand(Constants.AC_CANCEL);
    }

    public void shadow () {
        setVisible(false);
    }
}
