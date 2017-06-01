/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.StablesAddModel;
import view.StablesAddView;
import db.Entity;
import javax.swing.JOptionPane;
import db.db;
/**
 *
 * @author duyphuoc
 */
public class StablesAddController extends ControllerBase<StablesAddModel, StablesAddView>{

    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if(StablesAddView.CANCEL_COMMAND.equals(command)){
            view.dispose();
        }
        if(StablesAddView.REFRESH_COMMAND.equals(command)){
            view.describe_tx.setText("");
            view.location_tx.setText("");
            view.number_tx.setText("");
        }
        if(StablesAddView.ADD_COMMAND.equals(command)){
            String location,describe;
            int number = 0;
            if(view.location_tx.getText().equals("") || view.describe_tx.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Failed, not enough info");
            }
            else
                try{
                number =Integer.parseInt(view.number_tx.getText());
                location = view.location_tx.getText();
                describe = view.describe_tx.getText();
                db.saveAutoId(Entity.idGenner("CHUONG","MACHUONG","CHUONG_objtyp",
                            "CHUONG_objtyp('abc','2','"+number+"','"+location+"','"+describe+"')"));
                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null,"Failed, 'Số lượng tối đa' values must be number");
                    }   
                JOptionPane.showMessageDialog(null,"Success");
        }
        super.actionPerformed(ae);
    }
}
