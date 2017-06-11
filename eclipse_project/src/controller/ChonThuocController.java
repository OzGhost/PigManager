package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.ChonThuocModel;
import view.ChonThuocView;

public class ChonThuocController extends ControllerBase<ChonThuocModel, ChonThuocView>
		implements MouseListener, ActionListener
{
	public ResultSet	rs	= null;
	public Vector		rowData;

	public void loadData()
	{
		rs = ChonThuocModel.getData("SELECT mathuoc,tenthuoc,thanhphan" + " FROM thuoc");
		if (rs != null)
		{

			ChonThuocView.dtm.setRowCount(0);
			try
			{
				while (rs.next())
				{
					rowData = new Vector();
					rowData.add(rs.getString("mathuoc"));
					rowData.add(rs.getString("tenthuoc"));
					rowData.add(rs.getString("thanhphan"));
					ChonThuocView.dtm.addRow(rowData);
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
		if (ChonThuocView.SEARCH_COMMAND.equals(e.getActionCommand()))
		{
			if (view.txtTimKiem.getText().isEmpty())
				rs = ChonThuocModel.getData("SELECT mathuoc,tenthuoc,thanhphan FROM thuoc ");
			else
			{
				switch (view.cbxTimKiem.getSelectedItem().toString())
				{
				case "Mã thuốc":
				{
					rs = ChonThuocModel.getData("SELECT mathuoc,tenthuoc,thanhphan FROM thuoc"
							+ " WHERE mathuoc LIKE '%" + view.txtTimKiem.getText() + "%'");
					break;
				}
				case "Tên thuốc":
				{
					rs = ChonThuocModel.getData("SELECT mathuoc,tenthuoc,thanhphan FROM thuoc"
							+ " WHERE tenthuoc LIKE '%" + view.txtTimKiem.getText() + "%'");
					break;
				}
				}
				// push data
				if (rs != null)
				{

					ChonThuocView.dtm.setRowCount(0);
					try
					{
						while (rs.next())
						{
							rowData = new Vector();
							rowData.add(rs.getString("mathuoc"));
							rowData.add(rs.getString("tenthuoc"));
							rowData.add(rs.getString("thanhphan"));
							ChonThuocView.dtm.addRow(rowData);
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

		if (ChonThuocView.SELECT_COMMAND.equals(e.getActionCommand()))
		{
			int row;
			row = view.tbChonThuoc.getSelectedRow();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Hãy chọn một dòng!");
				return;
			}
			String var = view.tbChonThuoc.getModel().getValueAt(row, 0).toString();
			watcher.beNoticed(var, 5);
			view.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

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
}
