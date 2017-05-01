package model;

import java.util.Observable;

public class DemoModel extends Observable {
    private String secret;
    private String anotherSecret;
    private int counter = 0;
    public DemoModel() {
        secret = "this is secret";
        anotherSecret = "this is just one more secret";
    }
    public String getSecret() { return this.secret; }
    public String getAnotherSecret() { return this.anotherSecret; }
    public void changeState() {
        setChanged();
        notifyObservers(++counter);
    }
}
