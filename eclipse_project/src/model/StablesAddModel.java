/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.db;
/**
 *
 * @author duyphuoc
 */
public class StablesAddModel {
    public void editData(String cmd){
        db.saveAutoId(cmd);
    }
}
