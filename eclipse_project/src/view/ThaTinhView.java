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
import javax.swing.JButton;
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
import controller.ThaTinhController;

/**
 *
 * @author SiMen
 */
public class ThaTinhView extends ViewBase
{
	/**
	 * 
	 */
	private static final long		serialVersionUID		= 3211142313884359712L;

	private JPanel					contentpanel;

	public JButton					btnTimKiem, btnChonNCC, btnThem, btnXoa, btnCapNhat, btnLuu, btnChonLSTK;
	public JDateChooser				dateNgaySX, dateNgayHH;
	public JTable					tbThaTinh;
	public JComboBox<Object>		cbxTimKiem;
	public static JTextField		txtMaNCC, txtMaLS;
	public JTextField				txtMaTinh, txtTenTinh, txtNguonGoc, txtTimKiem, txtDacDiem;

	public static DefaultTableModel	dtm;

	public static String			SEARCH_COMMAND			= "search";
	public static String			INSERT_COMMAND			= "insert";
	public static String			UPDATE_COMMAND			= "update";
	public static String			DELETE_COMMAND			= "delete";
	public static String			CHOOSE_COMMAND			= "choose";
	public static String			SAVE_COMMAND			= "save";
	public static String			SELECT_HISTORY_COMMAND	= "history";
	public static String			SELECT_PROVIDER_COMMAND	= "provider";

	public ThaTinhView() {
		contentpanel = new JPanel();
		SpringLayout sl_contentpanel = new SpringLayout();
		contentpanel.setLayout(sl_contentpanel);
		JPanel panelTop, panelBot, panelMidLeft, panelMidCenter, panelMidRight;

		// panelTop
		// create border
		final Border border = BorderFactory.createLineBorder(Color.gray);
		final TitledBorder titleTimKiem = BorderFactory.createTitledBorder(border, "Tim kiem tinh");

		panelTop = new JPanel();
		panelTop.setBorder(titleTimKiem);
		panelTop.setPreferredSize(new Dimension(400, 70));
		Layer.put(panelTop).in(sl_contentpanel).atTop(contentpanel).withMargin(5).atLeft(contentpanel).withMargin(10)
				.atRight(contentpanel).withMargin(10);

		cbxTimKiem = new JComboBox<>();
		cbxTimKiem.addItem("Ma tinh");
		cbxTimKiem.addItem("Ten tinh");
		cbxTimKiem.addItem("Nguon goc");
		cbxTimKiem.addItem("Ma nha cung cap");

		txtTimKiem = new JTextField(20);
		txtTimKiem.setText("");
		btnTimKiem = new JButton("Tim kiem");
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

		contentpanel.add(panelTop);

		// panelMidLeft

		panelMidLeft = new JPanel();
		panelMidLeft.setPreferredSize(new Dimension(430, 120));
		SpringLayout sl_panelMidLeft = new SpringLayout();
		panelMidLeft.setLayout(sl_panelMidLeft);
		panelMidLeft.setBorder(border);

		Layer.put(panelMidLeft).in(sl_contentpanel).atLeft(contentpanel).withMargin(10).bottomOf(panelTop)
				.withMargin(10);

		JLabel lblMaTinh = new JLabel("Ma Tinh");
		Layer.put(lblMaTinh).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).atTop(panelMidLeft).withMargin(15);

