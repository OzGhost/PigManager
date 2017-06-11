/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.SpringLayout;

import common.Genner;
import common.Layer;
import controller.StablesController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.ResultSet;
import db.db;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyphuoc
 */
public class StablesView extends ViewBase {
    
    
    private JLabel status;
    public JComboBox statusCb;
    private JLabel number;
    public JComboBox numberCb;
    public JTable gridTb;
    private JButton searchBt;
    private JButton saveBt;
    private JButton addBt;
    private JButton deleteBt;
    private JButton cancelBt;
    private JButton selectBt;
    private JPanel panel;
    private JLabel stables_id_lb;
    private JLabel status_lb;
    private JLabel number_lb;
    private JLabel location_lb;
    private JLabel describe_lb;
    public JTextField stables_id_tx;
    public JTextField status_tx;
    public JTextField number_tx;
    public JTextField location_tx;
    public JTextField describe_tx;
    
    public static final String ADD_ITEM_CB_COMMAND="select distinct soluongtoida from chuong order by soluongtoida";
    public static final String SEARCH_COMMAND="search";
    public static final String SAVE_COMMAND="save";
    public static final String ADD_COMMAND="add";
    public static final String DELETE_COMMAND="delete";
    public static final String CANCEL_COMMAND="cancel";
    public static final String SELECT_COMMAND="select";
    
