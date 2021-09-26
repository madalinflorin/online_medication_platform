package ro.tuc.ds2020.entities;

import org.hibernate.annotations.Type;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Entity
public class MedicalPlansDetail implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @Type(type = "uuid-binary")

    private UUID id;

    @Column(name = "name", nullable = false)

    private String name;

    @Column(name = "dosage", nullable = false)

    private float dosage;

    @Column(columnDefinition = "date",name = "start_period_tratament", nullable = false)


    private Date start_period_tratament;


    @Column(columnDefinition = "date",name = "end_period_tratament", nullable = false)

    private Date end_period_tratament;

    private LocalTime intake_interval_start;

    private LocalTime intake_interval_end;


    public MedicalPlansDetail(){

    }

    public MedicalPlansDetail(UUID id, String name, float dosage, Date start_period_tratament, Date end_period_tratament, LocalTime intake_interval_start, LocalTime intake_interval_end) {

        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.start_period_tratament = start_period_tratament;
        this.end_period_tratament = end_period_tratament;
        this.intake_interval_start = intake_interval_start;
        this.intake_interval_end = intake_interval_end;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDosage() {
        return dosage;
    }

    public void setDosage(float dosage) {
        this.dosage = dosage;
    }

    public Date getStart_period_tratament() {
        return start_period_tratament;
    }

    public void setStart_period_tratament(Date start_period_tratament) {
        this.start_period_tratament = start_period_tratament;
    }

    public Date getEnd_period_tratament() {
        return end_period_tratament;
    }

    public void setEnd_period_tratament(Date end_period_tratament) {
        this.end_period_tratament = end_period_tratament;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public LocalTime getIntake_interval_start() {
        return intake_interval_start;
    }

    public void setIntake_interval_start(LocalTime intake_interval_start) {
        this.intake_interval_start = intake_interval_start;
    }

    public LocalTime getIntake_interval_end() {
        return intake_interval_end;
    }

    public void setIntake_interval_end(LocalTime intake_interval_end) {
        this.intake_interval_end = intake_interval_end;
    }
}
