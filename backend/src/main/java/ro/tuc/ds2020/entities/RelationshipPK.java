package ro.tuc.ds2020.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class RelationshipPK implements Serializable {


    private UUID id_medical_plan;
    private UUID id_medication;

    public RelationshipPK() {

    }

    public RelationshipPK(UUID id_medical_plan,UUID id_medication){
        this.id_medical_plan = id_medical_plan;
        this.id_medication = id_medication;
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
        RelationshipPK pk = (RelationshipPK) o;
        return Objects.equals(id_medical_plan, pk.id_medical_plan) &&
                Objects.equals(id_medication, pk.id_medication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_medical_plan,id_medication);
    }

    public UUID getId_medical_plan(){
        return id_medical_plan;
    }

    public void setId_medical_plan(UUID id_medical_plan){
        this.id_medical_plan = id_medical_plan;
    }

    public UUID getId_medication(){
        return id_medication;

    }

    public void setId_medication(UUID id_medication){
        this.id_medication = id_medication;
    }
}
