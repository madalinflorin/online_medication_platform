package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.PatientDetailsDTO;
import ro.tuc.ds2020.dtos.PatientUserDTO;
import ro.tuc.ds2020.services.MedicalPlanService;
import ro.tuc.ds2020.services.MedicationService;
import ro.tuc.ds2020.services.PatientService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/patient")
@PreAuthorize("hasRole('DOCTOR')")
public class PatientController {

    private final PatientService patientService;
    private final MedicalPlanService medicalPlanService;
    private final MedicationService medicationService;

    @Autowired
    public PatientController(PatientService patientService,MedicalPlanService medicalPlanService,MedicationService medicationService) {
        this.patientService = patientService;
        this.medicalPlanService = medicalPlanService;
        this.medicationService = medicationService;
    }

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> dtos = patientService.findPatients();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }



    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody PatientDetailsDTO patientDTO) {
        UUID patientID = patientService.insert(patientDTO);
        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR')")

    @GetMapping(value = "/select/{username}")
    public ResponseEntity<List<PatientUserDTO>> getPatient(@PathVariable("username") String username) {
        List<PatientUserDTO> dtos = patientService.findPatientUser1(username);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{username}")
    public ResponseEntity<String> deletePatient(@PathVariable("username") String username){
        patientService.deleteUser(username);
        return new ResponseEntity<>("Succes operation for delete", HttpStatus.OK);
    }

    @PutMapping()

    public ResponseEntity<String> updatePatient(@RequestBody PatientUserDTO patientUserDTO){

        patientService.updatePatient(patientUserDTO);
        return new ResponseEntity<>("Succes operation for update", HttpStatus.OK);
    }

    @GetMapping(value = "/caregiver/{user}")
    @PreAuthorize("hasRole('CAREGIVER')")

    public ResponseEntity<List<PatientUserDTO>> getPatientsUsersForCaregiver(@PathVariable String user) {

        List<PatientUserDTO> dtos = patientService.findPatientsUsersForCaregiver(user);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
