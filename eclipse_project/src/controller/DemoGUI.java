package controller;

import db.KhachHang;
import db.db;

public class DemoGUI {
    public static void main (String[] args) {
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
