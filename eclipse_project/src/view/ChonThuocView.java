package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import common.Genner;
import common.Layer;
import controller.ChonThuocController;

public class ChonThuocView extends ViewBase
{
	/**
	 * 
	 */
	private static final long		serialVersionUID	= -7660128175824496550L;
	private JPanel					panelMain;
	public JButton					btnChonThuoc, btnTimKiem;
	public JTextField				txtTimKiem;
	public JComboBox<Object>		cbxTimKiem;
	public JTable					tbChonThuoc;
	public static DefaultTableModel	dtm;

	public static String			SEARCH_COMMAND		= "search";
	public static String			SELECT_COMMAND		= "select";

	public ChonThuocView() {
		panelMain = new JPanel();
		SpringLayout sl_panelMain = new SpringLayout();
		panelMain.setLayout(sl_panelMain);
		JPanel panelTop, panelBot;

		// panelTop
		final Border border = BorderFactory.createLineBorder(Color.gray);
		final TitledBorder titleTimKiem = BorderFactory.createTitledBorder(border, "Tim kiem benh");

		panelTop = new JPanel();
		panelTop.setBorder(titleTimKiem);
		panelTop.setPreferredSize(new Dimension(400, 70));
		Layer.put(panelTop).in(sl_panelMain).atTop(panelMain).withMargin(5).atLeft(panelMain).withMargin(10)
				.atRight(panelMain).withMargin(10);

		cbxTimKiem = new JComboBox<>();
		cbxTimKiem.addItem("Ma thuoc");
		cbxTimKiem.addItem("Ten thuoc");

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

		// set panelBot
		panelBot = new JPanel();
		SpringLayout sl_panelBot = new SpringLayout();
		panelBot.setLayout(sl_panelBot);
		Layer.put(panelBot).in(sl_panelMain).atLeft(panelMain).withMargin(5).bottomOf(panelTop).withMargin(10)
				.atBottom(panelMain).withMargin(5).atRight(panelMain).withMargin(150);
		BorderLayout bl_panelBot = new BorderLayout();
		panelBot.setLayout(bl_panelBot);

		final String[] columnChonThuoc =
		{ "Ma thuoc", "Ten thuoc", "Thanh phan" };
		tbChonThuoc = new JTable(new Object[0][0], columnChonThuoc);
		dtm = new DefaultTableModel(columnChonThuoc, 0);
		tbChonThuoc.setFillsViewportHeight(true);
		JScrollPane scrolltbBenh = new JScrollPane(tbChonThuoc, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelBot.add(scrolltbBenh);
		panelMain.add(panelBot);

		// set controller main

		btnChonThuoc = Genner.createButton("Chon thuoc", Genner.MEDIUM_SIZE);
		btnChonThuoc.setEnabled(false);
		Layer.put(btnChonThuoc).in(sl_panelMain).leftOf(panelBot).withMargin(20).bottomOf(panelTop).withMargin(80)
				.atRight(panelMain).withMargin(20);
		btnChonThuoc.setActionCommand(SELECT_COMMAND);

		panelMain.add(btnChonThuoc);

		// set mainform
		setPreferredSize(new Dimension(600, 480));
		setResizable(false);
		setTitle("Chon thuoc");
		setContentPane(new JScrollPane(panelMain));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void setController(ChonThuocController ctc)
	{
		btnChonThuoc.addActionListener(ctc);
		btnTimKiem.addActionListener(ctc);
		tbChonThuoc.setModel(dtm);
		tbChonThuoc.addMouseListener(ctc);

	}
}
