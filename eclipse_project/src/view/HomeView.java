package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import common.Constants;
import common.Layer;
import controller.HomeController;

public class HomeView extends ViewBase {

    static class BackPane extends JPanel {
        private Image background;

        public BackPane (String pathToImg) {
            try {
                this.background = ImageIO.read(new File(pathToImg));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, this);
        }
    }

    private static final long serialVersionUID = 5337391590871863765L;
    //Fields

    private JButton bt_pig;
    private JButton bt_stock;
    private JButton bt_pasture;
    private JButton bt_provider;
    private JButton bt_sicklog;
    private JButton bt_sick;
    private JButton bt_cashhist;
    private JButton bt_cashlog;
    private JButton bt_food;
    private JButton bt_tool;
    private JButton bt_preg;
    private JButton bt_lant;
    private final SpringLayout layout = new SpringLayout();
    private final BackPane panel = new BackPane("src/res/pigfix.jpg");

    private static JButton transparentButton (String title, Dimension size) {
        String strong_title = "<html><b>" + title + "</b></html>";
        final JButton rs = new JButton(strong_title);
        rs.setOpaque(false);
        rs.setContentAreaFilled(false);
        rs.setPreferredSize(size);
        rs.setForeground(Color.WHITE);
        rs.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
        rs.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                rs.setBorder(BorderFactory.createMatteBorder(1, 15, 1, 1, Color.WHITE));
            }

            public void mouseExited(MouseEvent evt) {
                rs.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
            }
        });
        return rs;
    }
    
    //Constructors
    public HomeView (HomeController ctl) {
        initButton();

        bt_pig.addActionListener(ctl);
        bt_stock.addActionListener(ctl);
        bt_pasture.addActionListener(ctl);
        bt_provider.addActionListener(ctl);
        bt_sicklog.addActionListener(ctl);
        bt_sick.addActionListener(ctl);
        bt_cashhist.addActionListener(ctl);
        bt_cashlog.addActionListener(ctl);
        bt_food.addActionListener(ctl);
        bt_tool.addActionListener(ctl);
        bt_preg.addActionListener(ctl);
        bt_lant.addActionListener(ctl);

        JPanel subpane = subpaneAssemble();


        panel.setLayout(layout);

        panel.add(subpane);

        Layer.put(subpane).in(layout)
            .atTop(panel).withMargin(10)
            .atRight(panel).withMargin(100);

        setContentPane(panel);
        setUndecorated(true);
        setPreferredSize(new Dimension(1300, 760));
    }

    //Methods
    private void initButton () {

        final Dimension bt_size = new Dimension(300, 50);

        bt_pig = transparentButton("Quản lý heo", bt_size);
        bt_stock = transparentButton("Quản lý kho", bt_size);
        bt_pasture = transparentButton("Quản lý chuồng", bt_size);
        bt_provider = transparentButton("Quản lý nhà cung cấp", bt_size);
        bt_sicklog = transparentButton("Quản lý bệnh án", bt_size);
        bt_sick = transparentButton("Quản lý bệnh", bt_size);
        bt_cashhist = transparentButton("Lịch sử thu chi", bt_size);
        bt_cashlog = transparentButton("Ghi nhận thu chi", bt_size);
        bt_food = transparentButton("Quản lý thức ăn", bt_size);
        bt_tool = transparentButton("Quản lý vật dụng", bt_size);
        bt_preg = transparentButton("Lịch sử thai kỳ", bt_size);
        bt_lant = transparentButton("Thả tinh", bt_size);

        bt_pig.setActionCommand(Constants.AC_PIG_FEAT);
        bt_stock.setActionCommand(Constants.AC_STOCK_FEAT);
        bt_pasture.setActionCommand(Constants.AC_PASTURE_FEAT);
        bt_provider.setActionCommand(Constants.AC_PROVIDER_FEAT);
        bt_sicklog.setActionCommand(Constants.AC_SICKLOG_FEAT);
        bt_sick.setActionCommand(Constants.AC_SICK_FEAT);
        bt_cashhist.setActionCommand(Constants.AC_CASHFLOW_FEAT);
        bt_cashlog.setActionCommand(Constants.AC_CASHLOG_FEAT);
        bt_food.setActionCommand(Constants.AC_FOOD_FEAT);
        bt_tool.setActionCommand(Constants.AC_TOOL_FEAT);
        bt_preg.setActionCommand(Constants.AC_PREGLOG_FEAT);
        bt_lant.setActionCommand(Constants.AC_LANT_FEAT);

    }

    private JPanel subpaneAssemble () {
        final SpringLayout l = new SpringLayout();
        final JPanel subpane = new JPanel(l);

        subpane.setPreferredSize(new Dimension(300, 760));
        subpane.setOpaque(false);

        subpane.add(bt_pig);
        subpane.add(bt_stock);
        subpane.add(bt_pasture);
        subpane.add(bt_provider);
        subpane.add(bt_sicklog);
        subpane.add(bt_sick);
        subpane.add(bt_cashhist);
        subpane.add(bt_cashlog);
        subpane.add(bt_food);
        subpane.add(bt_tool);
        subpane.add(bt_preg);
        subpane.add(bt_lant);

        Layer.put(bt_pig).in(l)
            .atLeft(subpane).withMargin(0)
            .atRight(subpane).withMargin(0)
            .atTop(subpane).withMargin(0);
        Layer.put(bt_sicklog).in(l)
            .atLeft(subpane).withMargin(0)
            .atRight(subpane).withMargin(0)
            .bottomOf(bt_pig).withMargin(10);
        Layer.put(bt_sick).in(l)
            .atLeft(subpane).withMargin(0)
            .atRight(subpane).withMargin(0)
            .bottomOf(bt_sicklog).withMargin(10);
        Layer.put(bt_preg).in(l)
            .atLeft(subpane).withMargin(0)
            .atRight(subpane).withMargin(0)
            .bottomOf(bt_sick).withMargin(10);
        Layer.put(bt_lant).in(l)
            .atLeft(subpane).withMargin(0)
            .atRight(subpane).withMargin(0)
            .bottomOf(bt_preg).withMargin(10);
        Layer.put(bt_provider).in(l)
            .atLeft(subpane).withMargin(0)
            .atRight(subpane).withMargin(0)
            .bottomOf(bt_lant).withMargin(10);
        Layer.put(bt_pasture).in(l)
            .atLeft(subpane).withMargin(0)
            .atRight(subpane).withMargin(0)
            .bottomOf(bt_provider).withMargin(10);
        Layer.put(bt_stock).in(l)
            .atLeft(subpane).withMargin(0)
            .atRight(subpane).withMargin(0)
            .bottomOf(bt_pasture).withMargin(10);
        Layer.put(bt_cashhist).in(l)
            .atLeft(subpane).withMargin(0)
            .atRight(subpane).withMargin(0)
            .bottomOf(bt_stock).withMargin(10);
        Layer.put(bt_cashlog).in(l)
            .atLeft(subpane).withMargin(0)
            .atRight(subpane).withMargin(0)
            .bottomOf(bt_cashhist).withMargin(10);
        Layer.put(bt_food).in(l)
            .atLeft(subpane).withMargin(0)
            .atRight(subpane).withMargin(0)
            .bottomOf(bt_cashlog).withMargin(10);
        Layer.put(bt_tool).in(l)
            .atLeft(subpane).withMargin(0)
            .atRight(subpane).withMargin(0)
            .bottomOf(bt_food).withMargin(10);

        return subpane;
    }

    public void shadow () {
        setVisible(false);
    }
}
