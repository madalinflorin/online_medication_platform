package project.myresources;

import java.util.Date;
import java.util.List;

public interface MedicationPlanService{

    List<String> getMedicalPlan(Date dateFromClient);
    void sendMessage(String message);

}
