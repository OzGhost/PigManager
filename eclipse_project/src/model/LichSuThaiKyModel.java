package model;

import java.sql.ResultSet;

import db.db;

public class LichSuThaiKyModel {
	public static ResultSet getData(String cmd){
    	return db.sendForResult(cmd);
    }
    
    public static boolean editData(String cmd){
    	return db.send(cmd);
    }
}
