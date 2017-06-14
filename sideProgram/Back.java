
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;

public class Back {
    static class BackgroundPanel extends JPanel {
        private Image background;

        public BackgroundPanel (String pathToImg) {
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
    private static JLabel getGap() {
        final JLabel jl = new JLabel();
        return jl;
    }
    public static void main (String[] args) {
        final Dimension bt_size = new Dimension(300, 50);
        JButton bt_pig = new JButton("Quan ly heo");
        JButton bt_stock = new JButton("Quan ly kho");
        JButton bt_pasture = new JButton("Quan ly chuong");
        JButton bt_provider = new JButton("Quan ly nha cung cap");
        JButton bt_sicklog = new JButton("Quan ly benh an");
        JButton bt_sick = new JButton("Quan ly benh");
        JButton bt_cashhist = new JButton("Lich su thu chi");
        JButton bt_cashlog = new JButton("Ghi nhan thu chi");
        JButton bt_food = new JButton("Quan ly thuc an");
        JButton bt_tool = new JButton("Quan ly vat dung");
        JButton bt_preg = new JButton("Lich su thai ky");
        JButton bt_lant = new JButton("Tha tinh");

        bt_pig.setPreferredSize(bt_size);
        bt_stock.setPreferredSize(bt_size);
        bt_pasture.setPreferredSize(bt_size);
        bt_provider.setPreferredSize(bt_size);
        bt_sicklog.setPreferredSize(bt_size);
        bt_sick.setPreferredSize(bt_size);
        bt_cashhist.setPreferredSize(bt_size);
        bt_cashlog.setPreferredSize(bt_size);
        bt_food.setPreferredSize(bt_size);
        bt_tool.setPreferredSize(bt_size);
        bt_preg.setPreferredSize(bt_size);
        bt_lant.setPreferredSize(bt_size);

        JPanel menu = new JPanel(new GridLayout(0, 1));
        menu.add(bt_pig);
        menu.add(bt_stock);
        menu.add(bt_pasture);
        menu.add(bt_provider);
        menu.add(bt_sicklog);
        menu.add(bt_sick);
        menu.add(bt_cashhist);
        menu.add(bt_cashlog);
        menu.add(bt_food);
        menu.add(bt_tool);
        menu.add(bt_preg);
        menu.add(bt_lant);

        SpringLayout layout = new SpringLayout();
        BackgroundPanel bp = new BackgroundPanel("pigfix.jpg");
        bp.setLayout(layout);
        
        bp.add(menu);

        layout.putConstraint(
                SpringLayout.EAST,
                menu,
                -100,
                SpringLayout.EAST,
                bp
        );
        layout.putConstraint(
                SpringLayout.NORTH,
                menu,
                60,
                SpringLayout.NORTH,
                bp
        );
        JFrame jf = new JFrame();
        jf.setContentPane(bp);
        jf.setPreferredSize(new Dimension(1300, 760));
        jf.setUndecorated(true);
        jf.setResizable(false);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
