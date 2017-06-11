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

import javax.swing.JOptionPane;

import common.RefPayload;
import common.Watcher;
import db.Entity;
import db.db;
import model.LichSuThaiKyModel;
import model.ProviderManagerModel;
import model.ThaTinhModel;
import view.LichSuThaiKyView;
import view.ProviderManagerView;
import view.ThaTinhView;

public class ThaTinhController extends ControllerBase<ThaTinhModel, ThaTinhView>
		implements MouseListener, ActionListener, Watcher
{

	public ResultSet		rs			= null;
	public Vector			rowData;
	public SimpleDateFormat	formatdate	= new SimpleDateFormat("yyyy-MM-dd");

	public void loaddata()
	{
		rs = ThaTinhModel.getData("select T.MATINH, T.TENTINH, T.NGUONGOC, T.DACDIEM, "
				+ "T.NHACUNGCAP_REF.MANCC, T.NGAYSANXUAT, T.NGAYHETHAN, T.LICHSUTHAIKY_REF.MALSTK " + "FROM TINH T");
		if (rs != null)
		{

			ThaTinhView.dtm.setRowCount(0);
			try
			{
				while (rs.next())
				{
					rowData = new Vector();
					rowData.add(rs.getString("MATINH"));
					rowData.add(rs.getString("TENTINH"));
					rowData.add(rs.getString("NGUONGOC"));
					rowData.add(rs.getString("DACDIEM"));
					rowData.add(rs.getString("NHACUNGCAP_REF.MANCC"));
					rowData.add(rs.getDate("NGAYSANXUAT"));
					rowData.add(rs.getDate("NGAYHETHAN"));
					rowData.add(rs.getString("LICHSUTHAIKY_REF.MALSTK"));
					ThaTinhView.dtm.addRow(rowData);
				}
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();
		// search event button
		if (ThaTinhView.SEARCH_COMMAND.equals(cmd))
		{
			if (view == null)
			{
				System.out.println("view not ready");
				return;
			}
			if (view.txtTimKiem == null)
			{
				System.out.println("text file not initialed");
				return;
			}
			if (view.txtTimKiem.getText() == null || view.txtTimKiem.getText().isEmpty())
				rs = ThaTinhModel.getData("select T.MATINH, T.TENTINH, T.NGUONGOC, T.DACDIEM, "
						+ "T.NHACUNGCAP_REF.MANCC, T.NGAYSANXUAT, T.NGAYHETHAN, T.LICHSUTHAIKY_REF.MALSTK "
						+ "FROM TINH T");
			else
			{
				switch (view.cbxTimKiem.getSelectedItem().toString())
				{
				case "Mã tinh":
				{
					rs = ThaTinhModel.getData("select T.MATINH, T.TENTINH, T.NGUONGOC, T.DACDIEM,"
							+ " T.NHACUNGCAP_REF.MANCC, T.NGAYSANXUAT, T.NGAYHETHAN, T.LICHSUTHAIKY_REF.MALSTK "
							+ "from TINH T" + " WHERE T.MATINH LIKE '%" + view.txtTimKiem.getText() + "%'");
					break;
				}
				case "Tên tinh":
				{
					rs = ThaTinhModel.getData("select T.MATINH, T.TENTINH, T.NGUONGOC, T.DACDIEM,"
							+ " T.NHACUNGCAP_REF.MANCC, T.NGAYSANXUAT, T.NGAYHETHAN, T.LICHSUTHAIKY_REF.MALSTK "
							+ "from TINH T" + " WHERE T.TENTINH LIKE '%" + view.txtTimKiem.getText() + "%'");
					break;
				}
				case "Nguồn gốc":
				{
					rs = ThaTinhModel.getData("select T.MATINH, T.TENTINH, T.NGUONGOC, T.DACDIEM,"
							+ " T.NHACUNGCAP_REF.MANCC, T.NGAYSANXUAT, T.NGAYHETHAN, T.LICHSUTHAIKY_REF.MALSTK "
							+ "from TINH T" + " WHERE T.NGUONGOC LIKE '%" + view.txtTimKiem.getText() + "%'");
					break;
				}
				case "Mã nhà cung cấp":
				{
					rs = ThaTinhModel.getData("select T.MATINH, T.TENTINH, T.NGUONGOC, T.DACDIEM,"
							+ " T.NHACUNGCAP_REF.MANCC, T.NGAYSANXUAT, T.NGAYHETHAN, T.LICHSUTHAIKY_REF.MALSTK "
							+ "from TINH T" + " WHERE T.NHACUNGCAP_REF.MANCC LIKE '%" + view.txtTimKiem.getText()
							+ "%'");
					break;
				}
				}
			}
			// push data on table
			if (rs != null)
			{

				ThaTinhView.dtm.setRowCount(0);
				try
				{
					while (rs.next())
					{
						rowData = new Vector();
						rowData.add(rs.getString("MATINH"));
						rowData.add(rs.getString("TENTINH"));
						rowData.add(rs.getString("NGUONGOC"));
						rowData.add(rs.getString("DACDIEM"));
						rowData.add(rs.getString("NHACUNGCAP_REF.MANCC"));
						rowData.add(rs.getDate("NGAYSANXUAT"));
						rowData.add(rs.getDate("NGAYHETHAN"));
						rowData.add(rs.getString("LICHSUTHAIKY_REF.MALSTK"));
						ThaTinhView.dtm.addRow(rowData);
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
		// add button event
		if (ThaTinhView.INSERT_COMMAND.equals(cmd))
		{
			if (view.txtTenTinh.getText().isEmpty() || view.txtNguonGoc.getText().isEmpty()
					|| view.txtDacDiem.getText().isEmpty() || view.txtNguonGoc.getText().isEmpty()
					|| view.dateNgaySX.getDate() == null || view.dateNgayHH.getDate() == null)
				JOptionPane.showMessageDialog(null, "Bạn cần phải điền đầy đủ các thông tin cần thiết!");
			else
			{
				try
				{
					String dateHH, dateSX;
					dateHH = formatdate.format(view.dateNgayHH.getDate());
					dateSX = formatdate.format(view.dateNgaySX.getDate());
					List<RefPayload> ref = new ArrayList<>();
					ref.add(new RefPayload("NhaCungCap", "MaNCC", "" + ThaTinhView.txtMaNCC.getText() + "", "NhaCungCap_ref"));
					ref.add(new RefPayload("lichsuthaiky", "malstk", "" + ThaTinhView.txtMaLS.getText() + "",
							"lichsuthaiky_ref"));
					db.saveAutoId(Entity.idGenner("TINH", "MATINH", "Tinh_objtyp",
							"Tinh_objtyp('111', '" + view.txtTenTinh.getText() + "', '" + view.txtNguonGoc.getText()
									+ "','" + view.txtDacDiem.getText() + "', null," + "TO_DATE('" + dateHH
									+ "', 'yyyy-mm-dd')," + "TO_DATE('" + dateSX + "', 'yyyy-mm-dd'), null)",
							ref));
					JOptionPane.showMessageDialog(null, "Thêm thành công");
					loaddata();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Quá trình thêm xảy ra lỗi");
				}
			}
			return;
		}
		// update button event
		if (ThaTinhView.UPDATE_COMMAND.equals(cmd))
		{
			int row = -1;
			String datensx = formatdate.format(view.dateNgaySX.getDate());
			String datenhh = formatdate.format(view.dateNgayHH.getDate());
			row = view.tbThaTinh.getSelectedRow();
			String id = view.tbThaTinh.getModel().getValueAt(row, 0).toString();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Hãy chọn dòng cần chỉnh sửa");
			}
			else
			{

				String query = "update tinh set tentinh = '" + view.txtTenTinh.getText() + "', " + "nguongoc = '"
						+ view.txtNguonGoc.getText() + "', " + "dacdiem = '" + view.txtDacDiem.getText() + "', "
						+ "NHACUNGCAP_REF = (select REF(ncc) from nhacungcap ncc where mancc = '"
						+ ThaTinhView.txtMaNCC.getText() + "'), " + "ngaysanxuat = TO_DATE('" + datensx
						+ "', 'yyyy-mm-dd'), ngayhethan = TO_DATE('" + datenhh + "', 'yyyy-mm-dd'), "
						+ "LICHSUTHAIKY_REF = (select REF(lstk) from lichsuthaiky lstk where malstk = '"
						+ ThaTinhView.txtMaLS.getText() + "')" + " WHERE MATINH ='" + id + "'";
				ThaTinhModel.editData(query);
				JOptionPane.showMessageDialog(null, "Cập nhật thành công");
				loaddata();
			}
			return;
		}

		// delete button event
		if (ThaTinhView.DELETE_COMMAND.equals(cmd))
		{
			int row = view.tbThaTinh.getSelectedRow();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Hãy chọn dòng cần xóa!");
			}
			else
			{
				db.send("delete from tinh where matinh =" + view.tbThaTinh.getModel().getValueAt(row, 0).toString());
				JOptionPane.showMessageDialog(null, "Đã xóa thành công!");
				loaddata();

			}
			return;
		}

		// select provider button event
		if (ThaTinhView.SELECT_PROVIDER_COMMAND.equals(cmd))
		{
			ProviderManagerView pmv = new ProviderManagerView();
			ProviderManagerModel pmm = new ProviderManagerModel();
			ProviderManagerController pmc = new ProviderManagerController();

			pmv.setController(pmc);
			pmc.setView(pmv);
			pmc.setModel(pmm);
			pmc.setWatcher(this);
			pmv.showUp();
			return;

		}

		// select history

		if (ThaTinhView.SELECT_HISTORY_COMMAND.equals(cmd))
		{

			LichSuThaiKyView lsv = new LichSuThaiKyView();
			LichSuThaiKyModel lsm = new LichSuThaiKyModel();
			LichSuThaiKyController lsc = new LichSuThaiKyController();

			lsv.setController(lsc);
			lsc.setView(lsv);
			lsc.setModel(lsm);
			lsc.setWatcher(this);
			lsv.btnChonLS.setEnabled(true);
			lsv.showUp();
			return;

		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		Date dateValue1 = null;
		Date dateValue2 = null;

		int row = view.tbThaTinh.getSelectedRow();
		view.txtMaTinh.setText((String) view.tbThaTinh.getModel().getValueAt(row, 0));
		view.txtTenTinh.setText((String) view.tbThaTinh.getModel().getValueAt(row, 1));
		view.txtNguonGoc.setText((String) view.tbThaTinh.getModel().getValueAt(row, 2));
		view.txtDacDiem.setText((String) view.tbThaTinh.getModel().getValueAt(row, 3));
		ThaTinhView.txtMaNCC.setText((String) view.tbThaTinh.getModel().getValueAt(row, 4));
		ThaTinhView.txtMaLS.setText((String) view.tbThaTinh.getModel().getValueAt(row, 7));

		try
		{
			dateValue1 = formatdate.parse(view.tbThaTinh.getModel().getValueAt(row, 5).toString());
			view.dateNgaySX.setDate(dateValue1);
			dateValue2 = formatdate.parse(view.tbThaTinh.getModel().getValueAt(row, 6).toString());
			view.dateNgayHH.setDate(dateValue2);
		}
		catch (ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
		case 1:
			ThaTinhView.txtMaNCC.setText(var);
			break;
		case 4:
			ThaTinhView.txtMaLS.setText(var);
			break;
		}

	}

}
