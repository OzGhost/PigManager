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

import common.RefPayload;
import common.Watcher;
import db.Entity;
import db.db;
import model.BenhAnModel;
import model.FoodModel;
import model.PigsManagerModel;
import model.ProviderManagerModel;
import model.StablesModel;
import view.BenhAnView;
import view.FoodView;
import view.PigsManagerView;
import view.ProviderManagerView;
import view.StablesView;

public class PigsManagerController extends ControllerBase<PigsManagerModel, PigsManagerView>
		implements ActionListener, MouseListener, Watcher
{

	public ResultSet		resultSet	= null;
	public Vector			rowData;
	public SimpleDateFormat	formatdate	= new SimpleDateFormat("yyyy-MM-dd");

	public void LoadHeo(int luachon, String txttimkiem)
	{
		final String[] _columnsTBHeo =
		{ "Ma heo", "Chieu cao", "Chieu dai", "Can nang", "Ngay nuoi", "Ngay ban", "Ma the tai", "Ma nguon",
				"Ma nha cung cap", "Nha cung cap", "Ma loai thuc an", "Ten loai thuc an", "Ma chuong" };
		view.dtm = new DefaultTableModel(_columnsTBHeo, 0);

		view._tbHeo.setModel(view.dtm);
		if (luachon == 0 && txttimkiem == "")
		{
			resultSet = PigsManagerModel.getData("SELECT h.MAHEO, " + "h.CHIEUCAO, " + "h.CHIEUDAI, " + "h.CANNANG, "
					+ "h.NGAYBATDAUNUOI, " + "h.NGAYKETTHUC, " + "h.MATHETAI, " + "h.NGUON_REF.MAHEO, "
					+ "h.NHACUNGCAP_REF.MANCC, " + "h.NHACUNGCAP_REF.TENNCC, " + "h.LOAITHUCAN_REF.MALOAITHUCAN, "
					+ "h.LOAITHUCAN_REF.TENLOAITHUCAN, " + "h.CHUONG_REF.MACHUONG FROM heo h");
		}
		else
		{
			int checkcbx = view._cbxTimKiem.getSelectedIndex();
			switch (checkcbx)
			{
			case 0:
				resultSet = PigsManagerModel.getData("SELECT h.MAHEO, " + "h.CHIEUCAO, " + "h.CHIEUDAI, "
						+ "h.CANNANG, " + "h.NGAYBATDAUNUOI, " + "h.NGAYKETTHUC, " + "h.MATHETAI, "
						+ "h.NGUON_REF.MAHEO, " + "h.NHACUNGCAP_REF.MANCC, " + "h.NHACUNGCAP_REF.TENNCC, "
						+ "h.LOAITHUCAN_REF.MALOAITHUCAN, " + "h.LOAITHUCAN_REF.TENLOAITHUCAN, "
						+ "h.CHUONG_REF.MACHUONG FROM heo h where h.MAHEO LIKE '%" + txttimkiem
						+ "%' or h.NGUON_REF.MAHEO LIKE '%" + txttimkiem + "%'");
				break;
			case 1:
				resultSet = PigsManagerModel.getData("SELECT h.MAHEO, " + "h.CHIEUCAO, " + "h.CHIEUDAI, "
						+ "h.CANNANG, " + "h.NGAYBATDAUNUOI, " + "h.NGAYKETTHUC, " + "h.MATHETAI, "
						+ "h.NGUON_REF.MAHEO, " + "h.NHACUNGCAP_REF.MANCC, " + "h.NHACUNGCAP_REF.TENNCC, "
						+ "h.LOAITHUCAN_REF.MALOAITHUCAN, " + "h.LOAITHUCAN_REF.TENLOAITHUCAN, "
						+ "h.CHUONG_REF.MACHUONG FROM heo h where h.NHACUNGCAP_REF.TENNCC like '%" + txttimkiem + "%'");
				break;
			case 2:
				resultSet = PigsManagerModel.getData("SELECT h.MAHEO, " + "h.CHIEUCAO, " + "h.CHIEUDAI, "
						+ "h.CANNANG, " + "h.NGAYBATDAUNUOI, " + "h.NGAYKETTHUC, " + "h.MATHETAI, "
						+ "h.NGUON_REF.MAHEO, " + "h.NHACUNGCAP_REF.MANCC, " + "h.NHACUNGCAP_REF.TENNCC, "
						+ "h.LOAITHUCAN_REF.MALOAITHUCAN, " + "h.LOAITHUCAN_REF.TENLOAITHUCAN, "
						+ "h.CHUONG_REF.MACHUONG FROM heo h where h.LOAITHUCAN_REF.TENLOAITHUCAN like '%" + txttimkiem
						+ "%'");
				break;
			}
		}

		if (resultSet != null)
		{
			view.dtm.setRowCount(0);
			try
			{
				while (resultSet.next())
				{
					rowData = new Vector();
					rowData.add(resultSet.getString("MAHEO"));
					rowData.add(resultSet.getString("CHIEUCAO"));
					rowData.add(resultSet.getString("CHIEUDAI"));
					rowData.add(resultSet.getString("CANNANG"));
					rowData.add(resultSet.getDate("NGAYBATDAUNUOI"));
					rowData.add(resultSet.getDate("NGAYKETTHUC"));
					rowData.add(resultSet.getString("MATHETAI"));
					rowData.add(resultSet.getString("NGUON_REF.MAHEO"));
					rowData.add(resultSet.getString("NHACUNGCAP_REF.MANCC"));
					rowData.add(resultSet.getString("NHACUNGCAP_REF.TENNCC"));
					rowData.add(resultSet.getString("LOAITHUCAN_REF.MALOAITHUCAN"));
					rowData.add(resultSet.getString("LOAITHUCAN_REF.TENLOAITHUCAN"));
					rowData.add(resultSet.getString("CHUONG_REF.MACHUONG"));

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

	public void ResetText()
	{
		view._txtfMaHeo.setText("");
		view._txtfChieuCao.setText("");
		view._txtfChieuDai.setText("");
		view._txtfCanNang.setText("");
		view._dateNgayBan.setDate(null);
		view._dateNgayNuoi.setDate(null);
		view._txtfMaNCC.setText("");
		view._txtfMaLoaiThucAn.setText("");
		view._txtfMaChuong.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();

		// Tim kiem
		if (PigsManagerView.SEARCH_COMMAND.equals(cmd))
		{
			if (view._txtfTimKiem.getText().equals(""))
			{
				LoadHeo(0, "");
			}
			else
			{
				LoadHeo(1, view._txtfTimKiem.getText());
			}
			return;
		}

		// Them
		if (PigsManagerView.ADD_COMMAND.equals(cmd))
		{
			List<RefPayload> ref = new ArrayList<>();
			if (!view._cbMaNguon.isSelected())
			{
				if (view._txtfMaNCC.getText() == "" || view._txtfMaLoaiThucAn.getText() == ""
						|| view._txtfMaChuong.getText() == "")
				{
					JOptionPane.showMessageDialog(null, "Thong tin chua day du");
					return;
				}
				ref.add(new RefPayload("NhaCungCap", "MaNCC", "" + view._txtfMaNCC.getText() + "", "NhaCungCap_ref"));
				ref.add(new RefPayload("LOAITHUCAN", "maloaithucan", "" + view._txtfMaLoaiThucAn.getText() + "",
						"loaithucan_ref"));
				ref.add(new RefPayload("Chuong", "Machuong", "" + view._txtfMaChuong.getText() + "", "Chuong_ref"));
			}
			else
			{
				if (view._txtfMaNguon.getText() == "" || view._txtfMaLoaiThucAn.getText() == ""
						|| view._txtfMaChuong.getText() == "")
				{
					JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ");
					return;
				}
				ref.add(new RefPayload("HEO", "Maheo", "" + view._txtfMaNguon.getText() + "", "Nguon_ref"));
				ref.add(new RefPayload("LOAITHUCAN", "maloaithucan", "" + view._txtfMaLoaiThucAn.getText() + "",
						"loaithucan_ref"));
				ref.add(new RefPayload("Chuong", "Machuong", "" + view._txtfMaChuong.getText() + "", "Chuong_ref"));
			}

			try
			{
				String dateNgayNuoi = formatdate.format(view._dateNgayNuoi.getDate());

				db.saveAutoId(Entity.idGenner("HEO", "MAHEO", "Heo_objtyp",
						"Heo_objtyp('123', '" + view._txtfChieuCao.getText() + "'," + " '"
								+ view._txtfChieuDai.getText() + "'," + " '" + view._txtfCanNang.getText()
								+ "', TO_DATE('" + dateNgayNuoi + "', 'yyyy-mm-dd'), null, null, null, null, null, '"
								+ view._txtfMaHeo.getText() + "',LichSuDiChuyen_ntabtyp())",
						ref));
				JOptionPane.showMessageDialog(null, "Them thanh cong");
				LoadHeo(0, "");
				ResetText();
			}
			catch (Exception e2)
			{
				// TODO: handle exception
			}
			return;
		}

		// Cap nhat
		if (PigsManagerView.UPDATE_COMMAND.equals(cmd))
		{
			int row = -1;
			row = view._tbHeo.getSelectedRow();
			String query= "";
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Chon dong muon cap nhat");
				return;
			}
			//check vao ma nguon
			if (!view._cbMaNguon.isSelected())
			{
				query = "update heo set CHIEUCAO = " + view._txtfChieuCao.getText() + ", ChieuDai = "
						+ view._txtfChieuDai.getText() + ", cannang = " + view._txtfCanNang.getText()
						+ ", NHACUNGCAP_REF = (select ref(ncc) from nhacungcap ncc where mancc = '" + view._txtfMaNCC.getText()
						+ "'), loaithucan_ref = (select ref(lta) from loaithucan lta where maloaithucan = '"
						+ view._txtfMaLoaiThucAn.getText()
						+ "'), Chuong_ref = (select ref(c) from chuong c where machuong = '" + view._txtfMaChuong.getText()
						+ "') where maheo = '" + view._txtfMaHeo.getText() + "'";
			}
			else
			{
				query = "update heo set CHIEUCAO = " + view._txtfChieuCao.getText() + ", ChieuDai = "
						+ view._txtfChieuDai.getText() + ", cannang = " + view._txtfCanNang.getText()
						+ ", NGUON_REF = (select ref(h) from heo h where maheo = '" + view._txtfMaNguon.getText()
						+ "'), loaithucan_ref = (select ref(lta) from loaithucan lta where maloaithucan = '"
						+ view._txtfMaLoaiThucAn.getText()
						+ "'), Chuong_ref = (select ref(c) from chuong c where machuong = '" + view._txtfMaChuong.getText()
						+ "') where maheo = '" + view._txtfMaHeo.getText() + "'";				
			}
			
			
			PigsManagerModel.editData(query);
			JOptionPane.showMessageDialog(null, "Cap nhat thanh cong");
			LoadHeo(1, view._txtfTimKiem.getText());
			ResetText();
			return;
		}

		// Xoa
		if (PigsManagerView.REMOVE_COMMAND.equals(cmd))
		{
			int row = view._tbHeo.getSelectedRow();
			if (row < 0)
			{
				JOptionPane.showMessageDialog(null, "Chon dong muon xoa");
			}
			else
			{
				String query = "delete from heo where maheo = '" + view._tbHeo.getModel().getValueAt(row, 0) + "'";
				PigsManagerModel.editData(query);
				JOptionPane.showMessageDialog(null, "Xoa thanh cong");
				LoadHeo(0, "");
				ResetText();
			}
			return;
		}

		// Chon NCC
		if (PigsManagerView.SELECT_PROVIDER_COMMAND.equals(cmd))
		{
			ProviderManagerModel pmm = new ProviderManagerModel();
			ProviderManagerView pmv = new ProviderManagerView();
			ProviderManagerController pmc = new ProviderManagerController();

			pmv.setController(pmc);
			pmc.setView(pmv);
			pmc.setModel(pmm);
			pmc.LoadProvider();
			pmc.setWatcher(this);
			pmv.showUp();

			return;
		}

		// Chon thuc an
		if (PigsManagerView.SELECT_FOOD_COMMAND.equals(cmd))
		{
			FoodModel fm = new FoodModel();
			FoodView fv = new FoodView();
			FoodController fc = new FoodController();

			fv.setController(fc);
			fc.setView(fv);
			fc.setModel(fm);
			fc.setWatcher(this);
			fv.showUp();
			return;
		}

		// Cho an
		if (PigsManagerView.EAT_COMMAND.equals(cmd))
		{
			JOptionPane.showMessageDialog(null, "Chua nang chua hoan thien");
			return;
		}

		// chọn chuồng
		if (PigsManagerView.SELECT_STABLES_COMMAND.equals(cmd))
		{
			StablesModel sm = new StablesModel();
			StablesView sv = new StablesView();
			StablesController sc = new StablesController();

			sv.setController(sc);
			sc.setView(sv);
			sc.setModel(sm);
			sc.setWatcher(this);
			sv.showUp();
		}

		// Chon ma heo
		if (PigsManagerView.SELECT_MAHEO_FROM_OTHER_JFRAME_COMMAND.equals(cmd))
		{
			int row = view._tbHeo.getSelectedRow();
			if (row == -1)
			{
				JOptionPane.showMessageDialog(null, "Can chon heo");
				return;
			}
			String var = view._tbHeo.getModel().getValueAt(row, 0).toString();
			watcher.beNoticed(var, 7);
			view.dispose();
			return;
		}

		// Kham benh
		if (PigsManagerView.KHAMBENH_COMMAND.equals(cmd))
		{
			BenhAnView bav = new BenhAnView();
			BenhAnModel bam = new BenhAnModel();
			BenhAnController bac = new BenhAnController();

			bav.setController(bac);
			bac.setModel(bam);
			bac.setView(bav);
			bav.showUp();

			return;
		}

		super.actionPerformed(e);
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		ResetText();
		int row = view._tbHeo.getSelectedRow();

		view._txtfMaHeo.setText(view._tbHeo.getModel().getValueAt(row, 0).toString());
		view._txtfChieuCao.setText(view._tbHeo.getModel().getValueAt(row, 1).toString());
		view._txtfChieuDai.setText(view._tbHeo.getModel().getValueAt(row, 2).toString());
		view._txtfCanNang.setText(view._tbHeo.getModel().getValueAt(row, 3).toString());
		view._txtfMaTheTai.setText(view._tbHeo.getModel().getValueAt(row, 6).toString());

		view._txtfMaChuong.setText(view._tbHeo.getModel().getValueAt(row, 12).toString());
		view._txtfMaLoaiThucAn.setText(view._tbHeo.getModel().getValueAt(row, 10).toString());

		if (view._tbHeo.getModel().getValueAt(row, 4).toString() != null)
		{
			try
			{
				Date dateNgayNuoi = formatdate.parse(view._tbHeo.getModel().getValueAt(row, 4).toString());
				view._dateNgayNuoi.setDate(dateNgayNuoi);
			}
			catch (ParseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else
			view._dateNgayNuoi.setDate(null);

		if (view._tbHeo.getModel().getValueAt(row, 5).toString() != null)
		{
			try
			{

				Date dateNgayBan = formatdate.parse(view._tbHeo.getModel().getValueAt(row, 5).toString());
				view._dateNgayBan.setDate(dateNgayBan);

			}
			catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			view._dateNgayBan.setDate(null);
		view._txtfMaNguon.setText(view._tbHeo.getModel().getValueAt(row, 7).toString());

		if (view._tbHeo.getModel().getValueAt(row, 7).toString().isEmpty())
		{
			view._txtfMaNCC.setText("");
		}
		else
			view._txtfMaNCC.setText(view._tbHeo.getModel().getValueAt(row, 7).toString());

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
			view._txtfMaNCC.setText(var);
			break;
		case 2:
			view._txtfMaChuong.setText(var);
			break;
		case 3:
			view._txtfMaLoaiThucAn.setText(var);
			break;
		}

	}

}
