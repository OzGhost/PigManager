package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
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

import common.Genner;
import common.Layer;

public class XuatChuongView extends ViewBase
{

	/**
	 * 
	 */
	private static final long		serialVersionUID	= -8449744882179292438L;

	private JPanel					panelMain;

	public JButton					btnTimKiem, btnChonChuongDen, btnChonChuongNguon, btnChonNCC, btnChuyenChuong,
			btnXoa, btnBan, btnChonHeo;
	public JTextField				txtTimKiem, txtMaHeo, txtMaChuongDen, txtMaChuongNguon, txtNhaCungCap,
			txtMaDiChuyenHeo;
	public JComboBox<Object>		cbxTimKiem;
	public JRadioButton				radChuyenChuong, radXuatBan;
	public JDateChooser				dateNgayDen;
	public JTextArea				txtGhiChu;
	public JTable					tbXuatChuong;
	public static DefaultTableModel	dtm;

	public XuatChuongView() {
		panelMain = new JPanel();
		SpringLayout sl_panelMain = new SpringLayout();
		panelMain.setLayout(sl_panelMain);
		JPanel panelTopRight, panelTopLeft, panelBot, panelMidLeft, panelMidCenter, panelMidRight;
		// panelTopRight
		// create border
		final Border border = BorderFactory.createLineBorder(Color.gray);
		final TitledBorder titleTimKiem = BorderFactory.createTitledBorder(border, "Tim lich su chuyen chuong");

		panelTopRight = new JPanel();
		panelTopRight.setBorder(titleTimKiem);
		panelTopRight.setPreferredSize(new Dimension(400, 70));
		Layer.put(panelTopRight).in(sl_panelMain).atTop(panelMain).withMargin(5).atLeft(panelMain).withMargin(400)
				.atRight(panelMain).withMargin(10);

		cbxTimKiem = new JComboBox<>();
		cbxTimKiem.addItem("Ma di chuyen heo");
		cbxTimKiem.addItem("Ma heo");
		cbxTimKiem.addItem("Ngay chuyen");

		txtTimKiem = new JTextField(20);
		btnTimKiem = new JButton("Tim kiem");
		SpringLayout sl_panelTopRight = new SpringLayout();
		panelTopRight.setLayout(sl_panelTopRight);

		sl_panelTopRight.putConstraint(SpringLayout.HORIZONTAL_CENTER, txtTimKiem, 0, SpringLayout.HORIZONTAL_CENTER,
				panelTopRight);
		sl_panelTopRight.putConstraint(SpringLayout.VERTICAL_CENTER, txtTimKiem, 0, SpringLayout.VERTICAL_CENTER,
				panelTopRight);
		Layer.put(cbxTimKiem).in(sl_panelTopRight).rightOf(txtTimKiem).withMargin(10);
		sl_panelTopRight.putConstraint(SpringLayout.VERTICAL_CENTER, cbxTimKiem, 0, SpringLayout.VERTICAL_CENTER,
				panelTopRight);
		Layer.put(btnTimKiem).in(sl_panelTopRight).leftOf(txtTimKiem).withMargin(10);
		sl_panelTopRight.putConstraint(SpringLayout.VERTICAL_CENTER, btnTimKiem, 0, SpringLayout.VERTICAL_CENTER,
				panelTopRight);

		panelTopRight.add(cbxTimKiem);
		panelTopRight.add(txtTimKiem);
		panelTopRight.add(btnTimKiem);
		panelMain.add(panelTopRight);

		// panleTopLeft
		final TitledBorder titleTacVu = BorderFactory.createTitledBorder(border, "Chon tac vu");

		panelTopLeft = new JPanel();
		panelTopLeft.setBorder(titleTacVu);
		panelTopLeft.setPreferredSize(new Dimension(70, 70));
		SpringLayout sl_panelTopLeft = new SpringLayout();
		panelTopLeft.setLayout(sl_panelTopLeft);
		Layer.put(panelTopLeft).in(sl_panelMain).atTop(panelMain).withMargin(5).atLeft(panelMain).withMargin(10)
				.rightOf(panelTopRight).withMargin(20);

		radChuyenChuong = new JRadioButton("Chuyen chuong");
		radChuyenChuong.setSelected(true);
		Layer.put(radChuyenChuong).in(sl_panelTopLeft).atTop(panelTopLeft).withMargin(5).atLeft(panelTopLeft)
				.withMargin(60);

		radXuatBan = new JRadioButton("Xuat ban");
		Layer.put(radXuatBan).in(sl_panelTopLeft).atTop(panelTopLeft).withMargin(5).atRight(panelTopLeft)
				.withMargin(60);

		ButtonGroup bg = new ButtonGroup();
		bg.add(radXuatBan);
		bg.add(radChuyenChuong);

		panelTopLeft.add(radChuyenChuong);
		panelTopLeft.add(radXuatBan);
		panelMain.add(panelTopLeft);

		// panelMidLeft

		panelMidLeft = new JPanel();
		panelMidLeft.setPreferredSize(new Dimension(450, 175));
		SpringLayout sl_panelMidLeft = new SpringLayout();
		panelMidLeft.setLayout(sl_panelMidLeft);
		panelMidLeft.setBorder(border);

		Layer.put(panelMidLeft).in(sl_panelMain).atLeft(panelMain).withMargin(10).bottomOf(panelTopLeft).withMargin(10);

		JLabel lblMaDiChuyenHeo = new JLabel("Ma di chuyen heo");
		Layer.put(lblMaDiChuyenHeo).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).atTop(panelMidLeft)
				.withMargin(15);

