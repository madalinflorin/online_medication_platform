package ro.tuc.ds2020.dtos;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class PatientUserDTO extends RepresentationModel<PatientUserDTO> {
    private UUID id;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date birth_date;

    private char gender;

    private String address;

    private String medical_record;

    private long id_user;

    private String username;

    private String email;

    private String password;

    public PatientUserDTO() {
    }

    public PatientUserDTO(UUID id, String name, Date birth_date, char gender, String medical_record,long id_user,String username,String email,String password) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.medical_record = medical_record;
        this.id_user = id_user;
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public PatientUserDTO(UUID id, String name, Date birth_date, char gender,long id_user,String username,String email,String password) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.id_user = id_user;
        this.username = username;
        this.email = email;
        this.password = password;
    }



    public PatientUserDTO(String name, Date birth_date, char gender,String address,String medical_record, long id_user,String username,String email,String password) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.id_user = id_user;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.medical_record = medical_record;
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
        PatientUserDTO patientDTO = (PatientUserDTO) o;
        return gender == patientDTO.gender && id_user == patientDTO.id_user &&
                Objects.equals(name, patientDTO.name) && Objects.equals(birth_date, patientDTO.birth_date) &&
                Objects.equals(medical_record, patientDTO.medical_record) &&
                Objects.equals(username, patientDTO.username) && Objects.equals(email, patientDTO.email)
                && Objects.equals(password, patientDTO.password)
                && Objects.equals(address, patientDTO.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birth_date, gender, address, medical_record, id_user,username,email,password);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
