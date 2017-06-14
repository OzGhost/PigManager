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

import common.Constants;
import common.RefPayload;
import common.Watcher;
import db.Entity;
import db.db;
import model.LichSuThaiKyModel;
import model.PigsManagerModel;
import view.LichSuThaiKyView;
import view.PigsManagerView;

public class LichSuThaiKyController extends ControllerBase<LichSuThaiKyModel, LichSuThaiKyView>
		implements MouseListener, Watcher
{
	public ResultSet		rs			= null;
	public Vector			rowData;
	public SimpleDateFormat	formatdate	= new SimpleDateFormat("yyyy-MM-dd");

	public void loaddata()
	{
		rs = LichSuThaiKyModel
				.getData("SELECT L.MALSTK, L.NGAYGHINHAN, L.NOIDUNG, L.HEO_REF.MAHEO " + "FROM LICHSUTHAIKY L");
		if (rs != null)
		{

			LichSuThaiKyView.dtm.setRowCount(0);
			try
			{
				while (rs.next())
				{
					rowData = new Vector();
					rowData.add(rs.getString("MALSTK"));
					rowData.add(rs.getString("NGAYGHINHAN"));
					rowData.add(rs.getString("NOIDUNG"));
					rowData.add(rs.getString("HEO_REF.MAHEO"));
					LichSuThaiKyView.dtm.addRow(rowData);
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
		if (LichSuThaiKyView.SEARCH_COMMAND.equals(cmd))
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
				rs = LichSuThaiKyModel
						.getData("SELECT L.MALSTK, L.NGAYGHINHAN, L.NOIDUNG, L.HEO_REF.MAHEO " + "FROM LICHSUTHAIKY L");
			else
			{
				switch (view.cbxTimKiem.getSelectedItem().toString())
				{
				case "Ma lich su thai ky":
				{
					rs = LichSuThaiKyModel.getData("SELECT L.MALSTK, L.NGAYGHINHAN, L.NOIDUNG, L.HEO_REF.MAHEO "
							+ "FROM LICHSUTHAIKY L" + " WHERE L.MALSTK LIKE '%" + view.txtTimKiem.getText() + "%'");
					break;
				}
				case "Ma heo":
				{
					rs = LichSuThaiKyModel.getData(
							"SELECT L.MALSTK, L.NGAYGHINHAN, L.NOIDUNG, L.HEO_REF.MAHEO " + "FROM LICHSUTHAIKY L"
									+ " WHERE L.HEO_REF.MAHEO LIKE '%" + view.txtTimKiem.getText() + "%'");
					break;
				}
				}
				return;
			}

			// push data on table
			if (rs != null)
			{

				LichSuThaiKyView.dtm.setRowCount(0);
				try
				{
					while (rs.next())
					{
						rowData = new Vector();
						rowData.add(rs.getString("MALSTK"));
						rowData.add(rs.getString("NGAYGHINHAN"));
						rowData.add(rs.getString("NOIDUNG"));
						rowData.add(rs.getString("HEO_REF.MAHEO"));
						LichSuThaiKyView.dtm.addRow(rowData);
					}
				}
				catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		// delete event button
		if (LichSuThaiKyView.DELETE_COMMAND.equals(cmd))
		{
			int row = view.tbLSTK.getSelectedRow();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Ban chua chon dong");
			}
			else
			{
				String query = "delete from lichsuthaiky where malstk = '"
						+ view.tbLSTK.getModel().getValueAt(row, 0).toString() + "' ";
				LichSuThaiKyModel.editData(query);
				JOptionPane.showMessageDialog(null, "Da xoa thanh cong!");
				loaddata();
			}
			return;
		}

		// update event button
		if (LichSuThaiKyView.UPDATE_COMMAND.equals(cmd))
		{
			int row = -1;
			String datensx = formatdate.format(view.dateNgayGhiNhan.getDate());
			row = view.tbLSTK.getSelectedRow();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Ban chua chon dong");
			}
			else
			{
				String query = "update LICHSUTHAIKY set NOIDUNG ='" + view.txtNoiDung.getText()
						+ "', NGAYGHINHAN = TO_DATE('" + datensx
						+ "', 'yyyy-mm-dd'), HEO_REF = (select REF(h) from heo h where h.MAHEO='"
						+ view.txtMaHeo.getText() + "')  where MALSTK ='" + view.txtMaLSTK.getText() + "'";
				db.send(query);
				JOptionPane.showMessageDialog(null, "Cap nhat thanh cong");
				loaddata();
			}
			return;
		}

		// insert event button
		if (LichSuThaiKyView.INSERT_COMMAND.equals(cmd))
		{

			if (view.txtMaHeo.getText().isEmpty() || view.txtNoiDung.getText().isEmpty()
					|| view.dateNgayGhiNhan.getDate() == null)
				JOptionPane.showMessageDialog(null, "Ban can phai dien day du cac thong tin can thiet!");
			else
			{
				try
				{
					String dateGN;
					dateGN = formatdate.format(view.dateNgayGhiNhan.getDate());
					List<RefPayload> ref = new ArrayList<>();
					ref.add(new RefPayload("heo", "Maheo", "" + view.txtMaHeo.getText() + "", "Heo_ref"));
					db.saveAutoId(Entity.idGenner("lichsuthaiky", "malstk", "LichSuThaiKy_objtyp",
							"LichSuThaiKy_objtyp('111'," + "TO_DATE('" + dateGN + "','yyyy-mm-dd'),'"
									+ view.txtNoiDung.getText() + "', null)",
							ref));
					JOptionPane.showMessageDialog(null, "Them thanh cong");
					loaddata();
					// resetText();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Qua trinh them xay ra loi");
				}
			}
			return;
		}

		// select event button
		if (LichSuThaiKyView.CHOOSE_HISTORY_COMMAND.equals(cmd))
		{
			int row = view.tbLSTK.getSelectedRow();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Can chon nha cung cap");
				return;
			}
			String var = view.tbLSTK.getModel().getValueAt(row, 0).toString();
			watcher.beNoticed(var, 4);
			view.dispose();
			return;
		}

		// select pig
		if (LichSuThaiKyView.SELECT_PIG.equals(cmd))
		{
			PigsManagerView pmv = new PigsManagerView();
			PigsManagerModel pmm = new PigsManagerModel();
			PigsManagerController pmc = new PigsManagerController();

			pmv.setController(pmc);
			pmc.setView(pmv);
			pmc.setModel(pmm);
			pmc.setWatcher(this);
			pmv._btnChonMaHeo.setEnabled(true);
			pmv.showUp();
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
		Date dateValue = null;

		// resetText();

		int row = view.tbLSTK.getSelectedRow();
		view.txtMaLSTK.setText((String) view.tbLSTK.getModel().getValueAt(row, 0));
		view.txtNoiDung.setText((String) view.tbLSTK.getModel().getValueAt(row, 2));
		view.txtMaHeo.setText((String) view.tbLSTK.getModel().getValueAt(row, 3));

		try
		{
			dateValue = formatdate.parse(view.tbLSTK.getModel().getValueAt(row, 1).toString());
			view.dateNgayGhiNhan.setDate(dateValue);
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
		case 7:
			view.txtMaHeo.setText(var);
			break;
		}

	}

}
