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

import com.toedter.calendar.JDateChooser;

import common.Genner;
import common.Layer;
import controller.CTBenhAnController;

public class CTBenhAnView extends ViewBase
{

	/**
	 * 
	 */
	private static final long		serialVersionUID	= -2649631143516978575L;
	private JPanel					panelMain;
	public JButton					btnTimKiem, btnChonBenh, btnThem, btnXoa;
	public JDateChooser				dateNgayPhatHien, dateNgayHetBenh;
	public JTable					tbCTBenh;
	public JComboBox<Object>		cbxTimKiem, cbxTinhTrang;
	public JTextField				txtTimKiem, txtMaBenh;
	public JTextArea				txtGhiChu;
	public static DefaultTableModel	dtm;
	public String					mabenhan;

	public static String			SEARCH_COMMAND		= "search";
	public static String			INSERT_COMMAND		= "insert";
	public static String			DELETE_COMMAND		= "delete";
	public static String			CHOOSE_COMMAND		= "choose";

	public CTBenhAnView(String _mabenhan) {

		mabenhan = _mabenhan;
		panelMain = new JPanel();
		SpringLayout sl_panelMain = new SpringLayout();
		panelMain.setLayout(sl_panelMain);
		JPanel panelTop, panelBot, panelMidLeft, panelMidRight;

		// set panelTop
		final Border border = BorderFactory.createLineBorder(Color.gray);
		final TitledBorder titleTimKiem = BorderFactory.createTitledBorder(border, "Tim kiem chi tiet bệnh");

		panelTop = new JPanel();
		panelTop.setBorder(titleTimKiem);
		panelTop.setPreferredSize(new Dimension(400, 70));
		Layer.put(panelTop).in(sl_panelMain).atTop(panelMain).withMargin(5).atLeft(panelMain).withMargin(10)
				.atRight(panelMain).withMargin(10);

		cbxTimKiem = new JComboBox<>();
		cbxTimKiem.addItem("Ma benh");
		cbxTimKiem.addItem("Tinh trang");

		txtTimKiem = new JTextField(20);
		btnTimKiem = new JButton("Tim kiem");
//		btnTimKiem.setActionCommand(SEARCH_COMMAND);
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
		panelMidLeft.setPreferredSize(new Dimension(480, 130));
		SpringLayout sl_panelMidLeft = new SpringLayout();
		panelMidLeft.setLayout(sl_panelMidLeft);
		panelMidLeft.setBorder(border);

		Layer.put(panelMidLeft).in(sl_panelMain).atLeft(panelMain).withMargin(10).bottomOf(panelTop).withMargin(10);

		JLabel lblMaBenh = new JLabel("Ma benh");
		Layer.put(lblMaBenh).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).atTop(panelMidLeft).withMargin(15);

