package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

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

import common.Genner;
import common.Layer;
import controller.BenhController;

public class BenhView extends ViewBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8954560288310757783L;
	private JPanel panelMain;
	public JButton btnThem, btnXoa, btnChonBenh, btnCapNhat,btnTimKiem;
	public JTextField txtTimKiem,txtMaBenh,txtTenBenh,txtLoaiBenh;
	public JComboBox<Object> cbxTimKiem;
	public JTextArea txtTrieuChung;
	public JTable tbBenh;
	public static DefaultTableModel dtm;
	
	public static String SEARCH_COMMAND ="search";
    public static String INSERT_COMMAND ="insert";
    public static String UPDATE_COMMAND ="update";
    public static String DELETE_COMMAND ="delete";
    public static String SELECT_COMMAND ="select";
	
	public BenhView(){
		panelMain =new JPanel();
	    SpringLayout sl_panelMain = new SpringLayout();
	    panelMain.setLayout(sl_panelMain);
	    JPanel panelTop,panelBot,panelMidLeft,panelMidRight;
	    
	    //set panelTop
	    final Border border = BorderFactory.createLineBorder(Color.gray);
        final TitledBorder titleTimKiem=BorderFactory.createTitledBorder(border,"Tìm kiếm bệnh");
        
        panelTop=new JPanel();
        panelTop.setBorder(titleTimKiem);
        panelTop.setPreferredSize(new Dimension(400, 70));
        Layer.put(panelTop).in(sl_panelMain).atTop(panelMain).withMargin(5).atLeft(panelMain).withMargin(10).atRight(panelMain).withMargin(10);
        
        cbxTimKiem =new JComboBox<>();
        cbxTimKiem.addItem("Mã bệnh");
        cbxTimKiem.addItem("Tên bệnh");
        cbxTimKiem.addItem("Loại bệnh");
        
        txtTimKiem=new JTextField(20);
        btnTimKiem=new JButton("Tìm kiếm");
        btnTimKiem.setActionCommand(SEARCH_COMMAND);
        SpringLayout sl_panelTop =  new SpringLayout();
        panelTop.setLayout(sl_panelTop);
        
        sl_panelTop.putConstraint(SpringLayout.HORIZONTAL_CENTER, txtTimKiem, 0, SpringLayout.HORIZONTAL_CENTER, panelTop);
        sl_panelTop.putConstraint(SpringLayout.VERTICAL_CENTER, txtTimKiem, 0, SpringLayout.VERTICAL_CENTER, panelTop);
        Layer.put(cbxTimKiem).in(sl_panelTop).rightOf(txtTimKiem).withMargin(10);
        sl_panelTop.putConstraint(SpringLayout.VERTICAL_CENTER, cbxTimKiem, 0, SpringLayout.VERTICAL_CENTER, panelTop);
        Layer.put(btnTimKiem).in(sl_panelTop).leftOf(txtTimKiem).withMargin(10);
        sl_panelTop.putConstraint(SpringLayout.VERTICAL_CENTER, btnTimKiem, 0, SpringLayout.VERTICAL_CENTER, panelTop);
        
        
        panelTop.add(cbxTimKiem);
        panelTop.add(txtTimKiem);
        panelTop.add(btnTimKiem);
        panelMain.add(panelTop);
        
        //panelMidLeft
        
        panelMidLeft =new JPanel();
        panelMidLeft.setPreferredSize(new Dimension(480, 130));
        SpringLayout sl_panelMidLeft = new SpringLayout();
        panelMidLeft.setLayout(sl_panelMidLeft);
        panelMidLeft.setBorder(border);
        
        Layer.put(panelMidLeft).in(sl_panelMain).atLeft(panelMain).withMargin(10).bottomOf(panelTop).withMargin(10);

        JLabel lblMaBenh = new JLabel("Mã bệnh");
        Layer.put(lblMaBenh).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).atTop(panelMidLeft).withMargin(15);
        
        JLabel lblTenBenh=new JLabel("Tên bệnh");
        Layer.put(lblTenBenh).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).bottomOf(lblMaBenh).withMargin(20);

        JLabel lblLoaiBenh=new JLabel("Loại bệnh");
        Layer.put(lblLoaiBenh).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).bottomOf(lblTenBenh).withMargin(20);
        
        
        txtMaBenh =new JTextField(25);
        Layer.put(txtMaBenh).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(100).atTop(lblMaBenh).withMargin(0);
        txtMaBenh.setEditable(false);
        
        txtTenBenh =new JTextField(25);
        Layer.put(txtTenBenh).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(100).atTop(lblTenBenh).withMargin(0);
       
        txtLoaiBenh =new JTextField(25);
        Layer.put(txtLoaiBenh).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(100).atTop(lblLoaiBenh).withMargin(0);
       
              
        panelMidLeft.add(lblMaBenh);
        panelMidLeft.add(lblTenBenh);
        panelMidLeft.add(lblLoaiBenh);
        panelMidLeft.add(txtMaBenh);
        panelMidLeft.add(txtTenBenh);
        panelMidLeft.add(txtLoaiBenh);

        panelMain.add(panelMidLeft);
        
       //set panelRight
        panelMidRight =new JPanel();
        panelMidRight.setPreferredSize(new Dimension(480, 130));
        SpringLayout sl_panelMidRight = new SpringLayout();
        panelMidRight.setLayout(sl_panelMidRight);
        panelMidRight.setBorder(border);
        
        Layer.put(panelMidRight).in(sl_panelMain).atRight(panelMain).withMargin(10).bottomOf(panelTop).withMargin(10);
        
        JLabel lblTrieuChung =new JLabel("Mô tả triệu chứng");
        Layer.put(lblTrieuChung).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(10).atTop(panelMidRight).withMargin(15);
        
        txtTrieuChung =new JTextArea(5,30);
        txtTrieuChung.setLineWrap(true);
        txtTrieuChung.setWrapStyleWord(true);
        Layer.put(txtTrieuChung).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(120).atTop(lblTrieuChung).withMargin(0).atBottom(panelMidRight).withMargin(10).atRight(panelMidRight).withMargin(10);
        
        JScrollPane scrollNgayTao =new JScrollPane(txtTrieuChung,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        
        panelMidRight.add(lblTrieuChung);
        panelMidRight.add(txtTrieuChung);
        panelMidRight.add(scrollNgayTao);
        panelMain.add(panelMidRight);
      
        //set panelBot
        panelBot =new JPanel();
        SpringLayout sl_panelBot = new SpringLayout();
        panelBot.setLayout(sl_panelBot);
        Layer.put(panelBot).in(sl_panelMain).atLeft(panelMain).withMargin(5).bottomOf(panelMidLeft).withMargin(10).
        atBottom(panelMain).withMargin(5).atRight(panelMain).withMargin(150);
        BorderLayout bl_panelBot=new BorderLayout();
        panelBot.setLayout(bl_panelBot);
        
        final String[] columnBenh={"Mã bệnh","Tên bệnh","Mô tả triệu chứng","Loại bệnh"};
        tbBenh= new JTable(new Object[0][0], columnBenh);
        dtm = new DefaultTableModel(columnBenh,0);
        tbBenh.setFillsViewportHeight(true);
        JScrollPane scrolltbBenh = new JScrollPane(tbBenh, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        panelBot.add(scrolltbBenh);
        panelMain.add(panelBot);
        
        //set controller main
        
        btnThem = Genner.createButton("Thêm", Genner.MEDIUM_SIZE );
        Layer.put(btnThem).in(sl_panelMain).leftOf(panelBot).withMargin(20).bottomOf(panelMidRight).withMargin(80).atRight(panelMain).withMargin(20);
        btnThem.setActionCommand(INSERT_COMMAND);
        
        btnXoa = Genner.createButton("Xóa", Genner.MEDIUM_SIZE);
        Layer.put(btnXoa).in(sl_panelMain).leftOf(panelBot).withMargin(20).bottomOf(btnThem).withMargin(30).atRight(panelMain).withMargin(20);
        btnXoa.setActionCommand(DELETE_COMMAND);
        
        btnCapNhat = Genner.createButton("Cập nhật", Genner.MEDIUM_SIZE);
        Layer.put(btnCapNhat).in(sl_panelMain).leftOf(panelBot).withMargin(20).bottomOf(btnXoa).withMargin(30).atRight(panelMain).withMargin(20);
        btnCapNhat.setActionCommand(UPDATE_COMMAND);
        
        btnChonBenh = Genner.createButton("Chọn bệnh", Genner.MEDIUM_SIZE);
        Layer.put(btnChonBenh).in(sl_panelMain).leftOf(panelBot).withMargin(20).bottomOf(btnCapNhat).withMargin(30).atRight(panelMain).withMargin(20);
        btnChonBenh.setEnabled(false);
        btnChonBenh.setActionCommand(SELECT_COMMAND);
        
        panelMain.add(btnThem);
        panelMain.add(btnXoa);
        panelMain.add(btnCapNhat);
        panelMain.add(btnChonBenh);

	    
	    //set mainform
        setPreferredSize(new Dimension(1000, 720));
        setResizable(false);
        setTitle("Quản lý bệnh");
        setContentPane(new JScrollPane(panelMain));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void setController (BenhController bc){
    	btnThem.addActionListener(bc);
    	btnXoa.addActionListener(bc);
    	btnChonBenh.addActionListener(bc); 
    	btnCapNhat.addActionListener(bc);
    	btnTimKiem.addActionListener(bc);
    	tbBenh.setModel(dtm);
    	tbBenh.addMouseListener(bc);
    	
    }

}
