package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PatientUserDTO;
import ro.tuc.ds2020.services.PatientService;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/patient/user")
@PreAuthorize("hasRole('DOCTOR')")

public class PatientUserController {

    private final PatientService patientService;

    @Autowired
    public PatientUserController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public ResponseEntity<List<PatientUserDTO>> getPatientsUsers() {
        List<PatientUserDTO> dtos = patientService.findPatientsUsers();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


}
