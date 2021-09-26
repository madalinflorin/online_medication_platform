package ro.tuc.ds2020.dtos;

import java.util.UUID;

public class MedicationDTO {

    private UUID id;
    private String name;
    private float dosage;
    private String list_of_effects;

    public MedicationDTO(){

    }

    public MedicationDTO(UUID id,String name,float dosage,String list_of_effects) {
        if (dosage <= 0) {
            throw new NumberFormatException("Dosage <= 0");
        } else {
            this.id = id;
            this.name = name;
            this.dosage = dosage;
            this.list_of_effects = list_of_effects;
        }
    }

    public MedicationDTO(String name,float dosage,String list_of_effects){
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.list_of_effects = list_of_effects;
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

    public float getDosage() {
        return dosage;
    }

    public void setDosage(float dosage) {
        this.dosage = dosage;
    }

    public void setList_of_effects(String list_of_effects)
    {
        this.list_of_effects = list_of_effects;
    }

    public String getList_of_effects(){
        return this.list_of_effects;
    }


}
