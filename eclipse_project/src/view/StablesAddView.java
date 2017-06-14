/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import common.Genner;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import common.Layer;
import controller.ControllerBase;
import controller.StablesAddController;
import java.awt.Dimension;
import javax.swing.JFrame;
/**
 *
 * @author duyphuoc
 */
public class StablesAddView extends ViewBase{
    private static final long serialVersionUID = -3617338098446125739L;
    private JLabel status_lb;
    private JTextField status_tx;
    private JLabel number_lb;
    public JTextField number_tx;
    private JLabel location_lb;
    public JTextField location_tx;
    private JLabel describe_lb;
    public JTextField describe_tx;
    private JButton add_bt;
    private JButton cancel_bt;
    private JButton refresh_bt;
    private JPanel panel;
    
    public static final String CANCEL_COMMAND="cancel";
    public static final String ADD_COMMAND="add";
    public static final String REFRESH_COMMAND="refresh";
    
    public StablesAddView(){
        status_lb = new JLabel("Tình trạng");
        status_tx = new JTextField(15);
        status_tx.setText("Trống");
        status_tx.setEditable(false);
        number_lb = new JLabel("Số lương tối đa");
        number_tx = new JTextField(13);
        location_lb = new JLabel("Vị trí");
        location_tx = new JTextField(18);
        describe_lb = new JLabel("Mô tả");
        //describe_tx = new JTextField(12);
        describe_tx = new JTextField();
        describe_tx.setPreferredSize(new Dimension(0, 114));
        
        // button
        add_bt = Genner.createButton("Thêm", Genner.MEDIUM_SIZE);
        add_bt.setActionCommand(ADD_COMMAND);
        cancel_bt = Genner.createButton("Hủy", Genner.MEDIUM_SIZE);
        cancel_bt.setActionCommand(CANCEL_COMMAND);
        refresh_bt = Genner.createButton("Làm mới", Genner.MEDIUM_SIZE);
        refresh_bt.setActionCommand(REFRESH_COMMAND);
        //panel 
        panel = new JPanel();
        final Border colorBD = BorderFactory.createLineBorder(Color.gray);
        final TitledBorder mPanel = BorderFactory.createTitledBorder(colorBD, "");
        panel.setBorder(mPanel);
        final SpringLayout mainLayout = new SpringLayout();
        
        //set
        panel.setLayout(mainLayout);
        panel.add(status_lb);
        panel.add(status_tx);
        panel.add(number_lb);
        panel.add(number_tx);
        panel.add(location_lb);
        panel.add(location_tx);
        panel.add(describe_lb);
        panel.add(describe_tx);
        panel.add(add_bt);
        panel.add(cancel_bt);
        panel.add(refresh_bt);
        
        //set location
        Layer.put(status_lb).in(mainLayout).atTop(panel).withMargin(30).atLeft(panel).withMargin(15);
        Layer.put(status_tx).in(mainLayout).atTop(panel).withMargin(28).leftOf(status_lb).withMargin(8);
        Layer.put(location_lb).in(mainLayout).bottomOf(status_lb).withMargin(30).atLeft(panel).withMargin(15);
        Layer.put(location_tx).in(mainLayout).bottomOf(status_lb).withMargin(28).leftOf(location_lb).withMargin(5);
        Layer.put(number_lb).in(mainLayout).bottomOf(location_lb).withMargin(30).atLeft(panel).withMargin(15);
        Layer.put(number_tx).in(mainLayout).bottomOf(location_lb).withMargin(28).leftOf(number_lb).withMargin(5);
        Layer.put(describe_lb).in(mainLayout).atTop(panel).withMargin(30).leftOf(status_tx).withMargin(15);
        Layer.put(describe_tx).in(mainLayout).atTop(panel).withMargin(28).leftOf(describe_lb).withMargin(7).atRight(panel).withMargin(5);
        Layer.put(cancel_bt).in(mainLayout).atBottom(panel).withMargin(7).atRight(panel).withMargin(7);
        Layer.put(add_bt).in(mainLayout).atBottom(panel).withMargin(7).rightOf(cancel_bt).withMargin(7);
        Layer.put(refresh_bt).in(mainLayout).atBottom(panel).withMargin(7).rightOf(add_bt).withMargin(7);
        //set frame
        
        this.setTitle("Thêm chuồng");
        this.setSize(500, 250);
        this.add(panel);
        this.setResizable(false);
        this.getRootPane().setDefaultButton(add_bt);
    }

    @Override
    public void setController(ControllerBase c){
        StablesAddController sac = (StablesAddController) c;
        cancel_bt.addActionListener(sac);
        add_bt.addActionListener(sac);
        refresh_bt.addActionListener(sac);
        
    }
}
