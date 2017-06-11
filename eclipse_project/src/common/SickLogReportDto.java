package common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.SickLog;

public class SickLogReportDto {
    // Static methods
    public static List<SickLogReportDto> cast (SickLog sickLog) {
        final List<SickLogReportDto> rs = new ArrayList<>();
        final String earTag = sickLog.getPig().getEarTag();
        sickLog.getSickDetails().forEach(sd -> {
            rs.add(
                new SickLogReportDto(
                    earTag,
                    sd.getSick().getName(),
                    sd.getSickDate(),
                    sd.getHealDate()
                )
            );
        });
        return rs;
    }
    // Fields
    private String earTag;
    private String sickName;
    private Date sickDate;
    private Date healDate;
    // Constructors
    public SickLogReportDto (
            String earTag,
            String sickName,
            Date sickDate,
            Date healDate
    ) {
        this.earTag = earTag;
        this.sickName = sickName;
        this.sickDate = sickDate;
        this.healDate = healDate;
    }
    // Getters
    public String getEarTag () {
        return this.earTag;
    }
    public String getSickName () {
        return this.sickName;
    }
    public Date getSickDate () {
        return this.sickDate;
    }
    public Date getHealDate () {
        return this.healDate;
    }

    // Setters

    // Methods
    @Override
    public String toString () {
        return "\n earTag: " + earTag
            + "\n sickName: " + sickName
            + "\n sickDate: " + sickDate
            + "\n healDate: " + healDate
        ;
    }
}
