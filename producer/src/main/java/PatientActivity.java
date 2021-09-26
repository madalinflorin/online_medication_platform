import java.io.Serializable;
import java.util.UUID;

public class PatientActivity implements Serializable {

    private UUID id_patient;

    private String activity;

    private long start;

    private long end;


    public PatientActivity(){

    }

    public PatientActivity(UUID id_patient, String activity, long start, long end){
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
