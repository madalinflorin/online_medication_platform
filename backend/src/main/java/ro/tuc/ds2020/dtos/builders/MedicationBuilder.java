package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.entities.Medication;

public class MedicationBuilder {


    public static Medication toEntity(MedicationDTO medicationDTO) {



        return new Medication(medicationDTO.getName(),
                medicationDTO.getDosage());
    }

}
