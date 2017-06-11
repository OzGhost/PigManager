package db;

import java.util.Date;

public class SickDetail extends Entity{
    // Fields
    private Sick sick;
    private Date sickDate;
    private Date healDate;
    private String status;
    private String note;

    // Constructors
    public SickDetail (){
        super("");
    }
    public SickDetail (Sick s, Date start, Date end) {
        this.sick = s;
        this.sickDate = start;
        this.healDate = end;
    }

    // Getters
    public Sick getSick() {
        return this.sick;
    }
    public Date getSickDate() {
        return this.sickDate;
    }
    public Date getHealDate() {
        return this.healDate;
    }
    // Setters
    // Methods
    @Override
    public String toString () {
        return "\n Sick: " + sick.toString()
            + "\n start: " + sickDate
            + "\n end: " + healDate
        ;
    }
}
