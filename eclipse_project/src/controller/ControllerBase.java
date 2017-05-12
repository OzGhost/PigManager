package controller;

/**
 * Base class for controller
 * @author ducnh
 * create: 12-05-2017
 * @param <M>
 * @param <V>
 */
public class ControllerBase<M, V> {
    protected M model;
    protected V view;
    
    public void setModel(M model){
        this.model = model;
    }
    public void setView(V view) {
        this.view = view;
    }
}