    public static DefaultTableModel dtm;
     
//constructors
    public StablesView(){
        status = new JLabel("Tình trạng");
        statusCb = new JComboBox();
        statusCb.addItem(null);
        statusCb.addItem("Trống");
        statusCb.addItem("Đầy");
        statusCb.addItem("Đang nuôi");
        number = new JLabel("Số lượng tối đa");
        numberCb = new JComboBox();
        numberCb.addItem(null);
        ResultSet rs = db.sendForResult(ADD_ITEM_CB_COMMAND);
        try {
            while(rs.next())
            {
                numberCb.addItem(rs.getInt("soluongtoida"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StablesView.class.getName()).log(Level.SEVERE, null, ex);
        }
        stables_id_lb = new JLabel("Mã chuồng");
        stables_id_tx = new JTextField(15);
        stables_id_tx.setEditable(false);
        status_lb = new JLabel("Tình trạng");
        status_tx = new JTextField(6);
        status_tx.setEditable(false);
        number_lb = new JLabel("Số lượng tối đa");
        number_tx = new JTextField(13);
        location_lb = new JLabel("Vị trí");
        location_tx = new JTextField(18);
        describe_lb = new JLabel("Mô tả");
        describe_tx = new JTextField();
        describe_tx.setPreferredSize(new Dimension(0,68));
     
        //init table
        final String[] colNames ={"Mã chuồng", "Tình trạng", "Số lượng tối đa", "Vị trí","Mô tả"};
        gridTb = new JTable(new Object[0][0],colNames); 
        gridTb.setFillsViewportHeight(true);
        
       //button create
       searchBt =Genner.createButton("Tìm", Genner.MEDIUM_SIZE);
       searchBt.setActionCommand(SEARCH_COMMAND);
       saveBt = Genner.createButton("Cập nhật", Genner.MEDIUM_SIZE);
       saveBt.setActionCommand(SAVE_COMMAND);
       addBt = Genner.createButton("Thêm", Genner.MEDIUM_SIZE);
       addBt.setActionCommand(ADD_COMMAND);
       deleteBt = Genner.createButton("Xóa", Genner.MEDIUM_SIZE);
       deleteBt.setActionCommand(DELETE_COMMAND);
       cancelBt = Genner.createButton("Hủy", Genner.BIG_SIZE);
       cancelBt.setActionCommand(CANCEL_COMMAND);
       selectBt = Genner.createButton("Chọn", Genner.BIG_SIZE);
       selectBt.setActionCommand(SELECT_COMMAND);
       //panel
       panel = new JPanel();
       
       //color border
       final Border colorBD = BorderFactory.createLineBorder(Color.gray);
       
       //top left panel 
       
       final JPanel topLeftPanel = new JPanel();
       final SpringLayout TopLeftLayout = new SpringLayout();
       final TitledBorder tlPanel =BorderFactory.createTitledBorder(colorBD, "Tìm theo");
       topLeftPanel.setLayout(TopLeftLayout);
       topLeftPanel.setBorder(tlPanel);
       topLeftPanel.setPreferredSize(new Dimension(325,75));
       //----> add item 
       
       topLeftPanel.add(status);
       topLeftPanel.add(statusCb);
       topLeftPanel.add(number);
       topLeftPanel.add(numberCb);
       //top right panel
       final JPanel topRightPanel= new JPanel();
       final SpringLayout TopRightLayout = new SpringLayout();
       final TitledBorder trPanel =BorderFactory.createTitledBorder(colorBD, "Chỉnh sửa");
       topRightPanel.setLayout(TopRightLayout);
       topRightPanel.setBorder(trPanel);
       topRightPanel.setPreferredSize(new Dimension(0,150));
       //----> add item 
       topRightPanel.add(stables_id_lb);
       topRightPanel.add(stables_id_tx);
       topRightPanel.add(status_lb);
       topRightPanel.add(status_tx);
       topRightPanel.add(number_lb);
       topRightPanel.add(number_tx);
       topRightPanel.add(location_lb);
       topRightPanel.add(location_tx);
       topRightPanel.add(describe_lb);
       topRightPanel.add(describe_tx);
       //center panel
       
       final JPanel centerPanel = new JPanel();
       final TitledBorder cPanel = BorderFactory.createTitledBorder(colorBD, "Chuồng");
       centerPanel.setBorder(cPanel);
       centerPanel.setLayout(new BorderLayout());
       //---> add item
       JScrollPane jsp= new JScrollPane(gridTb);
       centerPanel.add(jsp, BorderLayout.CENTER);
       dtm = new DefaultTableModel(colNames,0);
       
       //main panel
       
       final SpringLayout MainLayout= new SpringLayout();
       panel.setLayout(MainLayout);
       panel.add(topLeftPanel);
       panel.add(topRightPanel);
       panel.add(centerPanel);
       panel.add(searchBt);
       panel.add(saveBt);
       panel.add(addBt);
       panel.add(deleteBt);
       panel.add(cancelBt);
       panel.add(selectBt);
       //set location top left panel 
       Layer.put(topLeftPanel).atTop(panel).in(MainLayout).withMargin(78)
               .atLeft(panel).withMargin(3);
       Layer.put(status).in(TopLeftLayout).atTop(topLeftPanel).withMargin(15).atLeft(topLeftPanel).withMargin(3);
       Layer.put(statusCb).in(TopLeftLayout).atTop(topLeftPanel).withMargin(13).leftOf(status).withMargin(5);
       Layer.put(number).in(TopLeftLayout).atTop(topLeftPanel).withMargin(15).leftOf(statusCb).withMargin(10);
       Layer.put(numberCb).in(TopLeftLayout).atTop(topLeftPanel).withMargin(13).leftOf(number).withMargin(5);
       //set location top right panel
       Layer.put(topRightPanel).atTop(panel).in(MainLayout).withMargin(3)
               .atRight(panel).withMargin(3)
               .leftOf(topLeftPanel).withMargin(5);
       
       Layer.put(stables_id_lb).in(TopRightLayout).atTop(topRightPanel).withMargin(3)
               .atLeft(topRightPanel).withMargin(3);
       Layer.put(stables_id_tx).in(TopRightLayout).atTop(topRightPanel)
               .leftOf(stables_id_lb).withMargin(5);
       Layer.put(status_lb).in(TopRightLayout).atTop(topRightPanel).withMargin(3)
               .leftOf(stables_id_tx).withMargin(10);
       Layer.put(status_tx).in(TopRightLayout).atTop(topRightPanel)
               .leftOf(status_lb).withMargin(5);
       Layer.put(number_lb).in(TopRightLayout).bottomOf(stables_id_lb).withMargin(30)
               .atLeft(topRightPanel).withMargin(3);
       Layer.put(number_tx).in(TopRightLayout).bottomOf(stables_id_lb).withMargin(28)
               .leftOf(number_lb).withMargin(4);
       Layer.put(location_lb).in(TopRightLayout).bottomOf(number_lb).withMargin(30)
               .atLeft(topRightPanel).withMargin(3);
       Layer.put(location_tx).in(TopRightLayout).bottomOf(number_lb).withMargin(28)
               .leftOf(location_lb).withMargin(5);
       Layer.put(describe_lb).in(TopRightLayout).bottomOf(status_lb).withMargin(30)
               .leftOf(number_tx).withMargin(10);
       Layer.put(describe_tx).in(TopRightLayout).bottomOf(status_lb).withMargin(28)
               .leftOf(describe_lb).withMargin(4).atRight(topRightPanel).withMargin(5);
       
       
       //set location center panel 
       Layer.put(centerPanel).in(MainLayout).bottomOf(topRightPanel).withMargin(3)
               .atLeft(panel).withMargin(3)
               .atRight(panel).withMargin(3)
               .topOf(cancelBt).withMargin(3);
      
       //set location main panel
       Layer.put(addBt).in(MainLayout).atBottom(panel).withMargin(23).rightOf(saveBt).withMargin(7);
       Layer.put(saveBt).in(MainLayout).atBottom(panel).withMargin(23).rightOf(deleteBt).withMargin(7);
       Layer.put(deleteBt).in(MainLayout).atBottom(panel).withMargin(23).rightOf(searchBt).withMargin(7);
       Layer.put(searchBt).in(MainLayout).atBottom(panel).withMargin(23).rightOf(cancelBt).withMargin(7);
       Layer.put(cancelBt).atBottomRight(panel).in(MainLayout).withMargin(15);
       Layer.put(selectBt).atBottomLeft(panel).in(MainLayout).withMargin(15);
       
       //add main panel into frame 
       add(panel);
       //
       
       setTitle("Quản lý chuồng");
       this.getRootPane().setDefaultButton(searchBt);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    public void setController(StablesController sc)
    {
        searchBt.addActionListener(sc);
        saveBt.addActionListener(sc);
        addBt.addActionListener(sc);
        deleteBt.addActionListener(sc);
        cancelBt.addActionListener(sc);
        selectBt.addActionListener(sc);
        gridTb.setModel(dtm);
        gridTb.addMouseListener(sc);
    }
}
