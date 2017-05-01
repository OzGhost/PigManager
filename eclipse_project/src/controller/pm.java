package controller;
import db.*;
import java.sql.ResultSet;

public class pm {
    public static void main (String[] args) {
        db.init("orcbase", "c##oz", "ngaymai");
        ResultSet rs = db.sendForResult("select * from khachhang");
        try {
            while(rs.next()) {
                System.out.println(rs.getString("MAKH"));
                System.out.println(rs.getString("HOTEN"));
                System.out.println(rs.getString("NGSINH"));
                System.out.println(rs.getInt("GIOITINH"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
