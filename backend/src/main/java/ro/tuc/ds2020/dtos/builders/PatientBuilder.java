package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.PatientDetailsDTO;
import ro.tuc.ds2020.entities.Patient;

public class PatientBuilder {

    private PatientBuilder() {
    }

    public static PatientDTO toPatientDTO(Patient patient) {
        return new PatientDTO(patient.getId(), patient.getName(), patient.getBirth_date(),patient.getGender(),patient.getMedical_record(),patient.getId_user());
    }

    public static PatientDetailsDTO toPatientDetailsDTO(Patient patient) {
        return new PatientDetailsDTO(patient.getId(), patient.getName(), patient.getBirth_date(), patient.getGender(), patient.getAddress(),patient.getMedical_record(),patient.getId_user());
    }

    public static Patient toEntity(PatientDetailsDTO patientDetailsDTO) {
        return new Patient(patientDetailsDTO.getName(), patientDetailsDTO.getBirth_date(), patientDetailsDTO.getGender(), patientDetailsDTO.getAddress(), patientDetailsDTO.getMedical_record(),patientDetailsDTO.getId_user());
    }
}
