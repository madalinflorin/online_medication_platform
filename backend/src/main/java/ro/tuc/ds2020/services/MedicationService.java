package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.builders.MedicationBuilder;
import ro.tuc.ds2020.entities.*;
import ro.tuc.ds2020.repositories.EffectRepository;
import ro.tuc.ds2020.repositories.MedicationEffectRepository;
import ro.tuc.ds2020.repositories.MedicationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service

public class MedicationService {


    private static final Logger LOGGER = LoggerFactory.getLogger(CaregiverService.class);
    private final MedicationRepository medicationRepository;
    private final MedicationEffectRepository medicationEffectRepository;
    private final EffectRepository effectRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository,MedicationEffectRepository medicationEffectRepository,EffectRepository effectRepository) {
        this.medicationRepository = medicationRepository;
        this.medicationEffectRepository = medicationEffectRepository;
        this.effectRepository = effectRepository;

    }


    public List<MedicationDTO> findMedications() {



        List<Medication> medicationList = medicationRepository.findAll();
        List<MedicationDTO> medicationDTOList = new ArrayList<>();

        convert(medicationList, medicationDTOList);

        return medicationDTOList;

    }


    public UUID insert(MedicationDTO medicationDTO) {

        Medication medication = MedicationBuilder.toEntity(medicationDTO);
        medicationRepository.save(medication);

        String[] list_of_effects = medicationDTO.getList_of_effects().split(",");

        for(int i=0;i<list_of_effects.length;i++){


            Effect effect = new Effect(list_of_effects[i]);
            effectRepository.save(effect);
            Medication_effect medicationEffect = new Medication_effect(medication.getId(),effect.getId());
            medicationEffectRepository.save(medicationEffect);


        }


        return medication.getId();
    }


    public List<MedicationDTO> findMedicationById(UUID id) {

        List<Medication> medicationList = new ArrayList<>();
        List<MedicationDTO> medicationDTOList = new ArrayList<>();
        Optional<Medication> medication = medicationRepository.findById(id);

        if (!medication.isPresent()) {
            LOGGER.error("Medication with id {} was not found in db", id);
        }

        else {
            medicationList.add(medication.get());

            convert(medicationList, medicationDTOList);
        }

        return medicationDTOList;


    }




    private void convert(List<Medication> medicationList, List<MedicationDTO> medicationDTOList) {
        for(int i=0;i<medicationList.size();i++) {
            List<Medication_effect> medicationEffect = medicationEffectRepository.findAllById_medication(PatientService.asBytes(medicationList.get(i).getId()));

            String list_of_effects = "";

            for(int j=0;j<medicationEffect.size();j++){

                Optional<Effect> effect = effectRepository.findById(medicationEffect.get(j).getId_effect());

                if(j==0) list_of_effects = list_of_effects + effect.get().getName();

                else {
                    list_of_effects = list_of_effects + "," + effect.get().getName();
                }


            }

            MedicationDTO medicationDTO = new MedicationDTO(medicationList.get(i).getId(),medicationList.get(i).getName(),medicationList.get(i).getDosage(),list_of_effects);
            medicationDTOList.add(medicationDTO);
        }
    }


    public boolean deleteMedicationById(UUID id) {


        List<Medication_effect> medicationEffect = medicationEffectRepository.findAllById_medication(PatientService.asBytes(id));

        if(medicationEffect.size()>0){

            for(int i=0;i<medicationEffect.size();i++){
                effectRepository.deleteById(medicationEffect.get(i).getId_effect());
            }

        }

        medicationRepository.deleteById(id);

        return true;
    }

    public UUID updateMedication(MedicationDTO medicationDTO) {
        this.deleteMedicationById(medicationDTO.getId());
        UUID newID = this.insert(medicationDTO);
        medicationRepository.updateMedication(newID,medicationDTO.getId());

        return medicationDTO.getId();

    }




}
