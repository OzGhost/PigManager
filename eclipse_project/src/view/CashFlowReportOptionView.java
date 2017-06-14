package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.Calendar;
import java.util.Observable;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpringLayout;
import javax.swing.filechooser.FileNameExtensionFilter;

import common.Constants;
import common.Genner;
import common.Layer;
import controller.CashFlowReportOptionController;
import controller.ControllerBase;
import model.CashFlowReportOptionModel;

public class CashFlowReportOptionView extends ViewBase {
    
    private static final long serialVersionUID = 3765653417887253677L;
    // Fields
    private JSpinner from;
    private JSpinner to;
    private JComboBox<String> gby;
    private JButton bt_back;
    private JButton bt_go;
    private JButton bt_browse;
    private JTextField tf_of;
    private final SpringLayout layout = new SpringLayout();
    private final JPanel pane = new JPanel();
    private final JPanel subpan = new JPanel(new GridLayout(0, 1));
    private final JPanel fileBar = new JPanel(new BorderLayout());

    // Constructors
    public CashFlowReportOptionView () {
        init();
        setTitle("Kết xuất báo cáo thu/chi");
    }

    // Methods
    private void init () {

        // button
        bt_back = Genner.createButton("Trở lại", Genner.MEDIUM_SIZE);
        bt_go = Genner.createButton("Kết xuất", Genner.MEDIUM_LONG_SIZE);
        bt_browse = Genner.createButton("...", Genner.MEDIUM_SIZE);

        bt_back.setActionCommand(Constants.AC_BACK);
        bt_go.setActionCommand(Constants.AC_DONE);
        bt_browse.setActionCommand(Constants.AC_BROWSE);

        tf_of = Genner.createTextField(Genner.MEDIUM_SIZE);
        tf_of.setText(" ... ");
        tf_of.setEditable(false);

        fileBar.add(tf_of, BorderLayout.CENTER);
        fileBar.add(bt_browse, BorderLayout.EAST);

        // group by
        final Set<String> group = Constants.TIME_STAGE.keySet();
        final String[] grp = new String[group.size()];
        int i = 0;
        for (String e: group) {
            grp[i] = e;
            i++;
        }
        gby = new JComboBox<String>(grp);

        subpan.setPreferredSize(new Dimension(400, 400));

        pane.setLayout(layout);

        pane.add(bt_back);
        pane.add(bt_go);
        pane.add(subpan);

        Layer.put(bt_back).in(layout)
            .atBottomLeft(pane).withMargin(5);
        Layer.put(bt_go).in(layout)
            .atBottomRight(pane).withMargin(5);
        Layer.put(subpan).in(layout)
            .atTopLeft(pane).withMargin(5)
            .atRight(pane).withMargin(5)
            .topOf(bt_go).withMargin(5);
        
        setContentPane(pane);
        setPreferredSize(new Dimension(300, 450));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @Override
    public void setController (ControllerBase c) {
        CashFlowReportOptionController roc = (CashFlowReportOptionController) c;
        bt_back.addActionListener(roc);
        bt_go.addActionListener(roc);
        bt_browse.addActionListener(roc);
    }

    private File getSaveTo () {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PDF File", "pdf"
        );
        fc.setFileFilter(filter);
        int returnVal = fc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        }
        return null;
    }

    public void specificFile () {
        File target = getSaveTo();
        if (target == null)
            return;
        tf_of.setText(target.getAbsolutePath());
    }

    public Object[] getMetaData () {
        String out = tf_of.getText();
        if (!out.toLowerCase().endsWith(".pdf")) {
            noticeError("Vui lòng chọn tập tin để lưu với định dạng PDF!");
            release();
            return null;
        }
        Object[] rs = new Object[4];
        rs[0] = from.getValue();
        rs[1] = to.getValue();
        rs[2] = gby.getSelectedItem();
        rs[3] = tf_of.getText();
        return rs;
    }

    public void  noticeResult (boolean rs) {
        String successMessage = "Kết xuất hoàn tất!";
        String falseMessage = "Xảy ra lỗi trong quá trình kết xuất!";
        super.noticeResult(rs, successMessage, falseMessage);
    }

    public void silent () {
        bt_back.setEnabled(false);
        bt_go.setEnabled(false);
        bt_browse.setEnabled(false);

        bt_go.setText("Đang xử lý ...");
        bt_back.setText("...");
    }

    public void release () {
        bt_back.setEnabled(true);
        bt_go.setEnabled(true);
        bt_browse.setEnabled(true);

        bt_go.setText("Kết xuất");
        bt_back.setText("Trở lại");
    }

    @Override
    public void update(Observable o, Object arg) {
        CashFlowReportOptionModel model = (CashFlowReportOptionModel) o;

        final Calendar calen = Calendar.getInstance();
        calen.setTime(model.getMaxDate());
        calen.add(Calendar.MONTH, -4);
        
        final SpinnerModel minModel = new SpinnerDateModel(
				calen.getTime(), // init number
				model.getMinDate(), // min
				model.getMaxDate(), // max
				Calendar.DAY_OF_MONTH // step
		);
        final SpinnerModel maxModel = new SpinnerDateModel(
				model.getMaxDate(), // init number
				model.getMinDate(), // min
				model.getMaxDate(), // max
				Calendar.DAY_OF_MONTH // step
		);

        from = new JSpinner(minModel);
        to = new JSpinner(maxModel);

        subpan.add(new JLabel("Kết xuất từ ngày:"));
        subpan.add(from);
        subpan.add(new JLabel("Đến ngày:"));
        subpan.add(to);
        subpan.add(new JLabel("Cộng dồn trên:"));
        subpan.add(gby);
        subpan.add(new JLabel("Lưu thành:"));
        subpan.add(fileBar);
    }
}
