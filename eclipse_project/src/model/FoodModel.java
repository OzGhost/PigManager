/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.db;
import java.sql.ResultSet;

/**
 *
 * @author Dang Nhat Hai Long
 */
public class FoodModel extends ModelBase
{
    public static ResultSet getData(String cmd)
    {
        return db.sendForResult(cmd);
    }
    
    public static boolean editData(String cmd)
    {
        return db.send(cmd);
    }
}
