package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.services.MedicationService;
import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/medications")
@PreAuthorize("hasRole('DOCTOR')")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }


    @GetMapping()
    public ResponseEntity<List<MedicationDTO>> getMedications() {
        List<MedicationDTO> dtos = medicationService.findMedications();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<UUID> insertMedication(@RequestBody MedicationDTO medicationDTO) {
        UUID medicationID = medicationService.insert(medicationDTO);
        return new ResponseEntity<>(medicationID, HttpStatus.CREATED);
    }



    @GetMapping(value = "/{id}")
    public ResponseEntity<List<MedicationDTO>> getMedication(@PathVariable("id") UUID medicationId) {
        List<MedicationDTO> dto = medicationService.findMedicationById(medicationId);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteMedication(@PathVariable("id") UUID medicationId) {
        medicationService.deleteMedicationById(medicationId);
        return new ResponseEntity<>("Succes operation for delete", HttpStatus.OK);
    }

    @PutMapping()

    public ResponseEntity<String> updateMedication(@RequestBody MedicationDTO medicationDTO){

        medicationService.updateMedication(medicationDTO);
        return new ResponseEntity<>("Succes operation for update", HttpStatus.OK);
    }

}
