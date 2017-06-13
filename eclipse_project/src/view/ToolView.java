/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.Genner;
import common.Layer;
import controller.ToolController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import common.*;

/**
 *
 * @author duyphuoc
 */
public class ToolView extends ViewBase{
    private static final long serialVersionUID = 1857414447041145980L;
    private JLabel tool_id;
    private JLabel tool_name;
    private JLabel cost;
    private JLabel describe;
    private JLabel provider_id;
    private JLabel stables_id;
    public JTextField tool_id_tx;
    public JTextField tool_name_tx;
    public JTextField cost_tx;
    public JTextField describe_tx;
    public JTextField provider_id_tx;
    public JTextField stables_id_tx;
    public JTextField search_tx;
    public JTable tool_tb;
    
    private JButton cancel_bt;
    private JButton search_bt;
    private JButton add_bt;
    private JButton delete_bt;
    private JButton update_bt;
    private JButton move_bt;
    private JButton home_bt;
    
    private final JPanel mainPanel;
    private final JPanel topPanel;
    private final JPanel bottomPanel;
    
    public DefaultTableModel dtm;
    
    public static final String CANCEL_COMMAND="cancel";
    public static final String SEARCH_COMMAND="search";
    public static final String ADD_COMMAND="add";
    public static final String DELETE_COMMAND="delete";
    public static final String UPDATE_COMMAND="update";
    public static final String MOVE_COMMAND="move";
    //constructor
    public ToolView(){
        tool_id= new JLabel("Mã vật dụng");
        tool_name = new JLabel("Tên vật dụng:");
        cost = new JLabel("Giá:");
        describe = new JLabel("Mô tả:");
        provider_id = new JLabel("Mã nhà cung cấp:");
        stables_id = new JLabel("Mã chuồng:");
        tool_id_tx = new JTextField(15);
        tool_id_tx.setEditable(false);
        tool_name_tx = new JTextField(15);
        cost_tx = new JTextField(15);
        describe_tx = new JTextField(15);
        provider_id_tx = new JTextField(15);
        provider_id_tx.setEditable(false);
        stables_id_tx = new JTextField(15);
        stables_id_tx.setEditable(false);
        search_tx = new JTextField();
        
        //table
        final String[] colNames ={"Mã vật dụng","Tên vật dụng","Giá mua","Mô tả","Mã nhà cung cấp","Mã chuồng"};
        tool_tb = new JTable(new Object[0][0], colNames);
        JScrollPane jsp= new JScrollPane(tool_tb);
        dtm = new DefaultTableModel(colNames,0);
        tool_tb.setFillsViewportHeight(true);
        
        //button
        cancel_bt = Genner.createButton("Hủy", Genner.BIG_SIZE);
        cancel_bt.setActionCommand(CANCEL_COMMAND);
        search_bt = Genner.createButton("Tìm", Genner.MEDIUM_SIZE);
        search_bt.setActionCommand(SEARCH_COMMAND);
        add_bt = Genner.createButton("Thêm", Genner.MEDIUM_SIZE);
        add_bt.setActionCommand(ADD_COMMAND);
        delete_bt = Genner.createButton("Xóa", Genner.MEDIUM_SIZE);
        delete_bt.setActionCommand(DELETE_COMMAND);
        update_bt = Genner.createButton("Cập nhật", Genner.MEDIUM_SIZE);
        update_bt.setActionCommand(UPDATE_COMMAND);
        move_bt = Genner.createButton("Di chuyển", Genner.MEDIUM_SIZE);
        move_bt.setActionCommand(MOVE_COMMAND);
        home_bt= Genner.createButton("Trang chủ", Genner.BIG_SIZE);
        home_bt.setActionCommand(Constants.AC_HOME);
        
        //panels
        mainPanel = new JPanel();
        topPanel = new JPanel();
        bottomPanel = new JPanel();
        //
        final Border colorBD = BorderFactory.createLineBorder(Color.gray);
        //
        final SpringLayout mainLayout = new SpringLayout();
        final SpringLayout topLayout = new SpringLayout();
        final SpringLayout bottomLayout = new SpringLayout();
        
        //init toppanel
        final TitledBorder topPanelBorder = BorderFactory.createTitledBorder(colorBD, "Tìm kiếm vật dụng");
        topPanel.setLayout(topLayout);
        topPanel.setBorder(topPanelBorder);
        topPanel.setPreferredSize(new Dimension(100,100));
            //additem 
            topPanel.add(search_tx);
            topPanel.add(search_bt);
        //init bottompanel
        final TitledBorder bottomPanelBorder = BorderFactory.createTitledBorder(colorBD, "Vật dụng");
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(bottomPanelBorder);
        bottomPanel.setPreferredSize(new Dimension(400,0));
            //add item 
            bottomPanel.add(jsp,BorderLayout.CENTER);
            
            
        //init main panel
        mainPanel.setLayout(mainLayout);
        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);
        mainPanel.add(tool_id);
        mainPanel.add(tool_name);
        mainPanel.add(cost);
        mainPanel.add(describe);
        mainPanel.add(provider_id);
        mainPanel.add(stables_id);
        mainPanel.add(tool_id_tx);
        mainPanel.add(tool_name_tx);
        mainPanel.add(cost_tx);
        mainPanel.add(describe_tx);
        mainPanel.add(provider_id_tx);
        mainPanel.add(stables_id_tx);
        mainPanel.add(cancel_bt);
        mainPanel.add(add_bt);
        mainPanel.add(delete_bt);
        mainPanel.add(update_bt);
        mainPanel.add(move_bt);
        mainPanel.add(home_bt);
        
