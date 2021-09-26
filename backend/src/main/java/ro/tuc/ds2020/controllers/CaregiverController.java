package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.services.CaregiverService;
import ro.tuc.ds2020.services.PatientService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/caregiver")
public class CaregiverController {

    private final CaregiverService caregiverService;
    private final PatientService patientService;

    @Autowired
    public CaregiverController(CaregiverService caregiverService,PatientService patientService) {
        this.caregiverService = caregiverService;
        this.patientService = patientService;
    }

    @GetMapping()
    public ResponseEntity<List<CaregiverDTO>> getCaregivers() {
        List<CaregiverDTO> dtos = caregiverService.findCaregivers();
        for (CaregiverDTO dto : dtos) {
            Link caregiverLink = linkTo(methodOn(CaregiverController.class)
                    .getCaregiver(dto.getId())).withRel("caregiverDetails");
            dto.add(caregiverLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<PatientUserDTO>> getCaregiversUsers() {
        List<PatientUserDTO> dtos = patientService.findCaregiversUsers();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/select/{username}")
    public ResponseEntity<List<PatientUserDTO>> getCaregiver(@PathVariable("username") String username) {
        List<PatientUserDTO> dto = patientService.findCaregiverUser1(username);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody CaregiverDetailsDTO caregiverDTO) {
        UUID caregiverID = caregiverService.insert(caregiverDTO);
        return new ResponseEntity<>(caregiverID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CaregiverDetailsDTO> getCaregiver(@PathVariable("id") UUID caregiverId) {
        CaregiverDetailsDTO dto = caregiverService.findCaregiverById(caregiverId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{username}")
    public ResponseEntity<?>  deleteCaregiver(@PathVariable("username") String username) {


       caregiverService.deleteCaregiverByUsername(username);

        return ResponseEntity.ok("Resource deleted");

    }

    @PutMapping()

    public ResponseEntity<?> updateCaregiver(@RequestBody PatientUserDTO patientUserDTO){

        patientService.updateCaregiver(patientUserDTO);
        return ResponseEntity.ok("Resource updated");
    }

}
