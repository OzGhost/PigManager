package common;

import java.util.Date;

public class CashFlowReportDto {
    private Date time;
    private long cash;

    // Getter
    public Date getTime() {
        return this.time;
    }
    public long getCash() {
        return this.cash;
    }

    // Setter
    public void setTime(Date time) {
        this.time = time;
    }
    public void setCash(long cash) {
        this.cash = cash;
    }
}
