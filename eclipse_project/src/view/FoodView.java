/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.Genner;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import common.Layer;
import controller.FoodController;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dang Nhat Hai Long
 */
public class FoodView extends ViewBase
{
    public JPanel contentpane, _panelTop, _panelMid, _panelBot;
    public JComboBox _cbxTimKiem;
    public JTextField _txtfTimKiem, _txtfMaLoai, _txtfTenLoai, _txtfDonVi, _txtfMucBaoDong;
    public JButton _btnTimKiem, _btnThem, _btnXoa, _btnCapNhat, _btnLuu, _btnChonLoai;
    public JTable _tbThucAn;
    public JLabel _lblMaloai, _lblTenLoai, _lblMota, _lblDonVi, _lblMuaBaoDong;
    public JTextArea _txtaMota;
    final private Border _border = BorderFactory.createLineBorder(Color.gray);
    private TitledBorder _titleTimKiem;
    public DefaultTableModel dtm;
    
    public static final short SAVE_DONE_CODE = 0;
    public static final short SAVE_FAILURE_CODE = 1;
    public static final String SAVE_COMMAND = "save";
    public static final String ADD_COMMAND = "add";
    public static final String UPDATE_COMMAND = "update";
    public static final String REMOVE_COMMAND = "remove";
    public static final String SELECT_FOOD_COMMAND = "select provider";
    public static final String SEARCH_COMMAND = "search";
    
