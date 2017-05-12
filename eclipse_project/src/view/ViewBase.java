package view;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public abstract class ViewBase extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    
    protected int height = 800;
    protected int width = 600;
    protected Dimension size = new Dimension(height, width);
    
    /**
     * Show message to user
     * @param code
     */
    abstract void notice(short code);
    
    @Override
    public void update(Observable o, Object arg) {
    }
}
