/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import db.db;
/**
 *
 * @author duyphuoc
 */
public class StablesModel extends ModelBase {
    
    
    public static ResultSet getData(String cmd)
    {
         return db.sendForResult(cmd);
    }
    
    public static boolean editData(String cmd){
        return db.send(cmd);
    }
    
    
}
