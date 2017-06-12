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

import common.Constants;
import common.RefPayload;
import db.Entity;
import db.db;
import model.BenhAnModel;
import view.BenhAnView;
import view.CTBenhAnView;
import view.DungThuocView;

public class BenhAnController extends ControllerBase<BenhAnModel, BenhAnView> implements MouseListener, ActionListener
{
	public ResultSet		rs			= null;
	public Vector			rowData;
	public SimpleDateFormat	formatdate	= new SimpleDateFormat("yyyy-MM-dd");

	public void loadData()
	{
		rs = BenhAnModel.getData("SELECT BA.MABENHAN,BA.HEO_REF.MAHEO,BA.NGAYTAO FROM BENHAN BA ");
		if (rs != null)
		{

			BenhAnView.dtm.setRowCount(0);
			try
			{
				while (rs.next())
				{
					rowData = new Vector();
					rowData.add(rs.getString("MABENHAN"));
					rowData.add(rs.getString("HEO_REF.MAHEO"));
					rowData.add(rs.getDate("NGAYTAO"));
					BenhAnView.dtm.addRow(rowData);
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
		// search event button
		if (BenhAnView.SEARCH_COMMAND.equals(cmd))
		{
			if (view.txtTimKiem.getText().isEmpty())
				rs = BenhAnModel.getData("SELECT BA.MABENHAN,BA.HEO_REF.MAHEO,BA.NGAYTAO FROM BENHAN BA ");
			else
			{
				switch (view.cbxTimKiem.getSelectedItem().toString())
				{
				case "Ma benh an":
				{
					rs = BenhAnModel.getData("SELECT BA.MABENHAN,BA.HEO_REF.MAHEO,BA.NGAYTAO FROM BENHAN BA"
							+ " WHERE BA.MABENHAN LIKE '%" + view.txtTimKiem.getText() + "%'");
					break;
				}
				}
				// pushdata
				if (rs != null)
				{

					BenhAnView.dtm.setRowCount(0);
					try
					{
						while (rs.next())
						{
							rowData = new Vector();
							rowData.add(rs.getString("MABENHAN"));
							rowData.add(rs.getString("HEO_REF.MAHEO"));
							rowData.add(rs.getDate("NGAYTAO"));
							BenhAnView.dtm.addRow(rowData);
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

		// insert event button
		if (BenhAnView.INSERT_COMMAND.equals(cmd))
		{
			if (view.dateNgayTao.getDate() == null)
				JOptionPane.showMessageDialog(null, "Ban can phai dien day du cac thong tin can thiet!");
			else
			{
				try
				{
					String dateNgayTao;
					dateNgayTao = formatdate.format(view.dateNgayTao.getDate());
					db.saveAutoId(Entity.idGenner("BENHAN", "MABENHAN", "BenhAn_objtyp",
							"BenhAn_objtyp('111', null, TO_DATE('" + dateNgayTao
									+ "','yyyy-mm-dd'), LichSuDungThuoc_ntabtyp(), ChiTietBenh_ntabtyp())",
							new RefPayload("HEO", "MAHEO", "201701029382", "HEO_ref")));
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

		// delete event button
		if (BenhAnView.DELETE_COMMAND.equals(cmd))
		{
			int row = view.tbBenhAn.getSelectedRow();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Ban chua chon dong");
			}
			else
			{
				String query = "DELETE FROM BENHAN WHERE MABENHAN = '"
						+ view.tbBenhAn.getModel().getValueAt(row, 0).toString() + "' ";
				BenhAnModel.editData(query);
				JOptionPane.showMessageDialog(null, "Xoa thanh cong!");
				loadData();
			}
			return;
		}

		// update event button
		if (BenhAnView.UPDATE_COMMAND.equals(cmd))
		{
			int row = -1;
			String datensx = formatdate.format(view.dateNgayTao.getDate());
			row = view.tbBenhAn.getSelectedRow();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Hay chon 1 dong!");
			}
			else
			{
				String query = "update BENHAN set HEO_REF = (select REF(h) from heo h where h.MAHEO='"
						+ view.txtMaSoHeo.getText() + "'), NGAYTAO = TO_DATE('" + datensx
						+ "', 'yyyy-mm-dd') where MABENHAN ='" + view.txtMaBenhAn.getText() + "'";
				db.send(query);
				JOptionPane.showMessageDialog(null, "Da cap nhat!");
				loadData();
			}
			return;
		}

		// watch detail
		if (BenhAnView.DETAIL_COMMAND.equals(cmd))
		{
			int row;
			row = view.tbBenhAn.getSelectedRow();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Hay chon 1 benh an!");
			}
			else
			{
				CTBenhAnView ctv = new CTBenhAnView(view.txtMaBenhAn.getText());
				CTBenhAnController ctc = new CTBenhAnController();
				ctv.setController(ctc);
				ctc.setView(ctv);
				ctc.loadData();
				ctv.showUp();

			}

			return;
		}

		// use pain
		if (BenhAnView.DRUG_COMMAND.equals(cmd))
		{
			int row;
			row = view.tbBenhAn.getSelectedRow();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Hay chon 1 benh an!");
			}
			else
			{
				DungThuocView dtv = new DungThuocView(view.txtMaBenhAn.getText());
				DungThuocController dtc = new DungThuocController();
				dtv.setController(dtc);
				dtc.setView(dtv);
				dtc.loadData();
				dtv.showUp();

			}

			return;
		}
		
		//Trang chu
		if (Constants.AC_HOME.equals(cmd))
		{
			return;
		}
		
		super.actionPerformed(e);

	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		Date dateValue = null;

		int row = view.tbBenhAn.getSelectedRow();
		view.txtMaBenhAn.setText((String) view.tbBenhAn.getModel().getValueAt(row, 0));
		view.txtMaSoHeo.setText((String) view.tbBenhAn.getModel().getValueAt(row, 1));

		try
		{
			dateValue = formatdate.parse(view.tbBenhAn.getModel().getValueAt(row, 2).toString());
			view.dateNgayTao.setDate(dateValue);
		}
		catch (ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

}
