package ro.tuc.ds2020.dtos;

public class CaregiverPatient {

     private String username_caregiver;
     private String username_patient;

     public CaregiverPatient(){

     }

     public CaregiverPatient(String username_caregiver,String username_patient){
         this.username_caregiver = username_caregiver;
         this.username_patient = username_patient;
     }

    public String getUsername_caregiver() {
        return username_caregiver;
    }

    public void setUsername_caregiver(String username_caregiver) {
        this.username_caregiver = username_caregiver;
    }

    public String getUsername_patient() {
        return username_patient;
    }

    public void setUsername_patient(String username_patient) {
        this.username_patient = username_patient;
    }
}
