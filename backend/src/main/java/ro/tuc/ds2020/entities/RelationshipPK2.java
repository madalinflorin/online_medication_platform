package ro.tuc.ds2020.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class RelationshipPK2 implements Serializable {


    private UUID id_patient;
    private UUID id_caregiver;

    public RelationshipPK2() {

    }

    public RelationshipPK2(UUID id_patient,UUID id_caregiver){
        this.id_patient = id_patient;
        this.id_caregiver = id_caregiver;
    }

    //Getters and setters are omitted for brevity

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        RelationshipPK2 pk = (RelationshipPK2) o;
        return Objects.equals(id_patient, pk.id_patient) &&
                Objects.equals(id_caregiver, pk.id_caregiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_patient,id_caregiver);
    }

    public UUID getId_patient() {
        return id_patient;
    }

    public void setId_patient(UUID id_patient) {
        this.id_patient = id_patient;
    }

    public UUID getId_caregiver(){
        return id_caregiver;
    }

    public void setId_caregiver(UUID id_caregiver){

        this.id_caregiver = id_caregiver;
    }


}
