package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import model.DemoModel;

public class DemoController implements ActionListener, ItemListener {

    private Observer view;
    private Observable model;
    
    public DemoController(Observer view, Observable model) {
        this.view = view;
        this.model = model;
    }
    
    public void connect() {
        if (null != model && view != null){
            model.addObserver(view);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(((JButton) e.getSource()).getText());
        ((DemoModel) model).changeState();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        
    }

}
