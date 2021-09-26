package ro.tuc.ds2020.dtos;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;
import java.util.Date;
import java.util.UUID;

public class MedicalPlansDetailDTO extends RepresentationModel<MedicalPlansDetailDTO> {


    private UUID id;

    private UUID id_medication;

    private String name;

    private float dosage;

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date start_period_tratament;

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date end_period_tratament;


    private String intake_interval_start;


    private String intake_interval_end;


    public MedicalPlansDetailDTO(){

    }

    public MedicalPlansDetailDTO(UUID id,String name,float dosage,Date start_period_tratament,Date end_period_tratament, String intake_interval_start, String intake_interval_end) {

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

    public UUID getId_medication() {
        return id_medication;
    }

    public void setId_medication(UUID id_medication) {
        this.id_medication = id_medication;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIntake_interval_start() {
        return intake_interval_start;
    }

    public void setIntake_interval_start(String intake_interval_start) {
        this.intake_interval_start = intake_interval_start;
    }

    public String getIntake_interval_end() {
        return intake_interval_end;
    }

    public void setIntake_interval_end(String intake_interval_end) {
        this.intake_interval_end = intake_interval_end;
    }
}
