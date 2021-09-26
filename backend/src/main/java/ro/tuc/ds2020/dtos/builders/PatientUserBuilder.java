package ro.tuc.ds2020.dtos.builders;
import ro.tuc.ds2020.dtos.PatientUserDTO;
import ro.tuc.ds2020.entities.PatientUser;

public class PatientUserBuilder {

    private PatientUserBuilder() {
    }

    public static PatientUserDTO toPatientUserDTO(PatientUser patient) {
        return new PatientUserDTO(patient.getId(), patient.getName(), patient.getBirth_date(),patient.getGender(),patient.getMedical_record(),patient.getId_user(),patient.getUsername(),patient.getEmail(),patient.getPassword());
    }

    public static PatientUserDTO toCaregiverUserDTO(PatientUser patient) {
        return new PatientUserDTO(patient.getId(), patient.getName(), patient.getBirth_date(),patient.getGender(),patient.getId_user(),patient.getUsername(),patient.getEmail(),patient.getPassword());
    }

}
