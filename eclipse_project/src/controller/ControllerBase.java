package controller;


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
