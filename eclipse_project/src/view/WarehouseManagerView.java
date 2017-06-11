/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import common.Constants;
import common.Genner;
import common.Layer;
import controller.WarehouseManagerController;

/**
 *
 * @author Dang Nhat Hai Long
 */
public class WarehouseManagerView extends ViewBase
{
	/**
	 * 
	 */
	private static final long	serialVersionUID			= -6402052758461371632L;
	public static final String	CANCEL_COMMAND				= "cancel";
	public static final String	ADD_COMMAND					= "add";
	public static final String	REMOVE_COMMAND				= "remove";
	public static final String	FOOD_SELECTED_COMMAND		= "food_switched";
	public static final String	DRUG_SELECTED_COMMAND		= "drug_switched";

	public static final String	UPDATE_COMMAND				= "update";
	public static final String	EXPORT_COMMAND				= "export";
	public static final String	TRANSFER_TO_RIGHT_COMMAND	= "transfer to right";
	public static final String	TRANSFER_TO_LEFT_COMMAND	= "transfer to left";
	public static final String	SEARCH_COMMAND				= "search";
	public static final String	SELECT_PROVIDER_COMMAND		= "select provider";
	public static final String	SELECT_FOOD_COMMAND			= "select food";
	public static final String	SELECT_NSX_COMMAND			= "select nsx";
	public static final String	SELECT_NHH_COMMAND			= "select nhh";

	public JPanel				contentpane, _panelTopLeft, _panelTopRight, _panelMidLeft, _panelMidCenter,
			_panelMidRight, _panelBotLeft, _panelBotRight;
	final private Border		_border						= BorderFactory.createLineBorder(Color.gray);
	final private TitledBorder	_titleQuanLy, _titleTimKiem, _titleBotLeft, _titleBotRight;
	public JRadioButton			_rdbtThuoc, _rdbtThucAn;
	public JComboBox<String>	_cbxTimKiem;
	public JTextField			_txtfMaNCC, _txtfMaLoaiThucAn;
	public JTextField			_txtfTimKiem, _txtfMa, _txtfTen, _txtfDonVi, _txtfConLai;
	public JButton				_btnTimKiem, _btnChonNCC, _btnChonThucAn, _btnThem, _btnXoa, _btnCapNhat, _btnXuatKho,
			_btnChuyenSangPhai, _btnChuyenSangTrai, _btnTrangChu;
	public JLabel				_lblMaNCC, _lblMa, _lblTen, _lblDonVi, _lblConLai, _lblNSX, _lblNHH, _lblMaLoaiThucAn,
			_lblThanhPhan, _lblChiDinh;
	public JTextArea			_txtaThanhPhan, _txtaChiDinh;
	public JScrollPane			_scrollThanhPhan, _scrollChiDinh, _scrolltbKho, _scrolltbXuatKho;
	public JTable				_tbKho, _tbXuatKho;
	public DefaultTableModel	dtm, dtmXK;
	public JDateChooser			_dateNSX, _dateNHH;

