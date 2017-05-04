package controller;

import javax.swing.UIManager;

import db.KhachHang;
import db.db;
import model.DemoModel;
import view.DemoView;

public class DemoGUI {
    public static void main (String[] args) {
//        try {
//            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        final DemoView view = new DemoView();
//        final DemoModel model = new DemoModel();
//        model.addObserver(view);
//        final DemoController controller = new DemoController(view, model);
//        view.addController(controller);
//        view.setVisible(true);
        db.init("orcBase", "c##oz", "ngaymai");
        KhachHang.init();
        KhachHang.findOne();
    }
}
