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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import db.Entity;
import db.db;
import model.FoodModel;
import view.FoodView;

/**
 *
 * @author Dang Nhat Hai Long
 */
public class FoodController extends ControllerBase<FoodModel, FoodView> implements MouseListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String _cmd = e.getActionCommand();
		ResultSet resultSet = null;
		Vector rowData;

		if (FoodView.SEARCH_COMMAND.equals(_cmd))
		{

			if ("".equals(view._txtfTimKiem.getText()))
			{
				resultSet = FoodModel.getData("select * from loaithucan");
			}
			else
			{
				resultSet = FoodModel.getData(
						"SELECT * FROM loaithucan WHERE TENLOAITHUCAN LIKE '%" + view._txtfTimKiem.getText() + "%'");
			}
			if (resultSet != null)
			{
				view.dtm.setRowCount(0);
				try
				{
					while (resultSet.next())
					{
						rowData = new Vector();
						rowData.add(resultSet.getString("MALOAITHUCAN"));
						rowData.add(resultSet.getString("TENLOAITHUCAN"));
						rowData.add(resultSet.getString("MOTA"));
						rowData.add(resultSet.getString("DONVI"));
						rowData.add(resultSet.getString("MUCBAODONG"));
						view.dtm.addRow(rowData);
					}
				}
				catch (SQLException ex)
				{
					JOptionPane.showMessageDialog(null, ex);
					Logger.getLogger(StablesController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			return;
		}

		if (FoodView.ADD_COMMAND.equals(_cmd))
		{
			if ("".equals(view._txtfTenLoai.getText()))
			{
				JOptionPane.showMessageDialog(null, "Thong tin chua du!");
			}
			else
			{
				try
				{
					int mucbaodong = Integer.parseInt(view._txtfMucBaoDong.getText());
					db.saveAutoId(Entity.idGenner("LOAITHUCAN", "MALOAITHUCAN", "LoaiThucAn_objtyp",
							"LoaiThucAn_objtyp('123', '" + view._txtfTenLoai.getText() + "', '"
									+ view._txtaMota.getText() + "', '" + view._txtfDonVi.getText() + "', " + mucbaodong
									+ ")"));
					JOptionPane.showMessageDialog(null, "Them thanh cong!");
				}
				catch (NumberFormatException b)
				{
					JOptionPane.showMessageDialog(null, "Mua bao dong la so");
				}
			}
			return;
		}

		if (FoodView.UPDATE_COMMAND.equals(_cmd))
		{
			int row = view._tbThucAn.getSelectedRow();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Can chon loai thuc an muon cap nhat thong tin");
			}
			else
			{
				int mucbaodong = Integer.parseInt(view._txtfMucBaoDong.getText());
				String _maloaithucan = view._tbThucAn.getModel().getValueAt(row, 0).toString();
				String _query = "update LOAITHUCAN set TENLOAITHUCAN = '" + view._txtfTenLoai.getText() + "', MOTA = '"
						+ view._txtaMota.getText() + "', DONVI = '" + view._txtfDonVi.getText() + "', MUCBAODONG = "
						+ mucbaodong + " WHERE MALOAITHUCAN = " + _maloaithucan;
				db.send(_query);
				JOptionPane.showMessageDialog(null, "Cap nhat thanh cong");
			}
			return;
		}

		// Xóa nhà cung cấp
		if (FoodView.REMOVE_COMMAND.equals(_cmd))
		{
			int row = view._tbThucAn.getSelectedRow();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Can chon loai thuc an muon cap nhat thong tin");
			}
			else
			{
				String _maloaithucan = view._tbThucAn.getModel().getValueAt(row, 0).toString();
				String _query = "DELETE FROM LOAITHUCAN WHERE MALOAITHUCAN =" + _maloaithucan;
				db.send(_query);
				JOptionPane.showMessageDialog(null, "Xoa thanh cong");
			}
			return;
		}

		if (FoodView.SELECT_FOOD_COMMAND.equals(_cmd))
		{
			int row = view._tbThucAn.getSelectedRow();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Chon chon loai thuc an");
				return;
			}
			String var = view._tbThucAn.getModel().getValueAt(row, 0).toString();
			watcher.beNoticed(var, 3);
			view.dispose();

			return;
		}

        super.actionPerformed(e);
	}

	public void mouseClicked(MouseEvent e)
	{
		int row = view._tbThucAn.getSelectedRow();
		view._txtfMaLoai.setText("");
		view._lblTenLoai.setText("");
		view._txtaMota.setText("");

		view._txtfMaLoai.setText(view._tbThucAn.getModel().getValueAt(row, 0).toString());
		view._txtfTenLoai.setText(view._tbThucAn.getModel().getValueAt(row, 1).toString());
		view._txtaMota.setText(view._tbThucAn.getModel().getValueAt(row, 2).toString());
		view._txtfDonVi.setText(view._tbThucAn.getModel().getValueAt(row, 3).toString());
		view._txtfMucBaoDong.setText(view._tbThucAn.getModel().getValueAt(row, 4).toString());

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
}
