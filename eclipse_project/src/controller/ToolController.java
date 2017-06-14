/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Watcher;
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
import model.StablesModel;
import model.ToolAddModel;
import model.ToolModel;
import view.StablesView;
import view.ToolAddView;
import view.ToolView;
import common.*;

/**
 *
 * @author duyphuoc
 */
public class ToolController extends ControllerBase<ToolModel, ToolView> implements MouseListener,Watcher{

    @Override
    public void actionPerformed(ActionEvent ae) {
        
       String command= ae.getActionCommand();
       ResultSet rs=null;
       Vector arrData;
       
       
       if(view.CANCEL_COMMAND.equals(command)){
           this.view.setVisible(false);
           this.view.dispose();
           return;
       }
       if(view.SEARCH_COMMAND.equals(command)){
            if(view.search_tx.getText().toString().equals("")){
                rs=ToolModel.getData("select t.mavatdung,t.tenvatdung,t.giamua,t.mota,t.nhacungcap_ref.mancc,t.chuong_ref.machuong"
                        + " from vatdung t");
            }
            else{
                String str=view.search_tx.getText();
                char[] digit=str.toCharArray();
                digit[0]=Character.toUpperCase(digit[0]);
                str=new String(digit);
                rs=ToolModel.getData("select t.mavatdung,t.tenvatdung,t.giamua,t.mota,t.nhacungcap_ref.mancc,t.chuong_ref.machuong"
                        + " from vatdung t"
                        + " where t.tenvatdung Like '%"+str+"%'");
            }
            
            if(rs!=null){
                try {
                    view.dtm.setRowCount(0);
                    while(rs.next()){
                        arrData = new Vector();
                        arrData.add(rs.getString("mavatdung"));
                        arrData.add(rs.getString("tenvatdung"));
                        arrData.add(rs.getInt("giamua"));
                        arrData.add(rs.getString("mota"));
                        arrData.add(rs.getString("nhacungcap_ref.mancc"));
                        arrData.add(rs.getString("chuong_ref.machuong"));
                        view.dtm.addRow(arrData);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ToolController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        //reset textbox
        view.tool_id_tx.setText(null);
        view.tool_name_tx.setText(null);
        view.cost_tx.setText(null);
        view.describe_tx.setText(null);
        view.provider_id_tx.setText(null);
        view.stables_id_tx.setText(null);
           
       }
       if(view.ADD_COMMAND.equals(command)){
           ToolAddView tav= new ToolAddView();
           ToolAddController tac = new ToolAddController();
           ToolAddModel tam= new ToolAddModel();
           tav.setController(tac);
           tac.setView(tav);
           tac.setModel(tam);
           tav.showUp();
       }
       if(view.DELETE_COMMAND.equals(command)){
           int row = view.tool_tb.getSelectedRow();
           if(row ==-1){
               JOptionPane.showMessageDialog(null, "Select please");
               return;
           }
               String id=view.tool_tb.getModel().getValueAt(row, 0).toString();
           model.editData("DELETE FROM VATDUNG WHERE MAVATDUNG='"+id+"'");
           JOptionPane.showMessageDialog(null, "Success");
           view.dtm.removeRow(row);
       }
       if(view.UPDATE_COMMAND.equals(command)){
           int row = view.tool_tb.getSelectedRow();
           if(row ==-1){
               JOptionPane.showMessageDialog(null, "Select please");
               return;
           }
           String id,toolname,describe ;
           int cost;
           id = view.tool_tb.getModel().getValueAt(row, 0).toString();
           try {
               toolname = view.tool_name_tx.getText().toString();
               cost =Integer.parseInt(view.cost_tx.getText().toString());
               if(cost>999 ){
                   JOptionPane.showMessageDialog(null,"Cost value must be 3 nunmber");
                   return;
               }
               if(cost<0){
                   JOptionPane.showMessageDialog(null,"Cost value must be >=0 ");
                   return;
               }
               describe = view.describe_tx.getText();
               model.editData("UPDATE VATDUNG SET"
                       + " TENVATDUNG='"+toolname+"',"
                               + " GIAMUA='"+cost+"',"
                                       + "MOTA='"+describe+"'"
                                               + " WHERE MAVATDUNG='"+id+"'");
               JOptionPane.showMessageDialog(null, "Success");
               
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null,"Failed, COST values must be number");
           }
           
           view.dtm.setValueAt(view.tool_name_tx.getText(), row, 1);
           view.dtm.setValueAt(view.cost_tx.getText(), row, 2);
           view.dtm.setValueAt(view.describe_tx.getText(), row, 3);
           
       }
       if(view.MOVE_COMMAND.equals(command)){
           int row= view.tool_tb.getSelectedRow();
           if(row==-1){
               JOptionPane.showMessageDialog(null, "Please select");
               return;
           }
           JOptionPane.showMessageDialog(null, "Select stable to move");
           StablesView sv = new StablesView();
           StablesController sc = new StablesController();
           StablesModel sm = new StablesModel();
           sc.setView(sv);
           sc.setModel(sm);
           sv.setController(sc);
           sc.setWatcher(this);
           sv.showUp();
       }
       if(Constants.AC_HOME.equals(command)){
           
       }
       
        super.actionPerformed(ae);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int row = view.tool_tb.getSelectedRow();
        view.tool_id_tx.setText(view.tool_tb.getModel().getValueAt(row, 0).toString());
        view.tool_name_tx.setText(view.tool_tb.getModel().getValueAt(row, 1).toString());
        view.cost_tx.setText(view.tool_tb.getModel().getValueAt(row, 2).toString());
        view.describe_tx.setText(view.tool_tb.getModel().getValueAt(row, 3).toString());
        view.provider_id_tx.setText(view.tool_tb.getModel().getValueAt(row, 4).toString());
        view.stables_id_tx.setText(view.tool_tb.getModel().getValueAt(row, 5).toString());
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

    /*@Override
    public void beNoticed(short code, Object thing) {
        
    }*/
    
    @Override
    public void beNoticed(String var, int num) {
        view.stables_id_tx.setText(var);
        String cmd= "UPDATE VATDUNG SET"
                + " CHUONG_REF=(SELECT REF(C) FROM CHUONG C WHERE C.MACHUONG='"+var+"')"
                + " WHERE MAVATDUNG='"+view.tool_id_tx.getText()+"'";
        model.editData(cmd);
    }
    
    
}
