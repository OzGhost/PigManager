package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import common.Constants;
import common.Genner;
import controller.HomeController;

public class HomeView extends ViewBase {

    //Fields
    private JButton bt_pigFeature;
    private JButton bt_stockFeature;
    private JButton bt_pastureFeature;
    private JButton bt_providerFeature;
    private JButton bt_reportFeature;
    private JButton bt_quit;
    private final JPanel panel = new JPanel(new GridLayout(0, 2));
    
    //Constructors
    public HomeView (HomeController ctl) {
        initButton();

        bt_pigFeature.addActionListener(ctl);
        bt_stockFeature.addActionListener(ctl);
        bt_pastureFeature.addActionListener(ctl);
        bt_providerFeature.addActionListener(ctl);
        bt_reportFeature.addActionListener(ctl);
        bt_quit.addActionListener(ctl);

        panel.add(bt_pigFeature);
        panel.add(bt_stockFeature);
        panel.add(bt_pastureFeature);
        panel.add(bt_providerFeature);
        panel.add(bt_reportFeature);
        panel.add(bt_quit);

        setContentPane(panel);
    }

    //Methods
    private void initButton () {
        bt_pigFeature = Genner.createButton("Quan ly Heo", Genner.BIG_SIZE);
        bt_pigFeature.setActionCommand(Constants.AC_PIG_FEAT);
        
        bt_stockFeature = Genner.createButton("Quan ly Kho", Genner.BIG_SIZE);
        bt_stockFeature.setActionCommand(Constants.AC_STOCK_FEAT);

        bt_pastureFeature = Genner.createButton("Quan ly Chuong", Genner.BIG_SIZE);
        bt_pastureFeature.setActionCommand(Constants.AC_PASTURE_FEAT);

        bt_providerFeature = Genner.createButton("Quan ly NCC", Genner.BIG_SIZE);
        bt_providerFeature.setActionCommand(Constants.AC_PROVIDER_FEAT);

        bt_reportFeature = Genner.createButton("Ket xuat bao cao", Genner.BIG_SIZE);
        bt_reportFeature.setActionCommand(Constants.AC_REPORT_FEAT);

        bt_quit = Genner.createButton("Thoat", Genner.BIG_SIZE);
        bt_quit.setActionCommand(Constants.AC_CANCEL);
    }

    public void shadow () {
        setVisible(false);
    }
}
