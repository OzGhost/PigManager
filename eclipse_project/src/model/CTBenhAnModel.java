package model;

import java.sql.ResultSet;

import db.db;

public class CTBenhAnModel extends ModelBase {
    public static ResultSet getData(String cmd){
    	return db.sendForResult(cmd);
    }
    
    public static boolean editData(String cmd){
    	return db.send(cmd);
    }
}
