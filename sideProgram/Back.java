
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
    public static void main (String[] args) {
        JButton bt_a = new JButton("Pigs manager");
        JButton bt_c = new JButton("Pigs manager");
        JButton bt_d = new JButton("Pigs manager");
        JButton bt_b = new JButton("Pigs manager");

        JPanel menu = new JPanel(new GridLayout(0, 1));
        menu.add(bt_a);
        menu.add(bt_b);
        menu.add(bt_c);
        menu.add(bt_d);

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
        JFrame jf = new JFrame();
        jf.setContentPane(bp);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jf.setUndecorated(true);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
