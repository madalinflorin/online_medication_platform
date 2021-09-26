package ro.tuc.ds2020.entities;

import java.util.Date;

public class PatientActivitySuspicious {

    private String name;

    private String activity;

    private Date start_date_activity;

    private Date end_date_activity;

    private String warningMessage;

    public PatientActivitySuspicious(String name,String activity,Date start_date_activity,Date end_date_activity){
        this.name = name;
        this.activity = activity;
        this.start_date_activity = start_date_activity;
        this.end_date_activity = end_date_activity;
    }

    public Date getStart_date_activity() {
        return start_date_activity;
    }

    public void setStart_date_activity(Date start_date_activity) {
        this.start_date_activity = start_date_activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getEnd_date_activity() {
        return end_date_activity;
    }

    public void setEnd_date_activity(Date end_date_activity) {
        this.end_date_activity = end_date_activity;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }
}
