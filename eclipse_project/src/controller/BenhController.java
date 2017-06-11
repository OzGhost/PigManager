package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JOptionPane;

import db.Entity;
import db.db;
import model.BenhModel;
import view.BenhView;

public class BenhController extends ControllerBase <BenhModel,BenhView> implements MouseListener,ActionListener
{
	public ResultSet rs = null;
	public Vector rowData;
	public SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
	
	public void loadData()
	{
		rs=BenhModel.getData("SELECT * FROM BENH");
		if(rs!=null){
			
			BenhView.dtm.setRowCount(0);
			try {
				while (rs.next()){
					rowData = new Vector();
					rowData.add(rs.getString("MABENH"));
					rowData.add(rs.getString("TENBENH"));
					rowData.add(rs.getString("MOTATRIEUCHUNG"));
					rowData.add(rs.getString("LOAIBENH"));
					
					BenhView.dtm.addRow(rowData);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();
		
		//search event button
		if(BenhView.SEARCH_COMMAND.equals(cmd))
		{
			if (view.txtTimKiem.getText().isEmpty())
				rs=BenhModel.getData("SELECT * FROM BENH");
			
			else{
				switch(view.cbxTimKiem.getSelectedItem().toString()){
				case "Mã bệnh":{
					rs=BenhModel.getData("SELECT * "
							+ "FROM BENH"
							+ " WHERE MABENH LIKE '%"+view.txtTimKiem.getText()+"%'");
					break;
				}
				case "Tên bệnh":{
					rs=BenhModel.getData("SELCT * "
							+ "FROM BENH"
							+ " WHERE TENBENH LIKE '%"+view.txtTimKiem.getText()+"%'");
					break;
				}
				case "Loại bệnh":{
					rs=BenhModel.getData("SELCT * "
							+ "FROM BENH"
							+ " WHERE LOAIBENH LIKE '%"+view.txtTimKiem.getText()+"%'");
					break;
				}
			}
				return;
		}
		//push data on jtable
		
		if(rs!=null){
			
			BenhView.dtm.setRowCount(0);
			try {
				while (rs.next()){
					rowData = new Vector();
					rowData.add(rs.getString("MABENH"));
					rowData.add(rs.getString("TENBENH"));
					rowData.add(rs.getString("MOTATRIEUCHUNG"));
					rowData.add(rs.getString("LOAIBENH"));
					BenhView.dtm.addRow(rowData);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		}
		
		//add event button
		if(BenhView.INSERT_COMMAND.equals(cmd))
		{
			if(view.txtTenBenh.getText().isEmpty()||view.txtTrieuChung.getText().isEmpty()||view.txtLoaiBenh.getText().isEmpty())
				JOptionPane.showMessageDialog(null, "Bạn cần phải điền đầy đủ các thông tin cần thiết!");
			else{
				   try {
			            db.saveAutoId(
			                    Entity.idGenner(
			                        "benh", 
			                        "mabenh", 
			                        "Benh_objtyp", 
			                        "Benh_objtyp('111','"+view.txtTenBenh.getText()+"','"+view.txtTrieuChung.getText()+"','"+view.txtLoaiBenh.getText()+"')"
			                    )
			            ); 
			            JOptionPane.showMessageDialog(null, "Thêm thành công");
			            loadData();
			        } catch (Exception e1) {
			            e1.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Quá trình thêm xảy ra lỗi");
			        }
			}
			return;
		}
		
		//delete event button
		
		if (BenhView.DELETE_COMMAND.equals(cmd))
		{
			int row = view.tbBenh.getSelectedRow();
			if(row == -1)
			{
				JOptionPane.showMessageDialog(null, "Hãy chọn dòng cần xóa!");
			}
			else
			{
				String query = "delete from benh where mabenh = '"+view.tbBenh.getModel().getValueAt(row, 0).toString()+"' ";
				BenhModel.editData(query);
				JOptionPane.showMessageDialog(null, "Đã xóa thành công");
				loadData();
			}
			return;
		}
		
		//update button event 
		
		if(BenhView.UPDATE_COMMAND.equals(cmd))
		{
			int row;
			row= view.tbBenh.getSelectedRow();
			if (row==-1)
			{
				JOptionPane.showMessageDialog(null, "Hãy chọn dòng cần sửa!");
			}
			else
			{
				try{
            		String query = "update Benh set tenbenh = '"+view.txtTenBenh.getText()+"', "
                			+ "motatrieuchung = '"+view.txtTrieuChung.getText()+"', "
        					+ "loaibenh = '"+view.txtLoaiBenh.getText()+"' "
    						+ "WHERE mabenh ='"+view.txtMaBenh.getText()+"'";
                	BenhModel.editData(query);
            		JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            		loadData();
            	}
				catch(Exception e1)
				{
            		JOptionPane.showMessageDialog(null, "Có lỗi xảy ra!");
            	}   
            	}
			return;
			}
				
		
		//choose
		
		if(BenhView.SELECT_COMMAND.equals(cmd))
		{
			int row;
			row= view.tbBenh.getSelectedRow();
			if (row==-1)
			{
				JOptionPane.showMessageDialog(null, "Hãy chọn một dòng!");
				return;
			}
			else
			{
				String var =view.tbBenh.getModel().getValueAt(row, 0).toString();
				watcher.beNoticed(var, 6);
				view.dispose();
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int row = view.tbBenh.getSelectedRow();
        view.txtMaBenh.setText((String)view.tbBenh.getModel().getValueAt(row, 0));
        view.txtTenBenh.setText((String)view.tbBenh.getModel().getValueAt(row, 1));
        view.txtTrieuChung.setText((String)view.tbBenh.getModel().getValueAt(row, 2));
        view.txtLoaiBenh.setText((String)view.tbBenh.getModel().getValueAt(row, 3));
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
