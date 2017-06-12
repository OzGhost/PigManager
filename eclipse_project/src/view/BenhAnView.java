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

import common.Constants;
import common.Genner;
import common.Layer;
import controller.BenhAnController;

public class BenhAnView extends ViewBase
{
	/**
	 * 
	 */
	private static final long		serialVersionUID	= -1282199101511235222L;

	private JPanel					panelMain;

	public static DefaultTableModel	dtm;

	public JButton					btnTimKiem, btnThem, btnXoa, btnCapNhat, btnDungThuoc, btnChonHeo, btnCTBenh, btnTrangChu;
	public JDateChooser				dateNgayTao, dateNgayPhatHien, dateNgayHetBenh;
	public JTable					tbBenhAn;
	public JComboBox<Object>		cbxTimKiem, cbxTinhTrang;
	public JTextField				txtMaSoHeo, txtMaBenh, txtTimKiem, txtMaBenhAn;
	public JTextArea				txtGhiChu;

	public static String			SEARCH_COMMAND		= "search";
	public static String			INSERT_COMMAND		= "insert";
	public static String			DELETE_COMMAND		= "delete";
	public static String			UPDATE_COMMAND		= "update";
	public static String			DETAIL_COMMAND		= "detail";
	public static String			DRUG_COMMAND		= "pain";
	public static String			PIG_COMMAND			= "pig";

