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
import model.BenhModel;
import model.CTBenhAnModel;
import view.BenhView;
import view.CTBenhAnView;

public class CTBenhAnController extends ControllerBase<CTBenhAnModel, CTBenhAnView>
		implements MouseListener, ActionListener, Watcher
{

	public ResultSet		rs			= null;
	public Vector			rowData;
	public SimpleDateFormat	formatdate	= new SimpleDateFormat("yyyy-MM-dd");
	public SimpleDateFormat	formatdate2	= new SimpleDateFormat("dd-MMM-yy");

	public void loadData()
	{
		rs = CTBenhAnModel.getData("SELECT ls.benh_ref.mabenh,ls.ngayphatbenh,ls.ngayhetbenh,ls.tinhtrang,ls.ghichu"
				+ " FROM BenhAn b, TABLE (b.chitietbenh_ntab) ls " + " WHERE MABENHAN='" + view.mabenhan + "'");
		if (rs != null)
		{

			CTBenhAnView.dtm.setRowCount(0);
			try
			{
				while (rs.next())
				{
					rowData = new Vector();
					rowData.add(rs.getString("benh_ref.mabenh"));
					rowData.add(rs.getString("ngayphatbenh"));
					rowData.add(rs.getString("ngayhetbenh"));
					rowData.add(rs.getString("tinhtrang"));
					rowData.add(rs.getString("ghichu"));
					CTBenhAnView.dtm.addRow(rowData);
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
		// search button event
		if (CTBenhAnView.SEARCH_COMMAND.equals(cmd))
		{
			if (view.txtTimKiem.getText().isEmpty())
				rs = CTBenhAnModel.getData("SELECT ls.benh_ref.mabenh,ls.ngayphatbenh,ls.ngayhetbenh,ls.tinhtrang,ls.ghichu"
						+ " FROM BenhAn b, TABLE (b.chitietbenh_ntab) ls " + " WHERE MABENHAN='" + view.mabenhan + "'");
			else
			{
				switch (view.cbxTimKiem.getSelectedItem().toString())
				{
				case "Ma benh":
				{
					rs = CTBenhAnModel.getData("SELECT ls.benh_ref.mabenh,ls.ngayphatbenh,ls.ngayhetbenh,ls.tinhtrang,ls.ghichu"
							+ " FROM BenhAn b, TABLE (b.chitietbenh_ntab) ls " + " WHERE MABENHAN='" + view.mabenhan
							+ "'" + " and ls.benh_ref.mabenh like '%" + view.txtTimKiem.getText() + "%'");
					break;
				}
				case "Tinh trang":
				{
					String TinhTrang;
					switch (view.cbxTinhTrang.getSelectedItem().toString())
					{
					case "Chua chua tri":
					{
						TinhTrang = "CCT";
						break;
					}
					case "Dang chua tri":
					{
						TinhTrang = "DCT";
						break;
					}
					case "Khong chua duoc":
					{
						TinhTrang = "KCD";
						break;
					}
					default:
					{
						TinhTrang = "DCK";
						break;
					}
					}
					rs = CTBenhAnModel.getData("SELECT ls.benh_ref.mabenh,ls.ngayphatbenh,ls.ngayhetbenh,ls.tinhtrang,ls.ghichu"
							+ " FROM BenhAn b, TABLE (b.chitietbenh_ntab) ls " + " WHERE MABENHAN='" + view.mabenhan
							+ "'" + " and ls.tinhtrang = '" + TinhTrang + "'");
					break;
				}
				}
				// push data
				if (rs != null)
				{

					CTBenhAnView.dtm.setRowCount(0);
					try
					{
						while (rs.next())
						{
							rowData = new Vector();
							rowData.add(rs.getString("benh_ref.mabenh"));
							rowData.add(rs.getString("ngayphatbenh"));
							rowData.add(rs.getString("ngayhetbenh"));
							rowData.add(rs.getString("tinhtrang"));
							rowData.add(rs.getString("ghichu"));
							CTBenhAnView.dtm.addRow(rowData);
						}
					}
					catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			return;
		}
		
		// insert button event
		if (CTBenhAnView.INSERT_COMMAND.equals(cmd))
		{
			if (view.dateNgayPhatHien.getDate() == null || view.txtMaBenh.getText().isEmpty())
				JOptionPane.showMessageDialog(null, "Ma benh va ngay phat hien khong duoc de trong!");
			else
			{
				try
				{
					String datePH, dateHB;
					String TinhTrang;
					String query;
					switch (view.cbxTinhTrang.getSelectedItem().toString())
					{
					case "Chua chua tri":
					{
						TinhTrang = "CCT";
						break;
					}
					case "Dang chua tri":
					{
						TinhTrang = "DCT";
						break;
					}
					case "Khong chua duoc":
					{
						TinhTrang = "KCD";
						break;
					}
					default:
					{
						TinhTrang = "DHB";
						break;
					}
					}
					datePH = formatdate2.format(view.dateNgayPhatHien.getDate());
					dateHB = formatdate2.format(view.dateNgayHetBenh.getDate());
					query = "INSERT INTO TABLE ( " + "SELECT ba.ChiTietBenh_ntab " + "FROM BenhAn ba "
							+ "WHERE ba.MaBenhAn = '" + view.mabenhan + "') " + "select ref(b), " + "'" + datePH + "', "
							+ "'" + dateHB + "', " + "'" + TinhTrang + "', " + "'" + view.txtGhiChu.getText() + "' "
							+ "from benh b where b.mabenh='" + view.txtMaBenh.getText() + "'";
					db.send(query);
					JOptionPane.showMessageDialog(null, "Them thanh cong");
					loadData();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Qua trinh them xay ra loi");
				}
			}
			return;
		}
		
		
		// delete button event
		if (CTBenhAnView.DELETE_COMMAND.equals(cmd))
		{
			int row;
			row = view.tbCTBenh.getSelectedRowCount();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Hay chon dong can xoa!");
			}
			else
			{
				try
				{
					String datePH;
					datePH = formatdate2.format(view.dateNgayPhatHien.getDate());
					String query = "DELETE FROM TABLE ( " + "SELECT b.ChiTietBenh_ntab " + "FROM BenhAn b "
							+ "where b.mabenhan='" + view.mabenhan + "') t " + "Where t.NgayPhatBenh='" + datePH + "' "
							+ "and t.benh_ref.mabenh='" + view.txtMaBenh.getText() + "'";
					db.send(query);
					JOptionPane.showMessageDialog(null, "Da xoa thanh cong!");
					loadData();
				}
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Xay ra loi!");
				}
			}
		}

		// choose button
		if (CTBenhAnView.CHOOSE_COMMAND.equals(cmd))
		{
			BenhView bv = new BenhView();
			BenhModel bm = new BenhModel();
			BenhController bc = new BenhController();

			bv.setController(bc);
			bc.setView(bv);
			bc.setModel(bm);
			bc.loadData();
			bc.setWatcher(this);
			bv.btnChonBenh.setEnabled(true);
			bv.showUp();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		int row = view.tbCTBenh.getSelectedRow();
		Date dateValue, dateValue2 = null;
		String TinhTrang;
		
		view.txtMaBenh.setText((String) view.tbCTBenh.getModel().getValueAt(row, 0));
		view.txtGhiChu.setText((String) view.tbCTBenh.getModel().getValueAt(row, 4));
		try
		{
			dateValue = formatdate.parse(view.tbCTBenh.getModel().getValueAt(row, 1).toString());
			view.dateNgayPhatHien.setDate(dateValue);
			dateValue2 = formatdate.parse(view.tbCTBenh.getModel().getValueAt(row, 2).toString());
			view.dateNgayHetBenh.setDate(dateValue2);
		}
		catch (ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TinhTrang = view.tbCTBenh.getModel().getValueAt(row, 3).toString();
		switch (TinhTrang)
		{
		case "CCT":
		{
			view.cbxTinhTrang.setSelectedIndex(0);
			break;
		}
		case "DCT":
		{
			view.cbxTinhTrang.setSelectedIndex(1);
			break;
		}
		case "KCD":
		{
			view.cbxTinhTrang.setSelectedIndex(2);
			break;
		}
		case "DHB":
		{
			view.cbxTinhTrang.setSelectedIndex(3);
			break;
		}
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
		case 6:
		{
			view.txtMaBenh.setText(var);
			break;
		}
		}

	}

}
