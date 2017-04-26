package controller;
import db.*;

public class pm {
    public static void main (String[] args) {
        db.init("orcbase", "c##oz", "ngaymai");
        db.send("select * from khachhang");
    }
}
