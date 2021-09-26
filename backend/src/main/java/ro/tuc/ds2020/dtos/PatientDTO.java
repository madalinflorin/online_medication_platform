package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class PatientDTO extends RepresentationModel<PatientDTO> {
    private UUID id;
    private String name;
    private Date birth_date;
    private char gender;
    private String medical_record;

    private long id_user;

    public PatientDTO() {
    }

    public PatientDTO(UUID id, String name, Date birth_date, char gender, String medical_record,long id_user) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.medical_record = medical_record;
        this.id_user = id_user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDTO patientDTO = (PatientDTO) o;
        return gender == patientDTO.gender && id_user == patientDTO.id_user &&
                Objects.equals(name, patientDTO.name) && Objects.equals(birth_date, patientDTO.birth_date) &&
                Objects.equals(medical_record, patientDTO.medical_record);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birth_date, gender, medical_record, id_user);
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getMedical_record() {
        return medical_record;
    }

    public void setMedical_record(String medical_record) {
        this.medical_record = medical_record;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }
}