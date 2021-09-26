package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Medical_plan  implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Type(type = "uuid-binary")
    @Column(name = "id_patient", nullable = false)
    private UUID id_patient;




    public Medical_plan() {
    }

    public Medical_plan(UUID id, UUID id_patient) {
        this.id = id;
        this.id_patient = id_patient;
    }

    public Medical_plan(UUID id_patient){
        this.id_patient = id_patient;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId_patient() {
        return id_patient;
    }

    public void setId_patient(UUID id_patient) {
        this.id_patient = id_patient;
    }
}