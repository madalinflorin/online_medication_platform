package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.MedicalPlansDetailDTO;
import ro.tuc.ds2020.entities.Medical_plan;
import ro.tuc.ds2020.services.MedicalPlanService;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/medical_plan")
@PreAuthorize("hasRole('DOCTOR')")

public class MedicalPlanController {


    private final MedicalPlanService medicalPlanService;

    @Autowired
    public MedicalPlanController(MedicalPlanService medicalPlanServiceService) {
        this.medicalPlanService = medicalPlanServiceService;
    }


    @PostMapping(value = "/insert")
    public ResponseEntity<String> insertMedicationsToMedicalPlan(@RequestBody MedicalPlansDetailDTO medicalPlansDetailDTO) {

        medicalPlanService.insertMedicationsToMedicalPlan(medicalPlansDetailDTO);
        return new ResponseEntity<>("Succes operation for insert", HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> insertMedicalPlan(@RequestBody String username) {

        username = username.substring(1,username.length()-1);
        medicalPlanService.insert(username);
        return new ResponseEntity<>("Succes operation for delete", HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<List<Medical_plan>> getMedicalPlans() {
        List<Medical_plan> dtos = medicalPlanService.findMedicalPlans();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @GetMapping(value = "/{user}")
    @PreAuthorize("hasRole('PATIENT')")

    public ResponseEntity<List<MedicalPlansDetailDTO>> getMedicalPlans(@PathVariable String user) {

        List<MedicalPlansDetailDTO> dtos = medicalPlanService.findMedicalPlansForPatient(user);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @GetMapping(value = "/caregiver/{user}/{userCaregiver}")
    @PreAuthorize("hasRole('CAREGIVER')")

    public ResponseEntity<List<MedicalPlansDetailDTO>> getMedicalPlansForPatient(@PathVariable String user, @PathVariable String userCaregiver) {

        System.out.println(user+" "+userCaregiver);

        List<MedicalPlansDetailDTO> dtos = medicalPlanService.findMedicalPlansForPatient1(user,userCaregiver);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }



}
