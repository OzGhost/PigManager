/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.Genner;
import common.Layer;
import controller.ControllerBase;
import controller.ToolAddController;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author duyphuoc
 */
public class ToolAddView extends ViewBase{
    private static final long serialVersionUID = 4705025519974462517L;
    private JLabel tool_name;
    private JLabel cost;
    private JLabel describe;
    private JLabel provider_id;
    private JLabel stables_id;
    public JTextField tool_name_tx;
    public JTextField cost_tx;
    public JTextField describe_tx;
    public JTextField provider_id_tx;
    public JTextField stables_id_tx;
    private JButton provider_sel;
    private JButton stables_sel;
    private JButton reset_bt;
    private JButton cancel_bt;
    private JButton add_bt;
    
    private final JPanel panel;
    
    public static final String CANCEL_COMMAND="cancel";
    public static final String RESET_COMMAND="reset";
    public static final String ADD_COMMAND="add";
    public static final String SELECT_PROVIDER_COMMAND="provider_sel";
    public static final String SELECT_STABLES_COMMAND="stables_sel";
    public static final String TITLE="Thêm vật dụng";
    
    public ToolAddView(){
        tool_name = new JLabel("Tên vật dụng");
        cost = new JLabel("Giá:");
        describe = new JLabel("Mô tả:");
        provider_id = new JLabel("Mã nhà cung cấp");
        stables_id = new JLabel("Mã chuông");
        tool_name_tx = new JTextField(15);
        cost_tx = new JTextField(15);
        describe_tx = new JTextField(15);
        provider_id_tx = new JTextField(15);
        provider_id_tx.setEditable(false);
        stables_id_tx = new JTextField(15);
        stables_id_tx.setEditable(false);
        
        //button 
        provider_sel = Genner.createButton("Chọn", Genner.SMALL_SIZE);
        provider_sel.setActionCommand(SELECT_PROVIDER_COMMAND);
        stables_sel = Genner.createButton("Chọn", Genner.SMALL_SIZE);
        stables_sel.setActionCommand(SELECT_STABLES_COMMAND);
        reset_bt = Genner.createButton("Làm mới", Genner.MEDIUM_SIZE);
        reset_bt.setActionCommand(RESET_COMMAND);
        add_bt = Genner.createButton("Thêm", Genner.MEDIUM_SIZE);
        add_bt.setActionCommand(ADD_COMMAND);
        cancel_bt =Genner.createButton("Hủy", Genner.BIG_SIZE);
        cancel_bt.setActionCommand(CANCEL_COMMAND);
        //panel 
        panel = new JPanel();
        final SpringLayout layout=new SpringLayout();
        final Border colorBD = BorderFactory.createLineBorder(Color.gray);
        final TitledBorder title = BorderFactory.createTitledBorder(colorBD, "Thêm vật dụng");
        panel.setLayout(layout);
        panel.setBorder(title);
        panel.setPreferredSize(new Dimension(100,100));
            //addi item
            panel.add(tool_name);
            panel.add(cost);
            panel.add(describe);
            panel.add(provider_id);
            panel.add(stables_id);
            panel.add(tool_name_tx);
            panel.add(cost_tx);
            panel.add(describe_tx);
            panel.add(provider_id_tx);
            panel.add(stables_id_tx);
            panel.add(provider_sel);
            panel.add(stables_sel);
            panel.add(reset_bt);
            panel.add(add_bt);
            panel.add(cancel_bt);
            
            
            
            
        //set location
        Layer.put(tool_name).in(layout).atTop(panel).withMargin(20).atLeft(panel).withMargin(10);
        Layer.put(tool_name_tx).in(layout).atTop(panel).withMargin(18).leftOf(tool_name).withMargin(5)
                .atRight(panel).withMargin(5);
        Layer.put(cost).in(layout).atLeft(panel).withMargin(10).bottomOf(tool_name).withMargin(25);
        Layer.put(cost_tx).in(layout).bottomOf(tool_name).withMargin(23).leftOf(cost).withMargin(5)
                .atRight(panel).withMargin(5);
        Layer.put(describe).in(layout).atLeft(panel).withMargin(10).bottomOf(cost).withMargin(25);
        Layer.put(describe_tx).in(layout).bottomOf(cost).withMargin(23).leftOf(describe).withMargin(5)
                .atRight(panel).withMargin(5);
        Layer.put(provider_id).in(layout).atLeft(panel).withMargin(10).bottomOf(describe).withMargin(25);
        Layer.put(provider_id_tx).in(layout).bottomOf(describe).withMargin(23).leftOf(provider_id).withMargin(5)
                .rightOf(provider_sel).withMargin(1);
        Layer.put(provider_sel).in(layout).atRight(panel).withMargin(5).bottomOf(describe).withMargin(22);
        Layer.put(stables_id).in(layout).atLeft(panel).withMargin(10).bottomOf(provider_id).withMargin(25);
        Layer.put(stables_id_tx).in(layout).bottomOf(provider_id).withMargin(23).leftOf(stables_id).withMargin(5)
                .rightOf(stables_sel).withMargin(1);
        Layer.put(stables_sel).in(layout).atRight(panel).withMargin(5).bottomOf(provider_id).withMargin(22);
        Layer.put(cancel_bt).in(layout).atBottomRight(panel).withMargin(5);
        Layer.put(add_bt).in(layout).atBottom(panel).withMargin(13).rightOf(cancel_bt).withMargin(5);
        Layer.put(reset_bt).in(layout).atBottom(panel).withMargin(13).rightOf(add_bt).withMargin(5);
        //
        add(panel);
        setTitle(TITLE);
        setPreferredSize(new Dimension(400,360));
        setResizable(false);
        this.getRootPane().setDefaultButton(add_bt);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    @Override
    public void setController(ControllerBase c){
        ToolAddController tac = (ToolAddController) c;
        provider_sel.addActionListener(tac);
        stables_sel.addActionListener(tac);
        cancel_bt.addActionListener(tac);
        reset_bt.addActionListener(tac);
        add_bt.addActionListener(tac);
    }
    
}
