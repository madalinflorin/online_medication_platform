package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity

public class PatientActivity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    @Column(name = "id",nullable = false)

    private UUID id;


    @Type(type = "uuid-binary")
    @Column(name = "id_patient", nullable = false)

    private UUID id_patient;

    @Column(name = "activity", nullable = false)

    private String activity;

    @Column(columnDefinition = "date",name = "start_date", nullable = false)

    private Date start_date;

    @Column(columnDefinition = "date",name = "end_date", nullable = false)

    private Date end_date;

    public PatientActivity(){

    }

    public PatientActivity(UUID id_patient, String activity, Date start_date, Date end_date){
        this.id_patient = id_patient;
        this.activity = activity;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public PatientActivity(UUID id,UUID id_patient, String activity, Date start_date, Date end_date){
        this.id = id;
        this.id_patient = id_patient;
        this.activity = activity;
        this.start_date = start_date;
        this.end_date = end_date;
    }


    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId_patient() {
        return id_patient;
    }

    public void setId_patient(UUID id_patient) {
        this.id_patient = id_patient;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
