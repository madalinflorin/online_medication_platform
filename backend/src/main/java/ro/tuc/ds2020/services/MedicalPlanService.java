package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.MedicalPlansDetailDTO;
import ro.tuc.ds2020.dtos.builders.MedicalPlansBuilder;
import ro.tuc.ds2020.entities.*;
import ro.tuc.ds2020.repositories.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicalPlanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaregiverService.class);
    private final MedicalPlanRepository medicalPlanRepository;
    private final PatientUserRepository patientUserRepository;
    private final MedicationRepository medicationRepository;
    private final MedicationsForPlanRepository medicationsForPlanRepository;
    private final MedicalPlansDetailRepository medicalPlansDetailRepository;

    @Autowired
    public MedicalPlanService(MedicalPlanRepository medicalPlanRepository,PatientUserRepository patientUserRepository, MedicationRepository medicationRepository, MedicationsForPlanRepository medicationsForPlanRepository, MedicalPlansDetailRepository medicalPlansDetailRepository) {
        this.medicalPlanRepository = medicalPlanRepository;
        this.patientUserRepository = patientUserRepository;
        this.medicationRepository = medicationRepository;
        this.medicationsForPlanRepository = medicationsForPlanRepository;
        this.medicalPlansDetailRepository = medicalPlansDetailRepository;

    }

    public UUID insert(String username){

        UUID patientID = patientUserRepository.searchIdByUsername(username);


        if(patientID!=null) {

            Medical_plan medical_plan = new Medical_plan(patientID);
            medicalPlanRepository.save(medical_plan);
            return patientID;

        }

        return null;

    }


    public List<Medical_plan> findMedicalPlans(){

        List<Medical_plan> medicalPlans = medicalPlanRepository.findAll();
        return medicalPlans;

    }

    public UUID insertMedicationsToMedicalPlan(MedicalPlansDetailDTO medicalPlansDetailDTO){

        Optional<Medical_plan> medicalPlan = medicalPlanRepository.findById(medicalPlansDetailDTO.getId());
        Optional<Medication> medication = medicationRepository.findById(medicalPlansDetailDTO.getId_medication());

        if(medicalPlan.isPresent() && medication.isPresent()){

            Medications_for_plan medicationsForPlan = new Medications_for_plan(medicalPlansDetailDTO.getId(),medicalPlansDetailDTO.getId_medication(),medicalPlansDetailDTO.getStart_period_tratament(),medicalPlansDetailDTO.getEnd_period_tratament(),LocalTime.parse(medicalPlansDetailDTO.getIntake_interval_start()),LocalTime.parse(medicalPlansDetailDTO.getIntake_interval_end()));
            medicationsForPlanRepository.save(medicationsForPlan);
            return medicalPlansDetailDTO.getId();

        }

        return null;

    }


    public List<MedicalPlansDetailDTO> findMedicalPlansForPatient(String username){

        List<MedicalPlansDetail> medicalPlans = new ArrayList<>();

        PatientUser user = patientUserRepository.findOnePatientUser(username);

        if(user!=null){

            medicalPlans = medicalPlansDetailRepository.findMedicalPlansForPatient(username);

        }

        return medicalPlans.stream()
                .map(MedicalPlansBuilder::toMedicalPlansDTO)
                .collect(Collectors.toList());

    }

    public List<MedicalPlansDetailDTO> findMedicalPlansForPatient1(String username, String userCaregiver){

        List<MedicalPlansDetail> medicalPlans = new ArrayList<>();

        PatientUser user = patientUserRepository.findOnePatientUserForCaregiver(username,userCaregiver);

        if(user!=null){

            medicalPlans = medicalPlansDetailRepository.findMedicalPlansForPatient(username);

        }

        return medicalPlans.stream()
                .map(MedicalPlansBuilder::toMedicalPlansDTO)
                .collect(Collectors.toList());

    }

}