		JLabel lblNgayPhatHien = new JLabel("Ngay phat hien");
		Layer.put(lblNgayPhatHien).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).bottomOf(lblMaBenh)
				.withMargin(15);

		JLabel lblNgayHetBenh = new JLabel("Ngay het benh");
		Layer.put(lblNgayHetBenh).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).bottomOf(lblNgayPhatHien)
				.withMargin(15);

		txtMaBenh = new JTextField(15);
		Layer.put(txtMaBenh).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(130).atTop(lblMaBenh).withMargin(0);
		txtMaBenh.setEditable(false);

		dateNgayPhatHien = new JDateChooser();
		Layer.put(dateNgayPhatHien).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(130).atTop(lblNgayPhatHien)
				.withMargin(0).atRight(panelMidLeft).withMargin(20);

		dateNgayHetBenh = new JDateChooser();
		Layer.put(dateNgayHetBenh).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(130).atTop(lblNgayHetBenh)
				.withMargin(0).atRight(panelMidLeft).withMargin(20);

		btnChonBenh = Genner.createButton("Chon", Genner.MEDIUM_SIZE);
		Layer.put(btnChonBenh).in(sl_panelMidLeft).leftOf(txtMaBenh).withMargin(15).atTop(txtMaBenh).withMargin(0)
				.atBottom(txtMaBenh).withMargin(0);
		btnChonBenh.setActionCommand(CHOOSE_COMMAND);

		panelMidLeft.add(lblMaBenh);
		panelMidLeft.add(lblNgayPhatHien);
		panelMidLeft.add(lblNgayHetBenh);
		panelMidLeft.add(txtMaBenh);
		panelMidLeft.add(dateNgayPhatHien);
		panelMidLeft.add(dateNgayHetBenh);
		panelMidLeft.add(btnChonBenh);
		panelMain.add(panelMidLeft);

		// set panelRight
		panelMidRight = new JPanel();
		panelMidRight.setPreferredSize(new Dimension(480, 130));
		SpringLayout sl_panelMidRight = new SpringLayout();
		panelMidRight.setLayout(sl_panelMidRight);
		panelMidRight.setBorder(border);

		Layer.put(panelMidRight).in(sl_panelMain).atRight(panelMain).withMargin(10).bottomOf(panelTop).withMargin(10);

		JLabel lblTinhTrang = new JLabel("Tinh trang");
		Layer.put(lblTinhTrang).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(10).atTop(panelMidRight)
				.withMargin(15);

		cbxTinhTrang = new JComboBox<>();
		Layer.put(cbxTinhTrang).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(100).atTop(lblTinhTrang)
				.withMargin(0);
		cbxTinhTrang.addItem("Chua chua tri");
		cbxTinhTrang.addItem("Dang chua tri");
		cbxTinhTrang.addItem("Khong chua duoc");
		cbxTinhTrang.addItem("Da het benh");

		JLabel lblGhiChu = new JLabel("Ghi chu");
		Layer.put(lblGhiChu).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(10).bottomOf(lblTinhTrang)
				.withMargin(20);

		txtGhiChu = new JTextArea(5, 30);
		txtGhiChu.setLineWrap(true);
		txtGhiChu.setWrapStyleWord(true);
		Layer.put(txtGhiChu).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(100).atTop(lblGhiChu).withMargin(0)
				.atBottom(panelMidRight).withMargin(10).atRight(panelMidRight).withMargin(10);
		JScrollPane scrollNgayTao = new JScrollPane(txtGhiChu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelMidRight.add(lblTinhTrang);
		panelMidRight.add(cbxTinhTrang);
		panelMidRight.add(lblGhiChu);
		panelMidRight.add(txtGhiChu);
		panelMain.add(panelMidRight);

		// panel bottom
		panelBot = new JPanel();
		SpringLayout sl_panelBot = new SpringLayout();
		panelBot.setLayout(sl_panelBot);
		Layer.put(panelBot).in(sl_panelMain).atLeft(panelMain).withMargin(5).bottomOf(panelMidLeft).withMargin(10)
				.atBottom(panelMain).withMargin(5).atRight(panelMain).withMargin(150);
		BorderLayout bl_panelBot = new BorderLayout();
		panelBot.setLayout(bl_panelBot);

		final String[] columnCTBenh =
		{ "Ma benh", "Ngay phat hien", "Ngay het benh", "Tinh trang", "Ghi chu" };
		tbCTBenh = new JTable(new Object[0][0], columnCTBenh);
		dtm = new DefaultTableModel(columnCTBenh, 0);
		tbCTBenh.setFillsViewportHeight(true);
		JScrollPane scrolltbBenh = new JScrollPane(tbCTBenh, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelBot.add(scrolltbBenh);
		panelMain.add(panelBot);

		// set controller main

		btnThem = Genner.createButton("Them", Genner.MEDIUM_SIZE);
		Layer.put(btnThem).in(sl_panelMain).leftOf(panelBot).withMargin(30).bottomOf(panelMidRight).withMargin(80);
		btnThem.setActionCommand(INSERT_COMMAND);

		btnXoa = Genner.createButton("Xoa", Genner.MEDIUM_SIZE);
		Layer.put(btnXoa).in(sl_panelMain).leftOf(panelBot).withMargin(30).bottomOf(btnThem).withMargin(30);
		btnXoa.setActionCommand(DELETE_COMMAND);

		panelMain.add(btnThem);
		panelMain.add(btnXoa);

		// set mainform
		setPreferredSize(new Dimension(1000, 720));
		setResizable(false);
		setTitle("Chi tiet benh");
		setContentPane(new JScrollPane(panelMain));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void setController(CTBenhAnController ctc)
	{
		btnTimKiem.addActionListener(ctc);
		btnThem.addActionListener(ctc);
		btnXoa.addActionListener(ctc);
		btnChonBenh.addActionListener(ctc);
		tbCTBenh.setModel(dtm);
		tbCTBenh.addMouseListener(ctc);
	}
}