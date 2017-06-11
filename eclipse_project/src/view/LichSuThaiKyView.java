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
import controller.LichSuThaiKyController;

public class LichSuThaiKyView extends ViewBase
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1259601497452384139L;
	private JPanel					panelMain;
	public JButton					btnThem, btnXoa, btnChonLS, btnCapNhat, btnTimKiem, btnChonHeo;
	public JTextField				txtMaLSTK, txtMaHeo, txtTimKiem;
	public JComboBox<Object>				cbxTimKiem;
	public JTextArea				txtNoiDung;
	public JDateChooser				dateNgayGhiNhan;
	public JTable					tbLSTK;
	public static DefaultTableModel	dtm;

	public static String			SEARCH_COMMAND			= "search";
	public static String			INSERT_COMMAND			= "insert";
	public static String			UPDATE_COMMAND			= "update";
	public static String			DELETE_COMMAND			= "delete";
	public static String			CHOOSE_HISTORY_COMMAND	= "CHOOSE";
	public static String			SELECT_PIG				= "pig";

	public LichSuThaiKyView() {
		panelMain = new JPanel();
		SpringLayout sl_panelMain = new SpringLayout();
		panelMain.setLayout(sl_panelMain);
		JPanel panelTop, panelBot, panelMidLeft, panelMidRight;

		// set panelTop
		final Border border = BorderFactory.createLineBorder(Color.gray);
		final TitledBorder titleTimKiem = BorderFactory.createTitledBorder(border, "Tìm kiếm bệnh");

		panelTop = new JPanel();
		panelTop.setBorder(titleTimKiem);
		panelTop.setPreferredSize(new Dimension(400, 70));
		Layer.put(panelTop).in(sl_panelMain).atTop(panelMain).withMargin(5).atLeft(panelMain).withMargin(10)
				.atRight(panelMain).withMargin(10);

		cbxTimKiem = new JComboBox<>();

		cbxTimKiem.addItem("Mã lịch sử thai kỳ");
		cbxTimKiem.addItem("Mã heo");

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
		panelMidLeft.setPreferredSize(new Dimension(480, 130));
		SpringLayout sl_panelMidLeft = new SpringLayout();
		panelMidLeft.setLayout(sl_panelMidLeft);
		panelMidLeft.setBorder(border);

		Layer.put(panelMidLeft).in(sl_panelMain).atLeft(panelMain).withMargin(10).bottomOf(panelTop).withMargin(10);

		JLabel lblMaLSTK = new JLabel("Mã lịch sử thai kỳ");
		Layer.put(lblMaLSTK).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).atTop(panelMidLeft).withMargin(15);

		JLabel lblNgayGhiNhan = new JLabel("Ngày ghi nhận");
		Layer.put(lblNgayGhiNhan).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).bottomOf(lblMaLSTK)
				.withMargin(20);

		JLabel lblMaHeo = new JLabel("Mã heo");
		Layer.put(lblMaHeo).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(10).bottomOf(lblNgayGhiNhan)
				.withMargin(20);

		txtMaLSTK = new JTextField(25);
		Layer.put(txtMaLSTK).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(130).atTop(lblMaLSTK).withMargin(0);
		txtMaLSTK.setEditable(false);

		dateNgayGhiNhan = new JDateChooser();
		Layer.put(dateNgayGhiNhan).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(130).atTop(lblNgayGhiNhan)
				.withMargin(0).atRight(txtMaLSTK).withMargin(0);

		txtMaHeo = new JTextField(15);
		Layer.put(txtMaHeo).in(sl_panelMidLeft).atLeft(panelMidLeft).withMargin(130).atTop(lblMaHeo).withMargin(0);
		txtMaHeo.setEditable(false);

		btnChonHeo = Genner.createButton("Chọn", Genner.MEDIUM_SIZE);
		Layer.put(btnChonHeo).in(sl_panelMidLeft).leftOf(txtMaHeo).withMargin(15).atTop(txtMaHeo).withMargin(0)
				.atBottom(txtMaHeo).withMargin(0);
		btnChonHeo.setActionCommand(SELECT_PIG);

		panelMidLeft.add(lblMaLSTK);
		panelMidLeft.add(lblNgayGhiNhan);
		panelMidLeft.add(lblMaHeo);
		panelMidLeft.add(txtMaLSTK);
		panelMidLeft.add(dateNgayGhiNhan);
		panelMidLeft.add(txtMaHeo);
		panelMidLeft.add(btnChonHeo);

		panelMain.add(panelMidLeft);

		// set panelRight
		panelMidRight = new JPanel();
		panelMidRight.setPreferredSize(new Dimension(480, 130));
		SpringLayout sl_panelMidRight = new SpringLayout();
		panelMidRight.setLayout(sl_panelMidRight);
		panelMidRight.setBorder(border);

		Layer.put(panelMidRight).in(sl_panelMain).atRight(panelMain).withMargin(10).bottomOf(panelTop).withMargin(10);

		JLabel lblNoiDung = new JLabel("Nội dung");
		Layer.put(lblNoiDung).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(10).atTop(panelMidRight)
				.withMargin(15);

		txtNoiDung = new JTextArea(5, 30);
		txtNoiDung.setLineWrap(true);
		txtNoiDung.setWrapStyleWord(true);
		Layer.put(txtNoiDung).in(sl_panelMidRight).atLeft(panelMidRight).withMargin(100).atTop(lblNoiDung).withMargin(0)
				.atBottom(panelMidRight).withMargin(20).atRight(panelMidRight).withMargin(50);

		JScrollPane scrollNoiDung = new JScrollPane(txtNoiDung, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelMidRight.add(lblNoiDung);
		panelMidRight.add(txtNoiDung);
		panelMidRight.add(scrollNoiDung);
		panelMain.add(panelMidRight);

		// set panelBot
		panelBot = new JPanel();
		SpringLayout sl_panelBot = new SpringLayout();
		panelBot.setLayout(sl_panelBot);
		Layer.put(panelBot).in(sl_panelMain).atLeft(panelMain).withMargin(5).bottomOf(panelMidLeft).withMargin(10)
				.atBottom(panelMain).withMargin(5).atRight(panelMain).withMargin(150);
		BorderLayout bl_panelBot = new BorderLayout();
		panelBot.setLayout(bl_panelBot);
		TitledBorder titleLichSu = BorderFactory.createTitledBorder(border, "Lịch sử thai kỳ");
		panelBot.setBorder(titleLichSu);

		final String[] columnLSTK =
		{ "Mã lịch sử thai kỳ", "Ngày ghi nhận", "Nội dung", "Mã heo" };
		tbLSTK = new JTable(new Object[0][0], columnLSTK);
		dtm = new DefaultTableModel(columnLSTK, 0);
		tbLSTK.setFillsViewportHeight(true);
		JScrollPane scrolltbBenh = new JScrollPane(tbLSTK, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelBot.add(scrolltbBenh);
		panelMain.add(panelBot);

		// set controller main

		btnThem = Genner.createButton("Thêm", Genner.MEDIUM_SIZE);
		Layer.put(btnThem).in(sl_panelMain).leftOf(panelBot).withMargin(30).bottomOf(panelMidRight).withMargin(80);
		btnThem.setActionCommand(INSERT_COMMAND);

		btnXoa = Genner.createButton("Xóa", Genner.MEDIUM_SIZE);
		Layer.put(btnXoa).in(sl_panelMain).leftOf(panelBot).withMargin(30).bottomOf(btnThem).withMargin(30);
		btnXoa.setActionCommand(DELETE_COMMAND);

		btnCapNhat = Genner.createButton("Cập nhật", Genner.MEDIUM_SIZE);
		Layer.put(btnCapNhat).in(sl_panelMain).leftOf(panelBot).withMargin(30).bottomOf(btnXoa).withMargin(30);
		btnCapNhat.setActionCommand(UPDATE_COMMAND);

		btnChonLS = Genner.createButton("Chọn LS", Genner.MEDIUM_SIZE);
		Layer.put(btnChonLS).in(sl_panelMain).leftOf(panelBot).withMargin(30).bottomOf(btnCapNhat).withMargin(30);
		btnChonLS.setActionCommand(CHOOSE_HISTORY_COMMAND);
		btnChonLS.setEnabled(false);

		panelMain.add(btnThem);
		panelMain.add(btnXoa);
		panelMain.add(btnCapNhat);
		panelMain.add(btnChonLS);

		// set mainform
		setPreferredSize(new Dimension(1000, 720));
		setResizable(false);
		setTitle("Ghi nhận thai kỳ");
		setContentPane(new JScrollPane(panelMain));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void setController(LichSuThaiKyController lstkc)
	{
		btnThem.addActionListener(lstkc);
		btnXoa.addActionListener(lstkc);
		btnChonLS.addActionListener(lstkc);
		btnCapNhat.addActionListener(lstkc);
		btnChonHeo.addActionListener(lstkc);
		btnTimKiem.addActionListener(lstkc);
		tbLSTK.setModel(dtm);
		tbLSTK.addMouseListener(lstkc);

	}

}
