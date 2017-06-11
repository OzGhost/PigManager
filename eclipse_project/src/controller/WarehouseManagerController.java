/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//import com.sun.javafx.scene.layout.region.Margins;
import common.RefPayload;
import common.Watcher;
import db.Entity;
import db.db;
import model.FoodModel;
import model.ProviderManagerModel;
import model.WarehouseManagerModel;
import view.FoodView;
import view.ProviderManagerView;
import view.WarehouseManagerView;

/**
 *
 * @author Dang Nhat Hai Long
 */
public class WarehouseManagerController extends ControllerBase<WarehouseManagerModel, WarehouseManagerView>
		implements ActionListener, MouseListener, Watcher
{
	public ResultSet		resultSet	= null;
	public Vector			rowData;
	public SimpleDateFormat	formatdate	= new SimpleDateFormat("yyyy-MM-dd");

	public void LoadThuocLenTable(int luachon, String tenthuoc)
	{
		final String[] _columnsTBKho =
		{ "Mã thuốc", "Tên thuốc", "Thành phần", "Chỉ định", "Đơn vị", "Còn lại", "Mã nhà cung cấp", "Nhà cung cấp",
				"Ngày sản xuất", "Ngày hết hạn" };
		view.dtm = new DefaultTableModel(_columnsTBKho, 0);

		view._tbKho.setModel(view.dtm);
		if (luachon == 0)
		{
			resultSet = WarehouseManagerModel.getData("SELECT T.MATHUOC, " + "T.TENTHUOC, " + "T.THANHPHAN, " + "T.CHIDINH, "
					+ "T.DONVI, " + "T.CONLAI, " + "T.NHACUNGCAP_REF.MANCC, " + "T.NHACUNGCAP_REF.TENNCC, "
					+ "T.NGAYSANXUAT, " + "T.NGAYHETHAN\n" + "FROM THUOC T");
		}
		else
		{
			resultSet = WarehouseManagerModel.getData("SELECT T.MATHUOC, " + "T.TENTHUOC, " + "T.THANHPHAN, " + "T.CHIDINH, "
					+ "T.DONVI, " + "T.CONLAI, " + "T.NHACUNGCAP_REF.MANCC, " + "T.NHACUNGCAP_REF.TENNCC, "
					+ "T.NGAYSANXUAT, " + "T.NGAYHETHAN\n" + "FROM THUOC T where tenthuoc like '%" + tenthuoc + "%'");
		}

		if (resultSet != null)
		{
			view.dtm.setRowCount(0);
			try
			{
				while (resultSet.next())
				{
					rowData = new Vector();
					rowData.add(resultSet.getString("MATHUOC"));
					rowData.add(resultSet.getString("TENTHUOC"));
					rowData.add(resultSet.getString("THANHPHAN"));
					rowData.add(resultSet.getString("CHIDINH"));
					rowData.add(resultSet.getString("DONVI"));
					rowData.add(resultSet.getFloat("CONLAI"));
					rowData.add(resultSet.getString("NHACUNGCAP_REF.MANCC"));
					rowData.add(resultSet.getString("NHACUNGCAP_REF.TENNCC"));
					rowData.add(resultSet.getDate("NGAYSANXUAT"));
					rowData.add(resultSet.getDate("NGAYHETHAN"));
					view.dtm.addRow(rowData);
				}
			}
			catch (SQLException ex)
			{
				JOptionPane.showMessageDialog(null, ex);
				Logger.getLogger(StablesController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public void LoadThucAnLenTable(int luachon, String tenloaithucan)
	{

		final String[] _columnsTBKho =
		{ "Mã thức ăn", "Mã loại thức ăn", "Loại thức ăn", "Còn lại", "Đơn vị", "Ngày sản xuất", "Ngày hết hạn",
				"Mã nhà cung cấp", "Nhà cung cấp" };
		view.dtm = new DefaultTableModel(_columnsTBKho, 0);

		view._tbKho.setModel(view.dtm);
		if (luachon == 0)
		{
			resultSet = WarehouseManagerModel.getData(
					"select TA.MATHUCAN, " + "TA.LOAITHUCAN_REF.MALOAITHUCAN," + "TA.LOAITHUCAN_REF.TENLOAITHUCAN, "
							+ "TA.CONLAI, " + "TA.LOAITHUCAN_REF.DONVI, " + "TA.NGAYSANXUAT, " + "TA.NGAYHETHAN, "
							+ "TA.NHACUNGCAP_REF.MANCC, " + "TA.NHACUNGCAP_REF.TENNCC\n" + "from THUCAN TA");
		}
		else
		{
			resultSet = WarehouseManagerModel.getData("select TA.MATHUCAN, " + "TA.LOAITHUCAN_REF.MALOAITHUCAN,"
					+ "TA.LOAITHUCAN_REF.TENLOAITHUCAN, " + "TA.CONLAI, TA.LOAITHUCAN_REF.DONVI, " + "TA.NGAYSANXUAT, "
					+ "TA.NGAYHETHAN, " + "TA.NHACUNGCAP_REF.MANCC, " + "TA.NHACUNGCAP_REF.TENNCC  " + "from THUCAN TA "
					+ "where TA.LOAITHUCAN_REF.TENLOAITHUCAN LIKE '%" + tenloaithucan + "%'");
		}

		if (resultSet != null)
		{
			view.dtm.setRowCount(0);
			try
			{
				while (resultSet.next())
				{
					rowData = new Vector();
					rowData.add(resultSet.getString("MATHUCAN"));
					rowData.add(resultSet.getString("LOAITHUCAN_REF.MALOAITHUCAN"));
					rowData.add(resultSet.getString("LOAITHUCAN_REF.TENLOAITHUCAN"));
					rowData.add(resultSet.getString("CONLAI"));
					rowData.add(resultSet.getString("LOAITHUCAN_REF.DONVI"));
					rowData.add(resultSet.getDate("NGAYSANXUAT"));
					rowData.add(resultSet.getDate("NGAYHETHAN"));
					rowData.add(resultSet.getString("NHACUNGCAP_REF.MANCC"));
					rowData.add(resultSet.getString("NHACUNGCAP_REF.TENNCC"));
					view.dtm.addRow(rowData);
				}
			}
			catch (SQLException ex)
			{
				JOptionPane.showMessageDialog(null, ex);
				Logger.getLogger(StablesController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public void CapNhatThuoc(String mathuoc, String tenthuoc, String thanhphan, String chidinh, String donvi,
			String conlai, String mancc, String datensx, String datenhh)
	{
		try
		{
			String query = "update thuoc set tenthuoc = '" + tenthuoc + "', " + "thanhphan = '" + thanhphan + "', "
					+ "chidinh = '" + chidinh + "', " + "donvi = '" + donvi + "', " + "conlai = " + conlai + ", "
					+ "NHACUNGCAP_REF = (select REF(ncc) from nhacungcap ncc where mancc = '" + mancc + "'), "
					+ "ngaysanxuat = TO_DATE('" + datensx + "', 'yyyy-mm-dd'), ngayhethan = TO_DATE('" + datenhh
					+ "', 'yyyy-mm-dd') " + "where mathuoc = '" + mathuoc + "'";
			WarehouseManagerModel.editData(query);
			JOptionPane.showMessageDialog(null, "Cập nhật thành công");
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Cập nhật không thành công");
		}

	}

	public void CapNhatThucAn(String mathucan, String mancc, String maloaithucan, String conlai, String datensx,
			String datenhh)
	{
		try
		{
			String query = "UPDATE thucan SET nhacungcap_ref = (select REF(ncc) from nhacungcap ncc where ncc.mancc = '"
					+ mancc + "'), "
					+ "thucan.LOAITHUCAN_REF = (select REF(lta) from loaithucan lta where lta.maloaithucan = '"
					+ maloaithucan + "'), " + "conlai = " + conlai + ", " + "NGAYSANXUAT = TO_DATE('" + datensx
					+ "', 'yyyy-mm-dd'), " + "ngayhethan = TO_DATE('" + datenhh + "', 'yyyy-mm-dd') "
					+ "where MATHUCAN = '" + mathucan + "' ";
			WarehouseManagerModel.editData(query);
			JOptionPane.showMessageDialog(null, "Cập nhật thành công");
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Cập nhật không thành công");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String _command = e.getActionCommand();

		// Radio button
		// Chọn thuốc
		if (WarehouseManagerView.DRUG_SELECTED_COMMAND.equals(_command))
		{
			view._txtfMaLoaiThucAn.setEditable(false);
			view._btnChonThucAn.setEnabled(false);
			LoadThuocLenTable(0, "");
			return;
		}

		// chọn thức ăn
		if (WarehouseManagerView.FOOD_SELECTED_COMMAND.equals(_command))
		{
			view._txtfMaLoaiThucAn.setEditable(true);
			view._btnChonThucAn.setEnabled(true);
			view._scrollChiDinh.setEnabled(true);
			view._txtaChiDinh.setEditable(true);
			LoadThucAnLenTable(0, "");
			return;
		}

		// Search button
		if (WarehouseManagerView.SEARCH_COMMAND.equals(_command))
		{

			if (view._txtfTimKiem.getText().equals(""))
			{
				if (view._rdbtThuoc.isSelected())
				{
					LoadThuocLenTable(0, "");
				}
				else
				{
					LoadThucAnLenTable(0, "");
				}
			}
			else
			{
				if (view._rdbtThuoc.isSelected())
				{
					LoadThuocLenTable(1, view._txtfTimKiem.getText());
				}
				else
				{
					LoadThucAnLenTable(1, view._txtfTimKiem.getText());
				}
			}
			return;
		}

		// select provider button
		if (WarehouseManagerView.SELECT_PROVIDER_COMMAND.equals(_command))
		{
			ProviderManagerController pmc = new ProviderManagerController();
			ProviderManagerView pmv = new ProviderManagerView();
			ProviderManagerModel pmm = new ProviderManagerModel();
			pmv.setController(pmc);
			pmc.setView(pmv);
			pmc.setModel(pmm);
			pmc.setWatcher(this);
			pmv.showUp();

			return;
		}

		// select food button
		if (WarehouseManagerView.SELECT_FOOD_COMMAND.equals(_command))
		{
			FoodView fv = new FoodView();
			FoodModel fm = new FoodModel();
			FoodController fc = new FoodController();

			fv.setController(fc);
			fc.setView(fv);
			fc.setModel(fm);
			fc.setWatcher(this);
			fv.showUp();

			return;
		}

		// add button
		if (WarehouseManagerView.ADD_COMMAND.equals(_command))
		{

			String datensx = formatdate.format(view._dateNSX.getDate());
			String datenhh = formatdate.format(view._dateNHH.getDate());

			if (view._rdbtThuoc.isSelected())
			{
				db.saveAutoId(Entity.idGenner("THUOC", "MATHUOC", "Thuoc_objtyp",
						"Thuoc_objtyp('111', '" + view._txtfTen.getText() + "','" + view._txtaThanhPhan.getText()
								+ "','" + view._txtaChiDinh.getText() + "','" + view._txtfDonVi.getText() + "', "
								+ view._txtfConLai.getText() + ",null," + "TO_DATE('" + datensx + "', 'yyyy-mm-dd'),"
								+ "TO_DATE('" + datenhh + "', 'yyyy-mm-dd'))",
						new RefPayload("NhaCungCap", "MaNCC", "" + view._txtfMaNCC.getText() + "", "NhaCungCap_ref")));
				JOptionPane.showMessageDialog(null, "Thêm thành công");
				LoadThuocLenTable(0, "");
			}
			else
			{
				List<RefPayload> ref = new ArrayList<>();
				ref.add(new RefPayload("NhaCungCap", "MaNCC", "" + view._txtfMaNCC.getText() + "", "NhaCungCap_ref"));
				ref.add(new RefPayload("LoaiThucAn", "MaLoaiThucAn", "" + view._txtfMaLoaiThucAn.getText() + "",
						"LoaiThucAn_ref"));
				db.saveAutoId(
						Entity.idGenner("THUCAN", "MATHUCAN", "ThucAn_objtyp",
								"ThucAn_objtyp('111', null, null, " + view._txtfConLai.getText() + "," + "TO_DATE('"
										+ datensx + "', 'yyyy-mm-dd')," + "TO_DATE('" + datenhh + "', 'yyyy-mm-dd'))",
								ref));
				JOptionPane.showMessageDialog(null, "Thêm thành công");
				LoadThucAnLenTable(0, "");
			}

			return;
		}

		// remove button
		if (WarehouseManagerView.REMOVE_COMMAND.equals(_command))
		{
			int row;
			if (view._rdbtThuoc.isSelected())
			{
				row = view._tbKho.getSelectedRow();
				if (row == -1)
				{
					JOptionPane.showMessageDialog(null, "Chọn dòng muốn xóa");
				}
				String id = view._tbKho.getModel().getValueAt(row, 0).toString();

				WarehouseManagerModel.editData("DELETE FROM THUOC WHERE MATHUOC = '" + id + "'");
				JOptionPane.showMessageDialog(null, "Xóa thành công");
				LoadThuocLenTable(0, "");
			}
			else
			{
				row = view._tbKho.getSelectedRow();
				if (row == -1)
				{
					JOptionPane.showMessageDialog(null, "Chọn dòng muốn xóa");
				}
				String id = view._tbKho.getModel().getValueAt(row, 0).toString();
				WarehouseManagerModel.editData("DELETE FROM THUCAN WHERE MATHUCAN = '" + id + "'");
				JOptionPane.showMessageDialog(null, "Xóa thành công");
				LoadThucAnLenTable(0, "");
			}
			return;
		}

		// update button
		if (WarehouseManagerView.UPDATE_COMMAND.equals(_command))
		{
			int row = -1;
			String datensx = formatdate.format(view._dateNSX.getDate());
			String datenhh = formatdate.format(view._dateNHH.getDate());
			if (view._rdbtThuoc.isSelected())
			{
				row = view._tbKho.getSelectedRow();
				if (row == -1)
				{
					JOptionPane.showMessageDialog(null, "Chọn dòng muốn cập nhật");
				}
				else
				{
					CapNhatThuoc(view._txtfMa.getText(), view._txtfTen.getText(), view._txtaThanhPhan.getText(),
							view._txtaChiDinh.getText(), view._txtfDonVi.getText(), view._txtfConLai.getText(),
							view._txtfMaNCC.getText(), datensx, datenhh);
					LoadThuocLenTable(0, "");
				}

			}
			else
			{
				row = view._tbKho.getSelectedRow();
				if (row == -1)
				{
					JOptionPane.showMessageDialog(null, "Chọn dòng muốn cập nhật");
				}
				else
				{
					CapNhatThucAn(view._txtfMa.getText(), view._txtfMaNCC.getText(), view._txtfMaLoaiThucAn.getText(),
							view._txtfConLai.getText(), datensx, datenhh);
					LoadThucAnLenTable(0, "");
				}
			}
			return;
		}

		// export button
		if (WarehouseManagerView.EXPORT_COMMAND.equals(_command))
		{
			int rows = view.dtmXK.getRowCount();
			for (int row = rows - 1; row >= 0; row--)
			{
				String check = view._tbXuatKho.getModel().getValueAt(row, 0).toString();
				if (check.equals("Thuốc"))
				{
					try
					{
						String query = "update thuoc set conlai = conlai -"
								+ Float.parseFloat(view._tbXuatKho.getModel().getValueAt(row, 3).toString())
								+ " where mathuoc = '" + view._tbXuatKho.getModel().getValueAt(row, 1) + "'";
						db.send(query);

					}
					catch (Exception e2)
					{
						// TODO: handle exception
					}
					LoadThuocLenTable(0, "");
				}
				else
				{
					try
					{
						String query = "update thucan set conlai = conlai -"
								+ Float.parseFloat(view._tbXuatKho.getModel().getValueAt(row, 3).toString())
								+ " where mathucan = '" + view._tbXuatKho.getModel().getValueAt(row, 1) + "'";
						db.send(query);

					}
					catch (Exception e2)
					{
						// TODO: handle exception
					}
					LoadThucAnLenTable(0, "");
				}
				view.dtmXK.removeRow(row);
			}
			JOptionPane.showMessageDialog(null, "Xuất kho thành công");
			return;
		}

		// transfer to rignt button
		if (WarehouseManagerView.TRANSFER_TO_RIGHT_COMMAND.equals(_command))
		{
			int row = view._tbKho.getSelectedRow();
			float _giamconlai = 0;

			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Chọn dòng muốn thao tác");
			}
			else
			{
				float f = Float.parseFloat(view._txtfConLai.getText());

				if (view._rdbtThuoc.isSelected())
				{
					_giamconlai = Float.parseFloat(JOptionPane.showInputDialog(view, "Nhập số lượng"));
					if (f >= _giamconlai && _giamconlai != 0)
					{
						view.dtmXK.addRow(new Object[]
						{ "Thuốc", view._tbKho.getModel().getValueAt(row, 0), view._tbKho.getModel().getValueAt(row, 1),
								_giamconlai, view._tbKho.getModel().getValueAt(row, 4),
								view._tbKho.getModel().getValueAt(row, 7), view._tbKho.getModel().getValueAt(row, 8),
								view._tbKho.getModel().getValueAt(row, 9) });
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Số lượng quá nhỏ hoặc quá lớn");
					}
				}
				else
				{
					_giamconlai = Float.parseFloat(JOptionPane.showInputDialog(view, "Nhập số lượng"));
					if (f >= _giamconlai && _giamconlai != 0)
					{
						JOptionPane.showMessageDialog(null, "ok");
						view.dtmXK.addRow(new Object[]
						{ "Thức ăn", view._tbKho.getModel().getValueAt(row, 0),
								view._tbKho.getModel().getValueAt(row, 2), _giamconlai,
								view._tbKho.getModel().getValueAt(row, 4), view._tbKho.getModel().getValueAt(row, 8),
								view._tbKho.getModel().getValueAt(row, 5), view._tbKho.getModel().getValueAt(row, 6) });
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Số lượng quá nhỏ hoặc quá lớn");
					}
				}
			}

			// cell click
			return;
		}

		// transfer to left button
		if (WarehouseManagerView.TRANSFER_TO_LEFT_COMMAND.equals(_command))
		{
			int row = view._tbXuatKho.getSelectedRow();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Chọn dòng muốn thao tác");
			}
			else
			{
				view.dtmXK.removeRow(row);
			}
			return;
		}
	}

	public void mouseClicked(MouseEvent e)
	{
		// cell click
		int row = view._tbKho.getSelectedRow();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Date dateValue = null;
		Date dateValue1 = null;
		if (view._rdbtThuoc.isSelected())
		{
			view._txtfMa.setText("");
			view._txtfTen.setText("");
			view._txtaThanhPhan.setText("");
			view._txtaChiDinh.setText("");
			view._txtfDonVi.setText("");
			view._txtfConLai.setText("");
			view._txtfMaNCC.setText("");
			view._txtfMaLoaiThucAn.setText("");

			try
			{
				dateValue = date.parse(view._tbKho.getModel().getValueAt(row, 8).toString());
				dateValue1 = date.parse(view._tbKho.getModel().getValueAt(row, 9).toString());
				view._dateNSX.setDate(dateValue);
				view._dateNHH.setDate(dateValue1);
			}
			catch (ParseException ex)
			{
				Logger.getLogger(WarehouseManagerController.class.getName()).log(Level.SEVERE, null, ex);
			}

			view._txtfMa.setText(view._tbKho.getModel().getValueAt(row, 0).toString());
			view._txtfTen.setText(view._tbKho.getModel().getValueAt(row, 1).toString());
			view._txtaThanhPhan.setText(view._tbKho.getModel().getValueAt(row, 2).toString());
			view._txtaChiDinh.setText(view._tbKho.getModel().getValueAt(row, 3).toString());
			view._txtfDonVi.setText(view._tbKho.getModel().getValueAt(row, 4).toString());
			view._txtfConLai.setText(view._tbKho.getModel().getValueAt(row, 5).toString());
			view._txtfMaNCC.setText(view._tbKho.getModel().getValueAt(row, 6).toString());

		}
		else
		{
			view._txtfMa.setText("");
			view._txtfTen.setText("");
			view._txtaThanhPhan.setText("");
			view._txtaChiDinh.setText("");
			view._txtfDonVi.setText("");
			view._txtfConLai.setText("");
			view._txtfMaNCC.setText("");
			view._txtfMaLoaiThucAn.setText("");

			try
			{
				dateValue = date.parse(view._tbKho.getModel().getValueAt(row, 5).toString());
				dateValue1 = date.parse(view._tbKho.getModel().getValueAt(row, 6).toString());
				view._dateNSX.setDate(dateValue);
				view._dateNHH.setDate(dateValue1);
			}
			catch (ParseException ex)
			{
				Logger.getLogger(WarehouseManagerController.class.getName()).log(Level.SEVERE, null, ex);
			}

			view._txtfMa.setText(view._tbKho.getModel().getValueAt(row, 0).toString());
			view._txtfMaLoaiThucAn.setText(view._tbKho.getModel().getValueAt(row, 1).toString());
			view._txtfTen.setText(view._tbKho.getModel().getValueAt(row, 2).toString());
			view._txtfConLai.setText(view._tbKho.getModel().getValueAt(row, 3).toString());
			view._txtfDonVi.setText(view._tbKho.getModel().getValueAt(row, 4).toString());
			view._txtfMaNCC.setText(view._tbKho.getModel().getValueAt(row, 7).toString());
		}
	}

	public void mousePressed(MouseEvent e)
	{

	}

	public void mouseReleased(MouseEvent e)
	{

	}

	public void mouseEntered(MouseEvent e)
	{

	}

	public void mouseExited(MouseEvent e)
	{

	}

	@Override
	public void beNoticed(String var, int num)
	{
		switch (num)
		{
		case 1:
			view._txtfMaNCC.setText(var);
			break;
		case 2:
			view._txtfMaLoaiThucAn.setText(var);
			break;
		}

	}
}
