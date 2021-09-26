package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.CaregiverPatient;
import ro.tuc.ds2020.services.PatientService;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/insert")
@PreAuthorize("hasRole('DOCTOR')")

public class CaregiverPatientController {

    private final PatientService patientService;

    @Autowired
    public CaregiverPatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @PostMapping()
    public ResponseEntity<String> insertLink(@RequestBody CaregiverPatient caregiverPatient) {
        patientService.makeOperations(caregiverPatient);
        return new ResponseEntity<>("Succes operation", HttpStatus.OK);
    }
}
