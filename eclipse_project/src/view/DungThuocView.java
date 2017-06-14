package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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

import com.toedter.calendar.JDateChooser;

import common.Genner;
import common.Layer;
import controller.ControllerBase;
import controller.DungThuocController;

public class DungThuocView extends ViewBase
{

	private static final long		serialVersionUID	= -2388455377284757616L;

	private JPanel					panelMain;

	public JButton					btnTimKiem, btnThem, btnXoa, btnChonMaThuoc;
	public JTextField				txtTimKiem, txtLieu, txtDonVi, txtMaThuoc;
	public JComboBox<Object>		cbxTimKiem;
	public JCheckBox				chkTiem;
	public JDateChooser				dateNgayDung;
	public JTable					tbDungThuoc;
	public static DefaultTableModel	dtm;
	public static String			mabenhan;

	public static String			SEARCH_COMMAND		= "search";
	public static String			INSERT_COMMAND		= "insert";
	public static String			DELETE_COMMAND		= "delete";
	public static String			CHOOSE_COMMAND		= "choose";

	public DungThuocView(String _mabenhan) {
		mabenhan = _mabenhan;
		panelMain = new JPanel();
		SpringLayout sl_panelMain = new SpringLayout();
		panelMain.setLayout(sl_panelMain);
		JPanel panelTop, panelBot, panelMidLeft, panelMidRight;

		// set panelTop
		final Border border = BorderFactory.createLineBorder(Color.gray);
		final TitledBorder titleTimKiem = BorderFactory.createTitledBorder(border, "Tìm kiếm lịch sử dùng thuốc");

		panelTop = new JPanel();
		panelTop.setBorder(titleTimKiem);
		panelTop.setPreferredSize(new Dimension(400, 70));
		Layer.put(panelTop).in(sl_panelMain).atTop(panelMain).withMargin(5).atLeft(panelMain).withMargin(10)
				.atRight(panelMain).withMargin(10);

		cbxTimKiem = new JComboBox<>();
		cbxTimKiem.addItem("Mã thuốc");
		cbxTimKiem.addItem("Hình thức sử dụng");

		txtTimKiem = new JTextField(20);
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setActionCommand(SEARCH_COMMAND);
		SpringLayout sl_panelTop = new SpringLayout();
		panelTop.setLayout(sl_panelTop);

		sl_panelTop.putConstraint(SpringLayout.HORIZONTAL_CENTER, txtTimKiem, 0, SpringLayout.HORIZONTAL_CENTER,
				panelTop);
		sl_panelTop.putConstraint(SpringLayout.VERTICAL_CENTER, txtTimKiem, 0, SpringLayout.VERTICAL_CENTER, panelTop);
		Layer.put(cbxTimKiem).in(sl_panelTop).rightOf(txtTimKiem).withMargin(10);
		sl_panelTop.putConstraint(SpringLayout.VERTICAL_CENTER, cbxTimKiem, 0, SpringLayout.VERTICAL_CENTER, panelTop);
		Layer.put(btnTimKiem).in(sl_panelTop).leftOf(txtTimKiem).withMargin(10);
		sl_panelTop.putConstraint(SpringLayout.VERTICAL_CENTER, btnTimKiem, 0, SpringLayout.VERTICAL_CENTER, panelTop);

		panelTop.add(cbxTimKiem);
		panelTop.add(txtTimKiem);
		panelTop.add(btnTimKiem);
		panelMain.add(panelTop);

		// panelMidLeft

		panelMidLeft = new JPanel();
		panelMidLeft.setPreferredSize(new Dimension(470, 120));
		SpringLayout sl_panelMidLeft = new SpringLayout();
		panelMidLeft.setLayout(sl_panelMidLeft);
		panelMidLeft.setBorder(border);

		Layer.put(panelMidLeft).in(sl_panelMain).atLeft(panelMain).withMargin(10).bottomOf(panelTop).withMargin(10);

		JLabel lblMaThuoc = new JLabel("Mã thuốc");
		Layer.put(lblMaThuoc).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(30).atTop(panelMidLeft)
				.withMargin(15);

		JLabel lblNgayDung = new JLabel("Ngày dùng");
		Layer.put(lblNgayDung).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(30).bottomOf(lblMaThuoc)
				.withMargin(15);

		JLabel lblLieu = new JLabel("Liều");
		Layer.put(lblLieu).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(30).bottomOf(lblNgayDung).withMargin(15);

		txtMaThuoc = new JTextField(15);
		Layer.put(txtMaThuoc).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(120).atTop(lblMaThuoc).withMargin(0);
		txtMaThuoc.setEditable(false);

		dateNgayDung = new JDateChooser();
		Layer.put(dateNgayDung).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(120).atTop(lblNgayDung)
				.withMargin(0).atRight(panelMidLeft).withMargin(70);

		txtLieu = new JTextField(25);
		Layer.put(txtLieu).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(120).atTop(lblLieu).withMargin(0);

		btnChonMaThuoc = Genner.createButton("Chọn thuốc", Genner.MEDIUM_SIZE);
		Layer.put(btnChonMaThuoc).in(sl_panelMidLeft).leftOf(txtMaThuoc).withMargin(15).atTop(txtMaThuoc).withMargin(0)
				.atBottom(txtMaThuoc).withMargin(0);
		btnChonMaThuoc.setActionCommand(CHOOSE_COMMAND);

		panelMidLeft.add(lblMaThuoc);
		panelMidLeft.add(lblNgayDung);
		panelMidLeft.add(lblLieu);
		panelMidLeft.add(txtMaThuoc);
		panelMidLeft.add(dateNgayDung);
		panelMidLeft.add(txtLieu);
		panelMidLeft.add(btnChonMaThuoc);

		panelMain.add(panelMidLeft);

		// set panelRight
		panelMidRight = new JPanel();
		panelMidRight.setPreferredSize(new Dimension(470, 120));
		SpringLayout sl_panelMidRight = new SpringLayout();
		panelMidRight.setLayout(sl_panelMidRight);
		panelMidRight.setBorder(border);

		Layer.put(panelMidRight).in(sl_panelMain).leftOf(panelMidLeft).withMargin(30).bottomOf(panelTop).withMargin(10);

		JLabel lblDonVi = new JLabel("Đơn vị");
		Layer.put(lblDonVi).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(30).atTop(panelMidRight)
				.withMargin(15);

		JLabel lblhtsd = new JLabel("Hình thức sử dụng");
		Layer.put(lblhtsd).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(30).bottomOf(lblDonVi).withMargin(15);

		txtDonVi = new JTextField(25);
		Layer.put(txtDonVi).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(160).atTop(lblDonVi).withMargin(0);

		chkTiem = new JCheckBox("Tiêm");
		Layer.put(chkTiem).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(160).atTop(lblhtsd).withMargin(0);

		panelMidRight.add(lblDonVi);
		panelMidRight.add(lblhtsd);
		panelMidRight.add(txtDonVi);
		panelMidRight.add(chkTiem);
		panelMain.add(panelMidRight);

		// panle Bot
		panelBot = new JPanel();
		SpringLayout sl_panelBot = new SpringLayout();
		panelBot.setLayout(sl_panelBot);
		Layer.put(panelBot).in(sl_panelMain).atLeft(panelMain).withMargin(5).bottomOf(panelMidLeft).withMargin(5)
				.atBottom(panelMain).withMargin(5).atRight(panelMain).withMargin(200);
		BorderLayout bl_panelBot = new BorderLayout();
		panelBot.setLayout(bl_panelBot);
		TitledBorder titleLichSu = BorderFactory.createTitledBorder(border, "Lịch sử dùng thuốc");
		panelBot.setBorder(titleLichSu);

		final String[] columnDungThuoc =
		{ "Mã thuốc", "Ngày dùng", "Liều", "Đơn vị", "Hình thức sử dụng" };
		tbDungThuoc = new JTable(new Object[0][0], columnDungThuoc);
		dtm = new DefaultTableModel(columnDungThuoc, 0);
		tbDungThuoc.setFillsViewportHeight(true);
		JScrollPane scrolltbBenhAn = new JScrollPane(tbDungThuoc, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelBot.add(scrolltbBenhAn);
		panelMain.add(panelBot);

		// set controller main

		btnThem = Genner.createButton("Thêm", Genner.MEDIUM_SIZE);
		Layer.put(btnThem).in(sl_panelMain).leftOf(panelBot).withMargin(60).bottomOf(panelMidRight).withMargin(80);
		btnThem.setActionCommand(INSERT_COMMAND);

		btnXoa = Genner.createButton("Xóa", Genner.MEDIUM_SIZE);
		Layer.put(btnXoa).in(sl_panelMain).leftOf(panelBot).withMargin(60).bottomOf(btnThem).withMargin(30);
		btnXoa.setActionCommand(DELETE_COMMAND);

		panelMain.add(btnThem);
		panelMain.add(btnXoa);

		// set mainform
		setPreferredSize(new Dimension(1000, 720));
		setResizable(false);
		setTitle("Dùng thuốc");
		setContentPane(new JScrollPane(panelMain));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void setController(ControllerBase c)
	{
	    DungThuocController dtc = (DungThuocController) c;
		btnChonMaThuoc.addActionListener(dtc);
		btnThem.addActionListener(dtc);
		btnTimKiem.addActionListener(dtc);
		btnXoa.addActionListener(dtc);
		chkTiem.addActionListener(dtc);
		tbDungThuoc.setModel(dtm);
		tbDungThuoc.addMouseListener(dtc);
	}

}
