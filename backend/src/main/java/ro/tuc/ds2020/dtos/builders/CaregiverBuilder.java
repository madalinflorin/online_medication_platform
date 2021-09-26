package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.CaregiverDetailsDTO;
import ro.tuc.ds2020.entities.Caregiver;

public class CaregiverBuilder {

    private CaregiverBuilder() {
    }

    public static CaregiverDTO toCaregiverDTO(Caregiver caregiver) {
        return new CaregiverDTO(caregiver.getId(), caregiver.getName(), caregiver.getBirth_date(),caregiver.getGender(), caregiver.getId_user());
    }

    public static CaregiverDetailsDTO toCaregiverDetailsDTO(Caregiver caregiver) {
        return new CaregiverDetailsDTO(caregiver.getId(), caregiver.getName(), caregiver.getBirth_date(), caregiver.getGender(), caregiver.getAddress(), caregiver.getId_user());
    }

    public static Caregiver toEntity(CaregiverDetailsDTO caregiverDetailsDTO) {
        return new Caregiver(caregiverDetailsDTO.getName(), caregiverDetailsDTO.getBirth_date(), caregiverDetailsDTO.getGender(), caregiverDetailsDTO.getAddress(),caregiverDetailsDTO.getId_user());
    }

}
