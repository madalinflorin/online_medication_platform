package ro.tuc.ds2020.dtos;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class CaregiverDetailsDTO {
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private Date birth_date;
    @NotNull
    private char gender;
    @NotNull
    private String address;
    @NotNull
    private long id_user;




    public CaregiverDetailsDTO() {
    }

    public CaregiverDetailsDTO( String name,Date birth_date,
                                char gender,String address,long id_user) {
        this.name = name;
        this.birth_date=birth_date;
        this.gender=gender;
        this.address = address;
        this.id_user = id_user;

    }

    public CaregiverDetailsDTO(UUID id, String name,Date birth_date,
                               char gender, String address,long id_user) {
        this.id = id;
        this.name = name;
        this.birth_date=birth_date;
        this.gender=gender;
        this.address = address;
        this.id_user = id_user;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaregiverDetailsDTO that = (CaregiverDetailsDTO) o;
        return
                gender == that.gender && id_user == that.id_user && Objects.equals(name, that.name) &&
                        Objects.equals(birth_date, that.birth_date) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birth_date, gender, address,id_user);
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

    public String getAddress() {
        return address;
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
