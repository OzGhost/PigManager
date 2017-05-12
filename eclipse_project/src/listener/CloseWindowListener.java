package listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Handle window close event
 * @author ducnh
 * create: 15-04-2017
 */
public class CloseWindowListener extends WindowAdapter {
    public void windowClosing(WindowEvent e){
        e.getWindow().setVisible(false);
        System.exit(0);
    }
}
