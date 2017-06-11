package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

import common.Watcher;
import db.db;
import model.ChonThuocModel;
import model.DungThuocModel;
import view.ChonThuocView;
import view.DungThuocView;

public class DungThuocController extends ControllerBase<DungThuocModel, DungThuocView>
		implements MouseListener, ActionListener, Watcher
{

	public ResultSet		rs			= null;
	public Vector			rowData;
	public SimpleDateFormat	formatdate	= new SimpleDateFormat("yyyy-MM-dd");
	public SimpleDateFormat	formatdate2	= new SimpleDateFormat("dd-MMM-yy");

	public void loadData()
	{
		rs = DungThuocModel.getData("SELECT ls.thuoc_ref.mathuoc,ls.ngaydung,ls.lieu,ls.donvi,ls.hinhthucsudung"
				+ " FROM BenhAn b, TABLE (b.LSdungthuoc_ntab) ls " + " WHERE MABENHAN='" + DungThuocView.mabenhan + "'");
		if (rs != null)
		{

			DungThuocView.dtm.setRowCount(0);
			try
			{
				while (rs.next())
				{
					rowData = new Vector();
					rowData.add(rs.getString("thuoc_ref.mathuoc"));
					rowData.add(rs.getString("ngaydung"));
					rowData.add(rs.getString("lieu"));
					rowData.add(rs.getString("donvi"));
					rowData.add(rs.getString("hinhthucsudung"));
					DungThuocView.dtm.addRow(rowData);
				}
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();
		//
		if (DungThuocView.SEARCH_COMMAND.equals(cmd))
		{
			if (view.txtTimKiem.getText().isEmpty())
				rs = DungThuocModel.getData("SELECT ls.thuoc_ref.mathuoc,ls.ngaydung,ls.lieu,ls.donvi,ls.hinhthucsudung"
						+ " FROM BenhAn b, TABLE (b.LSdungthuoc_ntab) ls " + " WHERE MABENHAN='" + DungThuocView.mabenhan + "'");
			else
			{
				switch (view.cbxTimKiem.getSelectedItem().toString())
				{
				case "Mã thuốc":
				{
					rs = DungThuocModel.getData("SELECT ls.thuoc_ref.mathuoc,ls.ngaydung,ls.lieu,ls.donvi,ls.hinhthucsudung"
							+ " FROM BenhAn b, TABLE (b.LSdungthuoc_ntab) ls " + " WHERE MABENHAN='" + DungThuocView.mabenhan
							+ "'" + " and ls.thuoc_ref.mathuoc like '%" + view.txtTimKiem.getText() + "%'");
					break;
				}
				case "Hình thức sử dụng":
				{
					rs = DungThuocModel.getData("SELECT ls.thuoc_ref.mathuoc,ls.ngaydung,ls.lieu,ls.donvi,ls.hinhthucsudung"
							+ " FROM BenhAn b, TABLE (b.LSdungthuoc_ntab) ls " + " WHERE MABENHAN='" + DungThuocView.mabenhan
							+ "'" + " and ls.hinhthucsudung like '%" + view.txtTimKiem.getText() + "%'");
					break;
				}
				}
			}

			// push data
			if (rs != null)
			{

				DungThuocView.dtm.setRowCount(0);
				try
				{
					while (rs.next())
					{
						rowData = new Vector();
						rowData = new Vector();
						rowData.add(rs.getString("thuoc_ref.mathuoc"));
						rowData.add(rs.getString("ngaydung"));
						rowData.add(rs.getString("lieu"));
						rowData.add(rs.getString("donvi"));
						rowData.add(rs.getString("hinhthucsudung"));
						DungThuocView.dtm.addRow(rowData);
					}
				}
				catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return;
		}
		// insert
		if (DungThuocView.INSERT_COMMAND.equals(cmd))
		{
			if (view.dateNgayDung.getDate() == null || view.txtMaThuoc.getText().isEmpty()
					|| view.txtDonVi.getText().isEmpty() || view.txtLieu.getText().isEmpty())
				JOptionPane.showMessageDialog(null, "Bạn hãy điền vào tất cả những trương cần thiết!");
			else
			{
				try
				{
					String dateD;
					String query;
					int tiem;
					int lieu = Integer.parseInt(view.txtLieu.getText());
					if (view.chkTiem.isSelected())
						tiem = 1;
					else
						tiem = 0;
					dateD = formatdate2.format(view.dateNgayDung.getDate());
					query = "INSERT INTO TABLE ( " + "SELECT ba.LSdungthuoc_ntab " + "FROM BenhAn ba "
							+ "WHERE ba.MaBenhAn = '" + DungThuocView.mabenhan + "') " + "select ref(t), " + "'" + dateD + "', "
							+ "'" + view.txtLieu.getText() + "', " + "'" + view.txtDonVi.getText() + "', " + "'" + tiem
							+ "' " + "from thuoc t where t.mathuoc='" + view.txtMaThuoc.getText() + "'";
					db.send(query);
					JOptionPane.showMessageDialog(null, "Thêm thành công");
					loadData();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Lỗi! Hãy kiểm tra chắc chắn liều là số!");
				}
			}
		}

		// delete
		if (DungThuocView.DELETE_COMMAND.equals(cmd))
		{
			int row;
			row = view.tbDungThuoc.getSelectedRow();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Hãy chọn dòng cần xóa!");
			}
			else
			{
				try
				{
					String dateND;
					dateND = formatdate2.format(view.dateNgayDung.getDate());
					String query = "DELETE FROM TABLE ( " + "SELECT b.LSDungThuoc_ntab " + "FROM BenhAn b "
							+ "where b.mabenhan='" + DungThuocView.mabenhan + "') t " + "Where t.NgayDung='" + dateND + "' "
							+ "and t.thuoc_ref.mathuoc='" + view.txtMaThuoc.getText() + "'";
					db.send(query);
					JOptionPane.showMessageDialog(null, "Đã xóa thành công!");
					loadData();
				}
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Xảy ra lỗi!");
				}
			}
		}

		// choose

		if (DungThuocView.CHOOSE_COMMAND.equals(cmd))
		{
			ChonThuocView ctv = new ChonThuocView();
			ChonThuocModel ctm = new ChonThuocModel();
			ChonThuocController ctc = new ChonThuocController();
			ctv.setController(ctc);
			ctc.setModel(ctm);
			ctc.setView(ctv);
			ctc.setWatcher(this);
			ctv.btnChonThuoc.setEnabled(true);
			ctc.loadData();
			ctv.showUp();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		int row = view.tbDungThuoc.getSelectedRow();
		Date dateValue = null;
		int check = 0;
		view.txtMaThuoc.setText((String) view.tbDungThuoc.getModel().getValueAt(row, 0));
		try
		{
			dateValue = formatdate.parse(view.tbDungThuoc.getModel().getValueAt(row, 1).toString());
			check = Integer.parseInt(view.tbDungThuoc.getModel().getValueAt(row, 4).toString());
			view.dateNgayDung.setDate(dateValue);
			if (check == 1)
				view.chkTiem.setSelected(true);
			else
				view.chkTiem.setSelected(false);
		}
		catch (ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		view.txtLieu.setText((String) view.tbDungThuoc.getModel().getValueAt(row, 2));
		view.txtDonVi.setText((String) view.tbDungThuoc.getModel().getValueAt(row, 3));
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void beNoticed(String var, int num)
	{
		switch (num)
		{
		case 5:
			view.txtMaThuoc.setText(var);
			break;

		}

	}

}
