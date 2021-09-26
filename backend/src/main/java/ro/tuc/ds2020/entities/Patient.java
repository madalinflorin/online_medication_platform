package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    @Column(name = "id",nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(columnDefinition = "date",name = "birth_date", nullable = false)
    private Date birth_date;

    @Column(name = "gender", nullable = false)
    private char gender;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "medical_record", nullable = false)
    private String medical_record;

    @Column(name = "id_user", nullable = true)

    private long id_user;


    public Patient() {
    }

    public Patient(String name, Date birth_date,char gender
            ,String address, String medical_record,long id_user) {
        this.name = name;
        this.birth_date=birth_date;
        this.gender=gender;
        this.address = address;
        this.medical_record=medical_record;
        this.id_user = id_user;

    }

    public Patient(UUID id, String name, Date birth_date,char gender
            ,String address, String medical_record,long id_user) {
        this.id = id;
        this.name = name;
        this.birth_date=birth_date;
        this.gender=gender;
        this.address = address;
        this.medical_record=medical_record;
        this.id_user = id_user;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedical_record() {
        return medical_record;
    }

    public void setMedical_record(String medical_record) {
        this.medical_record = medical_record;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

}
