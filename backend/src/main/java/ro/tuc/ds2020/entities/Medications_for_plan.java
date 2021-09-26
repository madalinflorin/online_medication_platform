package ro.tuc.ds2020.entities;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;
import java.sql.Time;

@Entity
@IdClass(RelationshipPK.class)
public class Medications_for_plan implements Serializable {

    @Id
    @Type(type = "uuid-binary")
    @Column(name = "id_medical_plan", nullable = false)
    private UUID id_medical_plan;

    @Id
    @Type(type = "uuid-binary")
    @Column(name = "id_medication", nullable = false)
    private UUID id_medication;

    @Column(columnDefinition = "date",name = "start_period_tratament", nullable = false)


    private Date start_period_tratament;


    @Column(columnDefinition = "date",name = "end_period_tratament", nullable = false)

    private Date end_period_tratament;


    LocalTime intake_interval_start ;


    LocalTime intake_interval_end ;

    public Medications_for_plan(){

    }

    public Medications_for_plan(UUID id_medical_plan, UUID id_medication, Date start_period_tratament, Date end_period_tratament, LocalTime intake_interval_start,LocalTime intake_interval_end){
        this.id_medical_plan = id_medical_plan;
        this.id_medication = id_medication;
        this.start_period_tratament = start_period_tratament;
        this.end_period_tratament = end_period_tratament;
        this.intake_interval_start = intake_interval_start;
        this.intake_interval_end = intake_interval_end;
    }


    public UUID getId_medical_plan() {
        return id_medical_plan;
    }

    public void setId_medical_plan(UUID id_medical_plan) {
        this.id_medical_plan = id_medical_plan;
    }

    public UUID getId_medication() {
        return id_medication;
    }

    public void setId_medication(UUID id_medication) {
        this.id_medication = id_medication;
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

    public LocalTime getIntake_interval_start(){
        return intake_interval_start;
    }

    public void setIntake_interval_start(LocalTime intake_interval_start){
        this.intake_interval_start = intake_interval_start;
    }

    public LocalTime getIntake_interval_end(){
        return intake_interval_end;
    }

    public void setIntake_interval_end(LocalTime intake_interval_end){
        this.intake_interval_end = intake_interval_end;
    }
}
