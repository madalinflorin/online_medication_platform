package ro.tuc.ds2020.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class RelationshipPK1 implements Serializable {


    private UUID id_medication;
    private UUID id_effect;

    public RelationshipPK1() {

    }

    public RelationshipPK1(UUID id_medication,UUID id_effect){

        this.id_medication = id_medication;
        this.id_effect = id_effect;
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
        RelationshipPK1 pk = (RelationshipPK1) o;
        return Objects.equals(id_effect, pk.id_effect) &&
                Objects.equals(id_medication, pk.id_medication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_medication,id_effect);
    }


    public UUID getId_medication(){
        return id_medication;

    }

    public void setId_medication(UUID id_medication){
        this.id_medication = id_medication;
    }

    public UUID getId_effect(){
        return this.id_effect;

    }

    public void setId_effect(UUID id_effect){
        this.id_effect = id_effect;
    }
}