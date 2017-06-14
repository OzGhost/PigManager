package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import common.Constants;
import common.Watcher;
import model.ModelBase;
import view.ViewBase;

/**
 * Base class for controller
 * @author ducnh
 * create: 12-05-2017
 * @param <M>
 * @param <V>
 */
public class ControllerBase<M extends ModelBase, V extends ViewBase>
    implements ActionListener
{
    protected M model;
    protected V view;
    protected Watcher watcher;
    
    public void setModel(M model){
        this.model = model;
    }
    public void setView(V view) {
        this.view = view;
    }
    public void setWatcher(Watcher watcher) {
        this.watcher = watcher;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        final String cmd = e.getActionCommand();
        if (cmd == null )
            return;
        if (Constants.AC_BACK.equals(cmd)) {
            view.dispose();
            if (watcher != null){
                watcher.beNoticed(cmd, 0);
            }
        }
    }
}
