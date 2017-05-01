package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewBase extends JFrame {

    private static final long serialVersionUID = 1L;
    
    protected int height = 800;
    protected int width = 600;
    protected Dimension size = new Dimension(height, width);
}
