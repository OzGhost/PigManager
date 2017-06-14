/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.RefPayload;
import common.Watcher;
import db.Entity;
import db.db;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ProviderManagerModel;
import model.StablesModel;
import model.ToolAddModel;
import view.ProviderManagerView;
import view.StablesView;
import view.ToolAddView;

/**
 *
 * @author duyphuoc
 */
public class ToolAddController extends ControllerBase<ToolAddModel, ToolAddView> implements Watcher{
   
    @Override
    public void actionPerformed(ActionEvent ae){
        String command= ae.getActionCommand();
        
        if(view.CANCEL_COMMAND.equals(command)){
            this.view.dispose();
            return;
        }
        if(view.SELECT_PROVIDER_COMMAND.equals(command)){
            ProviderManagerController pmc= new ProviderManagerController();
            ProviderManagerView pmv = new ProviderManagerView();
            ProviderManagerModel pmm = new ProviderManagerModel();
            pmv.setController(pmc);
            pmc.setView(pmv);
            pmc.setModel(pmm);
            pmc.setWatcher(this);
            pmc.LoadProvider();
	    pmv._btnChonNCC.setEnabled(true);
            pmv.showUp();
            
        }
        if(view.SELECT_STABLES_COMMAND.equals(command)){
            StablesView sv = new StablesView();
            StablesController sc = new StablesController();
            StablesModel sm = new StablesModel();
            sc.setView(sv);
            sc.setModel(sm);
            sv.setController(sc);
            sc.setWatcher(this);
            sv.showUp();       
        }
        if(view.RESET_COMMAND.equals(command)){
            view.tool_name_tx.setText(null);
            view.cost_tx.setText(null);
            view.describe_tx.setText(null);
            view.provider_id_tx.setText(null);
            view.stables_id_tx.setText(null);
        }
        if(view.ADD_COMMAND.equals(command)){
            if(view.tool_name_tx.getText().toString().equals("") ||
                    view.cost_tx.getText().toString().equals("") ||
                    view.describe_tx.getText().toString().equals("")||
                    view.provider_id_tx.getText().toString().equals("")||
                    view.stables_id_tx.getText().toString().equals("")
                    ){
                JOptionPane.showMessageDialog(null, "Not enough infomation");
                return;
            }
            List<RefPayload> ref = new ArrayList<>();
            ref.add(new RefPayload("NhaCungCap", "MaNCC", 
                    ""+view.provider_id_tx.getText()+"", "NhaCungCap_ref"));
            ref.add(new RefPayload("Chuong", "MaChuong", 
                    ""+view.stables_id_tx.getText()+"", "Chuong_ref"));
            try{
                int cost = Integer.parseInt(view.cost_tx.getText());
                db.saveAutoId(Entity.idGenner("VatDung",
                                              "MaVatDung", "VatDung_objtyp", 
                                              "VatDung_objtyp('1',"
                                                      + "'"+view.tool_name_tx.getText()+"',"
                                                              + "'"+cost+"',"
                                                                      + "'"+view.describe_tx.getText()+"',"
                                                                              + "null,null)", ref));
                JOptionPane.showMessageDialog(null, "Success");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Cost must be number value");
            }
        }
        super.actionPerformed(ae);
    }

    /*@Override
    public void beNoticed(short code, Object thing) {
            
    }*/

    @Override
    public void beNoticed(String var, int num) {
       switch(num){
            case 1:
                view.provider_id_tx.setText(var);
                break;
            case 2:
                view.stables_id_tx.setText(var); 
                break;
        }
    }
    
    
}
