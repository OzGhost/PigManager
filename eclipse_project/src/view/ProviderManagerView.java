/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.Constants;
import common.Genner;
import common.Layer;
import controller.ProviderManagerController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Dang Nhat Hai Long
 */
public class ProviderManagerView extends ViewBase
{

    public JPanel contentpane, _panelTop, _panelMidLeft, _panelMidRight, _panelBot;
    public JComboBox _cbxTimKiem;
    public JTextField _txtfTimKiem, _txtfMaNCC, _txtfTenNCC, _txtfSDT, _txtfDiaChi, _txtfNoPhaiTra;
    public JButton _btnTimKiem, _btnThem, _btnXoa, _btnSua, _btnChonNCC;
    public JTextArea _txtaMoTa;
    public JTable _tbNCC;
    public DefaultTableModel dtm;
    
    public static final short SAVE_DONE_CODE = 0;
    public static final short SAVE_FAILURE_CODE = 1;
    public static final String SAVE_COMMAND = "save";
    public static final String ADD_COMMAND = "add";
    public static final String UPDATE_COMMAND = "update";
    public static final String REMOVE_COMMAND = "remove";
    public static final String SELECT_PROVIDER_COMMAND = "select provider";
    public static final String SEARCH_COMMAND = "search";


    public ProviderManagerView()
    {
        contentpane = new JPanel();
        final Border _border = BorderFactory.createLineBorder(Color.gray);

        //_panelTimKiem
        final TitledBorder _titleTimKiem = BorderFactory.createTitledBorder(_border, "Tìm kiếm nhà cung cấp");
        _titleTimKiem.setTitleJustification(TitledBorder.CENTER);
        _panelTop = new JPanel();
        _panelTop.setPreferredSize(new Dimension(400, 80));
        _panelTop.setBorder(_titleTimKiem);
        SpringLayout sl_panelTopRight = new SpringLayout();
        _panelTop.setLayout(sl_panelTopRight);

        _cbxTimKiem = new JComboBox();
        _cbxTimKiem.addItem("Tên");
        _txtfTimKiem = new JTextField(20);

        _btnTimKiem = Genner.createButton("Tìm kiếm", Genner.MEDIUM_SIZE);
        _btnTimKiem.setActionCommand(SEARCH_COMMAND);


        sl_panelTopRight.putConstraint(SpringLayout.HORIZONTAL_CENTER, _txtfTimKiem, 0, SpringLayout.HORIZONTAL_CENTER, _panelTop);
        sl_panelTopRight.putConstraint(SpringLayout.VERTICAL_CENTER, _txtfTimKiem, 0, SpringLayout.VERTICAL_CENTER, _panelTop);

        Layer.put(_btnTimKiem).in(sl_panelTopRight).leftOf(_txtfTimKiem).withMargin(10);
        sl_panelTopRight.putConstraint(SpringLayout.VERTICAL_CENTER, _btnTimKiem, 0, SpringLayout.VERTICAL_CENTER, _panelTop);

        Layer.put(_cbxTimKiem).in(sl_panelTopRight).rightOf(_txtfTimKiem).withMargin(10);
        sl_panelTopRight.putConstraint(SpringLayout.VERTICAL_CENTER, _cbxTimKiem, 0, SpringLayout.VERTICAL_CENTER, _panelTop);

        _panelTop.add(_cbxTimKiem);
        _panelTop.add(_txtfTimKiem);
        _panelTop.add(_btnTimKiem);

        contentpane.add(_panelTop);

        //_panelMidLeft
        _panelMidLeft = new JPanel();
        _panelMidLeft.setPreferredSize(new Dimension(600, 170));
        _panelMidLeft.setBorder(_border);
        SpringLayout sl_panelMidLeft = new SpringLayout();
        _panelMidLeft.setLayout(sl_panelMidLeft);


        JLabel _lblMaNCC = new JLabel("Mã NCC");
        JLabel _lblTenNCC = new JLabel("Tên NCC");
        JLabel _lblSDT = new JLabel("Số điện thoại");
        JLabel _lblDiaChi = new JLabel("Địa chỉ");
        JLabel _lblNoPhaiTra = new JLabel("Nợ phải trả");

        _txtfMaNCC = new JTextField(25);
        _txtfMaNCC.setEditable(false);
        _txtfTenNCC = new JTextField(25);
        _txtfSDT = new JTextField(25);
        _txtfDiaChi = new JTextField(25);
        _txtfNoPhaiTra = new JTextField(25);

        //Chon NCC
        Layer.put(_lblMaNCC).in(sl_panelMidLeft).atTop(_panelMidLeft).withMargin(5).atLeft(_panelMidLeft).withMargin(70);
        Layer.put(_lblTenNCC).in(sl_panelMidLeft).bottomOf(_txtfMaNCC).withMargin(10).atLeft(_panelMidLeft).withMargin(70);
        Layer.put(_lblSDT).in(sl_panelMidLeft).bottomOf(_txtfTenNCC).withMargin(10).atLeft(_panelMidLeft).withMargin(70);
        Layer.put(_lblDiaChi).in(sl_panelMidLeft).bottomOf(_txtfSDT).withMargin(10).atLeft(_panelMidLeft).withMargin(70);
        Layer.put(_lblNoPhaiTra).in(sl_panelMidLeft).bottomOf(_txtfDiaChi).withMargin(10).atLeft(_panelMidLeft).withMargin(50);

        Layer.put(_txtfMaNCC).in(sl_panelMidLeft).atTop(_panelMidLeft).withMargin(5).atLeft(_panelMidLeft).withMargin(180);
        Layer.put(_txtfTenNCC).in(sl_panelMidLeft).atLeft(_panelMidLeft).withMargin(180).bottomOf(_txtfMaNCC).withMargin(10);
        Layer.put(_txtfSDT).in(sl_panelMidLeft).atLeft(_panelMidLeft).withMargin(180).bottomOf(_txtfTenNCC).withMargin(10);
        Layer.put(_txtfDiaChi).in(sl_panelMidLeft).atLeft(_panelMidLeft).withMargin(180).bottomOf(_txtfSDT).withMargin(10);
        Layer.put(_txtfNoPhaiTra).in(sl_panelMidLeft).atLeft(_panelMidLeft).withMargin(180).bottomOf(_txtfDiaChi).withMargin(10);
        
        _panelMidLeft.add(_lblMaNCC);
        _panelMidLeft.add(_lblTenNCC);
        _panelMidLeft.add(_lblSDT);
        _panelMidLeft.add(_lblDiaChi);
        _panelMidLeft.add(_lblNoPhaiTra);
        _panelMidLeft.add(_txtfMaNCC);
        _panelMidLeft.add(_txtfTenNCC);
        _panelMidLeft.add(_txtfSDT);
        _panelMidLeft.add(_txtfDiaChi);
        _panelMidLeft.add(_txtfNoPhaiTra);
        contentpane.add(_panelMidLeft);

        //_panelMidRight
        _panelMidRight = new JPanel();
        _panelMidRight.setBorder(_border);
        SpringLayout sl_panelMidRight = new SpringLayout();
        _panelMidRight.setLayout(sl_panelMidRight);

        JLabel _lblMoTa = new JLabel("Mô tả");
        _txtaMoTa = new JTextArea(8, 40);
        _txtaMoTa.setLineWrap(true);
        _txtaMoTa.setWrapStyleWord(true);
        JScrollPane _scrollMoTa = new JScrollPane(_txtaMoTa, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        Layer.put(_scrollMoTa).in(sl_panelMidRight).atTopRight(_panelMidRight).withMargin(10).atBottom(_panelMidRight).withMargin(10);

        Layer.put(_lblMoTa).in(sl_panelMidRight).rightOf(_scrollMoTa).withMargin(10)
                .atTop(_panelMidRight).withMargin(10);
        _panelMidRight.add(_scrollMoTa);
        _panelMidRight.add(_lblMoTa);
        contentpane.add(_panelMidRight);

        //_panelBot
        _panelBot = new JPanel();
        _panelBot.setPreferredSize(new Dimension(750, 100));
        final TitledBorder _titleBotLeft = BorderFactory.createTitledBorder(_border, "Danh sách nhà cung cấp");
        _panelBot.setBorder(_titleBotLeft);

        final String[] _columnsNCC =
        {
            "Mã chuồng", "Tình trạng", "Số lượng tối đa", "Vị trí", "Mô tả"
        };
        _tbNCC = new JTable(new Object[0][0], _columnsNCC);
        _tbNCC.setFillsViewportHeight(true);
        JScrollPane _scrolltbNCC = new JScrollPane(_tbNCC, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        BorderLayout bl_BotLeft = new BorderLayout();
        _panelBot.setLayout(bl_BotLeft);
        _panelBot.add(_scrolltbNCC);

        contentpane.add(_panelBot);

        //Button
        SpringLayout sl_contentpane = new SpringLayout();
        contentpane.setLayout(sl_contentpane);
        _btnThem = Genner.createButton("Thêm", Genner.MEDIUM_SIZE);
        _btnThem.setActionCommand(ADD_COMMAND);

        _btnXoa = Genner.createButton("Xóa", Genner.MEDIUM_SIZE);
        _btnXoa.setActionCommand(REMOVE_COMMAND);

        _btnSua = Genner.createButton("Sửa", Genner.MEDIUM_SIZE);
        _btnSua.setActionCommand(UPDATE_COMMAND);
        
        _btnChonNCC = Genner.createButton("Chọn NCC", Genner.MEDIUM_SIZE);
        _btnChonNCC.setActionCommand(SELECT_PROVIDER_COMMAND);

        contentpane.add(_btnThem);
        contentpane.add(_btnXoa);
        contentpane.add(_btnSua);
        contentpane.add(_btnChonNCC);

        //set layer
        Layer.put(_panelTop).in(sl_contentpane).atTop(contentpane).withMargin(30).atLeft(contentpane).withMargin(200).atRight(contentpane).withMargin(200);
        Layer.put(_panelMidLeft).in(sl_contentpane).bottomOf(_panelTop).withMargin(20).atLeft(contentpane).withMargin(50);
        sl_contentpane.putConstraint(SpringLayout.SOUTH, _panelMidRight, 0, SpringLayout.SOUTH, _panelMidLeft);
        Layer.put(_panelMidRight).in(sl_contentpane).bottomOf(_panelTop).withMargin(20).atRight(contentpane).withMargin(50).leftOf(_panelMidLeft).withMargin(50);
        Layer.put(_panelBot).in(sl_contentpane).bottomOf(_panelMidLeft).withMargin(20).atLeft(contentpane).withMargin(50).atRight(contentpane).withMargin(50).atBottom(contentpane).withMargin(60);
        Layer.put(_btnThem).in(sl_contentpane).atBottom(contentpane).withMargin(5)
                .atLeft(contentpane).withMargin(250).bottomOf(_panelBot).withMargin(5);
        Layer.put(_btnXoa).in(sl_contentpane).atBottom(contentpane).withMargin(5)
                .leftOf(_btnThem).withMargin(150).bottomOf(_panelBot).withMargin(5);
        Layer.put(_btnSua).in(sl_contentpane).atBottom(contentpane).withMargin(5)
                .leftOf(_btnXoa).withMargin(150).bottomOf(_panelBot).withMargin(5);
        Layer.put(_btnChonNCC).in(sl_contentpane).atBottom(contentpane).withMargin(5)
                .leftOf(_btnSua).withMargin(150).bottomOf(_panelBot).withMargin(5);

        //set form
        setPreferredSize(new Dimension(1366, 730));
        setResizable(false);
        setTitle("Quản lý nhà cung cấp");
        setContentPane(new JScrollPane(contentpane));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    void notice(short code)
    {
        
    }

    public void setController(ProviderManagerController pmc)
    {
        _btnTimKiem.addActionListener(pmc); 
        _btnThem.addActionListener(pmc); 
        _btnXoa.addActionListener(pmc); 
        _btnSua.addActionListener(pmc); 
        _btnChonNCC.addActionListener(pmc); 
        
        _tbNCC.addMouseListener(pmc);
    }

}
