package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.entities.MedicalPlansDetail;
import ro.tuc.ds2020.repositories.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service

public class MedicationPlanServiceImpl implements MedicationPlanService {

    private final MedicalPlansDetailRepository medicalPlansDetailRepository;


    @Autowired
    public MedicationPlanServiceImpl(MedicalPlansDetailRepository medicalPlansDetailRepository) {

        this.medicalPlansDetailRepository = medicalPlansDetailRepository;

    }

    public void sendMessage(String message){
        System.out.println(message);
    }

    public List<String> getMedicalPlan(Date dateFromClient){
        List<String> listMedications = new ArrayList<>();
        List<MedicalPlansDetail> medicalPlansDetails = medicalPlansDetailRepository.getMedicationsForMedicalPlan();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for(MedicalPlansDetail medicalPlansDetail : medicalPlansDetails){
                listMedications.add(medicalPlansDetail.getName()+" "+medicalPlansDetail.getDosage()+" "+dateFormat.format(medicalPlansDetail.getStart_period_tratament())+" "+dateFormat.format(medicalPlansDetail.getEnd_period_tratament())+" "+medicalPlansDetail.getIntake_interval_start()+" "+medicalPlansDetail.getIntake_interval_end());
        }

        return listMedications;

    }
}
