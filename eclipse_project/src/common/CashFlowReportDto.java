package common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.CashFlow;

public class CashFlowReportDto {
    private Date time;
    private long cash;
    private String type;
    
    public static List<Object> cast(List<CashFlow> l) {
        final List<Object> rs = new ArrayList<>();
        l.forEach(e -> rs.add(e.toReportDto()));
        return rs;
    }
    
    public CashFlowReportDto () {
        
    }
    
    public CashFlowReportDto (Date time, long cash, String type) {
        this.time = time;
        this.cash = cash;
        this.type = type;
    }

    // Getter
    public Date getTime() {
        return this.time;
    }
    public long getCash() {
        return this.cash;
    }
    public String getType() {
        return this.type;
    }

    // Setter
    public void setTime(Date time) {
        this.time = time;
    }
    public void setCash(long cash) {
        this.cash = cash;
    }
    public void setType(String type) {
        this.type = type;
    }
}
