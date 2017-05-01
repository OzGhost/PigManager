package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import listener.CloseWindowListener;
import model.DemoModel;

public class DemoView extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    
    private JTextField tf01 = new JTextField();
    private JTextField tf02 = new JTextField();
    private String[] columns = {"colum1", "column2", "column3", "columns4"};
    private TableModel tm = new DefaultTableModel(columns, 4);
    private JTable tb01 = new JTable(tm);
    private JButton bt = new JButton("getSecret");
    private JPanel layout = new JPanel(new GridLayout(3, 3));
    private JLabel lb = new JLabel();
    
    public DemoView() {
        
        tf01.setMinimumSize(new Dimension(150, 20));
        tf02.setMinimumSize(new Dimension(150, 20));
        
        setContentPane(layout);
        setSize(new Dimension(400, 100));
        setLocationRelativeTo(null);
        layout.add(new JLabel("First Secret:"));
        layout.add(tf01);
        layout.add(lb);
        layout.add(new JLabel("Second Secret:"));
        layout.add(tf02);
        layout.add(bt);
        layout.add(tb01);
        
        addWindowListener(new CloseWindowListener());
    }
    
    public void addController(ActionListener controller) {
        this.bt.addActionListener(controller);
    }
    
    public void up(){
        this.lb.setText("clicked");
        layout.repaint();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        tf01.setText(((DemoModel) o).getSecret());
        tf02.setText(((DemoModel) o).getAnotherSecret());
        lb.setText("Signal: " + arg);
        layout.repaint();
    }

}
