/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.Entity;
import db.db;
import java.awt.HeadlessException;
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
import javax.swing.table.DefaultTableModel;
import model.FoodModel;
import view.FoodView;
import view.WarehouseManagerView;

/**
 *
 * @author Dang Nhat Hai Long
 */
public class FoodController extends ControllerBase<FoodModel, FoodView> implements ActionListener, MouseListener
{
    private FoodView view;
    private FoodModel model;
    
    public  FoodController (FoodModel m, FoodView v)
    {
        this.view = v;
        this.model = m;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String _cmd = e.getActionCommand();
        ResultSet resultSet = null;
        Vector rowData;
        
        if (view.SEARCH_COMMAND.equals(_cmd))
        {
            

            
            
            if ("".equals(view._txtfTimKiem.getText()))
            {
                resultSet = model.getData("select * from loaithucan");
            }
            else
            {
                resultSet = model.getData("SELECT * FROM loaithucan WHERE TENLOAITHUCAN LIKE '%"+view._txtfTimKiem.getText()+"%'");
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
        
        if(view.ADD_COMMAND.equals(_cmd))
        {
            if ("".equals(view._txtfTenLoai.getText()))
            {
                JOptionPane.showMessageDialog(null, "Fill all before you want to add!");
            }
            else
            {
                try
                {
                    int mucbaodong = Integer.parseInt(view._txtfMucBaoDong.getText());
                    db.saveAutoId(Entity.idGenner("LOAITHUCAN","MALOAITHUCAN","LoaiThucAn_objtyp","LoaiThucAn_objtyp('123', '"+ view._txtfTenLoai.getText() +"', '"+ view._txtaMota.getText()+ "', '"+ view._txtfDonVi.getText() +"', "+mucbaodong+")"));   
                    JOptionPane.showMessageDialog(null, "Thêm thành công!");
                }
                catch (NumberFormatException b)
                {
                    JOptionPane.showMessageDialog(null, "Mức báo động là số");
                }
            }
            return;
        }
        
        
        if (view.UPDATE_COMMAND.equals(_cmd))
        {
            int row = view._tbThucAn.getSelectedRow();
            if (row == -1)
            {
                JOptionPane.showMessageDialog(null, "Cần chọn loại thức ăn muốn cập nhật thông tin");
            }
            else
            {
                int mucbaodong = Integer.parseInt(view._txtfMucBaoDong.getText());
                String _maloaithucan = view._tbThucAn.getModel().getValueAt(row, 0).toString();
                String _query = "update LOAITHUCAN set TENLOAITHUCAN = '"+view._txtfTenLoai.getText()+"', MOTA = '"+view._txtaMota.getText()+"', DONVI = '"+view._txtfDonVi.getText()+"', MUCBAODONG = "+mucbaodong+" WHERE MALOAITHUCAN = "+_maloaithucan;
                db.send(_query);   
                JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            }
            return;
        }
        
        //Xóa nhà cung cấp
        if (view.REMOVE_COMMAND.equals(_cmd))
        {
            int row = view._tbThucAn.getSelectedRow();
            if (row == -1)
            {
                JOptionPane.showMessageDialog(null, "Cần chọn loại thức ăn muốn cập nhật thông tin");
            }
            else
            {
                String _maloaithucan = view._tbThucAn.getModel().getValueAt(row, 0).toString();
                String _query = "DELETE FROM LOAITHUCAN WHERE MALOAITHUCAN ="+_maloaithucan;
                db.send(_query);   
                JOptionPane.showMessageDialog(null, "Xóa thành công");
            }
            return;
        }
        
        if (view.SELECT_FOOD_COMMAND.equals(_cmd))
        {
            int row = view._tbThucAn.getSelectedRow();
            if (row == -1)
            {
                JOptionPane.showMessageDialog(null, "Cần chọn loại thức ăn");
            }
            else
            {
                WarehouseManagerView._txtfMaLoaiThucAn.setText(view._txtfMaLoai.getText());
                view.dispose();
            }
            return;
        }
        
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

