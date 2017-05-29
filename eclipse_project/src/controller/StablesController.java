/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.StablesModel;
import view.StablesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import db.db;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.StablesAddView;
/**
 *
 * @author duyphuoc
 */
public class StablesController extends ControllerBase<StablesModel,StablesView> implements ActionListener, MouseListener{
    
    
    
    String QUERY_COMMAND1= "SELECT * FROM CHUONG WHERE TINHTRANG=0 AND SOLUONGTOIDA=";
    //String QUERY_COMMAND2= "SELECT * FROM CHUONG WHERE TINHTRANG=1 AND SOLUONGTOIDA='"+view.numberCb.getSelectedItem()+"'";
    //String QUERY_COMMAND3= "SELECT * FROM CHUONG WHERE TINHTRANG=2 AND SOLUONGTOIDA='"+view.numberCb.getSelectedItem()+"'";
    
    @Override
    public void actionPerformed(ActionEvent ae) {
    //To change body of generated methods, choose Tools | Templates.
    String command = ae.getActionCommand();
    ResultSet rs = null;
    Vector rowData;
    

    //cancel button event
    if(StablesView.CANCEL_COMMAND.equals(command)){
        this.view.setVisible(false);
        this.view.dispose();
        return;
        }
    
    //search button event
    if (StablesView.SEARCH_COMMAND.equals(command)){
        
        //catch event of two combobox
        if (view.statusCb.getSelectedItem()==null && view.numberCb.getSelectedItem()==null)
            rs = model.getData("SELECT * FROM CHUONG");
        else if (view.statusCb.getSelectedItem()==null)
            rs = model.getData("SELECT * FROM CHUONG WHERE SOLUONGTOIDA='"+view.numberCb.getSelectedItem()+"'");
        else if (view.numberCb.getSelectedItem()==null)
            switch (view.statusCb.getSelectedItem().toString()){
                case "Đầy":
                    rs = model.getData("SELECT * FROM CHUONG WHERE TINHTRANG=0");
                    break;
                case "Đang nuôi":
                    rs = model.getData("SELECT * FROM CHUONG WHERE TINHTRANG=1");
                    break;
                case "Trống":
                    rs = model.getData("SELECT * FROM CHUONG WHERE TINHTRANG=2");
                    break;
            }
        else
            switch (view.statusCb.getSelectedItem().toString()){
                case "Đầy":
                    rs = model.getData("SELECT * FROM CHUONG WHERE TINHTRANG=0 AND SOLUONGTOIDA='"+view.numberCb.getSelectedItem()+"'");
                    break;
                case "Đang nuôi":
                    rs = model.getData("SELECT * FROM CHUONG WHERE TINHTRANG=1 AND SOLUONGTOIDA='"+view.numberCb.getSelectedItem()+"'");
                    break;
                case "Trống":
                    rs = model.getData("SELECT * FROM CHUONG WHERE TINHTRANG=2 AND SOLUONGTOIDA='"+view.numberCb.getSelectedItem()+"'");
                    break;
            }
        
        //push data into Jtable
        if (rs!= null)
            {
            view.dtm.setRowCount(0);
            try {
                while(rs.next()){
                    rowData = new Vector();
                    rowData.add(rs.getString("MaChuong"));
                    if(rs.getInt("TinhTrang")==0)
                        rowData.addElement("Đầy");
                    if(rs.getInt("TinhTrang")==1)
                        rowData.addElement("Đang nuôi");
                    if(rs.getInt("TinhTrang")==2)
                        rowData.addElement("Trống");                                         
                    rowData.add(rs.getInt("SoLuongToiDa"));
                    rowData.add(rs.getString("ViTri"));
                    rowData.add(rs.getString("MoTa"));
                    view.dtm.addRow(rowData);
                }                
            } catch (SQLException ex) {
                Logger.getLogger(StablesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        //reset text field
        view.stables_id_tx.setText(null);
        view.status_tx.setText(null);
        view.number_tx.setText(null);
        view.location_tx.setText(null);
        view.describe_tx.setText(null);
        }
    }
    
    //delete button event
    if(StablesView.DELETE_COMMAND.equals(command)){
        int row = view.gridTb.getSelectedRow();
        if(row ==-1)
            JOptionPane.showMessageDialog(null, "Select please");
        String id= view.gridTb.getModel().getValueAt(row, 0).toString();
        model.editData("DELETE FROM CHUONG WHERE MACHUONG='"+id+"'");
        JOptionPane.showMessageDialog(null, "Success");
    }
    
    //udpate button event
    if(StablesView.SAVE_COMMAND.equals(command)){
        int row = view.gridTb.getSelectedRow();
        if(row ==-1)
            JOptionPane.showMessageDialog(null, "Select please");//alert
        
        
        String id,location,describe ;
        int number = 0;
        id = view.gridTb.getModel().getValueAt(row, 0).toString();
       
            try{
                number =  Integer.parseInt(view.number_tx.getText());
                location = view.location_tx.getText();
                describe = view.describe_tx.getText();
                model.editData("UPDATE CHUONG SET "
                    + "SOLUONGTOIDA ='"+number+"',"
                        + "VITRI ='"+location+"',"
                                + "MOTA ='"+describe+"'"
                                        + "WHERE MACHUONG ='"+id+"'");
        JOptionPane.showMessageDialog(null, "Success");
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Failed, update values must be number");
            }
       
             
        
        
        

        //reset soluongtoida combobox
        view.numberCb.removeAllItems();
        view.numberCb.addItem(null);
        ResultSet rs_for_jcb = db.sendForResult(view.ADD_ITEM_CB_COMMAND);
        try {
            while(rs_for_jcb.next())
            {
                view.numberCb.addItem(rs_for_jcb.getInt("soluongtoida"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StablesView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//add button event
    if(view.ADD_COMMAND.equals(command)){
        StablesAddView sav= new StablesAddView();
        StablesAddController sac= new StablesAddController();
        sav.setController(sac);
        sav.setVisible(true);
        sac.setView(sav);
    }
}

    @Override
    public void mouseClicked(MouseEvent me) {
            int row = view.gridTb.getSelectedRow();
            view.stables_id_tx.setText(view.gridTb.getModel().getValueAt(row, 0).toString());
            view.status_tx.setText(view.gridTb.getModel().getValueAt(row, 1).toString());
            view.number_tx.setText(view.gridTb.getModel().getValueAt(row, 2).toString());
            view.location_tx.setText(view.gridTb.getModel().getValueAt(row, 3).toString());
            view.describe_tx.setText(view.gridTb.getModel().getValueAt(row, 4).toString());          
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    
}
