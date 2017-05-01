package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PastureListView extends ViewBase {

    private static final long serialVersionUID = 1L;
    
    private JSplitPane splitter;
    private JTable pastureTable = new JTable();
    private JPanel top = new JPanel();
    private JPanel topRightMenu = new JPanel();
    private JPanel topLeftFields = new JPanel();
    private JButton newButtton = new JButton("New");
    private JButton UpdateButtton = new JButton("Update");
    private JButton removeButtton = new JButton("Remove");
    
    public PastureListView() {
        setSize(this.size);
        
        topRightMenu.setLayout(new BoxLayout(topRightMenu, BoxLayout.PAGE_AXIS));
        topRightMenu.setSize(new Dimension(40, 40));
        topRightMenu.add(newButtton);
        topRightMenu.add(UpdateButtton);
        topRightMenu.add(removeButtton);
        
        topLeftFields.setLayout(new GridLayout(2, 4));
        topLeftFields.add(new JTextField());
        topLeftFields.add(new JTextField());
        topLeftFields.add(new JTextField());
        topLeftFields.add(new JTextField());
        
        top.add(new JScrollPane(topLeftFields), BorderLayout.EAST);
        top.add(topRightMenu, BorderLayout.WEST);
        
        splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT, top, new JScrollPane(pastureTable));
        splitter.setDividerLocation(150);
        
        setContentPane(splitter);
        setVisible(true);
    }
}
