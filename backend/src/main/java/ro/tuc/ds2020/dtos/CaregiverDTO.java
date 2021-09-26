package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class CaregiverDTO extends RepresentationModel<CaregiverDTO> {
    private UUID id;
    private String name;
    private Date birth_date;
    private char gender;
    private long id_user;



    public CaregiverDTO() {
    }

    public CaregiverDTO(UUID id, String name, Date birth_date, char gender,long id_user) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
        this.gender = gender;
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
        CaregiverDTO caregiverDTO = (CaregiverDTO) o;
        return  gender == caregiverDTO.gender && id_user == caregiverDTO.id_user && Objects.equals(name, caregiverDTO.name) &&
                Objects.equals(birth_date, caregiverDTO.birth_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,birth_date,gender,id_user);
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

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }
}
