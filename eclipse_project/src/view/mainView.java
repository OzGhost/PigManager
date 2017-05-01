package view;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import listener.CloseWindowListener;
import listener.PastureListListener;

public class mainView extends ViewBase {

    private static final long serialVersionUID = 1L;
    
    private JPanel panel = new JPanel();
    private GridLayout layout = new GridLayout(2, 2);
    private JButton pastureButton = new JButton("Pasture");
    private JButton stockButton = new JButton("Stock");
    private JButton providerButton = new JButton("Provider");
    private JButton toolButton = new JButton("Tool");
    
    public mainView() {
        this.setSize(this.size);
        this.setLocationRelativeTo(null);
        
        this.panel.setLayout(layout);
        pastureButton.addActionListener(new PastureListListener());
        this.panel.add(pastureButton);
        this.panel.add(stockButton);
        this.panel.add(providerButton);
        this.panel.add(toolButton);
        
        this.setContentPane(panel);
        
        addWindowListener(new CloseWindowListener());
        this.setVisible(true);
    }
}