		JLabel lblMaHeo = new JLabel("Ma heo");
		Layer.put(lblMaHeo).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).bottomOf(lblMaDiChuyenHeo)
				.withMargin(15);

		JLabel lblMaChuongDen = new JLabel("Ma chuong den");
		Layer.put(lblMaChuongDen).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).bottomOf(lblMaHeo)
				.withMargin(15);

		JLabel lblMaChuongNguon = new JLabel("Ma chuong nguon");
		Layer.put(lblMaChuongNguon).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).bottomOf(lblMaChuongDen)
				.withMargin(15);

		JLabel lblNgayDen = new JLabel("Ngay den");
		Layer.put(lblNgayDen).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).bottomOf(lblMaChuongNguon)
				.withMargin(15);

		txtMaDiChuyenHeo = new JTextField(25);
		Layer.put(txtMaDiChuyenHeo).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(130).atTop(lblMaDiChuyenHeo)
				.withMargin(0);
		txtMaDiChuyenHeo.setEditable(false);

		txtMaHeo = new JTextField(15);
		Layer.put(txtMaHeo).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(130).atTop(lblMaHeo).withMargin(0);
		txtMaHeo.setEditable(false);

		txtMaChuongDen = new JTextField(15);
		Layer.put(txtMaChuongDen).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(130).atTop(lblMaChuongDen)
				.withMargin(0);
		txtMaChuongDen.setEditable(false);

		txtMaChuongNguon = new JTextField(15);
		Layer.put(txtMaChuongNguon).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(130).atTop(lblMaChuongNguon)
				.withMargin(0);
		txtMaChuongNguon.setEditable(false);

		dateNgayDen = new JDateChooser();
		Layer.put(dateNgayDen).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(130).atTop(lblNgayDen).withMargin(0)
				.atRight(panelMidLeft).withMargin(40);

		btnChonChuongDen = Genner.createButton("Chon", Genner.MEDIUM_SIZE);
		Layer.put(btnChonChuongDen).in(sl_panelMidLeft).leftOf(txtMaChuongDen).withMargin(15).atTop(txtMaChuongDen)
				.withMargin(0).atBottom(txtMaChuongDen).withMargin(0);

		btnChonChuongNguon = Genner.createButton("Chon", Genner.MEDIUM_SIZE);
		Layer.put(btnChonChuongNguon).in(sl_panelMidLeft).leftOf(txtMaChuongNguon).withMargin(15)
				.atTop(txtMaChuongNguon).withMargin(0).atBottom(txtMaChuongNguon).withMargin(0);

		btnChonHeo = Genner.createButton("Chon", Genner.MEDIUM_SIZE);
		Layer.put(btnChonHeo).in(sl_panelMidLeft).leftOf(txtMaHeo).withMargin(15).atTop(txtMaHeo).withMargin(0)
				.atBottom(txtMaHeo).withMargin(0);

		panelMidLeft.add(lblMaDiChuyenHeo);
		panelMidLeft.add(lblMaHeo);
		panelMidLeft.add(lblMaChuongDen);
		panelMidLeft.add(lblMaChuongNguon);
		panelMidLeft.add(lblNgayDen);
		panelMidLeft.add(txtMaDiChuyenHeo);
		panelMidLeft.add(txtMaHeo);
		panelMidLeft.add(txtMaChuongDen);
		panelMidLeft.add(txtMaChuongNguon);
		panelMidLeft.add(dateNgayDen);
		panelMidLeft.add(btnChonChuongDen);
		panelMidLeft.add(btnChonChuongNguon);
		panelMidLeft.add(btnChonHeo);

		panelMain.add(panelMidLeft);

		// panelMidCenter
		panelMidCenter = new JPanel();
		panelMidCenter.setPreferredSize(new Dimension(450, 175));
		SpringLayout sl_panelMidCenter = new SpringLayout();
		panelMidCenter.setLayout(sl_panelMidCenter);
		panelMidCenter.setBorder(border);

		Layer.put(panelMidCenter).in(sl_panelMain).leftOf(panelMidLeft).withMargin(10).bottomOf(panelTopLeft)
				.withMargin(10);

		JLabel lblGhiChu = new JLabel("Ghi chu");
		Layer.put(lblGhiChu).in(sl_panelMidCenter).atLeft(panelMidCenter).withMargin(10).atTop(panelMidCenter)
				.withMargin(15);

		txtGhiChu = new JTextArea(5, 30);
		txtGhiChu.setLineWrap(true);
		txtGhiChu.setWrapStyleWord(true);
		Layer.put(txtGhiChu).in(sl_panelMidCenter).atLeft(panelMidCenter).withMargin(70).atTop(lblGhiChu).withMargin(0)
				.atBottom(panelMidCenter).withMargin(20).atRight(panelMidCenter).withMargin(30);

		JScrollPane scrollNgayTao = new JScrollPane(lblGhiChu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelMidCenter.add(lblGhiChu);
		panelMidCenter.add(txtGhiChu);
		panelMidCenter.add(scrollNgayTao);
		panelMain.add(panelMidCenter);

		// panelMidRight
		panelMidRight = new JPanel();
		panelMidRight.setPreferredSize(new Dimension(450, 175));
		SpringLayout sl_panelMidRight = new SpringLayout();
		panelMidRight.setLayout(sl_panelMidRight);
		panelMidRight.setBorder(border);

		Layer.put(panelMidRight).in(sl_panelMain).leftOf(panelMidCenter).withMargin(10).bottomOf(panelTopLeft)
				.withMargin(10).atRight(panelMain).withMargin(10);

		JLabel lblNhaCungCap = new JLabel("Nha cung cap");
		Layer.put(lblNhaCungCap).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(10).atTop(panelMidRight)
				.withMargin(15);

		txtNhaCungCap = new JTextField(15);
		Layer.put(txtNhaCungCap).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(100).atTop(lblNhaCungCap)
				.withMargin(0);
		txtNhaCungCap.setEditable(false);

		btnChonNCC = Genner.createButton("Chon", Genner.MEDIUM_SIZE);
		Layer.put(btnChonNCC).in(sl_panelMidRight).leftOf(txtNhaCungCap).withMargin(15).atTop(txtNhaCungCap)
				.withMargin(0).atBottom(txtNhaCungCap).withMargin(0);

		panelMidRight.add(lblNhaCungCap);
		panelMidRight.add(txtNhaCungCap);
		panelMidRight.add(btnChonNCC);
		panelMain.add(panelMidRight);

		// panelBot
		panelBot = new JPanel();
		SpringLayout sl_panelBot = new SpringLayout();
		panelBot.setLayout(sl_panelBot);
		Layer.put(panelBot).in(sl_panelMain).atLeft(panelMain).withMargin(10).bottomOf(panelMidLeft).withMargin(10)
				.atBottom(panelMain).withMargin(10).atRight(panelMain).withMargin(200);
		BorderLayout bl_panelBot = new BorderLayout();
		panelBot.setLayout(bl_panelBot);
		TitledBorder titleLSChuong = BorderFactory.createTitledBorder(border, "Lich su chuyen chuong");
		panelBot.setBorder(titleLSChuong);

		final String[] columnXuatChuong =
		{ "Ma di chuyen heo", "Ma heo", "Ma chuong den", "Ma chuong nguon", "Ngay den", "Ghi chu" };
		tbXuatChuong = new JTable(new Object[0][0], columnXuatChuong);
		dtm = new DefaultTableModel(columnXuatChuong, 0);
		tbXuatChuong.setFillsViewportHeight(true);
		JScrollPane scrolltbBenhAn = new JScrollPane(tbXuatChuong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelBot.add(scrolltbBenhAn);
		panelMain.add(panelBot);

		// set controller main

		btnChuyenChuong = Genner.createButton("Chuyen", Genner.MEDIUM_SIZE);
		Layer.put(btnChuyenChuong).in(sl_panelMain).leftOf(panelBot).withMargin(60).bottomOf(panelMidRight)
				.withMargin(80);

		btnXoa = Genner.createButton("Xoa", Genner.MEDIUM_SIZE);
		Layer.put(btnXoa).in(sl_panelMain).leftOf(panelBot).withMargin(60).bottomOf(btnChuyenChuong).withMargin(30);

		btnBan = Genner.createButton("Ban", Genner.MEDIUM_SIZE);
		Layer.put(btnBan).in(sl_panelMain).leftOf(panelBot).withMargin(60).bottomOf(btnXoa).withMargin(30);

		panelMain.add(btnChuyenChuong);
		panelMain.add(btnXoa);
		panelMain.add(btnBan);

		// set mainform
		setPreferredSize(new Dimension(1366, 730));
		setResizable(false);
		setTitle("Xuat chuong");
		setContentPane(new JScrollPane(panelMain));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