	public WarehouseManagerView() {
		contentpane = new JPanel();
		SpringLayout sl_contentpane = new SpringLayout();
		contentpane.setLayout(sl_contentpane);

		// _panelTopLeft
		_titleQuanLy = BorderFactory.createTitledBorder(_border, "Quan ly");
		_panelTopLeft = new JPanel();
		_panelTopLeft.setBorder(_titleQuanLy);
		_panelTopLeft.setLayout(new BoxLayout(_panelTopLeft, BoxLayout.X_AXIS));
		_panelTopLeft.setPreferredSize(new Dimension(200, 80));

		_rdbtThuoc = new JRadioButton("Thuoc", true);
		_rdbtThuoc.setActionCommand(DRUG_SELECTED_COMMAND);
		_rdbtThucAn = new JRadioButton("Thuc an", false);
		_rdbtThucAn.setActionCommand(FOOD_SELECTED_COMMAND);

		ButtonGroup _btngroup = new ButtonGroup();
		_btngroup.add(_rdbtThuoc);
		_btngroup.add(_rdbtThucAn);

		_panelTopLeft.add(_rdbtThuoc);
		_panelTopLeft.add(_rdbtThucAn);

		contentpane.add(_panelTopLeft);

		// _panelTopRight
		_titleTimKiem = BorderFactory.createTitledBorder(_border, "Tim kiem");
		_titleTimKiem.setTitleJustification(TitledBorder.CENTER);
		_panelTopRight = new JPanel();
		_panelTopRight.setPreferredSize(new Dimension(200, 80));
		_panelTopRight.setBorder(_titleTimKiem);
		SpringLayout sl_panelTopRight = new SpringLayout();
		_panelTopRight.setLayout(sl_panelTopRight);

		_cbxTimKiem = new JComboBox<String>();
		_cbxTimKiem.addItem("Ten");
		_txtfTimKiem = new JTextField(20);

		_btnTimKiem = Genner.createButton("Tim kiem", Genner.MEDIUM_SIZE);
		_btnTimKiem.setActionCommand(SEARCH_COMMAND);

		sl_panelTopRight.putConstraint(SpringLayout.HORIZONTAL_CENTER, _txtfTimKiem, 0, SpringLayout.HORIZONTAL_CENTER,
				_panelTopRight);
		sl_panelTopRight.putConstraint(SpringLayout.VERTICAL_CENTER, _txtfTimKiem, 0, SpringLayout.VERTICAL_CENTER,
				_panelTopRight);

		Layer.put(_btnTimKiem).in(sl_panelTopRight).leftOf(_txtfTimKiem).withMargin(10);
		sl_panelTopRight.putConstraint(SpringLayout.VERTICAL_CENTER, _btnTimKiem, 0, SpringLayout.VERTICAL_CENTER,
				_panelTopRight);

		Layer.put(_cbxTimKiem).in(sl_panelTopRight).rightOf(_txtfTimKiem).withMargin(10);
		sl_panelTopRight.putConstraint(SpringLayout.VERTICAL_CENTER, _cbxTimKiem, 0, SpringLayout.VERTICAL_CENTER,
				_panelTopRight);

		_panelTopRight.add(_cbxTimKiem);
		_panelTopRight.add(_txtfTimKiem);
		_panelTopRight.add(_btnTimKiem);

		contentpane.add(_panelTopRight);

		// _panelMidLeft
		_panelMidLeft = new JPanel();
		_panelMidLeft.setPreferredSize(new Dimension(400, 170));
		_panelMidLeft.setBorder(_border);
		SpringLayout sl_panelMidLeft = new SpringLayout();
		_panelMidLeft.setLayout(sl_panelMidLeft);

		_btnChonNCC = Genner.createButton("Chon", Genner.MEDIUM_SIZE);
		_btnChonNCC.setActionCommand(SELECT_PROVIDER_COMMAND);

		_lblMaNCC = new JLabel("Ma NCC");
		_lblMa = new JLabel("Ma");
		_lblTen = new JLabel("Ten");
		_lblDonVi = new JLabel("Don vi");

		_txtfMaNCC = new JTextField(18);
		_txtfMaNCC.setEditable(false);
		_txtfMa = new JTextField(25);
		_txtfMa.setEditable(false);
		_txtfMa.setEditable(false);
		_txtfTen = new JTextField(25);
		_txtfDonVi = new JTextField(25);

		// Chon NCC
		Layer.put(_lblMaNCC).in(sl_panelMidLeft).atTop(_panelMidLeft).withMargin(10).atLeft(_panelMidLeft)
				.withMargin(10);
		Layer.put(_lblMa).in(sl_panelMidLeft).bottomOf(_txtfMaNCC).withMargin(20).atLeft(_panelMidLeft).withMargin(10);
		Layer.put(_lblTen).in(sl_panelMidLeft).bottomOf(_txtfMa).withMargin(20).atLeft(_panelMidLeft).withMargin(10);
		Layer.put(_lblDonVi).in(sl_panelMidLeft).bottomOf(_txtfTen).withMargin(20).atLeft(_panelMidLeft).withMargin(10);

		Layer.put(_txtfMaNCC).in(sl_panelMidLeft).atTop(_panelMidLeft).withMargin(10).atLeft(_panelMidLeft)
				.withMargin(70);
		Layer.put(_txtfMa).in(sl_panelMidLeft).atLeft(_panelMidLeft).withMargin(70).bottomOf(_txtfMaNCC).withMargin(20);
		Layer.put(_txtfTen).in(sl_panelMidLeft).atLeft(_panelMidLeft).withMargin(70).bottomOf(_txtfMa).withMargin(20);
		Layer.put(_txtfDonVi).in(sl_panelMidLeft).atLeft(_panelMidLeft).withMargin(70).bottomOf(_txtfTen)
				.withMargin(20);

		Layer.put(_btnChonNCC).in(sl_panelMidLeft).atTop(_panelMidLeft).withMargin(10).leftOf(_txtfMaNCC)
				.withMargin(10);
		sl_panelMidLeft.putConstraint(SpringLayout.EAST, _btnChonNCC, 0, SpringLayout.EAST, _txtfMa);
		sl_panelMidLeft.putConstraint(SpringLayout.SOUTH, _btnChonNCC, 0, SpringLayout.SOUTH, _txtfMaNCC);

		_panelMidLeft.add(_btnChonNCC);
		_panelMidLeft.add(_lblMaNCC);
		_panelMidLeft.add(_lblMa);
		_panelMidLeft.add(_lblTen);
		_panelMidLeft.add(_lblDonVi);
		_panelMidLeft.add(_txtfMaNCC);
		_panelMidLeft.add(_txtfMa);
		_panelMidLeft.add(_txtfTen);
		_panelMidLeft.add(_txtfDonVi);

		contentpane.add(_panelMidLeft);

		// _panelMidCenter
		_panelMidCenter = new JPanel();
		_panelMidCenter.setPreferredSize(new Dimension(450, 170));
		_panelMidCenter.setBorder(_border);
		SpringLayout sl_panelMidCenter = new SpringLayout();
		_panelMidCenter.setLayout(sl_panelMidCenter);
		_lblConLai = new JLabel("Con lai");
		_lblNSX = new JLabel("Ngay san xuat");
		_lblNHH = new JLabel("Ngay het han");
		_lblMaLoaiThucAn = new JLabel("Ma loai thuc an");

		_txtfConLai = new JTextField(25);
		_dateNSX = new JDateChooser();
		_dateNHH = new JDateChooser();

		_txtfMaLoaiThucAn = new JTextField(18);
		_txtfMaLoaiThucAn.setEditable(false);

		_btnChonThucAn = Genner.createButton("Chon", Genner.MEDIUM_SIZE);
		_btnChonThucAn.setActionCommand(SELECT_FOOD_COMMAND);

		Layer.put(_txtfConLai).in(sl_panelMidCenter).atTop(_panelMidCenter).withMargin(10).atRight(_panelMidCenter)
				.withMargin(30);
		Layer.put(_lblConLai).in(sl_panelMidCenter).atTop(_panelMidCenter).withMargin(10).rightOf(_txtfConLai)
				.withMargin(10);
		Layer.put(_dateNSX).in(sl_panelMidCenter).atRight(_panelMidCenter).withMargin(30).bottomOf(_txtfConLai)
				.withMargin(20);
		sl_panelMidCenter.putConstraint(SpringLayout.WEST, _dateNSX, 0, SpringLayout.WEST, _txtfConLai);
		Layer.put(_lblNSX).in(sl_panelMidCenter).bottomOf(_txtfConLai).withMargin(20).rightOf(_dateNSX).withMargin(20);
		Layer.put(_dateNHH).in(sl_panelMidCenter).atRight(_panelMidCenter).withMargin(30).bottomOf(_dateNSX)
				.withMargin(20);
		Layer.put(_lblNHH).in(sl_panelMidCenter).bottomOf(_dateNSX).withMargin(20).rightOf(_dateNHH).withMargin(10);
		sl_panelMidCenter.putConstraint(SpringLayout.WEST, _dateNHH, 0, SpringLayout.WEST, _dateNSX);
		Layer.put(_btnChonThucAn).in(sl_panelMidCenter).atRight(_panelMidCenter).withMargin(30).bottomOf(_dateNHH)
				.withMargin(20);
		Layer.put(_txtfMaLoaiThucAn).in(sl_panelMidCenter).bottomOf(_dateNHH).withMargin(20).rightOf(_btnChonThucAn)
				.withMargin(10);
		Layer.put(_lblMaLoaiThucAn).in(sl_panelMidCenter).bottomOf(_dateNHH).withMargin(20).rightOf(_txtfMaLoaiThucAn)
				.withMargin(10);
		sl_panelMidCenter.putConstraint(SpringLayout.WEST, _txtfMaLoaiThucAn, 0, SpringLayout.WEST, _dateNHH);
		sl_panelMidCenter.putConstraint(SpringLayout.SOUTH, _btnChonThucAn, 0, SpringLayout.SOUTH, _txtfMaLoaiThucAn);

		_panelMidCenter.add(_lblConLai);
		_panelMidCenter.add(_lblNSX);
		_panelMidCenter.add(_lblNHH);
		_panelMidCenter.add(_lblMaLoaiThucAn);
		_panelMidCenter.add(_txtfConLai);
		_panelMidCenter.add(_dateNSX);
		_panelMidCenter.add(_dateNHH);
		_panelMidCenter.add(_txtfMaLoaiThucAn);
		_panelMidCenter.add(_btnChonThucAn);
		_txtfMaLoaiThucAn.setEditable(false);
		_btnChonThucAn.setEnabled(false);

		contentpane.add(_panelMidCenter);

		// _panelMidRight
		_panelMidRight = new JPanel();
		_panelMidRight.setBorder(_border);
		SpringLayout sl_panelMidRight = new SpringLayout();
		_panelMidRight.setLayout(sl_panelMidRight);

		_lblThanhPhan = new JLabel("Thanh phan");
		_lblChiDinh = new JLabel("Chi dinh");
		_txtaThanhPhan = new JTextArea(4, 36);
		_txtaThanhPhan.setLineWrap(true);
		_txtaThanhPhan.setWrapStyleWord(true);
		_scrollThanhPhan = new JScrollPane(_txtaThanhPhan, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		_txtaChiDinh = new JTextArea(4, 36);
		_txtaChiDinh.setLineWrap(true);
		_txtaChiDinh.setWrapStyleWord(true);
		_scrollChiDinh = new JScrollPane(_txtaChiDinh, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		Layer.put(_scrollThanhPhan).in(sl_panelMidRight).atTopRight(_panelMidRight).withMargin(5);
		Layer.put(_scrollChiDinh).in(sl_panelMidRight).atTopRight(_panelMidRight).withMargin(5)
				.bottomOf(_scrollThanhPhan).withMargin(5);
		Layer.put(_lblThanhPhan).in(sl_panelMidRight).rightOf(_scrollThanhPhan).withMargin(5).atTop(_panelMidRight)
				.withMargin(5);
		Layer.put(_lblChiDinh).in(sl_panelMidRight).rightOf(_scrollChiDinh).withMargin(5).bottomOf(_scrollThanhPhan)
				.withMargin(5);

		_panelMidRight.add(_scrollChiDinh);
		_panelMidRight.add(_scrollThanhPhan);
		_panelMidRight.add(_lblThanhPhan);
		_panelMidRight.add(_lblChiDinh);

		contentpane.add(_panelMidRight);

		// _panelBotLeft
		_panelBotLeft = new JPanel();
		_panelBotLeft.setPreferredSize(new Dimension(750, 100));
		_titleBotLeft = BorderFactory.createTitledBorder(_border);
		_panelBotLeft.setBorder(_titleBotLeft);

		_tbKho = new JTable();
		_tbKho.setFillsViewportHeight(true);
		_scrolltbKho = new JScrollPane(_tbKho, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		BorderLayout bl_BotLeft = new BorderLayout();
		_panelBotLeft.setLayout(bl_BotLeft);
		_panelBotLeft.add(_scrolltbKho);

		contentpane.add(_panelBotLeft);

		// _panelBotRight
		_panelBotRight = new JPanel();
		_titleBotRight = BorderFactory.createTitledBorder(_border);
		_panelBotRight.setBorder(_titleBotRight);

		_tbXuatKho = new JTable();
		final String[] _columnsTBXuatKho =
		{ "Loai", "Ma", "Ten", "So luong", "Don vi", "Nha cung cap", "Ngay san xuat", "Ngay het han" };
		dtmXK = new DefaultTableModel(_columnsTBXuatKho, 0);
		_tbXuatKho.setModel(dtmXK);
		_tbXuatKho.setFillsViewportHeight(true);
		_scrolltbXuatKho = new JScrollPane(_tbXuatKho, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		BorderLayout bl_BotRight = new BorderLayout();
		_panelBotRight.setLayout(bl_BotRight);
		_panelBotRight.add(_scrolltbXuatKho);
		contentpane.add(_panelBotRight);

		// _button on bottom

		_btnThem = Genner.createButton("Them", Genner.MEDIUM_SIZE);
		_btnThem.setActionCommand(ADD_COMMAND);
		_btnXoa = Genner.createButton("Xoa", Genner.MEDIUM_SIZE);
		_btnXoa.setActionCommand(REMOVE_COMMAND);
		_btnCapNhat = Genner.createButton("Cap nhat", Genner.MEDIUM_SIZE);
		_btnCapNhat.setActionCommand(UPDATE_COMMAND);
		_btnXuatKho = Genner.createButton("Xuat kho", Genner.MEDIUM_SIZE);
		_btnXuatKho.setActionCommand(EXPORT_COMMAND);
		_btnChuyenSangPhai = Genner.createButton(">>", Genner.SMALL_SIZE);
		_btnChuyenSangPhai.setActionCommand(TRANSFER_TO_RIGHT_COMMAND);
		_btnChuyenSangTrai = Genner.createButton("<<", Genner.SMALL_SIZE);
		_btnChuyenSangTrai.setActionCommand(TRANSFER_TO_LEFT_COMMAND);
		_btnTrangChu = Genner.createButton("Trang Chu", Genner.BIG_SIZE);
		_btnTrangChu.setActionCommand(Constants.AC_HOME);

		contentpane.add(_btnThem);
		contentpane.add(_btnXoa);
		contentpane.add(_btnCapNhat);
		contentpane.add(_btnXuatKho);
		contentpane.add(_btnChuyenSangPhai);
		contentpane.add(_btnChuyenSangTrai);
		contentpane.add(_btnTrangChu);

		// Set Layer
		Layer.put(_panelTopLeft).atTopLeft(contentpane).in(sl_contentpane).withMargin(5);
		Layer.put(_panelTopRight).atTopRight(contentpane).in(sl_contentpane).withMargin(5).leftOf(_panelTopLeft)
				.withMargin(5);
		Layer.put(_panelMidLeft).bottomOf(_panelTopLeft).in(sl_contentpane).withMargin(5).atLeft(contentpane)
				.withMargin(5);
		Layer.put(_panelMidCenter).in(sl_contentpane).bottomOf(_panelTopRight).withMargin(5).leftOf(_panelMidLeft)
				.withMargin(5);
		Layer.put(_panelMidRight).bottomOf(_panelTopRight).in(sl_contentpane).withMargin(5).atRight(contentpane)
				.withMargin(5).leftOf(_panelMidCenter).withMargin(5).topOf(_panelBotLeft).withMargin(5);
		Layer.put(_panelBotLeft).atBottom(contentpane).in(sl_contentpane).withMargin(50).bottomOf(_panelMidLeft)
				.withMargin(5).atLeft(contentpane).withMargin(5);
		Layer.put(_panelBotRight).atRight(contentpane).in(sl_contentpane).withMargin(5).atBottom(contentpane)
				.withMargin(50).bottomOf(_panelMidRight).withMargin(5).leftOf(_panelBotLeft).withMargin(110);
		Layer.put(_btnTrangChu).in(sl_contentpane).atBottom(contentpane).withMargin(5).atLeft(contentpane)
				.withMargin(90).bottomOf(_panelBotLeft).withMargin(5);
		Layer.put(_btnThem).in(sl_contentpane).atBottom(contentpane).withMargin(5).leftOf(_btnTrangChu).withMargin(50)
				.bottomOf(_panelBotLeft).withMargin(5);
		Layer.put(_btnXoa).in(sl_contentpane).atBottom(contentpane).withMargin(5).leftOf(_btnThem).withMargin(80)
				.bottomOf(_panelBotLeft).withMargin(5);
		Layer.put(_btnCapNhat).in(sl_contentpane).atBottom(contentpane).withMargin(5).leftOf(_btnXoa).withMargin(80)
				.bottomOf(_panelBotLeft).withMargin(5);

		sl_contentpane.putConstraint(SpringLayout.HORIZONTAL_CENTER, _btnXuatKho, 0, SpringLayout.HORIZONTAL_CENTER,
				_panelBotRight);
		Layer.put(_btnXuatKho).in(sl_contentpane).atBottom(contentpane).withMargin(5).bottomOf(_panelBotRight)
				.withMargin(5);
		Layer.put(_btnChuyenSangPhai).in(sl_contentpane).leftOf(_panelBotLeft).withMargin(10).rightOf(_panelBotRight)
				.withMargin(10).atBottom(contentpane).withMargin(300);
		Layer.put(_btnChuyenSangTrai).in(sl_contentpane).leftOf(_panelBotLeft).withMargin(10).rightOf(_panelBotRight)
				.withMargin(10).bottomOf(_btnChuyenSangPhai).withMargin(50);

		// Set form
		setPreferredSize(new Dimension(1366, 730));
		setResizable(false);
		setTitle("Quan ly kho");
		setContentPane(new JScrollPane(contentpane));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setController(WarehouseManagerController whc)
	{
		_btnTimKiem.addActionListener(whc);
		_btnChonNCC.addActionListener(whc);
		_btnChonThucAn.addActionListener(whc);
		_btnThem.addActionListener(whc);
		_btnXoa.addActionListener(whc);
		_btnCapNhat.addActionListener(whc);
		_btnXuatKho.addActionListener(whc);
		_btnChuyenSangPhai.addActionListener(whc);
		_btnChuyenSangTrai.addActionListener(whc);
		_rdbtThucAn.addActionListener(whc);
		_rdbtThuoc.addActionListener(whc);

		_cbxTimKiem.addActionListener(whc);
		_tbKho.addMouseListener(whc);
	}

	void notice(short code)
	{

	}

}