	public BenhAnView() {
		panelMain = new JPanel();
		SpringLayout sl_panelMain = new SpringLayout();
		panelMain.setLayout(sl_panelMain);
		JPanel panelTop, panelBot, panelMid;

		// set panelTop
		final Border border = BorderFactory.createLineBorder(Color.gray);
		final TitledBorder titleTimKiem = BorderFactory.createTitledBorder(border, "Tim kiem benh an");

		panelTop = new JPanel();
		panelTop.setBorder(titleTimKiem);
		panelTop.setPreferredSize(new Dimension(400, 70));
		Layer.put(panelTop).in(sl_panelMain).atTop(panelMain).withMargin(10).atLeft(panelMain).withMargin(10)
				.atRight(panelMain).withMargin(10);

		cbxTimKiem = new JComboBox<>();
		cbxTimKiem.addItem("Ma benh an");

		txtTimKiem = new JTextField(20);
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
		panelMain.add(panelTop);

		// panelMidLeft

		panelMid = new JPanel();
		panelMid.setPreferredSize(new Dimension(480, 120));
		SpringLayout sl_panelMid = new SpringLayout();
		panelMid.setLayout(sl_panelMid);
		panelMid.setBorder(border);

		Layer.put(panelMid).in(sl_panelMain).atLeft(panelMain).withMargin(10).bottomOf(panelTop).withMargin(10)
				.atRight(panelMain).withMargin(10);

		JLabel lblMaBenhAn = new JLabel("Ma benh an");
		Layer.put(lblMaBenhAn).in(sl_panelMid).atLeft(panelMid).withMargin(180).atTop(panelMid).withMargin(15);

		JLabel lblMaSoHeo = new JLabel("Ma so heo");
		Layer.put(lblMaSoHeo).in(sl_panelMid).atLeft(panelMid).withMargin(180).bottomOf(lblMaBenhAn).withMargin(15);

		JLabel lblNgayTao = new JLabel("Ngay tao");
		Layer.put(lblNgayTao).in(sl_panelMid).atLeft(panelMid).withMargin(180).bottomOf(lblMaSoHeo).withMargin(15);

		txtMaBenhAn = new JTextField(25);
		Layer.put(txtMaBenhAn).in(sl_panelMid).atLeft(panelMid).withMargin(300).atTop(lblMaBenhAn).withMargin(0);
		txtMaBenhAn.setEditable(false);

		txtMaSoHeo = new JTextField(15);
		Layer.put(txtMaSoHeo).in(sl_panelMid).atLeft(panelMid).withMargin(300).atTop(lblMaSoHeo).withMargin(0);
		txtMaSoHeo.setEditable(false);

		dateNgayTao = new JDateChooser();
		Layer.put(dateNgayTao).in(sl_panelMid).atLeft(panelMid).withMargin(300).atTop(lblNgayTao).withMargin(0)
				.atRight(txtMaBenhAn).withMargin(0);

		btnChonHeo = Genner.createButton("Chon", Genner.MEDIUM_SIZE);
		Layer.put(btnChonHeo).in(sl_panelMid).leftOf(txtMaSoHeo).withMargin(15).atTop(txtMaSoHeo).withMargin(0)
				.atBottom(txtMaSoHeo).withMargin(0);
		btnChonHeo.setActionCommand(PIG_COMMAND);

		panelMid.add(lblMaBenhAn);
		panelMid.add(lblMaSoHeo);
		panelMid.add(lblNgayTao);
		panelMid.add(txtMaBenhAn);
		panelMid.add(dateNgayTao);
		panelMid.add(txtMaSoHeo);
		panelMid.add(btnChonHeo);
		panelMain.add(panelMid);

		// set panelBot
		panelBot = new JPanel();
		SpringLayout sl_panelBot = new SpringLayout();
		panelBot.setLayout(sl_panelBot);
		Layer.put(panelBot).in(sl_panelMain).atLeft(panelMain).withMargin(5).bottomOf(panelMid).withMargin(10)
				.atBottom(panelMain).withMargin(5).atRight(panelMain).withMargin(200);
		BorderLayout bl_panelBot = new BorderLayout();
		panelBot.setLayout(bl_panelBot);

		final String[] columnBenhAn =
		{ "Ma benh an", "Ma so heo", "Ngay tao" };
		tbBenhAn = new JTable(new Object[0][0], columnBenhAn);
		dtm = new DefaultTableModel(columnBenhAn, 0);
		tbBenhAn.setFillsViewportHeight(true);
		JScrollPane scrolltbBenhAn = new JScrollPane(tbBenhAn, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelBot.add(scrolltbBenhAn);
		panelMain.add(panelBot);

		// set controller main

		btnThem = Genner.createButton("Them", Genner.MEDIUM_SIZE);
		Layer.put(btnThem).in(sl_panelMain).leftOf(panelBot).withMargin(50).bottomOf(panelMid).withMargin(30)
				.atRight(panelMain).withMargin(50);
		btnThem.setActionCommand(INSERT_COMMAND);

		btnXoa = Genner.createButton("Xoa", Genner.MEDIUM_SIZE);
		Layer.put(btnXoa).in(sl_panelMain).leftOf(panelBot).withMargin(50).bottomOf(btnThem).withMargin(20)
				.atRight(panelMain).withMargin(50);
		btnXoa.setActionCommand(DELETE_COMMAND);

		btnCapNhat = Genner.createButton("Cap nhat", Genner.MEDIUM_SIZE);
		Layer.put(btnCapNhat).in(sl_panelMain).leftOf(panelBot).withMargin(50).bottomOf(btnXoa).withMargin(20)
				.atRight(panelMain).withMargin(50);
		btnCapNhat.setActionCommand(UPDATE_COMMAND);

		btnCTBenh = Genner.createButton("Chi tiet", Genner.MEDIUM_SIZE);
		Layer.put(btnCTBenh).in(sl_panelMain).leftOf(panelBot).withMargin(50).bottomOf(btnCapNhat).withMargin(20)
				.atRight(panelMain).withMargin(50);
		btnCTBenh.setActionCommand(DETAIL_COMMAND);

		btnDungThuoc = Genner.createButton("Dung thuoc", Genner.MEDIUM_SIZE);
		Layer.put(btnDungThuoc).in(sl_panelMain).leftOf(panelBot).withMargin(50).bottomOf(btnCTBenh).withMargin(20)
				.atRight(panelMain).withMargin(50);
		btnDungThuoc.setActionCommand(DRUG_COMMAND);
		
		btnTrangChu = Genner.createButton("Trang Chu", Genner.BIG_SIZE);
		Layer.put(btnTrangChu).in(sl_panelMain).leftOf(panelBot).withMargin(40).atBottom(panelMain).withMargin(10)
				.atRight(panelMain).withMargin(40);
		btnTrangChu.setActionCommand(Constants.AC_HOME);

		panelMain.add(btnThem);
		panelMain.add(btnXoa);
		panelMain.add(btnCapNhat);
		panelMain.add(btnDungThuoc);
		panelMain.add(btnCTBenh);
		panelMain.add(btnTrangChu);

		// set mainform
		setSize(new Dimension(800, 600));
		setResizable(false);
		setTitle("Quan ly benh an");
		setContentPane(new JScrollPane(panelMain));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void setController(BenhAnController bac)
	{
		btnTimKiem.addActionListener(bac);
		btnThem.addActionListener(bac);
		btnXoa.addActionListener(bac);
		btnCapNhat.addActionListener(bac);
		btnCTBenh.addActionListener(bac);
		btnDungThuoc.addActionListener(bac);
		btnChonHeo.addActionListener(bac);
		btnTrangChu.addActionListener(bac);
		tbBenhAn.setModel(dtm);
		tbBenhAn.addMouseListener(bac);

	}

}