    public FoodView()
    {
        contentpane = new JPanel();
        SpringLayout sl_contentpane = new SpringLayout();
        contentpane.setLayout(sl_contentpane);
        
        
        //_panelTop
        _panelTop = new JPanel();
        _panelTop.setPreferredSize(new Dimension(450, 80));
        _titleTimKiem = new TitledBorder(_border, "Tìm kiếm loại thức ăn");
        _panelTop.setBorder(_titleTimKiem);
        SpringLayout sl_panelTop = new SpringLayout();
        _panelTop.setLayout(sl_panelTop);
        
        
        _cbxTimKiem = new JComboBox();
        _cbxTimKiem.addItem("Tên loại");
        
        _txtfTimKiem = new JTextField(18);
        _txtfTimKiem.setText("");
        
        _btnTimKiem = Genner.createButton("Tìm kiếm", Genner.MEDIUM_SIZE);
        _btnTimKiem.setActionCommand(SEARCH_COMMAND);
        
        sl_panelTop.putConstraint(SpringLayout.HORIZONTAL_CENTER, _txtfTimKiem, 0, SpringLayout.HORIZONTAL_CENTER, _panelTop);
        sl_panelTop.putConstraint(SpringLayout.VERTICAL_CENTER, _txtfTimKiem, 0, SpringLayout.VERTICAL_CENTER, _panelTop);
        sl_panelTop.putConstraint(SpringLayout.VERTICAL_CENTER, _cbxTimKiem, 0, SpringLayout.VERTICAL_CENTER, _txtfTimKiem);
        Layer.put(_cbxTimKiem).in(sl_panelTop).rightOf(_txtfTimKiem).withMargin(10);
        sl_panelTop.putConstraint(SpringLayout.VERTICAL_CENTER, _btnTimKiem, 0, SpringLayout.VERTICAL_CENTER, _txtfTimKiem);
        Layer.put(_btnTimKiem).in(sl_panelTop).leftOf(_txtfTimKiem).withMargin(10);
        
        _panelTop.add(_cbxTimKiem);
        _panelTop.add(_txtfTimKiem);
        _panelTop.add(_btnTimKiem);
        contentpane.add(_panelTop);
        
        
        //_panelMid
        _panelMid = new JPanel();
        SpringLayout sl_panelMid = new SpringLayout();
        _panelMid.setLayout(sl_panelMid);
        _panelMid.setPreferredSize(new Dimension(600, 150));
        _panelMid.setBorder(_border);
        
        _lblMaloai = new JLabel("Mã loại");
        _lblTenLoai = new JLabel("Tên loại");
        _lblMota = new JLabel("Mô tả");
        _lblDonVi = new JLabel("Đơn vị");
        _lblMuaBaoDong = new JLabel("<html>Mức <br> báo <br>động</html>");
        
        _txtfMaLoai = new JTextField(20);
        _txtfMaLoai.setEditable(false);
        _txtfTenLoai = new JTextField(20);
        _txtfDonVi = new JTextField(20);
        _txtfMucBaoDong = new JTextField(20);
        
        _txtaMota = new JTextArea(6,25);
        JScrollPane _scrollMota = new JScrollPane(_txtaMota, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        Layer.put(_lblMaloai).in(sl_panelMid).atTop(_panelMid).withMargin(30).atLeft(_panelMid).withMargin(10);
        Layer.put(_lblTenLoai).in(sl_panelMid).atLeft(_panelMid).withMargin(10).bottomOf(_lblMaloai).withMargin(30);
        Layer.put(_lblDonVi).in(sl_panelMid).leftOf(_txtfMaLoai).withMargin(30).atTop(_panelMid).withMargin(30);
        Layer.put(_lblMuaBaoDong).in(sl_panelMid).bottomOf(_lblDonVi).withMargin(30).leftOf(_txtfTenLoai).withMargin(30);
        
        Layer.put(_txtfMaLoai).in(sl_panelMid).leftOf(_lblMaloai).withMargin(10);
        sl_panelMid.putConstraint(SpringLayout.NORTH, _txtfMaLoai, 0, SpringLayout.NORTH, _lblMaloai);
        Layer.put(_txtfTenLoai).in(sl_panelMid).bottomOf(_lblMaloai).withMargin(30);
        sl_panelMid.putConstraint(SpringLayout.WEST, _txtfTenLoai, 0, SpringLayout.WEST, _txtfMaLoai);
        Layer.put(_txtfDonVi).in(sl_panelMid).leftOf(_lblDonVi).withMargin(10);
        sl_panelMid.putConstraint(SpringLayout.NORTH, _txtfDonVi, 0, SpringLayout.NORTH, _lblDonVi);
        Layer.put(_txtfMucBaoDong).in(sl_panelMid).bottomOf(_txtfDonVi).withMargin(30);
        sl_panelMid.putConstraint(SpringLayout.NORTH, _txtfMucBaoDong, 0, SpringLayout.NORTH, _lblMuaBaoDong);
        sl_panelMid.putConstraint(SpringLayout.WEST, _txtfMucBaoDong, 0, SpringLayout.WEST, _txtfDonVi);
        
        Layer.put(_lblMota).in(sl_panelMid).rightOf(_scrollMota).withMargin(10);
        sl_panelMid.putConstraint(SpringLayout.NORTH, _lblMota, 0, SpringLayout.NORTH, _scrollMota);
        Layer.put(_scrollMota).in(sl_panelMid).atTopRight(_panelMid).withMargin(10).atBottom(_panelMid).withMargin(10);
        
        
        _panelMid.add(_lblMaloai);
        _panelMid.add(_lblTenLoai);
        _panelMid.add(_lblMota);
        _panelMid.add(_lblDonVi);
        _panelMid.add(_lblMuaBaoDong);
        
        _panelMid.add(_txtfMaLoai);
        _panelMid.add(_txtfTenLoai);
        _panelMid.add(_txtfDonVi);
        _panelMid.add(_txtfMucBaoDong);
        _panelMid.add(_scrollMota);
        contentpane.add(_panelMid);
        
        
        //_panelBot
        _panelBot = new JPanel();
        _panelBot.setLayout(new BorderLayout());
        _panelBot.setBorder(_border);
        _panelBot.setPreferredSize(new Dimension(1000, 500));
        
        final String[] _columnsTBThucAn =
        {
            "Mã loại thức ăn", "Tên loại thức ăn", "Mô tả", "Đơn vị", "Mức báo động"
        };
        dtm = new DefaultTableModel(_columnsTBThucAn,0);
        
        
        _tbThucAn = new JTable();
        _tbThucAn.setFillsViewportHeight(true);
        _tbThucAn.setModel(dtm);
        JScrollPane _scrollTBThucAn = new JScrollPane(_tbThucAn, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        _panelBot.add(_scrollTBThucAn);
        
        
        contentpane.add(_panelBot);
        //Button
        _btnThem = Genner.createButton("Thêm", Genner.MEDIUM_SIZE);
        _btnThem.setActionCommand(ADD_COMMAND);
        _btnXoa = Genner.createButton("Xóa", Genner.MEDIUM_SIZE);
        _btnXoa.setActionCommand(REMOVE_COMMAND);
        _btnCapNhat = Genner.createButton("Cập nhật", Genner.MEDIUM_SIZE);
        _btnCapNhat.setActionCommand(UPDATE_COMMAND);
        
        _btnLuu = Genner.createButton("Lưu", Genner.MEDIUM_SIZE);
        
        _btnChonLoai = Genner.createButton("Chọn loại", Genner.MEDIUM_SIZE);
        _btnChonLoai.setActionCommand(SELECT_FOOD_COMMAND);
        
        contentpane.add(_btnThem);
        contentpane.add(_btnXoa);
        contentpane.add(_btnCapNhat);
        contentpane.add(_btnLuu);
        contentpane.add(_btnChonLoai);
        
        
        //layer
        Layer.put(_panelTop).in(sl_contentpane).atTop(contentpane).withMargin(20);
        sl_contentpane.putConstraint(SpringLayout.HORIZONTAL_CENTER, _panelTop, 0, SpringLayout.HORIZONTAL_CENTER, contentpane);
        Layer.put(_panelMid).in(sl_contentpane).atLeft(contentpane).withMargin(200).atRight(contentpane).withMargin(200).bottomOf(_panelTop).withMargin(10);
        Layer.put(_panelBot).in(sl_contentpane).atLeft(contentpane).withMargin(200).bottomOf(_panelMid).withMargin(10).atRight(contentpane).withMargin(200).atBottom(contentpane).withMargin(30);
        sl_contentpane.putConstraint(SpringLayout.VERTICAL_CENTER, _btnCapNhat, 0, SpringLayout.VERTICAL_CENTER, _panelBot);
        Layer.put(_btnCapNhat).in(sl_contentpane).leftOf(_panelBot).withMargin(25);
        Layer.put(_btnXoa).in(sl_contentpane).topOf(_btnCapNhat).withMargin(20).leftOf(_panelBot).withMargin(25);
        Layer.put(_btnThem).in(sl_contentpane).topOf(_btnXoa).withMargin(20).leftOf(_panelBot).withMargin(25);
        Layer.put(_btnLuu).in(sl_contentpane).bottomOf(_btnCapNhat).withMargin(20).leftOf(_panelBot).withMargin(25);
        Layer.put(_btnChonLoai).in(sl_contentpane).bottomOf(_btnLuu).withMargin(20).leftOf(_panelBot).withMargin(25);
        
        
        //set form
        setSize(new Dimension(1366, 730));
        setTitle("Loại thức ăn");
        setResizable(false);
        setContentPane(contentpane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    
    public void setController(FoodController fc)
    {
        _btnTimKiem.addActionListener(fc);
        _btnThem.addActionListener(fc);
        _btnXoa.addActionListener(fc);
        _btnCapNhat.addActionListener(fc);
        _btnLuu.addActionListener(fc);
        _btnChonLoai.addActionListener(fc);
        
        _tbThucAn.addMouseListener(fc);
        
    }

    void notice(short code)
    {
        
    }
}
