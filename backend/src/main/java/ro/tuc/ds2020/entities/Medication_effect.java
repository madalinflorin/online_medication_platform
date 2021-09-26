package ro.tuc.ds2020.entities;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.UUID;

@Entity
@IdClass(RelationshipPK1.class)
public class Medication_effect implements Serializable {

    @Id
    @Type(type = "uuid-binary")
    @Column(name = "id_medication", nullable = false)
    private UUID id_medication;


    @Id
    @Type(type = "uuid-binary")
    @Column(name = "id_effect", nullable = false)
    private UUID id_effect;

    public Medication_effect(){

    }


    public Medication_effect(UUID id_medication,UUID id_effect){
        this.id_medication= id_medication;
        this.id_effect = id_effect;
    }

    public UUID getId_medication() {
        return id_medication;
    }

    public void setId_medication(UUID id_medication) {
        this.id_medication = id_medication;
    }

    public UUID getId_effect() {
        return id_effect;
    }

    public void setId_effect(UUID id_effect) {
        this.id_effect = id_effect;
    }
}
