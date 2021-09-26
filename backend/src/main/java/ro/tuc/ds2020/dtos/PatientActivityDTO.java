package ro.tuc.ds2020.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class PatientActivityDTO {

    @NotNull
    private UUID id_patient;

    @NotNull
    private String activity;

    @NotNull

    private long start;

    @NotNull

    private long end;

    public PatientActivityDTO(){

    }

    public PatientActivityDTO(UUID id_patient, String activity, long start, long end){
        this.id_patient = id_patient;
        this.activity = activity;
        this.start = start;
        this.end = end;
    }



    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public UUID getId_patient() {
        return id_patient;
    }

    public void setId_patient(UUID id_patient) {
        this.id_patient = id_patient;
    }
}
