/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.Entity;
import db.db;
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
import model.ProviderManagerModel;
import model.WarehouseManagerModel;
import view.ProviderManagerView;
import view.WarehouseManagerView;

/**
 *
 * @author Dang Nhat Hai Long
 */
public class ProviderManagerController extends ControllerBase<ProviderManagerModel, ProviderManagerView> implements ActionListener, MouseListener
{
    private ProviderManagerView view;
    private ProviderManagerModel model;
    
    public ProviderManagerController(ProviderManagerView v, ProviderManagerModel m)
    {
        this.view = v;
        this.model = m;
    }

    public void actionPerformed(ActionEvent e)
    {
        String _cmd = e.getActionCommand();
        ResultSet resultSet = null;
        Vector rowData;
        if (view.SEARCH_COMMAND.equals(_cmd))
        {
            final String[] _columnsTBKho =
            {
                "Mã", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại", "Mô tả", "Nợ phải trả"
            };
            view.dtm = new DefaultTableModel(_columnsTBKho,0);

            view._tbNCC.setModel(view.dtm);
            if ("".equals(view._txtfTimKiem.getText()))
            {
                resultSet = model.getData("select * from nhacungcap");
            }
            else
            {
                resultSet = model.getData("SELECT * FROM nhacungcap WHERE TENNCC LIKE '%"+view._txtfTimKiem.getText()+"%'");
            }
            if (resultSet != null)
            {
                view.dtm.setRowCount(0);
                try
                {
                    while (resultSet.next())
                    {
                        rowData = new Vector();
                        rowData.add(resultSet.getString("MANCC"));
                        rowData.add(resultSet.getString("TENNCC"));
                        rowData.add(resultSet.getString("DIACHI"));
                        rowData.add(resultSet.getString("SODIENTHOAI"));
                        rowData.add(resultSet.getString("MOTA"));
                        rowData.add(resultSet.getFloat("NOPHAITRA"));
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
        
        //Thêm nhà cung cấp
        if(view.ADD_COMMAND.equals(_cmd))
        {
            if ("".equals(view._txtfTenNCC.getText()) || "".equals(view._txtfSDT.getText()) || "".equals(view._txtfDiaChi.getText()))
            {
                JOptionPane.showMessageDialog(null, "Fill all before you want to add!");
            }
            else
            {
                try
                {
                    int _nophaitra = Integer.parseInt(view._txtfNoPhaiTra.getText());
                    db.saveAutoId(Entity.idGenner("NHACUNGCAP","MANCC","NhaCungCap_objtyp","NhaCungCap_objtyp('123', '"+ view._txtfTenNCC.getText() +"', '"+ view._txtfDiaChi.getText()+ "', '"+ view._txtfSDT.getText() +"', '"+ view._txtaMoTa.getText() +"', '"+ _nophaitra +"')"));   
                    JOptionPane.showMessageDialog(null, "Thêm thành công!");
                }
                catch (NumberFormatException b)
                {
                    JOptionPane.showMessageDialog(null, "Nợ phải trả là giá trị số");
                }
            }
            return;
        }
        
        //Cập nhật nhà cung cấp
        if (view.UPDATE_COMMAND.equals(_cmd))
        {
            int row = view._tbNCC.getSelectedRow();
            if (row == -1)
            {
                JOptionPane.showMessageDialog(null, "Cần chọn nhà cung cấp muốn cập nhật thông tin");
            }
            else
            {
                int _nophaitra = Integer.parseInt(view._txtfNoPhaiTra.getText());
                String _maNCC = view._tbNCC.getModel().getValueAt(row, 0).toString();
                String _query = "update NHACUNGCAP SET TENNCC = '"+ view._txtfTenNCC.getText() +"', DIACHI = '"+ view._txtfDiaChi.getText() +"',SODIENTHOAI='"+ view._txtfSDT.getText() +"',MOTA='"+ view._txtaMoTa.getText() +"',NOPHAITRA='"+ _nophaitra +"' where MANCC = "+_maNCC;
                db.send(_query);   
                JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            }
            return;
        }
        
        //Xóa nhà cung cấp
        if (view.REMOVE_COMMAND.equals(_cmd))
        {
            int row = view._tbNCC.getSelectedRow();
            if (row == -1)
            {
                JOptionPane.showMessageDialog(null, "Cần chọn nhà cung cấp muốn cập nhật thông tin");
            }
            else
            {
                String _maNCC = view._tbNCC.getModel().getValueAt(row, 0).toString();
                String _query = "DELETE FROM NHACUNGCAP WHERE MANCC ="+_maNCC;
                db.send(_query);   
                JOptionPane.showMessageDialog(null, "Xóa thành công");
            }
            return;
        }
        
        if (view.SELECT_PROVIDER_COMMAND.equals(_cmd))
        {
            int row = view._tbNCC.getSelectedRow();
            if (row == -1)
            {
                JOptionPane.showMessageDialog(null, "Cần chọn nhà cung cấp");
            }
            else
            {
                WarehouseManagerView._txtfMaNCC.setText(view._txtfMaNCC.getText());
                view.dispose();
            }
            return;
        }
    }

    public void mouseClicked(MouseEvent e)
    {
        view._txtfMaNCC.setText("");
        view._txtfTenNCC.setText("");
        view._txtfSDT.setText("");
        view._txtfDiaChi.setText("");
        view._txtfNoPhaiTra.setText("");
        view._txtaMoTa.setText("");
        
        int row = view._tbNCC.getSelectedRow();
        view._txtfMaNCC.setText(view._tbNCC.getModel().getValueAt(row, 0).toString());
        view._txtfTenNCC.setText(view._tbNCC.getModel().getValueAt(row, 1).toString());
        view._txtfSDT.setText(view._tbNCC.getModel().getValueAt(row, 3).toString());
        view._txtfDiaChi.setText(view._tbNCC.getModel().getValueAt(row, 2).toString());
        view._txtfNoPhaiTra.setText(view._tbNCC.getModel().getValueAt(row, 5).toString());
        view._txtaMoTa.setText(view._tbNCC.getModel().getValueAt(row, 4).toString());
        
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