		JLabel lblTenTinh = new JLabel("Ten Tinh");
		Layer.put(lblTenTinh).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).bottomOf(lblMaTinh)
				.withMargin(15);

		JLabel lblNguonGoc = new JLabel("Nguon goc");
		Layer.put(lblNguonGoc).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).bottomOf(lblTenTinh)
				.withMargin(15);

		txtMaTinh = new JTextField(25);
		Layer.put(txtMaTinh).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(130).atTop(lblMaTinh).withMargin(0);
		txtMaTinh.setEditable(false);

		txtTenTinh = new JTextField(25);
		Layer.put(txtTenTinh).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(130).atTop(lblTenTinh).withMargin(0);

		txtNguonGoc = new JTextField(25);
		Layer.put(txtNguonGoc).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(130).atTop(lblNguonGoc)
				.withMargin(0);

		panelMidLeft.add(lblMaTinh);
		panelMidLeft.add(lblTenTinh);
		panelMidLeft.add(lblNguonGoc);
		panelMidLeft.add(txtMaTinh);
		panelMidLeft.add(txtTenTinh);
		panelMidLeft.add(txtNguonGoc);

		contentpanel.add(panelMidLeft);

		// panelMidCenter
		panelMidCenter = new JPanel();
		panelMidCenter.setPreferredSize(new Dimension(430, 120));
		SpringLayout sl_panelMidCenter = new SpringLayout();
		panelMidCenter.setLayout(sl_panelMidCenter);
		panelMidCenter.setBorder(border);

		Layer.put(panelMidCenter).in(sl_contentpanel).leftOf(panelMidLeft).withMargin(20).bottomOf(panelTop)
				.withMargin(10);

		JLabel lblDacDiem = new JLabel("Dac diem");
		Layer.put(lblDacDiem).in(sl_panelMidCenter).atLeft(panelMidCenter).withMargin(10).atTop(panelMidCenter)
				.withMargin(15);

		JLabel lblMaNCC = new JLabel("Ma nha cung cap");
		Layer.put(lblMaNCC).in(sl_panelMidCenter).atLeft(panelMidCenter).withMargin(10).bottomOf(lblDacDiem)
				.withMargin(15);

		JLabel lblNgaySX = new JLabel("Ngay san xuat");
		Layer.put(lblNgaySX).in(sl_panelMidCenter).atLeft(panelMidCenter).withMargin(10).bottomOf(lblMaNCC)
				.withMargin(15);

		txtDacDiem = new JTextField(25);
		Layer.put(txtDacDiem).in(sl_panelMidCenter).atLeft(panelMidCenter).withMargin(130).atTop(lblDacDiem)
				.withMargin(0);

		txtMaNCC = new JTextField(15);
		Layer.put(txtMaNCC).in(sl_panelMidCenter).atLeft(panelMidCenter).withMargin(130).atTop(lblMaNCC).withMargin(0);
		txtMaNCC.setEditable(false);

		dateNgaySX = new JDateChooser();
		Layer.put(dateNgaySX).in(sl_panelMidCenter).atLeft(panelMidCenter).withMargin(130).atTop(lblNgaySX)
				.withMargin(0).atRight(txtDacDiem).withMargin(0);

		btnChonNCC = Genner.createButton("Chon", Genner.MEDIUM_SIZE);
		Layer.put(btnChonNCC).in(sl_panelMidCenter).leftOf(txtMaNCC).withMargin(15).atTop(txtMaNCC).withMargin(0)
				.atBottom(txtMaNCC).withMargin(0);
		btnChonNCC.setActionCommand(SELECT_PROVIDER_COMMAND);

		panelMidCenter.add(lblDacDiem);
		panelMidCenter.add(lblMaNCC);
		panelMidCenter.add(lblNgaySX);
		panelMidCenter.add(txtDacDiem);
		panelMidCenter.add(txtMaNCC);
		panelMidCenter.add(dateNgaySX);
		panelMidCenter.add(btnChonNCC);
		contentpanel.add(panelMidCenter);

		// panelMidRight
		panelMidRight = new JPanel();
		panelMidRight.setPreferredSize(new Dimension(430, 120));
		SpringLayout sl_panelMidRight = new SpringLayout();
		panelMidRight.setLayout(sl_panelMidRight);
		panelMidRight.setBorder(border);

		Layer.put(panelMidRight).in(sl_contentpanel).leftOf(panelMidCenter).withMargin(20).bottomOf(panelTop)
				.withMargin(10);

		JLabel lblNgayHH = new JLabel("Ngay het han");
		Layer.put(lblNgayHH).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(10).atTop(panelMidRight)
				.withMargin(15);

		dateNgayHH = new JDateChooser();
		Layer.put(dateNgayHH).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(130).atTop(lblNgayHH).withMargin(0)
				.atRight(panelMidRight).withMargin(20);

		JLabel lblMaLS = new JLabel("Ma lich su thai ky");
		Layer.put(lblMaLS).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(10).bottomOf(dateNgayHH)
				.withMargin(15);

		txtMaLS = new JTextField(15);
		Layer.put(txtMaLS).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(130).atTop(lblMaLS).withMargin(0);
		txtMaLS.setEditable(false);
		btnChonLSTK = Genner.createButton("Chon", Genner.MEDIUM_SIZE);
		Layer.put(btnChonLSTK).in(sl_panelMidRight).leftOf(txtMaLS).withMargin(15).atTop(txtMaLS).withMargin(0)
				.atBottom(txtMaLS).withMargin(0);
		btnChonLSTK.setActionCommand(SELECT_HISTORY_COMMAND);

		panelMidRight.add(lblNgayHH);
		panelMidRight.add(dateNgayHH);
		panelMidRight.add(lblMaLS);
		panelMidRight.add(txtMaLS);
		panelMidRight.add(btnChonLSTK);
		contentpanel.add(panelMidRight);

		// panelBot
		panelBot = new JPanel();
		SpringLayout sl_panelBot = new SpringLayout();
		panelBot.setLayout(sl_panelBot);
		Layer.put(panelBot).in(sl_contentpanel).atLeft(contentpanel).withMargin(10).bottomOf(panelMidLeft)
				.withMargin(10).atBottom(contentpanel).withMargin(5).atRight(contentpanel).withMargin(200);
		BorderLayout bl_panelBot = new BorderLayout();
		panelBot.setLayout(bl_panelBot);

		final String[] columnThaTinh =
		{ "Ma tinh", "Ten tinh", "Nguon goc", "Dac diem", "Ma nha cung cap", "Ngay san xuat", "Ngay het han",
				"Ma lich su thai ky" };
		tbThaTinh = new JTable(new Object[0][0], columnThaTinh);
		dtm = new DefaultTableModel(columnThaTinh, 0);
		tbThaTinh.setFillsViewportHeight(true);
		JScrollPane scrolltbThaTinh = new JScrollPane(tbThaTinh, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelBot.add(scrolltbThaTinh);

		// control mainform
		btnThem = Genner.createButton("Them", Genner.MEDIUM_SIZE);
		Layer.put(btnThem).in(sl_contentpanel).leftOf(panelBot).withMargin(60).bottomOf(panelMidRight).withMargin(100);
		btnThem.setActionCommand(INSERT_COMMAND);

		btnXoa = Genner.createButton("Xoa", Genner.MEDIUM_SIZE);
		Layer.put(btnXoa).in(sl_contentpanel).leftOf(panelBot).withMargin(60).bottomOf(btnThem).withMargin(30);
		btnXoa.setActionCommand(DELETE_COMMAND);

		btnCapNhat = Genner.createButton("Cap nhat", Genner.MEDIUM_SIZE);
		Layer.put(btnCapNhat).in(sl_contentpanel).leftOf(panelBot).withMargin(60).bottomOf(btnXoa).withMargin(30);
		btnCapNhat.setActionCommand(UPDATE_COMMAND);

		btnLuu = Genner.createButton("Luu", Genner.MEDIUM_SIZE);
		Layer.put(btnLuu).in(sl_contentpanel).leftOf(panelBot).withMargin(60).bottomOf(btnCapNhat).withMargin(30);
		btnLuu.setActionCommand(SAVE_COMMAND);

		contentpanel.add(btnThem);
		contentpanel.add(btnXoa);
		contentpanel.add(btnCapNhat);
		contentpanel.add(btnLuu);
		contentpanel.add(panelBot);

		// set mainform
		setPreferredSize(new Dimension(1366, 730));
		setResizable(false);
		setTitle("Tha tinh");
		setContentPane(new JScrollPane(contentpanel));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void setController(ThaTinhController ttc)
	{
		btnTimKiem.addActionListener(ttc);
		btnChonNCC.addActionListener(ttc);
		btnThem.addActionListener(ttc);
		btnXoa.addActionListener(ttc);
		btnCapNhat.addActionListener(ttc);
		btnLuu.addActionListener(ttc);
		btnChonLSTK.addActionListener(ttc);
		tbThaTinh.setModel(dtm);

		tbThaTinh.addMouseListener(ttc);

	}

	void notice(short code)
	{
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

}
