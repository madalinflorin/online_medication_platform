package ro.tuc.ds2020.entities;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.UUID;

@Entity
@IdClass(RelationshipPK2.class)
public class Caregiver_patient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_patient",nullable = false)
    @Type(type = "uuid-binary")
    private UUID id_patient;
    @Id
    @Column(name = "id_caregiver",nullable = false)
    @Type(type = "uuid-binary")
    private UUID id_caregiver;
    public Caregiver_patient(){};
    public Caregiver_patient(UUID id_patient,UUID id_caregiver){
        this.id_patient=id_patient;
        this.id_caregiver=id_caregiver;
    };

    public UUID getId_caregiver() {
        return id_caregiver;
    }

    public void setId_caregiver(UUID id_caregiver) {
        this.id_caregiver = id_caregiver;
    }

    public UUID getId_patient() {
        return id_patient;
    }

    public void setId_patient(UUID id_patient) {
        this.id_patient = id_patient;
    }
}