        //set location for all of top panel
        Layer.put(topPanel).in(mainLayout).atTop(mainPanel).withMargin(10)
                .atLeft(mainPanel).withMargin(200)
                .atRight(mainPanel).withMargin(200);
        Layer.put(search_tx).in(topLayout).atLeft(topPanel).withMargin(5)
                .atTop(topPanel).withMargin(20)
                .rightOf(search_bt).withMargin(5);
        Layer.put(search_bt).in(topLayout)
                .atTop(topPanel).withMargin(14)
                .atRight(topPanel).withMargin(20);
        //set location for all of bottom panel
        Layer.put(bottomPanel).in(mainLayout)
                .atLeft(mainPanel).withMargin(3)
                .topOf(cancel_bt).withMargin(5)
                .rightOf(provider_id).withMargin(10)
                .bottomOf(topPanel).withMargin(1);
        //set location for main panel
        
        Layer.put(tool_id_tx).in(mainLayout).atRight(mainPanel).withMargin(10)
                .bottomOf(topPanel).withMargin(100);
        Layer.put(tool_id).in(mainLayout).bottomOf(topPanel).withMargin(102)
                .rightOf(tool_id_tx).withMargin(5);
        Layer.put(tool_name_tx).in(mainLayout).atRight(mainPanel).withMargin(10)
                .bottomOf(tool_id_tx).withMargin(30);
        Layer.put(tool_name).in(mainLayout).bottomOf(tool_id_tx).withMargin(32)
                .rightOf(tool_id_tx).withMargin(5);
        Layer.put(cost_tx).in(mainLayout).atRight(mainPanel).withMargin(10)
                .bottomOf(tool_name_tx).withMargin(30);
        Layer.put(cost).in(mainLayout).bottomOf(tool_name_tx).withMargin(32)
                .rightOf(cost_tx).withMargin(5);
        Layer.put(describe_tx).in(mainLayout).atRight(mainPanel).withMargin(10)
                .bottomOf(cost_tx).withMargin(30);
        Layer.put(describe).in(mainLayout).bottomOf(cost_tx).withMargin(32)
                .rightOf(describe_tx).withMargin(5);
        Layer.put(provider_id_tx).in(mainLayout).atRight(mainPanel).withMargin(10)
                .bottomOf(describe_tx).withMargin(30);
        Layer.put(provider_id).in(mainLayout).bottomOf(describe_tx).withMargin(32)
                .rightOf(provider_id_tx).withMargin(5);
        Layer.put(stables_id_tx).in(mainLayout).atRight(mainPanel).withMargin(10)
                .bottomOf(provider_id_tx).withMargin(30);
        Layer.put(stables_id).in(mainLayout).bottomOf(provider_id_tx).withMargin(32)
                .rightOf(stables_id_tx).withMargin(5);
        Layer.put(cancel_bt).in(mainLayout).atBottomRight(mainPanel).withMargin(5);
        Layer.put(add_bt).in(mainLayout).atBottom(mainPanel).withMargin(13).rightOf(cancel_bt).withMargin(7);
        Layer.put(delete_bt).in(mainLayout).atBottom(mainPanel).withMargin(13).rightOf(add_bt).withMargin(7);
        Layer.put(update_bt).in(mainLayout).atBottom(mainPanel).withMargin(13).rightOf(delete_bt).withMargin(7);
        Layer.put(move_bt).in(mainLayout).atBottom(mainPanel).withMargin(13).rightOf(update_bt).withMargin(7);
        Layer.put(home_bt).in(mainLayout).atBottomLeft(mainPanel).withMargin(5);

        //
        add(mainPanel);
        setTitle("Quản lý vật dụng");
        this.getRootPane().setDefaultButton(search_bt);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void setController(ToolController tc){
        cancel_bt.addActionListener(tc);
        search_bt.addActionListener(tc);
        add_bt.addActionListener(tc);
        delete_bt.addActionListener(tc);
        update_bt.addActionListener(tc);
        move_bt.addActionListener(tc);
        tool_tb.setModel(dtm);
        tool_tb.addMouseListener(tc);
    }
    
}
