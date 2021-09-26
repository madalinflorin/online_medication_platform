package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MedicalPlansDetailDTO;
import ro.tuc.ds2020.entities.MedicalPlansDetail;

public class MedicalPlansBuilder {


    public static MedicalPlansDetailDTO toMedicalPlansDTO(MedicalPlansDetail medicalPlan) {
        return new MedicalPlansDetailDTO(medicalPlan.getId(), medicalPlan.getName(), medicalPlan.getDosage(),medicalPlan.getStart_period_tratament(),medicalPlan.getEnd_period_tratament(), medicalPlan.getIntake_interval_start().toString(), medicalPlan.getIntake_interval_end().toString());
    }

}
